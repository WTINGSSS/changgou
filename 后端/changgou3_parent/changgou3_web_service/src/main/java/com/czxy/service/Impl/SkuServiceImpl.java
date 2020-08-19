package com.czxy.service.Impl;

import com.alibaba.fastjson.JSON;
import com.czxy.mapper.*;
import com.czxy.pojo.*;
import com.czxy.service.SkuService;
import com.czxy.vo.ESData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 庭前云落
 * @Date 2020/4/15 9:57
 * @description
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SkuCommentMapper skuCommentMapper;

    @Resource
    private SpuMapper spuMapper;
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SkuPhotoMapper skuPhotoMapper;

    @Resource
    private SpecificationMapper specificationMapper;

    /**
     * 查询所有sku,查询结果转换ESData
     */
    public List<ESData> findESData() {
        //查询所有详情sku
        List<Sku> skuList = skuMapper.findAllSkus();
        //集合存放处理后的ESData
        ArrayList<ESData> esDataList = new ArrayList<>();
        //遍历 将sku封装ESData
        for (Sku sku : skuList) {
            ESData esData = new ESData();
            //id
            esData.setId(sku.getId());
            //图片地址
            esData.setLogo(sku.getSpu().getLogo());
            //商品名称
            esData.setSkuName(sku.getSkuName());
            // all  “华为xx {"机身颜色":"白色","内存":"3GB","机身存储":"16GB"} 荣耀 ”
            esData.setAll(sku.getSkuName() + "" + sku.getSpecInfoIdTxt() + "" + sku.getSpu().getBrand().getBrandName());
            //on_sale_time
            esData.setOnSaleTime(sku.getSpu().getOnSaleTime());
            //brand_id
            esData.setBrandId(sku.getSpu().getBrandId());
            //cat_id
            esData.setCatId(sku.getSpu().getCat3Id());
            //JSON字符串 转换
            Map<String, Object> specs = JSON.parseObject(sku.getSpecInfoIdTxt(), Map.class);
            esData.setSpecs(specs);
            esData.setMidlogo(sku.getSpu().getLogo());
            esData.setPrice(sku.getPrice());
            esData.setSpuName(sku.getSpu().getSpuName());
            esData.setStock(sku.getStock());
            esData.setDescription(sku.getSpu().getDescription());
            esData.setPackages(sku.getSpu().getPackages());
            esData.setAftersale(sku.getSpu().getAftersale());
            //评论数
            Integer comment_count = skuCommentMapper.findNumBySpuId(sku.getSpu().getId());
            esData.setCommentCount(comment_count);

            //销售量
            esData.setSellerCount(10);

            esDataList.add(esData);
        }
        return esDataList;
    }

    /*
     * 查询详情
     */
    public OneSkuResult findSkuById(Integer skuid){
        OneSkuResult skuResult = new OneSkuResult();
        // 1 查找sku基本信息
        Sku sku = skuMapper.selectByPrimaryKey(skuid);
        // 2 根据sku查找spu信息
        Spu spu = spuMapper.findSpuById(sku.getSpuId());
        // 3 赋值
        // skuid;
        skuResult.setSkuid(sku.getId());
        // spuid;
        skuResult.setSpuid(sku.getSpuId());
        // 商品名称
        skuResult.setGoodsName(sku.getSkuName());
        // 价格
        skuResult.setPrice(sku.getPrice());
        // 上架时间
        skuResult.setOnSaleDate(spu.getOnSaleTime());
        // 评价数
        Integer comment_count = skuCommentMapper.findNumBySpuId(spu.getId());
        skuResult.setCommentCount(comment_count);
        // 评论级别
        skuResult.setCommentLevel(skuCommentMapper.findAvgStarBySkuId(sku.getId()));
        // 一级分类
        skuResult.setCat1Info(categoryMapper.selectByPrimaryKey(spu.getCat1Id()));
        // 二级分类
        skuResult.setCat2Info(categoryMapper.selectByPrimaryKey(spu.getCat2Id()));
        // 三级分类
        skuResult.setCat3Info(categoryMapper.selectByPrimaryKey(spu.getCat3Id()));
        // 第一张图片
        Map<String,String> logo = new HashMap();
        logo.put("smlogo",spu.getLogo());
        logo.put("biglogo",spu.getLogo());
        logo.put("xbiglogo",spu.getLogo());
        skuResult.setLogo(logo);
        // 通过skuId查询对应的所有的图片
        List<SkuPhoto> skuPhotoList = skuPhotoMapper.findSkuPhotoBySkuId(sku.getId());
        List<Map> photos = new ArrayList<>();
        for(SkuPhoto sp:skuPhotoList){
            Map<String,String> map = new HashMap();
            map.put("smimg",sp.getUrl());
            map.put("bigimg",sp.getUrl());
            map.put("xbigimg",sp.getUrl());
            photos.add(map);
        }
        skuResult.setPhotos(photos);

        // description;
        skuResult.setDescription(spu.getDescription());
        // aftersale;
        skuResult.setAftersale(spu.getAftersale());
        // stock;
        skuResult.setStock(sku.getStock());
        // List<SpecResult> spec_list; 根据分类查找规格和规格选项
        List<Specification> spec_list = specificationMapper.findAll(spu.getCat3Id());
        skuResult.setSpecList(spec_list);
        //id_list:'规格ID:选项ID|规格ID:选项ID|...',
        //id_txt:'规格名称:选项名称|规格名称:选项名称|...'
        // Map<String, String> spec_info;
        Map<String,String> spec_info = new HashMap<>();
        spec_info.put("id_list",sku.getSpecInfoIdList());
        spec_info.put("id_txt",sku.getSpecInfoIdTxt());
        skuResult.setSpecInfo(spec_info);
        // List<Map<String, String>> sku_list;
        List<Sku> skuBySpuIdList = skuMapper.findSkuBySpuId(spu.getId());
        List<Map<String, String>> sku_list = new ArrayList<>();
        for(Sku s:skuBySpuIdList){
            Map<String,String> map = new HashMap<>();
            map.put("skuid",s.getId().toString());
            map.put("id_list",s.getSpecInfoIdList());
            sku_list.add(map);
        }
        skuResult.setSkuList(sku_list);
        // 返回结果
        return skuResult;
    }

    /**
     * 更新商品数量
     */
    public void  updateSkuNum(Integer skuId,Integer count){
        Sku sku = skuMapper.selectByPrimaryKey(skuId);
        sku.setStock(sku.getStock()-count);
        skuMapper.updateByPrimaryKey(sku);
    }
}
