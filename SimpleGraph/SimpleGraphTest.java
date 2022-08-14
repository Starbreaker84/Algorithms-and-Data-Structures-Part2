import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {
    @Test
    void addVertex_test(){
        SimpleGraph graph = new SimpleGraph(6);
        assertNull(graph.vertex[0]);
        assertNull(graph.vertex[1]);
        assertNull(graph.vertex[2]);
        assertNull(graph.vertex[3]);
        assertNull(graph.vertex[4]);
        assertNull(graph.vertex[5]);

        graph.AddVertex(5);
        assertEquals(5, graph.vertex[0].Value);
        graph.AddVertex(3);
        assertEquals(3, graph.vertex[1].Value);
    }

    @Test
    void addEdge_test(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(5);
        graph.AddVertex(3);

        assertFalse(graph.IsEdge(0, 1));
        graph.AddEdge(0, 1);
        assertTrue(graph.IsEdge(0, 1));
    }

    @Test
    void removeEdge_test(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(5);
        graph.AddVertex(3);
        graph.AddEdge(0, 1);
        assertTrue(graph.IsEdge(0, 1));

        graph.RemoveEdge(1, 0);
        assertFalse(graph.IsEdge(1, 0));
    }

    @Test
    void removeVertex_test(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(5);
        graph.AddVertex(3);
        graph.AddVertex(8);
        graph.AddEdge(0, 1);
        graph.AddEdge(2, 1);
        graph.AddEdge(2, 0);
        assertTrue(graph.IsEdge(0, 2));
        assertTrue(graph.IsEdge(1, 2));

        graph.RemoveVertex(2);
        assertFalse(graph.IsEdge(0, 2));
        assertFalse(graph.IsEdge(1, 2));
        assertNull(graph.vertex[2]);
    }

    @Test
    void dfs_test_simple_way(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(2, 4);
        graph.AddEdge(3, 4);

        List<Integer> comparedList = new ArrayList<>();
        comparedList.add(2);
        comparedList.add(3);

        List<Integer> result = new ArrayList<>();
        for (Vertex v : graph.DepthFirstSearch(2, 3)){
            result.add(v.Value);
        }

        assertEquals(comparedList, result);
    }

    @Test
    void dfs_test_middle_way(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);

        List<Integer> comparedList = new ArrayList<>();
        comparedList.add(0);
        comparedList.add(1);
        comparedList.add(4);

        List<Integer> result = new ArrayList<>();
        for (Vertex v : graph.DepthFirstSearch(0, 4)){
            result.add(v.Value);
        }

        assertEquals(comparedList, result);
    }

    @Test
    void dfs_test_no_way(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);

        assertTrue(graph.DepthFirstSearch(0, 5).isEmpty());
    }

    @Test
    void breadthFirstSearch_test_long(){
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);

        List<Integer> comparedList = new ArrayList<>();
        comparedList.add(2);
        comparedList.add(3);
        comparedList.add(4);

        List<Integer> result = new ArrayList<>();
        for (Vertex v : graph.BreadthFirstSearch(2, 4)){
            result.add(v.Value);
        }

        assertEquals(comparedList, result);
    }

    @Test
    void breadthFirstSearch_test_short(){
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);

        List<Integer> comparedList = new ArrayList<>();
        comparedList.add(1);
        comparedList.add(4);

        List<Integer> result = new ArrayList<>();
        for (Vertex v : graph.BreadthFirstSearch(1, 4)){
            result.add(v.Value);
        }

        assertEquals(comparedList, result);
    }

    @Test
    void breadthFirstSearch_test_empty(){
        SimpleGraph graph = new SimpleGraph(6);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);

        assertTrue(graph.DepthFirstSearch(0, 5).isEmpty());
    }

    @Test
    void WeakVertices_test(){
        SimpleGraph graph = new SimpleGraph(9);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 4);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(2, 5);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 6);
        graph.AddEdge(5, 7);
        graph.AddEdge(6, 7);
        graph.AddEdge(7, 8);

        List<Integer> comparedList = new ArrayList<>();
        comparedList.add(4);
        comparedList.add(8);

        List<Integer> result = new ArrayList<>();
        for (Vertex v : graph.WeakVertices()){
            result.add(v.Value);
        }

        assertEquals(comparedList, result);
    }

    @Test
    void WeakVertices_test_no_weak(){
        SimpleGraph graph = new SimpleGraph(9);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(4, 6);
        graph.AddEdge(5, 6);

        assertTrue(graph.WeakVertices().isEmpty());
    }








}