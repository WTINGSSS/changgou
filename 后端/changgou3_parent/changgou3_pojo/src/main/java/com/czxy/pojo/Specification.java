package com.czxy.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "tb_specification")
public class Specification implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 规格名称
     */
    @Column(name = "spec_name")
    private String specName;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    private List<SpecificationOption> options = new ArrayList<>();
    private static final long serialVersionUID = 1L;
}