package com.czxy.service.Impl;

import com.czxy.mapper.ImpressionMapper;
import com.czxy.mapper.SkuCommentMapper;
import com.czxy.pojo.Impression;
import com.czxy.pojo.SkuComment;
import com.czxy.service.SkuCommentService;
import com.czxy.vo.CommentResult;
import com.czxy.vo.PageRequest;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/17 10:50
 * @description
 */
@Service
public class SkuCommentServiceImpl implements SkuCommentService {

    @Resource
    private SkuCommentMapper skuCommentMapper;
    @Resource
    private ImpressionMapper impressionMapper;

    public CommentResult findComments(Integer spuid, PageRequest pageRequest){
        //封装数据的对象
        CommentResult commentResult = new CommentResult();

        //查询所有印象
        List<Impression> impressionList = impressionMapper.findImpressionsBySpuid(spuid);
        commentResult.setImpressions(impressionList);
        System.out.println("所有印象"+impressionList);
        //好评度
        Integer goodCount = skuCommentMapper.findCommentCountByRadio(spuid,0);// 好评
        Integer commonCount = skuCommentMapper.findCommentCountByRadio(spuid,1);// 中评
        Integer badCount = skuCommentMapper.findCommentCountByRadio(spuid,2);// 差评
        Integer totalCount = skuCommentMapper.findTotalCommentBySpuid(spuid);//

        Map<String,Object> map = new HashMap<>();
        if(totalCount!=0){
            map.put("goods", String.format("%.2f" , goodCount * 100.0 / totalCount ));
            map.put("common",String.format("%.2f" , commonCount * 100.0 / totalCount ));
            map.put("bad",String.format("%.2f" , badCount * 100.0 / totalCount ));
        }else{
            map.put("goods","0");
            map.put("common","0");
            map.put("bad","0");
        }
        commentResult.setRatio( map );

        //总评论数
        Integer comment_count = skuCommentMapper.findNumBySpuId(spuid);
        commentResult.setComment_count(comment_count);

        //分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());

        //查询所有
        List<SkuComment> comments = skuCommentMapper.findCommentsBySpuid(spuid);
        commentResult.setComments(comments);


        return commentResult;
    }
}
