package com.czxy.service;

import com.czxy.vo.CommentResult;
import com.czxy.vo.PageRequest;

/**
 * @author 庭前云落
 * @Date 2020/4/17 10:53
 * @description
 */
public interface SkuCommentService {

    CommentResult findComments(Integer spuid, PageRequest pageRequest);
}
