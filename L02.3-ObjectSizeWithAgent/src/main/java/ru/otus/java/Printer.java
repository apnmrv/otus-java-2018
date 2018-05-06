package ru.otus.java;

public class Printer {
    public static void printObjectSize(Object obj) {
        System.out.println(String.format("%s, size=%s", obj.getClass()
                .getSimpleName(), MaxOttoVonStierlitz.estimateSizeOf(obj)));
    }
}
