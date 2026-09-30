package com.codemonk.common.cache;

import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * Builds deterministic cache keys for repository lookups.
 *
 * <p>Repository identity is made up of its owner and repository name. Both are
 * normalized so equivalent lookup inputs resolve to the same Redis key.
 */
@Component
public class RepositoryCacheKeyGenerator {

    /** Namespace used by the overloads that do not take one. */
    public static final String DEFAULT_NAMESPACE = "repository";

    private static final String SEGMENT_SEPARATOR = ":";

    private static final String OWNER_REPOSITORY_SEPARATOR = "/";

    private static final String RESERVED_REPLACEMENT = "_";

    private static final Pattern WHITESPACE = Pattern.compile("\\s+");

    private static final Pattern RESERVED = Pattern.compile("[:|=]");

    /**
     * Builds a key for a repository lookup under the default namespace.
     *
     * @param owner the repository owner, neither {@code null} nor blank
     * @param repositoryName the repository name, neither {@code null} nor blank
     * @return the cache key, for example {@code repository:codemonk/CodeMonk}
     */
    public String generate(String owner, String repositoryName) {
        return generate(DEFAULT_NAMESPACE, owner, repositoryName);
    }

    /**
     * Builds a key for a repository lookup under a caller-chosen namespace.
     *
     * @param namespace the cache namespace, neither {@code null} nor blank
     * @param owner the repository owner, neither {@code null} nor blank
     * @param repositoryName the repository name, neither {@code null} nor blank
     * @return the deterministic cache key
     */
    public String generate(String namespace, String owner, String repositoryName) {
        requireText(namespace, "namespace");
        requireText(owner, "owner");
        requireText(repositoryName, "repository name");

        return normalize(namespace)
                + SEGMENT_SEPARATOR
                + normalize(owner)
                + OWNER_REPOSITORY_SEPARATOR
                + normalize(repositoryName);
    }

    private String normalize(String value) {
        String collapsed = WHITESPACE.matcher(value.trim()).replaceAll(" ");
        return RESERVED.matcher(collapsed.toLowerCase(Locale.ROOT)).replaceAll(RESERVED_REPLACEMENT);
    }

    private void requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or blank");
        }
    }
}
