package com.dsalgo.AlgoPatterns.String;

/**
 * ==================================================================================
 *                      STRING MANIPULATION PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * String Manipulation encompasses various techniques for processing, transforming,
 * and analyzing strings. This includes operations like reversing, splitting, joining,
 * parsing, validation, pattern matching, and format conversion. These techniques
 * are fundamental to text processing, input parsing, and data transformation tasks.
 * 
 * ==================================================================================
 *                         WHEN TO USE STRING MANIPULATION
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Operations like reversing, splitting, joining strings
 * ✅ Converting strings to different formats
 * ✅ Parsing structured input (numbers, dates, expressions)
 * ✅ String validation and format checking
 * ✅ Text transformation and cleaning
 * ✅ Pattern matching and substring operations
 * ✅ Character-level processing and analysis
 * ✅ String encoding/decoding problems
 * ✅ Palindrome detection and manipulation
 * ✅ String compression and expansion
 * 
 * 🚨 RED FLAGS (when NOT the primary focus):
 * ❌ Dynamic programming on strings (separate pattern)
 * ❌ Advanced string algorithms (KMP, suffix arrays)
 * ❌ Graph-based string problems
 * ❌ Mathematical string problems requiring number theory
 * 
 * ==================================================================================
 *                         STRING MANIPULATION VARIATIONS
 * ==================================================================================
 */
public class StringManipulationReadingGuide {

    /**
     * ================================================================================
     *                       VARIATION 1: BASIC STRING OPERATIONS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Fundamental operations: reverse, split, join, substring
     * 2. Character-level manipulation and transformation
     * 3. Case conversion and whitespace handling
     * 4. Length and boundary operations
     * 
     * USE CASES:
     * - Reversing strings or substrings
     * - String cleaning and normalization
     * - Basic format transformations
     * - Character frequency analysis
     */
    
    // EXAMPLE: Reverse String
    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
    
    // EXAMPLE: Remove Extra Spaces
    public static String cleanSpaces(String s) {
        return s.trim().replaceAll("\\s+", " ");
    }

    /**
     * ================================================================================
     *                        VARIATION 2: PARSING AND CONVERSION
     * ================================================================================
     * 
     * PATTERN:
     * 1. Extract meaningful data from string representations
     * 2. Handle edge cases and invalid input gracefully
     * 3. Convert between different data formats
     * 4. Implement custom parsing logic
     * 
     * USE CASES:
     * - String to integer conversion (atoi)
     * - Parsing expressions and formulas
     * - Date and time parsing
     * - Custom format parsing
     */
    
    // EXAMPLE: String to Integer (atoi)
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int i = 0, n = s.length();
        
        // Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        
        if (i == n) return 0;
        
        // Handle sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        
        // Convert digits
        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            
            // Check for overflow
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            i++;
        }
        
        return (int) (sign * result);
    }

    /**
     * ================================================================================
     *                         VARIATION 3: PALINDROME OPERATIONS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Check for palindromic properties
     * 2. Expand around centers to find palindromes
     * 3. Use two pointers for validation
     * 4. Handle different types of palindromes
     * 
     * USE CASES:
     * - Palindrome validation
     * - Finding longest palindromic substring
     * - Palindrome partitioning
     * - Creating palindromes with minimum changes
     */
    
    // EXAMPLE: Valid Palindrome
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    // EXAMPLE: Longest Palindromic Substring
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        
        int start = 0, maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes
            int len1 = expandAroundCenter(s, i, i);
            // Check for even-length palindromes
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
        while (left >= 0 && right < s.length() && 
               s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * ================================================================================
     *                       VARIATION 4: STRING ENCODING/DECODING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Transform strings according to specific rules
     * 2. Implement compression and decompression algorithms
     * 3. Handle nested structures and recursion
     * 4. Stack-based parsing for complex patterns
     * 
     * USE CASES:
     * - Run-length encoding
     * - String compression
     * - Decode complex string patterns
     * - URL encoding/decoding
     */
    
    // EXAMPLE: Decode String
    public static String decodeString(String s) {
        java.util.Stack<Integer> countStack = new java.util.Stack<>();
        java.util.Stack<String> stringStack = new java.util.Stack<>();
        
        String currentString = "";
        int currentCount = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                currentCount = currentCount * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(currentCount);
                stringStack.push(currentString);
                currentCount = 0;
                currentString = "";
            } else if (c == ']') {
                int count = countStack.pop();
                String prevString = stringStack.pop();
                
                StringBuilder sb = new StringBuilder(prevString);
                for (int i = 0; i < count; i++) {
                    sb.append(currentString);
                }
                currentString = sb.toString();
            } else {
                currentString += c;
            }
        }
        
        return currentString;
    }

    /**
     * ================================================================================
     *                        VARIATION 5: PATTERN MATCHING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Find substrings or patterns within strings
     * 2. Implement string search algorithms
     * 3. Handle wildcard and regex-like matching
     * 4. Use rolling hash or other optimization techniques
     * 
     * USE CASES:
     * - Substring search (strStr)
     * - Pattern matching with wildcards
     * - Text search and indexing
     * - String similarity algorithms
     */
    
    // EXAMPLE: Implement strStr()
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * ================================================================================
     *                          VARIATION 6: VALIDATION
     * ================================================================================
     * 
     * PATTERN:
     * 1. Check if strings conform to specific formats
     * 2. Validate input according to rules and constraints
     * 3. Handle edge cases and malformed input
     * 4. Use regex or custom validation logic
     * 
     * USE CASES:
     * - Email validation
     * - IP address validation
     * - Phone number validation
     * - Password strength checking
     */
    
    // EXAMPLE: Validate IP Address
    public static String validIPAddress(String IP) {
        if (isValidIPv4(IP)) return "IPv4";
        if (isValidIPv6(IP)) return "IPv6";
        return "Neither";
    }
    
    private static boolean isValidIPv4(String ip) {
        String[] parts = ip.split("\\.", -1);
        if (parts.length != 4) return false;
        
        for (String part : parts) {
            if (part.length() == 0 || part.length() > 3) return false;
            if (part.charAt(0) == '0' && part.length() > 1) return false;
            
            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }
            
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) return false;
        }
        
        return true;
    }
    
    private static boolean isValidIPv6(String ip) {
        String[] parts = ip.split(":", -1);
        if (parts.length != 8) return false;
        
        for (String part : parts) {
            if (part.length() == 0 || part.length() > 4) return false;
            
            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c) && 
                    !(c >= 'a' && c <= 'f') && 
                    !(c >= 'A' && c <= 'F')) {
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * ================================================================================
     *                         VARIATION 7: TRANSFORMATION
     * ================================================================================
     * 
     * PATTERN:
     * 1. Convert strings between different representations
     * 2. Apply mathematical operations to string representations
     * 3. Handle digit manipulation and arithmetic
     * 4. Implement custom transformation rules
     * 
     * USE CASES:
     * - Number to string conversions
     * - Base conversions
     * - String arithmetic
     * - Custom encoding schemes
     */
    
    // EXAMPLE: Reverse Integer
    public static int reverse(int x) {
        long result = 0;
        
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        
        // Check for overflow
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) result;
    }
    
    // EXAMPLE: Count and Say
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        
        String prev = countAndSay(n - 1);
        StringBuilder result = new StringBuilder();
        
        int i = 0;
        while (i < prev.length()) {
            char digit = prev.charAt(i);
            int count = 1;
            
            while (i + 1 < prev.length() && prev.charAt(i + 1) == digit) {
                count++;
                i++;
            }
            
            result.append(count).append(digit);
            i++;
        }
        
        return result.toString();
    }

    /**
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. STRING IMMUTABILITY: Inefficient string concatenation with +
     * 2. INDEX BOUNDS: Off-by-one errors in substring operations
     * 3. CASE SENSITIVITY: Not handling upper/lower case properly
     * 4. WHITESPACE: Ignoring leading/trailing/internal whitespace
     * 5. OVERFLOW: Integer overflow in parsing large numbers
     * 6. EDGE CASES: Empty strings, null inputs, single characters
     * 7. UNICODE: Not considering multi-byte characters
     * 
     * 💡 PRO TIPS:
     * 1. Use StringBuilder for multiple string operations
     * 2. Consider using char arrays for in-place operations
     * 3. Handle null and empty string inputs first
     * 4. Use appropriate regex for complex pattern matching
     * 5. Consider ASCII vs Unicode character sets
     * 6. Use built-in String methods when appropriate
     * 7. Test with edge cases: empty, single char, very long strings
     * 8. For parsing, validate input format thoroughly
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Reverse String
     * - Valid Palindrome
     * - First Unique Character in String
     * - Reverse Words in String III
     * - Detect Capital
     * - Length of Last Word
     * - Roman to Integer
     * - Add Binary
     * 
     * 🟡 MEDIUM:
     * - Reverse Integer
     * - String to Integer (atoi)
     * - Longest Palindromic Substring
     * - Implement strStr()
     * - Count and Say
     * - Decode String
     * - Validate IP Address
     * - Group Anagrams
     * - Longest Substring Without Repeating Characters
     * - Palindromic Substrings
     * 
     * 🔴 HARD:
     * - Text Justification
     * - Valid Number
     * - Regular Expression Matching
     * - Wildcard Matching
     * - Minimum Window Substring
     * - Shortest Palindrome
     * - Basic Calculator
     * - Integer to English Words
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Do I need to reverse or rearrange characters? → Basic operations
     * 2. Converting between formats? → Parsing and conversion
     * 3. Looking for palindromic properties? → Palindrome operations
     * 4. Compressing or expanding strings? → Encoding/decoding
     * 5. Finding patterns or substrings? → Pattern matching
     * 6. Checking format compliance? → Validation
     * 7. Mathematical operations on strings? → Transformation
     * 
     * COMPLEXITY CONSIDERATIONS:
     * - String concatenation: O(n²) with +, O(n) with StringBuilder
     * - Pattern matching: O(nm) naive, O(n+m) with KMP
     * - Palindrome checking: O(n) with two pointers
     * - Parsing: Usually O(n) single pass
     * 
     * SPACE OPTIMIZATION:
     * - Use char arrays for in-place operations when possible
     * - StringBuilder for building strings incrementally
     * - Consider streaming for very large strings
     * 
     * Remember: String manipulation is about understanding the problem requirements
     * and choosing the right combination of basic operations to achieve the goal
     * efficiently and correctly!
     */
} 