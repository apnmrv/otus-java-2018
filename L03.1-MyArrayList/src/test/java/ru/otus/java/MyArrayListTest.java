package ru.otus.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

public class MyArrayListTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MyArrayListTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MyArrayListTest.class );
    }

    public void testMyArrayListConstructor() {

        List<String> myList = new MyArrayList<>();
        List<String> testList = new ArrayList<>();

        assertEquals(myList.isEmpty(), testList.isEmpty());
        assertEquals(myList.size(), testList.size());
    }

    public void testMyArrayListSizedConstructor() {

        List<?> myList = new MyArrayList<>(35);
        List<?> testList = new ArrayList<>(35);

        assertEquals(myList.isEmpty(), testList.isEmpty());

        assertEquals(myList.size(), testList.size());

    }

    public void testMyArrayListFromCollectionConstructor() {

        Collection<String> collection = new ArrayList<>();
        collection.add("String1");
        collection.add("String2");

        List <String> testList = new ArrayList<>(collection);
        List <String> myList = new MyArrayList<>(collection);

        assertEquals(myList.isEmpty(), testList.isEmpty());
        assertEquals(testList.size(), myList.size());
    }

    public void testSize(){

        List <Number> testlist = new ArrayList<>();

        List <Number> myList = new MyArrayList<>();

        assertEquals(myList.size(), testlist.size());

        myList.add(3);
        testlist.add(3);

        assertEquals(myList.size(), testlist.size());

        myList.add(5);
        assertTrue(myList.size() != testlist.size());
    }

    public void testIsEmpty(){
        List <String> myList = new MyArrayList<>();
        assertTrue(myList.isEmpty());
    }

    public void testContains(){
        List <String> myList = new MyArrayList<>();
        List <String> testList = new ArrayList<>();

        myList.add("string");
        testList.add("string");

        assertEquals(testList.contains("string"), myList.contains("string"));

        assertEquals(testList.contains("StrangeString"), myList.contains("StrangeString"));

    }

    public void testToArray(){
        List <String> myList = new MyArrayList<>();
        List <String> testList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            myList.add(i+"");
            testList.add(i+"");
        }

        Object [] myArr = myList.toArray();
        Object [] testArr = testList.toArray();

        for (int i = 0; i < 15; i++) {
            assertEquals(testArr[i], myArr[i]);
        }
    }

    public void testToSpecifiedArray(){
        List <String> myList = new MyArrayList<>();
        List <String> testList = new ArrayList<>();
        String [] myArr = new String[100];
        String [] testArr = new String[100];

        for (int i = 0; i < 100; i++) {
            myList.add(i+"");
            testList.add(i+"");
        }

        myList.toArray(myArr);
        testList.toArray(testArr);

        for (int i = 0; i < 100; i++) {

            assertEquals(testArr[i], myArr[i]);

        }
    }

    public void testIterator(){
        List <String> myList = new MyArrayList<>();
        List <String> testList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            myList.add(i+"");
            testList.add(i+"");
        }

        for(Iterator<String> it = myList.iterator(); it.hasNext();){
            if (it.next().equals("5")) {
                it.remove();
            }
        }

        for(Iterator<String> it = testList.iterator(); it.hasNext();){
            if (it.next().equals("5")) {
                it.remove();
            }
        }

        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), myList.get(i));
        }
    }

    public void testAdd(){
        List <Integer> myList = new MyArrayList<>();
        List <Integer> testList = new ArrayList<>();

        for (int i = 0; i < 1000_000; i++) {
            myList.add(i);
            testList.add(i);
            assertEquals(testList.get(i), myList.get(i));
        }

        assertEquals(testList.size(), myList.size());
        assertEquals(testList.getClass().getComponentType(), myList.getClass().getComponentType());
    }

    public void testRemove(){
        List <String> myList = new MyArrayList<>();
        List <String> testList = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            myList.add(i+"");
            testList.add(i+"");
        }

        assertEquals(testList.remove((Integer)5), myList.remove((Integer)5));

        assertEquals(testList.remove("String"), myList.remove("String"));
    }

    public void testAddAll(){
        List <String> myList = new MyArrayList<>();
        List <String> testList = new ArrayList<>();

        Collection<String> strColl = new LinkedHashSet<>();
        for (int i = 0; i < 100_000; i++) {
            strColl.add(i+"");
        }

        assertEquals(testList.addAll(strColl), myList.addAll(strColl));

        assertEquals(Collections.addAll(testList, "$", "%", "&"),
                Collections.addAll(myList, "$", "%", "&"));
    }

    public void testSort(){
        List <String> testList = new ArrayList<>();

        testList.add("B");
        testList.add("V");
        testList.add("C");
        testList.add("D");
        testList.add("a");
        testList.add("S");

        List <String> myList = new MyArrayList<>();
        myList.add("B");
        myList.add("V");
        myList.add("C");
        myList.add("D");
        myList.add("a");
        myList.add("S");

        Collections.sort(testList);
        Collections.sort(myList);

        for (int i = 0; i < myList.size(); i++) {
            assertEquals(testList.get(i), myList.get(i));
        }

        testList.listIterator(6).hasPrevious();
    }
}
