package com.codemonk.common.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.MappedSuperclass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * ArchUnit rules verifying entity abstractions, service boundaries,
 * and component stereotypes for architecture quality test 14.
 */
class ArchitectureQualityTest_14 {

    private static final String BASE_PACKAGE = "com.codemonk.common";
    private static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service..";
    private static final String CACHE_PACKAGE = BASE_PACKAGE + ".cache..";

    private static JavaClasses classes;

    @BeforeAll
    static void importClasses() {
        classes = ArchTestImports.importMainClasses(BASE_PACKAGE);
    }

    /**
     * Base entity classes must be annotated with @MappedSuperclass.
     */
    @Test
    void baseEntityShouldBeAnnotatedWithMappedSuperclass() {
        ArchRule rule = classes()
                .that()
                .haveSimpleName("BaseEntity")
                .should()
                .beAnnotatedWith(MappedSuperclass.class);

        rule.check(classes);
    }

    /**
     * Classes ending with Service in cache package must be annotated with @Service or @Component.
     */
    @Test
    void cacheServicesShouldBeSpringBeans() {
        ArchRule rule = classes()
                .that()
                .resideInAPackage(CACHE_PACKAGE)
                .and()
                .haveSimpleNameEndingWith("Service")
                .should()
                .beAnnotatedWith(Service.class)
                .orShould()
                .beAnnotatedWith(Component.class);

        rule.check(classes);
    }

    /**
     * Classes in service package must not depend on raw test packages.
     */
    @Test
    void servicesShouldNotDependOnTestPackages() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage(SERVICE_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideInAPackage("org.junit..");

        rule.check(classes);
    }
}
