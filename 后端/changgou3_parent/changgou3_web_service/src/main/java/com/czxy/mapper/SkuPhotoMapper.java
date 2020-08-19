package com.czxy.mapper;

import com.czxy.pojo.SkuPhoto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/17 8:48
 * @description
 */
@org.apache.ibatis.annotations.Mapper
public interface SkuPhotoMapper extends Mapper<SkuPhoto> {

    //根据skuId查询对应的所有图片
    @Select("select * from tb_sku_photo where sku_id = #{spuId}")
    @Results({
            @Result(property="id", column="id"),
            @Result(property="skuId", column="sku_id"),
            @Result(property="url", column="url")
    })
    List<SkuPhoto> findSkuPhotoBySkuId(@Param("spuId") Integer spuId);
}
