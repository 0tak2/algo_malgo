package org.example.adt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    @DisplayName("삽입 테스트 1")
    void insert1() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals(linkedList.get(0), "A");
        assertEquals(linkedList.get(1), "B");
        assertEquals(linkedList.get(2), "C");
    }

    @Test
    @DisplayName("삽입 테스트 2")
    void insert2() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add(1, "C");

        assertEquals(linkedList.get(0), "A");
        assertEquals(linkedList.get(1), "C");
        assertEquals(linkedList.get(2), "B");
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        linkedList.delete(1); // fixme

        assertEquals(linkedList.get(0), "A");
        assertEquals(linkedList.get(1), "C");
    }
}