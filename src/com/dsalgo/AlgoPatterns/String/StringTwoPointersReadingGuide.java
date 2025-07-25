package com.dsalgo.AlgoPatterns.String;

import java.util.*;

/**
 * 🎯 STRING TWO POINTERS PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * The String Two Pointers pattern is a specialized application of the two pointers technique
 * specifically designed for string manipulation problems. It's particularly powerful for problems
 * involving character comparisons, palindromes, string reversal, pattern matching, and
 * optimization within strings using dual index management.
 * 
 * ================================================================================
 * 📋 WHEN TO USE STRING TWO POINTERS
 * ================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Problems involving pairs or triplets of characters in strings
 * ✅ Character comparisons from both ends of string
 * ✅ Palindrome detection and validation
 * ✅ String reversal and transformation
 * ✅ Pattern matching with position constraints
 * ✅ In-place string modifications
 * ✅ Sorted string operations
 * ✅ Character frequency and replacement problems
 * 
 * 🚨 KEY PHRASES TO WATCH FOR:
 * - "pair of characters"
 * - "triplet of characters"
 * - "compare from both ends"
 * - "traverse simultaneously"
 * - "sorted string"
 * - "palindrome"
 * - "reverse"
 * - "in-place modification"
 * - "character replacement"
 * - "string compression"
 * - "validate format"
 * 
 * ================================================================================
 * 🔄 STRING TWO POINTERS VARIATIONS
 * ================================================================================
 * 
 * 1️⃣ OPPOSITE DIRECTION (CONVERGING)
 *    - Start from both ends, move pointers toward each other
 *    - Use cases: Palindrome validation, string reversal, character pairing
 *    - Time: O(n), Space: O(1)
 * 
 * 2️⃣ SAME DIRECTION (FAST & SLOW)
 *    - Two pointers moving at different speeds
 *    - Use cases: String compression, duplicate removal, pattern detection
 *    - Time: O(n), Space: O(1)
 * 
 * 3️⃣ PALINDROME EXPANSION
 *    - Start from center, expand outward to find palindromes
 *    - Use cases: Longest palindromic substring, palindrome counting
 *    - Time: O(n²), Space: O(1)
 * 
 * 4️⃣ PATTERN MATCHING
 *    - Use pointers to match patterns or substrings
 *    - Use cases: String search, pattern validation, format checking
 *    - Time: O(n*m), Space: O(1)
 * 
 * 5️⃣ CHARACTER REPLACEMENT
 *    - Track characters for replacement or transformation
 *    - Use cases: Character replacement, string transformation
 *    - Time: O(n), Space: O(1) to O(k)
 * 
 * 6️⃣ FORMAT VALIDATION
 *    - Validate string formats using positional logic
 *    - Use cases: IP address validation, email validation, number validation
 *    - Time: O(n), Space: O(1)
 * 
 * ================================================================================
 * 🧠 CORE CONCEPTS & TECHNIQUES
 * ================================================================================
 * 
 * CHARACTER COMPARISON STRATEGIES:
 * 📝 Case-insensitive comparison: Character.toLowerCase()
 * 📝 Alphanumeric filtering: Character.isLetterOrDigit()
 * 📝 ASCII value comparison: char - 'a' or char - '0'
 * 📝 Unicode handling: Character.isLetter(), Character.isDigit()
 * 
 * POINTER MOVEMENT PATTERNS:
 * ↔️ Convergent: left++, right-- until left >= right
 * ➡️ Same Direction: slow++, fast++ with different conditions
 * 🎯 Center Expansion: center--, center++ for palindromes
 * 🔍 Pattern Search: advance based on character matching
 * 
 * STRING MODIFICATION TECHNIQUES:
 * 🔄 In-place character swapping
 * 🗜️ Character compression and encoding
 * 🧹 Whitespace and special character handling
 * 🔤 Case conversion and normalization
 * 
 * ================================================================================
 * 🎯 PROBLEM-SOLVING FRAMEWORK
 * ================================================================================
 * 
 * STEP 1: ANALYZE THE STRING PROBLEM
 * - Are we comparing characters from different positions?
 * - Do we need to check palindrome properties?
 * - Is string reversal or transformation involved?
 * - Are there format validation requirements?
 * 
 * STEP 2: CHOOSE STRING TWO POINTER TYPE
 * - Opposite Direction: Palindromes, reversal, character pairing
 * - Same Direction: Compression, duplicate removal, pattern search
 * - Center Expansion: Palindromic substring finding
 * - Pattern Matching: String search, validation
 * 
 * STEP 3: IMPLEMENT POINTER LOGIC
 * ```java
 * // Template for opposite direction string two pointers
 * int left = 0, right = s.length() - 1;
 * 
 * while (left < right) {
 *     // Skip non-relevant characters if needed
 *     while (left < right && !isValid(s.charAt(left))) left++;
 *     while (left < right && !isValid(s.charAt(right))) right--;
 *     
 *     // Compare or process characters
 *     if (s.charAt(left) != s.charAt(right)) {
 *         return false; // or handle mismatch
 *     }
 *     
 *     left++;
 *     right--;
 * }
 * ```
 * 
 * STEP 4: HANDLE STRING-SPECIFIC EDGE CASES
 * - Empty strings
 * - Single character strings
 * - Case sensitivity
 * - Special characters and whitespace
 * - Unicode vs ASCII considerations
 * 
 * ================================================================================
 * 🔍 ADVANCED STRING TWO POINTER PATTERNS
 * ================================================================================
 * 
 * PALINDROME TECHNIQUES:
 * - Odd length: expand around single center
 * - Even length: expand around two adjacent centers
 * - Preprocessing: normalize case and remove non-alphanumeric
 * 
 * STRING COMPRESSION:
 * - Use slow pointer for write position
 * - Use fast pointer for read position
 * - Track character counts and repetitions
 * 
 * PATTERN MATCHING:
 * - KMP-style pointer advancement
 * - Mismatch handling and backtracking
 * - Multiple pattern matching
 * 
 * FORMAT VALIDATION:
 * - Section-by-section validation
 * - Delimiter handling
 * - Range and format checking
 * 
 * ================================================================================
 * 💡 PRO TIPS FOR STRING TWO POINTERS
 * ================================================================================
 * 
 * IMPLEMENTATION TIPS:
 * 1. Handle character filtering and normalization early
 * 2. Use helper methods for character validation
 * 3. Consider case sensitivity requirements
 * 4. Be careful with string indexing and bounds
 * 
 * PERFORMANCE OPTIMIZATION:
 * 1. Prefer character arrays for in-place modifications
 * 2. Use StringBuilder for string building
 * 3. Minimize string creation in loops
 * 4. Cache character conversions when possible
 * 
 * DEBUGGING STRATEGIES:
 * 1. Visualize pointer movement with string examples
 * 2. Check character comparisons step by step
 * 3. Verify edge cases with empty and single-char strings
 * 4. Test with different character sets and cases
 * 
 * COMMON PITFALLS:
 * ❌ Forgetting to handle case sensitivity
 * ❌ Not filtering non-alphanumeric characters properly
 * ❌ Off-by-one errors in string indexing
 * ❌ Not handling empty strings or single characters
 * ❌ Assuming ASCII-only character sets
 * 
 * ================================================================================
 * 📚 PRACTICE PROBLEMS BY DIFFICULTY
 * ================================================================================
 * 
 * 🟢 EASY - FOUNDATIONAL CONCEPTS:
 * 1. Valid Palindrome ⭐
 * 2. Reverse String
 * 3. First Unique Character in String
 * 4. Valid Anagram (Two Pointer approach)
 * 
 * 🟡 MEDIUM - CORE TECHNIQUES:
 * 1. Longest Palindromic Substring ⭐
 * 2. Reverse Words in a String ⭐
 * 3. String Compression ⭐
 * 4. Implement strStr() ⭐
 * 5. Longest Repeating Character Replacement
 * 6. Palindromic Substrings (Count)
 * 7. Remove Duplicates from Sorted String
 * 8. Valid Palindrome II (One deletion allowed)
 * 
 * 🔴 HARD - ADVANCED APPLICATIONS:
 * 1. Validate IP Address ⭐
 * 2. Shortest Palindrome
 * 3. Valid Number (String validation)
 * 4. Regular Expression Matching (Two pointer approach)
 * 5. Minimum Window Palindrome
 * 
 * ⭐ = Must-solve problems for mastery
 * 
 * ================================================================================
 * 🎯 DECISION FRAMEWORK
 * ================================================================================
 * 
 * WHEN YOU SEE A STRING PROBLEM, ASK:
 * 
 * 1. "Does it involve comparing characters from different positions?"
 *    → YES: Consider two pointers
 *    → NO: Look for other patterns
 * 
 * 2. "Are we checking palindrome properties?"
 *    → YES: Use opposite direction or center expansion
 *    → NO: Consider other two pointer variations
 * 
 * 3. "Do we need to modify the string in-place?"
 *    → YES: Use same direction pointers for read/write
 *    → NO: Simple comparison might suffice
 * 
 * 4. "Are there format validation requirements?"
 *    → YES: Use pattern matching with two pointers
 *    → NO: Focus on character comparison logic
 * 
 * 5. "Is pattern searching involved?"
 *    → YES: Use pointer advancement for pattern matching
 *    → NO: Use basic two pointer movement
 * 
 * ================================================================================
 * 🚀 REAL-WORLD APPLICATIONS
 * ================================================================================
 * 
 * TEXT PROCESSING:
 * - Document palindrome detection for content analysis
 * - Text reversal for right-to-left language support
 * - Content compression for storage optimization
 * - Pattern matching for search functionality
 * 
 * DATA VALIDATION:
 * - Format validation for user inputs (IP, email, phone)
 * - Data integrity checks using palindrome properties
 * - Input sanitization with character filtering
 * - Configuration file validation
 * 
 * NATURAL LANGUAGE PROCESSING:
 * - Word boundary detection using two pointers
 * - Text normalization and preprocessing
 * - Language pattern recognition
 * - Syntax validation in parsers
 * 
 * SECURITY & ANALYSIS:
 * - Password strength validation
 * - Pattern-based threat detection
 * - Data anonymization with character replacement
 * - Log file analysis with format validation
 * 
 * ================================================================================
 * 
 * Master the String Two Pointers pattern to efficiently solve complex string
 * manipulation problems with optimal character-level processing! 🎯
 */
public class StringTwoPointersReadingGuide {
    
    /**
     * Example: Template for Opposite Direction String Two Pointers
     * Classic palindrome validation approach.
     */
    public static boolean palindromeTemplate(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Example: Template for Same Direction String Two Pointers
     * String compression approach.
     */
    public static String compressionTemplate(char[] chars) {
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
        
        return new String(chars, 0, write);
    }
    
    /**
     * Example: Template for Center Expansion Palindromes
     * Find longest palindromic substring.
     */
    public static String longestPalindromeTemplate(String s) {
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
     * Example: Template for Pattern Matching
     * String search implementation.
     */
    public static int strStrTemplate(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            
            // Use two pointers to match pattern
            while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            
            if (j == needle.length()) {
                return i; // Found match
            }
        }
        
        return -1; // No match found
    }
    
    public static void main(String[] args) {
        System.out.println("🎯 STRING TWO POINTERS PATTERN - READING GUIDE");
        System.out.println("===============================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Test palindrome template
        System.out.println("1. Palindrome Check:");
        System.out.println("Input: \"A man, a plan, a canal: Panama\"");
        System.out.println("Output: " + palindromeTemplate("A man, a plan, a canal: Panama"));
        
        // Test compression template
        System.out.println("\n2. String Compression:");
        char[] chars = {'a','a','b','b','c','c','c'};
        System.out.println("Input: ['a','a','b','b','c','c','c']");
        System.out.println("Output: " + compressionTemplate(chars));
        
        // Test longest palindrome template
        System.out.println("\n3. Longest Palindromic Substring:");
        System.out.println("Input: \"babad\"");
        System.out.println("Output: \"" + longestPalindromeTemplate("babad") + "\"");
        
        // Test pattern matching template
        System.out.println("\n4. Pattern Matching (strStr):");
        System.out.println("Input: haystack=\"hello\", needle=\"ll\"");
        System.out.println("Output: " + strStrTemplate("hello", "ll"));
        
        System.out.println("\n✅ Key Takeaways:");
        System.out.println("1. Choose pointer direction based on problem type");
        System.out.println("2. Handle character filtering and case sensitivity");
        System.out.println("3. Use center expansion for palindrome problems");
        System.out.println("4. Apply same direction for compression and modification");
        System.out.println("5. Consider pattern matching for string search problems");
    }
} 