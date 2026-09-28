package com.codemonk.documentation.dto;

import com.codemonk.documentation.domain.DocType;

import java.time.Instant;
import java.util.Objects;

/**
 * Data Transfer Object returning documentation response details.
 */
public record DocResponse(
        String id,
        String repositoryId,
        DocType docType,
        String title,
        String markdownContent,
        String status,
        Instant createdAt
) {
    public DocResponse {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(repositoryId, "repositoryId must not be null");
        Objects.requireNonNull(docType, "docType must not be null");
    }
}
