package com.javaPractice;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceTypes {
    public static void main(String[] args){

        Predicate<Integer> pred = i-> (i>34);
        System.out.println(pred.test(54));

        Consumer<String> str = i-> System.out.println(i);
        str.accept("Java");

        Supplier<String> str2 = () -> "Java script";
        System.out.println(str2.get());

        Supplier<Double> var = () -> Math.random();
        System.out.println(var.get());

        Function<Integer,String> func = i -> i*10 +" data multiplied by 10";
        System.out.println(func.apply(4));
    }
}
