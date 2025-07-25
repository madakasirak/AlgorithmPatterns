package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🌲 MINIMUM SPANNING TREE PATTERN - COMPREHENSIVE IMPLEMENTATION
 * 
 * ============================================================================
 * 📚 PATTERN OVERVIEW
 * ============================================================================
 * 
 * Minimum Spanning Tree (MST) Pattern finds the minimum weight connected subtree
 * that connects all vertices in a weighted graph. Essential for network design,
 * clustering, and optimization problems where you need to connect all nodes
 * with minimum total cost.
 * 
 * 🔑 CORE ALGORITHMS:
 * - Kruskal's: Sort edges, use Union-Find to avoid cycles
 * - Prim's: Grow tree from arbitrary vertex using priority queue
 * 
 * Time: O(E log E) for Kruskal's, O((V + E) log V) for Prim's
 * Space: O(V) for Union-Find/visited, O(E) for edge storage
 * 
 * ============================================================================
 * 🎯 APPLICATIONS: Network design, clustering, circuit design, transportation
 * ============================================================================
 */

public class MinimumSpanningTree {
    
    /**
     * Edge class for MST algorithms
     */
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    
    /**
     * Minimum Cost to Connect All Points - Prim's Algorithm
     * 
     * You are given an array points representing integer coordinates of some 
     * points on a 2D-plane, where points[i] = [xi, yi]. The cost of connecting 
     * two points [xi, yi] and [xj, yj] is the manhattan distance between them.
     * Return the minimum cost to make all points connected.
     * 
     * Strategy: Prim's algorithm with priority queue
     * Time: O(V² log V), Space: O(V²)
     * 
     * LeetCode: https://leetcode.com/problems/min-cost-to-connect-all-points/
     */
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        
        // Priority queue: [cost, point_index]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[] visited = new boolean[n];
        
        pq.offer(new int[]{0, 0}); // Start from point 0
        int totalCost = 0;
        int edgesUsed = 0;
        
        while (!pq.isEmpty() && edgesUsed < n) {
            int[] current = pq.poll();
            int cost = current[0];
            int point = current[1];
            
            if (visited[point]) continue;
            
            visited[point] = true;
            totalCost += cost;
            edgesUsed++;
            
            // Add all edges from current point to unvisited points
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int manhattanDist = Math.abs(points[point][0] - points[i][0]) + 
                                      Math.abs(points[point][1] - points[i][1]);
                    pq.offer(new int[]{manhattanDist, i});
                }
            }
        }
        
        return totalCost;
    }
    
    /**
     * Alternative: Kruskal's Algorithm for Connect All Points
     * Strategy: Sort all edges, use Union-Find to build MST
     */
    public static int minCostConnectPointsKruskal(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();
        
        // Generate all possible edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cost = Math.abs(points[i][0] - points[j][0]) + 
                          Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, cost));
            }
        }
        
        // Sort edges by weight
        Collections.sort(edges);
        
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        int edgesUsed = 0;
        
        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                totalCost += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) break; // MST complete
            }
        }
        
        return totalCost;
    }
    
    /**
     * Connecting Cities With Minimum Cost - Classic MST Problem
     * 
     * There are n cities labeled from 1 to n. You are given the integer n and 
     * an array connections where connections[i] = [xi, yi, costi] indicates that 
     * the cost of connecting city xi and city yi is costi.
     * Return the minimum cost to connect all cities.
     * 
     * Strategy: Kruskal's algorithm with Union-Find
     * Time: O(E log E), Space: O(V)
     * 
     * LeetCode: https://leetcode.com/problems/connecting-cities-with-minimum-cost/
     */
    public static int minimumCost(int n, int[][] connections) {
        // Convert to Edge objects
        List<Edge> edges = new ArrayList<>();
        for (int[] conn : connections) {
            edges.add(new Edge(conn[0] - 1, conn[1] - 1, conn[2])); // Convert to 0-indexed
        }
        
        Collections.sort(edges);
        
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        int edgesUsed = 0;
        
        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                totalCost += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }
        
        return edgesUsed == n - 1 ? totalCost : -1; // Check if all connected
    }
    
    /**
     * Campus Bikes II - Minimum Assignment Cost
     * 
     * On a campus represented as a 2D grid, there are n workers and m bikes, 
     * with n <= m. Each worker and bike is a 2D coordinate on this grid.
     * Assign a bike to each worker such that the sum of Manhattan distances 
     * is minimized.
     * 
     * Strategy: Modified MST with assignment constraints (Hungarian-like approach)
     * Time: O(n * 2^n), Space: O(2^n)
     * 
     * LeetCode: https://leetcode.com/problems/campus-bikes-ii/
     */
    public static int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        
        // DP with bitmask: dp[mask] = min cost to assign bikes in mask to first popcount(mask) workers
        int[] dp = new int[1 << m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int mask = 0; mask < (1 << m); mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;
            
            int workerIndex = Integer.bitCount(mask);
            if (workerIndex >= n) continue;
            
            for (int bike = 0; bike < m; bike++) {
                if ((mask & (1 << bike)) == 0) { // Bike not assigned
                    int newMask = mask | (1 << bike);
                    int cost = manhattanDistance(workers[workerIndex], bikes[bike]);
                    dp[newMask] = Math.min(dp[newMask], dp[mask] + cost);
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << m); mask++) {
            if (Integer.bitCount(mask) == n) {
                result = Math.min(result, dp[mask]);
            }
        }
        
        return result;
    }
    
    private static int manhattanDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
    
    /**
     * BONUS: Generic MST using Prim's Algorithm
     * For adjacency list representation
     * 
     * Strategy: Grow MST from arbitrary vertex
     * Time: O((V + E) log V), Space: O(V + E)
     */
    public static List<Edge> primMST(List<List<int[]>> graph) {
        int n = graph.size();
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[n];
        
        // Priority queue: [weight, vertex, parent]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, -1}); // Start from vertex 0
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int weight = current[0];
            int vertex = current[1];
            int parent = current[2];
            
            if (visited[vertex]) continue;
            
            visited[vertex] = true;
            if (parent != -1) {
                mst.add(new Edge(parent, vertex, weight));
            }
            
            for (int[] neighbor : graph.get(vertex)) {
                int nextVertex = neighbor[0];
                int nextWeight = neighbor[1];
                
                if (!visited[nextVertex]) {
                    pq.offer(new int[]{nextWeight, nextVertex, vertex});
                }
            }
        }
        
        return mst;
    }
    
    /**
     * BONUS: Generic MST using Kruskal's Algorithm
     * For edge list representation
     * 
     * Strategy: Sort edges, use Union-Find to avoid cycles
     * Time: O(E log E), Space: O(V)
     */
    public static List<Edge> kruskalMST(int vertices, List<Edge> edges) {
        Collections.sort(edges);
        
        UnionFind uf = new UnionFind(vertices);
        List<Edge> mst = new ArrayList<>();
        
        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                mst.add(edge);
                if (mst.size() == vertices - 1) break;
            }
        }
        
        return mst;
    }
    
    /**
     * BONUS: Find Critical and Pseudo-Critical Edges in MST
     * 
     * Strategy: For each edge, check if removing it increases MST weight (critical)
     * or if forcing it doesn't increase MST weight (pseudo-critical)
     * Time: O(E² log E), Space: O(E)
     */
    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // Add original indices to edges
        List<int[]> edgesWithIndex = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            edgesWithIndex.add(new int[]{edges[i][0], edges[i][1], edges[i][2], i});
        }
        
        // Sort by weight
        edgesWithIndex.sort((a, b) -> a[2] - b[2]);
        
        // Find original MST weight
        int originalWeight = findMSTWeight(n, edgesWithIndex, -1, -1);
        
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();
        
        for (int i = 0; i < edgesWithIndex.size(); i++) {
            int edgeIndex = edgesWithIndex.get(i)[3];
            
            // Check if critical (removing edge increases weight)
            int weightWithoutEdge = findMSTWeight(n, edgesWithIndex, i, -1);
            if (weightWithoutEdge > originalWeight) {
                critical.add(edgeIndex);
            } else {
                // Check if pseudo-critical (forcing edge doesn't increase weight)
                int weightWithEdge = findMSTWeight(n, edgesWithIndex, -1, i);
                if (weightWithEdge == originalWeight) {
                    pseudoCritical.add(edgeIndex);
                }
            }
        }
        
        return Arrays.asList(critical, pseudoCritical);
    }
    
    private static int findMSTWeight(int n, List<int[]> edges, int skipEdge, int forceEdge) {
        UnionFind uf = new UnionFind(n);
        int weight = 0;
        
        // Force edge if specified
        if (forceEdge != -1) {
            int[] edge = edges.get(forceEdge);
            uf.union(edge[0], edge[1]);
            weight += edge[2];
        }
        
        for (int i = 0; i < edges.size(); i++) {
            if (i == skipEdge) continue;
            
            int[] edge = edges.get(i);
            if (uf.union(edge[0], edge[1])) {
                weight += edge[2];
            }
        }
        
        return uf.getComponentCount() == 1 ? weight : Integer.MAX_VALUE;
    }
    
    /**
     * Union-Find Data Structure for MST algorithms
     */
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int componentCount;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            componentCount = n;
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
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) return false;
            
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            
            componentCount--;
            return true;
        }
        
        public int getComponentCount() {
            return componentCount;
        }
    }
    
    /**
     * BONUS: Maximum Spanning Tree
     * Find the spanning tree with maximum total weight
     * 
     * Strategy: Negate weights and find MST, or use max-heap in Prim's
     * Time: O(E log E), Space: O(V)
     */
    public static List<Edge> maximumSpanningTree(int vertices, List<Edge> edges) {
        // Negate weights
        for (Edge edge : edges) {
            edge.weight = -edge.weight;
        }
        
        List<Edge> mst = kruskalMST(vertices, edges);
        
        // Restore original weights
        for (Edge edge : edges) {
            edge.weight = -edge.weight;
        }
        for (Edge edge : mst) {
            edge.weight = -edge.weight;
        }
        
        return mst;
    }
    
    public static void main(String[] args) {
        System.out.println("🌲 MINIMUM SPANNING TREE - COMPREHENSIVE IMPLEMENTATION");
        System.out.println("=======================================================");
        
        // Test min cost to connect points
        System.out.println("\n🔗 TESTING MIN COST CONNECT POINTS:");
        int[][] points1 = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int cost1 = minCostConnectPoints(points1);
        System.out.println("Prim's algorithm cost: " + cost1); // 20
        
        int cost2 = minCostConnectPointsKruskal(points1);
        System.out.println("Kruskal's algorithm cost: " + cost2); // 20
        
        // Test connecting cities
        System.out.println("\n🏙️ TESTING CONNECTING CITIES:");
        int[][] connections1 = {{1,2,5},{1,3,6},{2,3,1}};
        int cityCost1 = minimumCost(3, connections1);
        System.out.println("Minimum cost to connect cities: " + cityCost1); // 6
        
        int[][] connections2 = {{1,2,3},{3,4,4}};
        int cityCost2 = minimumCost(4, connections2);
        System.out.println("Impossible connection cost: " + cityCost2); // -1
        
        // Test campus bikes assignment
        System.out.println("\n🚲 TESTING CAMPUS BIKES:");
        int[][] workers = {{0,0},{1,1},{2,0}};
        int[][] bikes = {{1,0},{2,2},{2,1}};
        int bikesCost = assignBikes(workers, bikes);
        System.out.println("Minimum assignment cost: " + bikesCost); // 4
        
        // Test generic MST algorithms
        System.out.println("\n🌳 TESTING GENERIC MST:");
        
        // Create adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new int[]{1, 10});
        graph.get(0).add(new int[]{2, 6});
        graph.get(0).add(new int[]{3, 5});
        graph.get(1).add(new int[]{0, 10});
        graph.get(1).add(new int[]{3, 15});
        graph.get(2).add(new int[]{0, 6});
        graph.get(2).add(new int[]{3, 4});
        graph.get(3).add(new int[]{0, 5});
        graph.get(3).add(new int[]{1, 15});
        graph.get(3).add(new int[]{2, 4});
        
        List<Edge> primResult = primMST(graph);
        System.out.println("Prim's MST edges:");
        int primTotal = 0;
        for (Edge edge : primResult) {
            System.out.println("  " + edge.src + " - " + edge.dest + " (weight: " + edge.weight + ")");
            primTotal += edge.weight;
        }
        System.out.println("Prim's total weight: " + primTotal);
        
        // Test Kruskal's
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 10),
            new Edge(0, 2, 6),
            new Edge(0, 3, 5),
            new Edge(1, 3, 15),
            new Edge(2, 3, 4)
        );
        
        List<Edge> kruskalResult = kruskalMST(4, edges);
        System.out.println("Kruskal's MST edges:");
        int kruskalTotal = 0;
        for (Edge edge : kruskalResult) {
            System.out.println("  " + edge.src + " - " + edge.dest + " (weight: " + edge.weight + ")");
            kruskalTotal += edge.weight;
        }
        System.out.println("Kruskal's total weight: " + kruskalTotal);
        
        System.out.println("\n✅ Minimum Spanning Tree Pattern Completed!");
        System.out.println("Key Principles:");
        System.out.println("1. Use Kruskal's for sparse graphs, Prim's for dense graphs");
        System.out.println("2. Kruskal's: Sort edges + Union-Find for cycle detection");
        System.out.println("3. Prim's: Grow tree + Priority queue for minimum edge");
        System.out.println("4. MST weight is unique, but tree structure may vary");
        System.out.println("5. Critical edges increase MST weight when removed");
        System.out.println("6. Applications: Network design, clustering, optimization");
    }
} 