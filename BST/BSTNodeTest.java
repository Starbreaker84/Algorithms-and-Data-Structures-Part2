import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTNodeTest {
    @Test
    void FindNodeByKey_test(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);
        BSTNode<Integer> node4 = new BSTNode<>(4, 4, node8);
        BSTNode<Integer> node12 = new BSTNode<>(12, 12, node8);
        node8.LeftChild = node4;
        node8.RightChild = node12;
        BSTNode<Integer> node2 = new BSTNode<>(2, 2, node4);
        BSTNode<Integer> node6 = new BSTNode<>(6, 6, node4);
        node4.LeftChild = node2;
        node4.RightChild = node6;
        BSTNode<Integer> node10 = new BSTNode<>(10, 10, node12);
        BSTNode<Integer> node14 = new BSTNode<>(14, 14, node12);
        node12.LeftChild = node10;
        node12.RightChild = node14;

        BST<Integer> tree = new BST<>(node8);

        BSTFind<Integer> foundNode = tree.FindNodeByKey(2);
        assertEquals(2, foundNode.Node.NodeKey);
        assertTrue(foundNode.NodeHasKey);

        foundNode = tree.FindNodeByKey(1);
        assertEquals(2, foundNode.Node.NodeKey);
        assertFalse(foundNode.NodeHasKey);
        assertTrue(foundNode.ToLeft);

        foundNode = tree.FindNodeByKey(7);
        assertEquals(6, foundNode.Node.NodeKey);
        assertFalse(foundNode.NodeHasKey);
        assertFalse(foundNode.ToLeft);
    }

    @Test
    void AddKeyValue_test(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);

        BST<Integer> tree = new BST<>(node8);
        assertNull(node8.LeftChild);
        assertNull(node8.RightChild);

        assertTrue(tree.AddKeyValue(4, 4));
        assertEquals(4, node8.LeftChild.NodeKey);
        assertNull(node8.RightChild);

        assertTrue(tree.AddKeyValue(12, 12));
        assertEquals(4, node8.LeftChild.NodeKey);
        assertEquals(12, node8.RightChild.NodeKey);

        assertFalse(tree.AddKeyValue(12, 12));
    }

    @Test
    void AddKeyValue_empty(){
        BST<Integer> tree = new BST<>(null);
        assertEquals(0, tree.Count());

        tree.AddKeyValue(8, 8);
        assertEquals(1, tree.Count());
        assertEquals(8, tree.Root.NodeKey);
    }

    @Test
    void FinMinMax_test(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);
        BSTNode<Integer> node4 = new BSTNode<>(4, 4, node8);
        BSTNode<Integer> node12 = new BSTNode<>(12, 12, node8);
        node8.LeftChild = node4;
        node8.RightChild = node12;
        BSTNode<Integer> node2 = new BSTNode<>(2, 2, node4);
        BSTNode<Integer> node6 = new BSTNode<>(6, 6, node4);
        node4.LeftChild = node2;
        node4.RightChild = node6;
        BSTNode<Integer> node10 = new BSTNode<>(10, 10, node12);
        BSTNode<Integer> node14 = new BSTNode<>(14, 14, node12);
        node12.LeftChild = node10;
        node12.RightChild = node14;

        BST<Integer> tree = new BST<>(node8);

        assertEquals(2, tree.FinMinMax(node8, false).NodeKey);
        assertEquals(14, tree.FinMinMax(node8, true).NodeKey);
        assertEquals(2, tree.FinMinMax(node4, false).NodeKey);
        assertEquals(14, tree.FinMinMax(node12, true).NodeKey);
        assertEquals(2, tree.FinMinMax(node2, false).NodeKey);
        assertEquals(2, tree.FinMinMax(node2, true).NodeKey);
    }

    @Test
    void count_test(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);
        BSTNode<Integer> node4 = new BSTNode<>(4, 4, node8);
        BSTNode<Integer> node12 = new BSTNode<>(12, 12, node8);
        node8.LeftChild = node4;
        node8.RightChild = node12;
        BSTNode<Integer> node2 = new BSTNode<>(2, 2, node4);
        BSTNode<Integer> node6 = new BSTNode<>(6, 6, node4);
        node4.LeftChild = node2;
        node4.RightChild = node6;

        BST<Integer> tree = new BST<>(node8);

        assertEquals(5, tree.Count());
        assertTrue(tree.AddKeyValue(10, 10));
        assertTrue(tree.AddKeyValue(14, 14));
        assertEquals(7, tree.Count());
    }

    @Test
    void DeleteNodeByKey_test_only_left(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);
        BSTNode<Integer> node4 = new BSTNode<>(4, 4, node8);
        BSTNode<Integer> node12 = new BSTNode<>(12, 12, node8);
        node8.LeftChild = node4;
        node8.RightChild = node12;
        BSTNode<Integer> node2 = new BSTNode<>(2, 2, node4);
        node4.LeftChild = node2;
        BSTNode<Integer> node1 = new BSTNode<>(1, 1, node2);
        BSTNode<Integer> node3 = new BSTNode<>(3, 3, node2);
        node2.LeftChild = node1;
        node2.RightChild = node3;
        BSTNode<Integer> node10 = new BSTNode<>(10, 10, node12);
        BSTNode<Integer> node14 = new BSTNode<>(14, 14, node12);
        node12.LeftChild = node10;
        node12.RightChild = node14;

        BST<Integer> tree = new BST<>(node8);

        assertTrue(tree.FindNodeByKey(4).NodeHasKey);
        assertEquals(8, tree.Count());
        assertTrue(tree.DeleteNodeByKey(4));
        assertFalse(tree.FindNodeByKey(4).NodeHasKey);
        assertEquals(2, tree.Root.LeftChild.NodeKey);
        assertEquals(1, tree.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(3, tree.Root.LeftChild.RightChild.NodeKey);
        assertEquals(7, tree.Count());
    }

    @Test
    void DeleteNodeByKey_test_only_right(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);
        BSTNode<Integer> node4 = new BSTNode<>(4, 4, node8);
        BSTNode<Integer> node12 = new BSTNode<>(12, 12, node8);
        node8.LeftChild = node4;
        node8.RightChild = node12;
        BSTNode<Integer> node6 = new BSTNode<>(6, 6, node4);
        node4.RightChild = node6;
        BSTNode<Integer> node5 = new BSTNode<>(5, 5, node6);
        BSTNode<Integer> node7 = new BSTNode<>(7, 7, node6);
        node6.LeftChild = node5;
        node6.RightChild = node7;
        BSTNode<Integer> node10 = new BSTNode<>(10, 10, node12);
        BSTNode<Integer> node14 = new BSTNode<>(14, 14, node12);
        node12.LeftChild = node10;
        node12.RightChild = node14;

        BST<Integer> tree = new BST<>(node8);

        assertTrue(tree.FindNodeByKey(4).NodeHasKey);
        assertEquals(8, tree.Count());
        assertTrue(tree.DeleteNodeByKey(4));
        assertFalse(tree.FindNodeByKey(4).NodeHasKey);
        assertEquals(5, tree.Root.LeftChild.NodeKey);
        assertEquals(6, tree.Root.LeftChild.RightChild.NodeKey);
        assertEquals(7, tree.Root.LeftChild.RightChild.RightChild.NodeKey);
        assertNull(tree.Root.LeftChild.RightChild.LeftChild);
        assertEquals(7, tree.Count());
    }

    @Test
    void DeleteNodeByKey_test_last(){
        BSTNode<Integer> node8 = new BSTNode<>(8, 8, null);

        BST<Integer> tree = new BST<>(node8);

        assertTrue(tree.FindNodeByKey(8).NodeHasKey);
        assertEquals(1, tree.Count());
        assertTrue(tree.DeleteNodeByKey(8));
        assertFalse(tree.FindNodeByKey(8).NodeHasKey);
        assertNull(tree.Root);
        assertEquals(0, tree.Count());
    }
}