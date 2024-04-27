package com.epam.annotationTask;

import org.junit.Test;

public class AnnotationApplication {

    @DemoAnnotation
    private String name;
    private String address;

    @Test
    @DemoAnnotation(name="Yashaswini", funtionality = "Encryption")
    public void Test1(){
        System.out.println("-----------11----------");
    }

    @Test
    @DemoAnnotation(name="Yashu", funtionality = "Decryption")
    public void Test2(){
        System.out.println("-----------22----------");
    }

}
