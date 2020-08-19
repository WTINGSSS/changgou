package com.czxy.controller;

import com.czxy.service.SkuCommentService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.CommentResult;
import com.czxy.vo.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/4/17 10:52
 * @description
 */
@RestController
@RequestMapping("/comments")
public class SkuCommentController {

    @Resource
    private SkuCommentService skuCommentService;

    @GetMapping("/spu/{spuid}")
    public BaseResult findCommentsByPage(@PathVariable("spuid") Integer spuid, PageRequest pageRequest){
        CommentResult comments = skuCommentService.findComments(spuid, pageRequest);
        return BaseResult.ok("查询成功", comments);
    }
}
