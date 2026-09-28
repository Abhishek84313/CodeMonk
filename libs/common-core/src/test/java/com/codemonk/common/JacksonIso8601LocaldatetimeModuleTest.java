package com.codemonk.common;

import com.codemonk.common.constant.JsonConstants;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JacksonIso8601LocaldatetimeModuleTest {

    @Test
    void shouldSerializeLocalDateTimeAsIso8601() throws Exception {
        LocalDateTime value = LocalDateTime.of(2026, 9, 27, 12, 34, 56);

        String json = JsonConstants.OBJECT_MAPPER.writeValueAsString(value);

        assertFalse(JsonConstants.OBJECT_MAPPER.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
        assertEquals("\"2026-09-27T12:34:56\"", json);
    }
}
