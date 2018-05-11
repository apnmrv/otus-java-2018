package ru.otus.java;

import java.util.*;

public class App
{
    /**
     *  To build:
     *  > mvn clean install
     *
     *  To start:
     *  java -jar target/*.jar some spontaneous strings of your choice
     *
     *  @param strArgs
     */


    public static void main( String[] strArgs)
    {

        List<String> myArrayListToTest = getArrayList();

        List<String> myFilledArrayList = getArrayList(strArgs);

        myArrayListToTest.addAll(myFilledArrayList);

        System.out.printf("Your args : ");
        for(Iterator<?> it = myArrayListToTest.iterator(); it.hasNext();){
            System.out.printf("%s ", it.next());
        }

        myArrayListToTest.sort(null);

        System.out.printf("\nAnd get 'em sorted : ");
        for(Iterator<?> it = myArrayListToTest.iterator(); it.hasNext();){
            System.out.printf("%s ", it.next());
        }
    }

    public static <T> List<T> getArrayList(T [] els) {
        List<T> list = new MyArrayList<>();
        list.addAll(Arrays.asList(els));
        return list;
    }

    public static <T> List<T> getArrayList() {
        return new MyArrayList<>();
    }

    public static <T> List<T> getArrayList(int capacity) {
        return new MyArrayList<>(capacity);
    }
}
