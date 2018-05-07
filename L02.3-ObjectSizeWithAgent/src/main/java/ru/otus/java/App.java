package ru.otus.java;

import java.util.*;

public class App
{

    /*
    * To start:
    * java -javaagent:target/ObjectSizeWithAgent-1.0-SNAPSHOT-agent.jar -jar target/ObjectSizeWithAgent-1.0-SNAPSHOT-main-with-dependencies.jar
    */


    public static void main( String[] args )
    {
        System.out.println("<------------- Object -------------->");
        measureSize(new Object());

        System.out.println("<------------- Boolean -------------->");
        boolean bl = true;
        measureSize(bl);

        System.out.println("<------------- Byte -------------->");
        byte bt = 10;
        measureSize(bt);

        System.out.println("<------------- Short -------------->");
        short st = 100;
        measureSize(st);

        System.out.println("<------------- Integer -------------->");
        int x = 1000;
        measureSize(x);

        System.out.println("<------------- Float -------------->");
        float y = 13.56f;
        measureSize(y);

        System.out.println("<------------- Double -------------->");
        double d = 1235.523423d;
        measureSize(d);

        System.out.println("<------------- Long -------------->");
        double l = 2147483648L;
        measureSize(l);


        System.out.println("<------------- String -------------->");
        measureSize("");
        measureSize("Hello World");
        measureSize(new String(""));
        measureSize(new String("Hello World".toCharArray()));
        measureSize(10);
        measureSize(100);
        measureSize(1000);
        measureSize(new Parent());
        measureSize(new Child());

        Integer [] arrInt = new Integer[1_000_000];
        System.out.println("<------ Array of 1 000 0000 integers ------ >");
        measureSize(arrInt);
        for (int i = 0; i < 1_000_000; i++){
            arrInt[i] = i;
            if(0 == i%100_000) {
                System.out.println( (i+1) + " elements" );
                measureSize(arrInt);
            }
        }

        String [] arrStr = new String[1_000_000];
        System.out.println("<------ Array of 1 000 0000 strings ------ >");
        measureSize(arrStr);
        for (int i = 0; i < 1_000_000; i++){
            arrStr[i] = i+"";
            if(0 == i%100_000) {
                System.out.println( (i+1) + " elements" );
                measureSize(arrStr);
            }
        }

        ArrayList<String> stringArrayList = new ArrayList<>();
        System.out.println("<------ ArrayList of 1 000 0000 strings ------ >");
        measureSize(stringArrayList);
        for (int i = 0; i < 1_000_000; i++){
            stringArrayList.add(i+"");
            if(0 == i%100_000) {
                System.out.println( (i+1) + " elements" );
                measureSize(stringArrayList);
            }
        }

        ArrayList<Parent> parentObjectArrayList = new ArrayList<>();
        System.out.println("<------ ArrayList of 1 000 0000 Parent objects ------ >");
        measureSize(parentObjectArrayList);
        for (int i = 0; i < 1_000_000; i++){
            parentObjectArrayList.add(new Parent());
            if(0 == i%100_000) {
                System.out.println( (i+1) + " elements" );
                measureSize(parentObjectArrayList);
            }
        }

        ArrayList<Child> childObjectArrayList = new ArrayList<>();
        System.out.println("<------ ArrayList of 1 000 0000 Child objects ------ >");
        measureSize(childObjectArrayList);
        for (int i = 0; i < 1_000_000; i++){
            childObjectArrayList.add(new Child());
            if(0 == i%100_000) {
                System.out.println( (i+1) + " elements" );
                measureSize(childObjectArrayList);
            }
        }
    }

    public static void measureSize(Object o) {

        long objectMem = AgentCounter.estimateSizeOf(o);

        long objectMemFull = AgentCounter.estimateFullSizeOf(o);
        System.out.printf("%s, surface only=%d, all the guts included=%d%n",
                o.getClass().getSimpleName(),
                objectMem, objectMemFull);
    }
};

class Parent {
    private int i;
    private boolean b;
    private long l;
};

class Child extends Parent {
    private boolean b;
    private float f;
};

