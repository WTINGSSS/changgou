package com.czxy.mapper;

import com.czxy.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/30 9:19
 * @description
 */
@Mapper
public interface BrandMapper extends tk.mybatis.mapper.common.Mapper<Brand> {

    @Select("select b.* from tb_brand b,tb_category_brand cb where b.id=cb.brand_id AND cb.category_id=#{categoryId} ")
    @Results({
            @Result(property = "brandName",column = "brand_name")
    })
    List<Brand> findBlist(@Param("categoryId")Integer categoryId);
}
