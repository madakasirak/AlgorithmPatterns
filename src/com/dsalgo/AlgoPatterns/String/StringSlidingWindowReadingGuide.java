package com.dsalgo.AlgoPatterns.String;

import java.util.*;

/**
 * 🪟 STRING SLIDING WINDOW PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * The String Sliding Window pattern is a specialized application of the sliding window technique
 * specifically designed for string manipulation problems. It's particularly powerful for problems
 * involving contiguous substrings with character frequency constraints, pattern matching, and
 * optimization within strings.
 * 
 * ================================================================================
 * 📋 WHEN TO USE STRING SLIDING WINDOW
 * ================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Problems involving contiguous substrings
 * ✅ Character frequency constraints within windows
 * ✅ Finding optimal substring (longest, shortest, with specific properties)
 * ✅ String pattern matching and validation
 * ✅ Problems with "at most K", "exactly K", "at least K" character constraints
 * ✅ Substring problems that can benefit from expand/contract logic
 * ✅ Character replacement or transformation within windows
 * 
 * 🚨 KEY PHRASES TO WATCH FOR:
 * - "contiguous substring"
 * - "sliding window"
 * - "longest substring with..."
 * - "minimum window containing..."
 * - "at most K distinct characters"
 * - "character frequency"
 * - "repeating characters"
 * - "substring without..."
 * - "window that slides through string"
 * - "replace characters"
 * 
 * ================================================================================
 * 🔄 STRING SLIDING WINDOW VARIATIONS
 * ================================================================================
 * 
 * 1️⃣ FIXED SIZE STRING WINDOW
 *    - Window size remains constant
 *    - Slide one character at a time
 *    - Use cases: Anagram detection, pattern matching
 *    - Time: O(n), Space: O(k) for character tracking
 * 
 * 2️⃣ VARIABLE SIZE STRING WINDOW
 *    - Window expands/contracts based on character constraints
 *    - Use cases: Longest substring with unique chars, K distinct chars
 *    - Time: O(n), Space: O(alphabet size)
 * 
 * 3️⃣ CHARACTER FREQUENCY WINDOW
 *    - Track character frequencies within window
 *    - Use cases: Anagrams, permutations, character replacement
 *    - Time: O(n), Space: O(alphabet size)
 * 
 * 4️⃣ CONSTRAINT-BASED WINDOW
 *    - Window validity based on specific string constraints
 *    - Use cases: At most K distinct, character replacement with limit
 *    - Time: O(n), Space: O(k)
 * 
 * 5️⃣ MINIMUM/MAXIMUM WINDOW
 *    - Find optimal window size with specific properties
 *    - Use cases: Minimum window substring, longest valid substring
 *    - Time: O(n), Space: O(alphabet size)
 * 
 * 6️⃣ MULTI-PATTERN WINDOW
 *    - Multiple patterns or constraints simultaneously
 *    - Use cases: Complex validation, multiple character sets
 *    - Time: O(n), Space: O(multiple pattern sizes)
 * 
 * ================================================================================
 * 🧠 CORE CONCEPTS & TECHNIQUES
 * ================================================================================
 * 
 * CHARACTER TRACKING STRATEGIES:
 * 📊 HashMap<Character, Integer> - For frequency counting
 * 📊 int[] charCount = new int[256] - For ASCII character arrays
 * 📊 HashSet<Character> - For unique character tracking
 * 📊 Boolean arrays - For simple presence/absence tracking
 * 
 * WINDOW MANAGEMENT PATTERNS:
 * 🔄 Expand-Contract: Grow until invalid, shrink until valid
 * 🔄 Fixed-Slide: Maintain size, slide character by character
 * 🔄 Conditional-Resize: Adjust size based on string properties
 * 🔄 Multi-Pass: Separate expansion and contraction phases
 * 
 * OPTIMIZATION TECHNIQUES:
 * ⚡ Early termination when solution found
 * ⚡ Character array instead of HashMap for ASCII
 * ⚡ Running character counts vs recalculation
 * ⚡ Preprocessing for pattern matching
 * 
 * ================================================================================
 * 🎯 PROBLEM-SOLVING FRAMEWORK
 * ================================================================================
 * 
 * STEP 1: ANALYZE THE STRING PROBLEM
 * - What substring properties are we optimizing?
 * - Are there character frequency constraints?
 * - Is the window size fixed or variable?
 * - What defines a "valid" window?
 * 
 * STEP 2: CHOOSE STRING WINDOW TYPE
 * - Fixed size: Pattern matching, anagram detection
 * - Variable size: Longest/shortest with constraints
 * - Frequency-based: Character counting problems
 * - Constraint-based: Replacement or transformation
 * 
 * STEP 3: IMPLEMENT WINDOW LOGIC
 * ```java
 * // Template for variable size string sliding window
 * Map<Character, Integer> windowChars = new HashMap<>();
 * int left = 0, result = 0;
 * 
 * for (int right = 0; right < s.length(); right++) {
 *     char rightChar = s.charAt(right);
 *     windowChars.put(rightChar, windowChars.getOrDefault(rightChar, 0) + 1);
 *     
 *     while (// window invalid condition) {
 *         char leftChar = s.charAt(left);
 *         windowChars.put(leftChar, windowChars.get(leftChar) - 1);
 *         if (windowChars.get(leftChar) == 0) {
 *             windowChars.remove(leftChar);
 *         }
 *         left++;
 *     }
 *     
 *     result = Math.max(result, right - left + 1);
 * }
 * ```
 * 
 * STEP 4: HANDLE STRING-SPECIFIC EDGE CASES
 * - Empty strings
 * - Single character strings
 * - All characters identical
 * - No valid window exists
 * - Case sensitivity considerations
 * 
 * ================================================================================
 * 🔍 ADVANCED STRING WINDOW PATTERNS
 * ================================================================================
 * 
 * DOUBLE SLIDING WINDOW:
 * Use "at most K" - "at most K-1" to find "exactly K"
 * Applied to: Subarrays with exactly K distinct characters
 * 
 * COMPLEMENT WINDOW TECHNIQUE:
 * Find minimum invalid window to get maximum valid window
 * Applied to: Maximum points from string ends
 * 
 * FREQUENCY MATCHING WINDOW:
 * Compare character frequencies between pattern and window
 * Applied to: Anagram finding, permutation checking
 * 
 * REPLACEMENT WINDOW:
 * Track replacements needed to maintain window validity
 * Applied to: Character replacement with K limit
 * 
 * PATTERN VALIDATION WINDOW:
 * Validate multiple patterns or constraints simultaneously
 * Applied to: Complex string validation
 * 
 * ================================================================================
 * 💡 PRO TIPS FOR STRING SLIDING WINDOW
 * ================================================================================
 * 
 * IMPLEMENTATION TIPS:
 * 1. Use appropriate data structure for character tracking
 * 2. Handle character frequency updates carefully
 * 3. Consider case sensitivity requirements
 * 4. Use meaningful variable names for clarity
 * 
 * PERFORMANCE OPTIMIZATION:
 * 1. Prefer arrays over HashMap for ASCII characters
 * 2. Minimize HashMap operations in tight loops
 * 3. Use early termination when possible
 * 4. Cache expensive string operations
 * 
 * DEBUGGING STRATEGIES:
 * 1. Visualize window movement with string examples
 * 2. Check character frequency updates
 * 3. Verify window validity conditions
 * 4. Test with different string patterns
 * 
 * COMMON PITFALLS:
 * ❌ Forgetting to remove characters when window contracts
 * ❌ Not handling zero frequency correctly in HashMap
 * ❌ Off-by-one errors in window calculations
 * ❌ Case sensitivity issues
 * ❌ Not considering Unicode vs ASCII character sets
 * 
 * ================================================================================
 * 📚 PRACTICE PROBLEMS BY DIFFICULTY
 * ================================================================================
 * 
 * 🟢 EASY - FOUNDATIONAL CONCEPTS:
 * 1. Find All Anagrams in a String
 * 2. Valid Anagram with Window
 * 3. Contains Duplicate within K Distance
 * 4. First Unique Character in Sliding Window
 * 
 * 🟡 MEDIUM - CORE TECHNIQUES:
 * 1. Longest Substring Without Repeating Characters ⭐
 * 2. Longest Substring with At Most K Distinct Characters ⭐
 * 3. Longest Substring with At Most Two Distinct Characters
 * 4. Longest Repeating Character Replacement
 * 5. Permutation in String
 * 6. Minimum Window Substring (Basic)
 * 7. Character Replacement with K Operations
 * 8. Longest Substring with Same Letters after Replacement
 * 
 * 🔴 HARD - ADVANCED APPLICATIONS:
 * 1. Minimum Window Substring (Full) ⭐
 * 2. Substring with Concatenation of All Words
 * 3. Minimum Window Subsequence
 * 4. Subarrays with K Different Integers (String variant)
 * 5. Longest Substring with At Least K Repeating Characters
 * 
 * ⭐ = Must-solve problems for mastery
 * 
 * ================================================================================
 * 🎯 DECISION FRAMEWORK
 * ================================================================================
 * 
 * WHEN YOU SEE A STRING PROBLEM, ASK:
 * 
 * 1. "Does it involve contiguous substrings?"
 *    → YES: Consider sliding window
 *    → NO: Look for other patterns
 * 
 * 2. "Are there character frequency constraints?"
 *    → YES: Use frequency-based sliding window
 *    → NO: Consider simple two-pointer approach
 * 
 * 3. "Do we need to optimize substring length/properties?"
 *    → YES: Variable size sliding window
 *    → NO: Fixed size window might suffice
 * 
 * 4. "Are there multiple constraints or patterns?"
 *    → YES: Advanced sliding window with multiple tracking
 *    → NO: Basic sliding window implementation
 * 
 * 5. "Is the window size known in advance?"
 *    → YES: Fixed window with character tracking
 *    → NO: Variable window with expand/contract logic
 * 
 * ================================================================================
 * 🚀 REAL-WORLD APPLICATIONS
 * ================================================================================
 * 
 * TEXT PROCESSING:
 * - Content analysis with character pattern detection
 * - Document similarity using sliding character windows
 * - Text summarization with optimal content windows
 * - Language detection using character frequency patterns
 * 
 * DATA VALIDATION:
 * - Password strength analysis with character variety
 * - Input validation with character set constraints
 * - Data quality checks using pattern matching
 * - Format validation with sliding pattern windows
 * 
 * SEARCH & RECOMMENDATION:
 * - Search query optimization with character matching
 * - Auto-completion with character pattern prediction
 * - Content recommendation using text similarity
 * - Spam detection with character pattern analysis
 * 
 * SECURITY & ANALYSIS:
 * - Malware detection using string pattern analysis
 * - Log analysis with sliding window pattern matching
 * - Intrusion detection using character sequence analysis
 * - Data anonymization with pattern-based replacement
 * 
 * ================================================================================
 * 
 * Master the String Sliding Window pattern to efficiently solve complex string
 * manipulation problems with optimal time and space complexity! 🎯
 */
public class StringSlidingWindowReadingGuide {
    
    /**
     * Example: Template for Variable Size String Sliding Window
     * This template can be adapted for most string sliding window problems.
     */
    public static void stringWindowTemplate() {
        String s = "example string";
        int k = 2; // Example constraint
        
                 Map<Character, Integer> windowChars = new HashMap<Character, Integer>();
         int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Expand window: add right character
            char rightChar = s.charAt(right);
            windowChars.put(rightChar, windowChars.getOrDefault(rightChar, 0) + 1);
            
            // Contract window while invalid
            while (windowChars.size() > k) { // Example condition
                char leftChar = s.charAt(left);
                windowChars.put(leftChar, windowChars.get(leftChar) - 1);
                if (windowChars.get(leftChar) == 0) {
                    windowChars.remove(leftChar);
                }
                left++;
            }
            
            // Update result with current valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        System.out.println("Maximum length: " + maxLength);
    }
    
    /**
     * Example: Fixed Size String Window Template
     * For problems like anagram detection or pattern matching.
     */
    public static void fixedStringWindowTemplate() {
        String s = "example string";
        String pattern = "pattern";
        int windowSize = pattern.length();
        
        Map<Character, Integer> patternChars = new HashMap<Character, Integer>();
        Map<Character, Integer> windowChars = new HashMap<Character, Integer>();
        
        // Build pattern frequency map
        for (char c : pattern.toCharArray()) {
            patternChars.put(c, patternChars.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < s.length(); i++) {
            // Add character to window
            char c = s.charAt(i);
            windowChars.put(c, windowChars.getOrDefault(c, 0) + 1);
            
            // Remove character if window exceeds size
            if (i >= windowSize) {
                char oldChar = s.charAt(i - windowSize);
                windowChars.put(oldChar, windowChars.get(oldChar) - 1);
                if (windowChars.get(oldChar) == 0) {
                    windowChars.remove(oldChar);
                }
            }
            
            // Check if current window matches pattern
            if (windowChars.equals(patternChars)) {
                System.out.println("Match found at index: " + (i - windowSize + 1));
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🪟 STRING SLIDING WINDOW PATTERN - READING GUIDE");
        System.out.println("==================================================");
        
        System.out.println("\n📖 Template Examples:");
        stringWindowTemplate();
        fixedStringWindowTemplate();
        
        System.out.println("\n✅ Key Takeaways:");
        System.out.println("1. Use HashMap for character frequency tracking");
        System.out.println("2. Expand window by adding characters, contract by removing");
        System.out.println("3. Handle zero frequencies properly in HashMap");
        System.out.println("4. Choose fixed vs variable window based on problem requirements");
        System.out.println("5. Consider ASCII arrays for performance optimization");
    }
} 