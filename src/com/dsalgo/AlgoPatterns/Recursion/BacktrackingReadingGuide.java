package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * 🔙 BACKTRACKING PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BACKTRACKING?
 * ============================================================================
 * 
 * Backtracking is a systematic method for solving problems by exploring all
 * potential solutions through trial and error. It builds solutions incrementally
 * and abandons partial solutions (backtracks) as soon as it's determined that
 * they cannot lead to a valid complete solution.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. EXPLORE: Try a choice and move forward
 * 2. VALIDATE: Check if current path can lead to valid solution
 * 3. BACKTRACK: If invalid, undo choice and try alternatives
 * 4. REPEAT: Continue until all possibilities explored
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BACKTRACKING
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding all solutions to constraint satisfaction problems
 * - Generating permutations, combinations, and subsets
 * - Puzzle solving (N-Queens, Sudoku, maze solving)
 * - Game playing with decision trees
 * - Path finding with obstacles and constraints
 * - Configuration space exploration
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find all possible solutions"
 * - "Generate all permutations/combinations"
 * - "Explore all paths"
 * - "Satisfy constraints"
 * - "Place/arrange elements with restrictions"
 * - "Try different possibilities"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Only need one solution (use greedy or DP)
 * - Problem has optimal substructure without exploration (use DP)
 * - Simple enumeration without constraints
 * - Performance critical with large search spaces
 * 
 * ============================================================================
 * 🔄 BACKTRACKING VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ COMBINATORIAL BACKTRACKING
 * - Generate combinations, permutations, subsets
 * - Examples: Combination Sum, Generate Parentheses
 * 
 * 2️⃣ CONSTRAINT SATISFACTION BACKTRACKING
 * - Solve puzzles with placement constraints
 * - Examples: N-Queens, Sudoku Solver
 * 
 * 3️⃣ PATH EXPLORATION BACKTRACKING
 * - Find paths through graphs/grids with constraints
 * - Examples: Word Search, Maze Solving
 * 
 * 4️⃣ DECISION TREE BACKTRACKING
 * - Explore all possible decisions at each step
 * - Examples: Letter Combinations, Phone Number Mapping
 * 
 * 5️⃣ OPTIMIZATION BACKTRACKING
 * - Find optimal solution among all valid solutions
 * - Examples: Traveling Salesman, Knapsack variants
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS
 * ============================================================================
 * 
 * 🔹 BACKTRACKING TEMPLATE:
 * ```
 * function backtrack(currentState, choices, result) {
 *     // Base case: valid solution found
 *     if (isValidSolution(currentState)) {
 *         result.add(copy(currentState));
 *         return;
 *     }
 *     
 *     // Explore all possible choices
 *     for (choice : getValidChoices(currentState, choices)) {
 *         // Make choice
 *         makeChoice(currentState, choice);
 *         
 *         // Recurse with updated state
 *         backtrack(currentState, choices, result);
 *         
 *         // Undo choice (backtrack)
 *         undoChoice(currentState, choice);
 *     }
 * }
 * ```
 * 
 * 🔹 KEY COMPONENTS:
 * - Current State: Partial solution being built
 * - Valid Choices: Available options at current step
 * - Constraint Checking: Validation of partial solutions
 * - State Management: Making and undoing choices
 * 
 * 🔹 COMPLEXITY CHARACTERISTICS:
 * - Time: Often exponential O(k^n) or factorial O(n!)
 * - Space: O(depth) for recursion stack + solution storage
 * - Pruning can significantly reduce actual runtime
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY PROBLEM STRUCTURE
 * - What are we trying to generate or find?
 * - What constraints must be satisfied?
 * - What constitutes a valid complete solution?
 * 
 * STEP 2: DEFINE STATE REPRESENTATION
 * - How to represent partial solutions?
 * - What information needed to make next choice?
 * - How to efficiently check constraints?
 * 
 * STEP 3: IDENTIFY CHOICE POINTS
 * - What decisions need to be made at each step?
 * - How to generate valid choices from current state?
 * - What's the natural order for exploring choices?
 * 
 * STEP 4: IMPLEMENT CONSTRAINT CHECKING
 * - Early validation to prune invalid paths
 * - Efficient constraint evaluation
 * - Clear termination conditions
 * 
 * STEP 5: OPTIMIZE WITH PRUNING
 * - Identify early termination conditions
 * - Use heuristics to order choices
 * - Implement constraint propagation
 * 
 * ============================================================================
 * 🎨 BACKTRACKING TEMPLATES
 * ============================================================================
 */
public class BacktrackingReadingGuide {
    
    /**
     * 🔙 BASIC BACKTRACKING TEMPLATE
     * General template for exploring all possibilities
     */
    public static void basicBacktrackingTemplate(List<Integer> current, 
                                                List<List<Integer>> result, 
                                                int[] choices) {
        // Base case: valid solution found
        if (isValidSolution(current)) {
            result.add(new ArrayList<>(current)); // Make copy
            return;
        }
        
        // Try each possible choice
        for (int choice : choices) {
            // Check if choice is valid in current context
            if (isValidChoice(current, choice)) {
                // Make choice
                current.add(choice);
                
                // Recurse with updated state
                basicBacktrackingTemplate(current, result, choices);
                
                // Undo choice (backtrack)
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * 🎯 COMBINATION GENERATION TEMPLATE
     * Generate combinations with specific constraints
     */
    public static void combinationTemplate(int[] candidates, int target, 
                                         List<Integer> current, 
                                         List<List<Integer>> result, 
                                         int start) {
        // Base case: found valid combination
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Base case: target cannot be reached
        if (target < 0) {
            return;
        }
        
        // Try each candidate starting from 'start' index
        for (int i = start; i < candidates.length; i++) {
            // Make choice
            current.add(candidates[i]);
            
            // Recurse (allow reuse: i, no reuse: i+1)
            combinationTemplate(candidates, target - candidates[i], current, result, i);
            
            // Undo choice
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * 🔢 PERMUTATION GENERATION TEMPLATE
     * Generate all permutations of elements
     */
    public static void permutationTemplate(int[] nums, List<Integer> current, 
                                         List<List<Integer>> result, 
                                         boolean[] used) {
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
                permutationTemplate(nums, current, result, used);
                
                // Undo choice
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
    
    /**
     * 🏁 N-QUEENS TEMPLATE
     * Constraint satisfaction with placement rules
     */
    public static void nQueensTemplate(int n, int row, List<Integer> queens, 
                                     List<List<String>> result) {
        // Base case: all queens placed
        if (row == n) {
            result.add(generateBoard(queens, n));
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            // Check if placement is valid
            if (isValidQueenPlacement(queens, row, col)) {
                // Make choice
                queens.add(col);
                
                // Recurse to next row
                nQueensTemplate(n, row + 1, queens, result);
                
                // Undo choice
                queens.remove(queens.size() - 1);
            }
        }
    }
    
    /**
     * 🔍 WORD SEARCH TEMPLATE
     * Path exploration with grid constraints
     */
    public static boolean wordSearchTemplate(char[][] board, String word, 
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
        boolean found = wordSearchTemplate(board, word, row + 1, col, index + 1) ||
                       wordSearchTemplate(board, word, row - 1, col, index + 1) ||
                       wordSearchTemplate(board, word, row, col + 1, index + 1) ||
                       wordSearchTemplate(board, word, row, col - 1, index + 1);
        
        // Restore original character (backtrack)
        board[row][col] = original;
        
        return found;
    }
    
    /**
     * 📞 PHONE NUMBER MAPPING TEMPLATE
     * Decision tree exploration
     */
    public static void phoneNumberTemplate(String digits, int index, 
                                         String current, List<String> result, 
                                         String[] mapping) {
        // Base case: processed all digits
        if (index == digits.length()) {
            result.add(current);
            return;
        }
        
        // Get letters for current digit
        String letters = mapping[digits.charAt(index) - '0'];
        
        // Try each letter
        for (char letter : letters.toCharArray()) {
            // Make choice (append letter)
            phoneNumberTemplate(digits, index + 1, current + letter, result, mapping);
            // No explicit undo needed as we pass new string
        }
    }
    
    /**
     * 🎪 SUBSET GENERATION TEMPLATE
     * Generate all possible subsets
     */
    public static void subsetTemplate(int[] nums, int index, 
                                    List<Integer> current, 
                                    List<List<Integer>> result) {
        // Base case: processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Choice 1: exclude current element
        subsetTemplate(nums, index + 1, current, result);
        
        // Choice 2: include current element
        current.add(nums[index]);
        subsetTemplate(nums, index + 1, current, result);
        current.remove(current.size() - 1); // Backtrack
    }
    
    // ============================================================================
    // 🔧 HELPER METHODS FOR TEMPLATES
    // ============================================================================
    
    private static boolean isValidSolution(List<Integer> current) {
        // Define what constitutes a valid solution
        return current.size() == 3; // Example constraint
    }
    
    private static boolean isValidChoice(List<Integer> current, int choice) {
        // Define validity criteria for a choice
        return !current.contains(choice); // Example: no duplicates
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
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Forgetting to Backtrack
     * Always undo changes made to shared state
     */
    public static void badBacktrackExample(List<Integer> current, List<List<Integer>> result) {
        // BAD: Forgetting to remove added element
        // current.add(choice);
        // backtrack(...);
        // Missing: current.remove(current.size() - 1);
        
        // GOOD: Always backtrack
        // current.add(choice);
        // backtrack(...);
        // current.remove(current.size() - 1);
    }
    
    /**
     * ❌ PITFALL 2: Not Copying Solutions
     * Result lists should contain copies, not references
     */
    public static void badCopyExample(List<Integer> current, List<List<Integer>> result) {
        // BAD: Adding reference to mutable list
        // result.add(current);
        
        // GOOD: Adding copy of current state
        // result.add(new ArrayList<>(current));
    }
    
    /**
     * ❌ PITFALL 3: Poor Constraint Checking
     * Check constraints early to avoid unnecessary exploration
     */
    public static void inefficientConstraintExample() {
        // BAD: Checking constraints only at the end
        // GOOD: Early constraint validation with pruning
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Early Constraint Validation
     * Check constraints as early as possible to prune search space
     */
    
    /**
     * 🎯 TIP 2: Order Choices Intelligently
     * Try most promising choices first (heuristics)
     */
    
    /**
     * 🎯 TIP 3: Use Appropriate Data Structures
     * Choose data structures that support efficient backtracking
     */
    
    /**
     * 🎯 TIP 4: Minimize State Changes
     * Reduce the cost of making and undoing choices
     */
    
    /**
     * 🎯 TIP 5: Consider Iterative Alternatives
     * For simple cases, iterative solutions might be more efficient
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC BACKTRACKING:
     * - Generate Parentheses
     * - Letter Combinations of Phone Number
     * - Combination Sum
     * - Subsets
     * 
     * 🟡 INTERMEDIATE BACKTRACKING:
     * - Permutations
     * - Word Search
     * - Combination Sum II
     * - Palindrome Partitioning
     * 
     * 🔴 ADVANCED BACKTRACKING:
     * - N-Queens
     * - Sudoku Solver
     * - Word Search II
     * - Expression Add Operators
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE BACKTRACKING WHEN:
     * ✅ Need to explore all possible solutions
     * ✅ Problem involves making sequential choices
     * ✅ Constraints can be checked incrementally
     * ✅ Solution space is finite (though may be large)
     * 
     * AVOID BACKTRACKING WHEN:
     * ❌ Only need one solution (use greedy/BFS)
     * ❌ Problem has optimal substructure (use DP)
     * ❌ Search space is too large without pruning
     * ❌ Constraints cannot be checked early
     * 
     * OPTIMIZE BACKTRACKING WITH:
     * 🚀 Early constraint checking and pruning
     * 🚀 Intelligent choice ordering (heuristics)
     * 🚀 Efficient state representation
     * 🚀 Memoization for overlapping subproblems
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Game AI (chess, checkers, game trees)
     * - Puzzle solving (Sudoku, crosswords, logic puzzles)
     * - Resource allocation with constraints
     * - Configuration management systems
     * - Automated testing (test case generation)
     * - Compiler optimization (register allocation)
     * - Scheduling with complex constraints
     * - Circuit design and verification
     */
    
    public static void main(String[] args) {
        System.out.println("🔙 BACKTRACKING PATTERN - READING GUIDE");
        System.out.println("=======================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Basic Backtracking Example
        List<List<Integer>> basicResult = new ArrayList<>();
        basicBacktrackingTemplate(new ArrayList<>(), basicResult, new int[]{1, 2, 3, 4, 5});
        System.out.println("Basic Backtracking (3-element combinations): " + basicResult.size() + " solutions");
        
        // Combination Example
        List<List<Integer>> combResult = new ArrayList<>();
        combinationTemplate(new int[]{2, 3, 6, 7}, 7, new ArrayList<>(), combResult, 0);
        System.out.println("Combination Sum examples: " + combResult);
        
        // Permutation Example
        List<List<Integer>> permResult = new ArrayList<>();
        permutationTemplate(new int[]{1, 2, 3}, new ArrayList<>(), permResult, new boolean[3]);
        System.out.println("Permutations of [1,2,3]: " + permResult);
        
        // N-Queens Example
        List<List<String>> queensResult = new ArrayList<>();
        nQueensTemplate(4, 0, new ArrayList<>(), queensResult);
        System.out.println("4-Queens solutions: " + queensResult.size());
        if (!queensResult.isEmpty()) {
            System.out.println("First solution:");
            for (String row : queensResult.get(0)) {
                System.out.println("  " + row);
            }
        }
        
        // Word Search Example
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        System.out.println("Word 'ABCCED' found: " + 
            wordSearchTemplate(board, "ABCCED", 0, 0, 0));
        
        // Phone Number Example
        List<String> phoneResult = new ArrayList<>();
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        phoneNumberTemplate("23", 0, "", phoneResult, mapping);
        System.out.println("Phone number '23' combinations: " + phoneResult);
        
        // Subset Example
        List<List<Integer>> subsetResult = new ArrayList<>();
        subsetTemplate(new int[]{1, 2, 3}, 0, new ArrayList<>(), subsetResult);
        System.out.println("Subsets of [1,2,3]: " + subsetResult);
        
        System.out.println("\n✅ Key Backtracking Principles:");
        System.out.println("1. Make choice, explore, backtrack");
        System.out.println("2. Check constraints early for pruning");
        System.out.println("3. Always undo state changes");
        System.out.println("4. Copy solutions, don't store references");
        System.out.println("5. Order choices intelligently");
        System.out.println("6. Consider iterative alternatives for simple cases");
        System.out.println("7. Use appropriate data structures for efficient state management");
    }
} 