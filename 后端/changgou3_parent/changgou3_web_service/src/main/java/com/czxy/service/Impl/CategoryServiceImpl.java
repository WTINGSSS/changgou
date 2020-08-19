package com.czxy.service.Impl;

import com.czxy.mapper.CategoryMapper;
import com.czxy.pojo.Category;
import com.czxy.service.CategoryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/3/29 23:22
 * @description
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> findClist() {
        //查询 -- 按 parentId 升序
        Example example = new Example(Category.class);
        example.setOrderByClause("parent_id asc");
        List<Category> temp = categoryMapper.selectByExample(example);

        //存放所有的一级分类
        List<Category> list = new ArrayList<>();
        //存放每个分类(将来为父分类)
        Map<Long, Category> map = new HashMap<>();
        for (Category category : temp) {
            //过滤所有以及 parent==0
            if (category.getParentId() == 0) {
                list.add(category);
            }
            //向 map存一份
            map.put(category.getId(), category);

            //获得当前父分类
            Category parentCategory = map.get(category.getParentId());
            if(parentCategory!=null){
                parentCategory.getChildren().add(category);
            }
        }
        return list;
    }
}
