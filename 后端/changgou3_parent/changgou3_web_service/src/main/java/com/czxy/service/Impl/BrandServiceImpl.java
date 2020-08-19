package com.czxy.service.Impl;

import com.czxy.mapper.BrandMapper;
import com.czxy.pojo.Brand;
import com.czxy.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/30 9:24
 * @description
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    public List<Brand> findBlist(Integer categoryId){
        List<Brand> blist = brandMapper.findBlist(categoryId);
        return brandMapper.findBlist(categoryId);
    }
}
