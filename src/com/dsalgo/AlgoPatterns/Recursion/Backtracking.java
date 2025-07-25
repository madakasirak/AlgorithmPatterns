package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * 🔙 BACKTRACKING PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of backtracking algorithms.
 * Backtracking systematically explores all potential solutions by trying choices
 * and undoing them when they lead to dead ends, ensuring complete exploration
 * of the solution space while pruning invalid paths early.
 * 
 * Pattern Focus: Exhaustive search, constraint satisfaction, solution generation
 * Time Complexity: Often exponential O(2^n) or factorial O(n!)
 * Space Complexity: O(depth) for recursion + O(solutions) for result storage
 */
public class Backtracking {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Backtracking Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Generate Parentheses
     * 
     * Problem: Generate all combinations of well-formed parentheses for n pairs
     * LeetCode: https://leetcode.com/problems/generate-parentheses/
     * 
     * Approach: Backtracking with constraint validation
     * - Track open and close parentheses count
     * - Add open parenthesis if count < n
     * - Add close parenthesis if close count < open count
     * 
     * Time: O(4^n / √n) (Catalan number), Space: O(4^n / √n)
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesesHelper(result, "", 0, 0, n);
        return result;
    }
    
    private static void generateParenthesesHelper(List<String> result, String current, 
                                                int open, int close, int max) {
        // Base case: completed valid combination
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        
        // Add opening parenthesis if we haven't reached the limit
        if (open < max) {
            generateParenthesesHelper(result, current + "(", open + 1, close, max);
        }
        
        // Add closing parenthesis if it won't make the combination invalid
        if (close < open) {
            generateParenthesesHelper(result, current + ")", open, close + 1, max);
        }
    }
    
    /**
     * 🟢 EASY: Letter Combinations of a Phone Number
     * 
     * Problem: Return all possible letter combinations for given phone number digits
     * LeetCode: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     * 
     * Approach: Decision tree backtracking
     * - For each digit, try all corresponding letters
     * - Build combination character by character
     * - No explicit backtracking needed (immutable strings)
     * 
     * Time: O(3^m × 4^n) where m,n are counts of digits, Space: O(3^m × 4^n)
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        
        String[] mapping = {
            "",     "",     "abc",  "def",
            "ghi",  "jkl",  "mno",  "pqrs",
            "tuv",  "wxyz"
        };
        
        letterCombinationsHelper(digits, 0, "", result, mapping);
        return result;
    }
    
    private static void letterCombinationsHelper(String digits, int index, 
                                               String current, List<String> result, 
                                               String[] mapping) {
        // Base case: processed all digits
        if (index == digits.length()) {
            result.add(current);
            return;
        }
        
        // Get letters for current digit
        String letters = mapping[digits.charAt(index) - '0'];
        
        // Try each letter for current digit
        for (char letter : letters.toCharArray()) {
            letterCombinationsHelper(digits, index + 1, current + letter, result, mapping);
        }
    }
    
    /**
     * 🟢 EASY: Subsets
     * 
     * Problem: Generate all possible subsets (power set) of given array
     * 
     * Approach: Include/exclude backtracking
     * - For each element, choose to include or exclude
     * - Generate all 2^n combinations
     * 
     * Time: O(2^n), Space: O(2^n)
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void subsetsHelper(int[] nums, int index, 
                                    List<Integer> current, 
                                    List<List<Integer>> result) {
        // Base case: processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Choice 1: exclude current element
        subsetsHelper(nums, index + 1, current, result);
        
        // Choice 2: include current element
        current.add(nums[index]);
        subsetsHelper(nums, index + 1, current, result);
        current.remove(current.size() - 1); // Backtrack
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Backtracking Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Combination Sum
     * 
     * Problem: Find all unique combinations that sum to target
     * LeetCode: https://leetcode.com/problems/combination-sum/
     * 
     * Approach: Backtracking with pruning
     * - Try each candidate recursively
     * - Allow reuse of same candidate
     * - Prune when sum exceeds target
     * 
     * Time: O(2^target), Space: O(target) for recursion depth
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Optional optimization for early pruning
        combinationSumHelper(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void combinationSumHelper(int[] candidates, int target, int start,
                                           List<Integer> current, 
                                           List<List<Integer>> result) {
        // Base case: found valid combination
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Base case: target exceeded
        if (target < 0) {
            return;
        }
        
        // Try each candidate starting from 'start' index
        for (int i = start; i < candidates.length; i++) {
            // Early pruning if candidates are sorted
            if (candidates[i] > target) break;
            
            // Make choice
            current.add(candidates[i]);
            
            // Recurse (allow reuse of same element: start from i)
            combinationSumHelper(candidates, target - candidates[i], i, current, result);
            
            // Undo choice (backtrack)
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * 🟡 MEDIUM: Permutations
     * 
     * Problem: Generate all possible permutations of given array
     * 
     * Approach: Backtracking with used array
     * - Track which elements are used
     * - Try each unused element at each position
     * - Backtrack by marking elements as unused
     * 
     * Time: O(n!), Space: O(n) for recursion + used array
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums, new ArrayList<>(), result, new boolean[nums.length]);
        return result;
    }
    
    private static void permuteHelper(int[] nums, List<Integer> current, 
                                    List<List<Integer>> result, boolean[] used) {
        // Base case: permutation complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each unused element
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Make choice
                current.add(nums[i]);
                used[i] = true;
                
                // Recurse
                permuteHelper(nums, current, result, used);
                
                // Undo choice (backtrack)
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
    
    /**
     * 🟡 MEDIUM: Word Search
     * 
     * Problem: Find if word exists in 2D board by connecting adjacent cells
     * LeetCode: https://leetcode.com/problems/word-search/
     * 
     * Approach: DFS backtracking on grid
     * - Try starting from each cell
     * - Mark visited cells and restore after exploration
     * - Explore all 4 directions
     * 
     * Time: O(m×n×4^L) where L is word length, Space: O(L) for recursion
     */
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        // Try starting from each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (wordSearchHelper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean wordSearchHelper(char[][] board, String word, 
                                          int row, int col, int index) {
        // Base case: word completely found
        if (index == word.length()) {
            return true;
        }
        
        // Base case: out of bounds or character mismatch
        if (row < 0 || row >= board.length || 
            col < 0 || col >= board[0].length || 
            board[row][col] != word.charAt(index)) {
            return false;
        }
        
        // Mark current cell as visited
        char original = board[row][col];
        board[row][col] = '#'; // Temporary marker
        
        // Explore all 4 directions
        boolean found = wordSearchHelper(board, word, row + 1, col, index + 1) ||
                       wordSearchHelper(board, word, row - 1, col, index + 1) ||
                       wordSearchHelper(board, word, row, col + 1, index + 1) ||
                       wordSearchHelper(board, word, row, col - 1, index + 1);
        
        // Restore original character (backtrack)
        board[row][col] = original;
        
        return found;
    }
    
    /**
     * 🟡 MEDIUM: Palindrome Partitioning
     * 
     * Problem: Partition string into palindromic substrings
     * 
     * Approach: Backtracking with palindrome checking
     * - Try all possible partitions at each position
     * - Check if substring is palindrome before recursing
     * - Build result incrementally
     * 
     * Time: O(2^n × n), Space: O(n) for recursion depth
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitionHelper(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void partitionHelper(String s, int start, 
                                      List<String> current, 
                                      List<List<String>> result) {
        // Base case: processed entire string
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try all possible end positions
        for (int end = start; end < s.length(); end++) {
            String substring = s.substring(start, end + 1);
            
            // Only proceed if substring is palindrome
            if (isPalindrome(substring)) {
                // Make choice
                current.add(substring);
                
                // Recurse with remaining string
                partitionHelper(s, end + 1, current, result);
                
                // Undo choice (backtrack)
                current.remove(current.size() - 1);
            }
        }
    }
    
    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Backtracking Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: N-Queens
     * 
     * Problem: Place N queens on NxN chessboard so no two queens attack each other
     * LeetCode: https://leetcode.com/problems/n-queens/
     * 
     * Approach: Constraint satisfaction backtracking
     * - Place queens row by row
     * - Check column and diagonal conflicts
     * - Backtrack when no valid placement found
     * 
     * Time: O(N!), Space: O(N) for recursion depth
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> queens = new ArrayList<>(); // queens[i] = column of queen in row i
        solveNQueensHelper(n, 0, queens, result);
        return result;
    }
    
    private static void solveNQueensHelper(int n, int row, List<Integer> queens, 
                                         List<List<String>> result) {
        // Base case: all queens placed
        if (row == n) {
            result.add(generateBoard(queens, n));
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isValidQueenPlacement(queens, row, col)) {
                // Make choice
                queens.add(col);
                
                // Recurse to next row
                solveNQueensHelper(n, row + 1, queens, result);
                
                // Undo choice (backtrack)
                queens.remove(queens.size() - 1);
            }
        }
    }
    
    private static boolean isValidQueenPlacement(List<Integer> queens, int row, int col) {
        for (int i = 0; i < queens.size(); i++) {
            int placedCol = queens.get(i);
            
            // Check column conflict
            if (placedCol == col) return false;
            
            // Check diagonal conflicts
            if (Math.abs(i - row) == Math.abs(placedCol - col)) return false;
        }
        return true;
    }
    
    private static List<String> generateBoard(List<Integer> queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queens.get(i) == j) {
                    row.append('Q');
                } else {
                    row.append('.');
                }
            }
            board.add(row.toString());
        }
        return board;
    }
    
    /**
     * 🔴 HARD: Sudoku Solver
     * 
     * Problem: Solve a 9x9 Sudoku puzzle
     * 
     * Approach: Constraint propagation with backtracking
     * - Find empty cells and try digits 1-9
     * - Check row, column, and 3x3 box constraints
     * - Backtrack when no valid digit found
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
                            
                            // Recurse
                            if (solveSudokuHelper(board)) {
                                return true;
                            }
                            
                            // Undo choice (backtrack)
                            board[row][col] = '.';
                        }
                    }
                    return false; // No valid digit found
                }
            }
        }
        return true; // All cells filled
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
     * 🔴 HARD: Expression Add Operators
     * 
     * Problem: Add operators +, -, * to string of digits to get target value
     * 
     * Approach: Backtracking with expression evaluation
     * - Try all possible operator placements
     * - Handle operator precedence correctly
     * - Track current value and last operand for multiplication
     * 
     * Time: O(3^n), Space: O(n) for recursion depth
     */
    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        addOperatorsHelper(num, target, 0, "", 0, 0, result);
        return result;
    }
    
    private static void addOperatorsHelper(String num, int target, int index, 
                                         String expression, long value, long lastOperand,
                                         List<String> result) {
        // Base case: processed all digits
        if (index == num.length()) {
            if (value == target) {
                result.add(expression);
            }
            return;
        }
        
        // Try all possible number formations starting from current index
        for (int i = index; i < num.length(); i++) {
            String currentStr = num.substring(index, i + 1);
            
            // Skip numbers with leading zeros (except single digit 0)
            if (currentStr.length() > 1 && currentStr.charAt(0) == '0') {
                break;
            }
            
            long currentNum = Long.parseLong(currentStr);
            
            if (index == 0) {
                // First number, no operator needed
                addOperatorsHelper(num, target, i + 1, currentStr, currentNum, currentNum, result);
            } else {
                // Try addition
                addOperatorsHelper(num, target, i + 1, expression + "+" + currentStr, 
                                 value + currentNum, currentNum, result);
                
                // Try subtraction
                addOperatorsHelper(num, target, i + 1, expression + "-" + currentStr, 
                                 value - currentNum, -currentNum, result);
                
                // Try multiplication (handle precedence)
                addOperatorsHelper(num, target, i + 1, expression + "*" + currentStr, 
                                 value - lastOperand + lastOperand * currentNum, 
                                 lastOperand * currentNum, result);
            }
        }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🔙 BACKTRACKING PATTERN - PRACTICE PROBLEMS");
        System.out.println("===========================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY BACKTRACKING PROBLEMS:");
        
        // Generate Parentheses
        List<String> parentheses = generateParenthesis(3);
        System.out.println("Generate Parentheses (n=3): " + parentheses);
        
        // Letter Combinations
        List<String> letterCombos = letterCombinations("23");
        System.out.println("Letter Combinations ('23'): " + letterCombos);
        
        // Subsets
        List<List<Integer>> subsetResult = subsets(new int[]{1, 2, 3});
        System.out.println("Subsets of [1,2,3]: " + subsetResult);
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM BACKTRACKING PROBLEMS:");
        
        // Combination Sum
        List<List<Integer>> combSum = combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println("Combination Sum ([2,3,6,7], target=7): " + combSum);
        
        // Permutations
        List<List<Integer>> perms = permute(new int[]{1, 2, 3});
        System.out.println("Permutations of [1,2,3]: " + perms);
        
        // Word Search
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        System.out.println("Word Search ('ABCCED'): " + exist(board, "ABCCED"));
        System.out.println("Word Search ('SEE'): " + exist(board, "SEE"));
        System.out.println("Word Search ('ABCB'): " + exist(board, "ABCB"));
        
        // Palindrome Partitioning
        List<List<String>> palindromes = partition("aab");
        System.out.println("Palindrome Partitioning ('aab'): " + palindromes);
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD BACKTRACKING PROBLEMS:");
        
        // N-Queens
        List<List<String>> nQueens = solveNQueens(4);
        System.out.println("4-Queens solutions count: " + nQueens.size());
        if (!nQueens.isEmpty()) {
            System.out.println("First 4-Queens solution:");
            for (String row : nQueens.get(0)) {
                System.out.println("  " + row);
            }
        }
        
        // Sudoku Solver (create a simple puzzle)
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
        
        // Expression Add Operators
        List<String> expressions = addOperators("123", 6);
        System.out.println("Add Operators ('123', target=6): " + expressions);
        
        // Performance analysis
        System.out.println("\n⚡ COMPLEXITY ANALYSIS:");
        System.out.println("Generate Parentheses: O(4^n/√n) - Catalan number");
        System.out.println("Letter Combinations: O(3^m × 4^n) - Exponential in digits");
        System.out.println("Combination Sum: O(2^target) - Exponential in target");
        System.out.println("N-Queens: O(N!) - Factorial complexity");
        System.out.println("Word Search: O(m×n×4^L) - Grid size × word length");
        
        System.out.println("\n✅ Key Backtracking Principles:");
        System.out.println("1. Make choice, explore, backtrack (undo choice)");
        System.out.println("2. Check constraints early for efficient pruning");
        System.out.println("3. Always copy solutions, never store references");
        System.out.println("4. Use appropriate data structures for state management");
        System.out.println("5. Order choices intelligently to find solutions faster");
        System.out.println("6. Consider constraint propagation for optimization");
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