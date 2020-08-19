package com.czxy.mapper;

import com.czxy.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    @Select("select * from tb_user where username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM tb_user WHERE mobile = #{mobile}")
    User findByMybile(@Param("mobile") String mobile);
}