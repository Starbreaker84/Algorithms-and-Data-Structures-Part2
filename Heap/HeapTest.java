import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    void add_element_test_empty(){
        Heap heap = new Heap();
        int[] a = {12, 8, 5, 4, 19 ,15, 21};
        heap.MakeHeap(a, 2);
        int[] comparedArray = {21, 12, 19, 4, 8, 5, 15};
        assertArrayEquals(comparedArray, heap.HeapArray);
    }

    @Test
    void add_element_test_full_heap(){
        Heap heap = new Heap();
        int[] a = {12, 8, 5, 4, 19 ,15, 21};
        heap.MakeHeap(a, 2);
        assertFalse(heap.Add(9));
    }

    @Test
    void add_element_test_null_heap(){
        Heap heap = new Heap();
        assertFalse(heap.Add(5));
    }

    @Test
    void getMax_test(){
        Heap heap = new Heap();
        int[] a = {12, 8, 5, 4, 19 ,15, 21};
        heap.MakeHeap(a, 2);
        assertEquals(21, heap.GetMax());
        int[] comparedArray = {19, 12, 15, 4, 8, 5, -1};
        assertArrayEquals(comparedArray, heap.HeapArray);
    }

    @Test
    void getMax_test_empty(){
        Heap heap = new Heap();
        int[] a = {15};
        heap.MakeHeap(a, 2);
        assertEquals(15, heap.GetMax());
        assertEquals(-1, heap.GetMax());
        int[] comparedArray = {-1, -1, -1, -1, -1, -1, -1};
        assertArrayEquals(comparedArray, heap.HeapArray);
    }
}