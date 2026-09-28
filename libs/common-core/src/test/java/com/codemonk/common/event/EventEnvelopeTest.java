package com.codemonk.common.event;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EventEnvelopeTest {

    @Test
    void shouldCreateEventEnvelopeWithGeneratedDefaults() {
        EventEnvelope<String> envelope = EventEnvelope.of(EventType.REPOSITORY_INGESTED, "payload-data");

        assertNotNull(envelope.eventId());
        assertNotNull(envelope.timestamp());
        assertNotNull(envelope.correlationId());
        assertEquals(EventType.REPOSITORY_INGESTED, envelope.eventType());
        assertEquals("payload-data", envelope.payload());
    }

    @Test
    void shouldCreateEventEnvelopeWithExplicitCorrelationId() {
        String customCorrelationId = "corr-12345";
        EventEnvelope<String> envelope = EventEnvelope.of(EventType.CODE_PARSED, customCorrelationId, "ast-data");

        assertEquals(customCorrelationId, envelope.correlationId());
        assertEquals(EventType.CODE_PARSED, envelope.eventType());
        assertEquals("ast-data", envelope.payload());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenEventTypeIsNull() {
        assertThrows(NullPointerException.class, () -> new EventEnvelope<>("id-1", null, Instant.now(), "corr-1", "payload"));
    }

    @Test
    void shouldThrowNullPointerExceptionWhenPayloadIsNull() {
        assertThrows(NullPointerException.class, () -> EventEnvelope.of(EventType.DOCUMENTATION_GENERATED, null));
    }
}
