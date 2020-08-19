package com.czxy.controller;

import com.czxy.utils.PayHelper;
import com.czxy.utils.PayState;
import com.czxy.vo.BaseResult;
import com.czxy.vo.PayRequest;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/5/8 17:43
 * @description
 */
@RestController
@RequestMapping("/pay")
public class PayController {

  @Resource
  private PayHelper payHelper;

  @Resource
  private RabbitTemplate rabbitTemplate;

  @PostMapping
  public BaseResult pay(@RequestBody PayRequest payRequest){
      String payUrl = payHelper.createPayUrl(payRequest.getSn());
      return BaseResult.ok("二维码生产成功").append("wxurl",payUrl);
  }

    @PostMapping("/callback")
    public void callback(HttpServletRequest request , HttpServletResponse response){

        try {
            //1 处理请求
            // 1.1 以流的方式，获得请求体（请求参数）
            ServletInputStream is = request.getInputStream();
            // 1.2 将流转换成字符串  "<xml></xml>"
            String xmlStr = IOUtils.toString(is, "UTF-8");
            // 1.3 将字符串转换Map
            Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);

            //2 获得订单号，修改状态
            if("SUCCESS".equals(map.get("return_code")) &&
                    "SUCCESS".equals(map.get("result_code"))){  //返回状态码  &&  业务结果
                String sn = map.get("out_trade_no");
                System.out.println(sn);

            //讲订单号存放到MQ，order_pay
             rabbitTemplate.convertAndSend("","order_pay",sn);
            //将订单号存放到MQ，order_pay_auto，完成推送
                rabbitTemplate.convertAndSend("","order_pay_auto", sn);
                //3 响应规定格式的内容
                // 3.1 响应类型
                response.setContentType("text/xml");
                // 3.2 响应内容
                response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
            } else {
                System.out.println("错误了");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{sn}")
    public BaseResult query(@PathVariable("sn") Long sn) {
        //查询
        PayState payState = payHelper.queryOrder(sn);
        //根据查询结果，返回
        if (payState.getCode() == 1) {    //成功
            return BaseResult.ok(payState.getDesc());
        } else {
            return BaseResult.error(payState.getDesc());
        }
    }
}
