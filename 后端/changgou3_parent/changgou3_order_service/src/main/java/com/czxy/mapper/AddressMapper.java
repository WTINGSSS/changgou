package com.czxy.mapper;

import com.czxy.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author 庭前云落
 * @Date 2020/5/3 19:29
 * @description
 */
@Mapper
public interface AddressMapper extends tk.mybatis.mapper.common.Mapper<Address> {

    /**
     * 修改用户默认地址
     */
    @Update("update tb_address set isdefault = #{defaultValue} where user_id = #{userId}")
    void updateDefault(@Param("userId") Integer userId, @Param("defaultValue") int defaultValue);
}
