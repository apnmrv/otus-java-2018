package ru.otus.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello! I'm main of App!" );

        Printer.printObjectSize(new Object());
        Printer.printObjectSize(new A());
        Printer.printObjectSize(1);
        Printer.printObjectSize("string");
        Printer.printObjectSize(Calendar.getInstance());
        Printer.printObjectSize(new BigDecimal("999999999999999.999"));

        ArrayList<String> arrayList = new ArrayList<>();
        Printer.printObjectSize(arrayList);
        arrayList.add("One");
        Printer.printObjectSize(arrayList);
        arrayList.add("Two");
        Printer.printObjectSize(arrayList);
        arrayList.add("Three");
        Printer.printObjectSize(arrayList);

        Integer[] arr = new Integer[100];
        for (int i = 0; i < arr.length; i++){
            arr[i] = i*10;
            Printer.printObjectSize(arr);
        }
    }
}

class A {
    Integer id;
    String name;
};
class B {};
class C {};
