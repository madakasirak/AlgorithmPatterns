package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * 🔄 RECURSION PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of recursive problems.
 * Recursion is a fundamental technique where a function calls itself to solve
 * problems by breaking them down into smaller, similar subproblems.
 * 
 * Pattern Focus: Divide and conquer, mathematical calculations, problem decomposition
 * Time Complexity: Varies by problem (often O(n) to O(2^n))
 * Space Complexity: O(depth) for call stack
 */
public class Recursion {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Recursive Functions
    // ============================================================================
    
    /**
     * 🟢 EASY: Factorial Calculation
     * 
     * Problem: Calculate n! = n × (n-1) × (n-2) × ... × 1
     * 
     * Approach: Basic recursion with clear base case
     * - Base case: 0! = 1, 1! = 1
     * - Recursive case: n! = n × (n-1)!
     * 
     * Time: O(n), Space: O(n) for call stack
     */
    public static long factorial(int n) {
        // Base case: factorial of 0 or 1 is 1
        if (n <= 1) {
            return 1;
        }
        
        // Recursive case: n! = n × (n-1)!
        return n * factorial(n - 1);
    }
    
    /**
     * 🟢 EASY: Factorial with Tail Recursion
     * 
     * Optimized version using accumulator to enable tail call optimization
     */
    public static long factorialTailRecursive(int n) {
        return factorialHelper(n, 1);
    }
    
    private static long factorialHelper(int n, long accumulator) {
        // Base case: return accumulated result
        if (n <= 1) {
            return accumulator;
        }
        
        // Tail recursive call
        return factorialHelper(n - 1, n * accumulator);
    }
    
    /**
     * 🟢 EASY: Fibonacci Number
     * 
     * Problem: Find the nth Fibonacci number where F(n) = F(n-1) + F(n-2)
     * 
     * Approach: Multiple recursion (two recursive calls)
     * - Base cases: F(0) = 0, F(1) = 1
     * - Recursive case: F(n) = F(n-1) + F(n-2)
     * 
     * Time: O(2^n) - Exponential!, Space: O(n) for call stack
     */
    public static int fibonacci(int n) {
        // Base cases
        if (n <= 1) {
            return n; // F(0) = 0, F(1) = 1
        }
        
        // Recursive case: F(n) = F(n-1) + F(n-2)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * 🟢 EASY: Fibonacci with Memoization
     * 
     * Optimized version using dynamic programming to avoid recalculation
     * 
     * Time: O(n), Space: O(n)
     */
    public static int fibonacciMemo(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return fibonacciMemoHelper(n, memo);
    }
    
    private static int fibonacciMemoHelper(int n, Map<Integer, Integer> memo) {
        // Check if already calculated
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Calculate and store result
        int result = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    /**
     * 🟢 EASY: Power Function - Pow(x, n)
     * 
     * Problem: Calculate x raised to the power n (x^n)
     * 
     * Approach: Divide and conquer for efficiency
     * - Base cases: x^0 = 1, x^1 = x
     * - Recursive case: x^n = x^(n/2) × x^(n/2) [for even n]
     *                   x^n = x × x^(n-1) [for odd n]
     * 
     * Time: O(log n), Space: O(log n)
     */
    public static double myPow(double x, int n) {
        // Handle negative exponents
        if (n < 0) {
            return 1.0 / powHelper(x, -(long)n); // Use long to handle Integer.MIN_VALUE
        }
        
        return powHelper(x, n);
    }
    
    private static double powHelper(double x, long n) {
        // Base case
        if (n == 0) {
            return 1.0;
        }
        
        // Recursive case: divide and conquer
        if (n % 2 == 0) {
            // Even exponent: x^n = (x^(n/2))^2
            double half = powHelper(x, n / 2);
            return half * half;
        } else {
            // Odd exponent: x^n = x * x^(n-1)
            return x * powHelper(x, n - 1);
        }
    }
    
    /**
     * 🟢 EASY: Greatest Common Divisor (GCD) - Euclidean Algorithm
     * 
     * Problem: Find the greatest common divisor of two numbers
     * 
     * Approach: Euclidean algorithm using recursion
     * - Base case: gcd(a, 0) = a
     * - Recursive case: gcd(a, b) = gcd(b, a % b)
     * 
     * Time: O(log(min(a,b))), Space: O(log(min(a,b)))
     */
    public static int gcd(int a, int b) {
        // Base case: gcd(a, 0) = a
        if (b == 0) {
            return a;
        }
        
        // Recursive case: gcd(a, b) = gcd(b, a % b)
        return gcd(b, a % b);
    }
    
    /**
     * 🟡 MEDIUM: Greatest Common Divisor of Strings
     * 
     * Problem: Find the largest string that divides both str1 and str2
     * A string divides another if it can be formed by concatenating copies
     * 
     * Approach: Recursive approach using string properties
     * - If str1 + str2 != str2 + str1, no common divisor exists
     * - Use GCD of lengths to find the divisor length
     * 
     * Time: O(n + m), Space: O(log(min(n,m)))
     */
    public static String gcdOfStrings(String str1, String str2) {
        // Check if strings can have a common divisor
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        
        // Find GCD of lengths
        int gcdLength = gcd(str1.length(), str2.length());
        
        // Return the prefix of length gcdLength
        return str1.substring(0, gcdLength);
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Advanced Recursive Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Reverse String Recursively
     * 
     * Problem: Reverse a string using recursion
     * 
     * Approach: Recursive string manipulation
     * - Base case: empty or single character string
     * - Recursive case: last char + reverse(rest)
     * 
     * Time: O(n), Space: O(n)
     */
    public static String reverseString(String s) {
        // Base case: empty or single character
        if (s.length() <= 1) {
            return s;
        }
        
        // Recursive case: last char + reverse of rest
        return s.charAt(s.length() - 1) + reverseString(s.substring(0, s.length() - 1));
    }
    
    /**
     * 🟡 MEDIUM: Palindrome Check Recursively
     * 
     * Problem: Check if a string is a palindrome using recursion
     * 
     * Approach: Two-pointer approach with recursion
     * - Base case: left >= right (single char or empty)
     * - Recursive case: check ends and recurse on middle
     * 
     * Time: O(n), Space: O(n)
     */
    public static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }
    
    private static boolean isPalindromeHelper(String s, int left, int right) {
        // Base case: single character or empty
        if (left >= right) {
            return true;
        }
        
        // Check current characters and recurse
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        
        return isPalindromeHelper(s, left + 1, right - 1);
    }
    
    /**
     * 🟡 MEDIUM: Sum of Digits Recursively
     * 
     * Problem: Calculate sum of digits in a number
     * 
     * Approach: Extract digit and recurse on remaining
     * - Base case: n == 0
     * - Recursive case: (n % 10) + sumDigits(n / 10)
     * 
     * Time: O(log n), Space: O(log n)
     */
    public static int sumOfDigits(int n) {
        // Handle negative numbers
        n = Math.abs(n);
        
        // Base case
        if (n == 0) {
            return 0;
        }
        
        // Recursive case: last digit + sum of remaining digits
        return (n % 10) + sumOfDigits(n / 10);
    }
    
    /**
     * 🟡 MEDIUM: Binary to Decimal Conversion
     * 
     * Problem: Convert binary string to decimal using recursion
     * 
     * Approach: Process from right to left
     * - Base case: empty string
     * - Recursive case: digit value + 2 * convert(rest)
     * 
     * Time: O(n), Space: O(n)
     */
    public static int binaryToDecimal(String binary) {
        return binaryToDecimalHelper(binary, 0);
    }
    
    private static int binaryToDecimalHelper(String binary, int index) {
        // Base case: processed all digits
        if (index >= binary.length()) {
            return 0;
        }
        
        // Current digit value
        int digit = binary.charAt(index) - '0';
        int power = binary.length() - 1 - index;
        
        // Recursive case: current digit value + rest
        return digit * (int)Math.pow(2, power) + binaryToDecimalHelper(binary, index + 1);
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Complex Recursive Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Tower of Hanoi
     * 
     * Problem: Move n disks from source to destination using auxiliary peg
     * Rules: Only one disk at a time, larger disk cannot be on smaller
     * 
     * Approach: Divide and conquer
     * - Move n-1 disks to auxiliary
     * - Move largest disk to destination
     * - Move n-1 disks from auxiliary to destination
     * 
     * Time: O(2^n), Space: O(n)
     */
    public static List<String> solveTowerOfHanoi(int n) {
        List<String> moves = new ArrayList<>();
        hanoi(n, 'A', 'C', 'B', moves);
        return moves;
    }
    
    private static void hanoi(int n, char source, char dest, char aux, List<String> moves) {
        // Base case: one disk
        if (n == 1) {
            moves.add("Move disk 1 from " + source + " to " + dest);
            return;
        }
        
        // Move n-1 disks from source to auxiliary
        hanoi(n - 1, source, aux, dest, moves);
        
        // Move largest disk from source to destination
        moves.add("Move disk " + n + " from " + source + " to " + dest);
        
        // Move n-1 disks from auxiliary to destination
        hanoi(n - 1, aux, dest, source, moves);
    }
    
    /**
     * 🔴 HARD: Generate All Subsets (Power Set)
     * 
     * Problem: Generate all possible subsets of a given set
     * 
     * Approach: For each element, choose to include or exclude
     * - Base case: processed all elements
     * - Recursive case: include current + exclude current
     * 
     * Time: O(2^n), Space: O(2^n)
     */
    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsetsHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsetsHelper(int[] nums, int index, 
                                             List<Integer> current, 
                                             List<List<Integer>> result) {
        // Base case: processed all elements
        if (index >= nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Include current element
        current.add(nums[index]);
        generateSubsetsHelper(nums, index + 1, current, result);
        
        // Exclude current element (backtrack)
        current.remove(current.size() - 1);
        generateSubsetsHelper(nums, index + 1, current, result);
    }
    
    /**
     * 🔴 HARD: Minimum Steps to Reach Target (Simple Version)
     * 
     * Problem: Find minimum steps to move from start to target
     * Operations: +1, -1, *2
     * 
     * Approach: BFS with memoization (recursive exploration)
     * - Base case: reached target
     * - Recursive case: try all operations
     * 
     * Time: O(target), Space: O(target)
     */
    public static int minStepsToTarget(int start, int target) {
        Map<Integer, Integer> memo = new HashMap<>();
        return minStepsHelper(start, target, memo);
    }
    
    private static int minStepsHelper(int current, int target, Map<Integer, Integer> memo) {
        // Base case: reached target
        if (current == target) {
            return 0;
        }
        
        // Check memo
        if (memo.containsKey(current)) {
            return memo.get(current);
        }
        
        // Avoid infinite recursion
        if (Math.abs(current - target) > target) {
            return Integer.MAX_VALUE;
        }
        
        // Try all operations
        int steps = Integer.MAX_VALUE;
        
        // Operation 1: +1
        int step1 = minStepsHelper(current + 1, target, memo);
        if (step1 != Integer.MAX_VALUE) {
            steps = Math.min(steps, 1 + step1);
        }
        
        // Operation 2: -1
        int step2 = minStepsHelper(current - 1, target, memo);
        if (step2 != Integer.MAX_VALUE) {
            steps = Math.min(steps, 1 + step2);
        }
        
        // Operation 3: *2 (only if beneficial)
        if (current < target) {
            int step3 = minStepsHelper(current * 2, target, memo);
            if (step3 != Integer.MAX_VALUE) {
                steps = Math.min(steps, 1 + step3);
            }
        }
        
        memo.put(current, steps);
        return steps;
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🔄 RECURSION PATTERN - PRACTICE PROBLEMS");
        System.out.println("=========================================");
        
        // Test Basic Recursive Functions
        System.out.println("\n🟢 BASIC RECURSIVE FUNCTIONS:");
        System.out.println("Factorial(5): " + factorial(5));
        System.out.println("Factorial Tail Recursive(5): " + factorialTailRecursive(5));
        System.out.println("Fibonacci(7): " + fibonacci(7));
        System.out.println("Fibonacci Memoized(7): " + fibonacciMemo(7));
        System.out.println("Power(2, 10): " + myPow(2, 10));
        System.out.println("Power(2, -3): " + myPow(2, -3));
        System.out.println("GCD(48, 18): " + gcd(48, 18));
        System.out.println("GCD of Strings('ABCABC', 'ABC'): '" + gcdOfStrings("ABCABC", "ABC") + "'");
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM RECURSIVE PROBLEMS:");
        System.out.println("Reverse String('hello'): '" + reverseString("hello") + "'");
        System.out.println("Is Palindrome('racecar'): " + isPalindrome("racecar"));
        System.out.println("Is Palindrome('hello'): " + isPalindrome("hello"));
        System.out.println("Sum of Digits(12345): " + sumOfDigits(12345));
        System.out.println("Binary to Decimal('1101'): " + binaryToDecimal("1101"));
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD RECURSIVE PROBLEMS:");
        System.out.println("Tower of Hanoi(3 disks):");
        List<String> hanoiMoves = solveTowerOfHanoi(3);
        for (String move : hanoiMoves) {
            System.out.println("  " + move);
        }
        
        System.out.println("\nGenerate Subsets([1,2,3]):");
        List<List<Integer>> subsets = generateSubsets(new int[]{1, 2, 3});
        for (List<Integer> subset : subsets) {
            System.out.println("  " + subset);
        }
        
        System.out.println("\nMin Steps to Target (2 -> 10): " + minStepsToTarget(2, 10));
        
        // Performance comparison
        System.out.println("\n⚡ PERFORMANCE COMPARISON:");
        long start = System.currentTimeMillis();
        fibonacci(35);
        long naive = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        fibonacciMemo(35);
        long optimized = System.currentTimeMillis() - start;
        
        System.out.println("Fibonacci(35) - Naive: " + naive + "ms");
        System.out.println("Fibonacci(35) - Memoized: " + optimized + "ms");
        System.out.println("Speedup: " + (naive / Math.max(optimized, 1)) + "x");
        
        System.out.println("\n✅ Key Recursive Principles:");
        System.out.println("1. Always define clear base cases");
        System.out.println("2. Ensure progress toward base case");
        System.out.println("3. Use memoization for overlapping subproblems");
        System.out.println("4. Consider tail recursion for optimization");
        System.out.println("5. Trust the recursion - assume it works for smaller inputs");
    }
} 