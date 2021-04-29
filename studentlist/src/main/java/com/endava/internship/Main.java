package com.endava.internship;


import java.time.LocalDate;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
        Student std1 = new Student("a", LocalDate.now(), "asd");
        Student std2 = new Student("b", LocalDate.now(), "asd");
        Student std3 = new Student("c", LocalDate.now(), "asd");

        ArrayList<Student> list = new ArrayList<>();
        list.add(std1);
        list.add(std2);
        list.add(std3);

        ListIterator<Student> iterator = list.listIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println();

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }
}
