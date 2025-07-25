package com.dsalgo.AlgoPatterns.Arrays;

/**
 * ==================================================================================
 *                             SORTING PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * Sorting is one of the most fundamental algorithmic patterns in computer science.
 * It involves arranging elements in a specific order (ascending, descending, or custom)
 * to simplify searching, counting, comparing, or other operations. Sorting often serves
 * as a preprocessing step that enables efficient solutions to complex problems.
 * 
 * ==================================================================================
 *                               WHEN TO USE SORTING
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Problems mentioning "sorted array" as input or requirement
 * ✅ Need to arrange elements in specific order
 * ✅ Searching, counting, or comparing operations can be optimized
 * ✅ Finding duplicates, pairs, or patterns in data
 * ✅ Range or interval problems
 * ✅ Problems involving relative ordering
 * ✅ Optimization problems where order matters
 * ✅ Custom comparison criteria needed
 * ✅ Grouping similar elements together
 * 
 * 🚨 RED FLAGS (when NOT to use):
 * ❌ Order of elements must be preserved (unless stable sort needed)
 * ❌ Sorting would destroy important positional information
 * ❌ Real-time streaming data processing
 * ❌ Memory-constrained environments (large datasets)
 * 
 * ==================================================================================
 *                              SORTING VARIATIONS
 * ==================================================================================
 */
public class SortingReadingGuide {

    /**
     * ================================================================================
     *                           VARIATION 1: BASIC SORTING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Sort array using built-in or custom comparator
     * 2. Process sorted array to solve problem
     * 3. Often combined with two pointers or binary search
     * 
     * USE CASES:
     * - Finding pairs with target sum
     * - Removing duplicates
     * - Finding closest elements
     * - Merge operations
     */
    
    // EXAMPLE: Two Sum with Sorted Array
    public static int[] twoSum(int[] nums, int target) {
        // Create array of [value, originalIndex] pairs
        int[][] pairs = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new int[]{nums[i], i};
        }
        
        // Sort by value
        java.util.Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = pairs[left][0] + pairs[right][0];
            if (sum == target) {
                return new int[]{pairs[left][1], pairs[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * ================================================================================
     *                         VARIATION 2: CUSTOM COMPARATOR SORTING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Define custom comparison logic
     * 2. Sort using custom comparator
     * 3. Process result based on custom ordering
     * 
     * USE CASES:
     * - Sorting by multiple criteria
     * - Complex object sorting
     * - String sorting with special rules
     * - Priority-based sorting
     */
    
    // EXAMPLE: Largest Number Formation
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        // Custom comparator: compare concatenated results
        java.util.Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        
        // Handle edge case of all zeros
        if (strs[0].equals("0")) return "0";
        
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            result.append(str);
        }
        return result.toString();
    }

    /**
     * ================================================================================
     *                        VARIATION 3: COUNTING/BUCKET SORT
     * ================================================================================
     * 
     * PATTERN:
     * 1. Count frequency of each element
     * 2. Reconstruct sorted array from counts
     * 3. Often used when range of values is limited
     * 
     * USE CASES:
     * - Sorting with limited value range
     * - Color sorting (Dutch National Flag)
     * - Character frequency problems
     * - Age sorting
     */
    
    // EXAMPLE: Sort Colors (Dutch National Flag)
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
                // Don't increment mid (need to check swapped element)
            }
        }
    }

    /**
     * ================================================================================
     *                       VARIATION 4: PARTIAL/SELECTIVE SORTING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Don't sort entire array
     * 2. Use techniques like quickselect or heap
     * 3. Find k-th element or top-k elements
     * 
     * USE CASES:
     * - Finding k-th largest/smallest element
     * - Top-k frequent elements
     * - Median finding
     * - Partial ordering requirements
     */
    
    // EXAMPLE: K-th Largest Element (QuickSelect)
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private static int quickSelect(int[] nums, int left, int right, int kIndex) {
        if (left == right) return nums[left];
        
        int pivotIndex = partition(nums, left, right);
        
        if (pivotIndex == kIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < kIndex) {
            return quickSelect(nums, pivotIndex + 1, right, kIndex);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, kIndex);
        }
    }
    
    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    /**
     * ================================================================================
     *                         VARIATION 5: SORT + TWO POINTERS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Sort array first
     * 2. Use two pointers technique on sorted array
     * 3. Leverage sorted property for efficient searching
     * 
     * USE CASES:
     * - 3Sum, 4Sum problems
     * - Closest pair problems
     * - Container with most water
     * - Interval merging
     */
    
    // EXAMPLE: 3Sum Problem
    public static java.util.List<java.util.List<Integer>> threeSum(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Arrays.sort(nums); // Critical sorting step
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates
            
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(java.util.Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * ================================================================================
     *                        VARIATION 6: TOPOLOGICAL SORTING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Build dependency graph
     * 2. Find nodes with no incoming edges
     * 3. Process nodes in topological order
     * 
     * USE CASES:
     * - Course scheduling
     * - Task dependencies
     * - Build systems
     * - Deadlock detection
     */
    
    // EXAMPLE: Course Schedule (Topological Sort)
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        java.util.List<Integer>[] graph = new java.util.List[numCourses];
        int[] indegree = new int[numCourses];
        
        // Build graph
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new java.util.ArrayList<>();
        }
        
        for (int[] prereq : prerequisites) {
            graph[prereq[1]].add(prereq[0]);
            indegree[prereq[0]]++;
        }
        
        // Find courses with no prerequisites
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completed++;
            
            for (int nextCourse : graph[course]) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        return completed == numCourses;
    }

    /**
     * ================================================================================
     *                           ADVANCED SORTING PATTERNS
     * ================================================================================
     */
    
    // WIGGLE SORT II - Complex arrangement pattern
    public static void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);
        
        int mid = (n - 1) / 2;
        int end = n - 1;
        
        // Fill from both ends to avoid adjacent conflicts
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[mid--];
            } else {
                nums[i] = sorted[end--];
            }
        }
    }
    
    // MEETING ROOMS II - Interval sorting with heap
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // Sort by start time
        java.util.Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);
        }
        
        return minHeap.size();
    }

    /**
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. STABILITY ISSUES: Using unstable sort when order of equal elements matters
     * 2. COMPARATOR ERRORS: Incorrect comparison logic or inconsistent comparators
     * 3. OVERFLOW: Integer overflow in custom comparators
     * 4. TIME COMPLEXITY: Not considering O(n log n) impact on overall complexity
     * 5. SPACE USAGE: Unnecessary copying of arrays for sorting
     * 6. EDGE CASES: Empty arrays, single elements, all equal elements
     * 
     * 💡 PRO TIPS:
     * 1. Consider if sorting is really necessary or if partial sorting suffices
     * 2. Use counting sort for limited range integers (O(n) complexity)
     * 3. Custom comparators should be consistent and transitive
     * 4. For stability requirements, use stable sorting algorithms
     * 5. Consider external sorting for very large datasets
     * 6. Combine sorting with other patterns (two pointers, binary search)
     * 7. Sometimes sorting indices instead of values preserves original positions
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Sort Colors
     * - Merge Sorted Array
     * - Remove Duplicates from Sorted Array
     * - Squares of a Sorted Array
     * - Sort Array by Parity
     * - Height Checker
     * 
     * 🟡 MEDIUM:
     * - Meeting Rooms II
     * - Largest Number
     * - Sort Characters by Frequency
     * - Top K Frequent Elements
     * - 3Sum
     * - Wiggle Sort II
     * - Custom Sort String
     * - Sort List
     * - Kth Largest Element in Array
     * - Merge Intervals
     * 
     * 🔴 HARD:
     * - Count of Smaller Numbers After Self
     * - Reverse Pairs
     * - Create Maximum Number
     * - Smallest Range Covering Elements from K Lists
     * - Count of Range Sum
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Will sorting help simplify the problem? → Consider preprocessing with sort
     * 2. Do I need to find pairs/triplets? → Sort + two pointers
     * 3. Is there a custom ordering requirement? → Custom comparator
     * 4. Is the value range limited? → Consider counting/bucket sort
     * 5. Do I only need k-th element? → Partial sorting (quickselect/heap)
     * 6. Are there dependencies between elements? → Topological sort
     * 7. Do I need to maintain original positions? → Sort indices, not values
     * 
     * COMPLEXITY CONSIDERATIONS:
     * - Comparison-based sorting: O(n log n) time, O(1) to O(n) space
     * - Counting/Bucket sort: O(n + k) time, O(k) space (k = range)
     * - Partial sorting: O(n) average for quickselect, O(n log k) for heap
     * 
     * Remember: Sorting is often the key that unlocks an elegant solution to
     * what initially seems like a complex problem!
     */
    
    // Helper method for swapping elements
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
} 