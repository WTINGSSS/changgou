package com.czxy.mapper;

import com.czxy.pojo.Spu;
import org.apache.ibatis.annotations.*;

/**
 * @author 庭前云落
 * @Date 2020/4/15 9:46
 * @description
 */
@Mapper
public interface SpuMapper extends tk.mybatis.mapper.common.Mapper<Spu> {

    @Select("SELECT * FROM tb_spu WHERE id=#{spu_id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "spuName",column = "spu_name"),
            @Result(property = "spuSubname",column = "spu_subname"),
            @Result(property = "logo",column = "logo"),
            @Result(property = "cat1Id",column = "cat1_id"),
            @Result(property = "cat2Id",column = "cat2_id"),
            @Result(property = "cat3Id",column = "cat3_id"),
            @Result(property = "brandId",column = "brand_id"),
            @Result(property = "checkTime",column = "check_time"),
            @Result(property = "checkStatus",column = "check_status"),
            @Result(property = "price",column = "price"),
            @Result(property = "isOnSale",column = "is_on_sale"),
            @Result(property = "onSaleTime",column = "on_sale_time"),
            @Result(property = "deletedAt",column = "deleted_at"),
            @Result(property = "weight",column = "weight"),
            @Result(property = "description",column = "description"),
            @Result(property = "packages",column = "packages"),
            @Result(property = "aftersale",column = "aftersale"),
            @Result(property = "specList",column = "spec_list"),
            @Result(property = "createdAt",column = "created_at"),
            @Result(property = "updatedAt",column = "updated_at"),
            @Result(property = "brand",column = "brand_Id",one = @One(select = "com.czxy.mapper.BrandMapper.selectByPrimaryKey"))
    })
    Spu findSpuById(@Param("spu_id")Integer spu_id);
}
