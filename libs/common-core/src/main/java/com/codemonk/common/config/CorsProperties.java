package com.codemonk.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * CORS configuration properties for CodeMonk services.
 */
@Configuration
@ConfigurationProperties(prefix = "codemonk.cors")
public class CorsProperties {

    private List<String> origins = new ArrayList<>();
    private List<String> methods = new ArrayList<>();
    private List<String> headers = new ArrayList<>();

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
