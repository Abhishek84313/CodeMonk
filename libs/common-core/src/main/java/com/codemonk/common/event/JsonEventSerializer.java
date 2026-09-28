package com.codemonk.common.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Jackson-backed Kafka Serializer for serializing Java objects into JSON byte arrays.
 */
public class JsonEventSerializer implements Serializer<Object> {

    private final ObjectMapper objectMapper;

    public JsonEventSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public JsonEventSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper != null ? objectMapper : new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No custom configuration required
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize event payload for topic [" + topic + "]", e);
        }
    }

    @Override
    public void close() {
        // Resource cleanup if necessary
    }
}
