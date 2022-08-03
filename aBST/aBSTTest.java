import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {
    @Test
    void FindKeyIndex_test(){
        aBST listTree = new aBST(2);
        listTree.Tree[0] = 8;
        listTree.Tree[1] = 4;
        listTree.Tree[2] = 12;
        listTree.Tree[3] = 2;
        listTree.Tree[4] = 6;
        listTree.Tree[5] = 10;
        listTree.Tree[6] = 14;

        assertNull(listTree.FindKeyIndex(16));
        assertEquals(2, listTree.FindKeyIndex(12));
        listTree.Tree[4] = null;
        assertEquals(-4, listTree.FindKeyIndex(6));
    }

    @Test
    void AddKey_test(){
        aBST listTree = new aBST(2);
        listTree.Tree[0] = 8;
        listTree.Tree[1] = 4;
        listTree.Tree[2] = 12;
        listTree.Tree[3] = 2;
        listTree.Tree[4] = null;
        listTree.Tree[5] = null;
        listTree.Tree[6] = 14;

        assertEquals(4, listTree.AddKey(7));
        assertEquals(5, listTree.AddKey(9));
        assertEquals(0, listTree.AddKey(8));
        assertEquals(-1, listTree.AddKey(16));
    }
}