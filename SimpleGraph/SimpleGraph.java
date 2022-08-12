import java.util.*;

class Vertex {
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph {
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex
        for (int i = 0; i < vertex.length; i++){
            if (vertex[i] == null){
                vertex[i] = new Vertex(value);
                break;
            }
        }
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        vertex[v] = null;
        Arrays.fill(m_adjacency[v], 0);
        for (int i = 0; i < max_vertex; i++){
            m_adjacency[i][v] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) {
        return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] == 1;
    }

    public void AddEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.

        //Подготовка к работе алгоритма
        Stack<Vertex> path = new Stack<>();
        for (Vertex vert : vertex){
            if (vert != null) vert.Hit = false;
        }
        path = dfs(VFrom, VTo, path);
        return new ArrayList<>(path);
    }

    private Stack <Vertex> dfs(int vFrom, int vTo, Stack <Vertex> path){
        vertex[vFrom].Hit = true;
        path.push(vertex[vFrom]);
        if (IsEdge(vFrom, vTo)) {
            path.push(vertex[vTo]);
            return path;
        }
        int newFrom = getNewFromIndex(vFrom);
        if (newFrom != -1) return dfs(newFrom, vTo, path);
        path.pop();
        if (path.empty()) return path;
        return dfs(getVertexIndex(path.pop()), vTo, path);
    }

    private int getNewFromIndex(int vFrom) {
        for (int i = 0; i < vertex.length; i++){
            if (vertex[i] != null && IsEdge(vFrom, i) && vertex[i].Hit == false) {
                return i;
            }
        }
        return -1;
    }

    private int getVertexIndex(Vertex v){
        for (int i = 0; i < vertex.length; i++){
            if (vertex[i] != null && vertex[i].Value == v.Value) return i;
        }
        return -1;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        Queue<Vertex> vertexList = new LinkedList<>();
        int[] parents = new int[vertex.length];
        ArrayList<Vertex> path = new ArrayList<>();
        for (Vertex vert : vertex){
            if (vert != null) vert.Hit = false;
        }
        vertex[VFrom].Hit = true;
        parents[VFrom] = VFrom;
        path = bfs(VFrom, VFrom, VTo, vertexList, parents, path);
        return path;
    }

    private ArrayList<Vertex> bfs(int start, int vFrom, int vTo, Queue<Vertex> vertexList, int[] parents, ArrayList<Vertex> path){
        int nearVertexIndex = getNewFromIndex(vFrom); // ближайшая непосещённая вершина
        if (nearVertexIndex == vTo) {
            parents[vTo] = vFrom;
            return getPath(start, vTo, parents, path);
        }
        if (nearVertexIndex >= 0) { // если такая вершина есть, но не равно искомой
            vertex[nearVertexIndex].Hit = true;
            parents[nearVertexIndex] = vFrom;
            vertexList.add(vertex[nearVertexIndex]);
            return bfs(start, vFrom, vTo, vertexList, parents, path);
        }
        if (vertexList.isEmpty()) {
            return new ArrayList<>(); // путь не найден
        }
        Vertex newFromVertex = vertexList.remove(); //если нет непосещённых вершин
        int newFromIndex = getVertexIndex(newFromVertex);
        return bfs(start, newFromIndex, vTo, vertexList, parents, path);
    }

    private ArrayList<Vertex> getPath(int start, int vTo, int[] parents, ArrayList<Vertex> path){
        Vertex v = vertex[vTo];
        while (v != vertex[start]){
            path.add(v);
            v = vertex[parents[vTo]];
            vTo = parents[vTo];
        }
        path.add(vertex[start]);
        Collections.reverse(path);
        return path;
    }

}