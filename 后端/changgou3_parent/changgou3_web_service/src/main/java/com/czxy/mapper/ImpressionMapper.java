package com.czxy.mapper;

import com.czxy.pojo.Impression;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/17 10:44
 * @description
 */
@Mapper
public interface ImpressionMapper extends tk.mybatis.mapper.common.Mapper<Impression> {

    @Select("select * from tb_impression where spu_id = #{spuid}")
     List<Impression> findImpressionsBySpuid(@Param("spuid") Integer spuid);
}
