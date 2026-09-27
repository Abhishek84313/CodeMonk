package com.codemonk.common.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationProfilesTest {

    @Test
    void shouldDefineExpectedProfileNames() {
        assertEquals("local", ApplicationProfiles.PROFILE_LOCAL);
        assertEquals("dev", ApplicationProfiles.PROFILE_DEV);
        assertEquals("test", ApplicationProfiles.PROFILE_TEST);
        assertEquals("prod", ApplicationProfiles.PROFILE_PROD);
    }
}
