package com.codemonk.common.constant;

import com.codemonk.common.JacksonIso8601LocaldatetimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public final class JsonConstants {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JacksonIso8601LocaldatetimeModule())
            .disable(WRITE_DATES_AS_TIMESTAMPS);

    private JsonConstants() {
    }
}
