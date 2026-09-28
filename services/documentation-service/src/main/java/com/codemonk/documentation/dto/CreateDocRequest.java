package com.codemonk.documentation.dto;

import com.codemonk.documentation.domain.DocType;

import java.util.Objects;

/**
 * Data Transfer Object for creating documentation requests.
 */
public record CreateDocRequest(
        String repositoryId,
        DocType docType,
        String title,
        String description
) {
    public CreateDocRequest {
        Objects.requireNonNull(repositoryId, "repositoryId must not be null");
        Objects.requireNonNull(docType, "docType must not be null");
    }
}
