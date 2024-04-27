package com.epam.annotationTask;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface DemoAnnotation {
    String name() default "yashu";
    String[] funtionality() default {"fuctionality1"};
}
