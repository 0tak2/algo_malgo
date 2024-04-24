package org.example.adt;

import org.junit.jupiter.api.Test;

class HeapTest {

    @Test
    void add() {
        Heap heap = new Heap();
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(2);
    }

    @Test
    void delete() {
        Heap heap = new Heap();
        heap.add(5);
        heap.add(3);
        heap.delete();
        heap.add(7);
        heap.add(2);
    }
}