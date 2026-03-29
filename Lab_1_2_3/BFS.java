package Lab_1_2_3;

import java.util.*;

public class BFS {
    Deque<String> queue = new ArrayDeque<>();
    Map<String, List<String>> graph = new HashMap<>();

    public void addEdge(String city1, String city2) {
        graph.computeIfAbsent(city1, k -> new ArrayList<>()).add(city2);
        graph.computeIfAbsent(city2, k -> new ArrayList<>()).add(city1);
    }

    public String findPath(String city_Start, String city_dest) {
        if (!graph.containsKey(city_Start) || !graph.containsKey(city_dest)) {
            return "One or both cities not found in graph.";
        }

        Map<String, String> visited = new HashMap<>();
        queue.addLast(city_Start);       
        visited.put(city_Start, null);   

        while (!queue.isEmpty()) {
            String current = queue.pollFirst();   
            if (current.equals(city_dest)) {
                return reconstructPath(visited, city_Start, city_dest);
            }

            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, current);
                    queue.addLast(neighbor);     
                }
            }
        }

        return "No path found between " + city_Start + " and " + city_dest;
    }

    private String reconstructPath(Map<String, String> visited, String start, String dest) {
        LinkedList<String> path = new LinkedList<>();
        String step = dest;

        while (step != null) {
            path.addFirst(step);
            step = visited.get(step);
        }

        return String.join(" -> ", path);
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();

        bfs.addEdge("Delhi", "Agra");
        bfs.addEdge("Agra", "Jaipur");
        bfs.addEdge("Delhi", "Lucknow");
        bfs.addEdge("Lucknow", "Varanasi");

        System.out.println(bfs.findPath("Delhi", "Varanasi"));  
        System.out.println(bfs.findPath("Agra", "Varanasi"));   
    }
}