import java.util.*;

public class SimpleTreeNode<T>
{
    public int NodeLevel;
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
        Root.NodeLevel = 0;
    }
    private void AddLevels(SimpleTreeNode<T> root){
        if (root.Children == null || root.Children.isEmpty()) return;
        for (SimpleTreeNode<T> node : root.Children){
            node.NodeLevel = root.NodeLevel + 1;
            AddLevels(node);
        }
    }

    public void AddNodesLevels(){
        if (Root != null) {
            Root.NodeLevel = 0;
            AddLevels(Root);
        }
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
            }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
        NewChild.NodeLevel = NewChild.Parent.NodeLevel + 1;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        if (NodeToDelete.Parent != null) {
            NodeToDelete.Parent.Children.remove(NodeToDelete);
        }
    }

    private List<SimpleTreeNode<T>> getNodes(SimpleTreeNode<T> rootNode) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        nodes.add(rootNode);
        if (rootNode.Children == null || rootNode.Children.isEmpty()) {
            return nodes;
        }
        for (SimpleTreeNode<T> node : rootNode.Children){
            nodes.addAll(getNodes(node));
        }
        return nodes;
    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        if (Root == null) return Collections.emptyList();
        return getNodes(Root);
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        for (SimpleTreeNode<T> node : GetAllNodes()){
            if (node.NodeValue == val) nodes.add(node);
        }
        return nodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        if (OriginalNode.Parent != null) {
            DeleteNode(OriginalNode);
            AddChild(NewParent, OriginalNode);
        }
    }

    public int Count()
    {
        return GetAllNodes().size();
    }

    private int getLeafs(SimpleTreeNode<T> root){
        if (root.Children == null || root.Children.isEmpty()) return 1;
        int leafs = 0;
        for (SimpleTreeNode<T> node : root.Children){
            leafs += getLeafs(node);
        }
        return leafs;
    }

    public int LeafCount(){
        if (Root == null) return 0;
        return getLeafs(Root);
    }

    public ArrayList<T> EvenTrees() {
        ArrayList<T> deletedEdges = new ArrayList<>();
        LinkedList<SimpleTreeNode<T>> list = new LinkedList<>();
        SimpleTreeNode<T> node = Root;
        list.add(node);
        while (!list.isEmpty()) {
            node = list.pollFirst();
            SimpleTree<T> tree = new SimpleTree<>(node);
            if ((tree.Count() & 1) == 0) {
                list.addAll(node.Children);
            }
            if ((tree.Count() & 1) == 0 && node.Parent != null) {
                deletedEdges.add(node.Parent.NodeValue);
                deletedEdges.add(node.NodeValue);
            }
        }
        return deletedEdges;
    }
}
