package com.czxy.service;

import com.czxy.pojo.Specification;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/7 17:56
 * @description
 */
public interface SpecificationService {

    List<Specification> findAll(Integer categoryId);
}
