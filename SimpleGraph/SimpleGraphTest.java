import org.junit.jupiter.api.Test;

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




}