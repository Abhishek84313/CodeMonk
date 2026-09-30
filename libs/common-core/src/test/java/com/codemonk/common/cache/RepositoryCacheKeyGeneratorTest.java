package com.codemonk.common.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RepositoryCacheKeyGeneratorTest {

    private RepositoryCacheKeyGenerator keyGenerator;

    @BeforeEach
    void setUp() {
        keyGenerator = new RepositoryCacheKeyGenerator();
    }

    @Test
    void shouldBuildARepositoryKeyUnderTheDefaultNamespace() {
        assertEquals(
                "repository:codemonk/codemonk",
                keyGenerator.generate("codemonk", "CodeMonk"));
    }

    @Test
    void shouldNormalizeCaseAndSurroundingWhitespace() {
        assertEquals(
                keyGenerator.generate("codemonk", "CodeMonk"),
                keyGenerator.generate("  CODEMONK  ", "  codemonk  "));
    }

    @Test
    void shouldKeepDifferentRepositoriesOnDifferentKeys() {
        assertNotEquals(
                keyGenerator.generate("codemonk", "CodeMonk"),
                keyGenerator.generate("codemonk", "OtherRepo"));
        assertNotEquals(
                keyGenerator.generate("codemonk", "CodeMonk"),
                keyGenerator.generate("other", "CodeMonk"));
    }

    @Test
    void shouldHonourACallerChosenNamespace() {
        assertEquals(
                "repositories:codemonk/codemonk",
                keyGenerator.generate("repositories", "codemonk", "CodeMonk"));
    }

    @Test
    void shouldReplaceCharactersThatCouldBreakKeySegments() {
        assertEquals(
                "repository:code_monk/code_monk",
                keyGenerator.generate("code:monk", "code|monk"));
    }

    @Test
    void shouldRejectMissingRepositoryIdentity() {
        assertThrows(IllegalArgumentException.class, () -> keyGenerator.generate(null, "CodeMonk"));
        assertThrows(IllegalArgumentException.class, () -> keyGenerator.generate("   ", "CodeMonk"));
        assertThrows(IllegalArgumentException.class, () -> keyGenerator.generate("codemonk", null));
        assertThrows(IllegalArgumentException.class, () -> keyGenerator.generate("codemonk", "   "));
    }

    @Test
    void shouldRejectMissingNamespace() {
        assertThrows(
                IllegalArgumentException.class,
                () -> keyGenerator.generate(null, "codemonk", "CodeMonk"));
        assertThrows(
                IllegalArgumentException.class,
                () -> keyGenerator.generate("   ", "codemonk", "CodeMonk"));
    }

    @Test
    void shouldReturnTheSameKeyForRepeatedCalls() {
        assertEquals(
                keyGenerator.generate("codemonk", "CodeMonk"),
                keyGenerator.generate("codemonk", "CodeMonk"));
    }
}
