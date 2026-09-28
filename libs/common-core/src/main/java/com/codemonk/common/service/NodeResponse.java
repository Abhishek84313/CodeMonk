package com.codemonk.common.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NodeResponse(String id, String label, String properties, List<String> tags) {
    public NodeResponse{
        id = (id == null || id.isBlank()) ? "" : id.trim();
        label = (label == null || label.isBlank()) ? "" : label.trim();
        properties = properties == null ? "" : properties;
        tags = tags == null ? List.of() : List.copyOf(tags);
    }
}
