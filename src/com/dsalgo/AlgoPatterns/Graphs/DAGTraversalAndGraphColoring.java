package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🎯 DAG TRAVERSAL & GRAPH COLORING PATTERNS - COMPREHENSIVE IMPLEMENTATION
 * 
 * ============================================================================
 * 📚 DUAL PATTERN OVERVIEW
 * ============================================================================
 * 
 * This file combines two related graph patterns:
 * 
 * 1️⃣ DAG TRAVERSAL: Process directed acyclic graphs efficiently using topological
 * sorting, longest paths, and dependency resolution.
 * 
 * 2️⃣ GRAPH COLORING: Assign colors to vertices such that no adjacent vertices
 * share the same color, with applications in scheduling and conflict resolution.
 * 
 * Both patterns leverage graph traversal and constraint satisfaction techniques.
 * 
 * ============================================================================
 * 🎯 APPLICATIONS: Scheduling, dependency resolution, resource allocation, optimization
 * ============================================================================
 */

public class DAGTraversalAndGraphColoring {
    
    // ============================================================================
    // 🎯 DAG TRAVERSAL PATTERN IMPLEMENTATIONS
    // ============================================================================
    
    /**
     * Alien Dictionary - Topological Sort for Character Ordering
     * 
     * There is a new alien language that uses the English alphabet. However, 
     * the order among the letters is unknown to you. You are given a list of 
     * strings words from the alien language's dictionary, where the strings in 
     * words are sorted lexicographically by the rules of this new language.
     * 
     * Strategy: Build graph from word comparisons, then topological sort
     * Time: O(C) where C = total number of characters in all words
     * Space: O(1) since alphabet size is constant (26 characters)
     * 
     * LeetCode: https://leetcode.com/problems/alien-dictionary/
     */
    public static String alienOrder(String[] words) {
        // Build graph and calculate in-degrees
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        
        // Build edges based on word comparisons
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check if word1 is prefix of word2 but longer (invalid case)
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            // Find first different character
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break; // Only first difference matters
                }
            }
        }
        
        // Topological sort using Kahn's algorithm
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);
            
            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return result.length() == inDegree.size() ? result.toString() : "";
    }
    
    /**
     * Longest Increasing Path in a Matrix - DAG Longest Path
     * 
     * Given an m x n integers matrix, return the length of the longest increasing 
     * path. From each cell, you can either move in four directions: left, right, 
     * up, or down. You may not move diagonally or move outside the boundary.
     * 
     * Strategy: DFS with memoization (treat as DAG due to increasing constraint)
     * Time: O(m * n), Space: O(m * n)
     * 
     * LeetCode: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
     */
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxLength = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLength = Math.max(maxLength, dfsLongestPath(matrix, i, j, memo));
            }
        }
        
        return maxLength;
    }
    
    private static int dfsLongestPath(int[][] matrix, int row, int col, int[][] memo) {
        if (memo[row][col] != 0) return memo[row][col];
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxPath = 1; // At least the cell itself
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && 
                newCol < matrix[0].length && matrix[newRow][newCol] > matrix[row][col]) {
                maxPath = Math.max(maxPath, 1 + dfsLongestPath(matrix, newRow, newCol, memo));
            }
        }
        
        memo[row][col] = maxPath;
        return maxPath;
    }
    
    /**
     * Course Schedule III - Maximum Courses with Deadlines
     * 
     * There are n different online courses numbered from 1 to n. You are given 
     * an array courses where courses[i] = [durationi, lastDayi] denotes that the 
     * ith course should be taken continuously for durationi days and must be 
     * finished before or on lastDayi.
     * 
     * Strategy: Greedy algorithm with priority queue (not pure DAG but scheduling)
     * Time: O(n log n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/course-schedule-iii/
     */
    public static int scheduleCourse(int[][] courses) {
        // Sort courses by deadline
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        
        // Max-heap to store durations of selected courses
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int currentTime = 0;
        
        for (int[] course : courses) {
            int duration = course[0];
            int deadline = course[1];
            
            if (currentTime + duration <= deadline) {
                // Can take this course
                maxHeap.offer(duration);
                currentTime += duration;
            } else if (!maxHeap.isEmpty() && maxHeap.peek() > duration) {
                // Replace longest course with current shorter course
                currentTime -= maxHeap.poll();
                maxHeap.offer(duration);
                currentTime += duration;
            }
        }
        
        return maxHeap.size();
    }
    
    /**
     * All Paths From Source Lead to Destination - DAG Validation
     * 
     * Given the edges of a directed graph where edges[i] = [ai, bi] indicates 
     * there is an edge from node ai to node bi, and two nodes source and 
     * destination of this graph, determine whether or not all paths starting 
     * from source eventually end at destination.
     * 
     * Strategy: DFS with three states to detect cycles and validate paths
     * Time: O(V + E), Space: O(V)
     * 
     * LeetCode: https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
     */
    public static boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // Check if destination has any outgoing edges
        if (!graph.get(destination).isEmpty()) {
            return false;
        }
        
        // 0: WHITE, 1: GRAY, 2: BLACK
        int[] colors = new int[n];
        return dfsValidatePath(graph, source, destination, colors);
    }
    
    private static boolean dfsValidatePath(List<List<Integer>> graph, int node, 
                                         int destination, int[] colors) {
        if (colors[node] == 1) return false; // Cycle detected
        if (colors[node] == 2) return true;  // Already validated
        
        colors[node] = 1; // Mark as being processed
        
        if (graph.get(node).isEmpty()) {
            // Leaf node - must be destination
            colors[node] = 2;
            return node == destination;
        }
        
        for (int neighbor : graph.get(node)) {
            if (!dfsValidatePath(graph, neighbor, destination, colors)) {
                return false;
            }
        }
        
        colors[node] = 2; // Mark as validated
        return true;
    }
    
    // ============================================================================
    // 🎨 GRAPH COLORING PATTERN IMPLEMENTATIONS
    // ============================================================================
    
    /**
     * Flower Planting With No Adjacent - Simple Graph Coloring
     * 
     * You have n gardens, labeled from 1 to n, and an array paths where 
     * paths[i] = [xi, yi] describes a bidirectional path between garden xi to 
     * garden yi. In each garden, you want to plant one of 4 types of flowers.
     * No two gardens connected by a path should have the same type of flower.
     * 
     * Strategy: Greedy coloring with 4 colors
     * Time: O(V + E), Space: O(V + E)
     * 
     * LeetCode: https://leetcode.com/problems/flower-planting-with-no-adjacent/
     */
    public static int[] gardenNoAdj(int n, int[][] paths) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            graph.get(path[0]).add(path[1]);
            graph.get(path[1]).add(path[0]);
        }
        
        int[] colors = new int[n + 1]; // 1-indexed, colors 1-4
        
        for (int garden = 1; garden <= n; garden++) {
            Set<Integer> usedColors = new HashSet<>();
            
            // Check colors of adjacent gardens
            for (int neighbor : graph.get(garden)) {
                if (colors[neighbor] != 0) {
                    usedColors.add(colors[neighbor]);
                }
            }
            
            // Assign first available color (1-4)
            for (int color = 1; color <= 4; color++) {
                if (!usedColors.contains(color)) {
                    colors[garden] = color;
                    break;
                }
            }
        }
        
        return Arrays.copyOfRange(colors, 1, n + 1); // Remove index 0
    }
    
    /**
     * BONUS: Generic Graph Coloring (Greedy Algorithm)
     * 
     * Color graph with minimum number of colors using greedy approach.
     * Note: This doesn't guarantee minimum chromatic number.
     * 
     * Strategy: Process vertices in order, assign smallest available color
     * Time: O(V²), Space: O(V)
     */
    public static int[] greedyColoring(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        
        // Color first vertex with color 0
        colors[0] = 0;
        
        for (int vertex = 1; vertex < n; vertex++) {
            Set<Integer> usedColors = new HashSet<>();
            
            // Check colors of adjacent vertices
            for (int neighbor : graph.get(vertex)) {
                if (colors[neighbor] != -1) {
                    usedColors.add(colors[neighbor]);
                }
            }
            
            // Assign smallest available color
            int color = 0;
            while (usedColors.contains(color)) {
                color++;
            }
            colors[vertex] = color;
        }
        
        return colors;
    }
    
    /**
     * BONUS: Welsh-Powell Graph Coloring Algorithm
     * 
     * Improved greedy coloring that processes vertices in decreasing order of degree.
     * Often produces better results than simple greedy coloring.
     * 
     * Strategy: Sort by degree, then greedy coloring
     * Time: O(V log V + V²), Space: O(V)
     */
    public static int[] welshPowellColoring(List<List<Integer>> graph) {
        int n = graph.size();
        
        // Create vertices with their degrees
        List<int[]> vertexDegree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertexDegree.add(new int[]{i, graph.get(i).size()});
        }
        
        // Sort by degree (descending)
        vertexDegree.sort((a, b) -> b[1] - a[1]);
        
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        
        for (int[] vd : vertexDegree) {
            int vertex = vd[0];
            Set<Integer> usedColors = new HashSet<>();
            
            for (int neighbor : graph.get(vertex)) {
                if (colors[neighbor] != -1) {
                    usedColors.add(colors[neighbor]);
                }
            }
            
            int color = 0;
            while (usedColors.contains(color)) {
                color++;
            }
            colors[vertex] = color;
        }
        
        return colors;
    }
    
    /**
     * BONUS: Check if Graph is k-Colorable
     * 
     * Determine if graph can be colored with at most k colors.
     * Uses backtracking for exact solution.
     * 
     * Strategy: Backtracking with constraint checking
     * Time: O(k^V) in worst case, Space: O(V)
     */
    public static boolean isKColorable(List<List<Integer>> graph, int k) {
        int n = graph.size();
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        
        return backtrackColoring(graph, 0, colors, k);
    }
    
    private static boolean backtrackColoring(List<List<Integer>> graph, int vertex, 
                                           int[] colors, int k) {
        if (vertex == graph.size()) {
            return true; // All vertices colored
        }
        
        for (int color = 0; color < k; color++) {
            if (isSafeToColor(graph, vertex, color, colors)) {
                colors[vertex] = color;
                
                if (backtrackColoring(graph, vertex + 1, colors, k)) {
                    return true;
                }
                
                colors[vertex] = -1; // Backtrack
            }
        }
        
        return false;
    }
    
    private static boolean isSafeToColor(List<List<Integer>> graph, int vertex, 
                                       int color, int[] colors) {
        for (int neighbor : graph.get(vertex)) {
            if (colors[neighbor] == color) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * BONUS: Find Chromatic Number (Exact)
     * 
     * Find the minimum number of colors needed to color the graph.
     * Uses iterative approach trying k = 1, 2, 3, ... until success.
     * 
     * Strategy: Try k-coloring for increasing k
     * Time: Exponential in worst case, Space: O(V)
     */
    public static int chromaticNumber(List<List<Integer>> graph) {
        int n = graph.size();
        if (n == 0) return 0;
        
        // Check for trivial cases
        if (n == 1) return 1;
        
        // Check if bipartite (chromatic number = 2)
        if (isBipartiteCheck(graph)) return 2;
        
        // Try k-coloring for k = 3, 4, 5, ...
        for (int k = 3; k <= n; k++) {
            if (isKColorable(graph, k)) {
                return k;
            }
        }
        
        return n; // Worst case
    }
    
    private static boolean isBipartiteCheck(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfsBipartiteCheck(graph, i, 1, colors)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean dfsBipartiteCheck(List<List<Integer>> graph, int node, 
                                           int color, int[] colors) {
        colors[node] = color;
        
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == color) return false;
            if (colors[neighbor] == 0 && !dfsBipartiteCheck(graph, neighbor, -color, colors)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("🎯 DAG TRAVERSAL & GRAPH COLORING - COMPREHENSIVE IMPLEMENTATION");
        System.out.println("===============================================================");
        
        // Test Alien Dictionary
        System.out.println("\n👽 TESTING ALIEN DICTIONARY:");
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        String alienOrder1 = alienOrder(words1);
        System.out.println("Alien order: " + alienOrder1); // "wertf"
        
        String[] words2 = {"z", "x"};
        String alienOrder2 = alienOrder(words2);
        System.out.println("Simple alien order: " + alienOrder2); // "zx"
        
        // Test Longest Increasing Path
        System.out.println("\n📈 TESTING LONGEST INCREASING PATH:");
        int[][] matrix1 = {{9,9,4},{6,6,8},{2,1,1}};
        int longestPath1 = longestIncreasingPath(matrix1);
        System.out.println("Longest increasing path: " + longestPath1); // 4
        
        int[][] matrix2 = {{3,4,5},{3,2,6},{2,2,1}};
        int longestPath2 = longestIncreasingPath(matrix2);
        System.out.println("Another longest path: " + longestPath2); // 4
        
        // Test Course Schedule III
        System.out.println("\n📚 TESTING COURSE SCHEDULE III:");
        int[][] courses1 = {{100,200},{200,1300},{1000,1250},{2000,3200}};
        int maxCourses1 = scheduleCourse(courses1);
        System.out.println("Maximum courses: " + maxCourses1); // 3
        
        // Test All Paths Lead to Destination
        System.out.println("\n🎯 TESTING ALL PATHS TO DESTINATION:");
        int[][] edges1 = {{0,1},{0,2},{1,3},{2,3}};
        boolean leadsTo1 = leadsToDestination(4, edges1, 0, 3);
        System.out.println("All paths lead to destination: " + leadsTo1); // true
        
        // Test Flower Planting
        System.out.println("\n🌺 TESTING FLOWER PLANTING:");
        int[][] paths1 = {{1,2},{2,3},{3,1}};
        int[] flowerColors1 = gardenNoAdj(3, paths1);
        System.out.println("Flower colors: " + Arrays.toString(flowerColors1));
        
        int[][] paths2 = {{1,2},{3,4}};
        int[] flowerColors2 = gardenNoAdj(4, paths2);
        System.out.println("Disconnected flower colors: " + Arrays.toString(flowerColors2));
        
        // Test Graph Coloring Algorithms
        System.out.println("\n🎨 TESTING GRAPH COLORING:");
        
        // Create sample graph (triangle)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1); graph.get(1).add(0);
        graph.get(1).add(2); graph.get(2).add(1);
        graph.get(2).add(0); graph.get(0).add(2);
        
        int[] greedyColors = greedyColoring(graph);
        System.out.println("Greedy coloring: " + Arrays.toString(greedyColors));
        
        int[] welshColors = welshPowellColoring(graph);
        System.out.println("Welsh-Powell coloring: " + Arrays.toString(welshColors));
        
        boolean is2Colorable = isKColorable(graph, 2);
        System.out.println("Is 2-colorable: " + is2Colorable); // false
        
        boolean is3Colorable = isKColorable(graph, 3);
        System.out.println("Is 3-colorable: " + is3Colorable); // true
        
        int chromaticNum = chromaticNumber(graph);
        System.out.println("Chromatic number: " + chromaticNum); // 3
        
        System.out.println("\n✅ DAG Traversal & Graph Coloring Patterns Completed!");
        System.out.println("Key Principles:");
        System.out.println("🎯 DAG Traversal:");
        System.out.println("  - Use topological sort for dependency resolution");
        System.out.println("  - Apply DFS with memoization for longest paths");
        System.out.println("  - Detect cycles to validate DAG properties");
        System.out.println("🎨 Graph Coloring:");
        System.out.println("  - Greedy coloring for approximation solutions");
        System.out.println("  - Welsh-Powell for better degree-based ordering");
        System.out.println("  - Backtracking for exact k-colorability");
        System.out.println("  - Applications in scheduling and conflict resolution");
    }
} 