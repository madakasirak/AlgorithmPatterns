package com.dsalgo.AlgoPatterns.String;

/**
 * ==================================================================================
 *                     REGULAR EXPRESSIONS PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * Regular Expressions (Regex) is a powerful pattern-matching technique used for
 * string validation, pattern matching, and text processing. In algorithmic problems,
 * regex patterns are often implemented using dynamic programming or recursive
 * approaches rather than built-in regex engines. This pattern is essential for
 * problems involving complex string matching with wildcards, repetitions, and
 * character classes.
 * 
 * ==================================================================================
 *                         WHEN TO USE REGULAR EXPRESSIONS
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ String pattern matching with wildcards ('.' for any character)
 * ✅ Repetition patterns ('*' for zero or more occurrences)
 * ✅ String validation against complex rules
 * ✅ Text processing with multiple constraints
 * ✅ Pattern matching with character classes
 * ✅ Problems involving "matching", "validation", "pattern"
 * ✅ Input format validation (emails, phone numbers, etc.)
 * ✅ Complex string parsing requirements
 * ✅ State machine-like string processing
 * ✅ Backtracking pattern matching
 * 
 * 🚨 KEY PHRASES TO LOOK FOR:
 * ✅ "pattern matching", "regex", "regular expression"
 * ✅ "validation", "format checking"
 * ✅ "wildcard", "any character", "zero or more"
 * ✅ "matches pattern", "follows rules"
 * ✅ ".' (any character)", "'*' (zero or more)"
 * ✅ "email validation", "phone validation"
 * ✅ "text processing", "string parsing"
 * 
 * 🚨 RED FLAGS (when NOT the primary solution):
 * ❌ Simple string equality checks
 * ❌ Basic substring search (use KMP or simple search)
 * ❌ Character frequency problems (use HashMap)
 * ❌ Dynamic programming on subsequences (different pattern)
 * 
 * ==================================================================================
 *                        REGULAR EXPRESSIONS VARIATIONS
 * ==================================================================================
 */
public class RegularExpressionsReadingGuide {

    /**
     * ================================================================================
     *                         VARIATION 1: BASIC PATTERN MATCHING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Implement recursive or DP solution for pattern matching
     * 2. Handle '.' wildcard (matches any single character)
     * 3. Handle '*' quantifier (zero or more of preceding character)
     * 4. Base cases: empty string and pattern
     * 
     * USE CASES:
     * - Basic regex matching with '.' and '*'
     * - Simple pattern validation
     * - Wildcard string matching
     * - Text filtering based on patterns
     */
    
    // EXAMPLE: Regular Expression Matching (Recursive)
    public static boolean isMatch(String s, String p) {
        return isMatchHelper(s, p, 0, 0);
    }
    
    private static boolean isMatchHelper(String s, String p, int i, int j) {
        // Base case: pattern exhausted
        if (j >= p.length()) {
            return i >= s.length();
        }
        
        // Check if current characters match
        boolean currentMatch = (i < s.length()) && 
                              (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
        
        // Handle '*' quantifier
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Two choices: use * (zero occurrences) or match current and continue
            return isMatchHelper(s, p, i, j + 2) || 
                   (currentMatch && isMatchHelper(s, p, i + 1, j));
        } else {
            // No '*', must match current character
            return currentMatch && isMatchHelper(s, p, i + 1, j + 1);
        }
    }

    /**
     * ================================================================================
     *                       VARIATION 2: DYNAMIC PROGRAMMING APPROACH
     * ================================================================================
     * 
     * PATTERN:
     * 1. Create 2D DP table for string and pattern indices
     * 2. Fill table bottom-up using recurrence relation
     * 3. Handle edge cases and special characters
     * 4. Optimize space if needed (1D DP)
     * 
     * USE CASES:
     * - Optimized regex matching
     * - Pattern matching with memoization
     * - Large input string processing
     * - When recursion depth is a concern
     */
    
    // EXAMPLE: Regular Expression Matching (DP)
    public static boolean isMatchDP(String s, String p) {
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
                    // Handle '*' quantifier
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
     * ================================================================================
     *                         VARIATION 3: WILDCARD MATCHING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Handle '?' for single character wildcard
     * 2. Handle '*' for any sequence of characters
     * 3. Use greedy or DP approach for optimization
     * 4. Consider both matching and skipping options
     * 
     * USE CASES:
     * - File pattern matching (*.txt, file?.log)
     * - Shell glob patterns
     * - Advanced wildcard search
     * - Flexible string matching
     */
    
    // EXAMPLE: Wildcard Pattern Matching
    public static boolean isWildcardMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case
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
                if (p.charAt(j - 1) == '*') {
                    // '*' can match zero or more characters
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    // '?' matches any single character, or exact match
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[m][n];
    }

    /**
     * ================================================================================
     *                         VARIATION 4: STRING VALIDATION
     * ================================================================================
     * 
     * PATTERN:
     * 1. Define validation rules using regex concepts
     * 2. Implement state machine for complex validation
     * 3. Handle multiple validation criteria
     * 4. Use built-in regex or custom implementation
     * 
     * USE CASES:
     * - Email address validation
     * - Phone number validation
     * - IP address validation
     * - Custom format validation
     */
    
    // EXAMPLE: Email Validation (Simplified)
    public static boolean isValidEmail(String email) {
        if (email == null || email.length() == 0) return false;
        
        // Simple email pattern: localpart@domain
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailPattern);
    }
    
    // EXAMPLE: Phone Number Validation
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null) return false;
        
        // Remove all non-digit characters
        String cleaned = phone.replaceAll("[^0-9]", "");
        
        // Check for valid length (10 or 11 digits)
        return cleaned.length() == 10 || 
               (cleaned.length() == 11 && cleaned.charAt(0) == '1');
    }

    /**
     * ================================================================================
     *                      VARIATION 5: ADVANCED PATTERN MATCHING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Handle complex patterns with multiple rules
     * 2. Implement backtracking for pattern matching
     * 3. Use state machines for complex validation
     * 4. Optimize with memoization and pruning
     * 
     * USE CASES:
     * - Complex text parsing
     * - Advanced pattern recognition
     * - Multi-rule validation systems
     * - Natural language processing patterns
     */
    
    // EXAMPLE: Advanced Pattern Matching with Backtracking
    public static boolean matchPattern(String text, String pattern) {
        return matchPatternHelper(text, pattern, 0, 0, new java.util.HashMap<>());
    }
    
    private static boolean matchPatternHelper(String text, String pattern, 
                                            int i, int j, 
                                            java.util.Map<String, Boolean> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        boolean result;
        
        if (j >= pattern.length()) {
            result = i >= text.length();
        } else if (i >= text.length()) {
            // Check if remaining pattern can match empty string
            result = canMatchEmpty(pattern, j);
        } else {
            char pChar = pattern.charAt(j);
            char tChar = text.charAt(i);
            
            if (pChar == '.' || pChar == tChar) {
                result = matchPatternHelper(text, pattern, i + 1, j + 1, memo);
            } else if (pChar == '*') {
                // '*' matches zero or more of any character
                result = matchPatternHelper(text, pattern, i, j + 1, memo) ||
                        matchPatternHelper(text, pattern, i + 1, j, memo);
            } else {
                result = false;
            }
        }
        
        memo.put(key, result);
        return result;
    }
    
    private static boolean canMatchEmpty(String pattern, int start) {
        for (int i = start; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    /**
     * ================================================================================
     *                        VARIATION 6: STATE MACHINE APPROACH
     * ================================================================================
     * 
     * PATTERN:
     * 1. Model pattern matching as finite state machine
     * 2. Define states and transitions
     * 3. Handle accepting and rejecting states
     * 4. Optimize state transitions
     * 
     * USE CASES:
     * - Complex validation logic
     * - Multi-step pattern matching
     * - Protocol validation
     * - Lexical analysis
     */
    
    // EXAMPLE: Simple State Machine for Number Validation
    public static boolean isValidNumber(String s) {
        if (s == null || s.trim().isEmpty()) return false;
        
        s = s.trim();
        
        // States: START, SIGN, INTEGER, DECIMAL, FRACTION, EXP, EXP_SIGN, EXP_NUM
        enum State {
            START, SIGN, INTEGER, DECIMAL, FRACTION, EXP, EXP_SIGN, EXP_NUM
        }
        
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
        
        // Check final state validity
        return hasNum && 
               (state == State.INTEGER || state == State.FRACTION || 
                (state == State.EXP_NUM && hasExpNum));
    }

    /**
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. EDGE CASES: Not handling empty strings or patterns properly
     * 2. QUANTIFIER HANDLING: Incorrect '*' or '?' logic
     * 3. BACKTRACKING: Missing memoization leading to exponential time
     * 4. STATE TRANSITIONS: Incorrect state machine design
     * 5. CHARACTER MATCHING: Not handling special characters properly
     * 6. GREEDY vs NON-GREEDY: Choosing wrong matching strategy
     * 7. REGEX COMPLEXITY: Overusing regex for simple string operations
     * 
     * 💡 PRO TIPS:
     * 1. Always handle base cases first (empty string/pattern)
     * 2. Use memoization for recursive solutions to avoid TLE
     * 3. Consider both DP and recursive approaches
     * 4. Draw state diagrams for complex validation logic
     * 5. Test with edge cases: "", ".*", "a*", etc.
     * 6. For contests, implement custom regex rather than using built-in
     * 7. Use built-in regex for production validation code
     * 8. Consider performance: DP vs recursion vs state machine
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Valid Palindrome (basic pattern)
     * - Implement strStr() (simple pattern matching)
     * - Valid Parentheses (state validation)
     * - First Unique Character (character patterns)
     * - Detect Capital (case patterns)
     * 
     * 🟡 MEDIUM:
     * - Regular Expression Matching
     * - Wildcard Matching
     * - Valid Number
     * - String to Integer (atoi)
     * - Valid IP Address
     * - Letter Combinations of Phone Number
     * - Group Anagrams (pattern grouping)
     * - Find and Replace Pattern
     * 
     * 🔴 HARD:
     * - Regular Expression Matching (with advanced features)
     * - Wildcard Matching (optimized)
     * - Text Justification
     * - Valid Number (comprehensive)
     * - Parse Lisp Expression
     * - Remove Invalid Parentheses
     * - Basic Calculator III
     * - Expression Add Operators
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Need to match patterns with wildcards? → Regex matching
     * 2. Validating input format? → State machine or regex validation
     * 3. Complex pattern matching rules? → DP approach
     * 4. Simple pattern search? → Use string algorithms instead
     * 5. Multiple validation criteria? → State machine approach
     * 6. Performance critical? → Consider optimized DP or state machine
     * 7. Built-in regex available? → Use for production, implement for contests
     * 
     * COMPLEXITY ANALYSIS:
     * - Recursive: O(2^(m+n)) worst case, O(mn) with memoization
     * - DP: O(mn) time, O(mn) space (can optimize to O(n) space)
     * - State machine: O(n) time, O(1) space for fixed states
     * 
     * ALGORITHM CHOICE:
     * - Small inputs: Recursive with memoization
     * - Large inputs: DP approach
     * - Complex validation: State machine
     * - Simple patterns: Built-in string methods
     * 
     * Remember: Regular expressions are powerful but can be complex. Start with
     * simple cases and build up to handle more complex patterns. Always consider
     * if a simpler string algorithm might be more appropriate!
     */
} 