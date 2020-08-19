package com.czxy.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_specification_option")
public class SpecificationOption implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 规格ID
     */
    @Column(name = "spec_id")
    private Integer specId;

    /**
     * 选项名称
     */
    @Column(name = "option_name")
    private String optionName;

    private static final long serialVersionUID = 1L;
}