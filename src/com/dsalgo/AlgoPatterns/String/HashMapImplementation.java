package com.dsalgo.AlgoPatterns.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * HashMap is a fundamental data structure that provides O(1) average time complexity
 * for insertion, deletion, and lookup operations. It's particularly powerful for
 * problems involving frequency counting, grouping elements, and checking existence.
 * 
 * Employ hashmaps when the problem involves frequency counting, grouping characters
 * based on some property, or checking for the existence of certain characters in
 * the string. Hashmaps offer efficient storage and retrieval of characters by keys.
 * 
 * Phrases like "count," "frequency," "group," or "exist" suggest the potential use
 * of hashmaps in the problem. Problems requiring counting occurrences, grouping
 * similar characters, or checking for the presence of specific characters often
 * indicate the application of hashmaps.
 * 
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Explanation: Group anagrams using HashMap with sorted string as key.
 */
public class HashMapImplementation {

    public static void main(String[] args) {
        // Test HashMap problems
        System.out.println("=== HASHMAP PROBLEMS TEST ===");
        
        // Test Two Sum
        int[] nums1 = {2, 7, 11, 15};
        int[] result1 = twoSum(nums1, 9);
        System.out.println("Two Sum result: " + Arrays.toString(result1));
        
        // Test Group Anagrams
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> anagramGroups = groupAnagrams(strs);
        System.out.println("Anagram groups: " + anagramGroups);
        
        // Test First Unique Character
        String s = "leetcode";
        System.out.println("First unique char index: " + firstUniqChar(s));
        
        // Test Valid Anagram
        String s1 = "anagram", s2 = "nagaram";
        System.out.println("Is valid anagram: " + isAnagram(s1, s2));
        
        // Test Top K Frequent
        int[] nums2 = {1, 1, 1, 2, 2, 3};
        int[] topK = topKFrequent(nums2, 2);
        System.out.println("Top 2 frequent: " + Arrays.toString(topK));
    }

    // ==================================================================================
    //                          HASHMAP PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Two Sum
     * DIFFICULTY: Easy
     * PATTERN: Existence Checking with HashMap
     * 
     * DESCRIPTION:
     * Given an array of integers nums and an integer target, return indices of 
     * the two numbers such that they add up to target.
     * 
     * APPROACH:
     * 1. Use HashMap to store number and its index
     * 2. For each number, check if complement exists in map
     * 3. If found, return indices; otherwise, add current number to map
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            
            numToIndex.put(nums[i], i);
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * PROBLEM: Valid Anagram
     * DIFFICULTY: Easy
     * PATTERN: Frequency Counting
     * 
     * DESCRIPTION:
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * An Anagram is a word or phrase formed by rearranging the letters of a different 
     * word or phrase, typically using all the original letters exactly once.
     * 
     * APPROACH:
     * 1. Count frequency of each character in both strings
     * 2. Compare the frequency maps
     * 3. Return true if maps are equal
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(k) where k is number of unique characters
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> charCount = new java.util.HashMap<>();
        
        // Count characters in s
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Decrease count for characters in t
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c)) return false;
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) == 0) {
                charCount.remove(c);
            }
        }
        
        return charCount.isEmpty();
    }
    
    /**
     * PROBLEM: First Unique Character in a String
     * DIFFICULTY: Easy
     * PATTERN: Frequency Counting
     * 
     * DESCRIPTION:
     * Given a string s, find the first non-repeating character in it and return 
     * its index. If it does not exist, return -1.
     * 
     * APPROACH:
     * 1. Count frequency of each character
     * 2. Iterate through string to find first character with frequency 1
     * 3. Return its index or -1 if not found
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(k) where k is number of unique characters
     */
    public static int firstUniqChar(String s) {
        Map<Character, Integer> charCount = new java.util.HashMap<>();
        
        // Count frequencies
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Find first unique character
        for (int i = 0; i < s.length(); i++) {
            if (charCount.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * PROBLEM: Contains Duplicate
     * DIFFICULTY: Easy
     * PATTERN: Existence Checking
     * 
     * DESCRIPTION:
     * Given an integer array nums, return true if any value appears at least 
     * twice in the array, and return false if every element is distinct.
     * 
     * APPROACH:
     * 1. Use HashSet to track seen elements
     * 2. For each element, check if already seen
     * 3. If seen, return true; otherwise, add to set
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
    
    /**
     * PROBLEM: Jewels and Stones
     * DIFFICULTY: Easy
     * PATTERN: Existence Checking
     * 
     * DESCRIPTION:
     * You're given strings jewels representing the types of stones that are jewels, 
     * and stones representing the stones you have. Each character in stones is a 
     * type of stone you have. You want to know how many of the stones you have are also jewels.
     * 
     * APPROACH:
     * 1. Store all jewel types in HashSet for O(1) lookup
     * 2. Count stones that are also jewels
     * 
     * TIME COMPLEXITY: O(j + s) where j = jewels length, s = stones length
     * SPACE COMPLEXITY: O(j)
     */
    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
        
        for (char jewel : jewels.toCharArray()) {
            jewelSet.add(jewel);
        }
        
        int count = 0;
        for (char stone : stones.toCharArray()) {
            if (jewelSet.contains(stone)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Word Pattern
     * DIFFICULTY: Easy
     * PATTERN: Bidirectional Mapping
     * 
     * DESCRIPTION:
     * Given a pattern and a string s, find if s follows the same pattern.
     * Here follow means a full match, such that there is a bijection between 
     * a letter in pattern and a non-empty word in s.
     * 
     * APPROACH:
     * 1. Create mapping from pattern character to word
     * 2. Create reverse mapping from word to pattern character
     * 3. Check consistency of both mappings
     * 
     * TIME COMPLEXITY: O(n + m) where n = pattern length, m = total chars in s
     * SPACE COMPLEXITY: O(w) where w = number of unique words
     */
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        
        Map<Character, String> charToWord = new java.util.HashMap<>();
        Map<String, Character> wordToChar = new java.util.HashMap<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) return false;
            } else {
                charToWord.put(c, word);
            }
            
            if (wordToChar.containsKey(word)) {
                if (!wordToChar.get(word).equals(c)) return false;
            } else {
                wordToChar.put(word, c);
            }
        }
        
        return true;
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Group Anagrams
     * DIFFICULTY: Medium
     * PATTERN: Grouping by Property
     * 
     * DESCRIPTION:
     * Given an array of strings strs, group the anagrams together. You can 
     * return the answer in any order.
     * 
     * APPROACH:
     * 1. Sort characters in each string to create a key
     * 2. Group strings with the same sorted key
     * 3. Return grouped anagrams
     * 
     * TIME COMPLEXITY: O(n * m log m) where n = number of strings, m = max string length
     * SPACE COMPLEXITY: O(n * m)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new java.util.HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(anagramGroups.values());
    }
    
    /**
     * PROBLEM: Top K Frequent Elements
     * DIFFICULTY: Medium
     * PATTERN: Frequency Counting + Sorting
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * You may return the answer in any order.
     * 
     * APPROACH:
     * 1. Count frequency of each element
     * 2. Sort by frequency (or use heap for optimization)
     * 3. Return top k elements
     * 
     * TIME COMPLEXITY: O(n log n) with sorting, O(n log k) with heap
     * SPACE COMPLEXITY: O(n)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new java.util.HashMap<>();
        
        // Count frequencies
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Use heap to find top k frequent elements
        java.util.PriorityQueue<Map.Entry<Integer, Integer>> heap = 
            new java.util.PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll().getKey();
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Subarray Sum Equals K
     * DIFFICULTY: Medium
     * PATTERN: Prefix Sum with HashMap
     * 
     * DESCRIPTION:
     * Given an array of integers nums and an integer k, return the total number 
     * of continuous subarrays whose sum equals to k.
     * 
     * APPROACH:
     * 1. Use HashMap to store prefix sum frequencies
     * 2. For each position, check if (currentSum - k) exists in map
     * 3. This represents subarrays ending at current position with sum k
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new java.util.HashMap<>();
        prefixSumCount.put(0, 1); // Empty subarray has sum 0
        
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if (prefixSum - k) exists
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            
            // Update frequency of current prefix sum
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * PROBLEM: 4Sum II
     * DIFFICULTY: Medium
     * PATTERN: HashMap for Complement Finding
     * 
     * DESCRIPTION:
     * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, 
     * return the number of tuples (i, j, k, l) such that:
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * 
     * APPROACH:
     * 1. Compute all possible sums from first two arrays
     * 2. For each sum from last two arrays, check if complement exists
     * 3. Count all valid combinations
     * 
     * TIME COMPLEXITY: O(n²)
     * SPACE COMPLEXITY: O(n²)
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumCount = new java.util.HashMap<>();
        
        // Count all possible sums from first two arrays
        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
            }
        }
        
        int count = 0;
        
        // Check complements from last two arrays
        for (int c : nums3) {
            for (int d : nums4) {
                int target = -(c + d);
                if (sumCount.containsKey(target)) {
                    count += sumCount.get(target);
                }
            }
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Find All Anagrams in String
     * DIFFICULTY: Medium
     * PATTERN: Sliding Window with HashMap
     * 
     * DESCRIPTION:
     * Given two strings s and p, return an array of all the start indices of 
     * p's anagrams in s.
     * 
     * APPROACH:
     * 1. Use sliding window of size p.length()
     * 2. Maintain character frequency of current window
     * 3. Compare with target frequency at each position
     * 
     * TIME COMPLEXITY: O(s + p)
     * SPACE COMPLEXITY: O(p)
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        
        Map<Character, Integer> pCount = new java.util.HashMap<>();
        Map<Character, Integer> windowCount = new java.util.HashMap<>();
        
        // Count characters in p
        for (char c : p.toCharArray()) {
            pCount.put(c, pCount.getOrDefault(c, 0) + 1);
        }
        
        int windowSize = p.length();
        
        // Initialize first window
        for (int i = 0; i < windowSize; i++) {
            char c = s.charAt(i);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
        }
        
        if (windowCount.equals(pCount)) {
            result.add(0);
        }
        
        // Slide window
        for (int i = windowSize; i < s.length(); i++) {
            // Remove leftmost character
            char leftChar = s.charAt(i - windowSize);
            windowCount.put(leftChar, windowCount.get(leftChar) - 1);
            if (windowCount.get(leftChar) == 0) {
                windowCount.remove(leftChar);
            }
            
            // Add rightmost character
            char rightChar = s.charAt(i);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            // Check if current window is anagram
            if (windowCount.equals(pCount)) {
                result.add(i - windowSize + 1);
            }
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Valid Sudoku
     * DIFFICULTY: Medium
     * PATTERN: Multiple HashSets for Validation
     * 
     * DESCRIPTION:
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need 
     * to be validated according to the following rules:
     * - Each row must contain the digits 1-9 without repetition.
     * - Each column must contain the digits 1-9 without repetition.
     * - Each of the nine 3 x 3 sub-boxes must contain the digits 1-9 without repetition.
     * 
     * APPROACH:
     * 1. Use separate HashSets for rows, columns, and boxes
     * 2. For each cell, check if digit already exists in respective sets
     * 3. Add digit to sets if valid
     * 
     * TIME COMPLEXITY: O(1) - fixed 9x9 board
     * SPACE COMPLEXITY: O(1) - fixed number of sets
     */
    public static boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char digit = board[i][j];
                if (digit != '.') {
                    String row = digit + " in row " + i;
                    String col = digit + " in col " + j;
                    String box = digit + " in box " + (i/3) + "-" + (j/3);
                    
                    if (!seen.add(row) || !seen.add(col) || !seen.add(box)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: LRU Cache
     * DIFFICULTY: Hard
     * PATTERN: HashMap + Doubly Linked List
     * 
     * DESCRIPTION:
     * Design a data structure that follows the constraints of a Least Recently 
     * Used (LRU) cache.
     * 
     * APPROACH:
     * 1. Use HashMap for O(1) access to nodes
     * 2. Use doubly linked list to maintain access order
     * 3. Move accessed nodes to head, remove from tail when capacity exceeded
     * 
     * TIME COMPLEXITY: O(1) for get and put operations
     * SPACE COMPLEXITY: O(capacity)
     */
    static class LRUCache {
        class Node {
            int key, value;
            Node prev, next;
            
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        
        private final int capacity;
        private final Map<Integer, Node> cache;
        private final Node head, tail;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new java.util.HashMap<>();
            
            // Create dummy head and tail nodes
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) return -1;
            
            // Move to head (most recently used)
            moveToHead(node);
            return node.value;
        }
        
        public void put(int key, int value) {
            Node node = cache.get(key);
            
            if (node != null) {
                // Update existing node
                node.value = value;
                moveToHead(node);
            } else {
                // Add new node
                Node newNode = new Node(key, value);
                
                if (cache.size() >= capacity) {
                    // Remove least recently used
                    Node last = removeTail();
                    cache.remove(last.key);
                }
                
                cache.put(key, newNode);
                addToHead(newNode);
            }
        }
        
        private void addToHead(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
        
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }
        
        private Node removeTail() {
            Node last = tail.prev;
            removeNode(last);
            return last;
        }
    }
    
    /**
     * PROBLEM: All O`one Data Structure
     * DIFFICULTY: Hard
     * PATTERN: HashMap + Doubly Linked List with Buckets
     * 
     * DESCRIPTION:
     * Design a data structure to store the strings' count with the ability to 
     * return the strings with minimum and maximum counts.
     * 
     * APPROACH:
     * 1. Use HashMap to map strings to their count buckets
     * 2. Use doubly linked list of buckets ordered by count
     * 3. Each bucket contains a set of strings with same count
     * 
     * TIME COMPLEXITY: O(1) for all operations
     * SPACE COMPLEXITY: O(n) where n is number of strings
     */
    static class AllOne {
        class Bucket {
            int count;
            Set<String> keys;
            Bucket prev, next;
            
            Bucket(int count) {
                this.count = count;
                this.keys = new HashSet<>();
            }
        }
        
        private final Map<String, Bucket> keyToBucket;
        private final Bucket head, tail;
        
        public AllOne() {
            keyToBucket = new java.util.HashMap<>();
            head = new Bucket(0);
            tail = new Bucket(0);
            head.next = tail;
            tail.prev = head;
        }
        
        public void inc(String key) {
            if (keyToBucket.containsKey(key)) {
                changeKey(key, 1);
            } else {
                if (head.next.count != 1) {
                    addBucketAfter(new Bucket(1), head);
                }
                head.next.keys.add(key);
                keyToBucket.put(key, head.next);
            }
        }
        
        public void dec(String key) {
            if (keyToBucket.containsKey(key)) {
                Bucket bucket = keyToBucket.get(key);
                if (bucket.count == 1) {
                    keyToBucket.remove(key);
                } else {
                    changeKey(key, -1);
                }
                bucket.keys.remove(key);
                if (bucket.keys.isEmpty()) {
                    removeBucket(bucket);
                }
            }
        }
        
        public String getMaxKey() {
            return tail.prev == head ? "" : tail.prev.keys.iterator().next();
        }
        
        public String getMinKey() {
            return head.next == tail ? "" : head.next.keys.iterator().next();
        }
        
        private void changeKey(String key, int offset) {
            Bucket bucket = keyToBucket.get(key);
            int newCount = bucket.count + offset;
            
            Bucket newBucket;
            if (offset == 1) {
                newBucket = bucket.next.count == newCount ? bucket.next : 
                           addBucketAfter(new Bucket(newCount), bucket);
            } else {
                newBucket = bucket.prev.count == newCount ? bucket.prev : 
                           addBucketAfter(new Bucket(newCount), bucket.prev);
            }
            
            newBucket.keys.add(key);
            keyToBucket.put(key, newBucket);
        }
        
        private Bucket addBucketAfter(Bucket newBucket, Bucket prevBucket) {
            newBucket.prev = prevBucket;
            newBucket.next = prevBucket.next;
            prevBucket.next.prev = newBucket;
            prevBucket.next = newBucket;
            return newBucket;
        }
        
        private void removeBucket(Bucket bucket) {
            bucket.prev.next = bucket.next;
            bucket.next.prev = bucket.prev;
        }
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to print frequency map
     */
    private static void printFrequencyMap(Map<Character, Integer> freqMap) {
        System.out.println("Frequency Map: " + freqMap);
    }
    
    /**
     * Helper method to print grouped lists
     */
    private static void printGroupedLists(List<List<String>> groups) {
        for (List<String> group : groups) {
            System.out.println(group);
        }
    }
} 