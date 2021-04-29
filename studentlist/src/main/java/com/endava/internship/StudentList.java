package com.endava.internship;

import java.util.*;

public class StudentList implements List<Student> {

    private Student[] elements;

    private static final int DEFAULT_LENGTH = 10;
    private int lastElementIndex = -1;


    public StudentList() {
        elements = new Student[DEFAULT_LENGTH];
    }

    public StudentList(int length) {
        if (length < 0)
            throw new IllegalArgumentException();

        elements = new Student[length];
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
    public Iterator<Student> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Student student) {
        ensureSufficientSpace();
        elements[++lastElementIndex] = student;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }

        int i = 0;
        if (o == null) {
            while (i < size() && elements[i] != null) {
                i++;
            }
        } else {
            if (!(o instanceof Student)) {
                throw new ClassCastException();
            }
            while (i < size() && !elements[i].equals(o)) {
                i++;
            }
        }

        if (i < size()) {
            shiftLeftFrom(i);
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
    public boolean addAll(Collection<? extends Student> collection) {
        boolean changed = false;
        for (Student newStudent : collection) {
            changed = add(newStudent);
        }
        return changed;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Student> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        while (lastElementIndex > -1) {
            elements[lastElementIndex--] = null;
        }
    }

    @Override
    public Student get(int i) {
        if (isEmpty() || i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }
        return elements[i];
    }

    @Override
    public Student set(int i, Student student) {
        if (i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }

        Student replacedElement = elements[i];
        elements[i] = student;

        return replacedElement;
    }

    @Override
    public void add(int i, Student student) {
        if (i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }

        ensureSufficientSpace();
        shiftRightFrom(i);
        elements[i] = student;
        lastElementIndex++;
    }

    @Override
    public Student remove(int i) {
        if (isEmpty() || i < 0 || i > lastElementIndex) {
            throw new IndexOutOfBoundsException();
        }

        Student removedElement = elements[i];
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

        int index = -1;
        if (o == null) {
            for (Student element : elements) {
                index++;
                if (element == null) {
                    return index;
                }
            }
        } else {
            if (!(o instanceof Student)) {
                throw new ClassCastException();
            }
            for (Student element : elements) {
                index++;
                if (element.equals(o)) {
                    return index;
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
            if (!(o instanceof Student)) {
                throw new ClassCastException();
            }
            for (int i = lastElementIndex; i >= 0; i--) {
                if (elements[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<Student> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<Student> listIterator(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return new ListItr(i);
    }

    @Override
    public List<Student> subList(int i, int i1) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    private Student[] copyOfArray(Student[] source, int size) {
        Student[] newArr = new Student[size];

        for (int i = 0; i < source.length; i++) {
            newArr[i] = source[i];
        }

        return newArr;
    }

    private void doubleSize() {
        int newSize = elements.length == 0 ? 1 : elements.length * 2;
        elements = copyOfArray(elements, newSize);
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

    private class Itr implements Iterator<Student> {
        protected int nextElementIndex = 0;
        protected int lastIndex = -1;

        @Override
        public boolean hasNext() {
            return nextElementIndex != size();
        }

        @Override
        public Student next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastIndex = nextElementIndex;

            return elements[nextElementIndex++];
        }

        @Override
        public void remove() {
            if (lastIndex < 0 || lastIndex >= size()) {
                throw new IllegalStateException();
            }

            if (nextElementIndex > lastIndex) {
                nextElementIndex--;
            }

            StudentList.this.remove(lastIndex);
        }

    }


    private class ListItr extends Itr implements ListIterator<Student> {

        ListItr(int index) {
            nextElementIndex = index;
        }

        @Override
        public boolean hasPrevious() {
            return nextElementIndex != 0;
        }

        @Override
        public Student previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            lastIndex = nextElementIndex;

            return elements[--nextElementIndex];
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
        public void set(Student student) {
            StudentList.this.set(lastIndex, student);
        }

        @Override
        public void add(Student student) {
            StudentList.this.add(nextElementIndex, student);
        }

    }

}
