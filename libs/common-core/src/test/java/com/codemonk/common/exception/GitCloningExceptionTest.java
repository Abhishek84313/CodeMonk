package com.codemonk.common.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GitCloningExceptionTest {

    @Test
    void shouldExtendInternalServerException() {
        GitCloningException exception = new GitCloningException("Failed to clone repository");

        assertThat(exception).isInstanceOf(InternalServerException.class);
        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception).hasMessage("Failed to clone repository");
    }

    @Test
    void shouldPreserveCause() {
        RuntimeException cause = new RuntimeException("clone failed");
        GitCloningException exception = new GitCloningException("Failed to clone repository", cause);

        assertThat(exception).hasCause(cause);
    }
}
