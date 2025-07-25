package com.dsalgo.AlgoPatterns.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Sorting is a fundamental algorithmic pattern that involves arranging elements
 * in a specific order to simplify searching, counting, comparing, or other operations.
 * 
 * Consider problems where arranging elements in a specific order can simplify 
 * searching, counting, or comparing elements efficiently.
 * 
 * Look for tasks where sorting elements according to certain criteria can lead 
 * to a solution or optimize subsequent operations.
 * 
 * Look for problems mentioning that the array needs to be sorted first or hints 
 * that sorting might facilitate the solution process.
 * 
 * Example:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Explanation: Sort the array containing only 0s, 1s, and 2s.
 */
public class Sorting {

    public static void main(String[] args) {
        // Test Sorting problems
        System.out.println("=== SORTING PROBLEMS TEST ===");
        
        // Test Sort Colors
        int[] colors = {2,0,2,1,1,0};
        sortColors(colors);
        System.out.println("Sorted colors: " + Arrays.toString(colors));
        
        // Test Largest Number
        int[] nums = {10,2};
        System.out.println("Largest number: " + largestNumber(nums));
        
        // Test Wiggle Sort
        int[] wiggle = {1,5,1,1,6,4};
        wiggleSort(wiggle);
        System.out.println("Wiggle sorted: " + Arrays.toString(wiggle));
        
        // Test Meeting Rooms
        int[][] meetings = {{0,30},{5,10},{15,20}};
        System.out.println("Min meeting rooms: " + minMeetingRooms(meetings));
        
        // Test Kth Largest
        int[] kthArray = {3,2,1,5,6,4};
        System.out.println("3rd largest: " + findKthLargest(kthArray, 3));
    }

    // ==================================================================================
    //                             SORTING PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Sort Colors (Dutch National Flag)
     * DIFFICULTY: Easy
     * PATTERN: Counting/Bucket Sort with Three Pointers
     * 
     * DESCRIPTION:
     * Given an array nums with n objects colored red, white, or blue, sort them 
     * in-place so that objects of the same color are adjacent, with the colors 
     * in the order red, white, and blue (represented by integers 0, 1, and 2).
     * 
     * APPROACH:
     * 1. Use three pointers: low (for 0s), mid (current), high (for 2s)
     * 2. Partition array into three sections
     * 3. Move appropriate elements to their correct sections
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Move 0 to low section
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // 1 is already in correct position
                mid++;
            } else { // nums[mid] == 2
                // Move 2 to high section
                swap(nums, mid, high);
                high--;
                // Don't increment mid (need to check swapped element)
            }
        }
    }
    
    /**
     * PROBLEM: Merge Sorted Array
     * DIFFICULTY: Easy
     * PATTERN: Basic Sorting with Two Pointers
     * 
     * DESCRIPTION:
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing 
     * order, and two integers m and n, representing the number of elements in 
     * nums1 and nums2 respectively. Merge nums2 into nums1 as one sorted array.
     * 
     * APPROACH:
     * 1. Start from the end of both arrays
     * 2. Compare elements and place larger one at end of nums1
     * 3. Move pointers backward
     * 
     * TIME COMPLEXITY: O(m + n)
     * SPACE COMPLEXITY: O(1)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;      // Last element in nums1
        int j = n - 1;      // Last element in nums2
        int k = m + n - 1;  // Last position in merged array
        
        // Merge from the end to avoid overwriting
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        
        // Copy remaining elements from nums2
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
    
    /**
     * PROBLEM: Squares of a Sorted Array
     * DIFFICULTY: Easy
     * PATTERN: Sorting with Two Pointers
     * 
     * DESCRIPTION:
     * Given an integer array nums sorted in non-decreasing order, return an 
     * array of the squares of each number sorted in non-decreasing order.
     * 
     * APPROACH:
     * 1. Use two pointers from both ends
     * 2. Compare absolute values and place larger square at end
     * 3. Fill result array from right to left
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int index = n - 1;
        
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Sort Array by Parity
     * DIFFICULTY: Easy
     * PATTERN: Partition-based Sorting
     * 
     * DESCRIPTION:
     * Given an integer array nums, move all the even integers at the beginning 
     * of the array followed by all the odd integers.
     * 
     * APPROACH:
     * 1. Use two pointers: one for even position, one for scanning
     * 2. When odd number found, swap with current even position
     * 3. Increment even position pointer
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int[] sortArrayByParity(int[] nums) {
        int even = 0; // Position for next even number
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                swap(nums, even, i);
                even++;
            }
        }
        
        return nums;
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Largest Number
     * DIFFICULTY: Medium
     * PATTERN: Custom Comparator Sorting
     * 
     * DESCRIPTION:
     * Given a list of non-negative integers nums, arrange them such that they 
     * form the largest number and return it.
     * 
     * APPROACH:
     * 1. Convert numbers to strings
     * 2. Sort with custom comparator: compare concatenated results
     * 3. Concatenate sorted strings
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        // Custom comparator: if a+b > b+a, then a should come before b
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        
        // Handle edge case: all zeros
        if (strs[0].equals("0")) return "0";
        
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            result.append(str);
        }
        
        return result.toString();
    }
    
    /**
     * PROBLEM: Meeting Rooms II
     * DIFFICULTY: Medium
     * PATTERN: Sorting + Heap
     * 
     * DESCRIPTION:
     * Given an array of meeting time intervals intervals where 
     * intervals[i] = [start_i, end_i], return the minimum number of 
     * conference rooms required.
     * 
     * APPROACH:
     * 1. Sort meetings by start time
     * 2. Use min-heap to track end times
     * 3. For each meeting, check if room becomes free
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Min-heap to track end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            // If current meeting starts after earliest ending meeting
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll(); // Room becomes free
            }
            // Add current meeting's end time
            minHeap.add(intervals[i][1]);
        }
        
        return minHeap.size();
    }
    
    /**
     * PROBLEM: Wiggle Sort II
     * DIFFICULTY: Medium
     * PATTERN: Advanced Arrangement Sorting
     * 
     * DESCRIPTION:
     * Given an integer array nums, reorder it such that 
     * nums[0] < nums[1] > nums[2] < nums[3]....
     * 
     * APPROACH:
     * 1. Sort the array
     * 2. Split into two halves
     * 3. Interleave from smaller half and larger half
     * 4. Fill from ends to avoid adjacent conflicts
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        
        int mid = (n - 1) / 2;
        int end = n - 1;
        
        // Fill alternately: smaller half on even indices, larger half on odd indices
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[mid--];
            } else {
                nums[i] = sorted[end--];
            }
        }
    }
    
    /**
     * PROBLEM: Top K Frequent Elements
     * DIFFICULTY: Medium
     * PATTERN: Sorting + Frequency Counting
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * 
     * APPROACH:
     * 1. Count frequency of each element
     * 2. Sort by frequency using min-heap
     * 3. Return top k elements
     * 
     * TIME COMPLEXITY: O(n log k)
     * SPACE COMPLEXITY: O(n)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Min-heap based on frequency
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a, b) -> freqMap.get(a) - freqMap.get(b)
        );
        
        for (int num : freqMap.keySet()) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll();
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Sort Characters by Frequency
     * DIFFICULTY: Medium
     * PATTERN: Custom Sorting with Frequency
     * 
     * DESCRIPTION:
     * Given a string s, sort it in decreasing order based on the frequency of 
     * the characters. The frequency of a character is the number of times it 
     * appears in the string.
     * 
     * APPROACH:
     * 1. Count frequency of each character
     * 2. Sort characters by frequency (descending)
     * 3. Build result string
     * 
     * TIME COMPLEXITY: O(n + k log k) where k is unique characters
     * SPACE COMPLEXITY: O(n)
     */
    public static String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Sort characters by frequency (descending)
        List<Character> chars = new ArrayList<>(freqMap.keySet());
        chars.sort((a, b) -> freqMap.get(b) - freqMap.get(a));
        
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            int freq = freqMap.get(c);
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * PROBLEM: Kth Largest Element in Array
     * DIFFICULTY: Medium
     * PATTERN: Partial Sorting (QuickSelect)
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return the kth largest 
     * element in the array. Note that it is the kth largest element in the 
     * sorted order, not the kth distinct element.
     * 
     * APPROACH:
     * 1. Use QuickSelect algorithm
     * 2. Partition around pivot
     * 3. Recursively search in appropriate half
     * 
     * TIME COMPLEXITY: O(n) average, O(n²) worst case
     * SPACE COMPLEXITY: O(1)
     */
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
     * PROBLEM: Custom Sort String
     * DIFFICULTY: Medium
     * PATTERN: Custom Comparator with Priority
     * 
     * DESCRIPTION:
     * You are given two strings order and s. All the characters of order are 
     * unique and were sorted in some custom order previously. Permute the 
     * characters of s so that they match the order that order was sorted.
     * 
     * APPROACH:
     * 1. Create priority map from order string
     * 2. Sort characters in s based on this priority
     * 3. Characters not in order go at the end
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static String customSortString(String order, String s) {
        Map<Character, Integer> priority = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            priority.put(order.charAt(i), i);
        }
        
        Character[] chars = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        
        Arrays.sort(chars, (a, b) -> {
            int priorityA = priority.getOrDefault(a, Integer.MAX_VALUE);
            int priorityB = priority.getOrDefault(b, Integer.MAX_VALUE);
            return Integer.compare(priorityA, priorityB);
        });
        
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Count of Smaller Numbers After Self
     * DIFFICULTY: Hard
     * PATTERN: Merge Sort with Index Tracking
     * 
     * DESCRIPTION:
     * Given an integer array nums, return an integer array counts where 
     * counts[i] is the number of smaller elements to the right of nums[i].
     * 
     * APPROACH:
     * 1. Create array of [value, originalIndex] pairs
     * 2. Use modified merge sort
     * 3. During merge, count elements from right that are smaller
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[][] pairs = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums[i], i};
        }
        
        mergeSort(pairs, 0, n - 1, result);
        
        List<Integer> list = new ArrayList<>();
        for (int count : result) {
            list.add(count);
        }
        return list;
    }
    
    private static void mergeSort(int[][] pairs, int left, int right, int[] result) {
        if (left >= right) return;
        
        int mid = left + (right - left) / 2;
        mergeSort(pairs, left, mid, result);
        mergeSort(pairs, mid + 1, right, result);
        mergeAndCount(pairs, left, mid, right, result);
    }
    
    private static void mergeAndCount(int[][] pairs, int left, int mid, int right, int[] result) {
        int[][] temp = new int[right - left + 1][2];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (pairs[i][0] <= pairs[j][0]) {
                result[pairs[i][1]] += (j - mid - 1);
                temp[k++] = pairs[i++];
            } else {
                temp[k++] = pairs[j++];
            }
        }
        
        while (i <= mid) {
            result[pairs[i][1]] += (j - mid - 1);
            temp[k++] = pairs[i++];
        }
        
        while (j <= right) {
            temp[k++] = pairs[j++];
        }
        
        for (int idx = 0; idx < temp.length; idx++) {
            pairs[left + idx] = temp[idx];
        }
    }
    
    /**
     * PROBLEM: Create Maximum Number
     * DIFFICULTY: Hard
     * PATTERN: Greedy Selection + Merge
     * 
     * DESCRIPTION:
     * You are given two integer arrays nums1 and nums2 of lengths m and n 
     * respectively. nums1 and nums2 represent the digits of two numbers. 
     * You are also given an integer k. Create the maximum number of length k 
     * <= m + n from digits of the two arrays.
     * 
     * APPROACH:
     * 1. Try all possible combinations of selecting i from nums1 and k-i from nums2
     * 2. For each array, find maximum subsequence of required length
     * 3. Merge two subsequences to get maximum result
     * 
     * TIME COMPLEXITY: O(k * (m + n + k²))
     * SPACE COMPLEXITY: O(k)
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[k];
        
        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] candidate = merge(
                maxSubsequence(nums1, i),
                maxSubsequence(nums2, k - i)
            );
            
            if (isGreater(candidate, result)) {
                result = candidate;
            }
        }
        
        return result;
    }
    
    private static int[] maxSubsequence(int[] nums, int k) {
        if (k == 0) return new int[0];
        
        int[] result = new int[k];
        int toRemove = nums.length - k;
        int idx = 0;
        
        for (int num : nums) {
            while (idx > 0 && result[idx - 1] < num && toRemove > 0) {
                idx--;
                toRemove--;
            }
            if (idx < k) {
                result[idx++] = num;
            } else {
                toRemove--;
            }
        }
        
        return result;
    }
    
    private static int[] merge(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (isGreater(nums1, i, nums2, j)) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        
        while (i < nums1.length) result[k++] = nums1[i++];
        while (j < nums2.length) result[k++] = nums2[j++];
        
        return result;
    }
    
    private static boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    
    private static boolean isGreater(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != nums2[i]) {
                return nums1[i] > nums2[i];
            }
        }
        return false;
    }
    
    // ==================================================================================
    //                              TOPOLOGICAL SORTING
    // ==================================================================================
    
    /**
     * PROBLEM: Course Schedule
     * DIFFICULTY: Medium
     * PATTERN: Topological Sorting
     * 
     * DESCRIPTION:
     * There are a total of numCourses courses you have to take, labeled from 0 
     * to numCourses - 1. You are given an array prerequisites where 
     * prerequisites[i] = [ai, bi] indicates that you must take course bi first 
     * if you want to take course ai.
     * 
     * APPROACH:
     * 1. Build dependency graph
     * 2. Calculate in-degrees
     * 3. Use BFS to process nodes with zero in-degree
     * 4. Check if all courses can be completed
     * 
     * TIME COMPLEXITY: O(V + E)
     * SPACE COMPLEXITY: O(V + E)
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] indegree = new int[numCourses];
        
        // Initialize graph
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Build graph and calculate in-degrees
        for (int[] prereq : prerequisites) {
            graph[prereq[1]].add(prereq[0]);
            indegree[prereq[0]]++;
        }
        
        // Find courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
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
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to swap two elements in an array
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Helper method to print array for debugging
     */
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Helper method to print 2D array for debugging
     */
    private static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
    }
} 