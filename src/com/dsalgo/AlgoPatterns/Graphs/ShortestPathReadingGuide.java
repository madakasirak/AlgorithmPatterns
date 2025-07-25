package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🛣️ SHORTEST PATH FINDING PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS SHORTEST PATH FINDING PATTERN?
 * ============================================================================
 * 
 * Shortest Path Finding Pattern involves finding the minimum cost or distance
 * path between nodes in a graph. This pattern encompasses various algorithms
 * for different graph types and constraints, including unweighted graphs (BFS),
 * weighted graphs with non-negative weights (Dijkstra's), graphs with negative
 * weights (Bellman-Ford), and all-pairs shortest paths (Floyd-Warshall).
 * 
 * The pattern is fundamental for navigation systems, network routing, social
 * network analysis, game pathfinding, and optimization problems where finding
 * optimal routes or minimum costs is essential. It combines graph traversal
 * with priority-based selection and dynamic programming principles.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. OPTIMAL SUBSTRUCTURE: Shortest path has optimal subpaths
 * 2. GREEDY SELECTION: Choose locally optimal choices (Dijkstra's)
 * 3. RELAXATION: Update distances when better paths are found
 * 4. PRIORITY PROCESSING: Process nodes in order of current best distance
 * 
 * 🛣️ SHORTEST PATH METAPHOR:
 * Think of shortest path algorithms as "intelligent GPS navigation":
 * - Road network: graph with weighted edges representing distances/costs
 * - Traffic conditions: dynamic weights based on current conditions
 * - Route optimization: finding fastest/cheapest path to destination
 * - Real-time updates: adjusting paths when better routes are discovered
 * 
 * ============================================================================
 * 🎯 WHEN TO USE SHORTEST PATH FINDING PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Navigation and routing systems (GPS, maps, logistics)
 * - Network routing protocols and communication systems
 * - Game AI pathfinding and movement optimization
 * - Social network analysis (degrees of separation, influence paths)
 * - Resource allocation and supply chain optimization
 * - Circuit design and electronic routing problems
 * - Project scheduling with dependency constraints
 * - Financial arbitrage and trading path optimization
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find shortest/minimum path/distance/cost"
 * - "Optimal route between points"
 * - "Minimum travel time/distance"
 * - "Cheapest way to reach destination"
 * - "Network delay time"
 * - "Minimum steps/hops to target"
 * - "Path with minimum weight"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Just checking connectivity (use BFS/DFS)
 * - Finding any path, not shortest (use simple traversal)
 * - Tree structures only (use tree algorithms)
 * - Multiple targets equally good (use any traversal)
 * 
 * ============================================================================
 * 🔄 SHORTEST PATH PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ SINGLE-SOURCE SHORTEST PATH
 * - From one source to all destinations
 * - Dijkstra's algorithm for non-negative weights
 * - Bellman-Ford for graphs with negative weights
 * - BFS for unweighted graphs
 * 
 * 2️⃣ SINGLE-DESTINATION SHORTEST PATH
 * - From all sources to one destination
 * - Reverse graph and apply single-source algorithms
 * - Useful for delivery optimization problems
 * 
 * 3️⃣ SINGLE-PAIR SHORTEST PATH
 * - Between specific source and destination
 * - A* search with heuristics for optimization
 * - Early termination when destination reached
 * - Bidirectional search for better performance
 * 
 * 4️⃣ ALL-PAIRS SHORTEST PATH
 * - Between every pair of vertices
 * - Floyd-Warshall algorithm for dense graphs
 * - Johnson's algorithm for sparse graphs
 * - Matrix exponentiation for special cases
 * 
 * 5️⃣ CONSTRAINED SHORTEST PATH
 * - With additional constraints (K stops, time windows)
 * - Modified Dijkstra's with state expansion
 * - Dynamic programming approaches
 * - Multi-dimensional state spaces
 * 
 * 6️⃣ DYNAMIC SHORTEST PATH
 * - In graphs with changing weights
 * - Incremental algorithms for updates
 * - Real-time pathfinding systems
 * - Adaptive routing protocols
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 DIJKSTRA'S ALGORITHM (Non-negative weights):
 * ```
 * Dijkstra's Algorithm:
 *   1. Initialize distances: source = 0, others = infinity
 *   2. Use priority queue (min-heap) with (distance, node)
 *   3. While queue not empty:
 *      a. Extract node with minimum distance
 *      b. For each neighbor:
 *         - Calculate new distance through current node
 *         - If shorter, update distance and add to queue
 *   4. Result: shortest distances from source to all nodes
 * 
 * Time: O((V + E) log V), Space: O(V)
 * ```
 * 
 * 🔹 BFS FOR UNWEIGHTED GRAPHS:
 * ```
 * BFS Shortest Path:
 *   1. Use queue starting with source node
 *   2. Track distances and visited nodes
 *   3. For each node in queue:
 *      a. Explore all unvisited neighbors
 *      b. Set distance = current distance + 1
 *      c. Add neighbors to queue
 *   4. First time reaching destination = shortest path
 * 
 * Time: O(V + E), Space: O(V)
 * ```
 * 
 * 🔹 BELLMAN-FORD ALGORITHM (Negative weights):
 * ```
 * Bellman-Ford Algorithm:
 *   1. Initialize distances: source = 0, others = infinity
 *   2. Repeat V-1 times:
 *      a. For each edge (u, v, weight):
 *         - If dist[u] + weight < dist[v]:
 *           - Update dist[v] = dist[u] + weight
 *   3. Check for negative cycles (one more iteration)
 *   4. Result: shortest distances or negative cycle detection
 * 
 * Time: O(VE), Space: O(V)
 * ```
 * 
 * 🔹 FLOYD-WARSHALL ALGORITHM (All pairs):
 * ```
 * Floyd-Warshall Algorithm:
 *   1. Initialize distance matrix from adjacency matrix
 *   2. For k from 0 to V-1:
 *      a. For i from 0 to V-1:
 *         b. For j from 0 to V-1:
 *            - If dist[i][k] + dist[k][j] < dist[i][j]:
 *              - Update dist[i][j] = dist[i][k] + dist[k][j]
 *   3. Result: shortest distances between all pairs
 * 
 * Time: O(V³), Space: O(V²)
 * ```
 * 
 * 🔹 A* SEARCH ALGORITHM (Heuristic-guided):
 * ```
 * A* Algorithm:
 *   1. Use priority queue with f(n) = g(n) + h(n)
 *      - g(n): actual distance from start
 *      - h(n): heuristic estimate to goal
 *   2. Maintain open set (frontier) and closed set (visited)
 *   3. While open set not empty:
 *      a. Select node with lowest f(n)
 *      b. If goal reached, reconstruct path
 *      c. Move to closed set, explore neighbors
 *   4. Result: optimal path with good heuristic
 * 
 * Time: O(b^d) where b=branching factor, d=depth
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: ANALYZE GRAPH PROPERTIES
 * - Weighted vs unweighted edges?
 * - Non-negative vs potentially negative weights?
 * - Dense vs sparse graph structure?
 * - Static vs dynamic graph changes?
 * 
 * STEP 2: DETERMINE PATH REQUIREMENTS
 * - Single source to all destinations?
 * - Specific source to specific destination?
 * - All pairs shortest paths needed?
 * - Path reconstruction required?
 * 
 * STEP 3: CONSIDER PERFORMANCE CONSTRAINTS
 * - Real-time requirements vs preprocessing acceptable?
 * - Memory limitations for large graphs?
 * - Frequency of shortest path queries?
 * 
 * STEP 4: CHOOSE APPROPRIATE ALGORITHM
 * - BFS: Unweighted graphs, simple implementation
 * - Dijkstra's: Non-negative weights, single source
 * - Bellman-Ford: Negative weights, cycle detection
 * - Floyd-Warshall: All pairs, dense graphs
 * - A*: Single pair with good heuristic available
 * 
 * STEP 5: OPTIMIZE FOR SPECIFIC USE CASE
 * - Early termination for single destination
 * - Bidirectional search for long paths
 * - Precomputation for frequent queries
 * - Approximate algorithms for very large graphs
 * 
 * ============================================================================
 * 🎨 SHORTEST PATH ALGORITHM TEMPLATES
 * ============================================================================
 */

public class ShortestPathReadingGuide {
    
    /**
     * 🚀 DIJKSTRA'S ALGORITHM TEMPLATES
     */
    public static class DijkstraAlgorithm {
        
        /**
         * Classic Dijkstra's Algorithm Implementation
         * Strategy: Priority queue with distance tracking
         */
        public static int[] dijkstra(List<List<int[]>> graph, int source) {
            int n = graph.size();
            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;
            
            // Priority queue: (distance, node)
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            pq.offer(new int[]{0, source});
            
            boolean[] visited = new boolean[n];
            
            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int dist = current[0];
                int node = current[1];
                
                if (visited[node]) continue;
                visited[node] = true;
                
                // Explore neighbors
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int weight = neighbor[1];
                    
                    if (!visited[nextNode]) {
                        int newDist = dist + weight;
                        if (newDist < distances[nextNode]) {
                            distances[nextNode] = newDist;
                            pq.offer(new int[]{newDist, nextNode});
                        }
                    }
                }
            }
            
            return distances;
        }
        
        /**
         * Dijkstra with Path Reconstruction
         * Strategy: Track predecessors for path recovery
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
            
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            pq.offer(new int[]{0, source});
            boolean[] visited = new boolean[n];
            
            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int dist = current[0];
                int node = current[1];
                
                if (visited[node]) continue;
                visited[node] = true;
                
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int weight = neighbor[1];
                    
                    if (!visited[nextNode]) {
                        int newDist = dist + weight;
                        if (newDist < distances[nextNode]) {
                            distances[nextNode] = newDist;
                            predecessors[nextNode] = node;
                            pq.offer(new int[]{newDist, nextNode});
                        }
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
    }
    
    /**
     * 🌊 BFS SHORTEST PATH TEMPLATES
     */
    public static class BFSShortestPath {
        
        /**
         * BFS for Unweighted Graph Shortest Path
         * Strategy: Level-by-level exploration guarantees shortest path
         */
        public static int[] bfsShortestPath(List<List<Integer>> graph, int source) {
            int n = graph.size();
            int[] distances = new int[n];
            Arrays.fill(distances, -1);
            distances[source] = 0;
            
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                
                for (int neighbor : graph.get(node)) {
                    if (distances[neighbor] == -1) {
                        distances[neighbor] = distances[node] + 1;
                        queue.offer(neighbor);
                    }
                }
            }
            
            return distances;
        }
        
        /**
         * BFS with Path Reconstruction
         * Strategy: Track predecessors during BFS traversal
         */
        public static class BFSResult {
            public int[] distances;
            public int[] predecessors;
            
            BFSResult(int[] distances, int[] predecessors) {
                this.distances = distances;
                this.predecessors = predecessors;
            }
        }
        
        public static BFSResult bfsWithPath(List<List<Integer>> graph, int source) {
            int n = graph.size();
            int[] distances = new int[n];
            int[] predecessors = new int[n];
            Arrays.fill(distances, -1);
            Arrays.fill(predecessors, -1);
            distances[source] = 0;
            
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                
                for (int neighbor : graph.get(node)) {
                    if (distances[neighbor] == -1) {
                        distances[neighbor] = distances[node] + 1;
                        predecessors[neighbor] = node;
                        queue.offer(neighbor);
                    }
                }
            }
            
            return new BFSResult(distances, predecessors);
        }
        
        /**
         * Matrix BFS for Grid Problems
         * Strategy: 2D BFS with direction vectors
         */
        public static int shortestPathBinaryMatrix(int[][] grid) {
            int n = grid.length;
            if (grid[0][0] != 0 || grid[n-1][n-1] != 0) return -1;
            
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
                
                if (row == n-1 && col == n-1) return dist;
                
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] == 0 && !visited[newRow][newCol]) {
                        visited[newRow][newCol] = true;
                        queue.offer(new int[]{newRow, newCol, dist + 1});
                    }
                }
            }
            
            return -1;
        }
    }
    
    /**
     * ⚡ BELLMAN-FORD ALGORITHM TEMPLATES
     */
    public static class BellmanFordAlgorithm {
        
        /**
         * Bellman-Ford Algorithm with Negative Cycle Detection
         * Strategy: Relaxation with cycle detection
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
    }
    
    /**
     * 🔄 FLOYD-WARSHALL ALGORITHM TEMPLATES
     */
    public static class FloydWarshallAlgorithm {
        
        /**
         * Floyd-Warshall All-Pairs Shortest Path
         * Strategy: Dynamic programming with intermediate vertices
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
            
            // Main Floyd-Warshall algorithm
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
         * Floyd-Warshall with Path Reconstruction
         * Strategy: Track next node for path reconstruction
         */
        public static class FloydWarshallResult {
            public int[][] distances;
            public int[][] next;
            
            FloydWarshallResult(int[][] distances, int[][] next) {
                this.distances = distances;
                this.next = next;
            }
        }
        
        public static FloydWarshallResult floydWarshallWithPath(int[][] graph) {
            int n = graph.length;
            int[][] dist = new int[n][n];
            int[][] next = new int[n][n];
            
            // Initialize
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                        next[i][j] = j;
                    } else if (graph[i][j] != 0) {
                        dist[i][j] = graph[i][j];
                        next[i][j] = j;
                    } else {
                        dist[i][j] = Integer.MAX_VALUE;
                        next[i][j] = -1;
                    }
                }
            }
            
            // Floyd-Warshall with path tracking
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[i][k] != Integer.MAX_VALUE && 
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
            
            return new FloydWarshallResult(dist, next);
        }
        
        public static List<Integer> reconstructPath(int[][] next, int source, int destination) {
            List<Integer> path = new ArrayList<>();
            if (next[source][destination] == -1) return path;
            
            int current = source;
            while (current != destination) {
                path.add(current);
                current = next[current][destination];
            }
            path.add(destination);
            
            return path;
        }
    }
    
    /**
     * ⭐ A* SEARCH ALGORITHM TEMPLATES
     */
    public static class AStarAlgorithm {
        
        static class AStarNode {
            int node;
            int gScore; // Actual distance from start
            int fScore; // gScore + heuristic
            
            AStarNode(int node, int gScore, int fScore) {
                this.node = node;
                this.gScore = gScore;
                this.fScore = fScore;
            }
        }
        
        /**
         * A* Search Implementation
         * Strategy: Heuristic-guided search for optimal pathfinding
         */
        public static List<Integer> aStar(List<List<int[]>> graph, int start, int goal, 
                                        Function<Integer, Integer> heuristic) {
            PriorityQueue<AStarNode> openSet = new PriorityQueue<>((a, b) -> a.fScore - b.fScore);
            Set<Integer> closedSet = new HashSet<>();
            Map<Integer, Integer> gScore = new HashMap<>();
            Map<Integer, Integer> parent = new HashMap<>();
            
            gScore.put(start, 0);
            openSet.offer(new AStarNode(start, 0, heuristic.apply(start)));
            
            while (!openSet.isEmpty()) {
                AStarNode current = openSet.poll();
                
                if (current.node == goal) {
                    return reconstructAStarPath(parent, start, goal);
                }
                
                closedSet.add(current.node);
                
                for (int[] neighbor : graph.get(current.node)) {
                    int nextNode = neighbor[0];
                    int weight = neighbor[1];
                    
                    if (closedSet.contains(nextNode)) continue;
                    
                    int tentativeG = current.gScore + weight;
                    
                    if (!gScore.containsKey(nextNode) || tentativeG < gScore.get(nextNode)) {
                        parent.put(nextNode, current.node);
                        gScore.put(nextNode, tentativeG);
                        int fScore = tentativeG + heuristic.apply(nextNode);
                        openSet.offer(new AStarNode(nextNode, tentativeG, fScore));
                    }
                }
            }
            
            return new ArrayList<>(); // No path found
        }
        
        private static List<Integer> reconstructAStarPath(Map<Integer, Integer> parent, 
                                                        int start, int goal) {
            List<Integer> path = new ArrayList<>();
            int current = goal;
            
            while (current != start) {
                path.add(current);
                current = parent.get(current);
            }
            path.add(start);
            
            Collections.reverse(path);
            return path;
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Integer Overflow in Distance Calculation
     * Use appropriate data types and check for overflow
     */
    public static void overflowExample() {
        // Correct: Check for overflow before addition
        int newDist = current + weight;
        if (current != Integer.MAX_VALUE && newDist < distances[neighbor]) {
            distances[neighbor] = newDist;
        }
        
        // Incorrect: Can cause overflow
        // if (distances[current] + weight < distances[neighbor])
    }
    
    /**
     * ❌ PITFALL 2: Processing Already Visited Nodes in Dijkstra
     * Skip nodes that have been optimally processed
     */
    public static void visitedCheckExample() {
        // Correct: Check if already processed
        if (visited[node]) continue;
        visited[node] = true;
        
        // Incorrect: Processing same node multiple times
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Early Termination
     * Stop when destination is reached in single-pair problems
     */
    
    /**
     * 🎯 STRATEGY 2: Bidirectional Search
     * Search from both source and destination simultaneously
     */
    
    /**
     * 🎯 STRATEGY 3: Heuristic Optimization
     * Use domain knowledge to guide search direction
     */
    
    // ============================================================================
    // 🧭 ALGORITHM SELECTION GUIDE
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * GRAPH TYPE:
     * ✅ Unweighted → BFS (O(V + E))
     * ✅ Non-negative weights → Dijkstra's (O((V + E) log V))
     * ✅ Negative weights → Bellman-Ford (O(VE))
     * ✅ Dense graph, all pairs → Floyd-Warshall (O(V³))
     * 
     * PERFORMANCE REQUIREMENTS:
     * ✅ Single query → Direct algorithm application
     * ✅ Multiple queries → Precompute all-pairs or use faster algorithms
     * ✅ Real-time → A* with good heuristic
     * ✅ Approximate solutions → Bidirectional or heuristic methods
     */
    
    public static void main(String[] args) {
        System.out.println("🛣️ SHORTEST PATH FINDING PATTERN - READING GUIDE");
        System.out.println("================================================");
        
        // Create sample graph: adjacency list format [neighbor, weight]
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Add edges: 0->1(4), 0->2(1), 1->3(1), 2->1(2), 2->3(5), 3->4(3)
        graph.get(0).add(new int[]{1, 4});
        graph.get(0).add(new int[]{2, 1});
        graph.get(1).add(new int[]{3, 1});
        graph.get(2).add(new int[]{1, 2});
        graph.get(2).add(new int[]{3, 5});
        graph.get(3).add(new int[]{4, 3});
        
        // Test Dijkstra's Algorithm
        System.out.println("\n🚀 TESTING DIJKSTRA'S ALGORITHM:");
        int[] distances = DijkstraAlgorithm.dijkstra(graph, 0);
        System.out.println("Distances from node 0: " + Arrays.toString(distances));
        
        // Test Dijkstra with path reconstruction
        DijkstraAlgorithm.DijkstraResult result = DijkstraAlgorithm.dijkstraWithPath(graph, 0);
        List<Integer> path = DijkstraAlgorithm.reconstructPath(result.predecessors, 0, 4);
        System.out.println("Shortest path from 0 to 4: " + path);
        System.out.println("Distance: " + result.distances[4]);
        
        // Test BFS for unweighted graph
        System.out.println("\n🌊 TESTING BFS SHORTEST PATH:");
        List<List<Integer>> unweightedGraph = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            unweightedGraph.add(new ArrayList<>());
        }
        unweightedGraph.get(0).add(1); unweightedGraph.get(0).add(2);
        unweightedGraph.get(1).add(3); unweightedGraph.get(2).add(3);
        unweightedGraph.get(3).add(4);
        
        int[] bfsDistances = BFSShortestPath.bfsShortestPath(unweightedGraph, 0);
        System.out.println("BFS distances from node 0: " + Arrays.toString(bfsDistances));
        
        // Test Bellman-Ford
        System.out.println("\n⚡ TESTING BELLMAN-FORD:");
        int[][] edges = {{0,1,4}, {0,2,1}, {1,3,1}, {2,1,2}, {2,3,5}, {3,4,3}};
        BellmanFordAlgorithm.BellmanFordResult bfResult = 
            BellmanFordAlgorithm.bellmanFord(5, edges, 0);
        System.out.println("Bellman-Ford distances: " + Arrays.toString(bfResult.distances));
        System.out.println("Has negative cycle: " + bfResult.hasNegativeCycle);
        
        // Test Floyd-Warshall
        System.out.println("\n🔄 TESTING FLOYD-WARSHALL:");
        int[][] adjacencyMatrix = {
            {0, 4, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 2, 0, 5, 0},
            {0, 0, 0, 0, 3},
            {0, 0, 0, 0, 0}
        };
        int[][] allPairDistances = FloydWarshallAlgorithm.floydWarshall(adjacencyMatrix);
        System.out.println("All-pairs shortest distances:");
        for (int[] row : allPairDistances) {
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println("\n✅ Key Shortest Path Principles:");
        System.out.println("1. Choose algorithm based on graph properties and requirements");
        System.out.println("2. Use BFS for unweighted graphs for optimal performance");
        System.out.println("3. Apply Dijkstra's for non-negative weighted graphs");
        System.out.println("4. Use Bellman-Ford when negative weights are possible");
        System.out.println("5. Consider Floyd-Warshall for all-pairs in dense graphs");
        System.out.println("6. Implement early termination for single-destination problems");
        System.out.println("7. Handle edge cases: disconnected graphs, overflow, cycles");
    }
    
    // Java 8 Function interface simulation for older versions
    interface Function<T, R> {
        R apply(T t);
    }
} 