package ru.otus.java;

import java.util.ArrayList;
import java.util.List;


public class MyTask implements Runnable {

    private int size;
    private long pause;

    public MyTask(int s, long p) {
        this.size = s;
        this.pause = p;
    }

    public void generateOOM() {

        int value = size;
        List<String> list = new ArrayList<>();
        int i = 1;
        String s;
        while (true) {
                for (int j = 0; j < value; j++) {
                        s = new String("string " + i);
                        list.add(s);
                }
                list.subList(size / 2, list.size()).clear();
                value *= 2;
                i++;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        generateOOM();
    }
}
