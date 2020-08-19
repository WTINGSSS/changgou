package com.czxy.mapper;

import com.czxy.pojo.Specification;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/7 17:54
 * @description
 */
@org.apache.ibatis.annotations.Mapper
public interface SpecificationMapper extends Mapper<Specification> {

    /**
     * 查询指定分类的所有规格
     * @param categoryId
     * @return
     */
    @Select("select * from tb_specification where category_id = #{categoryId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "specName", column = "spec_name"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "options", column = "id", many = @Many(select = "com.czxy.mapper.SpecificationOptionMapper.findAll")),
    })
    public List<Specification> findAll(@Param("categoryId") Integer categoryId);
}
