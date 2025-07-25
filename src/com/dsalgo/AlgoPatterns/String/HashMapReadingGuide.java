package com.dsalgo.AlgoPatterns.String;

/**
 * ==================================================================================
 *                         HASHMAP PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * HashMap is a fundamental data structure that provides O(1) average time complexity
 * for insertion, deletion, and lookup operations. It's particularly powerful for
 * problems involving frequency counting, grouping elements, caching results, and
 * checking existence of elements. HashMaps transform many O(n²) problems into O(n)
 * solutions by providing instant access to stored information.
 * 
 * ==================================================================================
 *                            WHEN TO USE HASHMAPS
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Frequency counting of characters, numbers, or objects
 * ✅ Grouping elements based on properties or patterns
 * ✅ Checking existence of elements efficiently
 * ✅ Caching computed results to avoid recomputation
 * ✅ Mapping relationships between different data types
 * ✅ Finding pairs, complements, or matching elements
 * ✅ Implementing LRU cache or memoization
 * ✅ String/array problems involving "count", "frequency", "group"
 * ✅ Problems requiring fast lookups and insertions
 * ✅ Tracking visited states in algorithms
 * 
 * 🚨 KEY PHRASES TO LOOK FOR:
 * ✅ "count occurrences", "frequency"
 * ✅ "group by", "categorize", "classify"
 * ✅ "check if exists", "contains"
 * ✅ "find pairs", "two sum", "complement"
 * ✅ "anagram", "permutation"
 * ✅ "unique", "duplicate", "distinct"
 * ✅ "cache", "memoize", "store results"
 * 
 * 🚨 RED FLAGS (when NOT the primary solution):
 * ❌ Need to maintain sorted order (consider TreeMap)
 * ❌ Range queries (consider segment trees, prefix sums)
 * ❌ Need to access elements by index (use arrays/lists)
 * ❌ Memory is extremely constrained
 * 
 * ==================================================================================
 *                             HASHMAP VARIATIONS
 * ==================================================================================
 */
public class HashMapReadingGuide {

    /**
     * ================================================================================
     *                        VARIATION 1: FREQUENCY COUNTING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Iterate through collection
     * 2. For each element, increment its count in HashMap
     * 3. Use getOrDefault() or computeIfAbsent() for clean code
     * 4. Process results based on frequency requirements
     * 
     * USE CASES:
     * - Character frequency in strings
     * - Finding most/least frequent elements
     * - Checking if frequencies match certain conditions
     * - Anagram detection and grouping
     */
    
    // EXAMPLE: Character Frequency Count
    public static java.util.Map<Character, Integer> getCharFrequency(String s) {
        java.util.Map<Character, Integer> freqMap = new java.util.HashMap<>();
        
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        return freqMap;
    }
    
    // EXAMPLE: First Unique Character
    public static int firstUniqChar(String s) {
        java.util.Map<Character, Integer> charCount = new java.util.HashMap<>();
        
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
     * ================================================================================
     *                         VARIATION 2: GROUPING BY PROPERTY
     * ================================================================================
     * 
     * PATTERN:
     * 1. Define a key function that extracts grouping property
     * 2. Use HashMap where key represents the group
     * 3. Value is typically a List of elements in that group
     * 4. Use computeIfAbsent() to handle group creation
     * 
     * USE CASES:
     * - Group anagrams by sorted characters
     * - Group numbers by sum of digits
     * - Group strings by length or pattern
     * - Classify objects by properties
     */
    
    // EXAMPLE: Group Anagrams
    public static java.util.List<java.util.List<String>> groupAnagrams(String[] strs) {
        java.util.Map<String, java.util.List<String>> anagramGroups = new java.util.HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            java.util.Arrays.sort(chars);
            String key = new String(chars);
            
            anagramGroups.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(str);
        }
        
        return new java.util.ArrayList<>(anagramGroups.values());
    }
    
    // EXAMPLE: Group Numbers by Sum of Digits
    public static java.util.Map<Integer, java.util.List<Integer>> groupByDigitSum(int[] nums) {
        java.util.Map<Integer, java.util.List<Integer>> groups = new java.util.HashMap<>();
        
        for (int num : nums) {
            int digitSum = getDigitSum(num);
            groups.computeIfAbsent(digitSum, k -> new java.util.ArrayList<>()).add(num);
        }
        
        return groups;
    }
    
    private static int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    /**
     * ================================================================================
     *                        VARIATION 3: EXISTENCE CHECKING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Store elements in HashMap/HashSet for O(1) lookup
     * 2. Check existence using containsKey() or contains()
     * 3. Often combined with other operations like counting
     * 4. Useful for avoiding nested loops
     * 
     * USE CASES:
     * - Two Sum problem variations
     * - Finding complements or pairs
     * - Checking if array contains duplicates
     * - Validating conditions efficiently
     */
    
    // EXAMPLE: Two Sum
    public static int[] twoSum(int[] nums, int target) {
        java.util.Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            
            numToIndex.put(nums[i], i);
        }
        
        return new int[]{-1, -1};
    }
    
    // EXAMPLE: Contains Duplicate
    public static boolean containsDuplicate(int[] nums) {
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }

    /**
     * ================================================================================
     *                          VARIATION 4: CACHING/MEMOIZATION
     * ================================================================================
     * 
     * PATTERN:
     * 1. Use HashMap to store computed results
     * 2. Check cache before performing expensive computation
     * 3. Store result in cache for future use
     * 4. Often used in recursive algorithms (dynamic programming)
     * 
     * USE CASES:
     * - Fibonacci with memoization
     * - Path counting problems
     * - Expensive function call caching
     * - Dynamic programming optimizations
     */
    
    // EXAMPLE: Fibonacci with Memoization
    public static long fibonacci(int n, java.util.Map<Integer, Long> memo) {
        if (n <= 1) return n;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        long result = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    /**
     * ================================================================================
     *                         VARIATION 5: MAPPING RELATIONSHIPS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Create bidirectional or unidirectional mappings
     * 2. Map between different data types or representations
     * 3. Use for translation, conversion, or lookup tables
     * 4. Often involves nested HashMaps for complex relationships
     * 
     * USE CASES:
     * - Character to frequency mappings
     * - ID to object mappings
     * - Graph adjacency lists
     * - Translation tables
     */
    
    // EXAMPLE: Graph Adjacency List
    public static java.util.Map<Integer, java.util.List<Integer>> buildGraph(int[][] edges) {
        java.util.Map<Integer, java.util.List<Integer>> graph = new java.util.HashMap<>();
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            
            graph.computeIfAbsent(u, k -> new java.util.ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new java.util.ArrayList<>()).add(u);
        }
        
        return graph;
    }

    /**
     * ================================================================================
     *                      VARIATION 6: SLIDING WINDOW WITH HASHMAP
     * ================================================================================
     * 
     * PATTERN:
     * 1. Combine sliding window technique with HashMap
     * 2. Use HashMap to track frequency within current window
     * 3. Expand/contract window based on HashMap state
     * 4. Particularly useful for substring problems
     * 
     * USE CASES:
     * - Longest substring with k distinct characters
     * - Minimum window substring
     * - Permutation in string
     * - Substring with given character frequency
     */
    
    // EXAMPLE: Longest Substring with At Most K Distinct Characters
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        
        java.util.Map<Character, Integer> charCount = new java.util.HashMap<>();
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            // Contract window if too many distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    /**
     * ================================================================================
     *                        VARIATION 7: ADVANCED HASHMAP PATTERNS
     * ================================================================================
     */
    
    // PREFIX SUM WITH HASHMAP - For subarray sum problems
    public static int subarraySum(int[] nums, int k) {
        java.util.Map<Integer, Integer> prefixSumCount = new java.util.HashMap<>();
        prefixSumCount.put(0, 1); // Empty subarray
        
        int count = 0, prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    // UNION-FIND WITH HASHMAP - For grouping with path compression
    static class UnionFind {
        private java.util.Map<Integer, Integer> parent;
        private java.util.Map<Integer, Integer> rank;
        
        public UnionFind() {
            parent = new java.util.HashMap<>();
            rank = new java.util.HashMap<>();
        }
        
        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                rank.put(x, 0);
                return x;
            }
            
            if (parent.get(x) != x) {
                parent.put(x, find(parent.get(x))); // Path compression
            }
            
            return parent.get(x);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                int rankX = rank.get(rootX);
                int rankY = rank.get(rootY);
                
                if (rankX < rankY) {
                    parent.put(rootX, rootY);
                } else if (rankX > rankY) {
                    parent.put(rootY, rootX);
                } else {
                    parent.put(rootY, rootX);
                    rank.put(rootX, rankX + 1);
                }
            }
        }
    }

    /**
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. NULL POINTER EXCEPTIONS: Not checking if key exists before operations
     * 2. CONCURRENT MODIFICATION: Modifying HashMap while iterating
     * 3. INCORRECT FREQUENCY UPDATES: Not handling zero counts properly
     * 4. KEY MUTABILITY: Using mutable objects as keys
     * 5. HASH COLLISIONS: Poor hashCode() implementation in custom objects
     * 6. MEMORY LEAKS: Not removing unnecessary entries
     * 7. DEFAULT VALUES: Forgetting to use getOrDefault() or computeIfAbsent()
     * 
     * 💡 PRO TIPS:
     * 1. Use getOrDefault(key, 0) for frequency counting
     * 2. Use computeIfAbsent() for complex default value creation
     * 3. Use computeIfPresent() for conditional updates
     * 4. Consider LinkedHashMap for insertion order preservation
     * 5. Consider TreeMap for sorted key iteration
     * 6. Use proper equals() and hashCode() for custom key objects
     * 7. Initialize HashMap with appropriate capacity for performance
     * 8. Use entrySet() instead of keySet() when you need both key and value
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Two Sum
     * - Valid Anagram
     * - First Unique Character in a String
     * - Contains Duplicate
     * - Contains Duplicate II
     * - Jewels and Stones
     * - Ransom Note
     * - Word Pattern
     * 
     * 🟡 MEDIUM:
     * - Group Anagrams
     * - Top K Frequent Elements
     * - Subarray Sum Equals K
     * - 4Sum II
     * - Longest Substring Without Repeating Characters
     * - Find All Anagrams in String
     * - Minimum Window Substring
     * - Group Shifted Strings
     * - Valid Sudoku
     * - Isomorphic Strings
     * 
     * 🔴 HARD:
     * - LRU Cache
     * - LFU Cache
     * - All O`one Data Structure
     * - Design Twitter
     * - Alien Dictionary
     * - Substring with Concatenation of All Words
     * - Count of Smaller Numbers After Self
     * - Insert Delete GetRandom O(1) - Duplicates allowed
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Do I need to count frequencies? → HashMap<Element, Integer>
     * 2. Do I need to group elements? → HashMap<Key, List<Element>>
     * 3. Do I need fast lookups? → HashMap or HashSet
     * 4. Do I need to cache results? → HashMap for memoization
     * 5. Do I need to find pairs/complements? → HashMap for O(1) lookups
     * 6. Do I need to track visited states? → HashSet or HashMap
     * 7. Do I need ordered iteration? → LinkedHashMap or TreeMap
     * 
     * COMPLEXITY ANALYSIS:
     * - Average case: O(1) for get, put, remove operations
     * - Worst case: O(n) when many hash collisions occur
     * - Space: O(n) where n is number of unique elements
     * 
     * WHEN TO USE ALTERNATIVES:
     * - TreeMap: When you need sorted keys
     * - LinkedHashMap: When you need insertion/access order
     * - ConcurrentHashMap: For thread-safe operations
     * - Arrays: When keys are small integers (0 to 255 for characters)
     * 
     * Remember: HashMap transforms many O(n²) problems into O(n) solutions
     * by providing instant access to stored information. The key is identifying
     * what to store and how to use it efficiently!
     */
} 