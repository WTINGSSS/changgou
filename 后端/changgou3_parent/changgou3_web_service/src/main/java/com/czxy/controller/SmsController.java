package com.czxy.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.czxy.pojo.User;
import com.czxy.utils.SmsUtil;
import com.czxy.vo.BaseResult;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
/**
 * @author 庭前云落
 * @Date 2020/3/22 9:03
 * @description
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;



    @PostMapping
    public BaseResult sendSms(@RequestBody User user) {

        try {
            //1、随机生成验证码
            String code = RandomStringUtils.randomNumeric(4);
            //2、存放到redis中,key:"sms_register"+手机号, values: 验证码,5分钟
            stringRedisTemplate.opsForValue().set("sms_register" + user.getMobile(), code, 5, TimeUnit.MINUTES);
            //3、发送验证码到手机
            SendSmsResponse smsResponse = SmsUtil.sendSms(user.getMobile(),code);

            //equalsIgnoreCase() 方法用于将字符串与指定的对象比较，不考虑大小写。
            if ("OK".equalsIgnoreCase(smsResponse.getCode())) {
                return BaseResult.ok("发送成功");
            } else {
                return BaseResult.error(smsResponse.getMessage());
            }

        } catch (Exception e) {
            long end = System.currentTimeMillis();
            return BaseResult.error("发送失败");
        }

    }
}
