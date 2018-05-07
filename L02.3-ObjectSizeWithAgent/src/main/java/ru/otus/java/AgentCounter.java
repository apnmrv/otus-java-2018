package ru.otus.java;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.*;
import java.util.*;

public class AgentCounter {

    private static Instrumentation instrumentation;

    public static void premain(String args,
                               Instrumentation inst) {

        AgentCounter.instrumentation = inst;
        System.out.println("Hello! I`m AgentCounter.");
    }

    /** Get object size */
    public static long estimateSizeOf(Object obj){
        if (instrumentation == null) {
            throw new IllegalStateException(
                    "Stierlitz's not initialized");
        }
        return instrumentation.getObjectSize(obj);
    }

    /**Iterating through object's components*/
    public static long estimateFullSizeOf(Object obj){
        Map checked = new HashMap();
        Stack stack = new Stack();
        stack.push(obj);

        long result = 0;

        do {
            result += internalSizeOf(stack.pop(), stack, checked);
        } while (!stack.isEmpty());
        return result;
    }

    private static long internalSizeOf(
            Object obj, Stack stack, Map checked) {
        if (obj == null || checked.containsKey(obj)) {
            return 0;
        }

        Class aClass = obj.getClass();


        if (aClass.isArray()) {
            // counting primitives
            addArrayElementsToStack(aClass, obj, stack);
        } else {
            // counting non-primitives
            while (aClass != null) {
                Field [] fields = aClass.getDeclaredFields();
                for (Field field : fields) {
                    if (!Modifier.isStatic(field.getModifiers())
                            && !field.getType().isPrimitive()) {
                        field.setAccessible(true);
                        try {
                            stack.add(field.get(obj));
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                aClass = aClass.getSuperclass();
            }
        }
        checked.put(obj, null);
        return estimateSizeOf(obj);
    }

    private static void addArrayElementsToStack(
            Class clazz, Object obj, Stack stack) {
        if (!clazz.getComponentType().isPrimitive()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                stack.add(Array.get(obj, i));
            }
        }
    }
}
