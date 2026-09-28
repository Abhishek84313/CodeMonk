package com.codemonk.common.arch;

import com.codemonk.common.exception.DomainException;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * ArchUnit rules verifying exception hierarchies, domain decoupling,
 * and package boundaries in common-core.
 */
class ArchitectureQualityTest_5 {

    private static final String BASE_PACKAGE = "com.codemonk.common";
    private static final String EXCEPTION_PACKAGE = BASE_PACKAGE + ".exception..";
    private static final String DTO_PACKAGE = BASE_PACKAGE + ".dto..";
    private static final String EVENT_PACKAGE = BASE_PACKAGE + ".event..";

    private static JavaClasses classes;

    @BeforeAll
    static void importClasses() {
        classes = ArchTestImports.importMainClasses(BASE_PACKAGE);
    }

    /**
     * Subclasses of DomainException must reside in the exception package.
     */
    @Test
    void domainExceptionsShouldResideInExceptionPackage() {
        ArchRule rule = classes()
                .that()
                .areAssignableTo(DomainException.class)
                .should()
                .resideInAPackage(EXCEPTION_PACKAGE);

        rule.check(classes);
    }

    /**
     * Exception classes must have simple names ending with Exception.
     */
    @Test
    void exceptionsShouldFollowNamingConvention() {
        ArchRule rule = classes()
                .that()
                .areAssignableTo(Exception.class)
                .should()
                .haveSimpleNameEndingWith("Exception");

        rule.check(classes);
    }

    /**
     * Event classes must not depend on DTO classes.
     */
    @Test
    void eventsShouldNotDependOnDtos() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage(EVENT_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(DTO_PACKAGE);

        rule.check(classes);
    }
}
