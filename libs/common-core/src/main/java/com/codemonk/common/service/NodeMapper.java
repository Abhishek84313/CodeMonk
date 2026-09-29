package com.codemonk.common.service;

import org.springframework.stereotype.Component;
import java.util.Objects;

/**
 * Maps between {@link CodeNode} domain objects and {@link NodeResponse} DTOs.
 */

@Component
public class NodeMapper {


    /**
     * Converts a {@link CodeNode} domain object to a {@link NodeResponse} DTO.
     *
     * @param node the domain node; must not be null
     * @return corresponding DTO
     */
    public NodeResponse toResponse(CodeNode node){
        Objects.requireNonNull(node, "node must not be null");
        return new NodeResponse(node.id(), node.label(), node.properties(),node.tags());
    }

    /**
     * Converts a {@link NodeResponse} DTO back to a {@link CodeNode} domain object.
     *
     * @param response the DTO; must not be null
     * @return corresponding domain object
     */
    public CodeNode toDomain(NodeResponse response){
        Objects.requireNonNull(response, "response must not be null");
        return new CodeNode(response.id(), response.label(), response.properties(), response.tags());
    }
}
