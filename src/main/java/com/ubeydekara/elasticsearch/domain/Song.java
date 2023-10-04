package com.ubeydekara.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.UUID;

@Document(indexName = "songs")
@Setting(settingPath = "/esconfig.json")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    private String songId;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "author", type = FieldType.Keyword)
    private String author;
}
