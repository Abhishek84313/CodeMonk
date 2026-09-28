package com.codemonk.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EnvironmentValidatorTest {

    @Test
    void shouldBeSpringComponent() {
        assertTrue(EnvironmentValidator.class.isAnnotationPresent(Component.class));
    }

    @Test
    void shouldReadActiveProfilesWhenApplicationIsReady() {
        Environment environment = mock(Environment.class);
        when(environment.getActiveProfiles()).thenReturn(new String[]{"dev", "local"});

        EnvironmentValidator validator = new EnvironmentValidator(environment);
        validator.onApplicationEvent(mock(ApplicationReadyEvent.class));

        verify(environment).getActiveProfiles();
    }
}
