import java.util.ArrayList;
import java.util.List;

/**
 * Basic Graph implementation using Adjacency Lists.
 */
public class Graph {
    int V;
    List<List<Integer>> adjList;

    public Graph(int v) {
        this.V = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int dest) {
        adjList.get(source).add(dest);
    }
}
