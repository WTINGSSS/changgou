package com.czxy.mapper;

import com.czxy.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author 庭前云落
 * @Date 2020/5/5 15:03
 * @description
 */
@Mapper
public interface OrderMapper extends tk.mybatis.mapper.common.Mapper<Order> {
    /**
     * 修改订单的状态
     * @param sn
     * @param status
     */
    @Update("UPDATE tb_order SET STATUS = #{status} WHERE sn = #{sn}")
    void updateOrderStatus(@Param("sn") String sn, @Param("status") String status);
}
