package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🎨 BIPARTITE GRAPH CHECK PATTERN - COMPREHENSIVE IMPLEMENTATION
 * 
 * ============================================================================
 * 📚 PATTERN OVERVIEW
 * ============================================================================
 * 
 * Bipartite Graph Check Pattern determines if a graph can be colored with two colors
 * such that no adjacent vertices have the same color. This is equivalent to checking
 * if the graph contains no odd-length cycles. The pattern is crucial for matching
 * problems, scheduling conflicts, and relationship analysis.
 * 
 * 🔑 CORE PRINCIPLE: A graph is bipartite iff it contains no odd cycles
 * 
 * Strategy: Two-coloring using DFS/BFS
 * Time: O(V + E), Space: O(V)
 * 
 * ============================================================================
 * 🎯 WHEN TO USE: Conflict detection, matching problems, two-group classification
 * ============================================================================
 */

public class BipartiteGraphCheck {
    
    /**
     * Is Graph Bipartite - Classic Bipartite Check
     * 
     * There is an undirected graph with n nodes, where each node is numbered 
     * between 0 and n - 1. You are given a 2D array graph, where graph[i] is 
     * an array of the nodes that are adjacent to node i.
     * Return true if and only if it is bipartite.
     * 
     * Strategy: DFS with two-coloring
     * Time: O(V + E), Space: O(V)
     * 
     * LeetCode: https://leetcode.com/problems/is-graph-bipartite/
     */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // 0: uncolored, 1: color1, -1: color2
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfsBipartite(graph, i, 1, colors)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean dfsBipartite(int[][] graph, int node, int color, int[] colors) {
        colors[node] = color;
        
        for (int neighbor : graph[node]) {
            if (colors[neighbor] == color) {
                return false; // Same color as neighbor - odd cycle found
            }
            
            if (colors[neighbor] == 0 && !dfsBipartite(graph, neighbor, -color, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Alternative BFS Implementation for Bipartite Check
     * Strategy: Level-by-level coloring with queue
     */
    public static boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!bfsBipartite(graph, i, colors)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean bfsBipartite(int[][] graph, int start, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            for (int neighbor : graph[node]) {
                if (colors[neighbor] == colors[node]) {
                    return false; // Same color conflict
                }
                
                if (colors[neighbor] == 0) {
                    colors[neighbor] = -colors[node];
                    queue.offer(neighbor);
                }
            }
        }
        
        return true;
    }
    
    /**
     * Possible Bipartition - People Grouping Problem
     * 
     * We want to split a group of n people (labeled from 1 to n) into two groups 
     * of any size. Each person may dislike some other people, and they should not 
     * go into the same group. Return true if it is possible to split everyone.
     * 
     * Strategy: Build graph from dislikes and check bipartiteness
     * Time: O(V + E), Space: O(V + E)
     * 
     * LeetCode: https://leetcode.com/problems/possible-bipartition/
     */
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        // Build adjacency list from dislikes
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] dislike : dislikes) {
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }
        
        int[] colors = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0) {
                if (!dfsGrouping(graph, i, 1, colors)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean dfsGrouping(List<List<Integer>> graph, int person, 
                                     int color, int[] colors) {
        colors[person] = color;
        
        for (int disliked : graph.get(person)) {
            if (colors[disliked] == color) {
                return false; // Same group - impossible
            }
            
            if (colors[disliked] == 0 && !dfsGrouping(graph, disliked, -color, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * BONUS: Find Bipartition Groups
     * Return the actual two groups if bipartition is possible
     * 
     * Strategy: Two-coloring with group extraction
     * Time: O(V + E), Space: O(V)
     */
    public static List<List<Integer>> getBipartiteGroups(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfsBipartite(graph, i, 1, colors)) {
                    return new ArrayList<>(); // Not bipartite
                }
            }
        }
        
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 1) {
                group1.add(i);
            } else if (colors[i] == -1) {
                group2.add(i);
            }
        }
        
        return Arrays.asList(group1, group2);
    }
    
    /**
     * BONUS: Maximum Bipartite Matching
     * Find maximum matching in bipartite graph using alternating paths
     * 
     * Strategy: Augmenting path algorithm
     * Time: O(V * E), Space: O(V)
     */
    public static int maxBipartiteMatching(List<List<Integer>> graph, int leftSize) {
        int[] match = new int[graph.size()];
        Arrays.fill(match, -1);
        
        int matching = 0;
        for (int u = 0; u < leftSize; u++) {
            boolean[] visited = new boolean[graph.size()];
            if (dfsMatching(graph, u, visited, match)) {
                matching++;
            }
        }
        
        return matching;
    }
    
    private static boolean dfsMatching(List<List<Integer>> graph, int u, 
                                     boolean[] visited, int[] match) {
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                visited[v] = true;
                
                if (match[v] == -1 || dfsMatching(graph, match[v], visited, match)) {
                    match[v] = u;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * BONUS: Check if Graph has Odd Cycle
     * A graph is bipartite iff it has no odd cycles
     * 
     * Strategy: DFS with distance tracking
     * Time: O(V + E), Space: O(V)
     */
    public static boolean hasOddCycle(List<List<Integer>> graph) {
        int n = graph.size();
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        
        for (int i = 0; i < n; i++) {
            if (distances[i] == -1) {
                if (dfsOddCycle(graph, i, 0, distances)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean dfsOddCycle(List<List<Integer>> graph, int node, 
                                     int dist, int[] distances) {
        distances[node] = dist;
        
        for (int neighbor : graph.get(node)) {
            if (distances[neighbor] == -1) {
                if (dfsOddCycle(graph, neighbor, dist + 1, distances)) {
                    return true;
                }
            } else if ((distances[neighbor] - dist) % 2 == 0) {
                return true; // Odd cycle found
            }
        }
        
        return false;
    }
    
    /**
     * BONUS: Union-Find Bipartite Check
     * Use Union-Find with enemy tracking
     * 
     * Strategy: For each node, track both itself and its "enemy" set
     * Time: O(E * α(V)), Space: O(V)
     */
    public static boolean isBipartiteUnionFind(int n, int[][] edges) {
        UnionFind uf = new UnionFind(2 * n); // Double size for enemy tracking
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            // Check if u and v are in same component (conflict)
            if (uf.isConnected(u, v)) {
                return false;
            }
            
            // Connect u with v's enemies and v with u's enemies
            uf.union(u, v + n);
            uf.union(v, u + n);
        }
        
        return true;
    }
    
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
        
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🎨 BIPARTITE GRAPH CHECK - COMPREHENSIVE IMPLEMENTATION");
        System.out.println("======================================================");
        
        // Test basic bipartite check
        System.out.println("\n🔍 TESTING BIPARTITE CHECK:");
        int[][] graph1 = {{1,3},{0,2},{1,3},{0,2}};
        boolean bipartite1 = isBipartite(graph1);
        System.out.println("Graph is bipartite: " + bipartite1); // true
        
        int[][] graph2 = {{1,2,3},{0,2},{0,1,3},{0,2}};
        boolean bipartite2 = isBipartite(graph2);
        System.out.println("Graph with triangle is bipartite: " + bipartite2); // false
        
        // Test BFS implementation
        boolean bipartiteBFS = isBipartiteBFS(graph1);
        System.out.println("BFS bipartite check: " + bipartiteBFS); // true
        
        // Test possible bipartition
        System.out.println("\n👥 TESTING POSSIBLE BIPARTITION:");
        int[][] dislikes1 = {{1,2},{1,3},{2,4}};
        boolean canSplit1 = possibleBipartition(4, dislikes1);
        System.out.println("Can split group: " + canSplit1); // true
        
        int[][] dislikes2 = {{1,2},{2,3},{3,4},{4,5},{1,5}};
        boolean canSplit2 = possibleBipartition(5, dislikes2);
        System.out.println("Can split group (odd cycle): " + canSplit2); // false
        
        // Test get bipartite groups
        System.out.println("\n🎯 TESTING BIPARTITE GROUPS:");
        List<List<Integer>> groups = getBipartiteGroups(graph1);
        if (!groups.isEmpty()) {
            System.out.println("Group 1: " + groups.get(0));
            System.out.println("Group 2: " + groups.get(1));
        }
        
        // Test odd cycle detection
        System.out.println("\n🔄 TESTING ODD CYCLE DETECTION:");
        List<List<Integer>> graphList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            graphList.add(new ArrayList<>());
        }
        graphList.get(0).add(1); graphList.get(1).add(0);
        graphList.get(1).add(2); graphList.get(2).add(1);
        graphList.get(2).add(3); graphList.get(3).add(2);
        graphList.get(3).add(0); graphList.get(0).add(3);
        
        boolean hasOdd = hasOddCycle(graphList);
        System.out.println("Graph has odd cycle: " + hasOdd); // false (even cycle)
        
        // Add one more edge to create triangle
        graphList.get(0).add(2); graphList.get(2).add(0);
        boolean hasOdd2 = hasOddCycle(graphList);
        System.out.println("Graph with triangle has odd cycle: " + hasOdd2); // true
        
        // Test Union-Find bipartite check
        System.out.println("\n🔗 TESTING UNION-FIND BIPARTITE:");
        int[][] edges1 = {{0,1},{1,2},{2,3},{3,0}};
        boolean ufBipartite1 = isBipartiteUnionFind(4, edges1);
        System.out.println("Union-Find bipartite (even cycle): " + ufBipartite1); // true
        
        int[][] edges2 = {{0,1},{1,2},{2,0}};
        boolean ufBipartite2 = isBipartiteUnionFind(3, edges2);
        System.out.println("Union-Find bipartite (triangle): " + ufBipartite2); // false
        
        System.out.println("\n✅ Bipartite Graph Check Pattern Completed!");
        System.out.println("Key Principles:");
        System.out.println("1. A graph is bipartite iff it contains no odd cycles");
        System.out.println("2. Use two-coloring with DFS/BFS for detection");
        System.out.println("3. Check all connected components in disconnected graphs");
        System.out.println("4. Apply to conflict resolution and matching problems");
        System.out.println("5. Use Union-Find for dynamic bipartite checking");
        System.out.println("6. Handle edge cases: empty graphs, single nodes");
    }
} 