package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * ⚡ DIVIDE & CONQUER PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of divide & conquer algorithms.
 * Divide & Conquer is a fundamental paradigm that breaks problems into smaller
 * subproblems, solves them recursively, and combines their solutions.
 * 
 * Pattern Focus: Problem decomposition, recursive solving, solution combination
 * Time Complexity: Often O(n log n) for optimal algorithms
 * Space Complexity: O(log n) for recursion depth, additional space for combine operations
 */
public class DivideAndConquer {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Fundamental Divide & Conquer Algorithms
    // ============================================================================
    
    /**
     * 🟢 EASY: Binary Search
     * 
     * Problem: Search for target value in sorted array
     * LeetCode: https://leetcode.com/problems/binary-search/
     * 
     * Approach: Classic divide & conquer search
     * - Divide: Compare target with middle element
     * - Conquer: Search appropriate half recursively
     * - Combine: Return result directly (no combine needed)
     * 
     * Time: O(log n), Space: O(log n) for recursion
     */
    public static int binarySearch(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }
    
    private static int binarySearchHelper(int[] nums, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }
        
        // Divide: find middle point
        int mid = left + (right - left) / 2;
        
        // Check if target found
        if (nums[mid] == target) {
            return mid;
        }
        
        // Conquer: search appropriate half
        if (nums[mid] > target) {
            return binarySearchHelper(nums, target, left, mid - 1);
        } else {
            return binarySearchHelper(nums, target, mid + 1, right);
        }
    }
    
    /**
     * 🟢 EASY: Find Maximum Element in Array
     * 
     * Problem: Find the maximum element in an unsorted array
     * 
     * Approach: Divide & conquer comparison
     * - Divide: Split array into two halves
     * - Conquer: Find max in each half recursively
     * - Combine: Return maximum of the two results
     * 
     * Time: O(n), Space: O(log n)
     */
    public static int findMaximum(int[] nums) {
        if (nums.length == 0) throw new IllegalArgumentException("Empty array");
        return findMaxHelper(nums, 0, nums.length - 1);
    }
    
    private static int findMaxHelper(int[] nums, int left, int right) {
        // Base case: single element
        if (left == right) {
            return nums[left];
        }
        
        // Base case: two elements
        if (right - left == 1) {
            return Math.max(nums[left], nums[right]);
        }
        
        // Divide: split into two halves
        int mid = left + (right - left) / 2;
        
        // Conquer: find max in each half
        int leftMax = findMaxHelper(nums, left, mid);
        int rightMax = findMaxHelper(nums, mid + 1, right);
        
        // Combine: return maximum
        return Math.max(leftMax, rightMax);
    }
    
    /**
     * 🟢 EASY: Power Calculation (x^n)
     * 
     * Problem: Calculate x raised to the power n efficiently
     * 
     * Approach: Fast exponentiation using divide & conquer
     * - Divide: Split exponent in half
     * - Conquer: Calculate power of half recursively
     * - Combine: Square result, multiply by base if odd exponent
     * 
     * Time: O(log n), Space: O(log n)
     */
    public static double myPow(double x, int n) {
        // Handle negative exponents
        if (n < 0) {
            return 1.0 / powHelper(x, -(long)n);
        }
        return powHelper(x, n);
    }
    
    private static double powHelper(double x, long n) {
        // Base case
        if (n == 0) return 1.0;
        if (n == 1) return x;
        
        // Divide: split exponent in half
        double halfPower = powHelper(x, n / 2);
        
        // Combine: square the result
        if (n % 2 == 0) {
            return halfPower * halfPower;
        } else {
            return x * halfPower * halfPower;
        }
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Classic Divide & Conquer Algorithms
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Merge Sort
     * 
     * Problem: Sort an array using divide & conquer approach
     * LeetCode: https://leetcode.com/problems/sort-an-array/
     * 
     * Approach: Divide & conquer sorting
     * - Divide: Split array into two halves
     * - Conquer: Sort each half recursively
     * - Combine: Merge sorted halves
     * 
     * Time: O(n log n), Space: O(n) for temporary arrays
     */
    public static int[] mergeSort(int[] nums) {
        if (nums.length <= 1) return nums.clone();
        
        int[] result = nums.clone();
        mergeSortHelper(result, 0, result.length - 1);
        return result;
    }
    
    private static void mergeSortHelper(int[] arr, int left, int right) {
        // Base case: single element
        if (left >= right) {
            return;
        }
        
        // Divide: split into two halves
        int mid = left + (right - left) / 2;
        
        // Conquer: sort each half
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid + 1, right);
        
        // Combine: merge sorted halves
        merge(arr, left, mid, right);
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        
        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, leftSize);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightSize);
        
        // Merge the temporary arrays
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        // Copy remaining elements
        while (i < leftSize) arr[k++] = leftArr[i++];
        while (j < rightSize) arr[k++] = rightArr[j++];
    }
    
    /**
     * 🟡 MEDIUM: Quick Sort
     * 
     * Problem: Sort an array using quick sort algorithm
     * LeetCode: https://leetcode.com/problems/sort-an-array/
     * 
     * Approach: Divide & conquer with partitioning
     * - Divide: Partition array around pivot
     * - Conquer: Sort each partition recursively
     * - Combine: No explicit combine (in-place sorting)
     * 
     * Time: O(n log n) average, O(n²) worst case, Space: O(log n)
     */
    public static int[] quickSort(int[] nums) {
        if (nums.length <= 1) return nums.clone();
        
        int[] result = nums.clone();
        quickSortHelper(result, 0, result.length - 1);
        return result;
    }
    
    private static void quickSortHelper(int[] arr, int left, int right) {
        // Base case: single element or empty
        if (left >= right) {
            return;
        }
        
        // Divide: partition around pivot
        int pivotIndex = partition(arr, left, right);
        
        // Conquer: sort each partition
        quickSortHelper(arr, left, pivotIndex - 1);
        quickSortHelper(arr, pivotIndex + 1, right);
    }
    
    private static int partition(int[] arr, int left, int right) {
        // Choose rightmost element as pivot
        int pivot = arr[right];
        int i = left - 1; // Index of smaller element
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, right);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 🟡 MEDIUM: Maximum Subarray Sum
     * 
     * Problem: Find the contiguous subarray with the maximum sum
     * LeetCode: https://leetcode.com/problems/maximum-subarray/
     * 
     * Approach: Divide & conquer approach to Kadane's algorithm
     * - Divide: Split array into two halves
     * - Conquer: Find max subarray in each half
     * - Combine: Find max crossing subarray, return maximum of all three
     * 
     * Time: O(n log n), Space: O(log n)
     * Note: Kadane's algorithm is O(n), but this shows D&C approach
     */
    public static int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    
    private static int maxSubArrayHelper(int[] nums, int left, int right) {
        // Base case: single element
        if (left == right) {
            return nums[left];
        }
        
        // Divide: find middle point
        int mid = left + (right - left) / 2;
        
        // Conquer: find max subarray in each half
        int leftMax = maxSubArrayHelper(nums, left, mid);
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        
        // Combine: find max crossing subarray
        int crossMax = maxCrossingSubarray(nums, left, mid, right);
        
        // Return maximum of all three
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private static int maxCrossingSubarray(int[] nums, int left, int mid, int right) {
        // Find max sum on left side of mid
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Find max sum on right side of mid
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        // Return sum of both sides
        return leftSum + rightSum;
    }
    
    /**
     * 🟡 MEDIUM: Inversion Count
     * 
     * Problem: Count number of inversions in array (i < j but arr[i] > arr[j])
     * 
     * Approach: Modified merge sort
     * - Divide: Split array into two halves
     * - Conquer: Count inversions in each half
     * - Combine: Count inversions across halves during merge
     * 
     * Time: O(n log n), Space: O(n)
     */
    public static long inversionCount(int[] nums) {
        if (nums.length <= 1) return 0;
        
        int[] temp = nums.clone();
        return inversionCountHelper(temp, 0, temp.length - 1);
    }
    
    private static long inversionCountHelper(int[] arr, int left, int right) {
        long invCount = 0;
        
        if (left < right) {
            // Divide
            int mid = left + (right - left) / 2;
            
            // Conquer: count inversions in each half
            invCount += inversionCountHelper(arr, left, mid);
            invCount += inversionCountHelper(arr, mid + 1, right);
            
            // Combine: count inversions across halves
            invCount += mergeAndCount(arr, left, mid, right);
        }
        
        return invCount;
    }
    
    private static long mergeAndCount(int[] arr, int left, int mid, int right) {
        // Create temporary arrays
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        
        System.arraycopy(arr, left, leftArr, 0, leftSize);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightSize);
        
        // Merge and count inversions
        int i = 0, j = 0, k = left;
        long invCount = 0;
        
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                // All elements from leftArr[i...] are greater than rightArr[j]
                invCount += (leftSize - i);
            }
        }
        
        // Copy remaining elements
        while (i < leftSize) arr[k++] = leftArr[i++];
        while (j < rightSize) arr[k++] = rightArr[j++];
        
        return invCount;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Divide & Conquer Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Closest Pair of Points
     * 
     * Problem: Find the closest pair of points in 2D plane
     * 
     * Approach: Divide & conquer geometric algorithm
     * - Divide: Split points by x-coordinate
     * - Conquer: Find closest pair in each half
     * - Combine: Check points near dividing line
     * 
     * Time: O(n log n), Space: O(n)
     */
    public static double closestPairDistance(Point[] points) {
        if (points.length < 2) return Double.MAX_VALUE;
        
        // Sort by x-coordinate
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        
        return closestPairHelper(points, 0, points.length - 1);
    }
    
    private static double closestPairHelper(Point[] points, int left, int right) {
        // Base case: brute force for small arrays
        if (right - left <= 2) {
            return bruteForceClosest(points, left, right);
        }
        
        // Divide: find middle point
        int mid = left + (right - left) / 2;
        Point midPoint = points[mid];
        
        // Conquer: find closest pair in each half
        double leftMin = closestPairHelper(points, left, mid);
        double rightMin = closestPairHelper(points, mid + 1, right);
        
        double minDist = Math.min(leftMin, rightMin);
        
        // Combine: check points near dividing line
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < minDist) {
                strip.add(points[i]);
            }
        }
        
        // Sort strip by y-coordinate
        strip.sort(Comparator.comparingDouble(p -> p.y));
        
        // Check distances in strip
        return Math.min(minDist, stripClosest(strip, minDist));
    }
    
    private static double bruteForceClosest(Point[] points, int left, int right) {
        double minDist = Double.MAX_VALUE;
        
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                minDist = Math.min(minDist, distance(points[i], points[j]));
            }
        }
        
        return minDist;
    }
    
    private static double stripClosest(List<Point> strip, double minDist) {
        double min = minDist;
        
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, distance(strip.get(i), strip.get(j)));
            }
        }
        
        return min;
    }
    
    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
    
    // Helper class for points
    static class Point {
        double x, y;
        
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    
    /**
     * 🔴 HARD: Median of Two Sorted Arrays
     * 
     * Problem: Find median of two sorted arrays efficiently
     * LeetCode: https://leetcode.com/problems/median-of-two-sorted-arrays/
     * 
     * Approach: Binary search on smaller array
     * - Divide: Partition both arrays to create equal-sized left and right parts
     * - Conquer: Adjust partition using binary search
     * - Combine: Calculate median from partition elements
     * 
     * Time: O(log(min(m,n))), Space: O(1)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        
        while (left <= right) {
            // Partition nums1 and nums2
            int partition1 = (left + right) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;
            
            // Elements on left and right of partition
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];
            
            // Check if partition is correct
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Found correct partition
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                // Move partition1 to left
                right = partition1 - 1;
            } else {
                // Move partition1 to right
                left = partition1 + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
    
    /**
     * 🔴 HARD: Kth Largest Element
     * 
     * Problem: Find kth largest element in unsorted array
     * 
     * Approach: QuickSelect (modified QuickSort)
     * - Divide: Partition array around pivot
     * - Conquer: Recursively search only the relevant partition
     * - Combine: No explicit combine needed
     * 
     * Time: O(n) average, O(n²) worst case, Space: O(log n)
     */
    public static int findKthLargest(int[] nums, int k) {
        // Convert to 0-indexed (kth largest = (n-k)th smallest)
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private static int quickSelect(int[] nums, int left, int right, int k) {
        // Base case: single element
        if (left == right) {
            return nums[left];
        }
        
        // Divide: partition around pivot
        int pivotIndex = partition(nums, left, right);
        
        // Conquer: search appropriate partition
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("⚡ DIVIDE & CONQUER PATTERN - PRACTICE PROBLEMS");
        System.out.println("===============================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY DIVIDE & CONQUER PROBLEMS:");
        
        // Binary Search
        int[] sortedArr = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Binary Search for 7 in " + Arrays.toString(sortedArr) + ": " + binarySearch(sortedArr, 7));
        System.out.println("Binary Search for 6 in " + Arrays.toString(sortedArr) + ": " + binarySearch(sortedArr, 6));
        
        // Find Maximum
        int[] maxArr = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        System.out.println("Maximum in " + Arrays.toString(maxArr) + ": " + findMaximum(maxArr));
        
        // Power calculation
        System.out.println("2^10 = " + myPow(2, 10));
        System.out.println("3^4 = " + myPow(3, 4));
        System.out.println("2^(-3) = " + myPow(2, -3));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM DIVIDE & CONQUER PROBLEMS:");
        
        // Merge Sort
        int[] unsortedArr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(unsortedArr1));
        int[] mergeSorted = mergeSort(unsortedArr1);
        System.out.println("Merge Sort: " + Arrays.toString(mergeSorted));
        
        // Quick Sort
        int[] unsortedArr2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(unsortedArr2));
        int[] quickSorted = quickSort(unsortedArr2);
        System.out.println("Quick Sort: " + Arrays.toString(quickSorted));
        
        // Maximum Subarray
        int[] subarrayArr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum in " + Arrays.toString(subarrayArr) + ": " + maxSubArray(subarrayArr));
        
        // Inversion Count
        int[] inversionArr = {2, 3, 8, 6, 1};
        System.out.println("Inversion count in " + Arrays.toString(inversionArr) + ": " + inversionCount(inversionArr));
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD DIVIDE & CONQUER PROBLEMS:");
        
        // Closest Pair of Points
        Point[] points = {
            new Point(2, 3), new Point(12, 30), new Point(40, 50),
            new Point(5, 1), new Point(12, 10), new Point(3, 4)
        };
        System.out.println("Closest pair distance: " + String.format("%.2f", closestPairDistance(points)));
        
        // Median of Two Sorted Arrays
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median of " + Arrays.toString(nums1) + " and " + Arrays.toString(nums2) + ": " + findMedianSortedArrays(nums1, nums2));
        
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median of " + Arrays.toString(nums3) + " and " + Arrays.toString(nums4) + ": " + findMedianSortedArrays(nums3, nums4));
        
        // Kth Largest Element
        int[] kthArr = {3, 2, 1, 5, 6, 4};
        System.out.println("2nd largest in " + Arrays.toString(kthArr) + ": " + findKthLargest(kthArr, 2));
        
        // Performance comparison
        System.out.println("\n⚡ PERFORMANCE COMPARISON:");
        int[] largeArr = new int[10000];
        Random random = new Random(42);
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = random.nextInt(10000);
        }
        
        long start = System.currentTimeMillis();
        mergeSort(largeArr.clone());
        long mergeTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        quickSort(largeArr.clone());
        long quickTime = System.currentTimeMillis() - start;
        
        System.out.println("Merge Sort (10K elements): " + mergeTime + "ms");
        System.out.println("Quick Sort (10K elements): " + quickTime + "ms");
        
        System.out.println("\n✅ Key Divide & Conquer Principles:");
        System.out.println("1. Divide problem into similar smaller subproblems");
        System.out.println("2. Solve subproblems recursively");
        System.out.println("3. Combine solutions efficiently");
        System.out.println("4. Ensure balanced divisions for optimal performance");
        System.out.println("5. Master Theorem: T(n) = a*T(n/b) + f(n)");
        System.out.println("6. Common patterns: Binary Search O(log n), Merge Sort O(n log n)");
    }
} 