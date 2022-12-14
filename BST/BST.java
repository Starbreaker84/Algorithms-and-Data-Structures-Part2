import java.util.ArrayList;

class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T>
{
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    private BSTFind<T> findNode(BSTNode<T> node, int key){
        if (key == node.NodeKey) {
            BSTFind<T> foundNode = new BSTFind<>();
            foundNode.Node = node;
            foundNode.NodeHasKey = true;
            return foundNode;
        }
        if (key < node.NodeKey && node.LeftChild == null) {
            BSTFind<T> foundNode = new BSTFind<>();
            foundNode.Node = node;
            foundNode.NodeHasKey = false;
            foundNode.ToLeft = true;
            return foundNode;
        }
        if (key > node.NodeKey && node.RightChild == null) {
            BSTFind<T> foundNode = new BSTFind<>();
            foundNode.Node = node;
            foundNode.NodeHasKey = false;
            foundNode.ToLeft = false;
            return foundNode;
        }
        if (key < node.NodeKey) {
            return findNode(node.LeftChild, key);
        }
        return findNode(node.RightChild, key);
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        if (Root == null) return new BSTFind();
        return findNode(Root, key);
    }

    public boolean AddKeyValue(int key, T val) {
        BSTFind<T> foundNode = FindNodeByKey(key);
        if (foundNode.NodeHasKey) {
            return false;
        }

        BSTNode<T> newNode = new BSTNode<>(key, val, foundNode.Node);
        if (foundNode.Node == null) {
            Root = newNode;
        } else if (foundNode.ToLeft) {
            foundNode.Node.LeftChild = newNode;
        } else {
            foundNode.Node.RightChild = newNode;
        }
        return true;
    }

    private BSTNode<T> findMax(BSTNode<T> node){
        if (node.RightChild == null) return node;
        return findMax(node.RightChild);
    }

    private BSTNode<T> findMin(BSTNode<T> node){
        if (node.LeftChild == null) return node;
        return findMin(node.LeftChild);
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if (FindMax) {
            return findMax(FromNode);
        }
        return findMin(FromNode);
    }

    private void setParentChild(BSTFind<T> foundNode, int key){
        if (key < foundNode.Node.Parent.NodeKey) {
            foundNode.Node.Parent.LeftChild = foundNode.Node.LeftChild;
            foundNode.Node.LeftChild.Parent = foundNode.Node.Parent;
        } else {
            foundNode.Node.Parent.RightChild = foundNode.Node.LeftChild;
            foundNode.Node.LeftChild.Parent = foundNode.Node.Parent;
        }
    }

    private void nodeToNull(BSTFind<T> foundNode, int key){
        if (foundNode.Node == Root) {
            Root = null;
        } else if (key < foundNode.Node.Parent.NodeKey) {
            foundNode.Node.Parent.LeftChild = null;
        } else {
            foundNode.Node.Parent.RightChild = null;
        }
    }

    public boolean DeleteNodeByKey(int key)
    {
        // удаляем узел по ключу
        BSTFind<T> foundNode = FindNodeByKey(key);
        if (!foundNode.NodeHasKey){
            return false;
        }
        //case 1: есть правый потомок
        if (foundNode.Node.RightChild != null){
            BSTNode<T> minNode = FinMinMax(foundNode.Node.RightChild, false);
            int temp = minNode.NodeKey;
            DeleteNodeByKey(temp);
            foundNode.Node.NodeKey = temp;
        }
        //case 2: есть левый потомок
        if (foundNode.Node.LeftChild != null && foundNode.Node.RightChild == null){
            setParentChild(foundNode, key);
        }
        //case 3: нет потомков
        if (foundNode.Node.LeftChild == null && foundNode.Node.RightChild == null){
            nodeToNull(foundNode, key);
        }
        return true;
    }

    private int getSize(BSTNode<T> node){
        if (node == null) {
            return 0;
        }
        return getSize(node.LeftChild) + getSize(node.RightChild) + 1;
    }

    public int Count()
    {
        return getSize(Root); // количество узлов в дереве
    }

    public ArrayList<BSTNode> WideAllNodes(){
        ArrayList<BSTNode> wideListOfNodes = new ArrayList<>();
        if (Root != null) {
            wideListOfNodes.add(Root);
        }
        for (int i = 0; i < wideListOfNodes.size(); i++){
            if (wideListOfNodes.get(i).LeftChild != null) {
                wideListOfNodes.add(wideListOfNodes.get(i).LeftChild);
            }
            if (wideListOfNodes.get(i).RightChild != null) {
                wideListOfNodes.add(wideListOfNodes.get(i).RightChild);
            }
        }
        return wideListOfNodes;
    }

    private ArrayList<BSTNode> in_orderNodes(BSTNode root){
        ArrayList<BSTNode> nodes = new ArrayList<>();
        if (root == null) return nodes;
        nodes.addAll(in_orderNodes(root.LeftChild));
        nodes.add(root);
        nodes.addAll(in_orderNodes(root.RightChild));
        return nodes;
    }
    private ArrayList<BSTNode> post_orderNodes(BSTNode root){
        ArrayList<BSTNode> nodes = new ArrayList<>();
        if (root == null) return nodes;
        nodes.addAll(post_orderNodes(root.LeftChild));
        nodes.addAll(post_orderNodes(root.RightChild));
        nodes.add(root);
        return nodes;
    }
    private ArrayList<BSTNode> pre_orderNodes(BSTNode root){
        ArrayList<BSTNode> nodes = new ArrayList<>();
        if (root == null) return nodes;
        nodes.add(root);
        nodes.addAll(pre_orderNodes(root.LeftChild));
        nodes.addAll(pre_orderNodes(root.RightChild));
        return nodes;
    }


    public ArrayList<BSTNode> DeepAllNodes(int order){
        if (order == 0) return in_orderNodes(Root);
        if (order == 1) return post_orderNodes(Root);
        return pre_orderNodes(Root);
    }
}