package com.czxy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 庭前云落
 * @Date 2020/4/13 20:40
 * @description
 */
@Document(indexName = "czxy56",type = "book",shards = 4,replicas = 2)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ESBook {
    @Id
    private Long id;

    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type=FieldType.Keyword , index = true)
    private String images;

    @Field(type=FieldType.Float)
    private Float price;
}
