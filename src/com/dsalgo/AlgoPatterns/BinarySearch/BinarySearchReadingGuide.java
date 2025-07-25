package com.dsalgo.AlgoPatterns.BinarySearch;

import java.util.*;

/**
 * 🔍 BINARY SEARCH PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * Binary Search is a fundamental algorithmic technique for efficiently searching sorted data
 * and solving optimization problems. It's particularly powerful for problems involving
 * finding elements, boundaries, or optimal values in sorted arrays, search spaces, and
 * mathematical ranges with logarithmic time complexity.
 * 
 * ================================================================================
 * 📋 WHEN TO USE BINARY SEARCH
 * ================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Searching in sorted arrays or data structures
 * ✅ Finding target values, boundaries, or ranges
 * ✅ Optimization problems with monotonic properties
 * ✅ Problems with "find the minimum/maximum value that satisfies condition"
 * ✅ Search space reduction with divide-and-conquer approach
 * ✅ Peak finding and valley detection problems
 * ✅ Problems involving rotated or modified sorted arrays
 * ✅ Mathematical problems with continuous search spaces
 * 
 * 🚨 KEY PHRASES TO WATCH FOR:
 * - "sorted array"
 * - "find target", "search for element"
 * - "first occurrence", "last occurrence"
 * - "smallest/largest value that satisfies"
 * - "minimize/maximize", "optimization"
 * - "logarithmic time", "O(log n)"
 * - "rotated sorted array"
 * - "peak element", "valley element"
 * - "range query", "boundary finding"
 * 
 * ================================================================================
 * 🔄 BINARY SEARCH VARIATIONS
 * ================================================================================
 * 
 * 1️⃣ CLASSIC BINARY SEARCH
 *    - Search for exact target in sorted array
 *    - Use cases: Element existence, index finding
 *    - Time: O(log n), Space: O(1)
 * 
 * 2️⃣ BOUNDARY BINARY SEARCH
 *    - Find first/last occurrence of target or condition
 *    - Use cases: Range queries, duplicate handling
 *    - Time: O(log n), Space: O(1)
 * 
 * 3️⃣ ROTATED ARRAY SEARCH
 *    - Search in rotated sorted arrays
 *    - Use cases: Rotated arrays, circular arrays
 *    - Time: O(log n), Space: O(1)
 * 
 * 4️⃣ PEAK/VALLEY FINDING
 *    - Find local maxima or minima in arrays
 *    - Use cases: Peak detection, local extrema
 *    - Time: O(log n), Space: O(1)
 * 
 * 5️⃣ OPTIMIZATION BINARY SEARCH
 *    - Find optimal value in continuous or discrete space
 *    - Use cases: Mathematical optimization, answer binary search
 *    - Time: O(log(max-min)), Space: O(1)
 * 
 * 6️⃣ 2D BINARY SEARCH
 *    - Search in 2D sorted matrices
 *    - Use cases: Matrix search, 2D optimization
 *    - Time: O(log(m*n)), Space: O(1)
 * 
 * 7️⃣ RANGE SEARCH (VALUE-BASED BINARY SEARCH)
 *    - Search for optimal value within a given range
 *    - Use cases: Kth smallest/largest, matrix range queries, duplicate handling
 *    - Time: O(log(range) * validation_cost), Space: O(1)
 * 
 * 8️⃣ ALLOCATION PROBLEMS (RESOURCE DISTRIBUTION OPTIMIZATION)
 *    - Distribute resources optimally among entities with constraints
 *    - Use cases: Minimize maximum load, maximize minimum allocation, fair distribution
 *    - Time: O(n * log(sum)), Space: O(1)
 * 
 * 9️⃣ COUNTING OCCURRENCES (FREQUENCY ANALYSIS AND ELEMENT COUNTING)
 *    - Count elements, frequencies, or occurrences with specific properties
 *    - Use cases: Range counting, frequency analysis, matrix traversal, prime counting
 *    - Time: O(log n) for binary search, O(m+n) for matrix, O(n log log n) for sieve
 * 
 * 🔟 BITONIC ARRAY SEARCH (MOUNTAIN AND BITONIC ARRAY PATTERNS)
 *    - Search in arrays with mountain/bitonic properties (increase then decrease)
 *    - Use cases: Peak/valley finding, mountain array search, rotated bitonic search
 *    - Time: O(log n) for search operations, O(n) for mountain length analysis
 * 
 * ================================================================================
 * 🧠 CORE CONCEPTS & TECHNIQUES
 * ================================================================================
 * 
 * SEARCH SPACE MANAGEMENT:
 * 🎯 Left/Right boundary handling: left <= right vs left < right
 * 🎯 Mid calculation: left + (right - left) / 2 (prevents overflow)
 * 🎯 Boundary updates: left = mid + 1 vs left = mid
 * 🎯 Loop invariants: maintaining search space correctness
 * 
 * COMPARISON STRATEGIES:
 * 🔍 Exact match: nums[mid] == target
 * 🔍 Lower bound: find first element >= target
 * 🔍 Upper bound: find first element > target
 * 🔍 Custom condition: problem-specific boolean function
 * 
 * OPTIMIZATION TECHNIQUES:
 * ⚡ Answer binary search: binary search on answer space
 * ⚡ Predicate function: convert problem to boolean condition
 * ⚡ Search space transformation: map problem to sortable domain
 * ⚡ Monotonicity exploitation: leverage sorted properties
 * 
 * ================================================================================
 * 🎯 PROBLEM-SOLVING FRAMEWORK
 * ================================================================================
 * 
 * STEP 1: IDENTIFY BINARY SEARCH OPPORTUNITY
 * - Is the data sorted or can be conceptually sorted?
 * - Can we define a search space with clear boundaries?
 * - Is there a monotonic property we can exploit?
 * - Can we reduce search space by half in each iteration?
 * 
 * STEP 2: CHOOSE BINARY SEARCH TYPE
 * - Classic: Exact element search in sorted array
 * - Boundary: First/last occurrence or condition boundary
 * - Rotated: Search in rotated or modified sorted arrays
 * - Optimization: Find optimal value in answer space
 * 
 * STEP 3: IMPLEMENT SEARCH LOGIC
 * ```java
 * // Template for classic binary search
 * public int binarySearch(int[] nums, int target) {
 *     int left = 0, right = nums.length - 1;
 *     
 *     while (left <= right) {
 *         int mid = left + (right - left) / 2;
 *         
 *         if (nums[mid] == target) {
 *             return mid;
 *         } else if (nums[mid] < target) {
 *             left = mid + 1;
 *         } else {
 *             right = mid - 1;
 *         }
 *     }
 *     
 *     return -1; // Not found
 * }
 * ```
 * 
 * STEP 4: HANDLE EDGE CASES
 * - Empty arrays
 * - Single element arrays
 * - Target not in array
 * - All elements equal
 * - Integer overflow in mid calculation
 * 
 * ================================================================================
 * 🔍 ADVANCED BINARY SEARCH PATTERNS
 * ================================================================================
 * 
 * LOWER/UPPER BOUND SEARCH:
 * Find first/last position where condition is true
 * Applications: Range queries, duplicate boundaries
 * 
 * ANSWER BINARY SEARCH:
 * Binary search on the answer space rather than input array
 * Applications: Optimization problems, capacity problems
 * 
 * PREDICATE BINARY SEARCH:
 * Use boolean function to determine search direction
 * Applications: Complex condition checking, multi-criteria search
 * 
 * FRACTIONAL BINARY SEARCH:
 * Binary search on continuous domains with precision
 * Applications: Mathematical optimization, floating-point search
 * 
 * PARALLEL BINARY SEARCH:
 * Multiple binary searches with shared computation
 * Applications: Multiple queries, batch processing
 * 
 * ================================================================================
 * 💡 PRO TIPS FOR BINARY SEARCH
 * ================================================================================
 * 
 * IMPLEMENTATION TIPS:
 * 1. Use left + (right - left) / 2 to prevent integer overflow
 * 2. Be consistent with boundary updates (inclusive vs exclusive)
 * 3. Choose loop condition carefully: left <= right vs left < right
 * 4. Test with arrays of size 1, 2, and edge cases
 * 
 * BOUNDARY HANDLING:
 * 1. For lower bound: right = mid when condition is true
 * 2. For upper bound: left = mid + 1 when condition is false
 * 3. Always verify final answer after loop termination
 * 4. Handle cases where target doesn't exist
 * 
 * OPTIMIZATION STRATEGIES:
 * 1. Identify monotonic properties in problem space
 * 2. Transform non-obvious search spaces to binary searchable form
 * 3. Use answer binary search for optimization problems
 * 4. Consider precision requirements for floating-point searches
 * 
 * DEBUGGING STRATEGIES:
 * 1. Trace through search space reduction step by step
 * 2. Verify loop invariants at each iteration
 * 3. Check boundary conditions and edge cases
 * 4. Ensure search space is properly maintained
 * 
 * COMMON PITFALLS:
 * ❌ Integer overflow in mid calculation
 * ❌ Infinite loops due to incorrect boundary updates
 * ❌ Off-by-one errors in boundary conditions
 * ❌ Not handling duplicate elements correctly
 * ❌ Assuming strict sorting when duplicates exist
 * 
 * ================================================================================
 * 📚 PRACTICE PROBLEMS BY DIFFICULTY
 * ================================================================================
 * 
 * 🟢 EASY - FOUNDATIONAL CONCEPTS:
 * 1. Binary Search (Classic) ⭐
 * 2. Search Insert Position
 * 3. First Bad Version
 * 4. Sqrt(x) - Integer Square Root
 * 
 * 🟡 MEDIUM - CORE TECHNIQUES:
 * 1. Find First and Last Position of Element ⭐
 * 2. Search in Rotated Sorted Array ⭐
 * 3. Find Peak Element ⭐
 * 4. Find Minimum in Rotated Sorted Array
 * 5. Search a 2D Matrix
 * 6. Koko Eating Bananas (Answer Binary Search)
 * 7. Capacity to Ship Packages (Optimization)
 * 8. Time-Based Key-Value Store
 * 
 * 🔴 HARD - ADVANCED APPLICATIONS:
 * 1. Median of Two Sorted Arrays ⭐
 * 2. Find K-th Smallest Pair Distance
 * 3. Split Array Largest Sum
 * 4. Minimize Max Distance to Gas Station
 * 5. Count of Range Sum (with Binary Search optimization)
 * 
 * ⭐ = Must-solve problems for mastery
 * 
 * ================================================================================
 * 🎯 DECISION FRAMEWORK
 * ================================================================================
 * 
 * WHEN YOU SEE A PROBLEM, ASK:
 * 
 * 1. "Is the data sorted or has sortable properties?"
 *    → YES: Consider binary search
 *    → NO: Check if search space can be defined
 * 
 * 2. "Are we looking for a specific element or boundary?"
 *    → ELEMENT: Classic binary search
 *    → BOUNDARY: Lower/upper bound binary search
 * 
 * 3. "Is this an optimization problem with monotonic property?"
 *    → YES: Answer binary search on solution space
 *    → NO: Direct element search
 * 
 * 4. "Can we reduce search space by half based on comparison?"
 *    → YES: Binary search is applicable
 *    → NO: Consider other search strategies
 * 
 * 5. "What are the boundaries and how do we update them?"
 *    → Define clear left/right boundaries and update rules
 * 
 * ================================================================================
 * 🚀 REAL-WORLD APPLICATIONS
 * ================================================================================
 * 
 * DATABASE SYSTEMS:
 * - Index searching in B-trees and sorted tables
 * - Query optimization with range searches
 * - Join optimization using sorted merge
 * - Cache management with LRU approximation
 * 
 * SYSTEMS PROGRAMMING:
 * - Memory allocation with best-fit algorithms
 * - Resource scheduling and load balancing
 * - Network routing with sorted routing tables
 * - File system operations with sorted directories
 * 
 * SCIENTIFIC COMPUTING:
 * - Numerical methods and root finding
 * - Optimization algorithms in machine learning
 * - Signal processing with frequency domain search
 * - Simulation parameter tuning
 * 
 * BUSINESS APPLICATIONS:
 * - Inventory management with sorted product catalogs
 * - Financial modeling with price optimization
 * - Supply chain optimization
 * - Customer segmentation with sorted metrics
 * 
 * ================================================================================
 * 
 * Master the Binary Search pattern to efficiently solve search and optimization
 * problems with logarithmic time complexity! 🔍
 */
public class BinarySearchReadingGuide {
    
    /**
     * Example: Template for Classic Binary Search
     * Standard element search in sorted array.
     */
    public static int classicBinarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevent overflow
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Target not found
    }
    
    /**
     * Example: Template for Lower Bound Binary Search
     * Find first position where element >= target.
     */
    public static int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Example: Template for Upper Bound Binary Search
     * Find first position where element > target.
     */
    public static int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Example: Template for Answer Binary Search
     * Binary search on answer space for optimization problems.
     */
    public static int answerBinarySearch(int[] nums, int constraint) {
        int left = 1, right = getMaxPossibleAnswer(nums);
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (isPossible(nums, mid, constraint)) {
                result = mid;
                right = mid - 1; // Try to find smaller valid answer
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    // Helper method for answer binary search
    private static boolean isPossible(int[] nums, int answer, int constraint) {
        // Problem-specific logic to check if answer is achievable
        // This would be implemented based on the specific problem
        return true; // Placeholder
    }
    
    private static int getMaxPossibleAnswer(int[] nums) {
        // Calculate maximum possible answer based on problem constraints
        return 1000000; // Placeholder
    }
    
    /**
     * Example: Template for 2D Matrix Binary Search
     * Search in row and column sorted matrix.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Example: Template for Perfect Square Check
     * Mathematical binary search application.
     */
    public static boolean perfectSquareTemplate(int num) {
        if (num < 1) return false;
        
        long left = 1, right = num;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Example: Template for Circular Array Upper Bound
     * Handle wrap-around scenarios in binary search.
     */
    public static char nextGreatestLetterTemplate(char[] letters, char target) {
        int left = 0, right = letters.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Handle circular array - wrap around if needed
        return letters[left % letters.length];
    }
    
    /**
      * Example: Template for Range Search (Value-based Binary Search)
      * Search for optimal value within a range by counting elements.
      */
     public static int rangeSearchTemplate(int[][] matrix, int k) {
         // Define the search range [min_value, max_value]
         int left = matrix[0][0]; // minimum possible value
         int right = matrix[matrix.length - 1][matrix[0].length - 1]; // maximum possible value
         
         while (left < right) {
             int mid = left + (right - left) / 2;
             
             // Count elements that satisfy the condition (e.g., <= mid)
             int count = countElementsSatisfyingCondition(matrix, mid);
             
             if (count < k) {
                 left = mid + 1; // Need larger values
             } else {
                 right = mid; // Try smaller values
             }
         }
         
         return left; // The optimal value in the range
     }
     
     // Helper method for counting elements (problem-specific)
     private static int countElementsSatisfyingCondition(int[][] matrix, int target) {
         int count = 0;
         int n = matrix.length;
         int row = n - 1, col = 0; // Start from bottom-left for sorted matrix
         
         while (row >= 0 && col < n) {
             if (matrix[row][col] <= target) {
                 count += row + 1; // All elements in this column up to row
                 col++;
             } else {
                 row--;
             }
         }
         
         return count;
     }
    
    /**
      * Example: Template for Allocation Problems
      * Distribute resources optimally using binary search on the answer.
      */
     public static int allocationTemplate(int[] resources, int entities) {
         // Define search range based on problem constraints
         int left = Arrays.stream(resources).max().orElse(0); // Minimum possible allocation
         int right = Arrays.stream(resources).sum(); // Maximum possible allocation
         int result = right;
         
         while (left <= right) {
             int mid = left + (right - left) / 2;
             
             // Check if allocation is feasible with given constraint
             if (canAllocate(resources, entities, mid)) {
                 result = mid;
                 // Decide direction based on optimization goal
                 // For minimize maximum: right = mid - 1
                 // For maximize minimum: left = mid + 1
                 right = mid - 1; // Example: minimizing maximum
             } else {
                 left = mid + 1; // Need to relax constraint
             }
         }
         
         return result;
     }
     
     // Helper method for allocation feasibility check
     private static boolean canAllocate(int[] resources, int entities, int constraint) {
         int entitiesUsed = 1;
         int currentLoad = 0;
         
         for (int resource : resources) {
             if (currentLoad + resource <= constraint) {
                 currentLoad += resource;
             } else {
                 entitiesUsed++;
                 currentLoad = resource;
                 if (entitiesUsed > entities) {
                     return false;
                 }
             }
         }
         
         return true;
     }
    
    /**
      * Example: Template for Counting Occurrences
      * Count elements with specific properties using binary search or optimized traversal.
      */
      public static int countingTemplate(int[] nums, int target) {
          // For sorted arrays: use binary search for O(log n) counting
          return countOccurrencesInRange(nums, target);
      }
      
      // Count occurrences of target in sorted array
      private static int countOccurrencesInRange(int[] nums, int target) {
          int first = findFirstOccurrence(nums, target);
          if (first == -1) return 0;
          
          int last = findLastOccurrence(nums, target);
          return last - first + 1;
      }
      
      private static int findFirstOccurrence(int[] nums, int target) {
          int left = 0, right = nums.length;
          
          while (left < right) {
              int mid = left + (right - left) / 2;
              
              if (nums[mid] < target) {
                  left = mid + 1;
              } else {
                  right = mid;
              }
          }
          
          return (left < nums.length && nums[left] == target) ? left : -1;
      }
      
      private static int findLastOccurrence(int[] nums, int target) {
          int left = 0, right = nums.length;
          
          while (left < right) {
              int mid = left + (right - left) / 2;
              
              if (nums[mid] <= target) {
                  left = mid + 1;
              } else {
                  right = mid;
              }
          }
          
          return (left > 0 && nums[left - 1] == target) ? left - 1 : -1;
      }
      
      /**
       * Example: Template for Matrix Counting
       * Count elements with specific properties in sorted matrix using optimized traversal.
       */
      public static int matrixCountingTemplate(int[][] matrix, int threshold) {
          int rows = matrix.length, cols = matrix[0].length;
          int row = 0, col = cols - 1; // Start from top-right
          int count = 0;
          
          while (row < rows && col >= 0) {
              if (matrix[row][col] < threshold) {
                  // All elements in this column below current row satisfy condition
                  count += rows - row;
                  col--; // Move to previous column
              } else {
                  // Current element doesn't satisfy condition, move to next row
                  row++;
              }
          }
          
          return count;
      }
    
    /**
      * Example: Template for Bitonic Array Search
      * Search in mountain/bitonic arrays using multi-phase binary search.
      */
      public static int bitonicSearchTemplate(int[] arr, int target) {
          // Phase 1: Find peak index
          int peak = findPeakTemplate(arr);
          
          // Phase 2: Search in increasing part (left side)
          int leftResult = searchIncreasingPart(arr, target, 0, peak);
          if (leftResult != -1) {
              return leftResult;
          }
          
          // Phase 3: Search in decreasing part (right side)
          return searchDecreasingPart(arr, target, peak, arr.length - 1);
      }
      
      private static int findPeakTemplate(int[] arr) {
          int left = 0, right = arr.length - 1;
          
          while (left < right) {
              int mid = left + (right - left) / 2;
              
              if (arr[mid] < arr[mid + 1]) {
                  left = mid + 1; // Peak is on the right
              } else {
                  right = mid; // Peak is on the left or at mid
              }
          }
          
          return left;
      }
      
      private static int searchIncreasingPart(int[] arr, int target, int left, int right) {
          while (left <= right) {
              int mid = left + (right - left) / 2;
              
              if (arr[mid] == target) {
                  return mid;
              } else if (arr[mid] < target) {
                  left = mid + 1;
              } else {
                  right = mid - 1;
              }
          }
          
          return -1;
      }
      
      private static int searchDecreasingPart(int[] arr, int target, int left, int right) {
          while (left <= right) {
              int mid = left + (right - left) / 2;
              
              if (arr[mid] == target) {
                  return mid;
              } else if (arr[mid] > target) {
                  left = mid + 1; // In decreasing array, go right for smaller values
              } else {
                  right = mid - 1; // In decreasing array, go left for larger values
              }
          }
          
          return -1;
      }
    
    public static void main(String[] args) {
        System.out.println("🔍 BINARY SEARCH PATTERN - READING GUIDE");
        System.out.println("=========================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Test classic binary search
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("1. Classic Binary Search:");
        System.out.println("Array: " + Arrays.toString(sortedArray));
        System.out.println("Search for 7: Index " + classicBinarySearch(sortedArray, 7));
        System.out.println("Search for 6: Index " + classicBinarySearch(sortedArray, 6));
        
        // Test lower bound
        int[] duplicateArray = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\n2. Lower Bound Search:");
        System.out.println("Array: " + Arrays.toString(duplicateArray));
        System.out.println("Lower bound of 2: Index " + lowerBound(duplicateArray, 2));
        System.out.println("Lower bound of 6: Index " + lowerBound(duplicateArray, 6));
        
        // Test upper bound
        System.out.println("\n3. Upper Bound Search:");
        System.out.println("Array: " + Arrays.toString(duplicateArray));
        System.out.println("Upper bound of 2: Index " + upperBound(duplicateArray, 2));
        System.out.println("Upper bound of 0: Index " + upperBound(duplicateArray, 0));
        
        // Test 2D matrix search
        int[][] matrix = {{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}};
        System.out.println("\n4. 2D Matrix Search:");
        System.out.println("Matrix: [[1,4,7,11],[2,5,8,12],[3,6,9,16]]");
        System.out.println("Search for 5: " + searchMatrix(matrix, 5));
        System.out.println("Search for 13: " + searchMatrix(matrix, 13));
        
        // Test perfect square check
        System.out.println("\n5. Perfect Square Check:");
        System.out.println("Is 16 a perfect square? " + perfectSquareTemplate(16));
        System.out.println("Is 15 a perfect square? " + perfectSquareTemplate(15));
        
        // Test circular array search
        char[] letters = {'c', 'f', 'j'};
        System.out.println("\n6. Next Greatest Letter:");
        System.out.println("Letters: " + Arrays.toString(letters));
        System.out.println("Next after 'a': " + nextGreatestLetterTemplate(letters, 'a'));
        System.out.println("Next after 'c': " + nextGreatestLetterTemplate(letters, 'c'));
        System.out.println("Next after 'j': " + nextGreatestLetterTemplate(letters, 'j'));
        
        // Test range search
        int[][] matrixRange = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println("\n7. Range Search (Kth Smallest in Matrix):");
        System.out.println("Matrix: " + Arrays.deepToString(matrixRange));
        System.out.println("8th smallest element: " + rangeSearchTemplate(matrixRange, 8));
        
        // Test allocation problems
        int[] resourceArray = {10, 20, 30, 40};
        System.out.println("\n8. Allocation Problems (Book Allocation):");
        System.out.println("Resources: " + Arrays.toString(resourceArray) + ", Entities: 2");
        System.out.println("Minimum maximum allocation: " + allocationTemplate(resourceArray, 2));
        
        // Test counting occurrences
        int[] countingArray = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\n9. Counting Occurrences:");
        System.out.println("Array: " + Arrays.toString(countingArray) + ", Target: 2");
        System.out.println("Count occurrences of 2: " + countingTemplate(countingArray, 2));
        
        // Test matrix counting
        int[][] matrixCounting = {{-3, -2, -1}, {0, 1, 2}, {3, 4, 5}};
        System.out.println("\n10. Matrix Counting (negatives):");
        System.out.println("Matrix: " + Arrays.deepToString(matrixCounting));
        System.out.println("Count negatives: " + matrixCountingTemplate(matrixCounting, 0));
        
        // Test bitonic array search
        int[] bitonicArray = {1, 3, 8, 12, 4, 2};
        System.out.println("\n11. Bitonic Array Search:");
        System.out.println("Bitonic Array: " + Arrays.toString(bitonicArray) + ", Target: 4");
        System.out.println("Search in bitonic array: " + bitonicSearchTemplate(bitonicArray, 4));
        System.out.println("Peak index: " + findPeakTemplate(bitonicArray));
        
        System.out.println("\n✅ Key Takeaways:");
        System.out.println("1. Use left + (right - left) / 2 to prevent overflow");
        System.out.println("2. Choose loop condition based on boundary handling needs");
        System.out.println("3. Lower bound finds first occurrence, upper bound finds insertion point");
        System.out.println("4. Answer binary search works on solution space");
        System.out.println("5. Always verify boundary updates maintain loop invariants");
        System.out.println("6. Handle mathematical operations with appropriate data types");
        System.out.println("7. Consider wrap-around cases for circular arrays");
        System.out.println("8. Range search works on value space with counting validation");
        System.out.println("9. Allocation problems use greedy validation with binary search on constraints");
        System.out.println("10. Counting problems leverage sorted properties for O(log n) or O(m+n) solutions");
        System.out.println("11. Bitonic arrays require multi-phase search with peak finding and directional search");
    }
} 