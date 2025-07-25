package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 FINDING KTH LARGEST/SMALLEST PATTERN - SPECIALIZED READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS FINDING KTH LARGEST/SMALLEST PATTERN?
 * ============================================================================
 * 
 * Finding Kth Largest/Smallest Pattern is a specialized subset of priority queue
 * algorithms focused on efficiently selecting the Kth ranked element from datasets.
 * This pattern is essential for order statistics, percentile calculations, median
 * finding, and top-k selection problems where you need specific ranked elements
 * rather than complete sorting.
 * 
 * The pattern leverages heap-based selection, quickselect partitioning, binary
 * search optimization, and streaming algorithms to achieve optimal performance
 * for Kth element queries across various data structures and constraints.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. SELECTIVE ORDERING: Find specific ranked elements without complete sorting
 * 2. HEAP OPTIMIZATION: Use fixed-size heaps for memory-efficient selection
 * 3. PARTITION STRATEGIES: Apply quickselect for average O(n) performance
 * 4. STREAMING EFFICIENCY: Maintain Kth elements in dynamic data streams
 * 
 * 🎯 KTH ELEMENT METAPHOR:
 * Think of Kth element finding as "talent scouting":
 * - Talent scout: doesn't need to rank everyone, just find top K performers
 * - Audition process: eliminate clearly unqualified candidates early
 * - Final selection: maintain only the K best candidates seen so far
 * - Streaming auditions: continuously update rankings as new candidates arrive
 * 
 * ============================================================================
 * 🎯 WHEN TO USE KTH LARGEST/SMALLEST PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding specific ranked elements (Kth largest, smallest, median)
 * - Top-K selection from large datasets with memory constraints
 * - Streaming data where you need to maintain running Kth element
 * - Percentile calculations and order statistics
 * - Matrix searching with sorted properties
 * - Distance-based selection (K closest points, nearest neighbors)
 * - Performance optimization where full sorting is overkill
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find the Kth largest/smallest element"
 * - "Top K elements" or "Bottom K elements"
 * - "Median of stream" or "Running median"
 * - "K closest points/neighbors"
 * - "Percentile calculation"
 * - "Order statistics"
 * - "Select without sorting"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need all elements in sorted order (use full sorting)
 * - K is very close to N (sorting might be simpler)
 * - Single query on static data (quickselect might be better)
 * - Memory is unlimited (can afford full sorting)
 * 
 * ============================================================================
 * 🔄 KTH ELEMENT PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ HEAP-BASED KTH SELECTION
 * - Min-heap for Kth largest elements
 * - Max-heap for Kth smallest elements
 * - Fixed-size heap maintenance
 * - Optimal for memory-constrained scenarios
 * 
 * 2️⃣ QUICKSELECT PARTITIONING
 * - Divide-and-conquer approach
 * - Average O(n) time complexity
 * - In-place partitioning
 * - Best for single queries on arrays
 * 
 * 3️⃣ STREAMING KTH ELEMENT
 * - Dynamic maintenance of Kth element
 * - Efficient updates for data streams
 * - Dual-heap strategy for median
 * - Online algorithm optimization
 * 
 * 4️⃣ MATRIX KTH ELEMENT
 * - Binary search on sorted matrices
 * - Row/column sorted properties
 * - Multi-dimensional selection
 * - Logarithmic optimization techniques
 * 
 * 5️⃣ DISTANCE-BASED KTH SELECTION
 * - K closest points to origin
 * - Custom distance metrics
 * - Geometric optimization
 * - Space partitioning strategies
 * 
 * 6️⃣ MULTI-CRITERIA KTH SELECTION
 * - Complex comparison criteria
 * - Custom comparators
 * - Multi-dimensional ranking
 * - Priority-based selection
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 HEAP-BASED KTH LARGEST ALGORITHM:
 * ```
 * Use min-heap of size K:
 *   1. For each element in dataset:
 *      - If heap size < K: add element
 *      - Else if element > heap.top(): replace top
 *   2. Return heap.top() as Kth largest
 * 
 * Time: O(n log k), Space: O(k)
 * ```
 * 
 * 🔹 QUICKSELECT ALGORITHM:
 * ```
 * Quickselect for Kth smallest:
 *   1. Choose pivot and partition array
 *   2. If pivot position == k-1: return pivot
 *   3. If pivot position > k-1: recurse left
 *   4. If pivot position < k-1: recurse right
 * 
 * Time: O(n) average, O(n²) worst, Space: O(1)
 * ```
 * 
 * 🔹 STREAMING MEDIAN ALGORITHM:
 * ```
 * Two-heap approach:
 *   maxHeap (left half) + minHeap (right half)
 *   Maintain: |maxHeap| - |minHeap| ≤ 1
 *   Median: top of larger heap or average of tops
 * 
 * Time: O(log n) per insertion, Space: O(n)
 * ```
 * 
 * 🔹 MATRIX KTH SMALLEST ALGORITHM:
 * ```
 * Binary search on value range:
 *   1. Find min and max values in matrix
 *   2. Binary search on value range [min, max]
 *   3. For each mid value, count elements ≤ mid
 *   4. Adjust range based on count vs k
 * 
 * Time: O(n log(max-min)), Space: O(1)
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: ANALYZE DATA CHARACTERISTICS
 * - Static array vs streaming data?
 * - Memory constraints vs time optimization?
 * - Single query vs multiple queries?
 * 
 * STEP 2: CHOOSE OPTIMAL STRATEGY
 * - Heap-based: memory-efficient, good for multiple queries
 * - Quickselect: time-efficient for single query on arrays
 * - Binary search: optimal for sorted structures
 * 
 * STEP 3: IMPLEMENT CORE ALGORITHM
 * - Handle edge cases (K > N, empty data, duplicates)
 * - Optimize for specific data characteristics
 * - Consider stability requirements
 * 
 * STEP 4: OPTIMIZE FOR CONSTRAINTS
 * - Memory optimization: fixed-size heaps
 * - Time optimization: quickselect with good pivots
 * - Streaming optimization: incremental updates
 * 
 * STEP 5: VALIDATE AND TEST
 * - Test with various K values and data sizes
 * - Verify correctness with edge cases
 * - Benchmark performance characteristics
 * 
 * ============================================================================
 * 🎨 KTH ELEMENT ALGORITHM TEMPLATES
 * ============================================================================
 */

public class KthElementReadingGuide {
    
    /**
     * 🏆 HEAP-BASED KTH SELECTION TEMPLATES
     */
    public static class HeapBasedSelection {
        
        /**
         * Find Kth Largest Element using Min-Heap
         * Strategy: Maintain min-heap of size K containing K largest elements
         */
        public static int findKthLargest(int[] nums, int k) {
            // Min-heap to store K largest elements
            java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
            
            for (int num : nums) {
                if (minHeap.size() < k) {
                    minHeap.offer(num);
                } else if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            
            return minHeap.peek(); // Kth largest element
        }
        
        /**
         * Find Kth Smallest Element using Max-Heap
         * Strategy: Maintain max-heap of size K containing K smallest elements
         */
        public static int findKthSmallest(int[] nums, int k) {
            // Max-heap to store K smallest elements
            java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
            
            for (int num : nums) {
                if (maxHeap.size() < k) {
                    maxHeap.offer(num);
                } else if (num < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            }
            
            return maxHeap.peek(); // Kth smallest element
        }
        
        /**
         * K Closest Points to Origin
         * Strategy: Use max-heap based on distance, maintain K closest points
         */
        public static int[][] kClosest(int[][] points, int k) {
            // Max-heap based on distance from origin
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
         * Kth Largest in Stream
         * Strategy: Maintain min-heap of size K for dynamic stream
         */
        public static class KthLargestStream {
            private java.util.PriorityQueue<Integer> minHeap;
            private int k;
            
            public KthLargestStream(int k, int[] nums) {
                this.k = k;
                this.minHeap = new java.util.PriorityQueue<>();
                
                // Initialize with existing numbers
                for (int num : nums) {
                    add(num);
                }
            }
            
            public int add(int val) {
                minHeap.offer(val);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
                return minHeap.peek();
            }
        }
    }
    
    /**
     * ⚡ QUICKSELECT ALGORITHM TEMPLATES
     */
    public static class QuickSelectAlgorithms {
        
        /**
         * Quickselect for Kth Largest Element
         * Strategy: Partition-based selection with average O(n) time
         */
        public static int quickSelectKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }
        
        /**
         * Quickselect for Kth Smallest Element
         * Strategy: Find element at position k-1 in sorted order
         */
        public static int quickSelectKthSmallest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, k - 1);
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
    }
    
    /**
     * 📊 STREAMING KTH ELEMENT TEMPLATES
     */
    public static class StreamingAlgorithms {
        
        /**
         * Find Median from Data Stream
         * Strategy: Two heaps to maintain balanced partitions
         */
        public static class MedianFinder {
            private java.util.PriorityQueue<Integer> maxHeap; // Left half (smaller values)
            private java.util.PriorityQueue<Integer> minHeap; // Right half (larger values)
            
            public MedianFinder() {
                maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
                minHeap = new java.util.PriorityQueue<>();
            }
            
            public void addNum(int num) {
                // Add to maxHeap first
                maxHeap.offer(num);
                
                // Balance: move largest from maxHeap to minHeap
                minHeap.offer(maxHeap.poll());
                
                // Ensure maxHeap has equal or one more element
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
    }
    
    /**
     * 🔍 MATRIX KTH ELEMENT TEMPLATES
     */
    public static class MatrixAlgorithms {
        
        /**
         * Kth Smallest Element in Sorted Matrix
         * Strategy: Binary search on value range
         */
        public static int kthSmallestInMatrix(int[][] matrix, int k) {
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
            int row = n - 1, col = 0;
            
            while (row >= 0 && col < n) {
                if (matrix[row][col] <= target) {
                    count += row + 1;
                    col++;
                } else {
                    row--;
                }
            }
            
            return count;
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Wrong Heap Type
     * Use min-heap for k largest, max-heap for k smallest
     */
    public static void heapTypeExample() {
        // Correct for k largest: min-heap of size k
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        
        // Incorrect: max-heap for k largest (inefficient)
        // java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
    }
    
    /**
     * ❌ PITFALL 2: Integer Overflow in Distance Calculations
     * Use long for distance calculations to avoid overflow
     */
    public static void overflowExample() {
        // Correct: use long for distance
        int x = 1000, y = 1000;
        long distance = (long)x * x + (long)y * y;
        
        // Incorrect: potential overflow
        // int distance = x * x + y * y;
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Choose Right Algorithm for Data Type
     * - Arrays: Quickselect for single query, heap for multiple queries
     * - Streams: Heap-based for dynamic updates
     * - Matrices: Binary search for sorted properties
     */
    
    /**
     * 🎯 STRATEGY 2: Memory vs Time Trade-offs
     * - Heap: O(k) space, O(n log k) time
     * - Quickselect: O(1) space, O(n) average time
     * - Full sort: O(n) space, O(n log n) time
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * DATA TYPE:
     * ✅ Array → Quickselect (single query) or Heap (multiple queries)
     * ✅ Stream → Heap-based streaming algorithms
     * ✅ Matrix → Binary search or heap with coordinates
     * ✅ Points → Distance-based heap selection
     * 
     * CONSTRAINTS:
     * ✅ Memory limited → Fixed-size heap
     * ✅ Time critical → Quickselect for arrays
     * ✅ Multiple queries → Preprocessing with heaps
     * ✅ Online processing → Streaming algorithms
     */
    
    public static void main(String[] args) {
        System.out.println("🎯 FINDING KTH LARGEST/SMALLEST PATTERN - READING GUIDE");
        System.out.println("========================================================");
        
        // Test examples
        int[] testArray = {3, 2, 1, 5, 6, 4};
        
        // Heap-based examples
        int kthLargestHeap = HeapBasedSelection.findKthLargest(testArray, 2);
        System.out.println("2nd largest (heap): " + kthLargestHeap); // 5
        
        int kthSmallestHeap = HeapBasedSelection.findKthSmallest(testArray, 2);
        System.out.println("2nd smallest (heap): " + kthSmallestHeap); // 2
        
        // Quickselect examples
        int[] quickselectArray = testArray.clone();
        int kthLargestQuick = QuickSelectAlgorithms.quickSelectKthLargest(quickselectArray, 2);
        System.out.println("2nd largest (quickselect): " + kthLargestQuick); // 5
        
        // Streaming median example
        StreamingAlgorithms.MedianFinder medianFinder = new StreamingAlgorithms.MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after 1,2: " + medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println("Median after adding 3: " + medianFinder.findMedian()); // 2.0
        
        // K closest points example
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int[][] closest = HeapBasedSelection.kClosest(points, 2);
        System.out.println("2 closest points: " + Arrays.deepToString(closest));
        
        System.out.println("\n✅ Key Kth Element Principles:");
        System.out.println("1. Use min-heap for Kth largest, max-heap for Kth smallest");
        System.out.println("2. Choose quickselect for single queries on arrays");
        System.out.println("3. Apply heap-based methods for streaming data");
        System.out.println("4. Leverage binary search for sorted matrix structures");
        System.out.println("5. Use dual-heap strategy for dynamic median finding");
        System.out.println("6. Handle integer overflow in distance calculations");
        System.out.println("7. Optimize algorithm choice based on data characteristics");
    }
} 