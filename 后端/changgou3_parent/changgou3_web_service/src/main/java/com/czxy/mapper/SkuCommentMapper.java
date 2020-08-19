package com.czxy.mapper;

import com.czxy.pojo.SkuComment;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/15 9:46
 * @description
 */
@org.apache.ibatis.annotations.Mapper
public interface SkuCommentMapper extends Mapper<SkuComment> {


    @Select("SELECT COUNT(*) FROM tb_sku_comment WHERE spu_id=#{spuId}")
    Integer findNumBySpuId(@Param("spuId") Integer spuId);

    @Select("select avg(star) from tb_sku_comment where sku_id = #{skuId}")
    Integer findAvgStarBySkuId(@Param("skuId") Integer skuId);

    //查询指定好评度的评论数
    @Select("select count(*) from tb_sku_comment where spu_id = #{spuid} and ratio = #{radio}")
    Integer findCommentCountByRadio(@Param("spuid") Integer spuid, @Param("radio") Integer radio);

    //查询spuId的评论数
    @Select("select count(*) from tb_sku_comment where spu_id = #{spuid}")
    public Integer findTotalCommentBySpuid(@Param("spuid") Integer spuid);

    //查询指定spu的所有评论
    @Select("select * from tb_sku_comment where spu_id = #{spuid}")
    @Results({
            @Result(property = "createdAt" , column = "created_at"),
            @Result(property = "updatedAt" , column = "updated_at"),
            @Result(property = "userId" , column = "user_id"),
            @Result(property = "skuId" , column = "sku_id"),
            @Result(property = "specList" , column = "spec_list"),
            @Result(property = "user" , one = @One(select = "com.czxy.mapper.UserMapper.selectByPrimaryKey"), column = "user_id"),
    })
    public List<SkuComment> findCommentsBySpuid(@Param("spuid") Integer spuid);
}
