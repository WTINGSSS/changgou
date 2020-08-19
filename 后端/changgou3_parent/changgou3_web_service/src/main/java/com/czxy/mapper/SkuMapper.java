package com.czxy.mapper;

import com.czxy.pojo.Sku;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/15 9:46
 * @description
 */
@org.apache.ibatis.annotations.Mapper
public interface SkuMapper extends Mapper<Sku> {

    @Select("SELECT * FROM tb_sku")
    @Results(id = "skuResult",value = {
            @Result(property = "id",column = "id") ,
            @Result(property = "stock",column = "stock") ,
            @Result(property = "spuId",column = "spu_id") ,
            @Result(property = "skuName",column = "sku_name") ,
            @Result(property = "images",column = "images") ,
            @Result(property = "price",column = "price") ,
            @Result(property = "specInfoIdList",column = "spec_info_id_list") ,
            @Result(property = "specInfoIdTxt",column = "spec_info_id_txt") ,
            @Result(property = "spu",column = "spu_id",one = @One(select = "com.czxy.mapper.SpuMapper.findSpuById"))
    })
    List<Sku> findAllSkus();

    //查询指定 spuId的所有sku
    @Select("select * from tb_sku where spu_id = #{spuId}")
    @ResultMap("skuResult")
    List<Sku> findSkuBySpuId(@Param("spuId") Integer spuId);
}
