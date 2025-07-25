package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 🧮 EXPRESSION EVALUATION PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of expression evaluation algorithms
 * for parsing and computing arithmetic expressions, handling operator precedence,
 * managing nested structures, and evaluating various expression formats. These
 * algorithms transform complex expression parsing into systematic, stack-based
 * computation processes.
 * 
 * Pattern Focus: Expression parsing, operator precedence, nested structure handling
 * Time Complexity: Generally O(n) for single-pass evaluation
 * Space Complexity: O(n) for stack storage and intermediate results
 */
public class ExpressionEvaluation {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Expression Evaluation Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Basic Calculator II
     * 
     * Problem: Evaluate expression with +, -, *, / (no parentheses)
     * LeetCode: https://leetcode.com/problems/basic-calculator-ii/
     * 
     * Approach: Stack-based evaluation with immediate operator processing
     * - Process numbers and operators left to right
     * - Handle multiplication/division immediately
     * - Stack addition/subtraction for final sum
     * 
     * Time: O(n), Space: O(n)
     */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char operator = '+'; // Initialize with addition
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0'); // Build multi-digit number
            }
            
            // Process when we hit an operator or reach end of string
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                operator = c;
                num = 0;
            }
        }
        
        // Sum all values in stack
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Simplify Path
     * 
     * Problem: Simplify absolute path for Unix-style file system
     * LeetCode: https://leetcode.com/problems/simplify-path/
     * 
     * Approach: Stack-based path component processing
     * - Split path by '/' and process each component
     * - Skip empty strings and current directory '.'
     * - Pop for parent directory '..'
     * - Build canonical path from stack
     * 
     * Time: O(n), Space: O(n)
     */
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        
        for (String component : components) {
            if (component.equals("") || component.equals(".")) {
                continue; // Skip empty and current directory
            } else if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); // Go up one directory
                }
            } else {
                stack.push(component); // Regular directory name
            }
        }
        
        // Build result path
        if (stack.isEmpty()) {
            return "/";
        }
        
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        return result.toString();
    }
    
    /**
     * 🟢 EASY: Decode String
     * 
     * Problem: Decode string with nested patterns k[encoded_string]
     * LeetCode: https://leetcode.com/problems/decode-string/
     * 
     * Approach: Stack-based nested structure handling
     * - Use two stacks: one for counts, one for strings
     * - Build current string until '[' then push context
     * - On ']' pop context and repeat current string
     * 
     * Time: O(maxK * n), Space: O(n) where maxK is maximum k value
     */
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder current = new StringBuilder();
        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0'); // Build multi-digit count
            } else if (c == '[') {
                // Save current context and start new level
                countStack.push(count);
                stringStack.push(current);
                count = 0;
                current = new StringBuilder();
            } else if (c == ']') {
                // Restore context and repeat current string
                int repeatCount = countStack.pop();
                StringBuilder temp = stringStack.pop();
                for (int i = 0; i < repeatCount; i++) {
                    temp.append(current);
                }
                current = temp;
            } else {
                current.append(c); // Regular character
            }
        }
        
        return current.toString();
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Expression Evaluation
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Basic Calculator
     * 
     * Problem: Evaluate expression with +, -, and parentheses
     * LeetCode: https://leetcode.com/problems/basic-calculator/
     * 
     * Approach: Stack-based parentheses handling
     * - Use stack to save state when entering parentheses
     * - Track current result and sign
     * - Handle nested parentheses with state restoration
     * 
     * Time: O(n), Space: O(n)
     */
    public static int calculateWithParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int num = 0;
        int sign = 1; // 1 for positive, -1 for negative
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                // Save current state
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Restore previous state
                result += sign * num;
                num = 0;
                result *= stack.pop(); // Previous sign
                result += stack.pop(); // Previous result
            }
        }
        
        return result + (sign * num);
    }
    
    /**
     * 🟡 MEDIUM: Evaluate Division
     * 
     * Problem: Evaluate division expressions with variable equations
     * LeetCode: https://leetcode.com/problems/evaluate-division/
     * 
     * Approach: Graph-based evaluation with DFS
     * - Build graph where edges represent division relationships
     * - Use DFS to find path between query variables
     * - Multiply edge weights along path for result
     * 
     * Time: O(M * N) where M = equations, N = queries, Space: O(M)
     */
    public static double[] calcEquation(List<List<String>> equations, 
                                       double[] values, 
                                       List<List<String>> queries) {
        // Build graph of variable relationships
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        // Add equations to graph
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];
            
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, value);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / value);
        }
        
        // Process queries
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String source = queries.get(i).get(0);
            String target = queries.get(i).get(1);
            results[i] = dfsEvaluate(graph, source, target, new HashSet<>());
        }
        
        return results;
    }
    
    private static double dfsEvaluate(Map<String, Map<String, Double>> graph,
                                     String source, String target, Set<String> visited) {
        // Variable not in graph
        if (!graph.containsKey(source) || !graph.containsKey(target)) {
            return -1.0;
        }
        
        // Same variable
        if (source.equals(target)) {
            return 1.0;
        }
        
        visited.add(source);
        
        // Explore neighbors
        for (Map.Entry<String, Double> neighbor : graph.get(source).entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                double result = dfsEvaluate(graph, neighbor.getKey(), target, visited);
                if (result != -1.0) {
                    return result * neighbor.getValue();
                }
            }
        }
        
        visited.remove(source);
        return -1.0;
    }
    
    /**
     * 🟡 MEDIUM: Evaluate Reverse Polish Notation (Bonus)
     * 
     * Problem: Evaluate expression in postfix notation
     * 
     * Approach: Stack-based postfix evaluation
     * - Operands: push to stack
     * - Operators: pop two operands, compute, push result
     * - Final result is last item in stack
     * 
     * Time: O(n), Space: O(n)
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();
                int result = performOperation(a, b, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || 
               "*".equals(token) || "/".equals(token);
    }
    
    private static int performOperation(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Expression Evaluation
    // ============================================================================
    
    /**
     * 🔴 HARD: Basic Calculator III
     * 
     * Problem: Evaluate expression with +, -, *, /, and parentheses
     * 
     * Approach: Recursive descent parsing with precedence
     * - Parse expressions with proper operator precedence
     * - Handle parentheses recursively
     * - Separate parsing from evaluation for clarity
     * 
     * Time: O(n), Space: O(n)
     */
    public static int calculateAdvanced(String s) {
        return new AdvancedCalculator().calculate(s);
    }
    
    static class AdvancedCalculator {
        private String expression;
        private int index;
        
        public int calculate(String s) {
            expression = s.replaceAll(" ", "");
            index = 0;
            return parseExpression();
        }
        
        private int parseExpression() {
            int result = parseTerm();
            
            while (index < expression.length()) {
                char op = expression.charAt(index);
                if (op == '+' || op == '-') {
                    index++;
                    int term = parseTerm();
                    result = (op == '+') ? result + term : result - term;
                } else {
                    break;
                }
            }
            
            return result;
        }
        
        private int parseTerm() {
            int result = parseFactor();
            
            while (index < expression.length()) {
                char op = expression.charAt(index);
                if (op == '*' || op == '/') {
                    index++;
                    int factor = parseFactor();
                    result = (op == '*') ? result * factor : result / factor;
                } else {
                    break;
                }
            }
            
            return result;
        }
        
        private int parseFactor() {
            if (index < expression.length() && expression.charAt(index) == '(') {
                index++; // Skip '('
                int result = parseExpression();
                index++; // Skip ')'
                return result;
            }
            
            // Parse number (including negative numbers)
            int start = index;
            if (index < expression.length() && expression.charAt(index) == '-') {
                index++;
            }
            while (index < expression.length() && 
                   Character.isDigit(expression.charAt(index))) {
                index++;
            }
            
            return Integer.parseInt(expression.substring(start, index));
        }
    }
    
    /**
     * 🔴 HARD: Expression Add Operators
     * 
     * Problem: Add operators (+, -, *) to make target value
     * 
     * Approach: Backtracking with expression evaluation
     * - Try all possible operator placements
     * - Handle multiplication precedence correctly
     * - Track previous operand for multiplication handling
     * 
     * Time: O(4^n), Space: O(n)
     */
    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) return result;
        
        backtrackOperators(num, target, 0, 0, 0, "", result);
        return result;
    }
    
    private static void backtrackOperators(String num, int target, int index,
                                          long currentValue, long previousOperand,
                                          String expression, List<String> result) {
        if (index == num.length()) {
            if (currentValue == target) {
                result.add(expression);
            }
            return;
        }
        
        for (int i = index; i < num.length(); i++) {
            String operandStr = num.substring(index, i + 1);
            long operand = Long.parseLong(operandStr);
            
            // Skip numbers with leading zeros (except single digit 0)
            if (operandStr.length() > 1 && operandStr.charAt(0) == '0') {
                break;
            }
            
            if (index == 0) {
                // First operand (no operator before it)
                backtrackOperators(num, target, i + 1, operand, operand,
                                 operandStr, result);
            } else {
                // Try addition
                backtrackOperators(num, target, i + 1, 
                                 currentValue + operand, operand,
                                 expression + "+" + operandStr, result);
                
                // Try subtraction
                backtrackOperators(num, target, i + 1,
                                 currentValue - operand, -operand,
                                 expression + "-" + operandStr, result);
                
                // Try multiplication (handle precedence)
                backtrackOperators(num, target, i + 1,
                                 currentValue - previousOperand + previousOperand * operand,
                                 previousOperand * operand,
                                 expression + "*" + operandStr, result);
            }
        }
    }
    
    /**
     * 🔴 HARD: Different Ways to Add Parentheses
     * 
     * Problem: Compute all possible results from different parenthesizations
     * 
     * Approach: Divide and conquer with memoization
     * - Split expression at each operator
     * - Recursively evaluate left and right parts
     * - Combine results from all possible splits
     * 
     * Time: O(4^n / n^(3/2)), Space: O(4^n / n^(3/2))
     */
    public static List<Integer> diffWaysToCompute(String expression) {
        Map<String, List<Integer>> memo = new HashMap<>();
        return computeWithMemo(expression, memo);
    }
    
    private static List<Integer> computeWithMemo(String expression, 
                                               Map<String, List<Integer>> memo) {
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }
        
        List<Integer> result = new ArrayList<>();
        
        // Try to split at each operator
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = expression.substring(0, i);
                String right = expression.substring(i + 1);
                
                List<Integer> leftResults = computeWithMemo(left, memo);
                List<Integer> rightResults = computeWithMemo(right, memo);
                
                // Combine all possible results
                for (int leftVal : leftResults) {
                    for (int rightVal : rightResults) {
                        switch (c) {
                            case '+': result.add(leftVal + rightVal); break;
                            case '-': result.add(leftVal - rightVal); break;
                            case '*': result.add(leftVal * rightVal); break;
                        }
                    }
                }
            }
        }
        
        // Base case: expression is a single number
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }
        
        memo.put(expression, result);
        return result;
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🧮 EXPRESSION EVALUATION PATTERN - PRACTICE PROBLEMS");
        System.out.println("====================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY EXPRESSION EVALUATION PROBLEMS:");
        
        // Basic Calculator II
        String expr1 = "3+2*2";
        int result1 = calculate(expr1);
        System.out.println("Basic Calculator II (" + expr1 + "): " + result1); // 7
        
        String expr2 = " 3/2 ";
        int result2 = calculate(expr2);
        System.out.println("Basic Calculator II (" + expr2 + "): " + result2); // 1
        
        // Simplify Path
        String path1 = "/home/";
        String simplified1 = simplifyPath(path1);
        System.out.println("Simplify Path (" + path1 + "): " + simplified1); // "/home"
        
        String path2 = "/../";
        String simplified2 = simplifyPath(path2);
        System.out.println("Simplify Path (" + path2 + "): " + simplified2); // "/"
        
        String path3 = "/home//foo/";
        String simplified3 = simplifyPath(path3);
        System.out.println("Simplify Path (" + path3 + "): " + simplified3); // "/home/foo"
        
        // Decode String
        String encoded1 = "3[a]2[bc]";
        String decoded1 = decodeString(encoded1);
        System.out.println("Decode String (" + encoded1 + "): " + decoded1); // "aaabcbc"
        
        String encoded2 = "3[a2[c]]";
        String decoded2 = decodeString(encoded2);
        System.out.println("Decode String (" + encoded2 + "): " + decoded2); // "accaccacc"
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM EXPRESSION EVALUATION PROBLEMS:");
        
        // Basic Calculator
        String expr3 = "1 + 1";
        int result3 = calculateWithParentheses(expr3);
        System.out.println("Basic Calculator (" + expr3 + "): " + result3); // 2
        
        String expr4 = " 2-1 + 2 ";
        int result4 = calculateWithParentheses(expr4);
        System.out.println("Basic Calculator (" + expr4 + "): " + result4); // 3
        
        String expr5 = "(1+(4+5+2)-3)+(6+8)";
        int result5 = calculateWithParentheses(expr5);
        System.out.println("Basic Calculator (" + expr5 + "): " + result5); // 23
        
        // Evaluate Division
        List<List<String>> equations = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("b", "c")
        );
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
            Arrays.asList("a", "c"),
            Arrays.asList("b", "a"),
            Arrays.asList("a", "e"),
            Arrays.asList("a", "a"),
            Arrays.asList("x", "x")
        );
        double[] divResults = calcEquation(equations, values, queries);
        System.out.println("Evaluate Division: " + Arrays.toString(divResults)); // [6.0, 0.5, -1.0, 1.0, -1.0]
        
        // Evaluate RPN
        String[] tokens = {"2", "1", "+", "3", "*"};
        int rpnResult = evalRPN(tokens);
        System.out.println("Evaluate RPN " + Arrays.toString(tokens) + ": " + rpnResult); // 9
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD EXPRESSION EVALUATION PROBLEMS:");
        
        // Basic Calculator III
        String expr6 = "1 + 1";
        int result6 = calculateAdvanced(expr6);
        System.out.println("Basic Calculator III (" + expr6 + "): " + result6); // 2
        
        String expr7 = " 6-4 / 2 ";
        int result7 = calculateAdvanced(expr7);
        System.out.println("Basic Calculator III (" + expr7 + "): " + result7); // 4
        
        String expr8 = "2*(5+5*2)/3+(6/2+8)";
        int result8 = calculateAdvanced(expr8);
        System.out.println("Basic Calculator III (" + expr8 + "): " + result8); // 21
        
        // Expression Add Operators
        String num = "123";
        int target = 6;
        List<String> operators = addOperators(num, target);
        System.out.println("Add Operators (" + num + ", " + target + "): " + operators); // ["1+2+3", "1*2*3"]
        
        // Different Ways to Add Parentheses
        String expr9 = "2-1-1";
        List<Integer> ways = diffWaysToCompute(expr9);
        System.out.println("Different Ways (" + expr9 + "): " + ways); // [0, 2]
        
        String expr10 = "2*3-4*5";
        List<Integer> ways2 = diffWaysToCompute(expr10);
        System.out.println("Different Ways (" + expr10 + "): " + ways2); // [-34, -14, -10, -10, 10]
        
        System.out.println("\n✅ Key Expression Evaluation Principles:");
        System.out.println("1. Parse expressions systematically (left-to-right or recursive descent)");
        System.out.println("2. Handle operator precedence correctly (* / before + -)");
        System.out.println("3. Use stacks for managing operands and operators");
        System.out.println("4. Process nested structures (parentheses) with state saving/restoration");
        System.out.println("5. Handle edge cases: division by zero, overflow, invalid expressions");
        System.out.println("6. Separate parsing logic from evaluation for complex expressions");
        System.out.println("7. Use appropriate data structures (graphs for variable relationships)");
        System.out.println("8. Consider memoization for recursive expression evaluation");
    }
} 