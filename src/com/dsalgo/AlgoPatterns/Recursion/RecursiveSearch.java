package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * 🔍 RECURSIVE SEARCH PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of recursive search algorithms.
 * Recursive Search systematically explores search spaces using recursion to find
 * target elements, solutions, or validate conditions through depth-first traversal
 * of trees, graphs, grids, and other structured data.
 * 
 * Pattern Focus: Systematic exploration, depth-first search, constraint satisfaction
 * Time Complexity: Often O(V + E) for graphs, O(4^n) for grids, varies by problem
 * Space Complexity: O(depth) for recursion stack + O(visited) for state tracking
 */
public class RecursiveSearch {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Recursive Search Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Path Sum
     * 
     * Problem: Check if tree has root-to-leaf path with given sum
     * LeetCode: https://leetcode.com/problems/path-sum/
     * 
     * Approach: Tree DFS with sum tracking
     * - Recursively traverse tree while subtracting node values
     * - Check if leaf node reached with remaining sum = 0
     * - Return true if any path satisfies condition
     * 
     * Time: O(n), Space: O(h) where h is tree height
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: empty tree
        if (root == null) {
            return false;
        }
        
        // Base case: leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // Recursive case: check left and right subtrees
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || 
               hasPathSum(root.right, remainingSum);
    }
    
    /**
     * 🟢 EASY: Number of Islands
     * 
     * Problem: Count number of islands in 2D grid (connected land cells)
     * LeetCode: https://leetcode.com/problems/number-of-islands/
     * 
     * Approach: DFS flood fill for each unvisited land cell
     * - Iterate through grid, start DFS from each '1'
     * - Mark connected land cells as visited during DFS
     * - Count number of DFS calls initiated
     * 
     * Time: O(m×n), Space: O(m×n) for recursion in worst case
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsFloodFill(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private static void dfsFloodFill(char[][] grid, int row, int col) {
        // Base case: out of bounds or water
        if (row < 0 || row >= grid.length || 
            col < 0 || col >= grid[0].length || 
            grid[row][col] == '0') {
            return;
        }
        
        // Mark current cell as visited (convert to water)
        grid[row][col] = '0';
        
        // Recursively explore all 4 directions
        dfsFloodFill(grid, row + 1, col);
        dfsFloodFill(grid, row - 1, col);
        dfsFloodFill(grid, row, col + 1);
        dfsFloodFill(grid, row, col - 1);
    }
    
    /**
     * 🟢 EASY: Same Tree
     * 
     * Problem: Check if two binary trees are identical
     * 
     * Approach: Simultaneous tree traversal
     * - Compare current nodes and recursively check subtrees
     * - Both null: true, one null: false, different values: false
     * 
     * Time: O(min(m,n)), Space: O(min(m,n))
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: both null
        if (p == null && q == null) {
            return true;
        }
        
        // Base case: one null, one not
        if (p == null || q == null) {
            return false;
        }
        
        // Base case: different values
        if (p.val != q.val) {
            return false;
        }
        
        // Recursive case: check both subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Recursive Search Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Sudoku Solver
     * 
     * Problem: Solve 9x9 Sudoku puzzle using backtracking
     * LeetCode: https://leetcode.com/problems/sudoku-solver/
     * 
     * Approach: Constraint satisfaction with recursive search
     * - Find next empty cell and try digits 1-9
     * - Validate placement against Sudoku rules
     * - Recursively solve remaining board
     * - Backtrack if no solution found
     * 
     * Time: O(9^(empty cells)), Space: O(1) excluding recursion
     */
    public static void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }
    
    private static boolean solveSudokuHelper(char[][] board) {
        // Find next empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    // Try digits 1-9
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isValidSudokuPlacement(board, row, col, digit)) {
                            // Make choice
                            board[row][col] = digit;
                            
                            // Recursively solve rest of board
                            if (solveSudokuHelper(board)) {
                                return true;
                            }
                            
                            // Backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false; // No valid digit found
                }
            }
        }
        return true; // All cells filled successfully
    }
    
    private static boolean isValidSudokuPlacement(char[][] board, int row, int col, char digit) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == digit) return false;
        }
        
        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == digit) return false;
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == digit) return false;
            }
        }
        
        return true;
    }
    
    /**
     * 🟡 MEDIUM: Binary Tree Maximum Path Sum
     * 
     * Problem: Find maximum sum path between any two nodes in tree
     * 
     * Approach: Post-order traversal with global maximum
     * - For each node, calculate max path through that node
     * - Consider: left path + node + right path
     * - Update global maximum and return best single path
     * 
     * Time: O(n), Space: O(h)
     */
    public static int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }
    
    private static int maxPathSumHelper(TreeNode node, int[] maxSum) {
        if (node == null) return 0;
        
        // Get max sum from left and right subtrees (ignore negative)
        int leftMax = Math.max(0, maxPathSumHelper(node.left, maxSum));
        int rightMax = Math.max(0, maxPathSumHelper(node.right, maxSum));
        
        // Max path through current node
        int currentMax = node.val + leftMax + rightMax;
        maxSum[0] = Math.max(maxSum[0], currentMax);
        
        // Return max single path from current node
        return node.val + Math.max(leftMax, rightMax);
    }
    
    /**
     * 🟡 MEDIUM: Validate Binary Search Tree
     * 
     * Problem: Check if binary tree is valid BST
     * 
     * Approach: In-order traversal with bounds checking
     * - Pass min and max bounds for each subtree
     * - Ensure current node value is within bounds
     * - Update bounds for recursive calls
     * 
     * Time: O(n), Space: O(h)
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) return true;
        
        // Check if current node violates BST property
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // Recursively validate subtrees with updated bounds
        return isValidBSTHelper(node.left, min, node.val) &&
               isValidBSTHelper(node.right, node.val, max);
    }
    
    /**
     * 🟡 MEDIUM: Surrounded Regions
     * 
     * Problem: Capture surrounded regions ('O' -> 'X') in 2D board
     * 
     * Approach: DFS from border to mark unsurrounded regions
     * - Start DFS from all 'O' cells on borders
     * - Mark all connected 'O' cells as safe
     * - Convert remaining 'O' cells to 'X'
     * 
     * Time: O(m×n), Space: O(m×n)
     */
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int rows = board.length;
        int cols = board[0].length;
        
        // Mark unsurrounded regions starting from borders
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') markUnsurrounded(board, i, 0);
            if (board[i][cols-1] == 'O') markUnsurrounded(board, i, cols-1);
        }
        
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') markUnsurrounded(board, 0, j);
            if (board[rows-1][j] == 'O') markUnsurrounded(board, rows-1, j);
        }
        
        // Convert remaining 'O' to 'X' and restore marked cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private static void markUnsurrounded(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || 
            col < 0 || col >= board[0].length || 
            board[row][col] != 'O') {
            return;
        }
        
        board[row][col] = '#'; // Mark as unsurrounded
        
        // Recursively mark connected cells
        markUnsurrounded(board, row + 1, col);
        markUnsurrounded(board, row - 1, col);
        markUnsurrounded(board, row, col + 1);
        markUnsurrounded(board, row, col - 1);
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Recursive Search Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Word Search II
     * 
     * Problem: Find all words from dictionary that exist in 2D board
     * LeetCode: https://leetcode.com/problems/word-search-ii/
     * 
     * Approach: Trie + DFS backtracking
     * - Build trie from word dictionary
     * - DFS from each cell while traversing trie
     * - Mark visited cells and backtrack
     * - Collect words when reaching trie end nodes
     * 
     * Time: O(m×n×4^L), Space: O(TRIE_SIZE)
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        int rows = board.length;
        int cols = board[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfsWordSearch(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private static void dfsWordSearch(char[][] board, int row, int col, 
                                    TrieNode node, List<String> result) {
        // Base case: out of bounds or already visited
        if (row < 0 || row >= board.length || 
            col < 0 || col >= board[0].length || 
            board[row][col] == '#') {
            return;
        }
        
        char ch = board[row][col];
        TrieNode child = node.children[ch - 'a'];
        
        // Character not in trie
        if (child == null) return;
        
        // Found complete word
        if (child.word != null) {
            result.add(child.word);
            child.word = null; // Avoid duplicates
        }
        
        // Mark cell as visited
        board[row][col] = '#';
        
        // Explore all 4 directions
        dfsWordSearch(board, row + 1, col, child, result);
        dfsWordSearch(board, row - 1, col, child, result);
        dfsWordSearch(board, row, col + 1, child, result);
        dfsWordSearch(board, row, col - 1, child, result);
        
        // Backtrack: restore original character
        board[row][col] = ch;
    }
    
    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        
        for (String word : words) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.word = word;
        }
        
        return root;
    }
    
    /**
     * 🔴 HARD: Serialize and Deserialize Binary Tree
     * 
     * Problem: Serialize tree to string and deserialize back to tree
     * 
     * Approach: Pre-order traversal with null markers
     * - Serialize: pre-order with null placeholders
     * - Deserialize: recursive reconstruction using queue
     * 
     * Time: O(n), Space: O(n)
     */
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private static void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }
    
    public static TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }
    
    private static TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if ("null".equals(val)) {
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }
    
    /**
     * 🔴 HARD: Robot Room Cleaner
     * 
     * Problem: Clean entire room using robot with limited API
     * 
     * Approach: DFS with robot state tracking
     * - Track robot position and direction
     * - Use DFS to explore all reachable cells
     * - Backtrack by returning robot to previous state
     * 
     * Time: O(N), Space: O(N) where N is number of cells
     */
    public static void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfsClean(robot, 0, 0, 0, visited); // Start at (0,0) facing up
    }
    
    private static void dfsClean(Robot robot, int row, int col, int direction, Set<String> visited) {
        String position = row + "," + col;
        if (visited.contains(position)) return;
        
        // Clean current cell and mark as visited
        robot.clean();
        visited.add(position);
        
        // Directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // Try all 4 directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + directions[direction][0];
            int newCol = col + directions[direction][1];
            
            if (!visited.contains(newRow + "," + newCol) && robot.move()) {
                dfsClean(robot, newRow, newCol, direction, visited);
                // Backtrack: return to previous position
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            
            // Turn right for next direction
            robot.turnRight();
            direction = (direction + 1) % 4;
        }
    }
    
    // ============================================================================
    // 🔧 HELPER CLASSES AND INTERFACES
    // ============================================================================
    
    // TreeNode class for binary tree problems
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    // TrieNode class for string/word problems
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }
    
    // Robot interface for room cleaner problem
    interface Robot {
        boolean move();
        void turnLeft();
        void turnRight();
        void clean();
    }
    
    // Mock Robot implementation for testing
    static class MockRobot implements Robot {
        private Set<String> obstacles = new HashSet<>();
        private int x = 0, y = 0, direction = 0; // 0=up, 1=right, 2=down, 3=left
        private int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        public boolean move() {
            int nx = x + dirs[direction][0];
            int ny = y + dirs[direction][1];
            if (!obstacles.contains(nx + "," + ny)) {
                x = nx; y = ny;
                return true;
            }
            return false;
        }
        
        public void turnLeft() { direction = (direction + 3) % 4; }
        public void turnRight() { direction = (direction + 1) % 4; }
        public void clean() { /* Clean current cell */ }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🔍 RECURSIVE SEARCH PATTERN - PRACTICE PROBLEMS");
        System.out.println("===============================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY RECURSIVE SEARCH PROBLEMS:");
        
        // Path Sum
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        
        System.out.println("Path Sum (target=22): " + hasPathSum(root, 22));
        System.out.println("Path Sum (target=15): " + hasPathSum(root, 15));
        
        // Number of Islands
        char[][] grid = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Number of Islands: " + numIslands(grid));
        
        // Same Tree
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);
        
        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(2);
        tree2.right = new TreeNode(3);
        
        System.out.println("Trees are same: " + isSameTree(tree1, tree2));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM RECURSIVE SEARCH PROBLEMS:");
        
        // Sudoku Solver
        char[][] sudoku = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("Sudoku before solving:");
        printSudoku(sudoku);
        solveSudoku(sudoku);
        System.out.println("Sudoku after solving:");
        printSudoku(sudoku);
        
        // Binary Tree Maximum Path Sum
        TreeNode pathSumTree = new TreeNode(1);
        pathSumTree.left = new TreeNode(2);
        pathSumTree.right = new TreeNode(3);
        System.out.println("Max Path Sum: " + maxPathSum(pathSumTree));
        
        // Validate BST
        TreeNode bst = new TreeNode(2);
        bst.left = new TreeNode(1);
        bst.right = new TreeNode(3);
        System.out.println("Is Valid BST: " + isValidBST(bst));
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD RECURSIVE SEARCH PROBLEMS:");
        
        // Word Search II
        char[][] wordBoard = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        List<String> foundWords = findWords(wordBoard, words);
        System.out.println("Found words: " + foundWords);
        
        // Tree Serialization
        TreeNode serializeTree = new TreeNode(1);
        serializeTree.left = new TreeNode(2);
        serializeTree.right = new TreeNode(3);
        serializeTree.right.left = new TreeNode(4);
        serializeTree.right.right = new TreeNode(5);
        
        String serialized = serialize(serializeTree);
        System.out.println("Serialized tree: " + serialized);
        
        TreeNode deserialized = deserialize(serialized);
        System.out.println("Deserialized tree root value: " + deserialized.val);
        
        // Performance analysis
        System.out.println("\n⚡ COMPLEXITY ANALYSIS:");
        System.out.println("Path Sum: O(n) time, O(h) space - tree traversal");
        System.out.println("Number of Islands: O(m×n) time and space - grid DFS");
        System.out.println("Sudoku Solver: O(9^cells) time - constraint satisfaction");
        System.out.println("Word Search II: O(m×n×4^L) time - trie + DFS");
        
        System.out.println("\n✅ Key Recursive Search Principles:");
        System.out.println("1. Define clear base cases for termination");
        System.out.println("2. Track visited states to avoid infinite loops");
        System.out.println("3. Validate boundaries and constraints early");
        System.out.println("4. Use appropriate data structures (visited sets, tries)");
        System.out.println("5. Consider backtracking for state restoration");
        System.out.println("6. Optimize with pruning and early termination");
        System.out.println("7. Choose DFS vs BFS based on problem requirements");
    }
    
    private static void printSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
} 