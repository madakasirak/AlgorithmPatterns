package com.dsalgo.AlgoPatterns.Stack;

import java.util.*;

/**
 * 🎭 TWO STACKS PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of two-stack algorithms for
 * maintaining specific orders, performing efficient operations, and simulating
 * different data structure behaviors. These algorithms demonstrate how dual-stack
 * coordination can achieve functionality that would be difficult or inefficient
 * with a single stack, including queue simulation, auxiliary information tracking,
 * and specialized operation handling.
 * 
 * Pattern Focus: Dual-stack coordination, order maintenance, efficient operation simulation
 * Time Complexity: Often amortized O(1) for individual operations
 * Space Complexity: O(n) for dual-stack storage
 */
public class TwoStacks {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Two Stack Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Evaluate Reverse Polish Notation
     * 
     * Problem: Evaluate arithmetic expression in postfix notation
     * LeetCode: https://leetcode.com/problems/evaluate-reverse-polish-notation/
     * 
     * Approach: Single stack for operand storage
     * - Numbers: push to stack
     * - Operators: pop two operands, compute, push result
     * - Final result: last element in stack
     * 
     * Time: O(n), Space: O(n)
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                // Pop two operands (order matters for subtraction/division)
                int b = stack.pop();
                int a = stack.pop();
                int result = performOperation(a, b, token);
                stack.push(result);
            } else {
                // Push number to stack
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
    
    /**
     * 🟢 EASY: Min Stack
     * 
     * Problem: Design stack that supports push, pop, top, and retrieving minimum in O(1)
     * LeetCode: https://leetcode.com/problems/min-stack/
     * 
     * Approach: Two stacks - main stack and min tracking stack
     * - Main stack: stores all values
     * - Min stack: tracks minimum at each level
     * - Synchronization: both stacks maintained in parallel
     * 
     * Time: O(1) for all operations, Space: O(n)
     */
    public static class MinStack {
        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;
        
        public MinStack() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            mainStack.push(val);
            
            // Update minimum stack
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek()); // Maintain same size
            }
        }
        
        public void pop() {
            if (!mainStack.isEmpty()) {
                mainStack.pop();
                minStack.pop();
            }
        }
        
        public int top() {
            return mainStack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    /**
     * 🟢 EASY: Baseball Game
     * 
     * Problem: Calculate total score based on special operations
     * LeetCode: https://leetcode.com/problems/baseball-game/
     * 
     * Approach: Single stack with operation processing
     * - Numbers: add to score stack
     * - "C": cancel (remove) last score
     * - "D": double last score
     * - "+": sum of last two scores
     * 
     * Time: O(n), Space: O(n)
     */
    public static int calPoints(String[] operations) {
        Stack<Integer> scores = new Stack<>();
        
        for (String op : operations) {
            if (op.equals("C")) {
                // Cancel last score
                if (!scores.isEmpty()) {
                    scores.pop();
                }
            } else if (op.equals("D")) {
                // Double last score
                if (!scores.isEmpty()) {
                    scores.push(scores.peek() * 2);
                }
            } else if (op.equals("+")) {
                // Sum of last two scores
                if (scores.size() >= 2) {
                    int last = scores.pop();
                    int secondLast = scores.peek();
                    scores.push(last); // Restore last score
                    scores.push(last + secondLast);
                }
            } else {
                // Regular score
                scores.push(Integer.parseInt(op));
            }
        }
        
        // Calculate total score
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        
        return total;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Two Stack Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Backspace String Compare
     * 
     * Problem: Compare two strings after processing backspace characters
     * LeetCode: https://leetcode.com/problems/backspace-string-compare/
     * 
     * Approach: Stack-based string processing
     * - Process each string with stack to handle backspaces
     * - '#' character: pop from stack (backspace)
     * - Other characters: push to stack
     * - Compare final stack contents
     * 
     * Time: O(m + n), Space: O(m + n)
     */
    public static boolean backspaceCompare(String s, String t) {
        return processBackspaces(s).equals(processBackspaces(t));
    }
    
    private static String processBackspaces(String str) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : str.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop(); // Backspace operation
                }
            } else {
                stack.push(c); // Regular character
            }
        }
        
        // Convert stack to string
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    /**
     * 🟡 MEDIUM: Design A Stack With Increment Operation
     * 
     * Problem: Design stack with efficient increment operation for bottom k elements
     * LeetCode: https://leetcode.com/problems/design-a-stack-with-increment-operation/
     * 
     * Approach: Stack with lazy increment propagation
     * - Main array: stores stack values
     * - Increment array: tracks pending increments
     * - Lazy evaluation: apply increments only when popping
     * 
     * Time: O(1) for all operations, Space: O(maxSize)
     */
    public static class CustomStack {
        private int[] stack;
        private int[] increments;
        private int top;
        private int maxSize;
        
        public CustomStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
            increments = new int[maxSize];
            top = -1;
        }
        
        public void push(int x) {
            if (top + 1 < maxSize) {
                top++;
                stack[top] = x;
                increments[top] = 0; // Initialize increment
            }
        }
        
        public int pop() {
            if (top == -1) return -1;
            
            // Apply increment and propagate down
            int result = stack[top] + increments[top];
            
            // Propagate increment to element below
            if (top > 0) {
                increments[top - 1] += increments[top];
            }
            
            increments[top] = 0; // Clear increment
            top--;
            
            return result;
        }
        
        public void increment(int k, int val) {
            if (top >= 0) {
                // Apply increment lazily to the k-th element from bottom
                int applyIndex = Math.min(k - 1, top);
                increments[applyIndex] += val;
            }
        }
    }
    
    /**
     * 🟡 MEDIUM: Implement Queue using Stacks
     * 
     * Problem: Implement FIFO queue using only LIFO stacks
     * 
     * Approach: Two stacks for input/output separation
     * - Input stack: receives all enqueue operations
     * - Output stack: serves all dequeue operations
     * - Transfer: move elements when output stack is empty
     * 
     * Time: O(1) amortized for all operations, Space: O(n)
     */
    public static class MyQueue {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;
        
        public MyQueue() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        
        public void push(int x) {
            inputStack.push(x);
        }
        
        public int pop() {
            peek(); // Ensure output stack has elements
            return outputStack.pop();
        }
        
        public int peek() {
            if (outputStack.isEmpty()) {
                // Transfer all elements from input to output
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }
        
        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Two Stack Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Maximum Frequency Stack
     * 
     * Problem: Design stack where pop returns most frequent element
     * 
     * Approach: Frequency tracking with multiple stacks
     * - Frequency map: tracks count of each element
     * - Frequency stacks: separate stack for each frequency level
     * - Push: increment frequency, add to appropriate frequency stack
     * - Pop: remove from highest frequency stack, decrement frequency
     * 
     * Time: O(1) for push and pop, Space: O(n)
     */
    public static class FreqStack {
        private Map<Integer, Integer> frequency;
        private Map<Integer, Stack<Integer>> frequencyStacks;
        private int maxFrequency;
        
        public FreqStack() {
            frequency = new HashMap<>();
            frequencyStacks = new HashMap<>();
            maxFrequency = 0;
        }
        
        public void push(int val) {
            // Update frequency
            int freq = frequency.getOrDefault(val, 0) + 1;
            frequency.put(val, freq);
            
            // Update max frequency
            maxFrequency = Math.max(maxFrequency, freq);
            
            // Add to appropriate frequency stack
            frequencyStacks.computeIfAbsent(freq, k -> new Stack<>()).push(val);
        }
        
        public int pop() {
            // Get element from highest frequency stack
            Stack<Integer> maxFreqStack = frequencyStacks.get(maxFrequency);
            int val = maxFreqStack.pop();
            
            // Update frequency
            frequency.put(val, frequency.get(val) - 1);
            
            // Update max frequency if necessary
            if (maxFreqStack.isEmpty()) {
                maxFrequency--;
            }
            
            return val;
        }
    }
    
    /**
     * 🔴 HARD: Design Browser History
     * 
     * Problem: Design browser history with back/forward functionality
     * 
     * Approach: Two stacks for history and future navigation
     * - History stack: stores previous pages
     * - Future stack: stores forward pages (cleared on new visit)
     * - Visit: add current to history, clear future
     * - Back: move from history to future
     * - Forward: move from future to history
     * 
     * Time: O(1) per step for back/forward, Space: O(n)
     */
    public static class BrowserHistory {
        private Stack<String> history;
        private Stack<String> future;
        private String current;
        
        public BrowserHistory(String homepage) {
            history = new Stack<>();
            future = new Stack<>();
            current = homepage;
        }
        
        public void visit(String url) {
            history.push(current);
            current = url;
            future.clear(); // Clear future when visiting new page
        }
        
        public String back(int steps) {
            while (steps > 0 && !history.isEmpty()) {
                future.push(current);
                current = history.pop();
                steps--;
            }
            return current;
        }
        
        public String forward(int steps) {
            while (steps > 0 && !future.isEmpty()) {
                history.push(current);
                current = future.pop();
                steps--;
            }
            return current;
        }
    }
    
    /**
     * 🔴 HARD: Valid Parentheses with Star
     * 
     * Problem: Check if parentheses string is valid with '*' as wildcard
     * 
     * Approach: Two stacks tracking indices of '(' and '*'
     * - Left stack: indices of unmatched '('
     * - Star stack: indices of available '*'
     * - Match ')' with '(' first, then with '*'
     * - Finally match remaining '(' with '*' (if '*' comes after '(')
     * 
     * Time: O(n), Space: O(n)
     */
    public static boolean checkValidString(String s) {
        Stack<Integer> leftStack = new Stack<>();  // Indices of '('
        Stack<Integer> starStack = new Stack<>();  // Indices of '*'
        
        // First pass: match ')' with '(' or '*'
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else { // c == ')'
                if (!leftStack.isEmpty()) {
                    leftStack.pop(); // Match with '('
                } else if (!starStack.isEmpty()) {
                    starStack.pop(); // Match with '*'
                } else {
                    return false; // No match available
                }
            }
        }
        
        // Second pass: match remaining '(' with '*'
        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            if (leftStack.pop() > starStack.pop()) {
                return false; // '*' must come after '(' to match
            }
        }
        
        return leftStack.isEmpty(); // All '(' should be matched
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎭 TWO STACKS PATTERN - PRACTICE PROBLEMS");
        System.out.println("==========================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY TWO STACKS PROBLEMS:");
        
        // Evaluate RPN
        String[] rpnTokens = {"2", "1", "+", "3", "*"};
        int rpnResult = evalRPN(rpnTokens);
        System.out.println("Evaluate RPN " + Arrays.toString(rpnTokens) + ": " + rpnResult); // 9
        
        String[] rpnTokens2 = {"4", "13", "5", "/", "+"};
        int rpnResult2 = evalRPN(rpnTokens2);
        System.out.println("Evaluate RPN " + Arrays.toString(rpnTokens2) + ": " + rpnResult2); // 6
        
        // Min Stack
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Min Stack - getMin(): " + minStack.getMin()); // -3
        minStack.pop();
        System.out.println("Min Stack - top(): " + minStack.top()); // 0
        System.out.println("Min Stack - getMin(): " + minStack.getMin()); // -2
        
        // Baseball Game
        String[] ops = {"5", "2", "C", "D", "+"};
        int totalScore = calPoints(ops);
        System.out.println("Baseball Game " + Arrays.toString(ops) + ": " + totalScore); // 30
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM TWO STACKS PROBLEMS:");
        
        // Backspace String Compare
        boolean backspaceResult = backspaceCompare("ab#c", "ad#c");
        System.out.println("Backspace Compare 'ab#c' vs 'ad#c': " + backspaceResult); // true
        
        boolean backspaceResult2 = backspaceCompare("ab##", "#a#c");
        System.out.println("Backspace Compare 'ab##' vs '#a#c': " + backspaceResult2); // true
        
        boolean backspaceResult3 = backspaceCompare("a#c", "b");
        System.out.println("Backspace Compare 'a#c' vs 'b': " + backspaceResult3); // false
        
        // Custom Stack with Increment
        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        customStack.push(2);
        System.out.println("Custom Stack - pop(): " + customStack.pop()); // 2
        customStack.push(2);
        customStack.push(3);
        customStack.push(4); // Should be ignored (max size 3)
        customStack.increment(5, 100);
        customStack.increment(2, 100);
        System.out.println("Custom Stack - pop(): " + customStack.pop()); // 103
        System.out.println("Custom Stack - pop(): " + customStack.pop()); // 202
        System.out.println("Custom Stack - pop(): " + customStack.pop()); // 201
        
        // Queue using Stacks
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("Queue - peek(): " + queue.peek()); // 1
        System.out.println("Queue - pop(): " + queue.pop()); // 1
        System.out.println("Queue - empty(): " + queue.empty()); // false
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD TWO STACKS PROBLEMS:");
        
        // Frequency Stack
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println("Freq Stack - pop(): " + freqStack.pop()); // 5
        System.out.println("Freq Stack - pop(): " + freqStack.pop()); // 7
        System.out.println("Freq Stack - pop(): " + freqStack.pop()); // 5
        System.out.println("Freq Stack - pop(): " + freqStack.pop()); // 4
        
        // Browser History
        BrowserHistory browser = new BrowserHistory("leetcode.com");
        browser.visit("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        System.out.println("Browser - back(1): " + browser.back(1)); // facebook.com
        System.out.println("Browser - back(1): " + browser.back(1)); // google.com
        System.out.println("Browser - forward(1): " + browser.forward(1)); // facebook.com
        browser.visit("linkedin.com");
        System.out.println("Browser - forward(2): " + browser.forward(2)); // linkedin.com
        System.out.println("Browser - back(2): " + browser.back(2)); // google.com
        System.out.println("Browser - back(7): " + browser.back(7)); // leetcode.com
        
        // Valid Parentheses with Star
        boolean validResult1 = checkValidString("()");
        System.out.println("Valid String '()': " + validResult1); // true
        
        boolean validResult2 = checkValidString("(*)");
        System.out.println("Valid String '(*)': " + validResult2); // true
        
        boolean validResult3 = checkValidString("(*))");
        System.out.println("Valid String '(*))': " + validResult3); // true
        
        System.out.println("\n✅ Key Two Stacks Principles:");
        System.out.println("1. Assign clear, distinct purposes to each stack");
        System.out.println("2. Coordinate operations between stacks strategically");
        System.out.println("3. Use one stack for main data, another for auxiliary information");
        System.out.println("4. Consider input/output separation for queue simulation");
        System.out.println("5. Implement lazy evaluation for efficient operations");
        System.out.println("6. Maintain consistency and invariants between stacks");
        System.out.println("7. Handle edge cases (empty stacks, transfers, boundaries)");
        System.out.println("8. Evaluate amortized complexity for transfer operations");
    }
} 