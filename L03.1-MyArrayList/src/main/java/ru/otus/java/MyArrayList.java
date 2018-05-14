package ru.otus.java;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private T [] arr;
    private int size = 0;
    private int capacity = 10;

    public MyArrayList() {
        arr = ( T [] ) new Object [capacity];
    }

    public MyArrayList( int initialCapacity ) {
        capacity = initialCapacity;
        arr = ( T [] ) new Object [capacity];
    }

    public MyArrayList(Collection<T> c) {
        arr = ( T [] ) new Object [capacity];
        addAll(c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size() ? true : false;
    }

    @Override
    public boolean contains(Object obj) {
        for (int i = 0; i < size; i++) {
            if(obj == arr[i]) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        int current = 0;
        int previous = -1;
        int last = MyArrayList.this.size;

        MyIterator() {}

        @Override
        public boolean hasNext() {
            return current < last;
        }

        @Override
        public T next() {
            int i = current;
            current = i + 1;
            return arr[previous = i];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(previous);
            current = previous;
            previous = -1;
        }
    }

    @Override
    public T [] toArray() {

        T [] outputArr = (T[]) new Object[size];

        System.arraycopy(arr, 0,
                outputArr, 0,
                size);

        return outputArr;
    }

    @Override
    public <T> T[] toArray(T[] newArr) {
        for (int i = 0; i < size; i++) {
            newArr[i] = (T) arr[i];
        }
        return newArr;
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(1);
        arr[size] = t;
        size++;

        return true;
    }

    private void ensureCapacity(int addition){
        if (size+addition > capacity) {
            int newCapacity = capacity*3/2 + 1;
            T [] newArr = (T[]) new Object [newCapacity];
            System.arraycopy(arr, 0, newArr, 0, capacity);
            arr = newArr;
            capacity = newCapacity;
        }
    }

    @Override
    public boolean remove(Object o) {

        for (int i = 0; i < size; i++) {
            if (arr[i] != null && o.equals(arr[i])){
                for (; i < size-1; i++) {
                    arr[i] = arr[i + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Iterator<?> it = c.iterator(); it.hasNext();)
            if ( !contains(it.next()) ) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (Iterator<? extends T> it = c.iterator(); it.hasNext();)
            add(it.next());
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {

        for (Iterator<? extends T> it = c.iterator(); it.hasNext();){
            add(index, it.next());
            index++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Iterator<?> it = c.iterator(); it.hasNext();){
            Object toRemove = it.next();
            if (contains(toRemove)) remove(toRemove);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        for (Iterator<?> it = this.iterator(); it.hasNext();){
            Object toRemove = it.next();
            if (c.contains(toRemove)) remove(toRemove);
        }
        return true;
    }

    //@ TODO
    @Override
    public void clear() {
        for(Iterator<?> it = iterator(); it.hasNext();) remove(it.next());
    }

    @Override
    public T get(int index) {
        return arr[index];
    }

    @Override
    public T set(int index, T element) {

        T changedElement = arr[index];
        arr[index] = element;

        return changedElement;
    }

    @Override
    public void add(int index, T element) {
        int last = size;
        add(get(last-1));
        for (int i = last; i > index; i--) arr[i] = arr[i-1];
    }

    @Override
    public T remove(int index) {
        T el = get(index);
        remove(el);
        return el;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if(arr[i].equals(o)) return i;
        }
        System.out.println("Element " + o.toString() + " not found in the list");
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {

        for (int i = size; i >= 0; i--) {
            if(arr[i].equals(o)) return i;
        }
        System.out.println("Element " + o.toString() + " not found in the list");
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator(0);
    }

    private class MyListIterator extends MyIterator
            implements ListIterator<T> {

        MyListIterator(int index) {
            super();
            current = index;
        }

        @Override
        public boolean hasPrevious() {
            return current != 0;
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            return current--;
        }

        @Override
        public T previous() {
            int i = current--;
            if (i < 0) throw new NoSuchElementException();
            current = i;
            return arr[previous = i];
        }

        @Override
        public void set(T t) {
            MyArrayList.this.set(previous, t);
        }

        @Override
        public void add(T t) {
            int i = current;
            MyArrayList.this.set(i, t);
            current = i++;
            previous = -1;
        }
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new MyArrayList.MyListIterator(index);
    }

    //@ TODO
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method's not implemented.");
    }
}
