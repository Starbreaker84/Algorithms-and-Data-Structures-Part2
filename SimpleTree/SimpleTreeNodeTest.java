import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeNodeTest {

    @Test
    void AddChild_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        assertEquals(node2, node1.Children.get(0));
        tree.AddChild(node2, node4);
        assertEquals(node4, node2.Children.get(0));
    }

    @Test
    void DeleteNode_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(7, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        tree.AddChild(node2, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node5, node7);
        tree.AddChild(node5, node8);

        tree.DeleteNode(node5);
        assertEquals(1, node2.Children.size());
        assertEquals(node4, node2.Children.get(0));
        tree.DeleteNode(node1);
        assertEquals(node1, tree.Root);
    }

    @Test
    void GetAllNodes_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(20, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        tree.AddChild(node2, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node5, node7);
        tree.AddChild(node5, node8);
        tree.AddChild(node1, node3);
        tree.AddChild(node3, node6);
        tree.AddChild(node6, node9);

        List<SimpleTreeNode<Integer>> comparableList = new ArrayList<>();
        comparableList.add(node1);
        comparableList.add(node2);
        comparableList.add(node4);
        comparableList.add(node5);
        comparableList.add(node7);
        comparableList.add(node8);
        comparableList.add(node3);
        comparableList.add(node6);
        comparableList.add(node9);

        assertEquals(comparableList, tree.GetAllNodes());
    }

    @Test
    void FindNodesByValue_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(20, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        tree.AddChild(node2, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node5, node7);
        tree.AddChild(node5, node8);
        tree.AddChild(node1, node3);
        tree.AddChild(node3, node6);
        tree.AddChild(node6, node9);

        List<SimpleTreeNode<Integer>> comparableList = new ArrayList<>();
        comparableList.add(node5);
        comparableList.add(node8);
        comparableList.add(node6);

        assertEquals(comparableList, tree.FindNodesByValue(6));
    }

    @Test
    void MoveNode_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(20, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        tree.AddChild(node2, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node5, node7);
        tree.AddChild(node5, node8);
        tree.AddChild(node1, node3);
        tree.AddChild(node3, node6);
        tree.AddChild(node6, node9);

        tree.MoveNode(node5, node6);

        assertEquals(1, node2.Children.size());
        assertEquals(node4, node2.Children.get(0));
        assertEquals(2, node6.Children.size());
        assertEquals(node9, node6.Children.get(0));
        assertEquals(node5, node6.Children.get(1));
        assertEquals(2, node5.Children.size());
        assertEquals(node7, node5.Children.get(0));
        assertEquals(node8, node5.Children.get(1));
    }

    @Test
    void Count_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(20, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        tree.AddChild(node2, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node5, node7);
        tree.AddChild(node5, node8);
        tree.AddChild(node1, node3);
        tree.AddChild(node3, node6);
        tree.AddChild(node6, node9);

        assertEquals(9, tree.Count());

        tree.DeleteNode(node5);
        assertEquals(6, tree.Count());
    }

    @Test
    void LeafCount_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(20, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);

        tree.AddChild(node1, node2);
        tree.AddChild(node2, node4);
        tree.AddChild(node2, node5);
        tree.AddChild(node5, node7);
        tree.AddChild(node5, node8);
        tree.AddChild(node1, node3);
        tree.AddChild(node3, node6);
        tree.AddChild(node6, node9);

        assertEquals(4, tree.LeafCount());

        tree.DeleteNode(node5);
        assertEquals(2, tree.LeafCount());

        tree.DeleteNode(node2);
        assertEquals(1, tree.LeafCount());
    }

    @Test
    void EvenTrees_test(){
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);

        SimpleTree<Integer> tree = new SimpleTree<>(node1);
        tree.AddChild(node1, node2);
        tree.AddChild(node1, node3);
        tree.AddChild(node1, node6);
        tree.AddChild(node2, node5);
        tree.AddChild(node2, node7);
        tree.AddChild(node3, node4);
        tree.AddChild(node6, node8);
        tree.AddChild(node8, node9);
        tree.AddChild(node8, node10);

        ArrayList<Integer> comparableList = new ArrayList<>();
        comparableList.add(1);
        comparableList.add(3);
        comparableList.add(1);
        comparableList.add(6);

        assertEquals(comparableList, tree.EvenTrees());
    }
}