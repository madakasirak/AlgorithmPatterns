package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 FINDING KTH LARGEST/SMALLEST PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains specialized implementations of Kth element finding algorithms
 * for efficiently selecting ranked elements from datasets, streaming data, matrices,
 * and geometric points. These algorithms demonstrate heap-based selection, quickselect
 * optimization, binary search techniques, and streaming algorithms for optimal Kth
 * element queries across various data structures and computational constraints.
 * 
 * Pattern Focus: Kth element selection, order statistics, heap optimization, streaming algorithms
 * Time Complexity: O(n log k) for heap-based, O(n) average for quickselect
 * Space Complexity: O(k) for fixed-size heaps, O(1) for in-place algorithms
 */

public class KthElement {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Kth Element Finding
    // ============================================================================
    
    /**
     * 🟢 EASY: Kth Largest Element in an Array
     * 
     * Problem: Find the kth largest element in an unsorted array
     * LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/
     * 
     * Approach 1: Min-Heap of size k
     * - Maintain min-heap containing k largest elements seen so far
     * - Root of min-heap is always kth largest element
     * - When heap size exceeds k, remove smallest element
     * - More memory efficient than sorting entire array
     * 
     * Time: O(n log k), Space: O(k)
     */
    public static int findKthLargest(int[] nums, int k) {
        // Min-heap to store k largest elements
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        return minHeap.peek(); // kth largest element
    }
    
    /**
     * Alternative: Quickselect approach for Kth largest
     * - Partition-based selection with average O(n) time
     * - More time-efficient for single queries
     * - In-place algorithm with O(1) space
     * 
     * Time: O(n) average, O(n²) worst, Space: O(1)
     */
    public static int findKthLargestQuickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private static int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) return nums[left];
        
        // Choose random pivot for better average performance
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        
        // Partition around pivot
        int finalPivotIndex = partition(nums, left, right, pivotIndex);
        
        if (finalPivotIndex == targetIndex) {
            return nums[finalPivotIndex];
        } else if (finalPivotIndex > targetIndex) {
            return quickSelect(nums, left, finalPivotIndex - 1, targetIndex);
        } else {
            return quickSelect(nums, finalPivotIndex + 1, right, targetIndex);
        }
    }
    
    private static int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        
        // Move pivot to end
        swap(nums, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }
        
        // Move pivot to final position
        swap(nums, storeIndex, right);
        return storeIndex;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * 🟢 EASY: Kth Largest Element in a Stream
     * 
     * Problem: Design class to find kth largest element in a stream
     * LeetCode: https://leetcode.com/problems/kth-largest-element-in-a-stream/
     * 
     * Approach: Maintain min-heap of size k
     * - Min-heap always contains k largest elements seen
     * - Root is always kth largest element
     * - Add operation maintains heap size constraint
     * - Efficient for streaming data with memory constraint
     * 
     * Time: O(log k) per add, Space: O(k)
     */
    public static class KthLargest {
        private java.util.PriorityQueue<Integer> minHeap;
        private int k;
        
        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.minHeap = new java.util.PriorityQueue<>();
            
            // Add all initial numbers
            for (int num : nums) {
                add(num);
            }
        }
        
        public int add(int val) {
            minHeap.offer(val);
            
            // Maintain heap size k
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            
            return minHeap.peek();
        }
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Advanced Kth Element Algorithms
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Kth Smallest Element in a Sorted Matrix
     * 
     * Problem: Find kth smallest element in n x n matrix with sorted rows and columns
     * LeetCode: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
     * 
     * Approach 1: Binary Search on Value Range
     * - Matrix is sorted row-wise and column-wise
     * - Binary search on value range [min, max]
     * - For each candidate value, count elements ≤ candidate
     * - Adjust search range based on count vs k
     * 
     * Time: O(n log(max-min)), Space: O(1)
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessEqual(matrix, mid);
            
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private static int countLessEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int row = n - 1, col = 0; // Start from bottom-left
        
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1; // All elements above are smaller
                col++;
            } else {
                row--;
            }
        }
        
        return count;
    }
    
    /**
     * Alternative: Min-Heap approach for Kth smallest in matrix
     * - Use heap to track smallest elements with coordinates
     * - Start from top-left and expand to neighbors
     * - More intuitive but uses more space
     * 
     * Time: O(k log n), Space: O(n)
     */
    public static int kthSmallestHeap(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        
        // Min-heap storing [value, row, col]
        java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        
        Set<String> visited = new HashSet<>();
        
        // Start with top-left element
        minHeap.offer(new int[]{matrix[0][0], 0, 0});
        visited.add("0,0");
        
        for (int i = 0; i < k - 1; i++) {
            int[] current = minHeap.poll();
            int row = current[1];
            int col = current[2];
            
            // Add right neighbor
            if (col + 1 < n && !visited.contains(row + "," + (col + 1))) {
                minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
                visited.add(row + "," + (col + 1));
            }
            
            // Add bottom neighbor
            if (row + 1 < m && !visited.contains((row + 1) + "," + col)) {
                minHeap.offer(new int[]{matrix[row + 1][col], row + 1, col});
                visited.add((row + 1) + "," + col);
            }
        }
        
        return minHeap.peek()[0];
    }
    
    /**
     * 🟡 MEDIUM: Find Median from Data Stream
     * 
     * Problem: Find median of numbers from continuous data stream
     * LeetCode: https://leetcode.com/problems/find-median-from-data-stream/
     * 
     * Approach: Two heaps strategy
     * - Max-heap for smaller half of numbers
     * - Min-heap for larger half of numbers
     * - Balance heaps to maintain size difference ≤ 1
     * - Median is root of larger heap or average of both roots
     * 
     * Time: O(log n) add, O(1) findMedian, Space: O(n)
     */
    public static class MedianFinder {
        private java.util.PriorityQueue<Integer> maxHeap; // smaller half
        private java.util.PriorityQueue<Integer> minHeap; // larger half
        
        public MedianFinder() {
            maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
            minHeap = new java.util.PriorityQueue<>();
        }
        
        public void addNum(int num) {
            // Add to max-heap (smaller half) first
            maxHeap.offer(num);
            
            // Balance: move largest from max-heap to min-heap
            minHeap.offer(maxHeap.poll());
            
            // Ensure max-heap has equal or one more element
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }
    
    /**
     * 🟡 MEDIUM: K Closest Points to Origin
     * 
     * Problem: Find k closest points to origin (0, 0)
     * LeetCode: https://leetcode.com/problems/k-closest-points-to-origin/
     * 
     * Approach: Max-heap of size k based on distance
     * - Use max-heap to maintain k closest points
     * - Calculate squared distance to avoid floating point
     * - When heap full, replace farthest if current is closer
     * - Final heap contains k closest points
     * 
     * Time: O(n log k), Space: O(k)
     */
    public static int[][] kClosest(int[][] points, int k) {
        // Max-heap based on squared distance from origin
        java.util.PriorityQueue<int[]> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );
        
        for (int[] point : points) {
            if (maxHeap.size() < k) {
                maxHeap.offer(point);
            } else {
                int currentDist = point[0] * point[0] + point[1] * point[1];
                int[] farthest = maxHeap.peek();
                int farthestDist = farthest[0] * farthest[0] + farthest[1] * farthest[1];
                
                if (currentDist < farthestDist) {
                    maxHeap.poll();
                    maxHeap.offer(point);
                }
            }
        }
        
        return maxHeap.toArray(new int[k][]);
    }
    
    /**
     * Alternative: Quickselect approach for K closest points
     * - Partition based on distance from origin
     * - Average O(n) time but worst case O(n²)
     * - In-place algorithm with O(1) space
     * 
     * Time: O(n) average, Space: O(1)
     */
    public static int[][] kClosestQuickSelect(int[][] points, int k) {
        quickSelectPoints(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }
    
    private static void quickSelectPoints(int[][] points, int left, int right, int k) {
        if (left >= right) return;
        
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        int finalPivotIndex = partitionPoints(points, left, right, pivotIndex);
        
        if (finalPivotIndex == k - 1) {
            return; // Found exact position
        } else if (finalPivotIndex > k - 1) {
            quickSelectPoints(points, left, finalPivotIndex - 1, k);
        } else {
            quickSelectPoints(points, finalPivotIndex + 1, right, k);
        }
    }
    
    private static int partitionPoints(int[][] points, int left, int right, int pivotIndex) {
        int pivotDist = distance(points[pivotIndex]);
        swapPoints(points, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (distance(points[i]) < pivotDist) {
                swapPoints(points, i, storeIndex);
                storeIndex++;
            }
        }
        
        swapPoints(points, storeIndex, right);
        return storeIndex;
    }
    
    private static int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
    private static void swapPoints(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎯 FINDING KTH LARGEST/SMALLEST PATTERN - PRACTICE PROBLEMS");
        System.out.println("===========================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        // Test Kth Largest Element
        int[] testArray = {3, 2, 1, 5, 6, 4};
        int kthLargest = findKthLargest(testArray, 2);
        System.out.println("2nd largest element (heap): " + kthLargest); // 5
        
        int kthLargestQuick = findKthLargestQuickSelect(testArray.clone(), 2);
        System.out.println("2nd largest element (quickselect): " + kthLargestQuick); // 5
        
        // Test Kth Largest in Stream
        KthLargest kthLargestStream = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println("3rd largest after adding 3: " + kthLargestStream.add(3)); // 4
        System.out.println("3rd largest after adding 5: " + kthLargestStream.add(5)); // 5
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        // Test Kth Smallest in Matrix
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int kthSmallestMatrix = kthSmallest(matrix, 8);
        System.out.println("8th smallest in matrix: " + kthSmallestMatrix); // 13
        
        int kthSmallestMatrixHeap = kthSmallestHeap(matrix, 8);
        System.out.println("8th smallest in matrix (heap): " + kthSmallestMatrixHeap); // 13
        
        // Test Median Finder
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after 1, 2: " + medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println("Median after adding 3: " + medianFinder.findMedian()); // 2.0
        
        // Test K Closest Points
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] closest = kClosest(points, 2);
        System.out.println("2 closest points to origin:");
        for (int[] point : closest) {
            System.out.println("  [" + point[0] + ", " + point[1] + "]");
        }
        
        int[][] closestQuick = kClosestQuickSelect(points.clone(), 2);
        System.out.println("2 closest points (quickselect):");
        for (int[] point : closestQuick) {
            System.out.println("  [" + point[0] + ", " + point[1] + "]");
        }
        
        System.out.println("\n✅ Key Kth Element Finding Principles:");
        System.out.println("1. Use min-heap for Kth largest, max-heap for Kth smallest");
        System.out.println("2. Apply quickselect for single queries on arrays (average O(n))");
        System.out.println("3. Leverage binary search for sorted matrix structures");
        System.out.println("4. Use dual-heap strategy for dynamic median finding");
        System.out.println("5. Handle integer overflow in distance calculations with long");
        System.out.println("6. Choose algorithm based on data type and query pattern");
        System.out.println("7. Optimize for memory constraints with fixed-size heaps");
        System.out.println("8. Consider streaming vs batch processing requirements");
    }
} 