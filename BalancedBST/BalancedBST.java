import java.util.*;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок	
    public int     Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    private BSTNode generateTree(BSTNode parent, int[] a){
        if (a.length == 0) return null;
        BSTNode node = new BSTNode(a[a.length / 2], parent);
        node.Level = parent != null ? parent.Level + 1 : 0;
        node.LeftChild = generateTree(node, Arrays.copyOfRange(a, 0, a.length / 2));
        node.RightChild = generateTree(node, Arrays.copyOfRange(a, a.length / 2 + 1, a.length));
        return node;
    }

    public void GenerateTree(int[] a) {
        // создаём дерево с нуля из неотсортированного массива a
        Arrays.sort(a);
        Root = generateTree(null, a);
    }

    public int getMaxLevel(BSTNode node){
        if (node == null) return 0;
        return Math.max(1 + getMaxLevel(node.LeftChild), 1 + getMaxLevel(node.RightChild));
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) return true;
        return Math.abs(getMaxLevel(root_node.LeftChild) - getMaxLevel(root_node.RightChild)) <= 1;
    }
}  