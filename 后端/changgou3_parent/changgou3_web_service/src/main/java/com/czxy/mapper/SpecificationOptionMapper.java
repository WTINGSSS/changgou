package com.czxy.mapper;

import com.czxy.pojo.SpecificationOption;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/7 17:12
 * @description
 */
@org.apache.ibatis.annotations.Mapper
public interface SpecificationOptionMapper extends Mapper<SpecificationOption> {
    /**
     * 查询指定规格的所有规格选项
     * @param specId
     * @return
     */
    @Select("select * from tb_specification_option where spec_id = #{specId} ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "optionName",column = "option_name"),
            @Result(property = "specId",column = "spec_id"),
    })
    public List<SpecificationOption> findAll(@Param("specId") String specId);
}
