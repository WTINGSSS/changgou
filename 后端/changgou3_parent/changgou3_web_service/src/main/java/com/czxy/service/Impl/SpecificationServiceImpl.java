package com.czxy.service.Impl;

import com.czxy.mapper.SpecificationMapper;
import com.czxy.pojo.Specification;
import com.czxy.service.SpecificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/7 17:56
 * @description
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Resource
    private SpecificationMapper specificationMapper;

    public List<Specification> findAll(Integer categoryId){
        //查询
        List<Specification> list = specificationMapper.findAll(categoryId);
        return list;
    }
}
