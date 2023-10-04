package com.ubeydekara.elasticsearch.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    List<String> fields;
    List<String> values;
}
