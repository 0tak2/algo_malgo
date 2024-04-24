package org.example.adt;

public class Heap {
    private final int[] data;
    private int leafIdx;

    public Heap() {
        this(16);
    }

    public Heap(int capacity) {
        data = new int[capacity];
        leafIdx = -1;
    }

    private int getParentIdx(int idx) {
        return (idx - 1) / 2;
    }

    private int getParentVal(int idx) {
        return data[getParentIdx(idx)];
    }

    private int getLeftChildIdx(int idx) {
        return 2 * idx + 1;
    }

    private int getLeftChildVal(int idx) {
        return data[getLeftChildIdx(idx)];
    }

    private int getRightChildIdx(int idx) {
        return 2 * idx + 2;
    }

    private int getRightChildVal(int idx) {
        return data[getRightChildIdx(idx)];
    }

    private void swap(int idxA, int idxB) {
        int tmp = data[idxA];
        data[idxA] = data[idxB];
        data[idxB] = tmp;
    }

    public void add(int value) {
        int newValIdx = ++leafIdx;
        data[newValIdx] = value;

        while(newValIdx >= 0) {
            int parentIdx = getParentIdx(newValIdx);

            if (parentIdx == 0 && newValIdx == 0) {
                break;
            }

            if (value > data[parentIdx]) {
                break;
            }

            swap(newValIdx, parentIdx);
            newValIdx = parentIdx;
        }
    }

    public void delete() {
        data[leafIdx--] = 0;
    }
}
