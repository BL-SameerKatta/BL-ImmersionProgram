import java.util.LinkedList;
import java.util.Queue;

/**
 * Performs Breadth-First Search (BFS) to find the shortest path in an unweighted graph.
 */
public class GraphBFS {
    public static int findShortestPath(Graph graph, int start, int target) {
        if (start == target) return 0;

        boolean[] visited = new boolean[graph.V];
        int[] distance = new int[graph.V]; 
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        distance[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    queue.add(neighbor);

                    if (neighbor == target) {
                        return distance[neighbor]; 
                    }
                }
            }
        }
        return -1; 
    }
}
