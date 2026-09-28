package com.codemonk.common.event;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Generic container record for event envelopes streamed over Apache Kafka topics.
 *
 * @param <T> Payload type contained within the event envelope
 */
public record EventEnvelope<T>(
        String eventId,
        EventType eventType,
        Instant timestamp,
        String correlationId,
        T payload
) {
    public EventEnvelope {
        Objects.requireNonNull(eventType, "eventType must not be null");
        Objects.requireNonNull(payload, "payload must not be null");
        if (eventId == null || eventId.isBlank()) {
            eventId = UUID.randomUUID().toString();
        }
        if (timestamp == null) {
            timestamp = Instant.now();
        }
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }
    }

    public static <T> EventEnvelope<T> of(EventType eventType, T payload) {
        return new EventEnvelope<>(UUID.randomUUID().toString(), eventType, Instant.now(), UUID.randomUUID().toString(), payload);
    }

    public static <T> EventEnvelope<T> of(EventType eventType, String correlationId, T payload) {
        return new EventEnvelope<>(UUID.randomUUID().toString(), eventType, Instant.now(), correlationId, payload);
    }
}
