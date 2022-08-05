import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {
    @Test
    void GenerateTree_test(){
        int[] keys = {3, 5, 8, 9, 11, 12, 16, 32};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(keys);

        assertEquals(11, tree.Root.NodeKey);
        assertEquals(8, tree.Root.LeftChild.NodeKey);
        assertEquals(16, tree.Root.RightChild.NodeKey);
        assertEquals(5, tree.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(9, tree.Root.LeftChild.RightChild.NodeKey);
        assertEquals(3, tree.Root.LeftChild.LeftChild.LeftChild.NodeKey);
        assertEquals(12, tree.Root.RightChild.LeftChild.NodeKey);
        assertEquals(32, tree.Root.RightChild.RightChild.NodeKey);
    }

    @Test
    void GenerateTree_test_empty(){
        int[] keys = {};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(keys);

        assertNull(tree.Root);
    }

    @Test
    void IsBalanced_test(){
        int[] keys = {3, 5, 8, 9, 11, 12, 16, 32};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(keys);

        assertTrue(tree.IsBalanced(tree.Root));
    }

    @Test
    void IsBalanced_test_empty(){
        int[] keys = {};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(keys);

        assertTrue(tree.IsBalanced(tree.Root));
    }
}