package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 🪟 SLIDING WINDOW & MONOTONIC QUEUE PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of sliding window and monotonic queue
 * algorithms for maintaining window statistics, finding extrema efficiently, and processing
 * data streams. These algorithms demonstrate how to maintain sliding windows with optimal
 * time complexity, use monotonic properties for efficient queries, and handle continuous
 * data processing with bounded memory usage.
 * 
 * Pattern Focus: Window maintenance, monotonic ordering, stream processing, bounded memory
 * Time Complexity: Often amortized O(1) per element, O(n) for complete processing
 * Space Complexity: O(k) for window size k, or O(n) for auxiliary structures
 */
public class SlidingWindowMonotonicQueue {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Sliding Window & Monotonic Queue Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Moving Average from Data Stream
     * 
     * Problem: Calculate moving average from data stream with fixed window size
     * LeetCode: https://leetcode.com/problems/moving-average-from-data-stream/
     * 
     * Approach: Circular buffer for efficient window storage
     * - Circular buffer: maintains window elements with O(1) operations
     * - Running sum: tracks total for O(1) average calculation
     * - Overflow handling: replace oldest element when buffer full
     * 
     * Time: O(1) per operation, Space: O(k) for window size k
     */
    public static class MovingAverage {
        private int[] buffer;
        private int head, count, sum, windowSize;
        
        public MovingAverage(int size) {
            windowSize = size;
            buffer = new int[size];
            head = 0;
            count = 0;
            sum = 0;
        }
        
        public double next(int val) {
            if (count < windowSize) {
                // Buffer not full yet
                buffer[count] = val;
                sum += val;
                count++;
            } else {
                // Buffer full, replace oldest element
                sum = sum - buffer[head] + val;
                buffer[head] = val;
                head = (head + 1) % windowSize;
            }
            
            return (double) sum / count;
        }
    }
    
    /**
     * 🟢 EASY: Design Circular Deque
     * 
     * Problem: Design circular double-ended queue with fixed capacity
     * LeetCode: https://leetcode.com/problems/design-circular-deque/
     * 
     * Approach: Circular buffer with front/rear pointers
     * - Front pointer: tracks first element position
     * - Rear pointer: tracks position after last element
     * - Circular arithmetic: handle wraparound with modulo
     * - Size tracking: maintain current element count
     * 
     * Time: O(1) for all operations, Space: O(k) for capacity k
     */
    public static class MyCircularDeque {
        private int[] buffer;
        private int front, rear, size, capacity;
        
        public MyCircularDeque(int k) {
            capacity = k;
            buffer = new int[k];
            front = 0;
            rear = 0;
            size = 0;
        }
        
        public boolean insertFront(int value) {
            if (isFull()) return false;
            
            front = (front - 1 + capacity) % capacity;
            buffer[front] = value;
            size++;
            return true;
        }
        
        public boolean insertLast(int value) {
            if (isFull()) return false;
            
            buffer[rear] = value;
            rear = (rear + 1) % capacity;
            size++;
            return true;
        }
        
        public boolean deleteFront() {
            if (isEmpty()) return false;
            
            front = (front + 1) % capacity;
            size--;
            return true;
        }
        
        public boolean deleteLast() {
            if (isEmpty()) return false;
            
            rear = (rear - 1 + capacity) % capacity;
            size--;
            return true;
        }
        
        public int getFront() {
            return isEmpty() ? -1 : buffer[front];
        }
        
        public int getRear() {
            return isEmpty() ? -1 : buffer[(rear - 1 + capacity) % capacity];
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isFull() {
            return size == capacity;
        }
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Sliding Window & Monotonic Queue Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Sliding Window Maximum
     * 
     * Problem: Find maximum in each sliding window of size k
     * LeetCode: https://leetcode.com/problems/sliding-window-maximum/
     * 
     * Approach: Monotonic deque for efficient maximum tracking
     * - Deque stores indices in decreasing order of values
     * - Front always contains index of maximum element in current window
     * - Remove indices outside window and smaller values to maintain monotonic property
     * 
     * Time: O(n) amortized, Space: O(k)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Remove elements outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove elements smaller than current (maintain monotonic decreasing)
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            
            // Add current element index
            deque.offerLast(i);
            
            // Record result when window is complete
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * 🟡 MEDIUM: First Unique Number
     * 
     * Problem: Find first unique number in a data stream
     * LeetCode: https://leetcode.com/problems/first-unique-number/
     * 
     * Approach: Queue + HashMap for unique tracking
     * - Queue: maintains order of potential unique elements
     * - HashMap: tracks frequency of each element
     * - Lazy removal: clean up non-unique elements when queried
     * 
     * Time: O(1) amortized for add, O(n) worst case for showFirstUnique, Space: O(n)
     */
    public static class FirstUnique {
        private Queue<Integer> queue;
        private Map<Integer, Integer> frequency;
        
        public FirstUnique(int[] nums) {
            queue = new LinkedList<>();
            frequency = new HashMap<>();
            
            // Initialize with given numbers
            for (int num : nums) {
                add(num);
            }
        }
        
        public int showFirstUnique() {
            // Remove non-unique elements from front
            while (!queue.isEmpty() && frequency.get(queue.peek()) > 1) {
                queue.poll();
            }
            
            return queue.isEmpty() ? -1 : queue.peek();
        }
        
        public void add(int value) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
            
            // Add to queue only if first occurrence
            if (frequency.get(value) == 1) {
                queue.offer(value);
            }
        }
    }
    
    /**
     * 🟡 MEDIUM: Sliding Window Minimum
     * 
     * Problem: Find minimum in each sliding window of size k
     * 
     * Approach: Monotonic deque for efficient minimum tracking
     * - Similar to maximum but maintain increasing order
     * - Front contains index of minimum element in current window
     * - Remove indices outside window and larger values
     * 
     * Time: O(n) amortized, Space: O(k)
     */
    public static int[] minSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Remove elements outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove elements larger than current (maintain monotonic increasing)
            while (!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]) {
                deque.pollLast();
            }
            
            // Add current element index
            deque.offerLast(i);
            
            // Record result when window is complete
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Sliding Window & Monotonic Queue Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Max Sum of Rectangle No Larger Than K
     * 
     * Problem: Find maximum sum of rectangle in matrix with sum <= k
     * LeetCode: https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
     * 
     * Approach: 2D to 1D reduction + sliding window with constraints
     * - For each row range: reduce to 1D subarray problem
     * - Use prefix sums with TreeSet for efficient range queries
     * - Find maximum subarray sum <= k using sliding window
     * 
     * Time: O(rows^2 * cols * log(cols)), Space: O(cols)
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int rows = matrix.length, cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        
        // Try all possible row ranges
        for (int top = 0; top < rows; top++) {
            int[] colSums = new int[cols];
            
            for (int bottom = top; bottom < rows; bottom++) {
                // Add current row to column sums
                for (int col = 0; col < cols; col++) {
                    colSums[col] += matrix[bottom][col];
                }
                
                // Find max subarray sum <= k in 1D array
                int currentMax = maxSubarraySum(colSums, k);
                maxSum = Math.max(maxSum, currentMax);
                
                // Early termination if exact match
                if (maxSum == k) return k;
            }
        }
        
        return maxSum;
    }
    
    private static int maxSubarraySum(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        
        // Use prefix sum with TreeSet for efficient range queries
        TreeSet<Integer> prefixSums = new TreeSet<>();
        prefixSums.add(0);
        int currentSum = 0;
        
        for (int num : arr) {
            currentSum += num;
            
            // Find smallest prefix sum >= (currentSum - k)
            Integer ceiling = prefixSums.ceiling(currentSum - k);
            if (ceiling != null) {
                maxSum = Math.max(maxSum, currentSum - ceiling);
            }
            
            prefixSums.add(currentSum);
        }
        
        return maxSum;
    }
    
    /**
     * 🔴 HARD: Sliding Window Median
     * 
     * Problem: Find median in each sliding window of size k
     * 
     * Approach: Two heaps for efficient median tracking
     * - Max heap: stores smaller half of elements
     * - Min heap: stores larger half of elements
     * - Balance heaps to maintain median at heap tops
     * - Remove elements outside window using lazy deletion
     * 
     * Time: O(n log k), Space: O(k)
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Map<Integer, Integer> toRemove = new HashMap<>();
        
        double[] result = new double[nums.length - k + 1];
        int resultIndex = 0;
        
        // Initialize first window
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        
        // Move half elements to minHeap
        for (int i = 0; i < k / 2; i++) {
            minHeap.offer(maxHeap.poll());
        }
        
        for (int i = k; i <= nums.length; i++) {
            // Calculate median
            if (k % 2 == 1) {
                result[resultIndex++] = maxHeap.peek();
            } else {
                result[resultIndex++] = ((long) maxHeap.peek() + minHeap.peek()) * 0.5;
            }
            
            if (i == nums.length) break;
            
            // Add new element and remove old element
            int toAdd = nums[i];
            int toDelete = nums[i - k];
            
            // Track element to remove
            toRemove.put(toDelete, toRemove.getOrDefault(toDelete, 0) + 1);
            
            // Add new element
            if (toAdd <= maxHeap.peek()) {
                maxHeap.offer(toAdd);
            } else {
                minHeap.offer(toAdd);
            }
            
            // Balance heaps
            rebalanceHeaps(maxHeap, minHeap, toRemove, k);
        }
        
        return result;
    }
    
    private static void rebalanceHeaps(PriorityQueue<Integer> maxHeap, 
                                     PriorityQueue<Integer> minHeap, 
                                     Map<Integer, Integer> toRemove, int k) {
        // Remove lazy-deleted elements from tops
        while (!maxHeap.isEmpty() && toRemove.getOrDefault(maxHeap.peek(), 0) > 0) {
            int val = maxHeap.poll();
            toRemove.put(val, toRemove.get(val) - 1);
        }
        
        while (!minHeap.isEmpty() && toRemove.getOrDefault(minHeap.peek(), 0) > 0) {
            int val = minHeap.poll();
            toRemove.put(val, toRemove.get(val) - 1);
        }
        
        // Balance heap sizes
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    /**
     * 🔴 HARD: Constrained Subsequence Sum
     * 
     * Problem: Find maximum sum of subsequence with constraint on gap between elements
     * 
     * Approach: Dynamic programming with monotonic deque optimization
     * - DP state: dp[i] = maximum sum ending at position i
     * - Constraint: elements must be within k positions of each other
     * - Monotonic deque: maintain maximum DP values in sliding window
     * 
     * Time: O(n), Space: O(n)
     */
    public static int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices
        
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            // Remove elements outside window
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            
            // Calculate DP value
            dp[i] = nums[i];
            if (!deque.isEmpty()) {
                dp[i] = Math.max(dp[i], nums[i] + dp[deque.peekFirst()]);
            }
            
            maxSum = Math.max(maxSum, dp[i]);
            
            // Maintain monotonic decreasing deque
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            
            // Add current index if DP value is positive
            if (dp[i] > 0) {
                deque.offerLast(i);
            }
        }
        
        return maxSum;
    }
    
    /**
     * 🔴 HARD: Shortest Subarray with Sum at Least K
     * 
     * Problem: Find length of shortest subarray with sum >= k
     * 
     * Approach: Prefix sum with monotonic deque
     * - Prefix sum: convert to range sum problem
     * - Monotonic deque: maintain increasing prefix sums
     * - Sliding window: find shortest valid subarray
     * 
     * Time: O(n), Space: O(n)
     */
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        
        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices
        int minLength = Integer.MAX_VALUE;
        
        for (int i = 0; i <= n; i++) {
            // Check if current prefix sum can form valid subarray
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            
            // Maintain monotonic increasing deque
            while (!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
        }
        
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
    
    /**
     * 🔴 HARD: Maximum Performance of a Team
     * 
     * Problem: Select team members to maximize performance with size constraint
     * 
     * Approach: Sort by efficiency + sliding window on speed
     * - Sort engineers by efficiency in descending order
     * - Use min heap to maintain k largest speeds
     * - Calculate performance for each efficiency threshold
     * 
     * Time: O(n log n), Space: O(k)
     */
    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[]{efficiency[i], speed[i]};
        }
        
        // Sort by efficiency in descending order
        Arrays.sort(engineers, (a, b) -> b[0] - a[0]);
        
        PriorityQueue<Integer> speedHeap = new PriorityQueue<>(); // Min heap for speeds
        long maxPerformance = 0;
        long speedSum = 0;
        final int MOD = 1_000_000_007;
        
        for (int[] engineer : engineers) {
            int eff = engineer[0];
            int spd = engineer[1];
            
            // Add current engineer
            speedHeap.offer(spd);
            speedSum += spd;
            
            // Remove slowest engineer if team size exceeds k
            if (speedHeap.size() > k) {
                speedSum -= speedHeap.poll();
            }
            
            // Calculate performance with current efficiency as minimum
            maxPerformance = Math.max(maxPerformance, speedSum * eff);
        }
        
        return (int) (maxPerformance % MOD);
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🪟 SLIDING WINDOW & MONOTONIC QUEUE PATTERN - PRACTICE PROBLEMS");
        System.out.println("================================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY SLIDING WINDOW & MONOTONIC QUEUE PROBLEMS:");
        
        // Moving Average
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println("Moving Average next(1): " + movingAverage.next(1)); // 1.0
        System.out.println("Moving Average next(10): " + movingAverage.next(10)); // 5.5
        System.out.println("Moving Average next(3): " + movingAverage.next(3)); // 4.67
        System.out.println("Moving Average next(5): " + movingAverage.next(5)); // 6.0
        
        // Circular Deque
        MyCircularDeque deque = new MyCircularDeque(3);
        System.out.println("Circular Deque insertLast(1): " + deque.insertLast(1)); // true
        System.out.println("Circular Deque insertLast(2): " + deque.insertLast(2)); // true
        System.out.println("Circular Deque insertFront(3): " + deque.insertFront(3)); // true
        System.out.println("Circular Deque insertFront(4): " + deque.insertFront(4)); // false
        System.out.println("Circular Deque getRear(): " + deque.getRear()); // 2
        System.out.println("Circular Deque isFull(): " + deque.isFull()); // true
        System.out.println("Circular Deque deleteLast(): " + deque.deleteLast()); // true
        System.out.println("Circular Deque insertFront(4): " + deque.insertFront(4)); // true
        System.out.println("Circular Deque getFront(): " + deque.getFront()); // 4
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM SLIDING WINDOW & MONOTONIC QUEUE PROBLEMS:");
        
        // Sliding Window Maximum
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] maxWindow = maxSlidingWindow(nums, k);
        System.out.println("Sliding Window Maximum: " + Arrays.toString(maxWindow)); // [3, 3, 5, 5, 6, 7]
        
        // Sliding Window Minimum
        int[] minWindow = minSlidingWindow(nums, k);
        System.out.println("Sliding Window Minimum: " + Arrays.toString(minWindow)); // [-1, -3, -3, -3, 3, 3]
        
        // First Unique Number
        FirstUnique firstUnique = new FirstUnique(new int[]{2, 3, 5});
        System.out.println("First Unique showFirstUnique(): " + firstUnique.showFirstUnique()); // 2
        firstUnique.add(5);
        System.out.println("First Unique after add(5): " + firstUnique.showFirstUnique()); // 2
        firstUnique.add(2);
        System.out.println("First Unique after add(2): " + firstUnique.showFirstUnique()); // 3
        firstUnique.add(3);
        System.out.println("First Unique after add(3): " + firstUnique.showFirstUnique()); // -1
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD SLIDING WINDOW & MONOTONIC QUEUE PROBLEMS:");
        
        // Max Sum Rectangle
        int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
        int maxSum = maxSumSubmatrix(matrix, 2);
        System.out.println("Max Sum Rectangle (<= 2): " + maxSum); // 2
        
        int[][] matrix2 = {{2, 2, -1}};
        int maxSum2 = maxSumSubmatrix(matrix2, 3);
        System.out.println("Max Sum Rectangle (<= 3): " + maxSum2); // 3
        
        // Sliding Window Median
        int[] nums2 = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] medians = medianSlidingWindow(nums2, 3);
        System.out.println("Sliding Window Median: " + Arrays.toString(medians)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
        
        // Constrained Subsequence Sum
        int[] nums3 = {10, 2, -10, 5, 20};
        int constrainedSum = constrainedSubsetSum(nums3, 2);
        System.out.println("Constrained Subsequence Sum (k=2): " + constrainedSum); // 37
        
        // Shortest Subarray with Sum at Least K
        int[] nums4 = {2, -1, 2};
        int shortestLen = shortestSubarray(nums4, 3);
        System.out.println("Shortest Subarray with Sum >= 3: " + shortestLen); // 3
        
        // Maximum Performance
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        int maxPerf = maxPerformance(6, speed, efficiency, 2);
        System.out.println("Maximum Performance (k=2): " + maxPerf); // 60
        
        System.out.println("\n✅ Key Sliding Window & Monotonic Queue Principles:");
        System.out.println("1. Use monotonic deques for efficient min/max queries in windows");
        System.out.println("2. Maintain window boundaries with proper element removal");
        System.out.println("3. Store indices in deques for position tracking and validation");
        System.out.println("4. Ensure amortized O(1) operations per element");
        System.out.println("5. Use circular buffers for simple sliding window storage");
        System.out.println("6. Combine with other techniques (heap, TreeSet) for complex queries");
        System.out.println("7. Handle streaming data with bounded memory usage");
        System.out.println("8. Apply lazy deletion for efficient element removal");
    }
} 