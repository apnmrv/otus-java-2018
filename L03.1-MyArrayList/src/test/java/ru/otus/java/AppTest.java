package ru.otus.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testGetArrayList()
    {
        String [] sArr = {"String1", "String2"};
        List<String> stringList = App.getArrayList(sArr);

        testListContainsElements(stringList, sArr);
        testNotEmptyList(stringList);
        testListDoNotContainsElement(stringList);

    }

    private void testNotEmptyList(List<?> list) {
        assertTrue(!list.isEmpty());
    }

    private <T> void testListContainsElements(List<T> list, T [] els){
        for(T e : els){
            assertTrue( list.contains(e) );
        }
    }

    private <T> void testListDoNotContainsElement(List<T> list){
        assertTrue(!list.contains("StrangerThings"));
    }
}
