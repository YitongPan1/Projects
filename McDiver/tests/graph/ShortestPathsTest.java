package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cms.util.maybe.NoMaybeValue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ShortestPathsTest {
    /** The graph example from lecture */
    static final String[] vertices1 = { "a", "b", "c", "d", "e", "f", "g" };
    static final int[][] edges1 = {
        {0, 1, 9}, {0, 2, 14}, {0, 3, 15},
        {1, 4, 23},
        {2, 4, 17}, {2, 3, 5}, {2, 5, 30},
        {3, 5, 20}, {3, 6, 37},
        {4, 5, 3}, {4, 6, 20},
        {5, 6, 16}
    };
    static class TestGraph implements WeightedDigraph<String, int[]> {
        int[][] edges;
        String[] vertices;
        Map<String, Set<int[]>> outgoing;

        TestGraph(String[] vertices, int[][] edges) {
            this.vertices = vertices;
            this.edges = edges;
            this.outgoing = new HashMap<>();
            for (String v : vertices) {
                outgoing.put(v, new HashSet<>());
            }
            for (int[] edge : edges) {
                outgoing.get(vertices[edge[0]]).add(edge);
            }
        }
        public Iterable<int[]> outgoingEdges(String vertex) { return outgoing.get(vertex); }
        public String source(int[] edge) { return vertices[edge[0]]; }
        public String dest(int[] edge) { return vertices[edge[1]]; }
        public double weight(int[] edge) { return edge[2]; }
    }
    static TestGraph testGraph1() {
        return new TestGraph(vertices1, edges1);
    }

    @Test
    void lectureNotesTest() {
        TestGraph graph = testGraph1();
        ShortestPaths<String, int[]> ssp = new ShortestPaths<>(graph);
        ssp.singleSourceDistances("a");
        assertEquals(50, ssp.getDistance("g"));
        StringBuilder sb = new StringBuilder();
        sb.append("best path:");
        for (int[] e : ssp.bestPath("g")) {
            sb.append(" " + vertices1[e[0]]);
        }
        sb.append(" g");
        assertEquals("best path: a c e f g", sb.toString());
    }
    @Test
    void lectureNotesTest1() {
        TestGraph graph1 = testGraph1();
        ShortestPaths<String, int[]> ssp1 = new ShortestPaths<>(graph1);
        ssp1.singleSourceDistances("b");
        assertEquals(26, ssp1.getDistance("f"));
        StringBuilder sb1 = new StringBuilder();
        sb1.append("best path:");
        for (int[] e : ssp1.bestPath("f")) {
            sb1.append(" " + vertices1[e[0]]);
        }
        sb1.append(" f");
        assertEquals("best path: b e f", sb1.toString());
    }
    @Test
    void lectureNotesTest2() {
        TestGraph graph2 = testGraph1();
        ShortestPaths<String, int[]> ssp2 = new ShortestPaths<>(graph2);
        ssp2.singleSourceDistances("d");
        assertEquals(0, ssp2.getDistance("d"));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("best path:");
        for (int[] e : ssp2.bestPath("d")) {
            sb2.append(" " + vertices1[e[0]]);
        }
        sb2.append(" d");
        assertEquals("best path: d", sb2.toString());
    }
    // TODO: Add 2 more tests
}
