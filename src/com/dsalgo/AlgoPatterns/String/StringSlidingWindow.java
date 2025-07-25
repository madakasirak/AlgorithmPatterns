import java.util.*;

/**
 * 🪟 STRING SLIDING WINDOW PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of string-based sliding window problems.
 * String sliding window is a specialized technique for solving contiguous substring problems
 * with character frequency constraints and optimization requirements.
 * 
 * Pattern Focus: String manipulation with window-based optimization
 * Time Complexity: Generally O(n) for single-pass algorithms
 * Space Complexity: O(k) where k is alphabet size or constraint parameter
 */
public class StringSlidingWindow {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Foundational String Window Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Find All Anagrams in a String
     * 
     * Problem: Find all start indices of anagrams of pattern in string s.
     * 
     * Approach: Fixed size sliding window with character frequency matching
     * - Use fixed window of pattern length
     * - Compare character frequencies between pattern and window
     * - Slide window and update frequencies incrementally
     * 
     * Time: O(n), Space: O(1) - constant alphabet size
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        
        int[] patternCount = new int[26];
        int[] windowCount = new int[26];
        
        // Build pattern frequency
        for (char c : p.toCharArray()) {
            patternCount[c - 'a']++;
        }
        
        int windowSize = p.length();
        
        for (int i = 0; i < s.length(); i++) {
            // Add character to window
            windowCount[s.charAt(i) - 'a']++;
            
            // Remove character if window exceeds size
            if (i >= windowSize) {
                windowCount[s.charAt(i - windowSize) - 'a']--;
            }
            
            // Check if current window is anagram
            if (Arrays.equals(patternCount, windowCount)) {
                result.add(i - windowSize + 1);
            }
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Valid Anagram with Sliding Window
     * 
     * Problem: Check if two strings are anagrams using sliding window concept.
     * 
     * Approach: Character frequency comparison
     * - Count character frequencies in both strings
     * - Compare frequency arrays for equality
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] count = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) return false;
        }
        
        return true;
    }
    
    /**
     * 🟢 EASY: Contains Duplicate Within K Distance
     * 
     * Problem: Check if array contains duplicates within distance k.
     * 
     * Approach: Fixed size sliding window with set
     * - Maintain set of at most k elements
     * - Check for duplicates within current window
     * 
     * Time: O(n), Space: O(min(n,k))
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Check if current element exists in window
            if (window.contains(nums[i])) {
                return true;
            }
            
            window.add(nums[i]);
            
            // Maintain window size <= k
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        
        return false;
    }
    
    /**
     * 🟢 EASY: First Unique Character with Window
     * 
     * Problem: Find index of first unique character in string.
     * 
     * Approach: Character frequency counting
     * - Count frequencies in first pass
     * - Find first character with frequency 1
     * 
     * Time: O(n), Space: O(1)
     */
    public static int firstUniqChar(String s) {
        int[] count = new int[26];
        
        // Count character frequencies
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Find first unique character
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Core String Window Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Longest Substring Without Repeating Characters
     * 
     * Problem: Find length of longest substring without repeating characters.
     * 
     * Approach: Variable size sliding window with character set
     * - Expand window by adding characters
     * - Contract window when duplicate found
     * - Track maximum window size
     * 
     * Time: O(n), Space: O(min(m,n)) where m is character set size
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Contract window while duplicate exists
            while (window.contains(rightChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            
            window.add(rightChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * 🟡 MEDIUM: Longest Substring with At Most K Distinct Characters
     * 
     * Problem: Find longest substring with at most K distinct characters.
     * 
     * Approach: Variable size sliding window with character frequency
     * - Expand window until more than K distinct characters
     * - Contract window to maintain at most K distinct
     * 
     * Time: O(n), Space: O(k)
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            // Contract window while more than k distinct characters
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
     * 🟡 MEDIUM: Longest Substring with At Most Two Distinct Characters
     * 
     * Problem: Find longest substring with at most 2 distinct characters.
     * 
     * Approach: Specialized case of K distinct characters
     * - Use same logic as K distinct with k=2
     * 
     * Time: O(n), Space: O(1)
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        return lengthOfLongestSubstringKDistinct(s, 2);
    }
    
    /**
     * 🟡 MEDIUM: Longest Repeating Character Replacement
     * 
     * Problem: Find longest substring after replacing at most k characters.
     * 
     * Approach: Variable window with frequency tracking
     * - Track character with maximum frequency in window
     * - Window is valid if (windowSize - maxFreq) <= k
     * 
     * Time: O(n), Space: O(1)
     */
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxLength = 0, maxFreq = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            count[rightChar - 'A']++;
            maxFreq = Math.max(maxFreq, count[rightChar - 'A']);
            
            // If replacements needed exceed k, contract window
            if (right - left + 1 - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * 🟡 MEDIUM: Permutation in String
     * 
     * Problem: Check if s2 contains permutation of s1.
     * 
     * Approach: Fixed size sliding window with frequency matching
     * - Use sliding window of s1 length on s2
     * - Compare character frequencies
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];
        
        // Count characters in s1
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }
        
        int windowSize = s1.length();
        
        for (int i = 0; i < s2.length(); i++) {
            // Add character to window
            windowCount[s2.charAt(i) - 'a']++;
            
            // Remove character if window exceeds size
            if (i >= windowSize) {
                windowCount[s2.charAt(i - windowSize) - 'a']--;
            }
            
            // Check if current window matches s1
            if (Arrays.equals(s1Count, windowCount)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 🟡 MEDIUM: Character Replacement with Validation
     * 
     * Problem: Find longest valid substring after character replacements.
     * 
     * Approach: Variable window with replacement tracking
     * - Track number of replacements used in current window
     * - Contract window when replacements exceed limit
     * 
     * Time: O(n), Space: O(1)
     */
    public static int longestValidSubstring(String s, int maxReplacements) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, maxLength = 0, maxCharFreq = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            maxCharFreq = Math.max(maxCharFreq, charCount.get(rightChar));
            
            // Current window size minus most frequent char gives replacements needed
            int replacementsNeeded = (right - left + 1) - maxCharFreq;
            
            if (replacementsNeeded > maxReplacements) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * 🟡 MEDIUM: Fruit Into Baskets (At Most 2 Types)
     * 
     * Problem: Pick maximum fruits with at most 2 types of fruits.
     * 
     * Approach: Sliding window with at most 2 distinct elements
     * - Same as longest substring with at most 2 distinct characters
     * 
     * Time: O(n), Space: O(1)
     */
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int left = 0, maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            fruitCount.put(fruits[right], fruitCount.getOrDefault(fruits[right], 0) + 1);
            
            // Contract window while more than 2 fruit types
            while (fruitCount.size() > 2) {
                int leftFruit = fruits[left];
                fruitCount.put(leftFruit, fruitCount.get(leftFruit) - 1);
                if (fruitCount.get(leftFruit) == 0) {
                    fruitCount.remove(leftFruit);
                }
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced String Window Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Minimum Window Substring
     * 
     * Problem: Find minimum window in s that contains all characters of t.
     * 
     * Approach: Variable window with complex constraint tracking
     * - Expand window until all characters of t are included
     * - Contract window while maintaining validity
     * - Track minimum valid window
     * 
     * Time: O(|s| + |t|), Space: O(|s| + |t|)
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        Map<Character, Integer> required = new HashMap<>();
        for (char c : t.toCharArray()) {
            required.put(c, required.getOrDefault(c, 0) + 1);
        }
        
        Map<Character, Integer> windowCounts = new HashMap<>();
        int left = 0, right = 0;
        int formed = 0; // Number of unique chars in window with desired frequency
        int requiredSize = required.size();
        
        // Result: window length, left, right
        int[] result = {-1, 0, 0};
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowCounts.put(rightChar, windowCounts.getOrDefault(rightChar, 0) + 1);
            
            // Check if frequency of current character matches required frequency
            if (required.containsKey(rightChar) && 
                windowCounts.get(rightChar).intValue() == required.get(rightChar).intValue()) {
                formed++;
            }
            
            // Contract window until it's no longer valid
            while (left <= right && formed == requiredSize) {
                char leftChar = s.charAt(left);
                
                // Update result if current window is smaller
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }
                
                windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
                if (required.containsKey(leftChar) && 
                    windowCounts.get(leftChar).intValue() < required.get(leftChar).intValue()) {
                    formed--;
                }
                
                left++;
            }
            
            right++;
        }
        
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }
    
    /**
     * 🔴 HARD: Substring with Concatenation of All Words
     * 
     * Problem: Find all starting indices where substring is concatenation of all words.
     * 
     * Approach: Fixed window with word-level sliding
     * - Use sliding window of total words length
     * - Track word frequencies within window
     * 
     * Time: O(n * m * len) where n = s length, m = words count, len = word length
     * Space: O(m * len)
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        
        if (s.length() < totalLen) return result;
        
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seenWords = new HashMap<>();
            int j = 0;
            
            while (j < words.length) {
                String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                
                if (!wordCount.containsKey(word)) break;
                
                seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);
                
                if (seenWords.get(word) > wordCount.get(word)) break;
                
                j++;
            }
            
            if (j == words.length) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    /**
     * 🔴 HARD: Longest Substring with At Least K Repeating Characters
     * 
     * Problem: Find longest substring where every character appears at least k times.
     * 
     * Approach: Divide and conquer with frequency analysis
     * - Find characters that appear less than k times
     * - Recursively solve for substrings split by these characters
     * 
     * Time: O(n log n) average, O(n²) worst case
     * Space: O(n) for recursion stack
     */
    public static int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                int maxLen = 0;
                for (String substring : s.split(String.valueOf(s.charAt(i)))) {
                    maxLen = Math.max(maxLen, longestSubstring(substring, k));
                }
                return maxLen;
            }
        }
        
        return s.length(); // All characters appear at least k times
    }
    
    /**
     * 🔴 HARD: Subarrays with K Different Integers (String Version)
     * 
     * Problem: Count subarrays with exactly K distinct characters.
     * 
     * Approach: Double sliding window technique
     * - Use "at most K" - "at most K-1" = "exactly K"
     * 
     * Time: O(n), Space: O(k)
     */
    public static int subarraysWithKDistinct(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }
    
    private static int atMostK(String s, int k) {
        if (k == 0) return 0;
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, result = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            result += right - left + 1;
        }
        
        return result;
    }
    
    // ============================================================================
    // 🧪 TEST METHODS
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🪟 STRING SLIDING WINDOW PATTERN - PRACTICE PROBLEMS");
        System.out.println("======================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        System.out.println("\n1. Find All Anagrams:");
        System.out.println("Input: s = \"abab\", p = \"ab\"");
        System.out.println("Output: " + findAnagrams("abab", "ab"));
        
        System.out.println("\n2. Valid Anagram:");
        System.out.println("Input: s = \"anagram\", t = \"nagaram\"");
        System.out.println("Output: " + isAnagram("anagram", "nagaram"));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        System.out.println("\n3. Longest Substring Without Repeating Characters:");
        System.out.println("Input: \"abcabcbb\"");
        System.out.println("Output: " + lengthOfLongestSubstring("abcabcbb"));
        
        System.out.println("\n4. Longest Substring with At Most K Distinct:");
        System.out.println("Input: s = \"eceba\", k = 2");
        System.out.println("Output: " + lengthOfLongestSubstringKDistinct("eceba", 2));
        
        System.out.println("\n5. Character Replacement:");
        System.out.println("Input: s = \"ABAB\", k = 2");
        System.out.println("Output: " + characterReplacement("ABAB", 2));
        
        System.out.println("\n6. Permutation in String:");
        System.out.println("Input: s1 = \"ab\", s2 = \"eidbaooo\"");
        System.out.println("Output: " + checkInclusion("ab", "eidbaooo"));
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        System.out.println("\n7. Minimum Window Substring:");
        System.out.println("Input: s = \"ADOBECODEBANC\", t = \"ABC\"");
        System.out.println("Output: \"" + minWindow("ADOBECODEBANC", "ABC") + "\"");
        
        System.out.println("\n8. Longest Substring with At Least K Repeating:");
        System.out.println("Input: s = \"aaabb\", k = 3");
        System.out.println("Output: " + longestSubstring("aaabb", 3));
        
        System.out.println("\n✅ All String Sliding Window problems implemented!");
        System.out.println("Time Complexities: Mostly O(n) with efficient character tracking");
        System.out.println("Space Complexities: O(k) where k is alphabet size or constraint parameter");
    }
} 