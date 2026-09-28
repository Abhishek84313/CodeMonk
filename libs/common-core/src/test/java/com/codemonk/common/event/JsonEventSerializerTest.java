package com.codemonk.common.event;

import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonEventSerializerTest {

    private JsonEventSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new JsonEventSerializer();
    }

    @Test
    void shouldReturnNullWhenDataIsNull() {
        assertNull(serializer.serialize("codemonk.topic", null));
    }

    @Test
    void shouldSerializeMapToJsonBytes() {
        Map<String, Object> payload = Map.of("key", "value", "count", 42);
        byte[] bytes = serializer.serialize("codemonk.topic", payload);

        assertNotNull(bytes);
        String json = new String(bytes, StandardCharsets.UTF_8);
        assertTrue(json.contains("\"key\":\"value\""));
        assertTrue(json.contains("\"count\":42"));
    }

    @Test
    void shouldSerializeEventEnvelopeToJsonBytes() {
        EventEnvelope<String> envelope = EventEnvelope.of(EventType.DOCUMENTATION_GENERATED, "doc-content");
        byte[] bytes = serializer.serialize("codemonk.topic", envelope);

        assertNotNull(bytes);
        String json = new String(bytes, StandardCharsets.UTF_8);
        assertTrue(json.contains("DOCUMENTATION_GENERATED"));
        assertTrue(json.contains("doc-content"));
    }
}
