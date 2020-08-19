package com.czxy.feign;

import com.czxy.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 庭前云落
 * @Date 2020/3/26 11:41
 * @description
 */
@FeignClient(value = "cgwebservice",path = "/user")
public interface UserFeign {

    @PostMapping("/findByUsername")
    public User findByUsername(User user);
}
