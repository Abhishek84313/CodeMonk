package com.codemonk.common.event;

/**
 * Domain event identifiers used across CodeMonk microservices and Kafka event topics.
 */
public enum EventType {
    REPOSITORY_INGESTED,
    CODE_PARSED,
    INDEXING_REQUESTED,
    KNOWLEDGE_GRAPH_UPDATED,
    DOCUMENTATION_GENERATED,
    SEARCH_INDEX_UPDATED
}
