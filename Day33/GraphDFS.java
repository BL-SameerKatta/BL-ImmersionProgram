import java.util.ArrayList;
import java.util.List;

/**
 * Performs Depth-First Search (DFS) to find all possible paths between two nodes.
 */
public class GraphDFS {
    public static void printAllPaths(Graph graph, int source, int dest) {
        boolean[] visited = new boolean[graph.V];
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(source);
        System.out.println("All paths from " + source + " to " + dest + ":");
        dfsHelper(graph, source, dest, visited, currentPath);
    }

    private static void dfsHelper(Graph graph, int current, int dest, boolean[] visited, List<Integer> currentPath) {
        if (current == dest) {
            System.out.println(currentPath);
            return;
        }

        visited[current] = true;

        for (int neighbor : graph.adjList.get(current)) {
            if (!visited[neighbor]) {
                currentPath.add(neighbor);
                dfsHelper(graph, neighbor, dest, visited, currentPath);
                currentPath.remove(currentPath.size() - 1); 
            }
        }

        visited[current] = false; 
    }
}
