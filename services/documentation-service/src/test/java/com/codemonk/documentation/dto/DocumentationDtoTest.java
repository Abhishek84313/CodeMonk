package com.codemonk.documentation.dto;

import com.codemonk.documentation.domain.DocType;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class DocumentationDtoTest {

    @Test
    void shouldCreateCreateDocRequestSuccessfully() {
        CreateDocRequest request = new CreateDocRequest("repo-123", DocType.ARCHITECTURE, "Arch Doc", "Overview of microservices");

        assertEquals("repo-123", request.repositoryId());
        assertEquals(DocType.ARCHITECTURE, request.docType());
        assertEquals("Arch Doc", request.title());
        assertEquals("Overview of microservices", request.description());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenCreateDocRequestRepositoryIdIsNull() {
        assertThrows(NullPointerException.class, () -> new CreateDocRequest(null, DocType.API_OVERVIEW, "Title", "Desc"));
    }

    @Test
    void shouldCreateDocResponseSuccessfully() {
        Instant now = Instant.now();
        DocResponse response = new DocResponse("doc-1", "repo-123", DocType.COMPONENT_SUMMARY, "Summary", "# Markdown", "GENERATED", now);

        assertEquals("doc-1", response.id());
        assertEquals("repo-123", response.repositoryId());
        assertEquals(DocType.COMPONENT_SUMMARY, response.docType());
        assertEquals("Summary", response.title());
        assertEquals("# Markdown", response.markdownContent());
        assertEquals("GENERATED", response.status());
        assertEquals(now, response.createdAt());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenDocResponseIdIsNull() {
        assertThrows(NullPointerException.class, () -> new DocResponse(null, "repo-123", DocType.DEPENDENCY_GRAPH, "Title", "Content", "COMPLETED", Instant.now()));
    }
}
