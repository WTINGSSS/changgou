package com.czxy.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "tb_category")
public class Category implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "cat_name")
    @JsonProperty("cat_name")
    private String catName;

    @Column(name = "parent_id")
    @JsonProperty("parent_id")
    private Long parentId;

    @Column(name = "is_parent")
    @JsonProperty("is_parent")
    private Integer isParent;

    //此分类具有的所有子类
    private List<Category> children = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}