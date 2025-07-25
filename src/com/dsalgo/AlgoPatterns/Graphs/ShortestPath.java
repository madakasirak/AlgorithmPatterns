package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🛣️ SHORTEST PATH FINDING PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of shortest path algorithms
 * for navigation systems, network routing, game pathfinding, and optimization
 * problems. These algorithms demonstrate Dijkstra's algorithm, BFS for unweighted
 * graphs, Bellman-Ford for negative weights, and specialized shortest path
 * techniques for various graph types and constraints.
 * 
 * Pattern Focus: Path optimization, distance minimization, route finding
 * Time Complexity: O(V + E) for BFS, O((V + E) log V) for Dijkstra's
 * Space Complexity: O(V) for distance tracking, O(V + E) for graph storage
 */

public class ShortestPath {
    
    /**
     * Network Delay Time - Dijkstra's Algorithm Application
     * 
     * You are given a network of n nodes, labeled from 1 to n. You are also given 
     * times, a list of travel times as directed edges times[i] = (ui, vi, wi), 
     * where ui is the source node, vi is the target node, and wi is the time it 
     * takes for a signal to travel from source to target.
     * 
     * Strategy: Single-source shortest path using Dijkstra's algorithm
     * Time: O((V + E) log V) where V = nodes, E = edges
     * Space: O(V + E) for adjacency list and priority queue
     * 
     * LeetCode: https://leetcode.com/problems/network-delay-time/
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        
        // Dijkstra's algorithm
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];
            
            if (dist > distances[node]) continue;
            
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int newDist = dist + weight;
                
                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }
        
        // Find maximum distance (time for all nodes to receive signal)
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1; // Some node unreachable
            }
            maxTime = Math.max(maxTime, distances[i]);
        }
        
        return maxTime;
    }
    
    /**
     * Cheapest Flights Within K Stops - Modified Dijkstra's
     * 
     * There are n cities connected by some number of flights. You are given an 
     * array flights where flights[i] = [fromi, toi, pricei] indicates that there 
     * is a flight from city fromi to city toi with cost pricei.
     * Find the cheapest price from src to dst with at most k stops.
     * 
     * Strategy: Modified Dijkstra's with stop limit constraint
     * Time: O((V + E) * K * log(V * K))
     * Space: O(V + E + V * K)
     * 
     * LeetCode: https://leetcode.com/problems/cheapest-flights-within-k-stops/
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        
        // Priority queue: [cost, city, stops]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src, 0});
        
        // Track minimum cost to reach each city with at most i stops
        Map<String, Integer> visited = new HashMap<>();
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0];
            int city = current[1];
            int stops = current[2];
            
            if (city == dst) return cost;
            
            if (stops > k) continue;
            
            String key = city + "," + stops;
            if (visited.containsKey(key) && visited.get(key) <= cost) {
                continue;
            }
            visited.put(key, cost);
            
            for (int[] neighbor : graph.get(city)) {
                int nextCity = neighbor[0];
                int price = neighbor[1];
                pq.offer(new int[]{cost + price, nextCity, stops + 1});
            }
        }
        
        return -1;
    }
    
    /**
     * Shortest Path in Binary Matrix - BFS Application
     * 
     * Given an n x n binary matrix grid, return the length of the shortest 
     * clear path in the matrix. If there is no clear path, return -1.
     * A clear path is from top-left to bottom-right through cells with value 0.
     * 
     * Strategy: BFS with 8-directional movement
     * Time: O(n²) where n is matrix dimension
     * Space: O(n²) for visited tracking and queue
     * 
     * LeetCode: https://leetcode.com/problems/shortest-path-in-binary-matrix/
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n-1][n-1] != 0) return -1;
        
        if (n == 1) return 1;
        
        int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        queue.offer(new int[]{0, 0, 1}); // row, col, distance
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];
            
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n &&
                    grid[newRow][newCol] == 0 && !visited[newRow][newCol]) {
                    
                    if (newRow == n-1 && newCol == n-1) {
                        return dist + 1;
                    }
                    
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Word Ladder - BFS for Transformation Path
     * 
     * A transformation sequence from word beginWord to word endWord using a 
     * dictionary wordList is a sequence of words such that:
     * - The first word is beginWord
     * - The last word is endWord  
     * - Adjacent words differ by exactly one character
     * 
     * Strategy: BFS treating words as nodes and valid transformations as edges
     * Time: O(M² × N) where M = word length, N = number of words
     * Space: O(M × N) for storing word patterns and visited tracking
     * 
     * LeetCode: https://leetcode.com/problems/word-ladder/
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(beginWord);
        visited.add(beginWord);
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                // Try all possible one-character changes
                char[] chars = currentWord.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        chars[j] = c;
                        String newWord = new String(chars);
                        
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            queue.offer(newWord);
                        }
                    }
                    
                    chars[j] = originalChar; // Restore original character
                }
            }
            
            level++;
        }
        
        return 0;
    }
    
    /**
     * BONUS: All-Pairs Shortest Path using Floyd-Warshall
     * Find shortest distances between all pairs of vertices
     * 
     * Strategy: Dynamic programming with intermediate vertices
     * Time: O(V³), Space: O(V²)
     */
    public static int[][] floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];
        
        // Initialize distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (graph[i][j] != 0) {
                    dist[i][j] = graph[i][j];
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && 
                        dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        return dist;
    }
    
    /**
     * BONUS: Bellman-Ford Algorithm with Negative Cycle Detection
     * Handle graphs with negative edge weights
     * 
     * Strategy: Edge relaxation with cycle detection
     * Time: O(VE), Space: O(V)
     */
    public static class BellmanFordResult {
        public int[] distances;
        public boolean hasNegativeCycle;
        
        BellmanFordResult(int[] distances, boolean hasNegativeCycle) {
            this.distances = distances;
            this.hasNegativeCycle = hasNegativeCycle;
        }
    }
    
    public static BellmanFordResult bellmanFord(int n, int[][] edges, int source) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // Relax edges V-1 times
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                
                if (distances[u] != Integer.MAX_VALUE && 
                    distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }
        
        // Check for negative cycles
        boolean hasNegativeCycle = false;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            
            if (distances[u] != Integer.MAX_VALUE && 
                distances[u] + weight < distances[v]) {
                hasNegativeCycle = true;
                break;
            }
        }
        
        return new BellmanFordResult(distances, hasNegativeCycle);
    }
    
    /**
     * BONUS: Dijkstra's Algorithm with Path Reconstruction
     * Track actual shortest paths, not just distances
     * 
     * Strategy: Maintain predecessor tracking during relaxation
     * Time: O((V + E) log V), Space: O(V)
     */
    public static class DijkstraResult {
        public int[] distances;
        public int[] predecessors;
        
        DijkstraResult(int[] distances, int[] predecessors) {
            this.distances = distances;
            this.predecessors = predecessors;
        }
    }
    
    public static DijkstraResult dijkstraWithPath(List<List<int[]>> graph, int source) {
        int n = graph.size();
        int[] distances = new int[n];
        int[] predecessors = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[source] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{source, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];
            
            if (dist > distances[node]) continue;
            
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int newDist = dist + weight;
                
                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    predecessors[nextNode] = node;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }
        
        return new DijkstraResult(distances, predecessors);
    }
    
    public static List<Integer> reconstructPath(int[] predecessors, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        if (predecessors[destination] == -1 && destination != source) {
            return path; // No path exists
        }
        
        int current = destination;
        while (current != -1) {
            path.add(current);
            current = predecessors[current];
        }
        
        Collections.reverse(path);
        return path;
    }
    
    /**
     * BONUS: A* Search Algorithm Implementation
     * Heuristic-guided shortest path for informed search
     * 
     * Strategy: Priority queue with f(n) = g(n) + h(n)
     * Time: Depends on heuristic quality, can be much faster than Dijkstra's
     * Space: O(V) for open and closed sets
     */
    public static List<int[]> aStarSearch(int[][] grid, int[] start, int[] goal) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Priority queue with [f_score, g_score, row, col]
        PriorityQueue<int[]> openSet = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<String> closedSet = new HashSet<>();
        Map<String, int[]> parent = new HashMap<>();
        Map<String, Integer> gScore = new HashMap<>();
        
        String startKey = start[0] + "," + start[1];
        gScore.put(startKey, 0);
        int hStart = manhattanDistance(start, goal);
        openSet.offer(new int[]{hStart, 0, start[0], start[1]});
        
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        
        while (!openSet.isEmpty()) {
            int[] current = openSet.poll();
            int row = current[2];
            int col = current[3];
            String currentKey = row + "," + col;
            
            if (row == goal[0] && col == goal[1]) {
                return reconstructAStarPath(parent, start, goal);
            }
            
            closedSet.add(currentKey);
            
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                String neighborKey = newRow + "," + newCol;
                
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols ||
                    grid[newRow][newCol] == 1 || closedSet.contains(neighborKey)) {
                    continue;
                }
                
                int tentativeG = gScore.get(currentKey) + 1;
                
                if (!gScore.containsKey(neighborKey) || tentativeG < gScore.get(neighborKey)) {
                    parent.put(neighborKey, new int[]{row, col});
                    gScore.put(neighborKey, tentativeG);
                    int h = manhattanDistance(new int[]{newRow, newCol}, goal);
                    openSet.offer(new int[]{tentativeG + h, tentativeG, newRow, newCol});
                }
            }
        }
        
        return new ArrayList<>(); // No path found
    }
    
    private static int manhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
    
    private static List<int[]> reconstructAStarPath(Map<String, int[]> parent, int[] start, int[] goal) {
        List<int[]> path = new ArrayList<>();
        int[] current = goal;
        
        while (current != null) {
            path.add(new int[]{current[0], current[1]});
            String key = current[0] + "," + current[1];
            current = parent.get(key);
            
            if (current != null && current[0] == start[0] && current[1] == start[1]) {
                path.add(new int[]{start[0], start[1]});
                break;
            }
        }
        
        Collections.reverse(path);
        return path;
    }
    
    /**
     * BONUS: Bidirectional BFS for Shortest Path
     * Search from both ends to reduce search space
     * 
     * Strategy: Alternate BFS from source and destination
     * Time: O(b^(d/2)) vs O(b^d) for regular BFS
     * Space: O(b^(d/2))
     */
    public static int bidirectionalBFS(List<List<Integer>> graph, int start, int end) {
        if (start == end) return 0;
        
        Set<Integer> visitedFromStart = new HashSet<>();
        Set<Integer> visitedFromEnd = new HashSet<>();
        Queue<Integer> queueStart = new LinkedList<>();
        Queue<Integer> queueEnd = new LinkedList<>();
        
        queueStart.offer(start);
        queueEnd.offer(end);
        visitedFromStart.add(start);
        visitedFromEnd.add(end);
        
        int level = 0;
        
        while (!queueStart.isEmpty() || !queueEnd.isEmpty()) {
            level++;
            
            // Search from start
            if (!queueStart.isEmpty()) {
                int size = queueStart.size();
                for (int i = 0; i < size; i++) {
                    int node = queueStart.poll();
                    
                    for (int neighbor : graph.get(node)) {
                        if (visitedFromEnd.contains(neighbor)) {
                            return level;
                        }
                        
                        if (!visitedFromStart.contains(neighbor)) {
                            visitedFromStart.add(neighbor);
                            queueStart.offer(neighbor);
                        }
                    }
                }
            }
            
            // Search from end
            if (!queueEnd.isEmpty()) {
                int size = queueEnd.size();
                for (int i = 0; i < size; i++) {
                    int node = queueEnd.poll();
                    
                    for (int neighbor : graph.get(node)) {
                        if (visitedFromStart.contains(neighbor)) {
                            return level;
                        }
                        
                        if (!visitedFromEnd.contains(neighbor)) {
                            visitedFromEnd.add(neighbor);
                            queueEnd.offer(neighbor);
                        }
                    }
                }
            }
        }
        
        return -1; // No path found
    }
    
    public static void main(String[] args) {
        System.out.println("🛣️ SHORTEST PATH FINDING - PRACTICE IMPLEMENTATIONS");
        System.out.println("===================================================");
        
        // Test Network Delay Time
        System.out.println("\n📡 TESTING NETWORK DELAY TIME:");
        int[][] times1 = {{2,1,1},{2,3,1},{3,4,1}};
        int delay1 = networkDelayTime(times1, 4, 2);
        System.out.println("Network delay time: " + delay1); // 2
        
        int[][] times2 = {{1,2,1}};
        int delay2 = networkDelayTime(times2, 2, 1);
        System.out.println("Network delay time (simple): " + delay2); // 1
        
        // Test Cheapest Flights
        System.out.println("\n✈️ TESTING CHEAPEST FLIGHTS:");
        int[][] flights1 = {{0,1,100},{1,2,100},{0,2,500}};
        int cheapest1 = findCheapestPrice(3, flights1, 0, 2, 1);
        System.out.println("Cheapest price with 1 stop: " + cheapest1); // 200
        
        int[][] flights2 = {{0,1,100},{1,2,100},{0,2,500}};
        int cheapest2 = findCheapestPrice(3, flights2, 0, 2, 0);
        System.out.println("Cheapest price with 0 stops: " + cheapest2); // 500
        
        // Test Shortest Path in Binary Matrix
        System.out.println("\n🗂️ TESTING BINARY MATRIX PATH:");
        int[][] grid1 = {{0,0,0},{1,1,0},{1,1,0}};
        int path1 = shortestPathBinaryMatrix(grid1);
        System.out.println("Shortest path in binary matrix: " + path1); // 4
        
        int[][] grid2 = {{0,1},{1,0}};
        int path2 = shortestPathBinaryMatrix(grid2);
        System.out.println("Shortest path (no path): " + path2); // -1
        
        // Test Word Ladder
        System.out.println("\n🔤 TESTING WORD LADDER:");
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        int ladder1 = ladderLength("hit", "cog", wordList1);
        System.out.println("Word ladder length: " + ladder1); // 5
        
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        int ladder2 = ladderLength("hit", "cog", wordList2);
        System.out.println("Word ladder (no path): " + ladder2); // 0
        
        // Test Floyd-Warshall
        System.out.println("\n🔄 TESTING FLOYD-WARSHALL:");
        int[][] graph = {
            {0, 3, 0, 0, 0, 0, 0, 8, 0},
            {3, 0, 0, 0, 0, 0, 0, 11, 0},
            {0, 0, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        int[][] allPairs = floydWarshall(graph);
        System.out.println("Floyd-Warshall computed for 9x9 graph");
        System.out.println("Distance from 0 to 4: " + allPairs[0][4]);
        
        // Test Bellman-Ford
        System.out.println("\n⚡ TESTING BELLMAN-FORD:");
        int[][] edges = {{0,1,4},{0,2,1},{1,3,1},{2,1,2},{2,3,5},{3,4,3}};
        BellmanFordResult bf = bellmanFord(5, edges, 0);
        System.out.println("Bellman-Ford distances: " + Arrays.toString(bf.distances));
        System.out.println("Has negative cycle: " + bf.hasNegativeCycle);
        
        // Test A* Search
        System.out.println("\n⭐ TESTING A* SEARCH:");
        int[][] aStarGrid = {
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 0}
        };
        List<int[]> aStarPath = aStarSearch(aStarGrid, new int[]{0,0}, new int[]{3,3});
        System.out.println("A* path length: " + aStarPath.size());
        
        System.out.println("\n✅ Shortest Path Pattern Completed!");
        System.out.println("Key Principles:");
        System.out.println("1. Choose appropriate algorithm based on graph properties");
        System.out.println("2. Use BFS for unweighted graphs for optimal O(V+E) performance");
        System.out.println("3. Apply Dijkstra's for non-negative weighted graphs");
        System.out.println("4. Consider Bellman-Ford when negative weights are possible");
        System.out.println("5. Use Floyd-Warshall for all-pairs shortest paths in dense graphs");
        System.out.println("6. Implement A* search when good heuristics are available");
        System.out.println("7. Optimize with bidirectional search for long paths");
    }
} 