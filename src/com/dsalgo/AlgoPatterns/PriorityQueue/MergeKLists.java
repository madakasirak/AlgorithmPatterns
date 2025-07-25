package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 MERGE K LISTS PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of merge algorithms for efficiently
 * combining multiple sorted sequences while maintaining order. These algorithms demonstrate
 * priority queue-based merging for optimal time complexity, divide-and-conquer strategies
 * for balanced processing, interval merging techniques for overlapping ranges, and
 * specialized algorithms for finding common elements across multiple sorted data sources.
 * 
 * Pattern Focus: Multi-sequence merging, order preservation, interval processing
 * Time Complexity: O(N log K) for most algorithms where N = total elements, K = number of sequences
 * Space Complexity: O(K) for priority queue approaches, O(log K) for divide-and-conquer
 */

public class MergeKLists {
    
    /**
     * ListNode class for linked list problems
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                sb.append(current.val);
                if (current.next != null) sb.append("->");
                current = current.next;
            }
            return sb.toString();
        }
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Core Merge K Lists Algorithms
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Merge K Sorted Lists
     * 
     * Problem: Merge k sorted linked lists and return it as one sorted list
     * LeetCode: https://leetcode.com/problems/merge-k-sorted-lists/
     * 
     * Approach 1: Priority Queue (Min-Heap)
     * - Use min-heap to always select smallest available element
     * - Add first node from each non-empty list to heap
     * - Extract minimum, add to result, advance pointer in same list
     * - Optimal for scenarios with many lists or when lists have varying sizes
     * 
     * Time: O(N log K), Space: O(K)
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
     * Alternative: Divide and Conquer approach
     * - Recursively merge pairs of lists for balanced processing
     * - Better cache locality and reduced heap overhead
     * - Optimal when K is relatively small
     * 
     * Time: O(N log K), Space: O(log K) for recursion
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
     * Helper: Merge Two Sorted Lists
     * - Core building block for divide-and-conquer approach
     * - Two-pointer technique for optimal merging
     * 
     * Time: O(n + m), Space: O(1)
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
     * 🟡 MEDIUM: Merge Sorted Array
     * 
     * Problem: Merge nums2 into nums1 in-place as one sorted array
     * LeetCode: https://leetcode.com/problems/merge-sorted-array/
     * 
     * Approach: Reverse Two-Pointer Technique
     * - Start from end to avoid overwriting elements
     * - Compare elements from end of both arrays
     * - Place larger element at end of merged array
     * - Elegant solution that requires no extra space
     * 
     * Time: O(m + n), Space: O(1)
     */
    public static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Last element in nums1's valid portion
        int j = n - 1; // Last element in nums2
        int k = m + n - 1; // Last position in result array
        
        // Merge from the end to avoid overwriting
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        
        // Copy remaining elements from nums2 (nums1 elements are already in place)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
    
    /**
     * 🟡 MEDIUM: Merge Intervals
     * 
     * Problem: Merge all overlapping intervals
     * LeetCode: https://leetcode.com/problems/merge-intervals/
     * 
     * Approach: Sort and Merge Strategy
     * - Sort intervals by start time for processing order
     * - Iterate through intervals, merging overlapping ones
     * - Two intervals overlap if current start <= previous end
     * - Essential for timeline processing and scheduling problems
     * 
     * Time: O(n log n), Space: O(1) for merging (excluding output)
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
     * Bonus: Insert Interval and Merge
     * - Insert new interval into sorted list and merge overlaps
     * - More complex variant requiring careful boundary handling
     * 
     * Time: O(n), Space: O(n)
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
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Merge Algorithms
    // ============================================================================
    
    /**
     * 🔴 HARD: Find Smallest Common Number
     * 
     * Problem: Find smallest number common to all three sorted arrays
     * 
     * Approach: Synchronized Three-Pointer Traversal
     * - Use three pointers, one for each array
     * - Advance pointer with smallest current value
     * - When all three values are equal, found common element
     * - Efficient single-pass algorithm with optimal time complexity
     * 
     * Time: O(n1 + n2 + n3), Space: O(1)
     */
    public static int findSmallestCommonNumber(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                return arr1[i]; // Found smallest common number
            }
            
            // Move pointer of smallest element to find next potential match
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
    
    /**
     * Generalized: Find Common Elements in K Sorted Arrays
     * - Extend three-pointer approach to K arrays
     * - Use array of pointers for K arrays
     * - More complex but handles arbitrary number of arrays
     * 
     * Time: O(N * K), Space: O(K) for pointers
     */
    public static List<Integer> findCommonElementsKArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        
        if (arrays.length == 0 || arrays[0].length == 0) return result;
        
        int[] pointers = new int[arrays.length];
        
        while (true) {
            // Find current minimum and maximum across all arrays
            int min = arrays[0][pointers[0]];
            int max = arrays[0][pointers[0]];
            boolean canContinue = true;
            
            for (int i = 0; i < arrays.length; i++) {
                if (pointers[i] >= arrays[i].length) {
                    canContinue = false;
                    break;
                }
                min = Math.min(min, arrays[i][pointers[i]]);
                max = Math.max(max, arrays[i][pointers[i]]);
            }
            
            if (!canContinue) break;
            
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
                    if (pointers[i] < arrays[i].length && arrays[i][pointers[i]] == min) {
                        pointers[i]++;
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * 🔴 HARD: Smallest Range Covering Elements from K Lists
     * 
     * Problem: Find smallest range that includes at least one number from each list
     * LeetCode: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
     * 
     * Approach: Priority Queue with Range Tracking
     * - Use min-heap to track current minimum across all lists
     * - Maintain current maximum element seen
     * - Current range = [min_element, max_element]
     * - Advance minimum element to find smaller ranges
     * - Stop when any list is exhausted
     * 
     * Time: O(N log K), Space: O(K)
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
        
        // Continue while we have elements from all lists
        while (minHeap.size() == nums.size()) {
            int[] current = minHeap.poll();
            int currentMin = current[0];
            int listIndex = current[1];
            int elementIndex = current[2];
            
            // Update smallest range if current is better
            if (currentMax - currentMin < smallestRange[1] - smallestRange[0] ||
                (currentMax - currentMin == smallestRange[1] - smallestRange[0] && 
                 currentMin < smallestRange[0])) {
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
    
    /**
     * 🔴 HARD: Merge K Sorted Arrays
     * 
     * Problem: Merge K sorted arrays into single sorted array
     * 
     * Approach 1: Priority Queue with Array Tracking
     * - Similar to merge K lists but for arrays
     * - Track array index and element index for each entry
     * - Efficient for large K or when arrays have varying sizes
     * 
     * Time: O(N log K), Space: O(K)
     */
    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        
        // Min-heap storing [value, arrayIndex, elementIndex]
        java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        
        // Initialize heap with first element from each non-empty array
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
     * Alternative: Divide and Conquer for K Arrays
     * - Recursively merge pairs of arrays
     * - Better cache locality for smaller K values
     * 
     * Time: O(N log K), Space: O(log K)
     */
    public static int[] mergeKArraysDivideConquer(int[][] arrays) {
        if (arrays.length == 0) return new int[0];
        if (arrays.length == 1) return arrays[0];
        
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
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎯 MERGE K LISTS PATTERN - PRACTICE PROBLEMS");
        System.out.println("===========================================");
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        // Test Merge K Sorted Lists
        ListNode[] lists1 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged1 = mergeKLists(lists1);
        System.out.println("Merge K Lists (Priority Queue): " + merged1); // 1->1->2->3->4->4->5->6
        
        ListNode[] lists2 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged2 = mergeKListsDivideConquer(lists2);
        System.out.println("Merge K Lists (Divide & Conquer): " + merged2); // 1->1->2->3->4->4->5->6
        
        // Test Merge Sorted Array
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        mergeSortedArray(nums1, 3, nums2, 3);
        System.out.println("Merge Sorted Array: " + Arrays.toString(nums1)); // [1, 2, 2, 3, 5, 6]
        
        // Test Merge Intervals
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = mergeIntervals(intervals);
        System.out.println("Merge Intervals: " + Arrays.deepToString(mergedIntervals)); // [[1,6],[8,10],[15,18]]
        
        // Test Insert Interval
        int[][] intervals2 = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] insertedIntervals = insertInterval(intervals2, newInterval);
        System.out.println("Insert Interval: " + Arrays.deepToString(insertedIntervals)); // [[1,5],[6,9]]
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        // Test Find Smallest Common Number
        int[] arr1 = {6, 7, 10, 25, 30, 63, 64};
        int[] arr2 = {0, 4, 5, 6, 7, 8, 50};
        int[] arr3 = {1, 6, 10, 14};
        int commonNumber = findSmallestCommonNumber(arr1, arr2, arr3);
        System.out.println("Smallest Common Number: " + commonNumber); // 6
        
        // Test Common Elements in K Arrays
        int[][] commonArrays = {{1, 2, 3, 4, 5}, {2, 3, 4, 5, 6}, {3, 4, 5, 6, 7}};
        List<Integer> commonElements = findCommonElementsKArrays(commonArrays);
        System.out.println("Common Elements in K Arrays: " + commonElements); // [3, 4, 5]
        
        // Test Smallest Range
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4, 10, 15, 24, 26),
            Arrays.asList(0, 9, 12, 20),
            Arrays.asList(5, 18, 22, 30)
        );
        int[] smallestRange = smallestRange(nums);
        System.out.println("Smallest Range: [" + smallestRange[0] + ", " + smallestRange[1] + "]"); // [20, 24]
        
        // Test Merge K Sorted Arrays
        int[][] arrays = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        List<Integer> mergedArrays = mergeKSortedArrays(arrays);
        System.out.println("Merge K Sorted Arrays (Priority Queue): " + mergedArrays); // [1, 1, 2, 3, 4, 4, 5, 6]
        
        int[] mergedArraysDC = mergeKArraysDivideConquer(arrays);
        System.out.println("Merge K Sorted Arrays (Divide & Conquer): " + Arrays.toString(mergedArraysDC)); // [1, 1, 2, 3, 4, 4, 5, 6]
        
        System.out.println("\n✅ Key Merge K Lists Principles:");
        System.out.println("1. Use priority queue for optimal element selection across K sequences");
        System.out.println("2. Apply divide-and-conquer for balanced processing and cache efficiency");
        System.out.println("3. Handle empty sequences and edge cases properly");
        System.out.println("4. Choose algorithm based on K size and memory constraints");
        System.out.println("5. Maintain sorted order throughout the merging process");
        System.out.println("6. Use reverse merging for in-place array operations");
        System.out.println("7. Sort intervals by start time for effective overlap detection");
        System.out.println("8. Apply synchronized traversal for finding common elements");
    }
    
    // Helper method for creating linked lists
    private static ListNode createList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
} 