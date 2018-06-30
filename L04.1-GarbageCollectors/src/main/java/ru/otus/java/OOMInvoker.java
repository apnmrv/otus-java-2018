package ru.otus.java;

import java.util.ArrayList;
import java.util.List;

class OOMInvoker {
    private int size = 0;

    public OOMInvoker(int number) {
        this.size = number;
    }

    public void generateOOM() throws Exception {
        int value = size;
        List<String> list = new ArrayList<>();
        int i = 1;
        String s;
        while (true) {
            System.out.println("Iteration " + i + " Free Mem: " + Runtime.getRuntime().freeMemory());
            for (int j = 0; j < value; j++) {
                s = new String("string "+i);
                list.add(s);
            }
            list.subList(size/2, list.size()).clear();
            System.out.println("List purged");
            value *= 2;
            i++;
            Thread.sleep(1000);
        }
    }
}
