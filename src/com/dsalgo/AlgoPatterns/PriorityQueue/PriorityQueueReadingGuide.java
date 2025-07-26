package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🔥 PRIORITY QUEUE PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS THE PRIORITY QUEUE PATTERN?
 * ============================================================================
 * 
 * The Priority Queue Pattern leverages heap data structures to efficiently manage
 * elements based on priority rather than insertion order. This pattern excels at
 * maintaining sorted order for streaming data, finding extreme values, scheduling
 * tasks, and solving optimization problems where order matters. Priority queues
 * provide O(log n) insertion and deletion while maintaining the most important
 * element at the root.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. PRIORITY ORDERING: Elements ordered by importance, not insertion time
 * 2. EFFICIENT ACCESS: O(1) access to highest/lowest priority element
 * 3. BALANCED OPERATIONS: O(log n) insert/delete maintains heap property
 * 4. FLEXIBLE COMPARISON: Custom comparators define priority criteria
 * 
 * 🏔️ HEAP METAPHOR:
 * Think of a priority queue as a "corporate hierarchy":
 * - CEO (root) has highest priority and is always accessible
 * - Each level maintains proper authority relationships
 * - Promotions/demotions (insert/delete) maintain structure
 * - New employees find their proper level in the hierarchy
 * 
 * ============================================================================
 * 🎯 WHEN TO USE PRIORITY QUEUE PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding kth largest/smallest elements
 * - Merging k sorted lists/arrays
 * - Real-time median calculation
 * - Task scheduling with priorities
 * - Graph algorithms (Dijkstra's, Prim's)
 * - Top-k frequent elements
 * - Sliding window maximum/minimum
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find the kth largest/smallest"
 * - "Top k elements"
 * - "Merge k sorted..."
 * - "Running median"
 * - "Schedule tasks with priority"
 * - "Find minimum/maximum in sliding window"
 * - "Shortest path" or "minimum spanning tree"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need to access arbitrary elements (use HashMap)
 * - Frequent updates to priorities (consider indexed heaps)
 * - Small, fixed-size data (simple sorting might be better)
 * - Need FIFO/LIFO behavior (use Queue/Stack)
 * 
 * ============================================================================
 * 🔄 PRIORITY QUEUE VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ MIN-HEAP (Default Java PriorityQueue)
 * - Smallest element at root
 * - Use for: kth largest, bottom-k elements
 * - Construction: new java.util.PriorityQueue<>()
 * 
 * 2️⃣ MAX-HEAP (Reverse Comparator)
 * - Largest element at root  
 * - Use for: kth smallest, top-k elements
 * - Construction: new java.util.PriorityQueue<>(Collections.reverseOrder())
 * 
 * 3️⃣ CUSTOM PRIORITY
 * - Elements ordered by custom criteria
 * - Use for: complex objects, multiple priorities
 * - Construction: new java.util.PriorityQueue<>((a, b) -> customComparison)
 * 
 * 4️⃣ BOUNDED HEAP
 * - Fixed-size heap with overflow handling
 * - Use for: top-k problems with memory constraints
 * - Manually maintain size <= k
 * 
 * 5️⃣ DUAL HEAP SYSTEM
 * - Two heaps working together (min + max)
 * - Use for: running median, balanced partitioning
 * - Balance sizes to maintain median access
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 HEAP OPERATIONS:
 * ```
 * INSERT (offer): Add element, bubble up to maintain heap property - O(log n)
 * EXTRACT (poll): Remove root, bubble down to restore heap - O(log n)  
 * PEEK: Access root element without removal - O(1)
 * SIZE: Get current number of elements - O(1)
 * ```
 * 
 * 🔹 COMMON ALGORITHMS:
 * ```
 * TOP-K PATTERN:
 * 1. Use min-heap of size k for k-largest problems
 * 2. Use max-heap of size k for k-smallest problems
 * 3. Maintain heap size <= k by removing root when exceeded
 * 
 * MERGE PATTERN:
 * 1. Add first element from each list to heap
 * 2. Extract minimum, add to result
 * 3. Add next element from same list if available
 * 4. Repeat until heap is empty
 * 
 * MEDIAN PATTERN:
 * 1. Use max-heap for smaller half (left side)
 * 2. Use min-heap for larger half (right side)  
 * 3. Balance sizes: |left| - |right| <= 1
 * 4. Median is root of larger heap or average of both roots
 * ```
 * 
 * 🔹 COMPLEXITY ANALYSIS:
 * ```
 * Time Complexities:
 * - Insert/Delete: O(log n)
 * - Peek: O(1)
 * - Build heap from array: O(n)
 * 
 * Space Complexities:
 * - Heap storage: O(n) for n elements
 * - Top-k problems: O(k) space
 * - Merge k lists: O(k) space
 * ```
 * 
 * ============================================================================
 * 📋 PRIORITY QUEUE FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY PRIORITY CRITERIA
 * - What makes one element "higher priority" than another?
 * - Do you need minimum or maximum priority at root?
 * - Are there multiple priority levels or custom comparisons?
 * 
 * STEP 2: CHOOSE HEAP TYPE
 * - Min-heap: for problems asking for largest elements
 * - Max-heap: for problems asking for smallest elements  
 * - Custom comparator: for complex priority rules
 * 
 * STEP 3: DETERMINE SIZE CONSTRAINTS
 * - Fixed size k: for top-k problems
 * - Unbounded: for complete sorting or merging
 * - Balanced: for median-finding problems
 * 
 * STEP 4: IMPLEMENT CORE LOGIC
 * - Initialize heap with appropriate comparator
 * - Process elements maintaining heap constraints
 * - Extract results in required order
 * 
 * STEP 5: OPTIMIZE FOR SPECIFIC REQUIREMENTS
 * - Memory: use bounded heaps for large datasets
 * - Performance: consider batch operations
 * - Functionality: add custom methods as needed
 * 
 * ============================================================================
 * 🎨 PRIORITY QUEUE TEMPLATES
 * ============================================================================
 */

public class PriorityQueueReadingGuide {

    /**
     * ================================================================================
     *                          VARIATION 1: TOP-K PROBLEMS
     * ================================================================================
     * 
     * PATTERN:
     * Use heap of size k to maintain top-k elements efficiently
     * - Min-heap for k-largest elements (root is kth largest)
     * - Max-heap for k-smallest elements (root is kth smallest)
     * 
     * USE CASES:
     * - Kth largest element in array
     * - Top k frequent elements  
     * - K closest points to origin
     * - Find k largest numbers in stream
     */
    
    // TEMPLATE: Top K Elements 
    public static class TopKTemplates {
        public static List<Integer> findKLargest(int[] nums, int k) {
            java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
            
            for (int num : nums) {
                if (minHeap.size() < k) {
                    minHeap.offer(num);
                } else if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            
            return new ArrayList<>(minHeap);
        }
        
        public static List<Integer> findKSmallest(int[] nums, int k) {
            java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
            
            for (int num : nums) {
                if (maxHeap.size() < k) {
                    maxHeap.offer(num);
                } else if (num < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            }
            
            return new ArrayList<>(maxHeap);
        }
    }

    // TEMPLATE: Kth Largest Element
    public static class KthLargestTemplate {
        public static int findKthLargest(int[] nums, int k) {
            // Min-heap of size k
            java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
            
            for (int num : nums) {
                if (minHeap.size() < k) {
                    minHeap.offer(num);
                } else if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            
            return minHeap.peek(); // kth largest
        }
    }
    
    // EXAMPLE: Kth Largest in Stream
    public static class KthLargestStream {
        private java.util.PriorityQueue<Integer> minHeap;
        private int k;
        
        public KthLargestStream(int k, int[] nums) {
            this.k = k;
            this.minHeap = new java.util.PriorityQueue<>();
            
            // Add all initial elements
            for (int num : nums) {
                add(num);
            }
        }
        
        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.offer(val);
            } else if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(val);
            }
            return minHeap.peek();
        }
    }
    
    /**
     * 🔄 MERGE OPERATIONS TEMPLATES
     */
    public static class MergeTemplates {
        
        /**
         * Merge K Sorted Arrays
         * Strategy: Use min-heap with array tracking
         */
        public static List<Integer> mergeKSortedArrays(int[][] arrays) {
            List<Integer> result = new ArrayList<>();
            
            // Min-heap storing (value, arrayIndex, elementIndex)
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
                
                // Add next element from same array
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
         * Merge K Sorted Lists
         * Strategy: Similar to arrays but with ListNode
         */
        public static class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
        
        public static ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            
            // Min-heap for list nodes
            java.util.PriorityQueue<ListNode> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a.val - b.val
            );
            
            // Add all non-null list heads
            for (ListNode list : lists) {
                if (list != null) {
                    minHeap.offer(list);
                }
            }
            
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            while (!minHeap.isEmpty()) {
                ListNode node = minHeap.poll();
                current.next = node;
                current = current.next;
                
                if (node.next != null) {
                    minHeap.offer(node.next);
                }
            }
            
            return dummy.next;
        }
        
        /**
         * Smallest Range Covering Elements from K Lists
         * Strategy: Use heap to track current elements from each list
         */
        public static int[] smallestRange(List<List<Integer>> nums) {
            // Heap storing (value, listIndex, elementIndex)
            java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a[0] - b[0]
            );
            
            int maxVal = Integer.MIN_VALUE;
            
            // Initialize heap with first element from each list
            for (int i = 0; i < nums.size(); i++) {
                if (!nums.get(i).isEmpty()) {
                    int val = nums.get(i).get(0);
                    minHeap.offer(new int[]{val, i, 0});
                    maxVal = Math.max(maxVal, val);
                }
            }
            
            int[] result = {0, Integer.MAX_VALUE};
            
            while (minHeap.size() == nums.size()) {
                int[] current = minHeap.poll();
                int minVal = current[0];
                int listIndex = current[1];
                int elementIndex = current[2];
                
                // Update result if current range is smaller
                if (maxVal - minVal < result[1] - result[0]) {
                    result[0] = minVal;
                    result[1] = maxVal;
                }
                
                // Move to next element in the same list
                if (elementIndex + 1 < nums.get(listIndex).size()) {
                    int nextVal = nums.get(listIndex).get(elementIndex + 1);
                    minHeap.offer(new int[]{nextVal, listIndex, elementIndex + 1});
                    maxVal = Math.max(maxVal, nextVal);
                }
            }
            
            return result;
        }
    }
    
    /**
     * 📊 STREAMING ALGORITHMS TEMPLATES
     */
    public static class StreamingTemplates {
        
        /**
         * Find Median from Data Stream
         * Strategy: Use two heaps to maintain median
         */
        public static class MedianFinder {
            // Max-heap for smaller half
            private java.util.PriorityQueue<Integer> maxHeap;
            // Min-heap for larger half
            private java.util.PriorityQueue<Integer> minHeap;
            
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
         * Sliding Window Maximum
         * Strategy: Use deque for efficient window maximum tracking
         */
        public static int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[0];
            
            int[] result = new int[nums.length - k + 1];
            Deque<Integer> deque = new ArrayDeque<>(); // Store indices
            
            for (int i = 0; i < nums.length; i++) {
                // Remove indices outside window
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
                
                // Remove smaller elements from back
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }
                
                deque.offerLast(i);
                
                // Record maximum for current window
                if (i >= k - 1) {
                    result[i - k + 1] = nums[deque.peekFirst()];
                }
            }
            
            return result;
        }
        
        /**
         * Top K Elements in Sliding Window
         * Strategy: Combine sliding window with top-k tracking
         */
        public static List<List<Integer>> topKInSlidingWindow(int[] nums, int k, int windowSize) {
            List<List<Integer>> result = new ArrayList<>();
            
            for (int i = 0; i <= nums.length - windowSize; i++) {
                // Extract window
                int[] window = Arrays.copyOfRange(nums, i, i + windowSize);
                
                // Find top k in current window
                List<Integer> topK = TopKTemplates.findKLargest(window, k);
                result.add(topK);
            }
            
            return result;
        }
    }
    
    /**
     * 🎯 GRAPH ALGORITHMS TEMPLATES
     */
    public static class GraphAlgorithmTemplates {
        
        /**
         * Dijkstra's Shortest Path
         * Strategy: Use min-heap for efficient path exploration
         */
        public static int[] dijkstra(int[][] graph, int source) {
            int n = graph.length;
            int[] distances = new int[n];
            boolean[] visited = new boolean[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;
            
            // Min-heap storing (distance, vertex)
            java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a[0] - b[0]
            );
            minHeap.offer(new int[]{0, source});
            
            while (!minHeap.isEmpty()) {
                int[] current = minHeap.poll();
                int currentDist = current[0];
                int currentVertex = current[1];
                
                if (visited[currentVertex]) continue;
                visited[currentVertex] = true;
                
                // Explore neighbors
                for (int neighbor = 0; neighbor < n; neighbor++) {
                    if (graph[currentVertex][neighbor] > 0 && !visited[neighbor]) {
                        int newDist = currentDist + graph[currentVertex][neighbor];
                        if (newDist < distances[neighbor]) {
                            distances[neighbor] = newDist;
                            minHeap.offer(new int[]{newDist, neighbor});
                        }
                    }
                }
            }
            
            return distances;
        }
        
        /**
         * Network Delay Time
         * Strategy: Modified Dijkstra for network propagation
         */
        public static int networkDelayTime(int[][] times, int n, int k) {
            // Build adjacency list
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] time : times) {
                graph.computeIfAbsent(time[0], x -> new ArrayList<>())
                     .add(new int[]{time[1], time[2]});
            }
            
            // Dijkstra's algorithm
            int[] distances = new int[n + 1];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[k] = 0;
            
            java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a[1] - b[1]
            );
            minHeap.offer(new int[]{k, 0});
            
            while (!minHeap.isEmpty()) {
                int[] current = minHeap.poll();
                int node = current[0];
                int time = current[1];
                
                if (time > distances[node]) continue;
                
                if (graph.containsKey(node)) {
                    for (int[] edge : graph.get(node)) {
                        int neighbor = edge[0];
                        int weight = edge[1];
                        int newTime = time + weight;
                        
                        if (newTime < distances[neighbor]) {
                            distances[neighbor] = newTime;
                            minHeap.offer(new int[]{neighbor, newTime});
                        }
                    }
                }
            }
            
            int maxTime = 0;
            for (int i = 1; i <= n; i++) {
                if (distances[i] == Integer.MAX_VALUE) return -1;
                maxTime = Math.max(maxTime, distances[i]);
            }
            
            return maxTime;
        }
        
        /**
         * Minimum Cost to Connect All Points (Prim's Algorithm)
         * Strategy: Use min-heap for MST construction
         */
        public static int minCostConnectPoints(int[][] points) {
            int n = points.length;
            if (n <= 1) return 0;
            
            boolean[] visited = new boolean[n];
            // Min-heap storing (cost, pointIndex)
            java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
                (a, b) -> a[0] - b[0]
            );
            
            // Start from point 0
            visited[0] = true;
            for (int i = 1; i < n; i++) {
                int cost = manhattanDistance(points[0], points[i]);
                minHeap.offer(new int[]{cost, i});
            }
            
            int totalCost = 0;
            int edgesAdded = 0;
            
            while (!minHeap.isEmpty() && edgesAdded < n - 1) {
                int[] edge = minHeap.poll();
                int cost = edge[0];
                int pointIndex = edge[1];
                
                if (visited[pointIndex]) continue;
                
                visited[pointIndex] = true;
                totalCost += cost;
                edgesAdded++;
                
                // Add edges to unvisited points
                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        int newCost = manhattanDistance(points[pointIndex], points[i]);
                        minHeap.offer(new int[]{newCost, i});
                    }
                }
            }
            
            return totalCost;
        }
        
        private static int manhattanDistance(int[] p1, int[] p2) {
            return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }
    }
    
    /**
     * ⏰ SCHEDULING TEMPLATES
     */
    public static class SchedulingTemplates {
        
        /**
         * Task Scheduler with Priorities
         * Strategy: Use max-heap for priority-based scheduling
         */
        public static class TaskScheduler {
            private java.util.PriorityQueue<Task> taskQueue;
            
            public TaskScheduler() {
                // Max-heap based on priority
                this.taskQueue = new java.util.PriorityQueue<>(
                    (a, b) -> b.priority - a.priority
                );
            }
            
            public void addTask(String name, int priority, int duration) {
                taskQueue.offer(new Task(name, priority, duration));
            }
            
            public Task getNextTask() {
                return taskQueue.poll();
            }
            
            public boolean hasTasks() {
                return !taskQueue.isEmpty();
            }
            
            private static class Task {
                String name;
                int priority;
                int duration;
                
                Task(String name, int priority, int duration) {
                    this.name = name;
                    this.priority = priority;
                    this.duration = duration;
                }
            }
        }
        
        /**
         * Meeting Rooms II
         * Strategy: Use min-heap to track meeting end times
         */
        public static int minMeetingRooms(int[][] intervals) {
            if (intervals.length == 0) return 0;
            
            // Sort by start time
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            
            // Min-heap to track end times
            java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
            minHeap.offer(intervals[0][1]);
            
            for (int i = 1; i < intervals.length; i++) {
                // If current meeting starts after earliest ending meeting
                if (intervals[i][0] >= minHeap.peek()) {
                    minHeap.poll();
                }
                minHeap.offer(intervals[i][1]);
            }
            
            return minHeap.size();
        }
        
        /**
         * CPU Task Scheduling with Cooldown
         * Strategy: Use max-heap with cooldown tracking
         */
        public static int leastInterval(char[] tasks, int n) {
            // Count task frequencies
            Map<Character, Integer> taskCount = new HashMap<>();
            for (char task : tasks) {
                taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
            }
            
            // Max-heap based on frequency
            java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
            maxHeap.addAll(taskCount.values());
            
            int time = 0;
            Queue<int[]> cooldownQueue = new LinkedList<>(); // [count, availableTime]
            
            while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
                time++;
                
                // Add back tasks that have finished cooldown
                if (!cooldownQueue.isEmpty() && cooldownQueue.peek()[1] == time) {
                    maxHeap.offer(cooldownQueue.poll()[0]);
                }
                
                // Execute highest frequency task
                if (!maxHeap.isEmpty()) {
                    int count = maxHeap.poll() - 1;
                    if (count > 0) {
                        cooldownQueue.offer(new int[]{count, time + n + 1});
                    }
                }
            }
            
            return time;
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
     * ❌ PITFALL 2: Comparator Consistency
     * Ensure comparator is consistent and handles edge cases
     */
    public static void comparatorExample() {
        // Correct: consistent comparison
        java.util.PriorityQueue<int[]> correct = new java.util.PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Problematic: potential overflow
        // java.util.PriorityQueue<int[]> problematic = new java.util.PriorityQueue<>((a, b) -> a[0] - b[0]);
    }
    
    /**
     * ❌ PITFALL 3: Heap Size Management
     * Properly maintain heap size constraints
     */
    public static void heapSizeExample() {
        // Correct: maintain size k
        java.util.PriorityQueue<Integer> heap = new java.util.PriorityQueue<>();
        int k = 3;
        
        // Add element with size check
        if (heap.size() < k) {
            heap.offer(42);
        } else if (42 > heap.peek()) {
            heap.poll();
            heap.offer(42);
        }
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Heap Size Strategy
     * - Fixed size k: for top-k problems
     * - Unbounded: for complete ordering
     * - Dual heaps: for median and range queries
     */
    
    /**
     * 🎯 TIP 2: Consider Custom Comparators
     * - Multi-criteria sorting
     * - Complex object comparisons
     * - Reverse ordering when needed
     */
    
    /**
     * 🎯 TIP 3: Optimize for Common Operations
     * - Batch insertions when possible
     * - Consider heap construction vs incremental building
     * - Use appropriate data structure for access patterns
     */
    
    /**
     * 🎯 TIP 4: Handle Edge Cases
     * - Empty heaps
     * - Duplicate priorities
     * - Integer overflow in comparisons
     * - Null elements
     */
    
    /**
     * 🎯 TIP 5: Memory and Performance Considerations
     * - Initial capacity for large datasets
     * - Custom heap implementations for specialized needs
     * - Trade-offs between time and space complexity
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC PRIORITY QUEUE PROBLEMS:
     * - Kth Largest Element in Array
     * - Last Stone Weight
     * - Find Median from Data Stream
     * - Top K Frequent Elements
     * 
     * 🟡 INTERMEDIATE PRIORITY QUEUE PROBLEMS:
     * - Merge k Sorted Lists
     * - Task Scheduler
     * - Meeting Rooms II
     * - Sliding Window Maximum
     * - Network Delay Time
     * 
     * 🔴 ADVANCED PRIORITY QUEUE PROBLEMS:
     * - Smallest Range Covering Elements from K Lists
     * - Super Ugly Number
     * - Find K Pairs with Smallest Sums
     * - Trapping Rain Water II
     * - Rearrange String k Distance Apart
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE PRIORITY QUEUE WHEN:
     * ✅ Need efficient access to min/max element
     * ✅ Processing elements in priority order
     * ✅ Top-k problems with large datasets
     * ✅ Merge operations on sorted sequences
     * ✅ Scheduling and optimization problems
     * 
     * HEAP TYPE SELECTION:
     * 🔥 Min-heap: smallest element at root (natural ordering)
     * 🔥 Max-heap: largest element at root (reverse ordering)
     * 🔥 Custom: complex comparison criteria
     * 
     * SIZE STRATEGY:
     * 📏 Fixed size k: for top-k problems
     * 📏 Unbounded: for complete priority ordering
     * 📏 Multi-heap: for complex requirements (median, ranges)
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Operating system task scheduling
     * - Network packet routing and QoS
     * - Database query optimization
     * - Search engine result ranking
     * - Load balancing and resource allocation
     * - Event-driven simulation systems
     * - Real-time data processing pipelines
     * - Game AI pathfinding and decision making
     */
    
    public static void main(String[] args) {
        System.out.println("🔥 PRIORITY QUEUE PATTERN - READING GUIDE");
        System.out.println("==========================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Test Top-K algorithms
        int[] testArray = {3, 2, 1, 5, 6, 4};
        List<Integer> top3Largest = TopKTemplates.findKLargest(testArray, 3);
        System.out.println("Top 3 largest: " + top3Largest);
        
        List<Integer> top3Smallest = TopKTemplates.findKSmallest(testArray, 3);
        System.out.println("Top 3 smallest: " + top3Smallest);
        
        // Test streaming median
        StreamingTemplates.MedianFinder medianFinder = new StreamingTemplates.MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after adding 1, 2: " + medianFinder.findMedian());
        
        medianFinder.addNum(3);
        System.out.println("Median after adding 3: " + medianFinder.findMedian());
        
        // Test merge operations
        int[][] sortedArrays = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        List<Integer> merged = MergeTemplates.mergeKSortedArrays(sortedArrays);
        System.out.println("Merged arrays: " + merged);
        
        // Test task scheduling
        SchedulingTemplates.TaskScheduler scheduler = new SchedulingTemplates.TaskScheduler();
        scheduler.addTask("High Priority Task", 10, 5);
        scheduler.addTask("Low Priority Task", 1, 3);
        scheduler.addTask("Medium Priority Task", 5, 4);
        
        System.out.println("Task execution order:");
        while (scheduler.hasTasks()) {
            SchedulingTemplates.TaskScheduler.Task task = scheduler.getNextTask();
            System.out.println("- " + task.name + " (Priority: " + task.priority + ")");
        }
        
        System.out.println("\n✅ Key Priority Queue Principles:");
        System.out.println("1. Use appropriate heap type for problem requirements");
        System.out.println("2. Maintain heap size constraints for top-k problems");
        System.out.println("3. Choose efficient algorithms for merge operations");
        System.out.println("4. Apply dual-heap strategy for median finding");
        System.out.println("5. Leverage priority queues for scheduling and optimization");
        System.out.println("6. Consider custom comparators for complex priority criteria");
        System.out.println("7. Handle edge cases and maintain heap properties");
    }
} 