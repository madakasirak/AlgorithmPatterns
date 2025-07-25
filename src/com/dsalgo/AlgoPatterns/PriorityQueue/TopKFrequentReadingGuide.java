package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 TOP K FREQUENT ELEMENTS PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS TOP K FREQUENT ELEMENTS PATTERN?
 * ============================================================================
 * 
 * Top K Frequent Elements Pattern combines frequency analysis with efficient
 * selection algorithms to identify the most frequently occurring elements in
 * datasets. This pattern is essential for data analytics, trend analysis,
 * recommendation systems, and performance monitoring where understanding
 * element occurrence patterns is crucial for decision-making.
 * 
 * The pattern leverages frequency counting with HashMap optimization, heap-based
 * selection for top-k queries, bucket sort for linear time solutions, and
 * specialized data structures for streaming frequency analysis with optimal
 * performance across various data types and computational constraints.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. FREQUENCY TRACKING: Efficiently count and maintain element occurrences
 * 2. SELECTION OPTIMIZATION: Apply top-k algorithms on frequency-sorted data
 * 3. MEMORY EFFICIENCY: Balance frequency storage with selection performance
 * 4. STREAMING ANALYSIS: Handle dynamic frequency updates in real-time data
 * 
 * 🎯 FREQUENCY ANALYSIS METAPHOR:
 * Think of Top K Frequent Elements as "popularity contest analytics":
 * - Vote counting: track how many times each candidate (element) appears
 * - Leaderboard: maintain ranking of most popular candidates efficiently
 * - Real-time updates: handle new votes (elements) without full recalculation
 * - Top performers: quickly identify winners without counting all votes
 * 
 * ============================================================================
 * 🎯 WHEN TO USE TOP K FREQUENT ELEMENTS PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Analyzing data trends and identifying most common patterns
 * - Building recommendation systems based on user behavior frequency
 * - Performance monitoring for most frequent errors or events
 * - Text analysis for most common words or character patterns
 * - Log analysis for identifying frequent system behaviors
 * - Social media analytics for trending topics and hashtags
 * - E-commerce analytics for popular products and categories
 * - Network analysis for most active nodes or frequent connections
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Most frequent elements" or "Top K frequent"
 * - "Find most common" or "Identify popular items"
 * - "Sort by frequency" or "Order by occurrence"
 * - "Trending analysis" or "Popularity ranking"
 * - "Frequency-based selection" or "Count-based filtering"
 * - "Most occurring patterns" or "Common element analysis"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need exact frequencies for all elements (use full frequency map)
 * - K is very close to total unique elements (sorting might be simpler)
 * - Single occurrence check (use contains/membership tests)
 * - Order matters more than frequency (use positional algorithms)
 * 
 * ============================================================================
 * 🔄 TOP K FREQUENT PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ BASIC FREQUENCY ANALYSIS
 * - HashMap-based frequency counting
 * - Min-heap selection for top-k elements
 * - Linear frequency accumulation
 * - Memory-efficient selection algorithms
 * 
 * 2️⃣ BUCKET SORT OPTIMIZATION
 * - Frequency-based bucket allocation
 * - Linear time complexity achievement
 * - Memory-intensive but time-optimal approach
 * - Best for bounded frequency ranges
 * 
 * 3️⃣ CHARACTER/STRING FREQUENCY
 * - Character-specific frequency analysis
 * - String building with frequency ordering
 * - Custom comparators for lexicographical ties
 * - Efficient string manipulation techniques
 * 
 * 4️⃣ STREAMING FREQUENCY ANALYSIS
 * - Dynamic frequency maintenance
 * - Real-time top-k updates
 * - Memory-bounded frequency tracking
 * - Incremental frequency stack operations
 * 
 * 5️⃣ MATRIX FREQUENCY ANALYSIS
 * - 2D coordinate frequency tracking
 * - Spatial frequency distribution
 * - Multi-dimensional frequency analysis
 * - Coordinate-based frequency selection
 * 
 * 6️⃣ ADVANCED FREQUENCY STRUCTURES
 * - Frequency stack with maximum access
 * - Multi-level frequency tracking
 * - Composite frequency analysis
 * - Dynamic frequency-based operations
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 HEAP-BASED TOP K FREQUENT ALGORITHM:
 * ```
 * HashMap + Min-Heap approach:
 *   1. Count frequencies using HashMap
 *   2. Use min-heap of size K based on frequency
 *   3. For each unique element:
 *      - If heap size < K: add element
 *      - Else if frequency > heap.top(): replace
 *   4. Extract all elements from heap
 * 
 * Time: O(n + m log k), Space: O(m + k)
 * where n = total elements, m = unique elements
 * ```
 * 
 * 🔹 BUCKET SORT FREQUENCY ALGORITHM:
 * ```
 * Frequency Bucket Sort:
 *   1. Count frequencies using HashMap
 *   2. Create buckets for each possible frequency [0...n]
 *   3. Place elements in buckets based on frequency
 *   4. Traverse buckets from high to low frequency
 *   5. Collect first K elements encountered
 * 
 * Time: O(n), Space: O(n)
 * ```
 * 
 * 🔹 CHARACTER FREQUENCY SORTING:
 * ```
 * Character frequency with string building:
 *   1. Count character frequencies
 *   2. Sort characters by frequency (desc) then lexicographically
 *   3. Build result string by repeating each character
 *   4. Optimize with StringBuilder for performance
 * 
 * Time: O(n + k log k), Space: O(k)
 * where k = unique characters
 * ```
 * 
 * 🔹 STREAMING FREQUENCY STACK:
 * ```
 * Maximum frequency stack:
 *   1. Maintain frequency count for each element
 *   2. Keep stack for each frequency level
 *   3. Track maximum frequency seen so far
 *   4. Push: increment frequency, add to frequency stack
 *   5. Pop: remove from max frequency stack, decrement
 * 
 * Time: O(1) push/pop, Space: O(n)
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: ANALYZE DATA CHARACTERISTICS
 * - Static dataset vs streaming data?
 * - Memory constraints vs time optimization?
 * - Single query vs multiple frequency queries?
 * 
 * STEP 2: CHOOSE FREQUENCY STRATEGY
 * - Heap-based: memory-efficient for large datasets with small K
 * - Bucket sort: time-optimal when frequency range is bounded
 * - Custom structures: specialized for specific data types
 * 
 * STEP 3: IMPLEMENT CORE ALGORITHM
 * - Handle frequency counting with appropriate data structures
 * - Optimize selection algorithm based on K and dataset size
 * - Consider tie-breaking strategies for equal frequencies
 * 
 * STEP 4: OPTIMIZE FOR CONSTRAINTS
 * - Memory optimization: use fixed-size heaps for bounded memory
 * - Time optimization: bucket sort for linear performance
 * - Streaming optimization: incremental frequency updates
 * 
 * STEP 5: VALIDATE AND TEST
 * - Test with various frequency distributions
 * - Verify correctness with edge cases and ties
 * - Benchmark performance across different data sizes
 * 
 * ============================================================================
 * 🎨 TOP K FREQUENT ALGORITHM TEMPLATES
 * ============================================================================
 */

public class TopKFrequentReadingGuide {
    
    /**
     * 🏆 BASIC HEAP-BASED FREQUENCY ANALYSIS TEMPLATES
     */
    public static class HeapBasedFrequency {
        
        /**
         * Top K Frequent Elements using Min-Heap
         * Strategy: Frequency counting + min-heap selection
         */
        public static int[] topKFrequent(int[] nums, int k) {
            // Step 1: Count frequencies
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
            
            // Step 2: Use min-heap to select top k
            java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>(
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
            
            // Step 3: Extract results
            int[] result = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                result[i] = minHeap.poll();
            }
            
            return result;
        }
        
        /**
         * Top K Frequent Words with Lexicographical Tie-Breaking
         * Strategy: Frequency counting + custom comparator for ties
         */
        public static List<String> topKFrequentWords(String[] words, int k) {
            // Count word frequencies
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String word : words) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
            
            // Min-heap with custom comparator
            java.util.PriorityQueue<String> minHeap = new java.util.PriorityQueue<>((a, b) -> {
                int freqCompare = frequencyMap.get(a) - frequencyMap.get(b);
                if (freqCompare == 0) {
                    return b.compareTo(a); // Reverse lexicographical for min-heap
                }
                return freqCompare;
            });
            
            for (String word : frequencyMap.keySet()) {
                minHeap.offer(word);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
            
            // Extract in reverse order for correct frequency ranking
            List<String> result = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                result.add(0, minHeap.poll());
            }
            
            return result;
        }
    }
    
    /**
     * ⚡ BUCKET SORT OPTIMIZATION TEMPLATES
     */
    public static class BucketSortFrequency {
        
        /**
         * Top K Frequent Elements using Bucket Sort
         * Strategy: Linear time complexity with frequency buckets
         */
        public static int[] topKFrequentBucketSort(int[] nums, int k) {
            // Step 1: Count frequencies
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
            
            // Step 2: Create frequency buckets
            List<Integer>[] buckets = new List[nums.length + 1];
            for (int i = 0; i <= nums.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            
            // Step 3: Fill buckets based on frequency
            for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
                int frequency = entry.getValue();
                buckets[frequency].add(entry.getKey());
            }
            
            // Step 4: Collect top k elements from high frequency buckets
            List<Integer> result = new ArrayList<>();
            for (int frequency = buckets.length - 1; frequency >= 0 && result.size() < k; frequency--) {
                for (int num : buckets[frequency]) {
                    if (result.size() < k) {
                        result.add(num);
                    }
                }
            }
            
            return result.stream().mapToInt(i -> i).toArray();
        }
        
        /**
         * Sort Characters By Frequency using Bucket Sort
         * Strategy: Character frequency with bucket-based sorting
         */
        public static String frequencySort(String s) {
            // Count character frequencies
            Map<Character, Integer> frequencyMap = new HashMap<>();
            for (char c : s.toCharArray()) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
            
            // Create frequency buckets
            List<Character>[] buckets = new List[s.length() + 1];
            for (int i = 0; i <= s.length(); i++) {
                buckets[i] = new ArrayList<>();
            }
            
            // Fill buckets
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                buckets[entry.getValue()].add(entry.getKey());
            }
            
            // Build result string
            StringBuilder result = new StringBuilder();
            for (int frequency = buckets.length - 1; frequency >= 0; frequency--) {
                for (char c : buckets[frequency]) {
                    for (int i = 0; i < frequency; i++) {
                        result.append(c);
                    }
                }
            }
            
            return result.toString();
        }
    }
    
    /**
     * 📊 STREAMING FREQUENCY ANALYSIS TEMPLATES
     */
    public static class StreamingFrequency {
        
        /**
         * Maximum Frequency Stack
         * Strategy: Multi-level frequency tracking with stack operations
         */
        public static class FreqStack {
            private Map<Integer, Integer> frequencies;
            private Map<Integer, Stack<Integer>> frequencyStacks;
            private int maxFrequency;
            
            public FreqStack() {
                frequencies = new HashMap<>();
                frequencyStacks = new HashMap<>();
                maxFrequency = 0;
            }
            
            public void push(int val) {
                // Increment frequency
                int frequency = frequencies.getOrDefault(val, 0) + 1;
                frequencies.put(val, frequency);
                
                // Update max frequency
                maxFrequency = Math.max(maxFrequency, frequency);
                
                // Add to frequency stack
                frequencyStacks.computeIfAbsent(frequency, k -> new Stack<>()).push(val);
            }
            
            public int pop() {
                // Get element from max frequency stack
                Stack<Integer> maxFreqStack = frequencyStacks.get(maxFrequency);
                int result = maxFreqStack.pop();
                
                // Update frequency
                frequencies.put(result, frequencies.get(result) - 1);
                
                // Update max frequency if needed
                if (maxFreqStack.isEmpty()) {
                    maxFrequency--;
                }
                
                return result;
            }
        }
        
        /**
         * Streaming Top K Frequent Tracker
         * Strategy: Maintain top k elements with frequency updates
         */
        public static class StreamingTopK {
            private Map<Integer, Integer> frequencies;
            private java.util.PriorityQueue<Integer> minHeap;
            private int k;
            
            public StreamingTopK(int k) {
                this.k = k;
                this.frequencies = new HashMap<>();
                this.minHeap = new java.util.PriorityQueue<>(
                    (a, b) -> frequencies.get(a) - frequencies.get(b)
                );
            }
            
            public void addElement(int num) {
                // Update frequency
                frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
                
                // Update heap
                if (!minHeap.contains(num)) {
                    if (minHeap.size() < k) {
                        minHeap.offer(num);
                    } else if (frequencies.get(num) > frequencies.get(minHeap.peek())) {
                        minHeap.poll();
                        minHeap.offer(num);
                    }
                }
                // Note: In practice, would need more sophisticated heap update
            }
            
            public List<Integer> getTopK() {
                return new ArrayList<>(minHeap);
            }
        }
    }
    
    /**
     * 🔍 MATRIX FREQUENCY ANALYSIS TEMPLATES
     */
    public static class MatrixFrequency {
        
        /**
         * Top K Frequent Elements in Matrix
         * Strategy: Coordinate-based frequency analysis
         */
        public static int[] topKFrequentInMatrix(int[][] matrix, int k) {
            // Count frequencies from matrix
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int[] row : matrix) {
                for (int num : row) {
                    frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
                }
            }
            
            // Use heap-based selection
            java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>(
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
            
            int[] result = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                result[i] = minHeap.poll();
            }
            
            return result;
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Heap Comparator
     * Use min-heap with frequency comparison for top-k selection
     */
    public static void heapComparatorExample() {
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Correct: Min-heap based on frequency
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b)
        );
        
        // Incorrect: Max-heap (inefficient for top-k)
        // java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(
        //     (a, b) -> freq.get(b) - freq.get(a)
        // );
    }
    
    /**
     * ❌ PITFALL 2: Tie-Breaking Issues
     * Handle equal frequencies with consistent tie-breaking
     */
    public static void tieBreakingExample() {
        Map<String, Integer> freq = new HashMap<>();
        
        // Correct: Handle frequency ties with lexicographical order
        java.util.PriorityQueue<String> heap = new java.util.PriorityQueue<>((a, b) -> {
            int freqCompare = freq.get(a) - freq.get(b);
            return freqCompare == 0 ? b.compareTo(a) : freqCompare;
        });
        
        // Incorrect: No tie-breaking can cause inconsistent results
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Choose Right Algorithm for Data Size
     * - Small k: Heap-based for memory efficiency
     * - Large datasets: Bucket sort for linear time
     * - Streaming: Specialized frequency tracking structures
     */
    
    /**
     * 🎯 STRATEGY 2: Memory vs Time Trade-offs
     * - Heap: O(k) extra space, O(n log k) time
     * - Bucket sort: O(n) space, O(n) time
     * - Hybrid: Combine based on data characteristics
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * DATA TYPE:
     * ✅ Numbers → Standard frequency counting with heap or bucket sort
     * ✅ Strings → Custom comparators for lexicographical tie-breaking
     * ✅ Characters → Efficient character array processing
     * ✅ Matrix → Coordinate-based frequency analysis
     * 
     * CONSTRAINTS:
     * ✅ Memory limited → Heap-based selection with fixed size
     * ✅ Time critical → Bucket sort for linear performance
     * ✅ Streaming data → Specialized frequency stack structures
     * ✅ Large k → Consider full sorting vs top-k selection
     */
    
    public static void main(String[] args) {
        System.out.println("🎯 TOP K FREQUENT ELEMENTS PATTERN - READING GUIDE");
        System.out.println("=================================================");
        
        // Test examples
        int[] testArray = {1, 1, 1, 2, 2, 3};
        
        // Heap-based example
        int[] topKHeap = HeapBasedFrequency.topKFrequent(testArray, 2);
        System.out.println("Top 2 frequent (heap): " + Arrays.toString(topKHeap)); // [1, 2]
        
        // Bucket sort example
        int[] topKBucket = BucketSortFrequency.topKFrequentBucketSort(testArray, 2);
        System.out.println("Top 2 frequent (bucket): " + Arrays.toString(topKBucket)); // [1, 2]
        
        // Character frequency example
        String charFreq = BucketSortFrequency.frequencySort("tree");
        System.out.println("Chars by frequency: " + charFreq); // "eert" or "eetr"
        
        // Word frequency example
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> topWords = HeapBasedFrequency.topKFrequentWords(words, 2);
        System.out.println("Top 2 frequent words: " + topWords); // ["i", "love"]
        
        // Frequency stack example
        StreamingFrequency.FreqStack freqStack = new StreamingFrequency.FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println("Pop from freq stack: " + freqStack.pop()); // 5 (most frequent)
        System.out.println("Pop from freq stack: " + freqStack.pop()); // 7 (most frequent after 5)
        
        System.out.println("\n✅ Key Top K Frequent Principles:");
        System.out.println("1. Count frequencies efficiently with HashMap");
        System.out.println("2. Use min-heap for memory-efficient top-k selection");
        System.out.println("3. Apply bucket sort for linear time complexity");
        System.out.println("4. Handle tie-breaking consistently for deterministic results");
        System.out.println("5. Choose algorithm based on data size and constraints");
        System.out.println("6. Use specialized structures for streaming frequency analysis");
        System.out.println("7. Optimize memory usage vs time complexity trade-offs");
    }
} 