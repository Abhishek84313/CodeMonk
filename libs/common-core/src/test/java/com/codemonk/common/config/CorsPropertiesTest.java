package com.codemonk.common.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.mock.env.MockEnvironment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CorsPropertiesTest {

    @Test
    void shouldBindCorsProperties() {
        MockEnvironment environment = new MockEnvironment()
                .withProperty("codemonk.cors.origins[0]", "https://example.com")
                .withProperty("codemonk.cors.methods[0]", "GET")
                .withProperty("codemonk.cors.methods[1]", "POST")
                .withProperty("codemonk.cors.headers[0]", "Authorization");

        CorsProperties properties = Binder.get(environment)
                .bind("codemonk.cors", Bindable.of(CorsProperties.class))
                .orElseThrow();

        assertEquals(List.of("https://example.com"), properties.getOrigins());
        assertEquals(List.of("GET", "POST"), properties.getMethods());
        assertEquals(List.of("Authorization"), properties.getHeaders());
    }
}
