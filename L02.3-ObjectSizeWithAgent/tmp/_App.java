package ru.otus.java;

import java.util.ArrayList;
import java.util.List;

public class _App
{

    /*
    * To start:
    * java -javaagent:target/ObjectSizeWithAgent-1.0-SNAPSHOT-agent.jar -jar target/ObjectSizeWithAgent-1.0-SNAPSHOT-main-with-dependencies.jar
    */

    private static long objSize=0;
    private static final long MEGABYTE = 1024L * 1024L;

    private static Integer [] intArr = new Integer[1000_000];
    private static String [] strArr = new String[10];
    private static Object [] objArr = new Object[10];



    public static void main( String[] args )
    {
        System.out.println( "Hello! I'm Object's size estimator." );

        System.out.println("JVM has " + Runtime.getRuntime().totalMemory() + " bytes of memory");
        System.out.println(Runtime.getRuntime().freeMemory() + " bytes are free");
        System.out.println((Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory()) + " bytes used");

        System.out.println( "Empty string: \"\"" );
        System.out.println( "As to JVM type of \"\" is " + getObjectType("") );
        System.out.println( "The size of \"\" is " + getSizeOf("") + " bytes" );
        System.out.println( "JVM has " + Runtime.getRuntime().totalMemory() + " bytes of memory");
        System.out.println( Runtime.getRuntime().freeMemory() + " bytes are free");
        System.out.println( Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory() + " bytes used");


        Object obj = new Object();

        System.out.println( "Empty Object: Object obj" );
        System.out.println( "As to JVM type of obj is " + getObjectType(obj) );
        System.out.println( "The size of obj is " + getSizeOf(obj) + " bytes" );
        System.out.println( Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory() + " bytes used");

        System.out.println( "Integers array: Integer [] intArr" );
        System.out.println( "As to JVM type of intArr is " + getObjectType(intArr) );
        System.out.println( "The size of intArr w/o elements is " + getSizeOf(intArr) + " bytes" );
        System.out.println( Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory() + " bytes used");

        System.out.printf( "Adding elements to intArr\n" );
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = i;
            if(0 == i%100_000) {
                System.out.println("Element " + (i + 1) + " of size " + getSizeOf(intArr[i]) + " is added");
                System.out.println("Size of intArr is " + getSizeOf(intArr));
                System.out.println(Runtime.getRuntime().totalMemory() -
                        Runtime.getRuntime().freeMemory() + " bytes used");
            }
        }

        System.out.println( "Strings array: String [] strArr" );
        System.out.println( "As to JVM type of strArr w/o elements is " + getObjectType(strArr) );
        System.out.println( "The size of strArr is " + getSizeOf(strArr) + " bytes" );
        System.out.println( Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory() + " bytes used");

        System.out.println( "Objects array: Object [] objArr" );
        System.out.println( "As to JVM type of objArr w/o elements is " + getObjectType(objArr) );
        System.out.println( "The size of objArr is " + getSizeOf(objArr) + " bytes" );
        System.out.println( Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory() + " bytes used");


        List<Person> list = new ArrayList<Person>();
        for (int i = 0; i <= 100_000; i++) {
            list.add(new Person("Jim", "Knopf"));
        }
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));


    }

    private static String getObjectType(Object o){
        return o.getClass().getSimpleName();
    };

    private static long getSizeOf(Object o){
        return MaxOttoVonStierlitz.estimateSizeOf(o);
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}

class _A {
    Integer id;
    String name;
};

class _Person {
    private String fName, sName;

    public _Person (String fName, String sName){
        this.fName = fName;
        this.sName = sName;
    }

};

class _B {};

class _C {};
