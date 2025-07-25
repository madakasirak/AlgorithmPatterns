package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 🪟 SLIDING WINDOW & MONOTONIC QUEUE PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS SLIDING WINDOW & MONOTONIC QUEUE PATTERN?
 * ============================================================================
 * 
 * The Sliding Window & Monotonic Queue Pattern combines two powerful techniques:
 * 1. SLIDING WINDOW: Maintain a fixed-size window that moves through data
 * 2. MONOTONIC QUEUE: Maintain elements in monotonic order for efficient min/max queries
 * 
 * This pattern is essential for data stream processing, window-based statistics,
 * and problems requiring efficient maximum/minimum queries over moving windows.
 * The monotonic property ensures O(1) access to extrema while the sliding window
 * provides efficient processing of sequential data segments.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. WINDOW MAINTENANCE: Efficiently track window boundaries and contents
 * 2. MONOTONIC PROPERTY: Maintain sorted order for optimal extrema access
 * 3. AMORTIZED EFFICIENCY: Each element enters and exits structures exactly once
 * 4. STREAM PROCESSING: Handle continuous data with bounded memory usage
 * 
 * 🪟 SLIDING WINDOW & MONOTONIC QUEUE METAPHOR:
 * Think of it as a "smart viewing window":
 * - Sliding window: the viewfinder that moves across data
 * - Monotonic queue: the intelligent memory that remembers only useful information
 * - Together: they provide optimal visibility into data trends and extrema
 * - Efficiency: no redundant work as window slides through data
 * 
 * ============================================================================
 * 🎯 WHEN TO USE SLIDING WINDOW & MONOTONIC QUEUE PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding maximum/minimum in sliding windows
 * - Data stream processing with window-based statistics
 * - Maintaining running statistics over fixed-size windows
 * - Problems requiring efficient range queries
 * - Real-time analytics with bounded memory
 * - Moving averages and window aggregations
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Maximum/minimum in sliding window"
 * - "Moving window statistics"
 * - "Data stream processing"
 * - "Window-based aggregation"
 * - "Real-time analytics"
 * - "Bounded memory processing"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Static range queries (use segment trees or sparse tables)
 * - Unbounded memory acceptable (use simpler data structures)
 * - Window size is very small (simple iteration might suffice)
 * - No need for extrema or ordering (use simple sliding window)
 * 
 * ============================================================================
 * 🔄 SLIDING WINDOW & MONOTONIC QUEUE PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ MONOTONIC DEQUE FOR WINDOW MAXIMUM
 * - Deque: maintains candidates for window maximum
 * - Monotonic decreasing: front has maximum, remove smaller elements
 * - Window sliding: remove elements outside window, add new elements
 * - Example: Sliding Window Maximum problem
 * 
 * 2️⃣ CIRCULAR BUFFER WITH STATISTICS
 * - Circular buffer: efficient window storage with O(1) operations
 * - Statistics tracking: maintain sum, count, extrema
 * - Window updates: add new, remove old, update statistics
 * - Example: Moving Average from Data Stream
 * 
 * 3️⃣ FREQUENCY-BASED MONOTONIC TRACKING
 * - Frequency map: track element occurrences in window
 * - Ordered structure: maintain sorted order for efficient queries
 * - Uniqueness tracking: identify first/last unique elements
 * - Example: First Unique Number in sliding window
 * 
 * 4️⃣ MULTI-DIMENSIONAL SLIDING WINDOW
 * - 2D windows: extend sliding window to rectangles or matrices
 * - Prefix sums: efficient range sum queries
 * - Constraints: maintain bounds while maximizing objectives
 * - Example: Max Sum Rectangle No Larger Than K
 * 
 * 5️⃣ STREAMING MONOTONIC STACK
 * - Stack-based: monotonic stack for nearest greater/smaller elements
 * - Streaming: handle elements as they arrive
 * - Window awareness: maintain stack state within window bounds
 * - Example: Next Greater Element in sliding window
 * 
 * 6️⃣ DEQUE-BASED RANGE QUERIES
 * - Deque storage: maintain window elements efficiently
 * - Range operations: support insertions at both ends
 * - Query efficiency: O(1) access to front and back
 * - Example: Design Circular Deque with range capabilities
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 MONOTONIC DEQUE ALGORITHM:
 * ```
 * For each new element:
 *   1. Remove elements outside window from front
 *   2. Remove smaller elements from back (maintain monotonic decreasing)
 *   3. Add current element to back
 *   4. Front element is window maximum
 * ```
 * 
 * 🔹 CIRCULAR BUFFER ALGORITHM:
 * ```
 * Initialize: buffer[windowSize], head = 0, count = 0, sum = 0
 * Add element:
 *   - If buffer full: subtract buffer[head], advance head
 *   - Add new element at (head + count) % size
 *   - Update sum and count
 * ```
 * 
 * 🔹 FREQUENCY TRACKING ALGORITHM:
 * ```
 * Window operations:
 *   - Add element: increment frequency, update unique tracking
 *   - Remove element: decrement frequency, update unique tracking
 *   - Query unique: return first element with frequency 1
 * ```
 * 
 * 🔹 2D SLIDING WINDOW ALGORITHM:
 * ```
 * For each row range:
 *   1. Compute 1D problem for current row range
 *   2. Use sliding window on column sums
 *   3. Apply constraints (e.g., sum <= k)
 *   4. Track maximum valid window
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY WINDOW REQUIREMENTS
 * - What is the window size or criteria?
 * - Is the window fixed-size or variable?
 * - What statistics need to be maintained?
 * 
 * STEP 2: DETERMINE MONOTONIC PROPERTY
 * - What ordering is needed (increasing/decreasing)?
 * - What elements can be discarded safely?
 * - How to maintain monotonicity efficiently?
 * 
 * STEP 3: CHOOSE DATA STRUCTURE
 * - Deque for both-ends access and monotonic property
 * - Circular buffer for simple window storage
 * - Combination for complex requirements
 * 
 * STEP 4: IMPLEMENT WINDOW OPERATIONS
 * - Add element: maintain monotonic property
 * - Remove element: handle window boundary
 * - Query: efficient access to required statistics
 * 
 * STEP 5: OPTIMIZE FOR PERFORMANCE
 * - Ensure amortized O(1) per element
 * - Minimize memory usage for streaming
 * - Handle edge cases (empty windows, boundary conditions)
 * 
 * ============================================================================
 * 🎨 SLIDING WINDOW & MONOTONIC QUEUE TEMPLATES
 * ============================================================================
 */
public class SlidingWindowMonotonicQueueReadingGuide {
    
    /**
     * 🪟 SLIDING WINDOW MAXIMUM TEMPLATE
     * Find maximum in each sliding window using monotonic deque
     */
    public static class SlidingWindowMaximumTemplate {
        
        public static int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0) return new int[0];
            
            Deque<Integer> deque = new ArrayDeque<>(); // Store indices
            int[] result = new int[nums.length - k + 1];
            int resultIndex = 0;
            
            for (int i = 0; i < nums.length; i++) {
                // Remove elements outside window
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
                
                // Remove smaller elements (maintain monotonic decreasing)
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }
                
                // Add current element
                deque.offerLast(i);
                
                // Record result when window is complete
                if (i >= k - 1) {
                    result[resultIndex++] = nums[deque.peekFirst()];
                }
            }
            
            return result;
        }
    }
    
    /**
     * 🔄 CIRCULAR DEQUE TEMPLATE
     * Design circular deque with efficient operations
     */
    public static class CircularDequeTemplate {
        private int[] buffer;
        private int front, rear, size, capacity;
        
        public CircularDequeTemplate(int k) {
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
    
    /**
     * 📊 MOVING AVERAGE TEMPLATE
     * Calculate moving average using circular buffer
     */
    public static class MovingAverageTemplate {
        private int[] buffer;
        private int head, count, sum, size;
        
        public MovingAverageTemplate(int size) {
            this.size = size;
            buffer = new int[size];
            head = 0;
            count = 0;
            sum = 0;
        }
        
        public double next(int val) {
            if (count < size) {
                // Buffer not full yet
                buffer[count] = val;
                sum += val;
                count++;
            } else {
                // Buffer full, replace oldest
                sum = sum - buffer[head] + val;
                buffer[head] = val;
                head = (head + 1) % size;
            }
            
            return (double) sum / count;
        }
    }
    
    /**
     * 🔍 FIRST UNIQUE NUMBER TEMPLATE
     * Track first unique number in sliding window
     */
    public static class FirstUniqueTemplate {
        private Queue<Integer> queue;
        private Map<Integer, Integer> frequency;
        
        public FirstUniqueTemplate(int[] nums) {
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
     * 📈 MAX SUM RECTANGLE TEMPLATE
     * Find maximum sum rectangle with constraint using sliding window
     */
    public static class MaxSumRectangleTemplate {
        
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
                    
                    // Find max subarray sum <= k
                    int currentMax = maxSubarraySum(colSums, k);
                    maxSum = Math.max(maxSum, currentMax);
                    
                    if (maxSum == k) return k; // Early termination
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
    }
    
    /**
     * 🌊 MONOTONIC QUEUE TEMPLATE
     * Generic monotonic queue for min/max queries
     */
    public static class MonotonicQueueTemplate {
        private Deque<Integer> deque; // Store indices
        private int[] arr;
        private boolean isMaxQueue; // true for max, false for min
        
        public MonotonicQueueTemplate(int[] array, boolean findMax) {
            arr = array;
            isMaxQueue = findMax;
            deque = new ArrayDeque<>();
        }
        
        public void push(int index) {
            // Remove elements that don't maintain monotonic property
            while (!deque.isEmpty() && !shouldKeep(deque.peekLast(), index)) {
                deque.pollLast();
            }
            deque.offerLast(index);
        }
        
        public void pop(int index) {
            // Remove element if it's at front and matches index
            if (!deque.isEmpty() && deque.peekFirst() == index) {
                deque.pollFirst();
            }
        }
        
        public int query() {
            return deque.isEmpty() ? -1 : arr[deque.peekFirst()];
        }
        
        private boolean shouldKeep(int oldIndex, int newIndex) {
            if (isMaxQueue) {
                return arr[oldIndex] > arr[newIndex]; // Keep if larger
            } else {
                return arr[oldIndex] < arr[newIndex]; // Keep if smaller
            }
        }
    }
    
    /**
     * 🔧 SLIDING WINDOW STATISTICS TEMPLATE
     * Maintain multiple statistics in sliding window
     */
    public static class SlidingWindowStatsTemplate {
        private Deque<Integer> window;
        private Deque<Integer> maxDeque, minDeque;
        private int windowSize;
        private long sum;
        
        public SlidingWindowStatsTemplate(int k) {
            windowSize = k;
            window = new ArrayDeque<>();
            maxDeque = new ArrayDeque<>();
            minDeque = new ArrayDeque<>();
            sum = 0;
        }
        
        public void addNumber(int num) {
            // Add to window
            window.offerLast(num);
            sum += num;
            
            // Maintain max deque (monotonic decreasing)
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < num) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(num);
            
            // Maintain min deque (monotonic increasing)
            while (!minDeque.isEmpty() && minDeque.peekLast() > num) {
                minDeque.pollLast();
            }
            minDeque.offerLast(num);
            
            // Remove oldest if window full
            if (window.size() > windowSize) {
                int removed = window.pollFirst();
                sum -= removed;
                
                if (!maxDeque.isEmpty() && maxDeque.peekFirst() == removed) {
                    maxDeque.pollFirst();
                }
                if (!minDeque.isEmpty() && minDeque.peekFirst() == removed) {
                    minDeque.pollFirst();
                }
            }
        }
        
        public int getMax() {
            return maxDeque.isEmpty() ? Integer.MIN_VALUE : maxDeque.peekFirst();
        }
        
        public int getMin() {
            return minDeque.isEmpty() ? Integer.MAX_VALUE : minDeque.peekFirst();
        }
        
        public double getAverage() {
            return window.isEmpty() ? 0.0 : (double) sum / window.size();
        }
        
        public int getRange() {
            return getMax() - getMin();
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Monotonic Property
     * Ensure monotonic deque maintains correct ordering
     */
    public static void monotonicExample() {
        // For maximum: maintain decreasing order (largest at front)
        // For minimum: maintain increasing order (smallest at front)
        // Remove from back to maintain property
    }
    
    /**
     * ❌ PITFALL 2: Window Boundary Errors
     * Correctly handle window size and boundaries
     */
    public static void boundaryExample() {
        // Always check if element is within current window
        // Remove elements outside window before processing
        // Handle edge cases (empty window, single element)
    }
    
    /**
     * ❌ PITFALL 3: Index vs Value Confusion
     * Store indices in deque when positions matter
     */
    public static void indexExample() {
        // Store indices for window boundary checking
        // Use values for comparison and result
        // Be consistent throughout implementation
    }
    
    /**
     * ❌ PITFALL 4: Memory Leaks in Streaming
     * Properly remove old elements in streaming scenarios
     */
    public static void memoryExample() {
        // Remove elements outside window bounds
        // Clean up frequency maps and auxiliary structures
        // Ensure bounded memory usage for infinite streams
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Data Structure
     * - Deque for both-ends access and monotonic property
     * - Circular buffer for simple window storage
     * - TreeSet for ordered statistics with range queries
     */
    
    /**
     * 🎯 TIP 2: Maintain Amortized Complexity
     * - Each element enters and exits structures exactly once
     * - Avoid nested loops for window operations
     * - Use lazy removal when possible
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases Gracefully
     * - Empty windows, single elements, full capacity
     * - Window size larger than array length
     * - Streaming scenarios with infinite data
     */
    
    /**
     * 🎯 TIP 4: Optimize for Memory Usage
     * - Use circular buffers instead of shifting arrays
     * - Store indices instead of values when possible
     * - Clean up auxiliary structures regularly
     */
    
    /**
     * 🎯 TIP 5: Consider Multi-Dimensional Extensions
     * - 2D sliding windows for matrix problems
     * - Multiple windows for parallel processing
     * - Hierarchical windows for multi-scale analysis
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC SLIDING WINDOW & MONOTONIC QUEUE PROBLEMS:
     * - Sliding Window Maximum
     * - Moving Average from Data Stream
     * - Design Circular Deque
     * - First Unique Number
     * 
     * 🟡 INTERMEDIATE SLIDING WINDOW & MONOTONIC QUEUE PROBLEMS:
     * - Sliding Window Minimum
     * - Max Sum Rectangle No Larger Than K
     * - Constrained Subsequence Sum
     * - Shortest Subarray with Sum at Least K
     * 
     * 🔴 ADVANCED SLIDING WINDOW & MONOTONIC QUEUE PROBLEMS:
     * - Maximum of Minimum Values in all windows
     * - Sliding Window Median
     * - Maximum Performance of a Team
     * - Minimum Window Substring with Character Constraints
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE SLIDING WINDOW & MONOTONIC QUEUE WHEN:
     * ✅ Need maximum/minimum in sliding windows
     * ✅ Processing data streams with window-based statistics
     * ✅ Bounded memory requirements for continuous data
     * ✅ Efficient range queries over moving windows
     * ✅ Real-time analytics with performance constraints
     * 
     * AVOID WHEN:
     * ❌ Static range queries (use segment trees)
     * ❌ Very small window sizes (simple iteration)
     * ❌ Unbounded memory acceptable
     * ❌ No ordering or extrema requirements
     * 
     * OPTIMIZE WITH:
     * 🚀 Amortized O(1) operations per element
     * 🚀 Monotonic property for efficient queries
     * 🚀 Circular buffers for memory efficiency
     * 🚀 Lazy removal for streaming scenarios
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Financial trading (moving averages, price trends)
     * - System monitoring (performance metrics, resource usage)
     * - IoT data processing (sensor readings, anomaly detection)
     * - Video processing (frame analysis, motion detection)
     * - Network analytics (traffic monitoring, bandwidth analysis)
     * - Game development (score tracking, performance analytics)
     * - Real-time recommendations (trending content, user behavior)
     * - Scientific computing (signal processing, data smoothing)
     */
    
    public static void main(String[] args) {
        System.out.println("🪟 SLIDING WINDOW & MONOTONIC QUEUE PATTERN - READING GUIDE");
        System.out.println("=============================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Sliding Window Maximum
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] maxWindow = SlidingWindowMaximumTemplate.maxSlidingWindow(nums, k);
        System.out.println("Sliding Window Maximum: " + Arrays.toString(maxWindow)); // [3, 3, 5, 5, 6, 7]
        
        // Circular Deque
        CircularDequeTemplate deque = new CircularDequeTemplate(3);
        System.out.println("Circular Deque insertLast(1): " + deque.insertLast(1)); // true
        System.out.println("Circular Deque insertLast(2): " + deque.insertLast(2)); // true
        System.out.println("Circular Deque insertFront(3): " + deque.insertFront(3)); // true
        System.out.println("Circular Deque getFront(): " + deque.getFront()); // 3
        System.out.println("Circular Deque getRear(): " + deque.getRear()); // 2
        
        // Moving Average
        MovingAverageTemplate movingAverage = new MovingAverageTemplate(3);
        System.out.println("Moving Average next(1): " + movingAverage.next(1)); // 1.0
        System.out.println("Moving Average next(10): " + movingAverage.next(10)); // 5.5
        System.out.println("Moving Average next(3): " + movingAverage.next(3)); // 4.67
        System.out.println("Moving Average next(5): " + movingAverage.next(5)); // 6.0
        
        // First Unique Number
        FirstUniqueTemplate firstUnique = new FirstUniqueTemplate(new int[]{2, 3, 5});
        System.out.println("First Unique showFirstUnique(): " + firstUnique.showFirstUnique()); // 2
        firstUnique.add(5);
        System.out.println("First Unique after add(5): " + firstUnique.showFirstUnique()); // 2
        firstUnique.add(2);
        System.out.println("First Unique after add(2): " + firstUnique.showFirstUnique()); // 3
        
        // Max Sum Rectangle
        int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
        int maxSum = MaxSumRectangleTemplate.maxSumSubmatrix(matrix, 2);
        System.out.println("Max Sum Rectangle (<= 2): " + maxSum); // 2
        
        // Sliding Window Statistics
        SlidingWindowStatsTemplate stats = new SlidingWindowStatsTemplate(3);
        stats.addNumber(1);
        stats.addNumber(3);
        stats.addNumber(2);
        System.out.println("Window Stats - Max: " + stats.getMax()); // 3
        System.out.println("Window Stats - Min: " + stats.getMin()); // 1
        System.out.println("Window Stats - Average: " + stats.getAverage()); // 2.0
        System.out.println("Window Stats - Range: " + stats.getRange()); // 2
        
        stats.addNumber(5);
        System.out.println("After adding 5 - Max: " + stats.getMax()); // 5
        System.out.println("After adding 5 - Average: " + stats.getAverage()); // 3.33
        
        System.out.println("\n✅ Key Sliding Window & Monotonic Queue Principles:");
        System.out.println("1. Maintain window boundaries efficiently with deques");
        System.out.println("2. Use monotonic property for O(1) extrema queries");
        System.out.println("3. Ensure amortized O(1) operations per element");
        System.out.println("4. Handle streaming data with bounded memory usage");
        System.out.println("5. Remove elements outside window before processing");
        System.out.println("6. Store indices for position tracking, values for comparison");
        System.out.println("7. Consider circular buffers for simple window storage");
    }
} 