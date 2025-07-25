package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🕸️ FINDING CONNECTED COMPONENTS PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of connected components algorithms
 * for graph analysis, network partitioning, island counting, and social network
 * clustering. These algorithms demonstrate DFS/BFS traversal techniques, Union-Find
 * data structures, and optimization strategies for identifying distinct subgraphs
 * where all vertices are connected through paths.
 * 
 * Pattern Focus: Graph traversal, component identification, connectivity analysis
 * Time Complexity: Generally O(V + E) for DFS/BFS, O(E * α(V)) for Union-Find
 * Space Complexity: O(V) for visited tracking, O(V) for Union-Find parent arrays
 */

public class ConnectedComponents {
    
    /**
     * Number of Islands - Classic Connected Components Problem
     * 
     * Given an m x n 2D binary grid which represents a map of '1's (land) 
     * and '0's (water), return the number of islands. An island is surrounded 
     * by water and is formed by connecting adjacent lands horizontally or vertically.
     * 
     * Strategy: DFS traversal to mark connected land cells as visited
     * Time: O(m * n) where m = rows, n = columns
     * Space: O(min(m, n)) for recursion stack in worst case
     * 
     * LeetCode: https://leetcode.com/problems/number-of-islands/
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
                    dfsMarkIsland(grid, i, j);
                }
            }
        }
        
        return islandCount;
    }
    
    private static void dfsMarkIsland(char[][] grid, int row, int col) {
        // Boundary and water checks
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || 
            grid[row][col] == '0') {
            return;
        }
        
        // Mark current cell as visited (water)
        grid[row][col] = '0';
        
        // Explore 4 directions
        dfsMarkIsland(grid, row + 1, col);
        dfsMarkIsland(grid, row - 1, col);
        dfsMarkIsland(grid, row, col + 1);
        dfsMarkIsland(grid, row, col - 1);
    }
    
    /**
     * Alternative Number of Islands - BFS Implementation
     * Strategy: Queue-based iterative exploration
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
    
    /**
     * Number of Connected Components in an Undirected Graph
     * 
     * You have a graph of n nodes labeled from 0 to n - 1. You are given an 
     * integer n and a list of edges where edges[i] = [ai, bi] indicates that 
     * there is an undirected edge between nodes ai and bi in the graph.
     * Return the number of connected components in the graph.
     * 
     * Strategy: DFS traversal with adjacency list representation
     * Time: O(V + E) where V = vertices, E = edges
     * Space: O(V + E) for adjacency list and visited array
     * 
     * LeetCode: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
     */
    public static int countComponents(int n, int[][] edges) {
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
                dfsTraversal(graph, i, visited);
                componentCount++;
            }
        }
        
        return componentCount;
    }
    
    private static void dfsTraversal(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsTraversal(graph, neighbor, visited);
            }
        }
    }
    
    /**
     * Alternative Connected Components - Union-Find Implementation
     * Strategy: Efficient disjoint set operations with path compression
     */
    public static int countComponentsUnionFind(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        
        return uf.getComponentCount();
    }
    
    /**
     * Union-Find Data Structure with Path Compression and Union by Rank
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
            
            // Union by rank for better tree balance
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
    
    /**
     * BONUS: Largest Component Size
     * Find the size of the largest connected component in the graph
     * 
     * Strategy: Track component sizes during DFS traversal
     * Time: O(V + E), Space: O(V)
     */
    public static int largestComponentSize(int n, int[][] edges) {
        List<List<Integer>> graph = buildAdjacencyList(n, edges);
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
     * BONUS: Component Sizes Analysis
     * Get the sizes of all connected components
     * 
     * Strategy: Track each component size during traversal
     * Time: O(V + E), Space: O(V)
     */
    public static List<Integer> getComponentSizes(int n, int[][] edges) {
        List<List<Integer>> graph = buildAdjacencyList(n, edges);
        boolean[] visited = new boolean[n];
        List<Integer> componentSizes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = dfsComponentSize(graph, i, visited);
                componentSizes.add(size);
            }
        }
        
        return componentSizes;
    }
    
    /**
     * BONUS: Component Labeling
     * Assign unique labels to each connected component
     * 
     * Strategy: BFS with component ID assignment
     * Time: O(V + E), Space: O(V)
     */
    public static int[] labelComponents(int n, int[][] edges) {
        List<List<Integer>> graph = buildAdjacencyList(n, edges);
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
    
    /**
     * BONUS: Maximum Island Area
     * Find the area of the largest island in a 2D grid
     * 
     * Strategy: DFS with area calculation
     * Time: O(m * n), Space: O(m * n) for recursion
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int area = dfsIslandArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    private static int dfsIslandArea(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || 
            grid[row][col] == 0) {
            return 0;
        }
        
        grid[row][col] = 0; // Mark as visited
        
        // Calculate area including current cell and all connected cells
        return 1 + dfsIslandArea(grid, row + 1, col) +
                   dfsIslandArea(grid, row - 1, col) +
                   dfsIslandArea(grid, row, col + 1) +
                   dfsIslandArea(grid, row, col - 1);
    }
    
    /**
     * BONUS: Friend Circles (Social Network Components)
     * Count the number of friend circles in a social network
     * 
     * Strategy: DFS on adjacency matrix representation
     * Time: O(n²), Space: O(n)
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int circleCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsFriendCircle(isConnected, i, visited);
                circleCount++;
            }
        }
        
        return circleCount;
    }
    
    private static void dfsFriendCircle(int[][] isConnected, int person, boolean[] visited) {
        visited[person] = true;
        
        for (int friend = 0; friend < isConnected.length; friend++) {
            if (isConnected[person][friend] == 1 && !visited[friend]) {
                dfsFriendCircle(isConnected, friend, visited);
            }
        }
    }
    
    /**
     * BONUS: Number of Islands II (Dynamic Island Addition)
     * Count islands after each land addition operation
     * 
     * Strategy: Union-Find with dynamic updates
     * Time: O(k * α(mn)) where k = number of operations
     * Space: O(mn)
     */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (positions == null || positions.length == 0) return result;
        
        UnionFind2D uf = new UnionFind2D(m, n);
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        for (int[] pos : positions) {
            int row = pos[0];
            int col = pos[1];
            
            if (uf.isLand(row, col)) {
                result.add(uf.getIslandCount());
                continue; // Already land
            }
            
            uf.addLand(row, col);
            
            // Connect to adjacent lands
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && 
                    uf.isLand(newRow, newCol)) {
                    uf.union(row * n + col, newRow * n + newCol);
                }
            }
            
            result.add(uf.getIslandCount());
        }
        
        return result;
    }
    
    /**
     * 2D Union-Find for Dynamic Island Management
     */
    public static class UnionFind2D {
        private int[] parent;
        private int[] rank;
        private boolean[] isLand;
        private int islandCount;
        private int rows, cols;
        
        public UnionFind2D(int m, int n) {
            this.rows = m;
            this.cols = n;
            int size = m * n;
            parent = new int[size];
            rank = new int[size];
            isLand = new boolean[size];
            islandCount = 0;
            
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
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
                islandCount--;
            }
        }
        
        public void addLand(int row, int col) {
            int index = row * cols + col;
            if (!isLand[index]) {
                isLand[index] = true;
                islandCount++;
            }
        }
        
        public boolean isLand(int row, int col) {
            if (row < 0 || row >= rows || col < 0 || col >= cols) return false;
            return isLand[row * cols + col];
        }
        
        public int getIslandCount() {
            return islandCount;
        }
    }
    
    // Helper method to build adjacency list
    private static List<List<Integer>> buildAdjacencyList(int n, int[][] edges) {
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
    
    public static void main(String[] args) {
        System.out.println("🕸️ FINDING CONNECTED COMPONENTS - PRACTICE IMPLEMENTATIONS");
        System.out.println("=========================================================");
        
        // Test Number of Islands
        System.out.println("\n🏝️ TESTING NUMBER OF ISLANDS:");
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        char[][] grid1Copy = copyGrid(grid1);
        int islands1 = numIslands(grid1);
        System.out.println("DFS Islands count: " + islands1); // 1
        
        int islands2 = numIslandsBFS(grid1Copy);
        System.out.println("BFS Islands count: " + islands2); // 1
        
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        int islands3 = numIslands(grid2);
        System.out.println("Multiple islands count: " + islands3); // 3
        
        // Test Connected Components in Graph
        System.out.println("\n🔗 TESTING CONNECTED COMPONENTS:");
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        int components1 = countComponents(5, edges1);
        System.out.println("DFS Components count: " + components1); // 2
        
        int components2 = countComponentsUnionFind(5, edges1);
        System.out.println("Union-Find Components count: " + components2); // 2
        
        // Test Component Analysis
        System.out.println("\n📊 TESTING COMPONENT ANALYSIS:");
        List<Integer> sizes = getComponentSizes(5, edges1);
        System.out.println("Component sizes: " + sizes); // [3, 2]
        
        int largestSize = largestComponentSize(5, edges1);
        System.out.println("Largest component size: " + largestSize); // 3
        
        int[] labels = labelComponents(5, edges1);
        System.out.println("Component labels: " + Arrays.toString(labels)); // [0, 0, 0, 1, 1]
        
        // Test Maximum Island Area
        System.out.println("\n🏞️ TESTING MAXIMUM ISLAND AREA:");
        int[][] grid3 = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int maxArea = maxAreaOfIsland(grid3);
        System.out.println("Maximum island area: " + maxArea); // 6
        
        // Test Friend Circles
        System.out.println("\n👥 TESTING FRIEND CIRCLES:");
        int[][] friendships = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        int circles = findCircleNum(friendships);
        System.out.println("Number of friend circles: " + circles); // 2
        
        // Test Dynamic Islands
        System.out.println("\n🌊 TESTING DYNAMIC ISLANDS:");
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        List<Integer> dynamicIslands = numIslands2(3, 3, positions);
        System.out.println("Islands after each addition: " + dynamicIslands); // [1, 1, 2, 3]
        
        System.out.println("\n✅ Connected Components Pattern Completed!");
        System.out.println("Key Principles:");
        System.out.println("1. Use DFS/BFS for systematic graph exploration");
        System.out.println("2. Track visited nodes to avoid cycles and infinite loops");
        System.out.println("3. Count components by counting independent traversal starts");
        System.out.println("4. Use Union-Find for efficient dynamic connectivity queries");
        System.out.println("5. Consider memory vs time trade-offs for large graphs");
        System.out.println("6. Handle matrix problems as implicit graph representations");
        System.out.println("7. Optimize with appropriate data structures for specific use cases");
    }
    
    // Helper method to copy 2D char array
    private static char[][] copyGrid(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
} 