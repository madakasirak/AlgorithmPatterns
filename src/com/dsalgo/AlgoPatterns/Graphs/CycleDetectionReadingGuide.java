package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🔄 CYCLE DETECTION PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS CYCLE DETECTION PATTERN?
 * ============================================================================
 * 
 * Cycle Detection Pattern involves identifying cycles in graphs to ensure
 * acyclicity, detect dependencies, validate topological ordering, and solve
 * scheduling problems. This pattern is crucial for deadlock detection, course
 * prerequisite validation, dependency resolution, and system design where
 * circular dependencies must be avoided or detected.
 * 
 * The pattern employs different strategies for directed vs undirected graphs,
 * uses DFS with state tracking (white/gray/black coloring), Union-Find for
 * undirected graphs, and topological sorting verification. It's essential
 * for many real-world applications involving precedence constraints.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. BACK EDGE DETECTION: Identify edges that create cycles in DFS traversal
 * 2. STATE TRACKING: Use three-color system (white/gray/black) for directed graphs
 * 3. PARENT TRACKING: Avoid trivial cycles in undirected graphs
 * 4. TOPOLOGICAL VALIDATION: Use in-degree counting for cycle detection
 * 
 * 🔄 CYCLE DETECTION METAPHOR:
 * Think of cycle detection as "dependency validation systems":
 * - Course prerequisites: ensure no circular requirements
 * - Build dependencies: detect circular imports/includes
 * - Task scheduling: identify impossible scheduling due to cycles
 * - Resource allocation: prevent deadlock situations
 * 
 * ============================================================================
 * 🎯 WHEN TO USE CYCLE DETECTION PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Course scheduling and prerequisite validation systems
 * - Build system dependency resolution and circular import detection
 * - Deadlock detection in operating systems and databases
 * - Topological sorting validation and DAG verification
 * - Compiler dependency analysis and symbol resolution
 * - Project management with task dependencies
 * - Financial transaction cycle detection and fraud prevention
 * - Social network analysis for relationship loops
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Detect cycles in graph"
 * - "Check if graph is acyclic/DAG"
 * - "Course schedule prerequisites"
 * - "Dependency resolution"
 * - "Circular dependencies"
 * - "Deadlock detection"
 * - "Topological order possible"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Tree structures only (trees are acyclic by definition)
 * - Simple connectivity checking (use traversal algorithms)
 * - Path finding without cycle concern (use shortest path algorithms)
 * - Static analysis without dynamic updates (precompute once)
 * 
 * ============================================================================
 * 🔄 CYCLE DETECTION PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ DIRECTED GRAPH CYCLE DETECTION
 * - DFS with three-color state tracking (white/gray/black)
 * - Back edge detection during DFS traversal
 * - Topological sorting with in-degree counting
 * - Strongly connected component analysis
 * 
 * 2️⃣ UNDIRECTED GRAPH CYCLE DETECTION
 * - DFS with parent tracking to avoid trivial cycles
 * - Union-Find with cycle detection during edge addition
 * - BFS-based cycle detection with level tracking
 * - Spanning tree construction with cycle avoidance
 * 
 * 3️⃣ FUNCTIONAL DEPENDENCY CYCLES
 * - Recursive function call cycle detection
 * - Module/package circular dependency detection
 * - Variable definition cycle detection
 * - Template instantiation cycle detection
 * 
 * 4️⃣ SCHEDULING AND PRECEDENCE CYCLES
 * - Task dependency cycle detection
 * - Course prerequisite cycle validation
 * - Build order dependency resolution
 * - Resource allocation deadlock detection
 * 
 * 5️⃣ DYNAMIC CYCLE DETECTION
 * - Incremental cycle detection with edge addition
 * - Cycle detection in evolving graphs
 * - Real-time dependency validation
 * - Streaming graph cycle detection
 * 
 * 6️⃣ SPECIALIZED CYCLE DETECTION
 * - Negative cycle detection in weighted graphs
 * - Simple cycle enumeration and counting
 * - Minimum cycle basis computation
 * - Cycle detection with path reconstruction
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 DFS CYCLE DETECTION (DIRECTED GRAPH):
 * ```
 * Three-Color DFS Algorithm:
 *   WHITE: Unvisited node
 *   GRAY: Currently being processed (in DFS stack)
 *   BLACK: Completely processed
 * 
 *   For each unvisited node:
 *     1. Mark node as GRAY (being processed)
 *     2. For each neighbor:
 *        - If neighbor is GRAY: cycle detected (back edge)
 *        - If neighbor is WHITE: recursively visit
 *     3. Mark node as BLACK (completely processed)
 * 
 * Time: O(V + E), Space: O(V)
 * ```
 * 
 * 🔹 DFS CYCLE DETECTION (UNDIRECTED GRAPH):
 * ```
 * Parent-Tracking DFS Algorithm:
 *   For each unvisited node:
 *     1. Mark node as visited
 *     2. For each neighbor:
 *        - If neighbor is visited and not parent: cycle detected
 *        - If neighbor is unvisited: recursively visit with current as parent
 * 
 * Time: O(V + E), Space: O(V)
 * ```
 * 
 * 🔹 UNION-FIND CYCLE DETECTION (UNDIRECTED):
 * ```
 * Union-Find Algorithm:
 *   For each edge (u, v):
 *     1. Find root of u and root of v
 *     2. If same root: cycle detected
 *     3. Otherwise: union u and v
 * 
 * Time: O(E * α(V)), Space: O(V)
 * where α is inverse Ackermann function
 * ```
 * 
 * 🔹 TOPOLOGICAL SORT CYCLE DETECTION:
 * ```
 * Kahn's Algorithm for Cycle Detection:
 *   1. Calculate in-degree for all vertices
 *   2. Add vertices with in-degree 0 to queue
 *   3. While queue not empty:
 *      a. Remove vertex and decrement neighbors' in-degrees
 *      b. Add vertices with in-degree 0 to queue
 *   4. If processed vertices < total vertices: cycle exists
 * 
 * Time: O(V + E), Space: O(V)
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY GRAPH TYPE
 * - Directed vs undirected graph?
 * - Sparse vs dense graph structure?
 * - Static vs dynamic graph updates?
 * 
 * STEP 2: DETERMINE DETECTION REQUIREMENTS
 * - Just detect cycle existence?
 * - Need to find actual cycle?
 * - Multiple cycle detection needed?
 * - Real-time detection vs batch processing?
 * 
 * STEP 3: CHOOSE APPROPRIATE ALGORITHM
 * - DFS: General purpose, good for cycle finding
 * - Union-Find: Efficient for undirected graphs
 * - Topological Sort: Natural for scheduling problems
 * - Specialized: For specific cycle types
 * 
 * STEP 4: OPTIMIZE FOR USE CASE
 * - Early termination when first cycle found?
 * - Incremental updates for dynamic graphs?
 * - Memory optimization for large graphs?
 * 
 * STEP 5: HANDLE EDGE CASES
 * - Self-loops, parallel edges
 * - Disconnected graph components
 * - Empty graphs and single nodes
 * 
 * ============================================================================
 * 🎨 CYCLE DETECTION ALGORITHM TEMPLATES
 * ============================================================================
 */

public class CycleDetectionReadingGuide {
    
    /**
     * 🎯 DIRECTED GRAPH CYCLE DETECTION TEMPLATES
     */
    public static class DirectedGraphCycles {
        
        // Three-color DFS state representation
        enum Color { WHITE, GRAY, BLACK }
        
        /**
         * DFS-based Cycle Detection with Three-Color Algorithm
         * Strategy: Track node states during DFS traversal
         */
        public static boolean hasCycleDFS(List<List<Integer>> graph) {
            int n = graph.size();
            Color[] colors = new Color[n];
            Arrays.fill(colors, Color.WHITE);
            
            for (int i = 0; i < n; i++) {
                if (colors[i] == Color.WHITE) {
                    if (dfsHasCycle(graph, i, colors)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        private static boolean dfsHasCycle(List<List<Integer>> graph, int node, Color[] colors) {
            colors[node] = Color.GRAY; // Mark as being processed
            
            for (int neighbor : graph.get(node)) {
                if (colors[neighbor] == Color.GRAY) {
                    return true; // Back edge found - cycle detected
                }
                
                if (colors[neighbor] == Color.WHITE && 
                    dfsHasCycle(graph, neighbor, colors)) {
                    return true;
                }
            }
            
            colors[node] = Color.BLACK; // Mark as completely processed
            return false;
        }
        
        /**
         * Topological Sort-based Cycle Detection (Kahn's Algorithm)
         * Strategy: If topological sort is not possible, cycle exists
         */
        public static boolean hasCycleTopological(List<List<Integer>> graph) {
            int n = graph.size();
            int[] inDegree = new int[n];
            
            // Calculate in-degrees
            for (int i = 0; i < n; i++) {
                for (int neighbor : graph.get(i)) {
                    inDegree[neighbor]++;
                }
            }
            
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            
            int processedNodes = 0;
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                processedNodes++;
                
                for (int neighbor : graph.get(node)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
            
            return processedNodes < n; // Cycle exists if not all nodes processed
        }
        
        /**
         * Cycle Detection with Path Reconstruction
         * Strategy: Track path during DFS to reconstruct cycle
         */
        public static List<Integer> findCycle(List<List<Integer>> graph) {
            int n = graph.size();
            Color[] colors = new Color[n];
            int[] parent = new int[n];
            Arrays.fill(colors, Color.WHITE);
            Arrays.fill(parent, -1);
            
            for (int i = 0; i < n; i++) {
                if (colors[i] == Color.WHITE) {
                    List<Integer> cycle = dfsFindCycle(graph, i, colors, parent);
                    if (!cycle.isEmpty()) {
                        return cycle;
                    }
                }
            }
            
            return new ArrayList<>(); // No cycle found
        }
        
        private static List<Integer> dfsFindCycle(List<List<Integer>> graph, int node, 
                                                Color[] colors, int[] parent) {
            colors[node] = Color.GRAY;
            
            for (int neighbor : graph.get(node)) {
                if (colors[neighbor] == Color.GRAY) {
                    // Cycle found, reconstruct it
                    return reconstructCycle(parent, node, neighbor);
                }
                
                if (colors[neighbor] == Color.WHITE) {
                    parent[neighbor] = node;
                    List<Integer> cycle = dfsFindCycle(graph, neighbor, colors, parent);
                    if (!cycle.isEmpty()) {
                        return cycle;
                    }
                }
            }
            
            colors[node] = Color.BLACK;
            return new ArrayList<>();
        }
        
        private static List<Integer> reconstructCycle(int[] parent, int start, int end) {
            List<Integer> cycle = new ArrayList<>();
            cycle.add(end);
            
            int current = start;
            while (current != end) {
                cycle.add(current);
                current = parent[current];
            }
            
            Collections.reverse(cycle);
            return cycle;
        }
    }
    
    /**
     * 🔗 UNDIRECTED GRAPH CYCLE DETECTION TEMPLATES
     */
    public static class UndirectedGraphCycles {
        
        /**
         * DFS-based Cycle Detection with Parent Tracking
         * Strategy: Avoid parent node to prevent trivial cycles
         */
        public static boolean hasCycleDFS(List<List<Integer>> graph) {
            int n = graph.size();
            boolean[] visited = new boolean[n];
            
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if (dfsHasCycle(graph, i, -1, visited)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        private static boolean dfsHasCycle(List<List<Integer>> graph, int node, 
                                         int parent, boolean[] visited) {
            visited[node] = true;
            
            for (int neighbor : graph.get(node)) {
                if (neighbor == parent) continue; // Skip parent to avoid trivial cycle
                
                if (visited[neighbor]) {
                    return true; // Cycle found
                }
                
                if (dfsHasCycle(graph, neighbor, node, visited)) {
                    return true;
                }
            }
            
            return false;
        }
        
        /**
         * Union-Find Cycle Detection
         * Strategy: Detect cycles during edge addition
         */
        public static boolean hasCycleUnionFind(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            
            for (int[] edge : edges) {
                if (uf.isConnected(edge[0], edge[1])) {
                    return true; // Adding edge creates cycle
                }
                uf.union(edge[0], edge[1]);
            }
            
            return false;
        }
        
        static class UnionFind {
            private int[] parent;
            private int[] rank;
            
            public UnionFind(int n) {
                parent = new int[n];
                rank = new int[n];
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
                
                if (rootX == rootY) return false; // Already connected
                
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                
                return true;
            }
            
            public boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }
        }
        
        /**
         * BFS-based Cycle Detection
         * Strategy: Level-based traversal with visited tracking
         */
        public static boolean hasCycleBFS(List<List<Integer>> graph) {
            int n = graph.size();
            boolean[] visited = new boolean[n];
            
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if (bfsHasCycle(graph, i, visited)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        private static boolean bfsHasCycle(List<List<Integer>> graph, int start, boolean[] visited) {
            Queue<int[]> queue = new LinkedList<>(); // [node, parent]
            queue.offer(new int[]{start, -1});
            visited[start] = true;
            
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int node = current[0];
                int parent = current[1];
                
                for (int neighbor : graph.get(node)) {
                    if (neighbor == parent) continue;
                    
                    if (visited[neighbor]) {
                        return true; // Cycle found
                    }
                    
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, node});
                }
            }
            
            return false;
        }
    }
    
    /**
     * ⚡ SPECIALIZED CYCLE DETECTION TEMPLATES
     */
    public static class SpecializedCycles {
        
        /**
         * Course Schedule Cycle Detection
         * Strategy: Topological sorting for prerequisite validation
         */
        public static boolean canFinishCourses(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            
            int[] inDegree = new int[numCourses];
            
            // Build graph and calculate in-degrees
            for (int[] prereq : prerequisites) {
                graph.get(prereq[1]).add(prereq[0]); // prereq[1] -> prereq[0]
                inDegree[prereq[0]]++;
            }
            
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            
            int completedCourses = 0;
            
            while (!queue.isEmpty()) {
                int course = queue.poll();
                completedCourses++;
                
                for (int nextCourse : graph.get(course)) {
                    inDegree[nextCourse]--;
                    if (inDegree[nextCourse] == 0) {
                        queue.offer(nextCourse);
                    }
                }
            }
            
            return completedCourses == numCourses;
        }
        
        /**
         * Find Safe States (Nodes not in cycles)
         * Strategy: Reverse graph and find nodes that don't lead to cycles
         */
        public static List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            List<List<Integer>> reverseGraph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                reverseGraph.add(new ArrayList<>());
            }
            
            int[] inDegree = new int[n];
            
            // Build reverse graph
            for (int i = 0; i < n; i++) {
                for (int neighbor : graph[i]) {
                    reverseGraph.get(neighbor).add(i);
                    inDegree[i]++;
                }
            }
            
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i); // Terminal nodes
                }
            }
            
            List<Integer> safeNodes = new ArrayList<>();
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                safeNodes.add(node);
                
                for (int predecessor : reverseGraph.get(node)) {
                    inDegree[predecessor]--;
                    if (inDegree[predecessor] == 0) {
                        queue.offer(predecessor);
                    }
                }
            }
            
            Collections.sort(safeNodes);
            return safeNodes;
        }
        
        /**
         * Detect Cycle in Linked List (Floyd's Algorithm)
         * Strategy: Two pointers with different speeds
         */
        public static boolean hasCycleLinkedList(ListNode head) {
            if (head == null || head.next == null) return false;
            
            ListNode slow = head;
            ListNode fast = head;
            
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                
                if (slow == fast) {
                    return true; // Cycle detected
                }
            }
            
            return false;
        }
        
        static class ListNode {
            int val;
            ListNode next;
            ListNode(int val) { this.val = val; }
        }
        
        /**
         * Find Cycle Start in Linked List
         * Strategy: Floyd's algorithm + mathematical analysis
         */
        public static ListNode detectCycleStart(ListNode head) {
            if (head == null || head.next == null) return null;
            
            // Phase 1: Detect cycle existence
            ListNode slow = head;
            ListNode fast = head;
            
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                
                if (slow == fast) break;
            }
            
            if (fast == null || fast.next == null) return null; // No cycle
            
            // Phase 2: Find cycle start
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            
            return slow; // Cycle start node
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Forgetting Parent Check in Undirected Graphs
     * Always skip parent node to avoid trivial cycles
     */
    public static void parentCheckExample() {
        // Correct: Skip parent to avoid trivial cycle
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) continue; // Essential check
            // ... rest of logic
        }
        
        // Incorrect: Will always detect trivial cycles in undirected graphs
    }
    
    /**
     * ❌ PITFALL 2: Incorrect State Management in Directed Graphs
     * Use proper three-color system for directed graph cycle detection
     */
    public static void stateManagementExample() {
        // Correct: Three-color system
        enum Color { WHITE, GRAY, BLACK }
        // WHITE: unvisited, GRAY: processing, BLACK: completed
        
        // Incorrect: Binary visited array insufficient for directed graphs
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Early Termination
     * Stop at first cycle detection for existence queries
     */
    
    /**
     * 🎯 STRATEGY 2: Incremental Detection
     * Use Union-Find for dynamic edge addition scenarios
     */
    
    /**
     * 🎯 STRATEGY 3: Specialized Algorithms
     * Use domain-specific optimizations (Floyd's for linked lists)
     */
    
    // ============================================================================
    // 🧭 ALGORITHM SELECTION GUIDE
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * GRAPH TYPE:
     * ✅ Directed graph → DFS with three colors or topological sort
     * ✅ Undirected graph → DFS with parent tracking or Union-Find
     * ✅ Linked list → Floyd's cycle detection algorithm
     * ✅ Function calls → Call stack cycle detection
     * 
     * REQUIREMENTS:
     * ✅ Just detect existence → Early termination DFS
     * ✅ Find actual cycle → DFS with path reconstruction
     * ✅ Dynamic updates → Union-Find for undirected graphs
     * ✅ Multiple cycles → Specialized enumeration algorithms
     */
    
    public static void main(String[] args) {
        System.out.println("🔄 CYCLE DETECTION PATTERN - READING GUIDE");
        System.out.println("==========================================");
        
        // Create sample directed graph
        List<List<Integer>> directedGraph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directedGraph.add(new ArrayList<>());
        }
        directedGraph.get(0).add(1);
        directedGraph.get(1).add(2);
        directedGraph.get(2).add(3);
        directedGraph.get(3).add(1); // Creates cycle: 1->2->3->1
        
        // Test directed graph cycle detection
        System.out.println("\n🎯 TESTING DIRECTED GRAPH CYCLES:");
        boolean hasCycleDFS = DirectedGraphCycles.hasCycleDFS(directedGraph);
        System.out.println("DFS cycle detection: " + hasCycleDFS); // true
        
        boolean hasCycleTopological = DirectedGraphCycles.hasCycleTopological(directedGraph);
        System.out.println("Topological cycle detection: " + hasCycleTopological); // true
        
        List<Integer> cycle = DirectedGraphCycles.findCycle(directedGraph);
        System.out.println("Found cycle: " + cycle);
        
        // Create sample undirected graph
        List<List<Integer>> undirectedGraph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            undirectedGraph.add(new ArrayList<>());
        }
        undirectedGraph.get(0).add(1); undirectedGraph.get(1).add(0);
        undirectedGraph.get(1).add(2); undirectedGraph.get(2).add(1);
        undirectedGraph.get(2).add(3); undirectedGraph.get(3).add(2);
        undirectedGraph.get(3).add(0); undirectedGraph.get(0).add(3); // Creates cycle
        
        // Test undirected graph cycle detection
        System.out.println("\n🔗 TESTING UNDIRECTED GRAPH CYCLES:");
        boolean undirectedCycleDFS = UndirectedGraphCycles.hasCycleDFS(undirectedGraph);
        System.out.println("Undirected DFS cycle detection: " + undirectedCycleDFS); // true
        
        boolean undirectedCycleBFS = UndirectedGraphCycles.hasCycleBFS(undirectedGraph);
        System.out.println("Undirected BFS cycle detection: " + undirectedCycleBFS); // true
        
        int[][] edges = {{0,1},{1,2},{2,3},{3,0}};
        boolean unionFindCycle = UndirectedGraphCycles.hasCycleUnionFind(4, edges);
        System.out.println("Union-Find cycle detection: " + unionFindCycle); // true
        
        // Test course scheduling
        System.out.println("\n📚 TESTING COURSE SCHEDULING:");
        int[][] prerequisites1 = {{1,0},{0,1}}; // Circular dependency
        boolean canFinish1 = SpecializedCycles.canFinishCourses(2, prerequisites1);
        System.out.println("Can finish courses (cycle): " + canFinish1); // false
        
        int[][] prerequisites2 = {{1,0}};
        boolean canFinish2 = SpecializedCycles.canFinishCourses(2, prerequisites2);
        System.out.println("Can finish courses (no cycle): " + canFinish2); // true
        
        // Test safe states
        System.out.println("\n🛡️ TESTING SAFE STATES:");
        int[][] safeGraph = {{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> safeNodes = SpecializedCycles.eventualSafeNodes(safeGraph);
        System.out.println("Safe nodes: " + safeNodes); // [2,4,5,6]
        
        System.out.println("\n✅ Key Cycle Detection Principles:");
        System.out.println("1. Use three-color DFS for directed graphs");
        System.out.println("2. Track parent nodes in undirected graph traversal");
        System.out.println("3. Apply topological sorting for scheduling problems");
        System.out.println("4. Use Union-Find for dynamic edge addition scenarios");
        System.out.println("5. Implement early termination for existence queries");
        System.out.println("6. Choose appropriate algorithm based on graph type");
        System.out.println("7. Handle edge cases: self-loops, disconnected components");
    }
} 