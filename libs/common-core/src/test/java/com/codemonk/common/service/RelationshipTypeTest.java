package com.codemonk.common.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RelationshipTypeTest {

    @Test
    void shouldContainAllSupportedRelationshipTypes() {
        assertArrayEquals(
                new RelationshipType[]{
                        RelationshipType.CALLS,
                        RelationshipType.INHERITS,
                        RelationshipType.IMPLEMENTS,
                        RelationshipType.IMPORTS,
                        RelationshipType.CONTAINS,
                        RelationshipType.DEPENDS_ON,
                        RelationshipType.ANNOTATED_WITH
                },
                RelationshipType.values()
        );
    }
}
