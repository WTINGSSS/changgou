package com.czxy.filter;

import com.czxy.config.FilterProperties;
import com.czxy.config.JwtProperties;
import com.czxy.pojo.User;
import com.czxy.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 庭前云落
 * @Date 2020/3/29 14:51
 * @description
 */
@Component
//2.1 加载JWT配置类
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    //2.2 注入jwt配置类实例
    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() { // 3 当前过滤器是否执行,true执行,false不执行
        //1、获得工具类(请求上下文)
        RequestContext currentContext = RequestContext.getCurrentContext();
        //2、获得请求对象
        HttpServletRequest request = currentContext.getRequest();
        //3、获得请求路径 v3/authservice/login
        String requestURI = request.getRequestURI();
        //3.2 如果路径是 /authservice/login 当前拦截器不执行
        for (String path : filterProperties.getAllowPaths()) {
            //判断包含
            if (requestURI.contains(path)) {
                return false;
            }
        }
        //执行run()方法
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //1、获得token
        //1.1 获得上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //1.2 获得request对象
        HttpServletRequest request = currentContext.getRequest();
        //1.3 获得指定请求头的值
        String token = request.getHeader("Authorization");

        //2 校验 token --- 使用 JWT工具类进行解析
        //2.3 使用工具类 通过公钥获得对应信息
        try {
            JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            //2.4 如果有异常 -- 没有登录(没有权限)
            currentContext.addOriginResponseHeader("content-type", "text/html;charset=UTF-8");
            currentContext.addZuulResponseHeader("content-type", "text/html;charset=UTF-8");
            currentContext.setResponseStatusCode(403);
            currentContext.setResponseBody("token失效或无效");
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }
}
