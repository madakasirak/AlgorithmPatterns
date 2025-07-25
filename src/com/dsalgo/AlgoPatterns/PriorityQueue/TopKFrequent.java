package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 TOP K FREQUENT ELEMENTS PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of frequency analysis algorithms
 * for identifying the most frequently occurring elements in datasets. These algorithms
 * demonstrate HashMap-based frequency counting, heap-based selection optimization,
 * bucket sort for linear time performance, and specialized data structures for
 * streaming frequency analysis across various data types and computational scenarios.
 * 
 * Pattern Focus: Frequency analysis, top-k selection, streaming frequency tracking
 * Time Complexity: O(n log k) for heap-based, O(n) for bucket sort approaches
 * Space Complexity: O(n) for frequency storage, O(k) for selection algorithms
 */

public class TopKFrequent {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Frequency Analysis
    // ============================================================================
    
    /**
     * 🟢 EASY: Top K Frequent Elements
     * 
     * Problem: Find k most frequent elements in an array
     * LeetCode: https://leetcode.com/problems/top-k-frequent-elements/
     * 
     * Approach 1: HashMap + Min-Heap
     * - Count frequencies using HashMap for O(1) access
     * - Use min-heap of size k to maintain top k frequent elements
     * - Min-heap keeps least frequent of top k at root for easy replacement
     * - Memory efficient approach for large datasets with small k
     * 
     * Time: O(n log k), Space: O(n + k)
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
        
        // Step 3: Extract results in correct order
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    /**
     * Alternative: Bucket Sort approach for linear time
     * - Create buckets for each possible frequency [0...n]
     * - Place elements in buckets based on their frequency
     * - Traverse from highest frequency bucket to collect top k
     * - Optimal time complexity but uses more memory
     * 
     * Time: O(n), Space: O(n)
     */
    public static int[] topKFrequentBucketSort(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create frequency buckets
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Fill buckets based on frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int frequency = entry.getValue();
            buckets[frequency].add(entry.getKey());
        }
        
        // Collect top k elements from high frequency buckets
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
     * 🟢 EASY: Sort Characters By Frequency
     * 
     * Problem: Sort characters in string by frequency of appearance
     * LeetCode: https://leetcode.com/problems/sort-characters-by-frequency/
     * 
     * Approach: Frequency counting + bucket sort
     * - Count character frequencies using HashMap
     * - Use bucket sort for optimal O(n) time complexity
     * - Build result string by appending characters in frequency order
     * - StringBuilder optimization for efficient string building
     * 
     * Time: O(n), Space: O(n)
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
        
        // Fill buckets based on frequency
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        
        // Build result string from high to low frequency
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
    
    /**
     * Alternative: Heap-based approach for character sorting
     * - More memory efficient for sparse character sets
     * - Better when frequency range is large
     * 
     * Time: O(n + k log k), Space: O(k) where k = unique characters
     */
    public static String frequencySortHeap(String s) {
        // Count frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        // Use max-heap for frequency ordering
        java.util.PriorityQueue<Character> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> frequencyMap.get(b) - frequencyMap.get(a)
        );
        
        maxHeap.addAll(frequencyMap.keySet());
        
        // Build result string
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            int frequency = frequencyMap.get(c);
            for (int i = 0; i < frequency; i++) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Advanced Frequency Analysis
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Top K Frequent Words
     * 
     * Problem: Find k most frequent words with lexicographical tie-breaking
     * LeetCode: https://leetcode.com/problems/top-k-frequent-words/
     * 
     * Approach: HashMap + Custom Comparator Min-Heap
     * - Count word frequencies using HashMap
     * - Use min-heap with custom comparator for tie-breaking
     * - Handle lexicographical ordering for words with same frequency
     * - Reverse extraction to get correct frequency ordering
     * 
     * Time: O(n log k), Space: O(n)
     */
    public static List<String> topKFrequentWords(String[] words, int k) {
        // Count word frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        
        // Min-heap with custom comparator for tie-breaking
        java.util.PriorityQueue<String> minHeap = new java.util.PriorityQueue<>((a, b) -> {
            int freqCompare = frequencyMap.get(a) - frequencyMap.get(b);
            if (freqCompare == 0) {
                return b.compareTo(a); // Reverse lexicographical for min-heap
            }
            return freqCompare;
        });
        
        // Build heap of size k
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
    
    /**
     * Alternative: Sorting approach for top k frequent words
     * - Sort all unique words by frequency and lexicographical order
     * - Take first k words from sorted list
     * - Simpler implementation but potentially less efficient
     * 
     * Time: O(n + m log m), Space: O(m) where m = unique words
     */
    public static List<String> topKFrequentWordsSorting(String[] words, int k) {
        // Count frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        
        // Sort by frequency (desc) then lexicographically (asc)
        List<String> candidates = new ArrayList<>(frequencyMap.keySet());
        candidates.sort((a, b) -> {
            int freqCompare = frequencyMap.get(b) - frequencyMap.get(a);
            return freqCompare == 0 ? a.compareTo(b) : freqCompare;
        });
        
        return candidates.subList(0, k);
    }
    
    /**
     * 🟡 MEDIUM: Maximum Frequency Stack
     * 
     * Problem: Design stack with push/pop operations that pops most frequent element
     * LeetCode: https://leetcode.com/problems/maximum-frequency-stack/
     * 
     * Approach: Multi-level frequency tracking
     * - Maintain frequency count for each element
     * - Keep separate stack for each frequency level
     * - Track maximum frequency seen so far
     * - Push: increment frequency, add to appropriate frequency stack
     * - Pop: remove from max frequency stack, update frequencies
     * 
     * Time: O(1) push/pop, Space: O(n)
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
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Complex Frequency Scenarios
    // ============================================================================
    
    /**
     * 🔴 HARD: Top K Frequent Elements in a Sorted Matrix
     * 
     * Problem: Find k most frequent elements in a matrix where each row is sorted
     * 
     * Approach: Coordinate-based frequency analysis
     * - Traverse entire matrix to count element frequencies
     * - Apply standard top-k frequent algorithm on frequency map
     * - Optimize matrix traversal if needed for large matrices
     * - Consider early termination strategies for sparse data
     * 
     * Time: O(mn + unique_elements * log k), Space: O(unique_elements + k)
     */
    public static int[] topKFrequentInMatrix(int[][] matrix, int k) {
        // Count frequencies from entire matrix
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int[] row : matrix) {
            for (int num : row) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
        }
        
        // Use min-heap for top-k selection
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
        
        // Extract results
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    /**
     * Alternative: Optimized matrix traversal for sorted rows
     * - Leverage sorted property for early termination
     * - Use binary search for frequency counting optimization
     * - Better for matrices with many duplicate consecutive elements
     * 
     * Time: O(mn) worst case, potentially better for sorted data
     */
    public static int[] topKFrequentInMatrixOptimized(int[][] matrix, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Optimized counting for sorted rows
        for (int[] row : matrix) {
            int i = 0;
            while (i < row.length) {
                int current = row[i];
                int count = 1;
                
                // Count consecutive identical elements
                while (i + count < row.length && row[i + count] == current) {
                    count++;
                }
                
                frequencyMap.put(current, frequencyMap.getOrDefault(current, 0) + count);
                i += count;
            }
        }
        
        // Standard top-k selection
        return topKFrequentFromMap(frequencyMap, k);
    }
    
    private static int[] topKFrequentFromMap(Map<Integer, Integer> frequencyMap, int k) {
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
    
    /**
     * 🔴 HARD: Streaming Top K Frequent Tracker
     * 
     * Problem: Maintain top k frequent elements in a data stream
     * 
     * Approach: Dynamic frequency maintenance with heap
     * - Maintain frequency map for all seen elements
     * - Use TreeMap for efficient frequency-based queries
     * - Handle element insertion/removal dynamically
     * - Optimize for frequent queries and updates
     * 
     * Time: O(log n) per operation, Space: O(n)
     */
    public static class StreamingTopKFrequent {
        private Map<Integer, Integer> frequencies;
        private TreeMap<Integer, Set<Integer>> frequencyToElements;
        private int k;
        
        public StreamingTopKFrequent(int k) {
            this.k = k;
            this.frequencies = new HashMap<>();
            this.frequencyToElements = new TreeMap<>(Collections.reverseOrder());
        }
        
        public void addElement(int num) {
            // Remove from old frequency bucket
            if (frequencies.containsKey(num)) {
                int oldFreq = frequencies.get(num);
                frequencyToElements.get(oldFreq).remove(num);
                if (frequencyToElements.get(oldFreq).isEmpty()) {
                    frequencyToElements.remove(oldFreq);
                }
            }
            
            // Update frequency
            int newFreq = frequencies.getOrDefault(num, 0) + 1;
            frequencies.put(num, newFreq);
            
            // Add to new frequency bucket
            frequencyToElements.computeIfAbsent(newFreq, k -> new HashSet<>()).add(num);
        }
        
        public List<Integer> getTopK() {
            List<Integer> result = new ArrayList<>();
            
            for (Map.Entry<Integer, Set<Integer>> entry : frequencyToElements.entrySet()) {
                for (int num : entry.getValue()) {
                    if (result.size() < k) {
                        result.add(num);
                    } else {
                        break;
                    }
                }
                if (result.size() >= k) break;
            }
            
            return result;
        }
        
        public void removeElement(int num) {
            if (!frequencies.containsKey(num)) return;
            
            int freq = frequencies.get(num);
            
            // Remove from frequency bucket
            frequencyToElements.get(freq).remove(num);
            if (frequencyToElements.get(freq).isEmpty()) {
                frequencyToElements.remove(freq);
            }
            
            // Update frequency
            if (freq == 1) {
                frequencies.remove(num);
            } else {
                frequencies.put(num, freq - 1);
                frequencyToElements.computeIfAbsent(freq - 1, k -> new HashSet<>()).add(num);
            }
        }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎯 TOP K FREQUENT ELEMENTS PATTERN - PRACTICE PROBLEMS");
        System.out.println("======================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        // Test Top K Frequent Elements
        int[] testArray = {1, 1, 1, 2, 2, 3};
        int[] topKHeap = topKFrequent(testArray, 2);
        System.out.println("Top 2 frequent elements (heap): " + Arrays.toString(topKHeap)); // [1, 2]
        
        int[] topKBucket = topKFrequentBucketSort(testArray, 2);
        System.out.println("Top 2 frequent elements (bucket): " + Arrays.toString(topKBucket)); // [1, 2]
        
        // Test Sort Characters by Frequency
        String charFreq1 = frequencySort("tree");
        System.out.println("Characters by frequency (bucket): " + charFreq1); // "eert" or "eetr"
        
        String charFreq2 = frequencySortHeap("tree");
        System.out.println("Characters by frequency (heap): " + charFreq2); // "eert" or "eetr"
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        // Test Top K Frequent Words
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> topWords = topKFrequentWords(words, 2);
        System.out.println("Top 2 frequent words: " + topWords); // ["i", "love"]
        
        List<String> topWordsSorting = topKFrequentWordsSorting(words, 2);
        System.out.println("Top 2 frequent words (sorting): " + topWordsSorting); // ["i", "love"]
        
        // Test Maximum Frequency Stack
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println("Pop from freq stack: " + freqStack.pop()); // 5 (most frequent, last occurrence)
        System.out.println("Pop from freq stack: " + freqStack.pop()); // 7 (most frequent after 5)
        System.out.println("Pop from freq stack: " + freqStack.pop()); // 5 (second most frequent)
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        // Test Top K Frequent in Matrix
        int[][] matrix = {{1, 2, 3}, {1, 1, 2}, {3, 3, 3}};
        int[] matrixTopK = topKFrequentInMatrix(matrix, 2);
        System.out.println("Top 2 frequent in matrix: " + Arrays.toString(matrixTopK)); // [3, 1] or [1, 3]
        
        int[] matrixTopKOpt = topKFrequentInMatrixOptimized(matrix, 2);
        System.out.println("Top 2 frequent in matrix (optimized): " + Arrays.toString(matrixTopKOpt)); // [3, 1] or [1, 3]
        
        // Test Streaming Top K Frequent
        StreamingTopKFrequent streamTracker = new StreamingTopKFrequent(3);
        streamTracker.addElement(1);
        streamTracker.addElement(2);
        streamTracker.addElement(1);
        streamTracker.addElement(3);
        streamTracker.addElement(1);
        System.out.println("Streaming top 3: " + streamTracker.getTopK()); // [1, 2, 3] (1 most frequent)
        
        streamTracker.removeElement(1);
        System.out.println("After removing one 1: " + streamTracker.getTopK()); // [1, 2, 3] (1 still most frequent)
        
        System.out.println("\n✅ Key Top K Frequent Principles:");
        System.out.println("1. Count frequencies efficiently with HashMap for O(1) access");
        System.out.println("2. Use min-heap for memory-efficient top-k selection");
        System.out.println("3. Apply bucket sort for optimal O(n) time complexity");
        System.out.println("4. Handle tie-breaking consistently for deterministic results");
        System.out.println("5. Choose algorithm based on data size and memory constraints");
        System.out.println("6. Use specialized structures for streaming frequency analysis");
        System.out.println("7. Optimize string building with StringBuilder for performance");
        System.out.println("8. Leverage sorted properties for matrix optimization");
    }
} 