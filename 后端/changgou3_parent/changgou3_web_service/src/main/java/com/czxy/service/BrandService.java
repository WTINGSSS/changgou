package com.czxy.service;

import com.czxy.pojo.Brand;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/30 9:25
 * @description
 */
public interface BrandService {
    List<Brand> findBlist(Integer categoryId);
}
