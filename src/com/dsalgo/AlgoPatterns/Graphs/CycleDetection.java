package com.dsalgo.AlgoPatterns.Graphs;

import java.util.*;

/**
 * 🔄 CYCLE DETECTION PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of cycle detection algorithms
 * for directed and undirected graphs, course scheduling validation, dependency
 * resolution, and safe state identification. These algorithms demonstrate DFS
 * with state tracking, topological sorting, Union-Find techniques, and specialized
 * cycle detection methods for various graph types and applications.
 * 
 * Pattern Focus: Cycle identification, dependency validation, scheduling verification
 * Time Complexity: Generally O(V + E) for DFS/BFS approaches
 * Space Complexity: O(V) for state tracking, O(V + E) for graph storage
 */

public class CycleDetection {
    
    /**
     * Course Schedule - Basic Cycle Detection
     * 
     * There are a total of numCourses courses you have to take, labeled from 0 
     * to numCourses - 1. You are given an array prerequisites where 
     * prerequisites[i] = [ai, bi] indicates that you must take course bi first 
     * if you want to take course ai. Return true if you can finish all courses.
     * 
     * Strategy: Topological sorting using Kahn's algorithm (BFS approach)
     * Time: O(V + E) where V = courses, E = prerequisites
     * Space: O(V + E) for adjacency list and in-degree array
     * 
     * LeetCode: https://leetcode.com/problems/course-schedule/
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and calculate in-degrees
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[numCourses];
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]); // prereq[1] -> prereq[0]
            inDegree[prereq[0]]++;
        }
        
        // Kahn's algorithm for topological sorting
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
     * Alternative Course Schedule - DFS with Three-Color Algorithm
     * Strategy: Use white/gray/black states to detect back edges
     */
    public static boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        // 0: WHITE (unvisited), 1: GRAY (processing), 2: BLACK (completed)
        int[] colors = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            if (colors[i] == 0 && hasCycleDFS(graph, i, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasCycleDFS(List<List<Integer>> graph, int node, int[] colors) {
        colors[node] = 1; // Mark as GRAY (being processed)
        
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == 1) {
                return true; // Back edge found - cycle detected
            }
            if (colors[neighbor] == 0 && hasCycleDFS(graph, neighbor, colors)) {
                return true;
            }
        }
        
        colors[node] = 2; // Mark as BLACK (completely processed)
        return false;
    }
    
    /**
     * Course Schedule II - Find Valid Topological Order
     * 
     * Return the ordering of courses you should take to finish all courses.
     * If there are many valid answers, return any of them. If it is impossible 
     * to finish all courses, return an empty array.
     * 
     * Strategy: Topological sorting with order tracking
     * Time: O(V + E), Space: O(V + E)
     * 
     * LeetCode: https://leetcode.com/problems/course-schedule-ii/
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[numCourses];
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            
            for (int nextCourse : graph.get(course)) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        return index == numCourses ? result : new int[0];
    }
    
    /**
     * Find Eventual Safe States
     * 
     * We start at some node in a directed graph, and every turn, we walk along 
     * a directed edge of the graph. If we reach a terminal node (that is, it has 
     * no outgoing directed edges), we stop. A node is eventually safe if every 
     * possible walk starting from that node leads to a terminal node.
     * 
     * Strategy: Reverse graph and topological sorting from terminal nodes
     * Time: O(V + E), Space: O(V + E)
     * 
     * LeetCode: https://leetcode.com/problems/find-eventual-safe-states/
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
     * Detect Cycle in Directed Graph - Generic Implementation
     * 
     * Given a directed graph, detect if there's a cycle in the graph.
     * 
     * Strategy: DFS with three-color state tracking
     * Time: O(V + E), Space: O(V)
     * 
     * LeetCode: https://leetcode.com/problems/detect-cycle-in-directed-graph/
     */
    public static boolean hasCycleDirected(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n]; // 0: WHITE, 1: GRAY, 2: BLACK
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && dfsDirectedCycle(graph, i, colors)) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean dfsDirectedCycle(List<List<Integer>> graph, int node, int[] colors) {
        colors[node] = 1; // GRAY
        
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == 1) {
                return true; // Back edge - cycle found
            }
            if (colors[neighbor] == 0 && dfsDirectedCycle(graph, neighbor, colors)) {
                return true;
            }
        }
        
        colors[node] = 2; // BLACK
        return false;
    }
    
    /**
     * BONUS: Detect Cycle in Undirected Graph
     * 
     * Given an undirected graph, detect if there's a cycle.
     * 
     * Strategy: DFS with parent tracking to avoid trivial cycles
     * Time: O(V + E), Space: O(V)
     */
    public static boolean hasCycleUndirected(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfsUndirectedCycle(graph, i, -1, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean dfsUndirectedCycle(List<List<Integer>> graph, int node, 
                                            int parent, boolean[] visited) {
        visited[node] = true;
        
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) continue; // Skip parent
            
            if (visited[neighbor]) {
                return true; // Cycle found
            }
            
            if (dfsUndirectedCycle(graph, neighbor, node, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * BONUS: Union-Find Cycle Detection for Undirected Graph
     * 
     * Detect cycles using Union-Find during edge addition.
     * 
     * Strategy: If adding edge connects already connected components, cycle exists
     * Time: O(E * α(V)), Space: O(V)
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
    
    /**
     * Union-Find Data Structure with Path Compression and Union by Rank
     */
    public static class UnionFind {
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
            
            if (rootX == rootY) return false;
            
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
     * BONUS: Linked List Cycle Detection (Floyd's Algorithm)
     * 
     * Given head, the head of a linked list, determine if the linked list has a cycle.
     * 
     * Strategy: Two pointers moving at different speeds
     * Time: O(n), Space: O(1)
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
    
    /**
     * BONUS: Find Cycle Start in Linked List
     * 
     * Given a linked list, return the node where the cycle begins.
     * 
     * Strategy: Floyd's algorithm + mathematical analysis
     * Time: O(n), Space: O(1)
     */
    public static ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) return null;
        
        // Phase 1: Detect cycle
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
        
        return slow;
    }
    
    /**
     * Supporting ListNode class
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    /**
     * BONUS: Cycle Detection with Path Reconstruction
     * 
     * Find and return the actual cycle in a directed graph.
     * 
     * Strategy: DFS with path tracking for cycle reconstruction
     * Time: O(V + E), Space: O(V)
     */
    public static List<Integer> findCycle(List<List<Integer>> graph) {
        int n = graph.size();
        int[] colors = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                List<Integer> cycle = dfsFindCycle(graph, i, colors, parent);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }
        
        return new ArrayList<>();
    }
    
    private static List<Integer> dfsFindCycle(List<List<Integer>> graph, int node, 
                                            int[] colors, int[] parent) {
        colors[node] = 1; // GRAY
        
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == 1) {
                // Cycle found, reconstruct it
                return reconstructCycle(parent, node, neighbor);
            }
            
            if (colors[neighbor] == 0) {
                parent[neighbor] = node;
                List<Integer> cycle = dfsFindCycle(graph, neighbor, colors, parent);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }
        
        colors[node] = 2; // BLACK
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
    
    /**
     * BONUS: Check if Graph is Bipartite (No Odd Cycles)
     * 
     * A graph is bipartite if nodes can be colored with two colors such that
     * no adjacent nodes have the same color. This is equivalent to having no odd cycles.
     * 
     * Strategy: DFS with two-coloring
     * Time: O(V + E), Space: O(V)
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
                return false; // Same color as neighbor - not bipartite
            }
            
            if (colors[neighbor] == 0 && !dfsBipartite(graph, neighbor, -color, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("🔄 CYCLE DETECTION - PRACTICE IMPLEMENTATIONS");
        System.out.println("============================================");
        
        // Test Course Schedule
        System.out.println("\n📚 TESTING COURSE SCHEDULE:");
        int[][] prereq1 = {{1,0},{0,1}}; // Circular dependency
        boolean canFinish1 = canFinish(2, prereq1);
        System.out.println("Can finish courses (cycle): " + canFinish1); // false
        
        int[][] prereq2 = {{1,0}};
        boolean canFinish2 = canFinish(2, prereq2);
        System.out.println("Can finish courses (no cycle): " + canFinish2); // true
        
        // Test Course Schedule II
        System.out.println("\n📖 TESTING COURSE SCHEDULE II:");
        int[] order1 = findOrder(2, prereq1);
        System.out.println("Course order (cycle): " + Arrays.toString(order1)); // []
        
        int[] order2 = findOrder(2, prereq2);
        System.out.println("Course order (valid): " + Arrays.toString(order2)); // [0,1]
        
        int[][] prereq3 = {{1,0},{2,0},{3,1},{3,2}};
        int[] order3 = findOrder(4, prereq3);
        System.out.println("Complex course order: " + Arrays.toString(order3)); // [0,1,2,3] or [0,2,1,3]
        
        // Test Find Eventual Safe States
        System.out.println("\n🛡️ TESTING SAFE STATES:");
        int[][] safeGraph = {{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> safeNodes = eventualSafeNodes(safeGraph);
        System.out.println("Safe nodes: " + safeNodes); // [2,4,5,6]
        
        // Test Directed Graph Cycle Detection
        System.out.println("\n🎯 TESTING DIRECTED GRAPH CYCLES:");
        List<List<Integer>> directedGraph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directedGraph.add(new ArrayList<>());
        }
        directedGraph.get(0).add(1);
        directedGraph.get(1).add(2);
        directedGraph.get(2).add(3);
        directedGraph.get(3).add(1); // Creates cycle
        
        boolean directedCycle = hasCycleDirected(directedGraph);
        System.out.println("Directed graph has cycle: " + directedCycle); // true
        
        List<Integer> cycle = findCycle(directedGraph);
        System.out.println("Found cycle: " + cycle);
        
        // Test Undirected Graph Cycle Detection
        System.out.println("\n🔗 TESTING UNDIRECTED GRAPH CYCLES:");
        List<List<Integer>> undirectedGraph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            undirectedGraph.add(new ArrayList<>());
        }
        undirectedGraph.get(0).add(1); undirectedGraph.get(1).add(0);
        undirectedGraph.get(1).add(2); undirectedGraph.get(2).add(1);
        undirectedGraph.get(2).add(3); undirectedGraph.get(3).add(2);
        undirectedGraph.get(3).add(0); undirectedGraph.get(0).add(3);
        
        boolean undirectedCycle = hasCycleUndirected(undirectedGraph);
        System.out.println("Undirected graph has cycle: " + undirectedCycle); // true
        
        // Test Union-Find Cycle Detection
        int[][] edges = {{0,1},{1,2},{2,3},{3,0}};
        boolean unionFindCycle = hasCycleUnionFind(4, edges);
        System.out.println("Union-Find cycle detection: " + unionFindCycle); // true
        
        // Test Linked List Cycle Detection
        System.out.println("\n🔗 TESTING LINKED LIST CYCLES:");
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // Creates cycle
        
        boolean linkedListCycle = hasCycleLinkedList(head1);
        System.out.println("Linked list has cycle: " + linkedListCycle); // true
        
        ListNode cycleStart = detectCycleStart(head1);
        System.out.println("Cycle starts at node with value: " + 
                          (cycleStart != null ? cycleStart.val : "null")); // 2
        
        // Test Bipartite Graph (No Odd Cycles)
        System.out.println("\n🎨 TESTING BIPARTITE GRAPH:");
        int[][] bipartiteGraph = {{1,3},{0,2},{1,3},{0,2}};
        boolean isBipartite1 = isBipartite(bipartiteGraph);
        System.out.println("Graph is bipartite: " + isBipartite1); // true
        
        int[][] nonBipartiteGraph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        boolean isBipartite2 = isBipartite(nonBipartiteGraph);
        System.out.println("Graph is bipartite (triangle): " + isBipartite2); // false
        
        System.out.println("\n✅ Cycle Detection Pattern Completed!");
        System.out.println("Key Principles:");
        System.out.println("1. Use three-color DFS for directed graph cycle detection");
        System.out.println("2. Track parent nodes in undirected graph traversal");
        System.out.println("3. Apply topological sorting for scheduling validation");
        System.out.println("4. Use Union-Find for efficient dynamic cycle detection");
        System.out.println("5. Employ Floyd's algorithm for linked list cycle detection");
        System.out.println("6. Implement early termination for existence queries");
        System.out.println("7. Choose appropriate algorithm based on graph type and requirements");
    }
} 