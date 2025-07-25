package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🔥 PRIORITY QUEUE PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS PRIORITY QUEUE PATTERN?
 * ============================================================================
 * 
 * Priority Queue Pattern involves using heap-based data structures to efficiently
 * manage elements based on their priority, enabling quick access to the highest
 * or lowest priority element. This pattern is fundamental for scheduling algorithms,
 * optimization problems, graph algorithms, stream processing, and any scenario
 * where elements need to be processed in priority order rather than insertion order.
 * 
 * The pattern leverages the heap property to maintain partial ordering, providing
 * O(log n) insertion and deletion of priority elements, and O(1) access to the
 * top priority element. It's essential for problems involving "best", "top-k",
 * "nearest", and "optimal" solutions.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. PRIORITY ORDERING: Maintain elements sorted by priority, not insertion order
 * 2. EFFICIENT ACCESS: O(1) access to highest/lowest priority element
 * 3. DYNAMIC UPDATES: Efficiently add/remove elements while maintaining order
 * 4. HEAP PROPERTY: Utilize heap structure for optimal performance characteristics
 * 
 * 🔥 PRIORITY QUEUE METAPHOR:
 * Think of priority queues as "smart waiting systems":
 * - Hospital emergency room: patients treated by severity, not arrival time
 * - Airport boarding: first class, business, then economy regardless of check-in time
 * - Task scheduler: high-priority tasks executed before low-priority ones
 * - Resource allocation: distribute limited resources to highest priority needs
 * 
 * ============================================================================
 * 🎯 WHEN TO USE PRIORITY QUEUE PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding top-k elements (largest, smallest, most frequent)
 * - Graph algorithms (Dijkstra's shortest path, Prim's MST)
 * - Scheduling and task management systems
 * - Stream processing with priority-based filtering
 * - Optimization problems requiring best-first search
 * - Merge operations on multiple sorted sequences
 * - Event simulation and discrete event systems
 * - Resource allocation and load balancing
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find the k largest/smallest elements"
 * - "Process elements in priority order"
 * - "Nearest/closest/optimal solution"
 * - "Merge k sorted arrays/lists"
 * - "Schedule tasks by priority"
 * - "Real-time top-k monitoring"
 * - "Median in streaming data"
 * - "Shortest path/minimum cost"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - All elements needed in order (use full sorting)
 * - Only need single min/max (use simple comparison)
 * - FIFO/LIFO processing (use queue/stack)
 * - Random access needed (use arrays/lists)
 * - Small, fixed dataset (simple iteration might suffice)
 * 
 * ============================================================================
 * 🔄 PRIORITY QUEUE PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TOP-K PROBLEMS
 * - Min-heap for k largest elements
 * - Max-heap for k smallest elements
 * - Sliding window top-k monitoring
 * - Frequency-based top-k analysis
 * 
 * 2️⃣ MERGE OPERATIONS
 * - Merge k sorted arrays/lists
 * - Stream merging from multiple sources
 * - Priority-based data aggregation
 * - Multi-way merge algorithms
 * 
 * 3️⃣ GRAPH ALGORITHMS
 * - Dijkstra's shortest path (min-heap)
 * - Prim's minimum spanning tree
 * - A* search algorithm
 * - Best-first search strategies
 * 
 * 4️⃣ SCHEDULING SYSTEMS
 * - Task scheduling by priority/deadline
 * - CPU scheduling algorithms
 * - Event-driven simulation
 * - Resource allocation optimization
 * 
 * 5️⃣ STREAMING ALGORITHMS
 * - Running median calculation
 * - Online top-k monitoring
 * - Sliding window statistics
 * - Real-time data processing
 * 
 * 6️⃣ OPTIMIZATION PROBLEMS
 * - Huffman coding tree construction
 * - Interval scheduling maximization
 * - Greedy algorithm optimization
 * - Dynamic programming with priority
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 HEAP OPERATIONS:
 * ```
 * Insert (O(log n)):
 *   1. Add element to end of heap
 *   2. Bubble up to maintain heap property
 * 
 * Extract Min/Max (O(log n)):
 *   1. Remove root element
 *   2. Move last element to root
 *   3. Bubble down to restore heap property
 * 
 * Peek (O(1)):
 *   Return root element without removal
 * ```
 * 
 * 🔹 TOP-K ALGORITHM:
 * ```
 * For k largest elements:
 *   Use min-heap of size k
 *   For each element:
 *     If heap.size() < k: add element
 *     Else if element > heap.peek(): replace root
 * 
 * For k smallest elements:
 *   Use max-heap of size k (opposite logic)
 * ```
 * 
 * 🔹 MERGE K SORTED ARRAYS:
 * ```
 * Use min-heap storing (value, arrayIndex, elementIndex)
 * Initialize heap with first element from each array
 * While heap not empty:
 *   Extract minimum element
 *   Add to result
 *   Add next element from same array to heap
 * ```
 * 
 * 🔹 RUNNING MEDIAN:
 * ```
 * Use two heaps:
 *   maxHeap: stores smaller half (max at top)
 *   minHeap: stores larger half (min at top)
 * 
 * Balance heaps to maintain size difference ≤ 1
 * Median: root of larger heap or average of both roots
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY PRIORITY REQUIREMENTS
 * - What determines element priority?
 * - Need min-heap, max-heap, or custom comparator?
 * - Is priority static or dynamic?
 * 
 * STEP 2: DETERMINE HEAP SIZE STRATEGY
 * - Fixed size k for top-k problems?
 * - Unbounded for complete ordering?
 * - Multiple heaps for complex requirements?
 * 
 * STEP 3: CHOOSE HEAP CONFIGURATION
 * - Single heap: simple priority ordering
 * - Dual heap: median finding, range queries
 * - Multiple heaps: complex multi-criteria problems
 * 
 * STEP 4: IMPLEMENT HEAP OPERATIONS
 * - Insert: maintain heap property efficiently
 * - Extract: handle root removal and rebalancing
 * - Update: modify priorities if needed
 * 
 * STEP 5: OPTIMIZE FOR USE CASE
 * - Batch operations when possible
 * - Consider heap construction vs incremental building
 * - Handle edge cases (empty heap, duplicate priorities)
 * 
 * ============================================================================
 * 🎨 PRIORITY QUEUE TEMPLATES
 * ============================================================================
 */

public class PriorityQueueReadingGuide {
    
    /**
     * 🏆 TOP-K PROBLEMS TEMPLATES
     */
    public static class TopKTemplates {
        
        /**
         * Find K Largest Elements
         * Strategy: Use min-heap of size k
         */
        public static List<Integer> findKLargest(int[] nums, int k) {
            // Min-heap to store k largest elements
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            
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
        
        /**
         * Find K Smallest Elements
         * Strategy: Use max-heap of size k
         */
        public static List<Integer> findKSmallest(int[] nums, int k) {
            // Max-heap to store k smallest elements
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            
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
        
        /**
         * Top K Frequent Elements
         * Strategy: Use min-heap with frequency comparison
         */
        public static List<Integer> topKFrequent(int[] nums, int k) {
            // Count frequencies
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
            
            // Min-heap based on frequency
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
            );
            
            for (int num : frequencyMap.keySet()) {
                if (minHeap.size() < k) {
                    minHeap.offer(num);
                } else if (frequencyMap.get(num) > frequencyMap.get(minHeap.peek())) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            
            return new ArrayList<>(minHeap);
        }
        
        /**
         * Kth Largest Element in Stream
         * Strategy: Maintain min-heap of size k
         */
        public static class KthLargestStream {
            private PriorityQueue<Integer> minHeap;
            private int k;
            
            public KthLargestStream(int k, int[] nums) {
                this.k = k;
                this.minHeap = new PriorityQueue<>();
                
                // Add all initial elements
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
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
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
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
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
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
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
            private PriorityQueue<Integer> maxHeap;
            // Min-heap for larger half
            private PriorityQueue<Integer> minHeap;
            
            public MedianFinder() {
                maxHeap = new PriorityQueue<>(Collections.reverseOrder());
                minHeap = new PriorityQueue<>();
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
                List<Integer> topK = findKLargest(window, k);
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
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
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
            
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
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
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
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
            private PriorityQueue<Task> taskQueue;
            
            public TaskScheduler() {
                // Max-heap based on priority
                this.taskQueue = new PriorityQueue<>(
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
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
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
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
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
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Incorrect: max-heap for k largest (inefficient)
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    /**
     * ❌ PITFALL 2: Comparator Consistency
     * Ensure comparator is consistent and handles edge cases
     */
    public static void comparatorExample() {
        // Correct: consistent comparison
        PriorityQueue<int[]> correct = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Problematic: potential overflow
        // PriorityQueue<int[]> problematic = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    }
    
    /**
     * ❌ PITFALL 3: Heap Size Management
     * Properly maintain heap size constraints
     */
    public static void heapSizeExample() {
        // Correct: maintain size k
        PriorityQueue<Integer> heap = new PriorityQueue<>();
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