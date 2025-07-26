package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🔥 PRIORITY QUEUE PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of priority queue algorithms
 * for solving heap-based problems, top-k optimization, scheduling systems, graph
 * algorithms, and stream processing. These algorithms demonstrate efficient priority
 * management, optimal selection strategies, merge operations, and scheduling optimization
 * techniques using heap data structures for various computational requirements.
 * 
 * Pattern Focus: Priority-based processing, heap operations, optimization, scheduling
 * Time Complexity: Generally O(n log k) for top-k problems, O(log n) for heap operations
 * Space Complexity: O(k) for top-k problems, O(n) for complete heap storage
 */

// Supporting classes for complex problems
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class PriorityQueueProblems {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Priority Queue Operations
    // ============================================================================
    
    /**
     * 🟢 EASY: Kth Largest Element in an Array
     * 
     * Problem: Find the kth largest element in unsorted array
     * LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/
     * 
     * Approach: Use min-heap of size k
     * - Min-heap maintains k largest elements seen so far
     * - Root of min-heap is kth largest element
     * - When heap size exceeds k, remove smallest element
     * - Final root is guaranteed to be kth largest
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
     * 🟢 EASY: Last Stone Weight
     * 
     * Problem: Simulate stone smashing process using heaviest stones
     * LeetCode: https://leetcode.com/problems/last-stone-weight/
     * 
     * Approach: Use max-heap for efficient heaviest stone access
     * - Max-heap allows O(log n) access to heaviest stone
     * - Simulate smashing: take two heaviest stones
     * - If weights differ, put difference back in heap
     * - Continue until at most one stone remains
     * 
     * Time: O(n log n), Space: O(n)
     */
    public static int lastStoneWeight(int[] stones) {
        // Max-heap for accessing heaviest stones
        java.util.PriorityQueue<Integer> maxHeap = 
            new java.util.PriorityQueue<>(Collections.reverseOrder());
        
        // Add all stones to heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        // Simulate stone smashing
        while (maxHeap.size() > 1) {
            int heaviest = maxHeap.poll();
            int secondHeaviest = maxHeap.poll();
            
            if (heaviest != secondHeaviest) {
                maxHeap.offer(heaviest - secondHeaviest);
            }
        }
        
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
    
    /**
     * 🟢 EASY: Kth Largest Element in a Stream
     * 
     * Problem: Design class to find kth largest element in stream
     * LeetCode: https://leetcode.com/problems/kth-largest-element-in-a-stream/
     * 
     * Approach: Maintain min-heap of size k
     * - Min-heap always contains k largest elements seen
     * - Root is always kth largest element
     * - Add operation maintains heap size constraint
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
    // 🟡 MEDIUM PROBLEMS - Intermediate Priority Queue Algorithms
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Top K Frequent Elements
     * 
     * Problem: Find k most frequent elements in array
     * LeetCode: https://leetcode.com/problems/top-k-frequent-elements/
     * 
     * Approach: Frequency counting + min-heap
     * - Count frequencies using HashMap
     * - Use min-heap of size k based on frequency
     * - Heap maintains k most frequent elements
     * - Elements with higher frequency replace lower frequency ones
     * 
     * Time: O(n log k), Space: O(n + k)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Min-heap based on frequency
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
        );
        
        // Maintain k most frequent elements
        for (int num : frequencyMap.keySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (frequencyMap.get(num) > frequencyMap.get(minHeap.peek())) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        // Convert to array
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    /**
     * 🟡 MEDIUM: Find Median from Data Stream
     * 
     * Problem: Find median of numbers from continuous stream
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
     * 🟡 MEDIUM: Merge k Sorted Lists
     * 
     * Problem: Merge k sorted linked lists into one sorted list
     * LeetCode: https://leetcode.com/problems/merge-k-sorted-lists/
     * 
     * Approach: Priority queue for efficient minimum finding
     * - Min-heap stores list nodes by value
     * - Always extract minimum node from all current heads
     * - Add extracted node to result and advance its list
     * - Continue until all lists are exhausted
     * 
     * Time: O(n log k) where n = total nodes, Space: O(k)
     */
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
        
        // Build merged list
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;
            
            // Add next node from same list
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    /**
     * 🟡 MEDIUM: Task Scheduler
     * 
     * Problem: Schedule tasks with cooldown period between identical tasks
     * LeetCode: https://leetcode.com/problems/task-scheduler/
     * 
     * Approach: Max-heap with cooldown queue
     * - Count task frequencies
     * - Use max-heap to always schedule most frequent available task
     * - Use queue to track tasks in cooldown period
     * - Continue until all tasks are scheduled
     * 
     * Time: O(m * n) where m = unique tasks, n = cooldown, Space: O(m)
     */
    public static int leastInterval(char[] tasks, int n) {
        // Count task frequencies
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }
        
        // Max-heap based on frequency
        java.util.PriorityQueue<Integer> maxHeap = 
            new java.util.PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(taskCount.values());
        
        int time = 0;
        Queue<int[]> cooldownQueue = new LinkedList<>(); // [count, availableTime]
        
        while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
            time++;
            
            // Add back tasks that finished cooldown
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
    
    /**
     * 🟡 MEDIUM: Meeting Rooms II
     * 
     * Problem: Find minimum meeting rooms needed for all meetings
     * LeetCode: https://leetcode.com/problems/meeting-rooms-ii/
     * 
     * Approach: Sort by start time + min-heap for end times
     * - Sort meetings by start time
     * - Use min-heap to track earliest ending meeting
     * - For each meeting, check if room is available (earliest end ≤ current start)
     * - If available, reuse room; otherwise, need new room
     * 
     * Time: O(n log n), Space: O(n)
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
                minHeap.poll(); // Reuse the room
            }
            minHeap.offer(intervals[i][1]); // Add current meeting end time
        }
        
        return minHeap.size(); // Number of rooms needed
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Priority Queue Algorithms
    // ============================================================================
    
    /**
     * 🔴 HARD: Smallest Range Covering Elements from K Lists
     * 
     * Problem: Find smallest range that includes at least one element from each list
     * LeetCode: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
     * 
     * Approach: Min-heap with range tracking
     * - Use heap to track current minimum from all lists
     * - Track maximum value among current elements
     * - Range = [min, max] where min is heap root
     * - Advance the list that contributed minimum element
     * - Continue until one list is exhausted
     * 
     * Time: O(n log k) where n = total elements, Space: O(k)
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        // Heap storing [value, listIndex, elementIndex]
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
    
    /**
     * 🔴 HARD: Sliding Window Maximum
     * 
     * Problem: Find maximum element in every sliding window of size k
     * LeetCode: https://leetcode.com/problems/sliding-window-maximum/
     * 
     * Approach: Deque-based monotonic queue (more efficient than heap)
     * - Use deque to maintain potential maximums in decreasing order
     * - Remove elements outside current window from front
     * - Remove smaller elements from back (they can't be maximum)
     * - Front element is always maximum of current window
     * 
     * Time: O(n), Space: O(k)
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
     * 🔴 HARD: Network Delay Time (Dijkstra's Algorithm)
     * 
     * Problem: Find time for signal to reach all nodes in network
     * LeetCode: https://leetcode.com/problems/network-delay-time/
     * 
     * Approach: Dijkstra's shortest path with min-heap
     * - Use min-heap for efficient shortest distance selection
     * - Maintain distances array with shortest paths from source
     * - Process nodes in order of shortest distance
     * - Update neighbors if shorter path found
     * 
     * Time: O((V + E) log V), Space: O(V + E)
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
            (a, b) -> a[1] - b[1] // Compare by distance
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
        
        // Find maximum time (time to reach all nodes)
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, distances[i]);
        }
        
        return maxTime;
    }
    
    /**
     * 🔴 HARD: Super Ugly Number
     * 
     * Problem: Find nth super ugly number using given prime factors
     * LeetCode: https://leetcode.com/problems/super-ugly-number/
     * 
     * Approach: Min-heap with multiple pointers
     * - Use heap to find next smallest ugly number
     * - Maintain pointers for each prime factor
     * - Generate candidates by multiplying existing ugly numbers with primes
     * - Use heap to efficiently find minimum candidate
     * 
     * Time: O(n log k) where k = number of primes, Space: O(n + k)
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        
        // Min-heap to store candidates [value, prime_index, ugly_index]
        java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        
        // Initialize heap with first candidates
        for (int i = 0; i < primes.length; i++) {
            minHeap.offer(new int[]{primes[i], i, 0});
        }
        
        for (int i = 1; i < n; i++) {
            // Get next ugly number
            ugly[i] = minHeap.peek()[0];
            
            // Remove all duplicates
            while (!minHeap.isEmpty() && minHeap.peek()[0] == ugly[i]) {
                int[] current = minHeap.poll();
                int primeIndex = current[1];
                int uglyIndex = current[2];
                
                // Generate next candidate with same prime
                minHeap.offer(new int[]{
                    primes[primeIndex] * ugly[uglyIndex + 1],
                    primeIndex,
                    uglyIndex + 1
                });
            }
        }
        
        return ugly[n - 1];
    }
    
    /**
     * 🔴 HARD: Find K Pairs with Smallest Sums
     * 
     * Problem: Find k pairs with smallest sums from two sorted arrays
     * LeetCode: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
     * 
     * Approach: Min-heap with intelligent candidate generation
     * - Use heap to efficiently find pairs with smallest sums
     * - Start with pairs involving first element of nums2
     * - For each extracted pair, add next candidate from same nums1 element
     * - Avoid duplicates using visited tracking
     * 
     * Time: O(k log k), Space: O(k)
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;
        
        // Min-heap storing [sum, i, j] where sum = nums1[i] + nums2[j]
        java.util.PriorityQueue<int[]> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        
        Set<String> visited = new HashSet<>();
        
        // Start with pairs involving first element of nums2
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            int sum = nums1[i] + nums2[0];
            minHeap.offer(new int[]{sum, i, 0});
            visited.add(i + "," + 0);
        }
        
        while (result.size() < k && !minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int i = current[1];
            int j = current[2];
            
            result.add(Arrays.asList(nums1[i], nums2[j]));
            
            // Add next candidate from same nums1 element
            if (j + 1 < nums2.length && !visited.contains(i + "," + (j + 1))) {
                int sum = nums1[i] + nums2[j + 1];
                minHeap.offer(new int[]{sum, i, j + 1});
                visited.add(i + "," + (j + 1));
            }
        }
        
        return result;
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    // Helper method to create test data
    private static int[][] createTestIntervals() {
        return new int[][]{{0, 30}, {5, 10}, {15, 20}};
    }
    
    private static ListNode createTestList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
    
    private static void printList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        System.out.println(values);
    }
    
    public static void main(String[] args) {
        System.out.println("🔥 PRIORITY QUEUE PATTERN - PRACTICE PROBLEMS");
        System.out.println("=============================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        // Test Kth Largest Element
        int[] testArray = {3, 2, 1, 5, 6, 4};
        int kthLargest = findKthLargest(testArray, 2);
        System.out.println("2nd largest element: " + kthLargest); // 5
        
        // Test Last Stone Weight
        int[] stones = {2, 7, 4, 1, 8, 1};
        int lastStone = lastStoneWeight(stones);
        System.out.println("Last stone weight: " + lastStone); // 1
        
        // Test Kth Largest in Stream
        KthLargest kthLargestStream = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println("Kth largest after adding 3: " + kthLargestStream.add(3)); // 4
        System.out.println("Kth largest after adding 5: " + kthLargestStream.add(5)); // 5
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        // Test Top K Frequent
        int[] frequencyArray = {1, 1, 1, 2, 2, 3};
        int[] topK = topKFrequent(frequencyArray, 2);
        System.out.println("Top 2 frequent: " + Arrays.toString(topK)); // [1, 2]
        
        // Test Median Finder
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after 1, 2: " + medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println("Median after adding 3: " + medianFinder.findMedian()); // 2.0
        
        // Test Merge K Lists
        ListNode[] lists = {
            createTestList(new int[]{1, 4, 5}),
            createTestList(new int[]{1, 3, 4}),
            createTestList(new int[]{2, 6})
        };
        ListNode merged = mergeKLists(lists);
        System.out.print("Merged lists: ");
        printList(merged); // [1, 1, 2, 3, 4, 4, 5, 6]
        
        // Test Task Scheduler
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int minTime = leastInterval(tasks, 2);
        System.out.println("Minimum time for task scheduling: " + minTime); // 8
        
        // Test Meeting Rooms
        int[][] intervals = createTestIntervals();
        int meetingRooms = minMeetingRooms(intervals);
        System.out.println("Minimum meeting rooms needed: " + meetingRooms); // 2
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        // Test Smallest Range
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4, 10, 15, 24, 26),
            Arrays.asList(0, 9, 12, 20),
            Arrays.asList(5, 18, 22, 30)
        );
        int[] smallestRange = smallestRange(nums);
        System.out.println("Smallest range: [" + smallestRange[0] + ", " + smallestRange[1] + "]"); // [20, 24]
        
        // Test Sliding Window Maximum
        int[] windowArray = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] maxWindow = maxSlidingWindow(windowArray, 3);
        System.out.println("Sliding window maximum: " + Arrays.toString(maxWindow)); // [3, 3, 5, 5, 6, 7]
        
        // Test Network Delay Time
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int networkDelay = networkDelayTime(times, 4, 2);
        System.out.println("Network delay time: " + networkDelay); // 2
        
        // Test Super Ugly Number
        int[] primes = {2, 7, 13, 19};
        int uglyNumber = nthSuperUglyNumber(12, primes);
        System.out.println("12th super ugly number: " + uglyNumber); // 32
        
        // Test K Smallest Pairs
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        List<List<Integer>> kSmallest = kSmallestPairs(nums1, nums2, 3);
        System.out.println("K smallest pairs: " + kSmallest); // [[1,2], [1,4], [1,6]]
        
        System.out.println("\n✅ Key Priority Queue Principles:");
        System.out.println("1. Use min-heap for k largest elements, max-heap for k smallest");
        System.out.println("2. Maintain heap size constraints for efficient top-k processing");
        System.out.println("3. Apply dual-heap strategy for median finding and range queries");
        System.out.println("4. Leverage priority queues for scheduling and optimization problems");
        System.out.println("5. Use custom comparators for complex priority criteria");
        System.out.println("6. Combine heaps with other data structures for advanced algorithms");
        System.out.println("7. Consider deque-based approaches for sliding window problems");
        System.out.println("8. Apply Dijkstra's algorithm for graph shortest path problems");
    }
} 