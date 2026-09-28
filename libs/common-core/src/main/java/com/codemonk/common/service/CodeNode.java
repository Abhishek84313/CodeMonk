package com.codemonk.common.service;

import java.util.List;

/**
 * Domain model representing a node in the code knowledge graph.
 */

public record CodeNode(String id, String label, String properties, List<String> tags) {

    public CodeNode{
        id = (id == null || id.isBlank()) ? "" : id.trim();
        label = (label == null || label.isBlank()) ? "" : label.trim();
        properties = (properties == null) ? "" : properties;
        tags = tags == null ? List.of() : List.copyOf(tags);
    }


}
