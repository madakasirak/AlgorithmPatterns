package com.dsalgo.AlgoPatterns.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
 * String Manipulation encompasses various techniques for processing, transforming,
 * and analyzing strings. This includes operations like reversing, splitting, joining,
 * parsing, validation, pattern matching, and format conversion.
 * 
 * Utilize string manipulation techniques when tasked with operations such as reversing,
 * splitting, joining, or converting strings into different formats. These techniques
 * are useful for parsing input, formatting output, or transforming strings according
 * to specific rules.
 * 
 * Look for problems where operations like "manipulate," "convert," "parse," or specific
 * string operations (e.g., reversing, splitting) indicate the need for string
 * manipulation techniques.
 * 
 * Example:
 * Input: s = "hello"
 * Output: "olleh"
 * Explanation: Reverse the string by swapping characters from both ends.
 */
public class StringManipulation {

    public static void main(String[] args) {
        // Test String Manipulation problems
        System.out.println("=== STRING MANIPULATION PROBLEMS TEST ===");
        
        // Test Reverse String
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s1);
        System.out.println("Reversed string: " + Arrays.toString(s1));
        
        // Test String to Integer
        String s2 = "   -42";
        System.out.println("String to int: " + myAtoi(s2));
        
        // Test Longest Palindrome
        String s3 = "babad";
        System.out.println("Longest palindrome: " + longestPalindrome(s3));
        
        // Test Decode String
        String s4 = "3[a2[c]]";
        System.out.println("Decoded string: " + decodeString(s4));
        
        // Test Validate IP
        String ip = "192.168.1.1";
        System.out.println("IP validation: " + validIPAddress(ip));
    }

    // ==================================================================================
    //                        STRING MANIPULATION PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Reverse String
     * DIFFICULTY: Easy
     * PATTERN: Basic String Operations (Two Pointers)
     * 
     * DESCRIPTION:
     * Write a function that reverses a string. The input string is given as an 
     * array of characters s.
     * 
     * APPROACH:
     * 1. Use two pointers from start and end
     * 2. Swap characters and move pointers inward
     * 3. Continue until pointers meet
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
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
    
    /**
     * PROBLEM: Valid Palindrome
     * DIFFICULTY: Easy
     * PATTERN: Palindrome Operations (Two Pointers)
     * 
     * DESCRIPTION:
     * A phrase is a palindrome if, after converting all uppercase letters into 
     * lowercase letters and removing all non-alphanumeric characters, it reads 
     * the same forward and backward.
     * 
     * APPROACH:
     * 1. Use two pointers from start and end
     * 2. Skip non-alphanumeric characters
     * 3. Compare characters in lowercase
     * 4. Move pointers inward until they meet
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
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
    
    /**
     * PROBLEM: First Unique Character in a String
     * DIFFICULTY: Easy
     * PATTERN: Character Frequency Analysis
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
     * SPACE COMPLEXITY: O(1) - limited by alphabet size
     */
    public static int firstUniqChar(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        
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
     * PROBLEM: Length of Last Word
     * DIFFICULTY: Easy
     * PATTERN: Basic String Operations
     * 
     * DESCRIPTION:
     * Given a string s consisting of words and spaces, return the length of the 
     * last word in the string.
     * 
     * APPROACH:
     * 1. Trim trailing spaces
     * 2. Iterate from end to find start of last word
     * 3. Count characters until space or beginning
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        
        // Skip trailing spaces
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        
        int start = end;
        
        // Find start of last word
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        
        return end - start;
    }
    
    /**
     * PROBLEM: Add Binary
     * DIFFICULTY: Easy
     * PATTERN: String Arithmetic
     * 
     * DESCRIPTION:
     * Given two binary strings a and b, return their sum as a binary string.
     * 
     * APPROACH:
     * 1. Use two pointers from end of both strings
     * 2. Add digits with carry handling
     * 3. Build result from right to left
     * 4. Handle remaining carry
     * 
     * TIME COMPLEXITY: O(max(m, n))
     * SPACE COMPLEXITY: O(max(m, n))
     */
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            result.append(sum % 2);
            carry = sum / 2;
        }
        
        return result.reverse().toString();
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Reverse Integer
     * DIFFICULTY: Medium
     * PATTERN: Transformation (Number Manipulation)
     * 
     * DESCRIPTION:
     * Given a signed 32-bit integer x, return x with its digits reversed. If 
     * reversing x causes the value to go outside the signed 32-bit integer 
     * range, then return 0.
     * 
     * APPROACH:
     * 1. Extract digits one by one using modulo and division
     * 2. Build reversed number by multiplying by 10 and adding digit
     * 3. Check for overflow before final assignment
     * 
     * TIME COMPLEXITY: O(log x) - number of digits
     * SPACE COMPLEXITY: O(1)
     */
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
    
    /**
     * PROBLEM: String to Integer (atoi)
     * DIFFICULTY: Medium
     * PATTERN: Parsing and Conversion
     * 
     * DESCRIPTION:
     * Implement the myAtoi(string s) function, which converts a string to a 
     * 32-bit signed integer (similar to C/C++'s atoi function).
     * 
     * APPROACH:
     * 1. Skip leading whitespace
     * 2. Handle optional sign
     * 3. Convert digits while checking for overflow
     * 4. Return result within integer bounds
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
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
     * PROBLEM: Longest Palindromic Substring
     * DIFFICULTY: Medium
     * PATTERN: Palindrome Operations (Expand Around Center)
     * 
     * DESCRIPTION:
     * Given a string s, return the longest palindromic substring in s.
     * 
     * APPROACH:
     * 1. For each character, expand around center to find palindromes
     * 2. Check both odd-length (center at character) and even-length (center between characters)
     * 3. Track the longest palindrome found
     * 
     * TIME COMPLEXITY: O(n²)
     * SPACE COMPLEXITY: O(1)
     */
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
     * PROBLEM: Count and Say
     * DIFFICULTY: Medium
     * PATTERN: Transformation (Recursive Pattern)
     * 
     * DESCRIPTION:
     * The count-and-say sequence is a sequence of digit strings defined by the 
     * recursive formula: countAndSay(1) = "1", countAndSay(n) is the way you 
     * would "say" the digit string from countAndSay(n-1).
     * 
     * APPROACH:
     * 1. Use recursion to build previous term
     * 2. Count consecutive identical digits
     * 3. Build result by appending count + digit
     * 
     * TIME COMPLEXITY: O(4^n) - exponential growth
     * SPACE COMPLEXITY: O(4^n)
     */
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
     * PROBLEM: Decode String
     * DIFFICULTY: Medium
     * PATTERN: String Encoding/Decoding (Stack-based)
     * 
     * DESCRIPTION:
     * Given an encoded string, return its decoded string. The encoding rule is: 
     * k[encoded_string], where the encoded_string inside the square brackets 
     * should be repeated exactly k times.
     * 
     * APPROACH:
     * 1. Use two stacks: one for counts, one for strings
     * 2. When '[' encountered, push current state and reset
     * 3. When ']' encountered, pop and repeat current string
     * 4. Build result incrementally
     * 
     * TIME COMPLEXITY: O(sum of all k * |s|)
     * SPACE COMPLEXITY: O(sum of all k * |s|)
     */
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        
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
     * PROBLEM: Implement strStr()
     * DIFFICULTY: Medium
     * PATTERN: Pattern Matching (Substring Search)
     * 
     * DESCRIPTION:
     * Given two strings needle and haystack, return the index of the first 
     * occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * 
     * APPROACH:
     * 1. Check each possible starting position in haystack
     * 2. For each position, check if needle matches
     * 3. Return first matching position or -1
     * 
     * TIME COMPLEXITY: O(nm) where n = haystack length, m = needle length
     * SPACE COMPLEXITY: O(1)
     */
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
     * PROBLEM: Group Anagrams
     * DIFFICULTY: Medium
     * PATTERN: String Classification (Sorting/Hashing)
     * 
     * DESCRIPTION:
     * Given an array of strings strs, group the anagrams together. You can 
     * return the answer in any order.
     * 
     * APPROACH:
     * 1. Sort characters in each string to create key
     * 2. Group strings with same sorted key
     * 3. Return grouped anagrams
     * 
     * TIME COMPLEXITY: O(n * m log m) where n = number of strings, m = max string length
     * SPACE COMPLEXITY: O(n * m)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(anagramGroups.values());
    }
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Validate IP Address
     * DIFFICULTY: Hard
     * PATTERN: Validation (Format Checking)
     * 
     * DESCRIPTION:
     * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, 
     * "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a 
     * correct IP of any type.
     * 
     * APPROACH:
     * 1. Check if it could be IPv4 by splitting on '.'
     * 2. Check if it could be IPv6 by splitting on ':'
     * 3. Validate each part according to respective rules
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
     * PROBLEM: Text Justification
     * DIFFICULTY: Hard
     * PATTERN: String Formatting (Complex Layout)
     * 
     * DESCRIPTION:
     * Given an array of strings words and a width maxWidth, format the text 
     * such that each line has exactly maxWidth characters and is fully justified.
     * 
     * APPROACH:
     * 1. Group words that fit in each line
     * 2. Distribute spaces evenly between words
     * 3. Handle last line (left-justified) separately
     * 4. Handle single word lines
     * 
     * TIME COMPLEXITY: O(n * maxWidth)
     * SPACE COMPLEXITY: O(maxWidth)
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while (i < words.length) {
            List<String> currentLine = new ArrayList<>();
            int currentLength = 0;
            
            // Collect words for current line
            while (i < words.length && 
                   currentLength + words[i].length() + currentLine.size() <= maxWidth) {
                currentLine.add(words[i]);
                currentLength += words[i].length();
                i++;
            }
            
            // Justify the line
            if (i == words.length || currentLine.size() == 1) {
                // Last line or single word - left justify
                result.add(leftJustify(currentLine, maxWidth));
            } else {
                // Middle lines - full justify
                result.add(fullJustifyLine(currentLine, maxWidth));
            }
        }
        
        return result;
    }
    
    private static String leftJustify(List<String> words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < words.size() - 1) {
                sb.append(" ");
            }
        }
        
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        
        return sb.toString();
    }
    
    private static String fullJustifyLine(List<String> words, int maxWidth) {
        int totalChars = 0;
        for (String word : words) {
            totalChars += word.length();
        }
        
        int totalSpaces = maxWidth - totalChars;
        int gaps = words.size() - 1;
        int spacesPerGap = totalSpaces / gaps;
        int extraSpaces = totalSpaces % gaps;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size() - 1; i++) {
            sb.append(words.get(i));
            for (int j = 0; j < spacesPerGap; j++) {
                sb.append(" ");
            }
            if (i < extraSpaces) {
                sb.append(" ");
            }
        }
        sb.append(words.get(words.size() - 1));
        
        return sb.toString();
    }
    
    /**
     * PROBLEM: Valid Number
     * DIFFICULTY: Hard
     * PATTERN: Validation (Complex Parsing)
     * 
     * DESCRIPTION:
     * A valid number can be split up into these components (in order):
     * 1. A decimal number or an integer.
     * 2. (Optional) An 'e' or 'E', followed by an integer.
     * 
     * APPROACH:
     * 1. Parse the string character by character
     * 2. Track state: sign, digits, decimal point, exponent
     * 3. Validate transitions between states
     * 4. Ensure final state is valid
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean isNumber(String s) {
        boolean seenDigit = false, seenDot = false, seenE = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '.') {
                if (seenDot || seenE) return false;
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                if (seenE || !seenDigit) return false;
                seenE = true;
                seenDigit = false; // Reset for exponent part
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return seenDigit;
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to check if character is alphanumeric
     */
    private static boolean isAlphanumeric(char c) {
        return Character.isLetterOrDigit(c);
    }
    
    /**
     * Helper method to print string array for debugging
     */
    private static void printStringArray(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Helper method to print list of string lists for debugging
     */
    private static void printGroupedStrings(List<List<String>> groups) {
        for (List<String> group : groups) {
            System.out.println(group);
        }
    }
}
