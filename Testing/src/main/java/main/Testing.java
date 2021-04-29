package main;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Testing {

    public static void main(String[] args) {
        Stream.of("a", "b", "c").peek(System.out::println).collect(Collectors.toSet());
    }
}