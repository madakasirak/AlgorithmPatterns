package com.dsalgo.AlgoPatterns.String;

import java.util.*;

/**
 * 🎯 STRING TWO POINTERS PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of string-based two pointer problems.
 * String two pointers is a specialized technique for solving character comparison problems,
 * palindrome detection, string reversal, pattern matching, and format validation.
 * 
 * Pattern Focus: String manipulation with dual index management
 * Time Complexity: Generally O(n) for single-pass algorithms, O(n²) for center expansion
 * Space Complexity: O(1) for most problems, O(n) for string building operations
 */
public class StringTwoPointers {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Foundational String Two Pointer Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Valid Palindrome
     * 
     * Problem: Check if string is palindrome considering only alphanumeric characters.
     * 
     * Approach: Opposite direction two pointers
     * - Skip non-alphanumeric characters
     * - Compare characters case-insensitively
     * - Move pointers toward center
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters case-insensitively
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * 🟢 EASY: Reverse String
     * 
     * Problem: Reverse string in-place using character array.
     * 
     * Approach: Opposite direction two pointers with swapping
     * - Start from both ends
     * - Swap characters and move inward
     * 
     * Time: O(n), Space: O(1)
     */
    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            left++;
            right--;
        }
    }
    
    /**
     * 🟢 EASY: Valid Anagram (Two Pointer Approach)
     * 
     * Problem: Check if two strings are anagrams using sorting + two pointers.
     * 
     * Approach: Sort both strings, then compare character by character
     * - Sort both strings to group identical characters
     * - Use two pointers to compare sorted strings
     * 
     * Time: O(n log n), Space: O(n)
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        
        int i = 0, j = 0;
        while (i < sChars.length && j < tChars.length) {
            if (sChars[i] != tChars[j]) {
                return false;
            }
            i++;
            j++;
        }
        
        return true;
    }
    
    /**
     * 🟢 EASY: Remove Duplicates from Sorted String
     * 
     * Problem: Remove duplicate characters from sorted string in-place.
     * 
     * Approach: Same direction two pointers (read/write)
     * - Use slow pointer for writing unique characters
     * - Use fast pointer for reading
     * 
     * Time: O(n), Space: O(1)
     */
    public static int removeDuplicates(char[] chars) {
        if (chars.length == 0) return 0;
        
        int write = 0; // slow pointer for writing
        
        for (int read = 0; read < chars.length; read++) {
            // Write character if it's different from previous
            if (write == 0 || chars[read] != chars[write - 1]) {
                chars[write] = chars[read];
                write++;
            }
        }
        
        return write;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Core String Two Pointer Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Longest Palindromic Substring
     * 
     * Problem: Find the longest palindromic substring in given string.
     * 
     * Approach: Center expansion for each possible center
     * - Check odd length palindromes (single center)
     * - Check even length palindromes (two centers)
     * - Track longest palindrome found
     * 
     * Time: O(n²), Space: O(1)
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd length palindromes
            int len1 = expandAroundCenter(s, i, i);
            // Check for even length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    /**
     * 🟡 MEDIUM: Reverse Words in a String
     * 
     * Problem: Reverse the order of words in a string.
     * 
     * Approach: Multiple two pointer operations
     * - Trim spaces and split into words
     * - Reverse the order of words
     * - Handle multiple spaces between words
     * 
     * Time: O(n), Space: O(n)
     */
    public static String reverseWords(String s) {
        // Split by spaces and filter empty strings
        String[] words = s.trim().split("\\s+");
        
        // Reverse the array of words using two pointers
        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }
        
        return String.join(" ", words);
    }
    
    /**
     * 🟡 MEDIUM: String Compression
     * 
     * Problem: Compress string by replacing consecutive characters with count.
     * 
     * Approach: Same direction two pointers for read/write
     * - Use read pointer to count consecutive characters
     * - Use write pointer to place compressed result
     * - Handle counts greater than 9 (multi-digit)
     * 
     * Time: O(n), Space: O(1)
     */
    public static int compress(char[] chars) {
        int write = 0; // slow pointer for writing
        int read = 0;  // fast pointer for reading
        
        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;
            
            // Count consecutive identical characters
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }
            
            // Write character
            chars[write++] = currentChar;
            
            // Write count if greater than 1
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char c : countStr.toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        
        return write;
    }
    
    /**
     * 🟡 MEDIUM: Implement strStr()
     * 
     * Problem: Find first occurrence of needle in haystack.
     * 
     * Approach: Pattern matching with two pointers
     * - Outer loop for haystack positions
     * - Inner two pointers to match needle
     * - Reset and continue on mismatch
     * 
     * Time: O(n*m), Space: O(1)
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            
            // Use two pointers to match pattern
            while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            
            if (j == needle.length()) {
                return i; // Found complete match
            }
        }
        
        return -1; // No match found
    }
    
    /**
     * 🟡 MEDIUM: Palindromic Substrings Count
     * 
     * Problem: Count number of palindromic substrings in string.
     * 
     * Approach: Center expansion for each possible center
     * - Count palindromes with odd length (single center)
     * - Count palindromes with even length (two centers)
     * - Sum all palindromic substrings
     * 
     * Time: O(n²), Space: O(1)
     */
    public static int countSubstrings(String s) {
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Count odd length palindromes
            count += countPalindromes(s, i, i);
            // Count even length palindromes
            count += countPalindromes(s, i, i + 1);
        }
        
        return count;
    }
    
    private static int countPalindromes(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
    
    /**
     * 🟡 MEDIUM: Valid Palindrome II
     * 
     * Problem: Check if string can be palindrome after deleting at most one character.
     * 
     * Approach: Two pointers with mismatch handling
     * - Use standard two pointer palindrome check
     * - On first mismatch, try skipping left or right character
     * - Check if either option results in palindrome
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try skipping left character or right character
                return isPalindromeRange(s, left + 1, right) || 
                       isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true; // Already a palindrome
    }
    
    private static boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * 🟡 MEDIUM: Longest Repeating Character Replacement
     * 
     * Problem: Find longest substring after replacing at most k characters.
     * 
     * Approach: Sliding window with character frequency tracking
     * - Use two pointers to maintain window
     * - Track character frequencies in current window
     * - Window is valid if (windowSize - maxFreq) <= k
     * 
     * Time: O(n), Space: O(1)
     */
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxCount = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            count[rightChar - 'A']++;
            maxCount = Math.max(maxCount, count[rightChar - 'A']);
            
            // If replacements needed exceed k, shrink window
            if (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced String Two Pointer Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Validate IP Address
     * 
     * Problem: Validate if string is valid IPv4 or IPv6 address.
     * 
     * Approach: Format validation with section-by-section checking
     * - Split by appropriate delimiter
     * - Validate each section using specific rules
     * - Handle edge cases and format requirements
     * 
     * Time: O(n), Space: O(1)
     */
    public static String validIPAddress(String IP) {
        if (isValidIPv4(IP)) {
            return "IPv4";
        } else if (isValidIPv6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }
    
    private static boolean isValidIPv4(String ip) {
        String[] parts = ip.split("\\.", -1);
        if (parts.length != 4) return false;
        
        for (String part : parts) {
            if (!isValidIPv4Part(part)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValidIPv4Part(String part) {
        if (part.length() == 0 || part.length() > 3) return false;
        if (part.length() > 1 && part.charAt(0) == '0') return false; // No leading zeros
        
        for (char c : part.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        
        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }
    
    private static boolean isValidIPv6(String ip) {
        String[] parts = ip.split(":", -1);
        if (parts.length != 8) return false;
        
        for (String part : parts) {
            if (!isValidIPv6Part(part)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValidIPv6Part(String part) {
        if (part.length() == 0 || part.length() > 4) return false;
        
        for (char c : part.toCharArray()) {
            if (!Character.isDigit(c) && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 🔴 HARD: Shortest Palindrome
     * 
     * Problem: Find shortest palindrome by adding characters to front.
     * 
     * Approach: Find longest palindromic prefix using two pointers
     * - Use KMP-like approach to find longest prefix that's also suffix
     * - Add minimum characters to make entire string palindrome
     * 
     * Time: O(n), Space: O(n)
     */
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        
        // Create string: s + "#" + reverse(s)
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        
        // Compute KMP failure function
        int[] lps = computeLPS(combined);
        
        // Length of longest palindromic prefix
        int palindromeLength = lps[combined.length() - 1];
        
        // Add characters from end to make palindrome
        String toAdd = rev.substring(0, s.length() - palindromeLength);
        return toAdd + s;
    }
    
    private static int[] computeLPS(String s) {
        int[] lps = new int[s.length()];
        int len = 0;
        int i = 1;
        
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    /**
     * 🔴 HARD: Valid Number
     * 
     * Problem: Validate if string represents a valid number.
     * 
     * Approach: State machine with character-by-character validation
     * - Track different states (sign, digits, decimal, exponent)
     * - Use pointers to validate each section
     * - Handle edge cases and format requirements
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0) return false;
        
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                numberSeen = true;
                numberAfterE = true;
            } else if (c == '.') {
                if (eSeen || pointSeen) return false;
                pointSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (eSeen || !numberSeen) return false;
                numberAfterE = false;
                eSeen = true;
            } else if (c == '-' || c == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return numberSeen && numberAfterE;
    }
    
    /**
     * 🔴 HARD: Minimum Window Palindrome
     * 
     * Problem: Find minimum window that can be made palindrome with at most k changes.
     * 
     * Approach: Sliding window with palindrome validation
     * - Use sliding window to find potential palindromes
     * - For each window, check if it can be made palindrome with k changes
     * - Track minimum window that satisfies condition
     * 
     * Time: O(n²), Space: O(1)
     */
    public static int minWindowPalindrome(String s, int k) {
        int n = s.length();
        
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                if (canMakePalindrome(s, i, i + len - 1, k)) {
                    return len;
                }
            }
        }
        
        return n;
    }
    
    private static boolean canMakePalindrome(String s, int left, int right, int k) {
        int changes = 0;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                changes++;
                if (changes > k) return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // ============================================================================
    // 🧪 TEST METHODS
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎯 STRING TWO POINTERS PATTERN - PRACTICE PROBLEMS");
        System.out.println("===================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        System.out.println("\n1. Valid Palindrome:");
        System.out.println("Input: \"A man, a plan, a canal: Panama\"");
        System.out.println("Output: " + isPalindrome("A man, a plan, a canal: Panama"));
        
        System.out.println("\n2. Reverse String:");
        char[] testChars = {'h','e','l','l','o'};
        System.out.println("Input: " + Arrays.toString(testChars));
        reverseString(testChars);
        System.out.println("Output: " + Arrays.toString(testChars));
        
        System.out.println("\n3. Valid Anagram:");
        System.out.println("Input: s = \"anagram\", t = \"nagaram\"");
        System.out.println("Output: " + isAnagram("anagram", "nagaram"));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        System.out.println("\n4. Longest Palindromic Substring:");
        System.out.println("Input: \"babad\"");
        System.out.println("Output: \"" + longestPalindrome("babad") + "\"");
        
        System.out.println("\n5. Reverse Words in String:");
        System.out.println("Input: \"the sky is blue\"");
        System.out.println("Output: \"" + reverseWords("the sky is blue") + "\"");
        
        System.out.println("\n6. String Compression:");
        char[] compressChars = {'a','a','b','b','c','c','c'};
        System.out.println("Input: " + Arrays.toString(compressChars));
        int newLength = compress(compressChars);
        System.out.println("Output: " + Arrays.toString(Arrays.copyOf(compressChars, newLength)));
        
        System.out.println("\n7. Implement strStr:");
        System.out.println("Input: haystack=\"hello\", needle=\"ll\"");
        System.out.println("Output: " + strStr("hello", "ll"));
        
        System.out.println("\n8. Palindromic Substrings Count:");
        System.out.println("Input: \"abc\"");
        System.out.println("Output: " + countSubstrings("abc"));
        
        System.out.println("\n9. Character Replacement:");
        System.out.println("Input: s = \"ABAB\", k = 2");
        System.out.println("Output: " + characterReplacement("ABAB", 2));
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        System.out.println("\n10. Validate IP Address:");
        System.out.println("Input: \"192.168.1.1\"");
        System.out.println("Output: " + validIPAddress("192.168.1.1"));
        
        System.out.println("\n11. Shortest Palindrome:");
        System.out.println("Input: \"aacecaaa\"");
        System.out.println("Output: \"" + shortestPalindrome("aacecaaa") + "\"");
        
        System.out.println("\n12. Valid Number:");
        System.out.println("Input: \"0\"");
        System.out.println("Output: " + isNumber("0"));
        
        System.out.println("\n✅ All String Two Pointer problems implemented!");
        System.out.println("Time Complexities: O(n) for most, O(n²) for center expansion");
        System.out.println("Space Complexities: O(1) for comparisons, O(n) for string building");
    }
} 