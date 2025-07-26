package com.dsalgo.AlgoPatterns.String;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/*
 * Regular Expressions (Regex) is a powerful pattern-matching technique used for
 * string validation, pattern matching, and text processing. In algorithmic problems,
 * regex patterns are often implemented using dynamic programming or recursive
 * approaches rather than built-in regex engines.
 * 
 * Apply regular expressions when the problem involves pattern matching or string
 * validation based on specific rules, especially complex patterns or constraints.
 * Regular expressions provide a powerful way to search, validate, and manipulate
 * text efficiently.
 * 
 * Look for problems like "pattern," "validation," "matching," or specific patterns
 * (e.g., email addresses, phone numbers) in problem descriptions. Problems requiring
 * validation of input strings against specific patterns or constraints often suggest
 * the application of regular expressions.
 * 
 * Example:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element 'a'.
 */
public class RegularExpressions {

    // State machine states for number validation
    enum State {
        START, SIGN, INTEGER, DECIMAL, FRACTION, EXP, EXP_SIGN, EXP_NUM
    }

    public static void main(String[] args) {
        // Test Regular Expression problems
        System.out.println("=== REGULAR EXPRESSIONS PROBLEMS TEST ===");
        
        // Test Regular Expression Matching
        String s1 = "aa", p1 = "a*";
        System.out.println("RegEx Match '" + s1 + "' with '" + p1 + "': " + isMatch(s1, p1));
        
        // Test Wildcard Matching
        String s2 = "cb", p2 = "?a";
        System.out.println("Wildcard Match '" + s2 + "' with '" + p2 + "': " + isWildcardMatch(s2, p2));
        
        // Test Valid Number
        String num = "3.14e2";
        System.out.println("Is valid number '" + num + "': " + isNumber(num));
        
        // Test Email Validation
        String email = "test@example.com";
        System.out.println("Is valid email '" + email + "': " + isValidEmail(email));
    }

    // ==================================================================================
    //                       REGULAR EXPRESSIONS PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Regular Expression Matching
     * DIFFICULTY: Hard (but fundamental)
     * PATTERN: Dynamic Programming with Pattern Matching
     * 
     * DESCRIPTION:
     * Given an input string s and a pattern p, implement regular expression 
     * matching with support for '.' and '*' where:
     * - '.' Matches any single character
     * - '*' Matches zero or more of the preceding element
     * 
     * APPROACH:
     * 1. Use dynamic programming with 2D table
     * 2. Handle base cases for empty string and pattern
     * 3. Process '*' quantifier with two choices: use or skip
     * 4. Handle '.' wildcard for any character matching
     * 
     * TIME COMPLEXITY: O(m * n)
     * SPACE COMPLEXITY: O(m * n)
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: empty string matches empty pattern
        dp[m][n] = true;
        
        // Fill DP table from bottom-right to top-left
        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                boolean currentMatch = (i < m) && 
                                      (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
                
                if (j + 1 < n && p.charAt(j + 1) == '*') {
                    // Handle '*' quantifier: zero occurrences OR one+ occurrences
                    dp[i][j] = dp[i][j + 2] || (currentMatch && dp[i + 1][j]);
                } else {
                    // Regular character matching
                    dp[i][j] = currentMatch && dp[i + 1][j + 1];
                }
            }
        }
        
        return dp[0][0];
    }
    
    /**
     * PROBLEM: Regular Expression Matching (Recursive with Memoization)
     * DIFFICULTY: Hard
     * PATTERN: Recursion with Memoization
     * 
     * DESCRIPTION:
     * Alternative recursive implementation with memoization for regex matching.
     * 
     * APPROACH:
     * 1. Use recursion with memoization
     * 2. Handle '*' with two recursive calls
     * 3. Cache results to avoid recomputation
     * 
     * TIME COMPLEXITY: O(m * n)
     * SPACE COMPLEXITY: O(m * n)
     */
    public static boolean isMatchRecursive(String s, String p) {
        return isMatchHelper(s, p, 0, 0, new HashMap<>());
    }
    
    private static boolean isMatchHelper(String s, String p, int i, int j, 
                                       Map<String, Boolean> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        boolean result;
        
        if (j >= p.length()) {
            result = i >= s.length();
        } else {
            boolean currentMatch = (i < s.length()) && 
                                  (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
            
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // Two choices: skip pattern or match current and continue
                result = isMatchHelper(s, p, i, j + 2, memo) || 
                        (currentMatch && isMatchHelper(s, p, i + 1, j, memo));
            } else {
                result = currentMatch && isMatchHelper(s, p, i + 1, j + 1, memo);
            }
        }
        
        memo.put(key, result);
        return result;
    }
    
    /**
     * PROBLEM: Wildcard Matching
     * DIFFICULTY: Hard
     * PATTERN: Dynamic Programming with Wildcard Patterns
     * 
     * DESCRIPTION:
     * Given an input string s and a pattern p, implement wildcard pattern 
     * matching with support for '?' and '*' where:
     * - '?' Matches any single character
     * - '*' Matches any sequence of characters (including empty sequence)
     * 
     * APPROACH:
     * 1. Use 2D DP table for string and pattern indices
     * 2. Handle '*' as matching zero or more characters
     * 3. Handle '?' as matching exactly one character
     * 4. Fill table based on character matching rules
     * 
     * TIME COMPLEXITY: O(m * n)
     * SPACE COMPLEXITY: O(m * n)
     */
    public static boolean isWildcardMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: empty string matches empty pattern
        dp[0][0] = true;
        
        // Handle patterns with '*' at the beginning
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pChar = p.charAt(j - 1);
                char sChar = s.charAt(i - 1);
                
                if (pChar == '*') {
                    // '*' can match zero characters or one or more
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pChar == '?' || pChar == sChar) {
                    // '?' matches any single character, or exact match
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // If no match, dp[i][j] remains false (default)
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * PROBLEM: Wildcard Matching (Optimized Iterative)
     * DIFFICULTY: Hard
     * PATTERN: Greedy with Backtracking
     * 
     * DESCRIPTION:
     * Space-optimized iterative solution for wildcard matching.
     * 
     * APPROACH:
     * 1. Use greedy approach with backtracking
     * 2. Track positions for backtracking when '*' is encountered
     * 3. Match greedily and backtrack when needed
     * 
     * TIME COMPLEXITY: O(m * n) worst case, O(m + n) average
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean isWildcardMatchOptimized(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sBacktrack = -1;
        
        while (sIdx < s.length()) {
            if (pIdx < p.length() && 
                (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                // Characters match, advance both pointers
                sIdx++;
                pIdx++;
            } else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                // Found '*', save positions for backtracking
                starIdx = pIdx;
                sBacktrack = sIdx;
                pIdx++;
            } else if (starIdx != -1) {
                // Backtrack: try matching more characters with '*'
                pIdx = starIdx + 1;
                sBacktrack++;
                sIdx = sBacktrack;
            } else {
                // No match and no '*' to backtrack
                return false;
            }
        }
        
        // Skip remaining '*' in pattern
        while (pIdx < p.length() && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        
        return pIdx == p.length();
    }
    
    /**
     * PROBLEM: Valid Number
     * DIFFICULTY: Hard
     * PATTERN: State Machine Validation
     * 
     * DESCRIPTION:
     * A valid number can be split up into these components (in order):
     * 1. A decimal number or an integer
     * 2. (Optional) An 'e' or 'E', followed by an integer
     * 
     * APPROACH:
     * 1. Implement finite state machine
     * 2. Define states for different number parts
     * 3. Handle transitions based on input characters
     * 4. Check if final state is accepting
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean isNumber(String s) {
        if (s == null || s.trim().isEmpty()) return false;
        
        s = s.trim();
        
        State state = State.START;
        boolean hasNum = false;
        boolean hasExpNum = false;
        
        for (char c : s.toCharArray()) {
            switch (state) {
                case START:
                    if (c == '+' || c == '-') {
                        state = State.SIGN;
                    } else if (Character.isDigit(c)) {
                        state = State.INTEGER;
                        hasNum = true;
                    } else if (c == '.') {
                        state = State.DECIMAL;
                    } else {
                        return false;
                    }
                    break;
                    
                case SIGN:
                    if (Character.isDigit(c)) {
                        state = State.INTEGER;
                        hasNum = true;
                    } else if (c == '.') {
                        state = State.DECIMAL;
                    } else {
                        return false;
                    }
                    break;
                    
                case INTEGER:
                    if (Character.isDigit(c)) {
                        // Stay in INTEGER state
                    } else if (c == '.') {
                        state = State.FRACTION;
                    } else if (c == 'e' || c == 'E') {
                        state = State.EXP;
                    } else {
                        return false;
                    }
                    break;
                    
                case DECIMAL:
                    if (Character.isDigit(c)) {
                        state = State.FRACTION;
                        hasNum = true;
                    } else {
                        return false;
                    }
                    break;
                    
                case FRACTION:
                    if (Character.isDigit(c)) {
                        // Stay in FRACTION state
                    } else if (c == 'e' || c == 'E') {
                        state = State.EXP;
                    } else {
                        return false;
                    }
                    break;
                    
                case EXP:
                    if (c == '+' || c == '-') {
                        state = State.EXP_SIGN;
                    } else if (Character.isDigit(c)) {
                        state = State.EXP_NUM;
                        hasExpNum = true;
                    } else {
                        return false;
                    }
                    break;
                    
                case EXP_SIGN:
                    if (Character.isDigit(c)) {
                        state = State.EXP_NUM;
                        hasExpNum = true;
                    } else {
                        return false;
                    }
                    break;
                    
                case EXP_NUM:
                    if (Character.isDigit(c)) {
                        // Stay in EXP_NUM state
                    } else {
                        return false;
                    }
                    break;
            }
        }
        
        // Check if final state is valid
        return hasNum && 
               (state == State.INTEGER || state == State.FRACTION || 
                (state == State.EXP_NUM && hasExpNum));
    }
    
    /**
     * PROBLEM: Valid Email Address
     * DIFFICULTY: Medium
     * PATTERN: String Validation with Regex
     * 
     * DESCRIPTION:
     * Validate if a given string is a valid email address format.
     * 
     * APPROACH:
     * 1. Use regex pattern for email validation
     * 2. Check for proper local and domain parts
     * 3. Validate character sets and structure
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.length() == 0) return false;
        
        // Comprehensive email pattern
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailPattern, email);
    }
    
    /**
     * PROBLEM: Valid Email Address (Custom Implementation)
     * DIFFICULTY: Medium
     * PATTERN: Manual String Validation
     * 
     * DESCRIPTION:
     * Validate email without using built-in regex.
     * 
     * APPROACH:
     * 1. Check for single '@' character
     * 2. Validate local part (before @)
     * 3. Validate domain part (after @)
     * 4. Check for proper domain extension
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean isValidEmailCustom(String email) {
        if (email == null || email.length() == 0) return false;
        
        int atIndex = email.indexOf('@');
        if (atIndex == -1 || atIndex != email.lastIndexOf('@')) {
            return false; // No @ or multiple @
        }
        
        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1);
        
        return isValidLocalPart(localPart) && isValidDomainPart(domainPart);
    }
    
    private static boolean isValidLocalPart(String local) {
        if (local.length() == 0 || local.length() > 64) return false;
        
        for (char c : local.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && 
                c != '.' && c != '_' && c != '%' && c != '+' && c != '-') {
                return false;
            }
        }
        
        return !local.startsWith(".") && !local.endsWith(".");
    }
    
    private static boolean isValidDomainPart(String domain) {
        if (domain.length() == 0 || domain.length() > 255) return false;
        
        int lastDotIndex = domain.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == domain.length() - 1) {
            return false; // No extension or ends with dot
        }
        
        String extension = domain.substring(lastDotIndex + 1);
        if (extension.length() < 2) return false;
        
        for (char c : domain.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-') {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * PROBLEM: Valid Phone Number
     * DIFFICULTY: Easy
     * PATTERN: Number Format Validation
     * 
     * DESCRIPTION:
     * Validate if a given string represents a valid phone number.
     * 
     * APPROACH:
     * 1. Remove all non-digit characters
     * 2. Check for valid length (10 or 11 digits)
     * 3. Handle country code (optional leading 1)
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null) return false;
        
        // Remove all non-digit characters
        String cleaned = phone.replaceAll("[^0-9]", "");
        
        // Check for valid length
        if (cleaned.length() == 10) {
            return true; // Standard 10-digit US number
        } else if (cleaned.length() == 11) {
            return cleaned.charAt(0) == '1'; // With country code
        }
        
        return false;
    }
    
    /**
     * PROBLEM: IP Address Validation
     * DIFFICULTY: Medium
     * PATTERN: Multi-format Validation
     * 
     * DESCRIPTION:
     * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address,
     * "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not correct.
     * 
     * APPROACH:
     * 1. Try to validate as IPv4 first
     * 2. If not IPv4, try to validate as IPv6
     * 3. Return appropriate result
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static String validIPAddress(String queryIP) {
        if (isValidIPv4(queryIP)) return "IPv4";
        if (isValidIPv6(queryIP)) return "IPv6";
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
     * PROBLEM: Find and Replace Pattern
     * DIFFICULTY: Medium
     * PATTERN: Pattern Matching with Bijection
     * 
     * DESCRIPTION:
     * Given a list of strings words and a string pattern, return a list of
     * words that match the given pattern. A word matches the pattern if there
     * exists a permutation of letters p so that after replacing every letter
     * x in the pattern with p(x), we get the desired word.
     * 
     * APPROACH:
     * 1. For each word, check if it matches the pattern
     * 2. Create bidirectional mapping between pattern and word characters
     * 3. Ensure consistent mapping throughout
     * 
     * TIME COMPLEXITY: O(n * m) where n = words count, m = word length
     * SPACE COMPLEXITY: O(k) where k = unique characters
     */
    public static java.util.List<String> findAndReplacePattern(String[] words, String pattern) {
        java.util.List<String> result = new java.util.ArrayList<>();
        
        for (String word : words) {
            if (matchesPattern(word, pattern)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private static boolean matchesPattern(String word, String pattern) {
        if (word.length() != pattern.length()) return false;
        
        Map<Character, Character> patternToWord = new HashMap<>();
        Map<Character, Character> wordToPattern = new HashMap<>();
        
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            
            if (patternToWord.containsKey(p)) {
                if (patternToWord.get(p) != w) return false;
            } else {
                patternToWord.put(p, w);
            }
            
            if (wordToPattern.containsKey(w)) {
                if (wordToPattern.get(w) != p) return false;
            } else {
                wordToPattern.put(w, p);
            }
        }
        
        return true;
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to print boolean result
     */
    private static void printResult(String description, boolean result) {
        System.out.println(description + ": " + result);
    }
    
    /**
     * Helper method to normalize string for pattern matching
     */
    private static String normalize(String s) {
        return s.toLowerCase().trim();
    }
} 