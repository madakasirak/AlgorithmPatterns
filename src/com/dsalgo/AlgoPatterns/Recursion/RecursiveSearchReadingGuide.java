package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * 🔍 RECURSIVE SEARCH PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS RECURSIVE SEARCH?
 * ============================================================================
 * 
 * Recursive Search is a systematic approach for finding target elements or
 * solutions by recursively exploring different branches of a search space.
 * It combines the power of recursion with various search strategies to
 * navigate through structured or unstructured data until the target is
 * found or the search space is exhausted.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. EXPLORE: Recursively visit nodes/states in the search space
 * 2. VALIDATE: Check if current state matches search criteria
 * 3. NAVIGATE: Move to adjacent/child states systematically
 * 4. TERMINATE: Stop when target found or space exhausted
 * 
 * ============================================================================
 * 🎯 WHEN TO USE RECURSIVE SEARCH
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Tree and graph traversal problems
 * - Grid-based pathfinding and exploration
 * - Constraint satisfaction with search (Sudoku, puzzles)
 * - Finding connected components or regions
 * - Depth-first exploration with specific goals
 * - Problems with natural recursive structure
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find path from source to destination"
 * - "Search for element in tree/graph"
 * - "Explore all connected regions"
 * - "Find solution that satisfies constraints"
 * - "Count connected components"
 * - "Validate if path exists"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need shortest path (use BFS instead)
 * - Memory-intensive with deep recursion (use iterative DFS)
 * - Need all paths (use backtracking)
 * - Simple linear search (use iteration)
 * 
 * ============================================================================
 * 🔄 RECURSIVE SEARCH VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TREE SEARCH
 * - Search in binary trees, n-ary trees, tries
 * - Examples: Path Sum, Binary Tree Search, Tree Validation
 * 
 * 2️⃣ GRAPH SEARCH (DFS)
 * - Depth-first exploration of graph structures
 * - Examples: Number of Islands, Connected Components
 * 
 * 3️⃣ GRID SEARCH
 * - 2D grid exploration with directional movement
 * - Examples: Word Search, Maze Solving, Flood Fill
 * 
 * 4️⃣ CONSTRAINT SATISFACTION SEARCH
 * - Search with validation of constraints at each step
 * - Examples: Sudoku Solver, N-Queens, Puzzle Solving
 * 
 * 5️⃣ TRIE-BASED SEARCH
 * - Efficient string/prefix searching using trie structures
 * - Examples: Word Search II, Auto-complete, Dictionary
 * 
 * 6️⃣ STATE SPACE SEARCH
 * - Explore different states to reach goal state
 * - Examples: Game solving, Configuration problems
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS
 * ============================================================================
 * 
 * 🔹 RECURSIVE SEARCH TEMPLATE:
 * ```
 * function recursiveSearch(currentState, target, visited) {
 *     // Base case: target found
 *     if (isTarget(currentState, target)) {
 *         return true;
 *     }
 *     
 *     // Base case: invalid state or already visited
 *     if (isInvalid(currentState) || visited.contains(currentState)) {
 *         return false;
 *     }
 *     
 *     // Mark current state as visited
 *     visited.add(currentState);
 *     
 *     // Explore all possible next states
 *     for (nextState : getNextStates(currentState)) {
 *         if (recursiveSearch(nextState, target, visited)) {
 *             return true;
 *         }
 *     }
 *     
 *     // Optional: unmark for backtracking scenarios
 *     visited.remove(currentState);
 *     
 *     return false;
 * }
 * ```
 * 
 * 🔹 KEY COMPONENTS:
 * - Current State: Position/node being explored
 * - Target Criteria: Condition defining successful search
 * - Visited Tracking: Avoid infinite loops and cycles
 * - State Transitions: Rules for moving to next states
 * 
 * 🔹 SEARCH STRATEGIES:
 * - Depth-First: Go deep before exploring siblings
 * - Bounded: Limit search depth or iterations
 * - Pruned: Skip branches that can't lead to solution
 * - Memoized: Cache results for repeated subproblems
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY SEARCH SPACE
 * - What are the possible states/positions?
 * - How are states connected (adjacency rules)?
 * - What constitutes a valid vs invalid state?
 * 
 * STEP 2: DEFINE TARGET CRITERIA
 * - What are we searching for?
 * - How to recognize when target is found?
 * - Are there multiple valid targets?
 * 
 * STEP 3: DESIGN STATE REPRESENTATION
 * - How to efficiently represent current state?
 * - What information needed for next moves?
 * - How to track visited states?
 * 
 * STEP 4: IMPLEMENT TRANSITION LOGIC
 * - How to generate next possible states?
 * - What are the movement/transition rules?
 * - How to validate state transitions?
 * 
 * STEP 5: OPTIMIZE SEARCH STRATEGY
 * - Add pruning to avoid unnecessary exploration
 * - Implement memoization for repeated subproblems
 * - Consider iterative alternatives for deep searches
 * 
 * ============================================================================
 * 🎨 RECURSIVE SEARCH TEMPLATES
 * ============================================================================
 */
public class RecursiveSearchReadingGuide {
    
    /**
     * 🌳 TREE SEARCH TEMPLATE
     * Search for target in binary tree structure
     */
    public static boolean treeSearchTemplate(TreeNode root, int target) {
        // Base case: empty tree
        if (root == null) {
            return false;
        }
        
        // Base case: target found
        if (root.val == target) {
            return true;
        }
        
        // Recursively search left and right subtrees
        return treeSearchTemplate(root.left, target) || 
               treeSearchTemplate(root.right, target);
    }
    
    /**
     * 🔗 GRAPH SEARCH TEMPLATE (DFS)
     * Depth-first search in graph structure
     */
    public static boolean graphSearchTemplate(int[][] graph, int start, int target, boolean[] visited) {
        // Base case: target found
        if (start == target) {
            return true;
        }
        
        // Mark current node as visited
        visited[start] = true;
        
        // Explore all adjacent nodes
        for (int neighbor : graph[start]) {
            if (!visited[neighbor]) {
                if (graphSearchTemplate(graph, neighbor, target, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 🏁 GRID SEARCH TEMPLATE
     * Search in 2D grid with directional movement
     */
    public static boolean gridSearchTemplate(char[][] grid, int row, int col, 
                                           String target, int index, boolean[][] visited) {
        // Base case: target completely found
        if (index == target.length()) {
            return true;
        }
        
        // Base case: out of bounds or character mismatch
        if (row < 0 || row >= grid.length || 
            col < 0 || col >= grid[0].length || 
            visited[row][col] || 
            grid[row][col] != target.charAt(index)) {
            return false;
        }
        
        // Mark current cell as visited
        visited[row][col] = true;
        
        // Explore all 4 directions
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (gridSearchTemplate(grid, newRow, newCol, target, index + 1, visited)) {
                visited[row][col] = false; // Backtrack for multiple path exploration
                return true;
            }
        }
        
        // Backtrack: unmark current cell
        visited[row][col] = false;
        return false;
    }
    
    /**
     * 🧩 CONSTRAINT SATISFACTION TEMPLATE
     * Search with constraint validation (Sudoku-style)
     */
    public static boolean constraintSearchTemplate(int[][] board) {
        // Find next empty cell
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 0) { // Empty cell
                    // Try all possible values
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, row, col, num)) {
                            // Make choice
                            board[row][col] = num;
                            
                            // Recursively solve rest of board
                            if (constraintSearchTemplate(board)) {
                                return true;
                            }
                            
                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // All cells filled successfully
    }
    
    /**
     * 🌿 TRIE SEARCH TEMPLATE
     * Efficient string searching using trie structure
     */
    public static boolean trieSearchTemplate(TrieNode root, String word) {
        return trieSearchHelper(root, word, 0);
    }
    
    private static boolean trieSearchHelper(TrieNode node, String word, int index) {
        // Base case: word completely matched
        if (index == word.length()) {
            return node.isEndOfWord;
        }
        
        // Base case: character not found in trie
        char ch = word.charAt(index);
        if (node.children[ch - 'a'] == null) {
            return false;
        }
        
        // Recursively search for rest of word
        return trieSearchHelper(node.children[ch - 'a'], word, index + 1);
    }
    
    /**
     * 🎯 PATH FINDING TEMPLATE
     * Find path from source to destination
     */
    public static boolean pathFindingTemplate(int[][] maze, int startRow, int startCol, 
                                            int endRow, int endCol, boolean[][] visited) {
        // Base case: reached destination
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        
        // Base case: out of bounds or obstacle or already visited
        if (startRow < 0 || startRow >= maze.length || 
            startCol < 0 || startCol >= maze[0].length || 
            maze[startRow][startCol] == 1 || // 1 represents obstacle
            visited[startRow][startCol]) {
            return false;
        }
        
        // Mark current cell as visited
        visited[startRow][startCol] = true;
        
        // Explore all 4 directions
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = startRow + dir[0];
            int newCol = startCol + dir[1];
            
            if (pathFindingTemplate(maze, newRow, newCol, endRow, endCol, visited)) {
                return true;
            }
        }
        
        // Optional: unmark for multiple path exploration
        // visited[startRow][startCol] = false;
        
        return false;
    }
    
    /**
     * 🌊 FLOOD FILL TEMPLATE
     * Fill connected region with new value
     */
    public static void floodFillTemplate(int[][] image, int row, int col, 
                                       int originalColor, int newColor) {
        // Base case: out of bounds or different color
        if (row < 0 || row >= image.length || 
            col < 0 || col >= image[0].length || 
            image[row][col] != originalColor || 
            image[row][col] == newColor) {
            return;
        }
        
        // Fill current cell
        image[row][col] = newColor;
        
        // Recursively fill all 4 directions
        floodFillTemplate(image, row + 1, col, originalColor, newColor);
        floodFillTemplate(image, row - 1, col, originalColor, newColor);
        floodFillTemplate(image, row, col + 1, originalColor, newColor);
        floodFillTemplate(image, row, col - 1, originalColor, newColor);
    }
    
    // ============================================================================
    // 🔧 HELPER CLASSES AND METHODS
    // ============================================================================
    
    // Helper class for tree nodes
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
    
    // Helper class for trie nodes
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }
    
    private static boolean isValidPlacement(int[][] board, int row, int col, int num) {
        // Check row
        for (int c = 0; c < board[0].length; c++) {
            if (board[row][c] == num) return false;
        }
        
        // Check column
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num) return false;
        }
        
        // For Sudoku: check 3x3 box (simplified example)
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }
        
        return true;
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Infinite Recursion
     * Always check for base cases and visited states
     */
    public static void infiniteRecursionExample() {
        // BAD: No base case or visited tracking
        // function search(node) { return search(node.neighbor); }
        
        // GOOD: Proper base case and visited tracking
        // if (visited.contains(node)) return false;
        // visited.add(node);
    }
    
    /**
     * ❌ PITFALL 2: Not Handling Boundaries
     * Always validate array/grid boundaries
     */
    public static void boundaryCheckExample() {
        // BAD: No boundary checking
        // return grid[row][col] == target;
        
        // GOOD: Proper boundary validation
        // if (row < 0 || row >= grid.length) return false;
    }
    
    /**
     * ❌ PITFALL 3: Incorrect Backtracking
     * Be careful when to unmark visited states
     */
    public static void backtrackingExample() {
        // Consider whether you need to unmark visited states
        // For single path: don't unmark
        // For multiple paths: unmark after exploration
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Search Strategy
     * DFS for existence, BFS for shortest path
     */
    
    /**
     * 🎯 TIP 2: Optimize State Representation
     * Use efficient data structures for visited tracking
     */
    
    /**
     * 🎯 TIP 3: Add Early Termination
     * Stop search as soon as target is found
     */
    
    /**
     * 🎯 TIP 4: Consider Iterative Alternatives
     * For very deep searches, avoid stack overflow
     */
    
    /**
     * 🎯 TIP 5: Use Memoization When Appropriate
     * Cache results for overlapping subproblems
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC RECURSIVE SEARCH:
     * - Binary Tree Search
     * - Path Sum
     * - Number of Islands
     * - Flood Fill
     * 
     * 🟡 INTERMEDIATE RECURSIVE SEARCH:
     * - Word Search
     * - Sudoku Solver
     * - Connected Components
     * - Maze Solving
     * 
     * 🔴 ADVANCED RECURSIVE SEARCH:
     * - Word Search II (Trie)
     * - Robot Room Cleaner
     * - Alien Dictionary
     * - Maximum Area of Island
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE RECURSIVE SEARCH WHEN:
     * ✅ Need to explore tree/graph structures
     * ✅ Problem has natural recursive decomposition
     * ✅ DFS strategy is appropriate (vs BFS)
     * ✅ Search space is manageable
     * 
     * AVOID RECURSIVE SEARCH WHEN:
     * ❌ Need shortest path (use BFS)
     * ❌ Very deep search spaces (stack overflow risk)
     * ❌ Simple linear search suffices
     * ❌ Memory constraints are tight
     * 
     * OPTIMIZE RECURSIVE SEARCH WITH:
     * 🚀 Early termination conditions
     * 🚀 Efficient visited state tracking
     * 🚀 Memoization for overlapping subproblems
     * 🚀 Iterative alternatives for deep searches
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - File system traversal and search
     * - Web crawling and link analysis
     * - Game AI and decision trees
     * - Network topology discovery
     * - Image processing (connected components)
     * - Dependency resolution systems
     * - Social network analysis
     * - Route planning with constraints
     */
    
    public static void main(String[] args) {
        System.out.println("🔍 RECURSIVE SEARCH PATTERN - READING GUIDE");
        System.out.println("==========================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Tree Search Example
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        
        System.out.println("Tree Search for 4: " + treeSearchTemplate(root, 4));
        System.out.println("Tree Search for 7: " + treeSearchTemplate(root, 7));
        
        // Graph Search Example
        int[][] graph = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        boolean[] visited = new boolean[4];
        System.out.println("Graph path from 0 to 3 exists: " + 
            graphSearchTemplate(graph, 0, 3, visited));
        
        // Grid Search Example
        char[][] grid = {
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'}
        };
        boolean[][] gridVisited = new boolean[3][3];
        System.out.println("Grid search for 'ABC': " + 
            gridSearchTemplate(grid, 0, 0, "ABC", 0, gridVisited));
        
        // Constraint Search Example (simplified)
        int[][] board = {
            {5, 3, 0}, 
            {6, 0, 0}, 
            {0, 9, 8}
        };
        System.out.println("Constraint search solvable: " + constraintSearchTemplate(board));
        
        // Flood Fill Example
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        System.out.println("Original image:");
        printMatrix(image);
        floodFillTemplate(image, 1, 1, 1, 2);
        System.out.println("After flood fill (1->2):");
        printMatrix(image);
        
        System.out.println("\n✅ Key Recursive Search Principles:");
        System.out.println("1. Define clear base cases for termination");
        System.out.println("2. Track visited states to avoid cycles");
        System.out.println("3. Validate boundaries and constraints");
        System.out.println("4. Choose appropriate search strategy (DFS vs BFS)");
        System.out.println("5. Consider backtracking for multiple path exploration");
        System.out.println("6. Optimize with early termination and pruning");
        System.out.println("7. Use iterative alternatives for very deep searches");
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println("  " + Arrays.toString(row));
        }
    }
} 