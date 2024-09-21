package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjacencyList {

    static int numNodes = 9;

    static boolean[] initializeVisitedArr() {
        boolean[] visited = new boolean[numNodes];
        visited[0] = true;
        for(int i = 1; i < numNodes; i++) {
            visited[i] = false;
        }
        return visited;
    }

    static void printGraph(ArrayList<ArrayList<Integer>> graph) {
        for(int i = 0; i < graph.size(); i++) {
            System.out.print(i + " -> [");
            ArrayList<Integer> neighbours = graph.get(i);
            for(int j = 0; j < neighbours.size(); j++) {
                if(j != neighbours.size() - 1) {
                    System.out.print(neighbours.get(j) + ", ");
                } else {
                    System.out.print(neighbours.get(j));
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }

    static void bfs(Integer node, ArrayList<Integer> neighbours, boolean[] visited) {
        System.out.println("Visiting all neighbours of " + node);
        neighbours.forEach(ele -> {
            if(!visited[ele]) {
                System.out.println("Visiting - " + ele);
                visited[ele] = true;
            } else {
                System.out.println("Already Visited - " + ele);
            }
        });
        System.out.println("Visited - " + neighbours.size() + " neighbours of " + node);
        System.out.println("Finished Visiting all neighbours of " + node);
    }

    static void dfs(Integer node, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        System.out.println("Visited - " + node);
        System.out.println("Visiting Neighbours of " + node);
        ArrayList<Integer> neighbours = graph.get(node);
        neighbours.forEach(ele -> {
            if(!visited[ele]) {
                dfs(ele, graph, visited);
            } else {
                System.out.println("Already Visited " + ele);
            }
        });
        System.out.println("Visited " + neighbours.size() + " neighbours of " + node);
        System.out.println("Completed Visiting neighbours of " + node);
    }

    public static void main(String[] args) {
        // Suppose you have a graph with 7 Nodes as follows
        /**  (1) - (2)
         *    ↓  /       (4)      (5) - (6)
         *   (3) - (8)                   ↓
         *                              (7)
         *
         */

        // "-" means double-edged else direction of edge is mentioned

        // To represent such a graph in adjacency

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(numNodes);
        graph.add(0, new ArrayList<>());
        graph.add(1, new ArrayList<>(Arrays.asList(2, 3)));
        graph.add(2, new ArrayList<>(Arrays.asList(1, 3)));
        graph.add(3, new ArrayList<>(Arrays.asList(2, 8)));
        graph.add(4, new ArrayList<>());
        graph.add(5, new ArrayList<>(List.of(6)));
        graph.add(6, new ArrayList<>(Arrays.asList(5, 7)));
        graph.add(7, new ArrayList<>(List.of()));
        graph.add(8, new ArrayList<>(List.of(3)));

        printGraph(graph);

        System.out.println("----------------------------------------------");

        System.out.println("BFS Traversal");
        boolean[] visited = initializeVisitedArr();

        for(int i = 1; i < visited.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                System.out.println("Visited - " + i);
                bfs(i, graph.get(i), visited);
            } else {
                System.out.println("Already Visited - " + i);
            }
        }

        System.out.println("----------------------------------------------");
        System.out.println("DFS Traversal");
        visited = initializeVisitedArr();

        for(int i = 1; i < visited.length; i++) {
            if(!visited[i]) {
                dfs(i, graph, visited);
            } else {
                System.out.println("Already Visited - " + i);
            }
        }

    }
}
