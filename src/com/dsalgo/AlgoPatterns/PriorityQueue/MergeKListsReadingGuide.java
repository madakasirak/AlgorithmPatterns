package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 MERGE K LISTS PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS MERGE K LISTS PATTERN?
 * ============================================================================
 * 
 * Merge K Lists Pattern focuses on efficiently combining multiple sorted sequences
 * into a single sorted result while maintaining order. This pattern is fundamental
 * for distributed computing, external sorting, data integration, and stream processing
 * where multiple sorted data sources must be combined efficiently without losing
 * the sorted property.
 * 
 * The pattern leverages priority queue-based merging for optimal time complexity,
 * divide-and-conquer strategies for balanced processing, interval merging techniques
 * for overlapping ranges, and specialized algorithms for finding common elements
 * across multiple sorted sequences with optimal performance characteristics.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. ORDER PRESERVATION: Maintain sorted order while merging multiple sequences
 * 2. EFFICIENT SELECTION: Use priority queues for optimal element selection
 * 3. DIVIDE AND CONQUER: Apply balanced merging for logarithmic depth
 * 4. INTERVAL PROCESSING: Handle overlapping ranges with merge strategies
 * 
 * 🎯 MERGE PROCESSING METAPHOR:
 * Think of Merge K Lists as "orchestra conductor coordination":
 * - Multiple musicians: each sorted list plays their part in order
 * - Conductor coordination: priority queue selects next note to play
 * - Harmonious result: merged sequence maintains perfect order
 * - Efficient scheduling: minimize total performance time with optimal strategy
 * 
 * ============================================================================
 * 🎯 WHEN TO USE MERGE K LISTS PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Merging multiple sorted data sources from distributed systems
 * - External sorting algorithms for large datasets that don't fit in memory
 * - Database query processing with multiple sorted indexes
 * - Stream processing where multiple sorted streams need combination
 * - Interval scheduling and range merging problems
 * - Finding common elements across multiple sorted collections
 * - Log file merging from multiple sources with timestamp ordering
 * - Priority-based task scheduling from multiple queues
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Merge K sorted lists/arrays"
 * - "Combine multiple sorted sequences"
 * - "Maintain sorted order while merging"
 * - "Find smallest range covering elements"
 * - "Merge overlapping intervals"
 * - "Common elements in sorted lists"
 * - "External merge sort"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Lists are not sorted (use sorting first or different approach)
 * - Need to merge unsorted data (use different merge strategies)
 * - Single list processing (use standard sorting or processing)
 * - Memory unlimited and K is small (simple concatenation + sort might work)
 * 
 * ============================================================================
 * 🔄 MERGE K LISTS PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ PRIORITY QUEUE MERGING
 * - Min-heap for selecting smallest elements
 * - LinkedList node-based merging
 * - Stream processing with multiple iterators
 * - Optimal for large K with small lists
 * 
 * 2️⃣ DIVIDE AND CONQUER MERGING
 * - Recursive pairwise merging
 * - Balanced merge tree construction
 * - Logarithmic depth processing
 * - Better for small K with large lists
 * 
 * 3️⃣ ARRAY MERGING TECHNIQUES
 * - In-place merging for arrays
 * - Two-pointer merge algorithms
 * - Memory-efficient processing
 * - Optimized for array-based data
 * 
 * 4️⃣ INTERVAL MERGING PROCESSING
 * - Overlapping interval detection
 * - Range consolidation algorithms
 * - Timeline merging strategies
 * - Event processing optimization
 * 
 * 5️⃣ COMMON ELEMENT FINDING
 * - Multi-list intersection algorithms
 * - Synchronized traversal techniques
 * - Efficient common value detection
 * - Set intersection optimization
 * 
 * 6️⃣ RANGE COVERING OPTIMIZATION
 * - Smallest range finding algorithms
 * - Multi-dimensional merging
 * - Coverage optimization strategies
 * - Window-based processing techniques
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 PRIORITY QUEUE MERGE ALGORITHM:
 * ```
 * Min-Heap based merging:
 *   1. Initialize min-heap with first element from each list
 *   2. While heap is not empty:
 *      - Extract minimum element from heap
 *      - Add to result list
 *      - If extracted element has next, add next to heap
 *   3. Return merged result
 * 
 * Time: O(N log K), Space: O(K)
 * where N = total elements, K = number of lists
 * ```
 * 
 * 🔹 DIVIDE AND CONQUER MERGE ALGORITHM:
 * ```
 * Recursive pairwise merging:
 *   1. If only one list, return it
 *   2. If two lists, merge them directly
 *   3. Divide lists into two halves
 *   4. Recursively merge each half
 *   5. Merge the two merged halves
 * 
 * Time: O(N log K), Space: O(log K) for recursion
 * ```
 * 
 * 🔹 INTERVAL MERGE ALGORITHM:
 * ```
 * Overlapping interval merging:
 *   1. Sort intervals by start time
 *   2. Initialize result with first interval
 *   3. For each subsequent interval:
 *      - If overlaps with last in result: merge them
 *      - Otherwise: add as new interval
 *   4. Return merged intervals
 * 
 * Time: O(N log N), Space: O(1) for merging
 * ```
 * 
 * 🔹 SMALLEST RANGE ALGORITHM:
 * ```
 * Multi-list range finding:
 *   1. Use min-heap with one element from each list
 *   2. Track current maximum element
 *   3. While all lists have elements:
 *      - Current range = [heap.min, current_max]
 *      - Update smallest range if current is smaller
 *      - Move to next element in list of minimum
 *   4. Return smallest range found
 * 
 * Time: O(N log K), Space: O(K)
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: ANALYZE DATA CHARACTERISTICS
 * - Number of lists K vs total elements N?
 * - Memory constraints vs time optimization?
 * - LinkedList vs Array representation?
 * 
 * STEP 2: CHOOSE MERGE STRATEGY
 * - Priority queue: best for large K, handles any list sizes
 * - Divide and conquer: better for small K, more cache-friendly
 * - Direct merging: optimal for K=2 or very small K
 * 
 * STEP 3: IMPLEMENT CORE ALGORITHM
 * - Handle empty lists and edge cases
 * - Optimize for specific data structures (nodes vs arrays)
 * - Consider memory allocation patterns
 * 
 * STEP 4: OPTIMIZE FOR CONSTRAINTS
 * - Memory optimization: minimize heap size and allocations
 * - Time optimization: choose optimal merge strategy
 * - Cache optimization: consider data locality patterns
 * 
 * STEP 5: VALIDATE AND TEST
 * - Test with various K values and list sizes
 * - Verify sorted order maintenance
 * - Benchmark different approaches for specific use cases
 * 
 * ============================================================================
 * 🎨 MERGE K LISTS ALGORITHM TEMPLATES
 * ============================================================================
 */

public class MergeKListsReadingGuide {
    
    /**
     * ListNode class for linked list problems
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    /**
     * Interval class for interval problems
     */
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    
    /**
     * 🏆 PRIORITY QUEUE MERGING TEMPLATES
     */
    public static class PriorityQueueMerging {
        
        /**
         * Merge K Sorted Lists using Min-Heap
         * Strategy: Use priority queue to always select smallest available element
         */
        public static ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            
            // Min-heap based on node values
            java.util.PriorityQueue<ListNode> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a.val - b.val
            );
            
            // Add first node from each non-empty list
            for (ListNode list : lists) {
                if (list != null) {
                    minHeap.offer(list);
                }
            }
            
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            // Process until heap is empty
            while (!minHeap.isEmpty()) {
                ListNode smallest = minHeap.poll();
                current.next = smallest;
                current = current.next;
                
                // Add next node from same list if exists
                if (smallest.next != null) {
                    minHeap.offer(smallest.next);
                }
            }
            
            return dummy.next;
        }
        
        /**
         * Merge K Sorted Arrays using Priority Queue
         * Strategy: Use heap with array indices for efficient merging
         */
        public static List<Integer> mergeKSortedArrays(int[][] arrays) {
            List<Integer> result = new ArrayList<>();
            
            // Min-heap storing [value, arrayIndex, elementIndex]
            java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a[0] - b[0]
            );
            
            // Initialize heap with first element from each array
            for (int i = 0; i < arrays.length; i++) {
                if (arrays[i].length > 0) {
                    minHeap.offer(new int[]{arrays[i][0], i, 0});
                }
            }
            
            while (!minHeap.isEmpty()) {
                int[] current = minHeap.poll();
                int value = current[0];
                int arrayIndex = current[1];
                int elementIndex = current[2];
                
                result.add(value);
                
                // Add next element from same array if exists
                if (elementIndex + 1 < arrays[arrayIndex].length) {
                    minHeap.offer(new int[]{
                        arrays[arrayIndex][elementIndex + 1],
                        arrayIndex,
                        elementIndex + 1
                    });
                }
            }
            
            return result;
        }
        
        /**
         * Smallest Range Covering Elements from K Lists
         * Strategy: Track current range while advancing minimum element
         */
        public static int[] smallestRange(List<List<Integer>> nums) {
            // Min-heap storing [value, listIndex, elementIndex]
            java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a[0] - b[0]
            );
            
            int currentMax = Integer.MIN_VALUE;
            
            // Initialize heap with first element from each list
            for (int i = 0; i < nums.size(); i++) {
                if (!nums.get(i).isEmpty()) {
                    int value = nums.get(i).get(0);
                    minHeap.offer(new int[]{value, i, 0});
                    currentMax = Math.max(currentMax, value);
                }
            }
            
            int[] smallestRange = {Integer.MIN_VALUE, Integer.MAX_VALUE};
            
            while (minHeap.size() == nums.size()) {
                int[] current = minHeap.poll();
                int currentMin = current[0];
                int listIndex = current[1];
                int elementIndex = current[2];
                
                // Update smallest range if current is better
                if (currentMax - currentMin < smallestRange[1] - smallestRange[0]) {
                    smallestRange[0] = currentMin;
                    smallestRange[1] = currentMax;
                }
                
                // Move to next element in the same list
                if (elementIndex + 1 < nums.get(listIndex).size()) {
                    int nextValue = nums.get(listIndex).get(elementIndex + 1);
                    minHeap.offer(new int[]{nextValue, listIndex, elementIndex + 1});
                    currentMax = Math.max(currentMax, nextValue);
                }
            }
            
            return smallestRange;
        }
    }
    
    /**
     * ⚡ DIVIDE AND CONQUER MERGING TEMPLATES
     */
    public static class DivideConquerMerging {
        
        /**
         * Merge K Lists using Divide and Conquer
         * Strategy: Recursively merge pairs of lists for balanced processing
         */
        public static ListNode mergeKListsDivideConquer(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            
            return mergeHelper(lists, 0, lists.length - 1);
        }
        
        private static ListNode mergeHelper(ListNode[] lists, int left, int right) {
            if (left == right) return lists[left];
            
            int mid = left + (right - left) / 2;
            ListNode leftMerged = mergeHelper(lists, left, mid);
            ListNode rightMerged = mergeHelper(lists, mid + 1, right);
            
            return mergeTwoLists(leftMerged, rightMerged);
        }
        
        /**
         * Merge Two Sorted Lists
         * Strategy: Two-pointer approach for optimal merging
         */
        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    current.next = list1;
                    list1 = list1.next;
                } else {
                    current.next = list2;
                    list2 = list2.next;
                }
                current = current.next;
            }
            
            // Attach remaining elements
            current.next = (list1 != null) ? list1 : list2;
            
            return dummy.next;
        }
        
        /**
         * Merge K Sorted Arrays using Divide and Conquer
         * Strategy: Recursively merge array pairs for balanced processing
         */
        public static int[] mergeKArrays(int[][] arrays) {
            if (arrays.length == 0) return new int[0];
            
            return mergeArraysHelper(arrays, 0, arrays.length - 1);
        }
        
        private static int[] mergeArraysHelper(int[][] arrays, int left, int right) {
            if (left == right) return arrays[left];
            
            int mid = left + (right - left) / 2;
            int[] leftMerged = mergeArraysHelper(arrays, left, mid);
            int[] rightMerged = mergeArraysHelper(arrays, mid + 1, right);
            
            return mergeTwoArrays(leftMerged, rightMerged);
        }
        
        private static int[] mergeTwoArrays(int[] arr1, int[] arr2) {
            int[] result = new int[arr1.length + arr2.length];
            int i = 0, j = 0, k = 0;
            
            while (i < arr1.length && j < arr2.length) {
                if (arr1[i] <= arr2[j]) {
                    result[k++] = arr1[i++];
                } else {
                    result[k++] = arr2[j++];
                }
            }
            
            while (i < arr1.length) result[k++] = arr1[i++];
            while (j < arr2.length) result[k++] = arr2[j++];
            
            return result;
        }
    }
    
    /**
     * 📊 ARRAY MERGING TECHNIQUES TEMPLATES
     */
    public static class ArrayMergingTechniques {
        
        /**
         * Merge Sorted Array (In-place)
         * Strategy: Start from end to avoid overwriting elements
         */
        public static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1; // Last element in nums1
            int j = n - 1; // Last element in nums2
            int k = m + n - 1; // Last position in result
            
            // Merge from the end
            while (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }
            
            // Copy remaining elements from nums2
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }
    }
    
    /**
     * 📅 INTERVAL MERGING TEMPLATES
     */
    public static class IntervalMerging {
        
        /**
         * Merge Overlapping Intervals
         * Strategy: Sort by start time and merge overlapping intervals
         */
        public static int[][] mergeIntervals(int[][] intervals) {
            if (intervals.length <= 1) return intervals;
            
            // Sort by start time
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            
            List<int[]> merged = new ArrayList<>();
            merged.add(intervals[0]);
            
            for (int i = 1; i < intervals.length; i++) {
                int[] current = intervals[i];
                int[] last = merged.get(merged.size() - 1);
                
                if (current[0] <= last[1]) {
                    // Overlapping intervals - merge them
                    last[1] = Math.max(last[1], current[1]);
                } else {
                    // Non-overlapping - add as new interval
                    merged.add(current);
                }
            }
            
            return merged.toArray(new int[merged.size()][]);
        }
        
        /**
         * Insert Interval and Merge
         * Strategy: Find insertion point and merge with overlapping intervals
         */
        public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();
            int i = 0;
            
            // Add all intervals that end before newInterval starts
            while (i < intervals.length && intervals[i][1] < newInterval[0]) {
                result.add(intervals[i++]);
            }
            
            // Merge overlapping intervals with newInterval
            while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }
            result.add(newInterval);
            
            // Add remaining intervals
            while (i < intervals.length) {
                result.add(intervals[i++]);
            }
            
            return result.toArray(new int[result.size()][]);
        }
    }
    
    /**
     * 🔍 COMMON ELEMENT FINDING TEMPLATES
     */
    public static class CommonElementFinding {
        
        /**
         * Find Common Elements in K Sorted Arrays
         * Strategy: Use synchronized traversal with multiple pointers
         */
        public static List<Integer> findCommonElements(int[][] arrays) {
            List<Integer> result = new ArrayList<>();
            
            if (arrays.length == 0 || arrays[0].length == 0) return result;
            
            int[] pointers = new int[arrays.length];
            
            while (true) {
                // Find current minimum and maximum
                int min = arrays[0][pointers[0]];
                int max = arrays[0][pointers[0]];
                
                for (int i = 0; i < arrays.length; i++) {
                    if (pointers[i] >= arrays[i].length) return result;
                    min = Math.min(min, arrays[i][pointers[i]]);
                    max = Math.max(max, arrays[i][pointers[i]]);
                }
                
                if (min == max) {
                    // All elements are equal - found common element
                    result.add(min);
                    // Advance all pointers
                    for (int i = 0; i < arrays.length; i++) {
                        pointers[i]++;
                    }
                } else {
                    // Advance pointers pointing to minimum values
                    for (int i = 0; i < arrays.length; i++) {
                        if (arrays[i][pointers[i]] == min) {
                            pointers[i]++;
                        }
                    }
                }
            }
        }
        
        /**
         * Find Smallest Common Number
         * Strategy: Binary search on one array for elements from others
         */
        public static int findSmallestCommonNumber(int[] arr1, int[] arr2, int[] arr3) {
            int i = 0, j = 0, k = 0;
            
            while (i < arr1.length && j < arr2.length && k < arr3.length) {
                if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                    return arr1[i];
                }
                
                // Move pointer of smallest element
                if (arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
                    i++;
                } else if (arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
                    j++;
                } else {
                    k++;
                }
            }
            
            return -1; // No common element found
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Priority Queue Comparator
     * Ensure proper comparison for min-heap behavior
     */
    public static void heapComparatorExample() {
        // Correct: Min-heap for smallest element selection
        java.util.PriorityQueue<ListNode> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> a.val - b.val
        );
        
        // Incorrect: Max-heap when we need min-heap
        // java.util.PriorityQueue<ListNode> maxHeap = new java.util.PriorityQueue<>(
        //     (a, b) -> b.val - a.val
        // );
    }
    
    /**
     * ❌ PITFALL 2: Not Handling Empty Lists
     * Always check for null and empty lists
     */
    public static void emptyListHandling() {
        // Correct: Check for null and empty
        ListNode[] lists = new ListNode[3];
        for (ListNode list : lists) {
            if (list != null) {
                // Process non-null list
            }
        }
        
        // Incorrect: Assuming all lists are non-null
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Choose Right Algorithm for K and N
     * - Small K: Divide and conquer approach
     * - Large K: Priority queue approach
     * - K=2: Direct two-list merge
     */
    
    /**
     * 🎯 STRATEGY 2: Memory vs Time Trade-offs
     * - Priority queue: O(K) space, O(N log K) time
     * - Divide and conquer: O(log K) space, O(N log K) time
     * - Direct merge: O(1) space, O(NK) time
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * DATA STRUCTURE:
     * ✅ LinkedLists → Priority queue or divide-and-conquer
     * ✅ Arrays → Divide-and-conquer or direct merging
     * ✅ Intervals → Sort by start time then merge
     * ✅ Streams → Priority queue with iterators
     * 
     * CONSTRAINTS:
     * ✅ Memory limited → Divide-and-conquer for O(log K) space
     * ✅ Time critical → Priority queue for consistent O(N log K)
     * ✅ Cache efficiency → Divide-and-conquer for better locality
     * ✅ Simple implementation → Priority queue approach
     */
    
    public static void main(String[] args) {
        System.out.println("🎯 MERGE K LISTS PATTERN - READING GUIDE");
        System.out.println("=========================================");
        
        // Test examples with different approaches
        
        // Example 1: Priority Queue Merge
        ListNode[] lists1 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged1 = PriorityQueueMerging.mergeKLists(lists1);
        System.out.println("Priority Queue Merge: " + listToString(merged1)); // 1->1->2->3->4->4->5->6
        
        // Example 2: Divide and Conquer Merge
        ListNode[] lists2 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged2 = DivideConquerMerging.mergeKListsDivideConquer(lists2);
        System.out.println("Divide & Conquer Merge: " + listToString(merged2)); // 1->1->2->3->4->4->5->6
        
        // Example 3: Array Merging
        int[][] arrays = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        List<Integer> mergedArrays = PriorityQueueMerging.mergeKSortedArrays(arrays);
        System.out.println("Array Merge: " + mergedArrays); // [1, 1, 2, 3, 4, 4, 5, 6]
        
        // Example 4: Interval Merging
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = IntervalMerging.mergeIntervals(intervals);
        System.out.println("Merged Intervals: " + Arrays.deepToString(mergedIntervals)); // [[1,6],[8,10],[15,18]]
        
        // Example 5: Smallest Range
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4, 10, 15, 24, 26),
            Arrays.asList(0, 9, 12, 20),
            Arrays.asList(5, 18, 22, 30)
        );
        int[] smallestRange = PriorityQueueMerging.smallestRange(nums);
        System.out.println("Smallest Range: [" + smallestRange[0] + ", " + smallestRange[1] + "]"); // [20, 24]
        
        System.out.println("\n✅ Key Merge K Lists Principles:");
        System.out.println("1. Use priority queue for optimal element selection across K lists");
        System.out.println("2. Apply divide-and-conquer for balanced processing and cache efficiency");
        System.out.println("3. Handle empty lists and edge cases properly");
        System.out.println("4. Choose algorithm based on K size and memory constraints");
        System.out.println("5. Maintain sorted order throughout the merging process");
        System.out.println("6. Use appropriate data structures for different problem types");
        System.out.println("7. Optimize for specific constraints (time vs space vs cache)");
    }
    
    // Helper methods for testing
    private static ListNode createList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
    
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append("->");
            head = head.next;
        }
        return sb.toString();
    }
} 