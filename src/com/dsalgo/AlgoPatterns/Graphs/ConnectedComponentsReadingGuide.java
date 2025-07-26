package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🕸️ FINDING CONNECTED COMPONENTS PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS FINDING CONNECTED COMPONENTS PATTERN?
 * ============================================================================
 * 
 * Finding Connected Components Pattern involves identifying distinct subgraphs
 * where all vertices are connected to each other by paths. This pattern is
 * fundamental for graph analysis, network partitioning, clustering algorithms,
 * and social network analysis where grouping related entities is essential
 * for understanding graph structure and relationships.
 * 
 * The pattern uses graph traversal algorithms (DFS/BFS) to systematically explore
 * connected regions, Union-Find data structures for efficient component tracking,
 * and various optimization techniques for large-scale graph processing. It's
 * crucial for problems involving network connectivity, island counting, friend
 * group identification, and distributed system component analysis.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. GRAPH TRAVERSAL: Use DFS/BFS to explore all reachable nodes from a starting point
 * 2. COMPONENT IDENTIFICATION: Group nodes that can reach each other through paths
 * 3. SYSTEMATIC EXPLORATION: Visit all nodes to ensure no components are missed
 * 4. EFFICIENT TRACKING: Use appropriate data structures for component management
 * 
 * 🕸️ CONNECTED COMPONENTS METAPHOR:
 * Think of connected components as "friendship circles" or "communication networks":
 * - Islands in water: separate land masses not connected by bridges
 * - Social groups: people connected through mutual friendships
 * - Network segments: devices that can communicate with each other
 * - Transportation systems: cities connected by roads or flights
 * 
 * ============================================================================
 * 🎯 WHEN TO USE FINDING CONNECTED COMPONENTS PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Counting distinct subgraphs or clusters in a network
 * - Finding groups of related entities (friends, similar items, connected regions)
 * - Network analysis and partitioning for distributed systems
 * - Image processing for object detection and region segmentation
 * - Social network analysis for community detection
 * - Graph coloring and vertex classification problems
 * - Connectivity analysis in transportation and communication networks
 * - Database clustering and data grouping applications
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Count number of islands/regions/groups"
 * - "Find connected components"
 * - "Group nodes based on connectivity"
 * - "Separate distinct subgraphs"
 * - "Count friend groups/circles"
 * - "Identify clusters in network"
 * - "Find reachable nodes"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need shortest paths between nodes (use shortest path algorithms)
 * - Looking for specific paths or cycles (use path finding algorithms)
 * - Need to maintain dynamic connectivity (use Union-Find with updates)
 * - Tree-specific operations (use tree algorithms)
 * 
 * ============================================================================
 * 🔄 CONNECTED COMPONENTS PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ BASIC COMPONENT COUNTING
 * - Count total number of connected components
 * - Simple DFS/BFS traversal with visited tracking
 * - Applications: Island counting, network partitioning
 * 
 * 2️⃣ COMPONENT SIZE ANALYSIS
 * - Find size of each connected component
 * - Track component sizes during traversal
 * - Applications: Largest cluster analysis, resource allocation
 * 
 * 3️⃣ COMPONENT LABELING
 * - Assign unique labels to each component
 * - Color or mark nodes by component membership
 * - Applications: Graph coloring, region segmentation
 * 
 * 4️⃣ UNION-FIND BASED DETECTION
 * - Efficient component tracking using disjoint sets
 * - Dynamic connectivity with union and find operations
 * - Applications: Kruskal's MST, dynamic graph connectivity
 * 
 * 5️⃣ MATRIX-BASED CONNECTIVITY
 * - 2D grid connectivity analysis
 * - 4-directional or 8-directional connections
 * - Applications: Image processing, maze analysis
 * 
 * 6️⃣ ADVANCED COMPONENT ANALYSIS
 * - Strongly connected components in directed graphs
 * - Biconnected components and articulation points
 * - Applications: Network robustness, critical path analysis
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 DFS-BASED COMPONENT DETECTION:
 * ```
 * Component Detection Algorithm:
 *   1. Initialize visited array to track explored nodes
 *   2. For each unvisited node:
 *      a. Start new component (increment count)
 *      b. Perform DFS to mark all reachable nodes
 *      c. Add all visited nodes to current component
 *   3. Return total component count and node assignments
 * 
 * Time: O(V + E), Space: O(V)
 * where V = vertices, E = edges
 * ```
 * 
 * 🔹 BFS-BASED COMPONENT DETECTION:
 * ```
 * BFS Component Algorithm:
 *   1. Use queue for level-by-level exploration
 *   2. For each unvisited node:
 *      a. Add to queue and mark as visited
 *      b. Process all neighbors level by level
 *      c. Continue until queue is empty
 *   3. Each BFS traversal finds one complete component
 * 
 * Time: O(V + E), Space: O(V)
 * ```
 * 
 * 🔹 UNION-FIND COMPONENT DETECTION:
 * ```
 * Union-Find Algorithm:
 *   1. Initialize each node as its own component
 *   2. For each edge (u, v):
 *      a. Find root of u and root of v
 *      b. If different roots, union the components
 *   3. Count distinct root nodes for total components
 * 
 * Time: O(E * α(V)), Space: O(V)
 * where α is inverse Ackermann function
 * ```
 * 
 * 🔹 MATRIX ISLAND COUNTING:
 * ```
 * Island Detection in 2D Grid:
 *   1. Scan grid cell by cell
 *   2. For each unvisited land cell:
 *      a. Increment island count
 *      b. DFS/BFS to mark entire island
 *      c. Explore 4 or 8 adjacent directions
 *   3. Return total island count
 * 
 * Time: O(m * n), Space: O(min(m, n)) for BFS queue
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: ANALYZE GRAPH REPRESENTATION
 * - Adjacency list vs adjacency matrix?
 * - Directed vs undirected graph?
 * - Dense vs sparse graph structure?
 * 
 * STEP 2: DETERMINE COMPONENT REQUIREMENTS
 * - Just count components or need component details?
 * - Static graph or dynamic updates needed?
 * - Memory constraints for large graphs?
 * 
 * STEP 3: CHOOSE TRAVERSAL STRATEGY
 * - DFS: Simple implementation, good for deep exploration
 * - BFS: Level-by-level, better for shortest path properties
 * - Union-Find: Efficient for dynamic connectivity queries
 * 
 * STEP 4: OPTIMIZE FOR SPECIFIC USE CASE
 * - Early termination if specific count reached?
 * - Parallel processing for large independent components?
 * - Memory optimization for very large graphs?
 * 
 * STEP 5: HANDLE EDGE CASES
 * - Empty graphs, single nodes, self-loops
 * - Disconnected graphs, complete graphs
 * - Invalid input validation and error handling
 * 
 * ============================================================================
 * 🎨 CONNECTED COMPONENTS ALGORITHM TEMPLATES
 * ============================================================================
 */

public class ConnectedComponentsReadingGuide {
    
    /**
     * 🏆 DFS-BASED COMPONENT DETECTION TEMPLATES
     */
    public static class DFSComponentDetection {
        
        /**
         * Basic Component Counting with DFS
         * Strategy: Systematic DFS traversal with visited tracking
         */
        public static int countConnectedComponents(int n, int[][] edges) {
            // Build adjacency list
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            
            boolean[] visited = new boolean[n];
            int componentCount = 0;
            
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(graph, i, visited);
                    componentCount++;
                }
            }
            
            return componentCount;
        }
        
        private static void dfs(List<List<Integer>> graph, int node, boolean[] visited) {
            visited[node] = true;
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    dfs(graph, neighbor, visited);
                }
            }
        }
        
        /**
         * Component Sizes Analysis
         * Strategy: Track size of each component during DFS
         */
        public static List<Integer> getComponentSizes(int n, int[][] edges) {
            List<List<Integer>> graph = buildGraph(n, edges);
            boolean[] visited = new boolean[n];
            List<Integer> componentSizes = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int size = dfsWithSize(graph, i, visited);
                    componentSizes.add(size);
                }
            }
            
            return componentSizes;
        }
        
        private static int dfsWithSize(List<List<Integer>> graph, int node, boolean[] visited) {
            visited[node] = true;
            int size = 1;
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    size += dfsWithSize(graph, neighbor, visited);
                }
            }
            
            return size;
        }
        
        private static List<List<Integer>> buildGraph(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            
            return graph;
        }
    }
    
    /**
     * 🌊 BFS-BASED COMPONENT DETECTION TEMPLATES
     */
    public static class BFSComponentDetection {
        
        /**
         * BFS Component Detection with Level Exploration
         * Strategy: Queue-based level-by-level exploration
         */
        public static int countComponentsBFS(int n, int[][] edges) {
            List<List<Integer>> graph = buildGraph(n, edges);
            boolean[] visited = new boolean[n];
            int componentCount = 0;
            
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    bfsComponent(graph, i, visited);
                    componentCount++;
                }
            }
            
            return componentCount;
        }
        
        private static void bfsComponent(List<List<Integer>> graph, int start, boolean[] visited) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            visited[start] = true;
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        /**
         * Component Labeling with BFS
         * Strategy: Assign unique labels to each component
         */
        public static int[] labelComponents(int n, int[][] edges) {
            List<List<Integer>> graph = buildGraph(n, edges);
            int[] componentLabels = new int[n];
            Arrays.fill(componentLabels, -1);
            int currentLabel = 0;
            
            for (int i = 0; i < n; i++) {
                if (componentLabels[i] == -1) {
                    bfsWithLabeling(graph, i, componentLabels, currentLabel++);
                }
            }
            
            return componentLabels;
        }
        
        private static void bfsWithLabeling(List<List<Integer>> graph, int start, 
                                          int[] labels, int label) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            labels[start] = label;
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                
                for (int neighbor : graph.get(node)) {
                    if (labels[neighbor] == -1) {
                        labels[neighbor] = label;
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        private static List<List<Integer>> buildGraph(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            
            return graph;
        }
    }
    
    /**
     * 🔗 UNION-FIND COMPONENT DETECTION TEMPLATES
     */
    public static class UnionFindComponentDetection {
        
        /**
         * Union-Find Data Structure for Component Detection
         * Strategy: Efficient disjoint set operations
         */
        public static class UnionFind {
            private int[] parent;
            private int[] rank;
            private int componentCount;
            
            public UnionFind(int n) {
                parent = new int[n];
                rank = new int[n];
                componentCount = n;
                
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    rank[i] = 0;
                }
            }
            
            public int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]); // Path compression
                }
                return parent[x];
            }
            
            public boolean union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                
                if (rootX == rootY) {
                    return false; // Already in same component
                }
                
                // Union by rank
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
            
            public boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }
        }
        
        public static int countComponentsUnionFind(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            
            for (int[] edge : edges) {
                uf.union(edge[0], edge[1]);
            }
            
            return uf.getComponentCount();
        }
    }
    
    /**
     * 🏝️ MATRIX-BASED ISLAND DETECTION TEMPLATES
     */
    public static class MatrixIslandDetection {
        
        /**
         * Classic Island Counting in 2D Grid
         * Strategy: DFS exploration of connected land cells
         */
        public static int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            
            int rows = grid.length;
            int cols = grid[0].length;
            int islandCount = 0;
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        islandCount++;
                        dfsIsland(grid, i, j);
                    }
                }
            }
            
            return islandCount;
        }
        
        private static void dfsIsland(char[][] grid, int row, int col) {
            // Boundary check and water check
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || 
                grid[row][col] == '0') {
                return;
            }
            
            // Mark as visited
            grid[row][col] = '0';
            
            // Explore 4 directions
            dfsIsland(grid, row + 1, col);
            dfsIsland(grid, row - 1, col);
            dfsIsland(grid, row, col + 1);
            dfsIsland(grid, row, col - 1);
        }
        
        /**
         * Island Detection with Size Tracking
         * Strategy: Track size of each island during exploration
         */
        public static List<Integer> getIslandSizes(char[][] grid) {
            if (grid == null || grid.length == 0) return new ArrayList<>();
            
            int rows = grid.length;
            int cols = grid[0].length;
            List<Integer> islandSizes = new ArrayList<>();
            boolean[][] visited = new boolean[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        int size = dfsIslandSize(grid, i, j, visited);
                        islandSizes.add(size);
                    }
                }
            }
            
            return islandSizes;
        }
        
        private static int dfsIslandSize(char[][] grid, int row, int col, boolean[][] visited) {
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||
                grid[row][col] == '0' || visited[row][col]) {
                return 0;
            }
            
            visited[row][col] = true;
            int size = 1;
            
            // Explore 4 directions and sum sizes
            size += dfsIslandSize(grid, row + 1, col, visited);
            size += dfsIslandSize(grid, row - 1, col, visited);
            size += dfsIslandSize(grid, row, col + 1, visited);
            size += dfsIslandSize(grid, row, col - 1, visited);
            
            return size;
        }
        
        /**
         * BFS Island Detection
         * Strategy: Queue-based exploration for iterative approach
         */
        public static int numIslandsBFS(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            
            int rows = grid.length;
            int cols = grid[0].length;
            int islandCount = 0;
            
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        islandCount++;
                        
                        // BFS to mark entire island
                        Queue<int[]> queue = new LinkedList<>();
                        queue.offer(new int[]{i, j});
                        grid[i][j] = '0';
                        
                        while (!queue.isEmpty()) {
                            int[] current = queue.poll();
                            int row = current[0];
                            int col = current[1];
                            
                            for (int[] dir : directions) {
                                int newRow = row + dir[0];
                                int newCol = col + dir[1];
                                
                                if (newRow >= 0 && newRow < rows && newCol >= 0 && 
                                    newCol < cols && grid[newRow][newCol] == '1') {
                                    grid[newRow][newCol] = '0';
                                    queue.offer(new int[]{newRow, newCol});
                                }
                            }
                        }
                    }
                }
            }
            
            return islandCount;
        }
    }
    
    /**
     * 🔧 ADVANCED COMPONENT ANALYSIS TEMPLATES
     */
    public static class AdvancedComponentAnalysis {
        
        /**
         * Largest Component Size
         * Strategy: Track maximum component size during traversal
         */
        public static int largestComponentSize(int n, int[][] edges) {
            List<List<Integer>> graph = buildGraph(n, edges);
            boolean[] visited = new boolean[n];
            int maxSize = 0;
            
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int size = dfsComponentSize(graph, i, visited);
                    maxSize = Math.max(maxSize, size);
                }
            }
            
            return maxSize;
        }
        
        private static int dfsComponentSize(List<List<Integer>> graph, int node, boolean[] visited) {
            visited[node] = true;
            int size = 1;
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    size += dfsComponentSize(graph, neighbor, visited);
                }
            }
            
            return size;
        }
        
        /**
         * Component Membership Query
         * Strategy: Efficient component lookup after preprocessing
         */
        public static class ComponentMembership {
            private int[] componentId;
            private int componentCount;
            
            public ComponentMembership(int n, int[][] edges) {
                List<List<Integer>> graph = buildGraph(n, edges);
                componentId = new int[n];
                Arrays.fill(componentId, -1);
                componentCount = 0;
                
                for (int i = 0; i < n; i++) {
                    if (componentId[i] == -1) {
                        dfsAssignComponent(graph, i, componentCount++);
                    }
                }
            }
            
            private void dfsAssignComponent(List<List<Integer>> graph, int node, int id) {
                componentId[node] = id;
                
                for (int neighbor : graph.get(node)) {
                    if (componentId[neighbor] == -1) {
                        dfsAssignComponent(graph, neighbor, id);
                    }
                }
            }
            
            public boolean areConnected(int u, int v) {
                return componentId[u] == componentId[v];
            }
            
            public int getComponentId(int node) {
                return componentId[node];
            }
            
            public int getComponentCount() {
                return componentCount;
            }
        }
        
        private static List<List<Integer>> buildGraph(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            
            return graph;
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Forgetting to Reset Visited State
     * Use separate visited tracking for each component search
     */
    public static void visitedStateExample(int n) {
        // Correct: Fresh visited array for each algorithm run
        boolean[] visited = new boolean[n];
        // Use and modify visited during single algorithm execution
        
        // Incorrect: Reusing visited without reset between algorithm calls
    }
    
    /**
     * ❌ PITFALL 2: Modifying Input During Traversal
     * Be careful when using input grid as visited marker
     */
    public static void inputModificationExample(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Correct: Use separate visited array
        boolean[][] visited = new boolean[rows][cols];
        
        // Alternative: Restore original values after modification
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char originalValue = grid[i][j];
                grid[i][j] = '0'; // Temporary modification
                // ... traversal ...
                grid[i][j] = originalValue; // Restore if needed
            }
        }
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Early Termination
     * Stop search when specific criteria are met
     */
    
    /**
     * 🎯 STRATEGY 2: Memory Optimization
     * Use space-efficient representations for large graphs
     */
    
    /**
     * 🎯 STRATEGY 3: Parallel Processing
     * Process independent components concurrently
     */
    
    // ============================================================================
    // 🧭 ALGORITHM SELECTION GUIDE
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * GRAPH CHARACTERISTICS:
     * ✅ Dense graphs → Adjacency matrix with DFS/BFS
     * ✅ Sparse graphs → Adjacency list with DFS/BFS
     * ✅ Dynamic connectivity → Union-Find with path compression
     * 
     * PERFORMANCE REQUIREMENTS:
     * ✅ One-time analysis → DFS/BFS traversal
     * ✅ Multiple connectivity queries → Union-Find preprocessing
     * ✅ Memory constrained → Iterative DFS or Union-Find
     * 
     * OUTPUT REQUIREMENTS:
     * ✅ Just count components → Simple traversal counting
     * ✅ Component details → Track sizes and memberships
     * ✅ Component labeling → Assign unique identifiers
     */
    
    public static void main(String[] args) {
        System.out.println("🕸️ FINDING CONNECTED COMPONENTS PATTERN - READING GUIDE");
        System.out.println("=======================================================");
        
        // Test basic component counting
        System.out.println("\n🔢 BASIC COMPONENT COUNTING:");
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        int count1 = DFSComponentDetection.countConnectedComponents(5, edges1);
        System.out.println("Components in graph with 5 nodes: " + count1); // 2
        
        // Test BFS component counting
        int count2 = BFSComponentDetection.countComponentsBFS(5, edges1);
        System.out.println("BFS component count: " + count2); // 2
        
        // Test Union-Find
        int count3 = UnionFindComponentDetection.countComponentsUnionFind(5, edges1);
        System.out.println("Union-Find component count: " + count3); // 2
        
        // Test island counting
        System.out.println("\n🏝️ ISLAND DETECTION:");
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        int islands = MatrixIslandDetection.numIslands(grid);
        System.out.println("Number of islands: " + islands); // Should be 3
        
        // Test component sizes
        System.out.println("\n📊 COMPONENT ANALYSIS:");
        List<Integer> sizes = DFSComponentDetection.getComponentSizes(5, edges1);
        System.out.println("Component sizes: " + sizes);
        
        int largestSize = AdvancedComponentAnalysis.largestComponentSize(5, edges1);
        System.out.println("Largest component size: " + largestSize);
        
        System.out.println("\n✅ Key Connected Components Principles:");
        System.out.println("1. Use DFS/BFS for systematic graph exploration");
        System.out.println("2. Track visited nodes to avoid infinite loops");
        System.out.println("3. Count components by counting traversal starts");
        System.out.println("4. Use Union-Find for dynamic connectivity queries");
        System.out.println("5. Consider memory vs time trade-offs for large graphs");
        System.out.println("6. Handle edge cases: empty graphs, single nodes");
        System.out.println("7. Choose appropriate algorithm based on use case");
    }
} 