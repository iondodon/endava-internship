package com.endava.internship;

import java.util.*;

public class ArrayList<T> implements List<T> {

    private Object[] elements;

    private static final int DEFAULT_LENGTH = 10;
    private int lastElementIndex = -1;


    public ArrayList() {
        elements = new Object[DEFAULT_LENGTH];
    }

    public ArrayList(int length) {
        if (length < 0)
            throw new IllegalArgumentException();

        elements = new Object[length];
    }

    public ArrayList(T[] defaultArray) {
        elements = defaultArray;
        lastElementIndex = defaultArray.length - 1;
    }

    @Override
    public int size() {
        return lastElementIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return lastElementIndex == -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return copyOfArray(elements, elements.length);
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        if (isEmpty()) {
            return ts;
        }
        if (ts.length >= elements.length) {
            copyIntoArray(elements, ts);
            return ts;
        }

        Object[] newArr = new Object[elements.length];
        copyIntoArray(elements, newArr);

        return (T[]) newArr;
    }

    @Override
    public boolean add(T element) {
        ensureSufficientSpace();
        elements[++lastElementIndex] = element;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }

        int index = indexOf(o);

        if (index != -1) {
            shiftLeftFrom(index);
            elements[lastElementIndex] = null;
            lastElementIndex--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean changed = false;
        for (T element : collection) {
            changed = add(element);
        }
        return changed;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        int oldSize = size();

        for (T element: collection) {
            add(i, element);
        }

        return size() != oldSize;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int oldSize = size();

        int index;
        for (Object element : collection) {
            index = indexOf(element);
            while (index > -1) {
                remove(index);
                index = indexOf(element);
            }
        }

        return size() != oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int oldSize = size();

        for (T element : this) {
            if (!collection.contains(element)) {
                remove(element);
            }
        }

        return oldSize != size();
    }

    @Override
    public void clear() {
        while (lastElementIndex > -1) {
            elements[lastElementIndex--] = null;
        }
    }

    @Override
    public T get(int i) {
        if (isEmpty() || i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[i];
    }

    @Override
    public T set(int i, T element) {
        if (i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }

        T replacedElement = (T) elements[i];
        elements[i] = element;

        return replacedElement;
    }

    @Override
    public void add(int index, T element) {
        if(isEmpty() && index != 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!isEmpty() && (index < 0 || index > lastElementIndex)) {
            throw new IndexOutOfBoundsException();
        }

        ensureSufficientSpace();
        shiftRightFrom(index);
        elements[index] = element;
        lastElementIndex++;
    }

    @Override
    public T remove(int i) {
        if (isEmpty() || i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }

        T removedElement = (T) elements[i];
        shiftLeftFrom(i);
        elements[lastElementIndex] = null;
        lastElementIndex--;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (isEmpty()) {
            return -1;
        }

        if (o == null) {
            for (int i  = 0; i < size(); i++) {
                if(elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if(elements[i] != null && elements[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (isEmpty()) {
            return -1;
        }

        if (o == null) {
            for (int i = lastElementIndex; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = lastElementIndex; i >= 0; i--) {
                if (elements[i] != null && elements[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return new ListItr(i);
    }

    @Override
    public List<T> subList(int i, int i1) {
        T[] subArray = subArray(i, i1);
        return new ArrayList<T>(subArray);
    }

    private T[] copyOfArray(Object[] source, int newLength) {
        if(newLength < source.length) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newArr = new Object[newLength];
        for (int i = 0; i < source.length; i++) {
            newArr[i] = source[i];
        }

        return (T[]) newArr;
    }

    private <T> void copyIntoArray(Object[] source, T[] destination) {
        if(source.length > destination.length){
            throw new ArrayIndexOutOfBoundsException();
        }

        int i = 0;
        while(i < source.length) {
            destination[i] = (T) source[i];
            i++;
        }
        while (i < destination.length) {
            destination[i] = null;
            i++;
        }
    }

    private T[] subArray(int i, int i1) {
        if (i < 0 || i1 > size() || i > i1) {
            throw new IndexOutOfBoundsException();
        }

        T[] subArray = (T[]) new Object[i1 - i];
        for (int j = i; j < i1; j++) {
            subArray[j] = (T) elements[j];
        }

        return subArray;
    }

    private void doubleSize() {
        int newLength = elements.length == 0 ? 1 : elements.length * 2;
        elements = copyOfArray(elements, newLength);
    }

    private void shiftLeftFrom(int fromIndex) {
        for (int i = fromIndex; i < size() - 1; i++) {
            elements[i] = elements[i + 1];
        }
    }

    private void shiftRightFrom(int fromIndex) {
        for (int i = size(); i > fromIndex; i--) {
            elements[i] = elements[i - 1];
        }
    }

    private void ensureSufficientSpace() {
        if (lastElementIndex + 1 == elements.length)
            doubleSize();
    }

    private class Itr implements Iterator<T> {
        protected int nextElementIndex = 0;
        protected int lastIndex = -1;

        @Override
        public boolean hasNext() {
            return nextElementIndex != size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastIndex = nextElementIndex;

            return (T) elements[nextElementIndex++];
        }

        @Override
        public void remove() {
            if (lastIndex < 0 || lastIndex >= size()) {
                throw new IllegalStateException();
            }

            if (nextElementIndex > lastIndex) {
                nextElementIndex--;
            }

            ArrayList.this.remove(lastIndex);
        }

    }


    private class ListItr extends Itr implements ListIterator<T> {
        int nextElementIndexForPrevious;

        ListItr() {
            nextElementIndex = 0;
            nextElementIndexForPrevious = size() - 1;
        }

        ListItr(int index) {
            nextElementIndex = index;
            nextElementIndexForPrevious = index;
        }

        @Override
        public boolean hasPrevious() {
            return nextElementIndexForPrevious >= 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            lastIndex = nextElementIndexForPrevious;

            return (T) elements[nextElementIndexForPrevious--];
        }

        @Override
        public int nextIndex() {
            return hasNext() ? nextElementIndex : size();
        }

        @Override
        public int previousIndex() {
            return lastIndex;
        }

        @Override
        public void set(T element) {
            ArrayList.this.set(lastIndex, element);
        }

        @Override
        public void add(T element) {
            if(lastIndex < 0) {
                throw new IllegalStateException();
            }
            if(!isEmpty() && (nextElementIndex < 0 || nextElementIndex >= size())) {
                throw new IllegalStateException();
            }
            ArrayList.this.add(nextElementIndex, element);
        }

    }

}
