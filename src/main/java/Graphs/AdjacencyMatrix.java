package Graphs;

// Writing a graph using Adjacency Matrix
public class AdjacencyMatrix {

    static boolean[] initializeVisitedArr(int numNodes) {
        boolean[] visited = new boolean[numNodes];
        // Note this is 1 based indexing that has been followed hence 0 will be true
        visited[0] = true;
        // Denoting that we don't have any node that is visited currently
        for(int i = 1; i < visited.length; i++) {
            visited[i] = false;
        }
        return visited;
    }

    static int[][] initializeArr(int[][] adj) {
        int numNodes = adj.length;
        // Initialize the matrix with 0s
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                adj[i][j] = 0;
            }
        }
        return adj;
    }

    static void printGraph(int[][] adj) {
        int numNodes = adj.length;
        System.out.print("i/j| ");
        for(int i = 0; i < numNodes; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("_____");
        for(int i = 0; i < numNodes; i++) {
            System.out.print("__");
        }
        System.out.println();
        // Print all set edges
        for(int i = 0; i < numNodes; i++) {
            System.out.print(i + "||  ");
            for(int j = 0; j < numNodes; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int node, int[][] adj, boolean[] visited) {
        int count = 0;
        for(int pn = 0; pn < adj[node].length; pn++) {
            if(adj[node][pn] == 1 && !visited[pn]) {
                visited[pn] = true;
                count++;
                System.out.println("Visited - " + pn);
            }
        }
        if(count == 0) {
            System.out.println("No Neighbours found for node " + node);
        } else {
            System.out.println("Visited " + count + " neighbours of " + node);
        }
        System.out.println("Finished Visiting neighbours of " + node);
    }

    static void dfs(int node, int[][] adj, boolean[] visited) {
        int count = 0;
        visited[node] = true;
        System.out.println("Visited - "  + node);
        System.out.println("Visiting neighbours of - " + node);
        for(int pn = 0; pn < adj[node].length; pn++) {
            if(adj[node][pn] == 1) {
                count++;
            }
            if(adj[node][pn] == 1 && !visited[pn]) {
                dfs(pn, adj, visited);
            }
        }

        if(count == 0) {
            System.out.println("Found No Neighbours for " + node);
        } else  {
            System.out.println("Visited " + count + " neighbours for node " + node);
        }
    }

    public static void main(String [] args) {
        // Suppose you have a graph with 7 Nodes as follows
        /**  (1) - (2)
         *    ↓  /       (4)      (5) - (6)
         *   (3) - (8)                   ↓
         *                              (7)
         *
         */

        // "-" means double-edged else direction of edge is mentioned

        // To represent such a graph in adjacency
        int numNodes = 9;
        int [][] adj = new int[numNodes][numNodes];

        initializeArr(adj);

        // Print all non set edges
        printGraph(adj);

        // adj[i][j] represents an edge between node i to node j
        // similarly adj[j][i] represents an edge between node j and node i
        // if i==j it represents a self node (usually set to 0 for all cases)

        // Setting edges
        adj[1][2] = 1;
        adj[2][1] = 1;
        adj[1][3] = 1;
        adj[2][3] = 1;
        adj[3][2] = 1;
        adj[3][8] = 1;
        adj[5][6] = 1;
        adj[6][5] = 1;
        adj[6][7] = 1;
        adj[8][3] = 1;
        // Note no entry for node 4 as it doesn't have any edge with any other node.

        System.out.println("--------------------------------------------");

        printGraph(adj);

        System.out.println("--------------------------------------------");

        // How to traverse a graph?
        // For that we need a visited array.
        // The info we need is the number of nodes that are present in the graph
        // if we have 1 to n (all nodes in the graph) else an array of nodes to know what
        // nodes are present in the graph

        boolean[] visited = initializeVisitedArr(numNodes);


        // Traversal of the graph can be done with 2 ways -
        // (i) BFS or
        // (ii) DFS

        // Breadth First Search Traversal
        /* Logic -
         * Start from Visited Array. for each non visited node, visited the node and
         * also visit the unvisited neighbours of that node using adjacency matrix.
         * Once done visiting all neighbours of the node, visit the next node from visited
         * Nodes array if not visited.
         */
        System.out.println("BFS Traversal - ");
        for(int i = 1; i < visited.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                System.out.println("Visited " + i);
                System.out.println("Visiting Neighbours of " + i);
                bfs(i, adj, visited);
            } else {
                System.out.println("Already Visited - " + i);
            }
        }

        visited = initializeVisitedArr(numNodes);

        // Depth First Search (DFS) Traversal
        /*
            Logic -
            Start from Visited Array. For each non visited node, visit the node and for each
            unvisited neighbour visit neighbours recursively until no child neighbour is left,
            then visit the next neighbour of the visited node. After that if there are more unvisited
            nodes, then visit those.
         */
        System.out.println("DFS Traversal - ");
        for(int i = 1; i < visited.length; i++) {
            if(!visited[i]) {
                dfs(i, adj, visited);
            } else {
                System.out.println("Already Visited - " + i);
            }
        }
    }
}
