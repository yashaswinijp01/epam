package com.epam.annotationTask;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationExample {
    public static void main(String[] args) {

        try {
            Class myclass = Class.forName(AnnotationApplication.class.getCanonicalName());

            Method methodArray[] = myclass.getMethods();
            for (Method method: methodArray
                 ) {
                DemoAnnotation demoAnnotation =method.getDeclaredAnnotation(DemoAnnotation.class);
                System.out.println("methodname="+method.getName());
                System.out.println("DemoAnnotation name="+ demoAnnotation.name());
                System.out.println("Functionality="+ Arrays.toString(demoAnnotation.funtionality()) );
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e); //print exception object
        }
    }
}
