import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {
    @Test
    void GenerateBBSTArray_test(){
        int[] a = {5, 4, 9, 12, 13, 6, 7, 1, 8, 15, 2, 10, 3, 14, 11};
        int[] aBST = {8, 4, 12, 2, 6, 10 , 14, 1, 3, 5, 7, 9, 11, 13, 15};
        assertArrayEquals(aBST, AlgorithmsDataStructures2.GenerateBBSTArray(a));
    }

    @Test
    void GenerateBBSTArray_empty(){
        int[] a = new int[0];
        int[] aBST = new int[0];
        assertArrayEquals(aBST, AlgorithmsDataStructures2.GenerateBBSTArray(a));
    }
}