package com.endava.internship;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;


import static org.assertj.core.api.Assertions.*;

class ArrayListTest {

    ArrayList<Student> list;
    Student student;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        student = new Student("Ion", LocalDate.now(), "Description");
    }

    @Test
    void sizeEquals0WhenListInitialized() {
        assertThat(list).isEmpty();
    }

    @Test
    void sizeEqualNumberOfElementAdded() {
        list.add(student);
        list.add(student);

        assertThat(list).hasSize(2);
    }

    @Test
    void isEmptyBeforeAddingElements() {
        assertThat(list).isEmpty();
    }

    @Test
    void isEmptyAfterRemovingAllElements() {
        list.add(student);
        list.remove(student);

        assertThat(list).isEmpty();
    }

    @Test
    void containsAddedElement() {
        list.add(student);

        assertThat(list).contains(student);
    }

    @Test
    void doesNotContainsNonAddedElement() {
        assertThat(list).doesNotContain(student);
    }

    @Test
    void iteratorElementsAreElementsFromList() {
        list.add(student);
        list.add(new Student("asd", LocalDate.now(), "dsadsa"));

        Iterator<Student> iterator = list.iterator();

        for (int i = 0; i < list.size(); i++) {
            Student std = list.get(i);
            assertThat(std).isEqualTo(iterator.next());
        }
    }

    @Test
    void toArrayReturnsArrayOfStudentsFromList() {
        list.add(student);
        list.add(student);

        Student[] arr = new Student[]{student, student};
        Object[] returnedArray = list.toArray();

        for (int i = 0; i < arr.length; i++) {
            assertThat(arr[i]).isEqualTo(returnedArray[i]);
        }
    }

    @Test
    void testToArrayWithArraySentAsParameterReturnsNewArray() {
        list.add(student);
        list.add(student);

        Student[] students = new Student[list.size() - 1];

        assertThat(students).isNotSameAs(list.toArray(students));
    }

    @Test
    void testToArrayWithArraySentAsParameterReturnsSameArray() {
        list.add(student);
        list.add(student);

        Student[] students = new Student[list.size() + 100];

        assertThat(students).isSameAs(list.toArray(students));
    }

    @Test
    void testToArrayWithArraySentAsParameterReturnsListsElements() {
        Student[] students = new Student[]{student, new Student("b", LocalDate.now(), "abc")};
        list = new ArrayList<>(students);

        Student[] returnedStudents = list.toArray(new Student[10]);

        assertThat(returnedStudents[0]).isEqualTo(students[0]);
        assertThat(returnedStudents[1]).isEqualTo(students[1]);
    }

    @Test
    void containsAddedElementAtIndex() {
        list.add(new Student("ab", LocalDate.now(), "abc"));
        list.add(new Student("abc", LocalDate.now(), "abcs"));
        list.add(new Student("abb", LocalDate.now(), "absc"));
        list.add(1, student);

        assertThat(list.get(1)).isEqualTo(student);
    }

    @Test
    void throwsIndexOutOfBoundsExceptionIfBadIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.add(-1, student));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.add(1, student));
        assertThatCode(() -> list.add(0, student)).doesNotThrowAnyException();
    }

    @Test
    void removesAddedElement() {
        list.add(student);
        assertThat(list.remove(student)).isTrue();
        assertThat(list).doesNotContain(student);
    }

    @Test
    void removeReturnsFalseIfNonExistentElement() {
        assertThat(list.remove(student)).isFalse();
    }

    @Test
    void doesNotRemoveNonExistentElement() {
        list.remove(student);
        assertThat(list.remove(student)).isFalse();
    }

    @Test
    void addAllWillAddAllElements() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        Collection<Student> set = new HashSet<>();
        set.add(students1);
        set.add(students2);

        list.addAll(set);

        assertThat(list).hasSize(2);
        assertThat(list).contains(students1);
        assertThat(list).contains(students2);
    }

    @Test
    void addAllFromIndexAddFirstAtIndex() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        Collection<Student> set = new HashSet<>();
        set.add(students1);
        set.add(students2);

        list.add(null);
        list.add(null);

        list.addAll(1, set);

        assertThat(students1).isEqualTo(list.get(1));
    }

    @Test
    void addAllFromIndexThrowsIndexOutOfBoundIfBadIndex() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        Collection<Student> set = new HashSet<>();
        set.add(students1);
        set.add(students2);

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.addAll(1, set));
    }

    @Test
    void removesAllNonNullProvided() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        Collection<Student> set = new HashSet<>();
        set.add(students1);
        set.add(students2);

        list.add(students1);
        list.add(students2);

        list.removeAll(set);

        assertThat(list).hasSize(0);
    }

    @Test
    void removesAllNull() {
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(student);

        java.util.ArrayList<Student> l = new java.util.ArrayList<>();
        l.add(null);
        l.add(student);

        list.removeAll(l);

        assertThat(list).hasSize(0);
    }

    @Test
    void retainAllRetainsOnlySpecifiedElements() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        list.add(students1);
        list.add(students2);
        list.add(null);

        Collection<Student> set = new HashSet<>();
        set.add(students1);
        set.add(null);

        list.retainAll(set);

        assertThat(list).doesNotContain(students2);
    }

    @Test
    void clearsAllElements() {
        list.add(student);
        list.add(student);

        list.clear();

        assertThat(list).isEmpty();
    }

    @Test
    void getReturnsElementOnIndex() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        list.add(students1);
        list.add(null);
        list.add(students2);

        assertThat(students2).isEqualTo(list.get(2));
        assertThat(list.get(1)).isNull();
    }

    @Test
    void getThrowsIndexOutOfBoundsException() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        list.add(students1);
        list.add(null);
        list.add(students2);

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.get(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.get(list.size()));

    }

    @Test
    void setSetsElementOnIndex() {
        list.add(null);

        list.set(0, student);

        assertThat(student).isEqualTo(list.get(0));
    }

    @Test
    void setThrowsIndexOutOfBoundsExceptionIfIndexDoesNotExist() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.set(0, student));
    }

    @Test
    void setOnIndexReturnReplacedElement() {
        Student students1 = new Student("a", LocalDate.now(), "abc");
        Student students2 = new Student("n", LocalDate.now(), "vcx");

        list.add(students1);

        Student returnedStudent = list.set(0, students2);

        assertThat(returnedStudent).isEqualTo(students1);
    }

    @Test
    void testAddOnIndexWhenListEmpty() {
        list.add(0, student);

        assertThat(list).hasSize(1);
        assertThat(student).isEqualTo(list.get(0));
    }

    @Test
    void testAddOnIndexWhenListNotEmpty() {
        list.add(null);
        list.add(null);

        list.add(0, student);

        assertThat(list).hasSize(3);
        assertThat(list.get(0)).isEqualTo(student);
    }

    @Test
    void testAddOnIndexThrowsIndexOutOfBounds() {
        list.add(null);
        list.add(null);

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.add(-1, student));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.add(list.size(), student));
    }

    @Test
    void testRemoveOnIndexRemoveElementOnIndex() {
        list.add(student);
        list.remove(0);
        assertThat(list).isEmpty();
    }

    @Test
    void testRemoveOnIndexReturnsRemovedElement() {
        list.add(student);
        Student std = list.remove(0);

        assertThat(std).isEqualTo(student);
    }

    @Test
    void testRemoveOnIndexThrowsIndexOutOfBoundException() {
        list.add(student);

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.remove(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.remove(list.size()));
    }

    @Test
    void indexOfReturnsFirstIndexIfElementFound() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThat(list.indexOf(student)).isEqualTo(0);
        assertThat(list.indexOf(null)).isEqualTo(1);
    }

    @Test
    void indexOfReturnsMinusOneIfElementNotFound() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThat(list.indexOf(new Student("a", LocalDate.now(), "v"))).isEqualTo(-1);
    }

    @Test
    void indexOfReturnsMinusOneWhenListEmpty() {
        assertThat(list.indexOf(null)).isEqualTo(-1);
    }

    @Test
    void lastIndexOfReturnsLastIndexOfElementIfFound() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThat(list.lastIndexOf(student)).isEqualTo(2);
        assertThat(list.lastIndexOf(null)).isEqualTo(1);
    }

    @Test
    void lastIndexOfReturnsMinusOneIfElementNotFound() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThat(list.lastIndexOf(new Student("a", LocalDate.now(), "v"))).isEqualTo(-1);
    }

    @Test
    void lastIndexOfReturnsMinusOneWhenListEmpty() {
        assertThat(list.lastIndexOf(student)).isEqualTo(-1);
    }

    @Test
    void listIteratorContainsElementsFromListInOrder() {
        list.add(new Student("asc", LocalDate.now(), "asd"));
        list.add(null);
        list.add(new Student("ad", LocalDate.now(), "sad"));

        ListIterator<Student> iterator = list.listIterator();
        for (int index = 0; index < list.size(); index++) {
            assertThat(list.get(index)).isEqualTo(iterator.next());
        }
    }

    @Test
    void listIteratorCanAccessElementsInReverseOrder() {
        list.add(new Student("asc", LocalDate.now(), "asd"));
        list.add(null);
        list.add(new Student("ad", LocalDate.now(), "sad"));

        ListIterator<Student> iterator = list.listIterator();
        for (int index = list.size() - 1; index >= 0; index--) {
            assertThat(list.get(index)).isEqualTo(iterator.previous());
        }
    }

    @Test
    void listIteratorFromIndexGivesElementsStartingWithIndex() {
        list.add(new Student("ad", LocalDate.now(), "sad"));
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator(1);

        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(student);
    }

    @Test
    void listIteratorFromIndexThrowsIndexOutOfBoundIfBadIndex() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.listIterator(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.listIterator(list.size()));
    }

    @Test
    void subListNumberOfElementRespectsRange() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThat(list.subList(0, 0)).isEmpty();
        assertThat(list.subList(0, 1)).hasSize(1);
        assertThat(list.subList(0, 2)).hasSize(2);
        assertThat(list.subList(0, 3)).hasSize(3);
    }

    @Test
    void subListReturnCorrectElements() {
        list.add(student);
        list.add(null);
        list.add(new Student("ad", LocalDate.now(), "sad"));

        List<Student> subList = list.subList(0, 2);

        assertThat(subList.get(0)).isEqualTo(student);
        assertThat(subList.get(1)).isNull();
    }


    @Test
    void subListThrowsIndexOutOfBoundExceptionWhenBadRange() {
        list.add(student);
        list.add(null);
        list.add(student);

        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.subList(-1, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.subList(1, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.subList(-1, 4));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> list.subList(1, 5));
    }

    @Test
    void subListReturnsEmptyListWhenEmptyRangeProvided() {
        assertThat(list.subList(0, 0)).isEmpty();
    }

    @Test
    void listIteratorRemovesLastElementReturned() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();
        iterator.next();
        iterator.next();

        iterator.remove();

        assertThat(list).doesNotContainNull();
    }

    @Test
    void listIteratorRemoveThrowsIllegalStateExceptionIfNoElementWasReturned() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();

        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> iterator.remove());
    }

    @Test
    void listIteratorThrowsNoSuchElementExceptionIfNextCalledWhenNoElementsLeft() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> iterator.next());
    }

    @Test
    void listIteratorThrowsNoSuchElementExceptionIfPreviousCalledWhenNoElementsLeft() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator(2);

        while (iterator.hasPrevious()) {
            iterator.previous();
        }

        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> iterator.previous());
    }

    @Test
    void listIteratorSetReplacesLastElementReturned() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();
        iterator.next();
        iterator.set(null);

        assertThat(list.get(0)).isNull();
    }

    @Test
    void listIteratorAddsElementBeforeNextElementThatShouldBeReturned() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();
        iterator.next();
        iterator.next();
        iterator.add(null);

        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(student);
    }

    @Test
    void listIteratorWhenAddThrowsIllegalStateExceptionIfNoElementWasReturned() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();

        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> iterator.add(null));
    }

    @Test
    void listIteratorPreviousAndNextIndex() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();
        iterator.next();

        assertThat(iterator.previousIndex()).isEqualTo(0);
        assertThat(iterator.nextIndex()).isEqualTo(1);
    }

    @Test
    void listIteratorPreviousIndexIsMinusOneIfNoElementReturned() {
        list.add(student);
        list.add(null);
        list.add(student);

        ListIterator<Student> iterator = list.listIterator();

        assertThat(iterator.previousIndex()).isEqualTo(-1);
    }
}