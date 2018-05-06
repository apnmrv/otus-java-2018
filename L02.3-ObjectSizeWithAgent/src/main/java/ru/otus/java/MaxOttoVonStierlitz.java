package ru.otus.java;

import java.lang.instrument.Instrumentation;

public class MaxOttoVonStierlitz {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {

        instrumentation = inst;
        System.out.println("Hello! I`m Stierlitz.");
    }

    public static long estimateSizeOf(Object o){
        if (instrumentation == null) {
            throw new IllegalStateException("Stierlitz's not initialized");
        }
        return instrumentation.getObjectSize(o);
    }
}
