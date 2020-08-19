package com.czxy.vo;

import com.czxy.pojo.Impression;
import com.czxy.pojo.SkuComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/17 10:43
 * @description
 */
@Data
public class CommentResult {

    private List<Impression> impressions;       //印象
    private Map<String,Object> ratio;           //好评度
    @JsonProperty("comment_count")
    private Integer comment_count;              //评论数
    private List<SkuComment> comments;          //评论
}
