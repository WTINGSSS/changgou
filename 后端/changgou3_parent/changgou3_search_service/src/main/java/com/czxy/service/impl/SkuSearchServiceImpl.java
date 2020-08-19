package com.czxy.service.impl;

import com.czxy.repository.SkuRepository;
import com.czxy.service.SkuSearchService;
import com.czxy.vo.ReturnSku;
import com.czxy.vo.SearchSku;
import com.czxy.vo.SearchVo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/15 21:40
 * @description
 */
@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Resource
    private SkuRepository skuRepository;

    public Map search(SearchVo searchVo){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //分类查询
        boolQueryBuilder.must(QueryBuilders.termQuery("catId",searchVo.getCatId()));
        //关键字查询
        if(StringUtils.isNotBlank(searchVo.getKeyword())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuName",searchVo.getKeyword()));
        }
        //品牌查询
        if(searchVo.getBrandId()!=null){
           boolQueryBuilder.must(QueryBuilders.termQuery("brandId",searchVo.getBrandId()));
        }
        //规格查询
        Map<String, String> specList = searchVo.getSpecList();
        if(specList!=null){
            for (Map.Entry<String, String> entry : specList.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                boolQueryBuilder.must(QueryBuilders.termQuery("spces."+key+".keyword",value));
            }
        }
        if(searchVo.getMaxPrice()!=null&&searchVo.getMaxPrice()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(searchVo.getMinPrice()).lt(searchVo.getMaxPrice()));
        }
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(boolQueryBuilder);


        if (searchVo.getSortBy()!=null){
            if(searchVo.getSortBy().equals("xl")&&searchVo.getSortWay().equals("asc")){
                //销量升序
                queryBuilder.withSort(SortBuilders.fieldSort("sellerCount").order(SortOrder.ASC));
            }else  if(searchVo.getSortBy().equals("xl")&&searchVo.getSortWay().equals("desc")) {
                // 销量降序
                queryBuilder.withSort(SortBuilders.fieldSort("sellerCount").order(SortOrder.DESC));
            }else if(searchVo.getSortBy().equals("jg")&&searchVo.getSortWay().equals("asc")){
                // 价格升序
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
            }else  if(searchVo.getSortBy().equals("jg")&&searchVo.getSortWay().equals("desc")) {
                // 价格降序
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
            }else if(searchVo.getSortBy().equals("pl")&&searchVo.getSortWay().equals("asc")){
                // 评论升序
                queryBuilder.withSort(SortBuilders.fieldSort("commentCount").order(SortOrder.ASC));
            }else  if(searchVo.getSortBy().equals("pl")&&searchVo.getSortWay().equals("desc")) {
                // 评论降序
                queryBuilder.withSort(SortBuilders.fieldSort("commentCount").order(SortOrder.DESC));
            }else if(searchVo.getSortBy().equals("sj")&&searchVo.getSortWay().equals("asc")){
                // 上架时间
                queryBuilder.withSort(SortBuilders.fieldSort("onSaleTime").order(SortOrder.ASC));
            }else  if(searchVo.getSortBy().equals("sj")&&searchVo.getSortWay().equals("desc")) {
                // 上架时间
                queryBuilder.withSort(SortBuilders.fieldSort("onSaleTime").order(SortOrder.DESC));
            }
        }

        // 2.2 分页
        queryBuilder.withPageable(PageRequest.of(searchVo.getPageNum() - 1 ,searchVo.getPageSize()));


        //3 查询,获取结果
        // 3.1 查询
        Page<SearchSku> pageInfo = this.skuRepository.search(queryBuilder.build());

        // 2.2 总条数
        long total = pageInfo.getTotalElements();

        // 3.3 获取返回结果 ，组装返回数据(SearchSku-->Return)
        List<SearchSku> list = pageInfo.getContent();

        List<ReturnSku> returnList = new ArrayList<>();

        for(SearchSku sku:list){
            //创建 ReturnSku对象
            ReturnSku rs = new ReturnSku();
            //依次填充数据
            rs.setId(sku.getId().intValue());
            rs.setGoodsName(sku.getSkuName());
            rs.setMidlogo(sku.getLogo());
            rs.setCommentCount(sku.getCommentCount());
            rs.setPrice(sku.getPrice());

            returnList.add(rs);
        }

        // 3.4 封装
        Map result = new HashMap();
        result.put("count" , total);
        result.put("list" ,returnList);

        return result;
    }
}
