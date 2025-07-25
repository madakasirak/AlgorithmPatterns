package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 🧮 EXPRESSION EVALUATION PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS EXPRESSION EVALUATION?
 * ============================================================================
 * 
 * Expression Evaluation involves parsing and computing arithmetic, logical, or
 * string expressions using systematic approaches. Stack-based techniques are
 * fundamental for handling operator precedence, parentheses, nested structures,
 * and various expression formats. This pattern transforms complex expression
 * parsing into manageable, step-by-step computation processes.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. PARSING: Break down expressions into manageable components
 * 2. PRECEDENCE: Handle operator precedence and associativity correctly
 * 3. NESTING: Manage nested structures (parentheses, brackets, braces)
 * 4. EVALUATION: Compute results systematically using stack operations
 * 
 * 🧮 EXPRESSION METAPHOR:
 * Think of expression evaluation as a "mathematical translator":
 * - Parse expressions like reading a sentence word by word
 * - Stack acts as temporary memory for partial results
 * - Operator precedence determines order of operations
 * - Parentheses create nested sub-expressions to solve first
 * 
 * ============================================================================
 * 🎯 WHEN TO USE EXPRESSION EVALUATION
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Arithmetic expression parsing and computation
 * - Logical expression evaluation with precedence
 * - String expression decoding and expansion
 * - Path simplification and navigation
 * - Mathematical formula evaluation
 * - Calculator implementations
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Evaluate expression" or "calculate result"
 * - "Basic calculator" or "arithmetic operations"
 * - "Parse expression" or "postfix notation"
 * - "Simplify path" or "canonical path"
 * - "Decode string" or "expand expression"
 * - "Operator precedence" or "parentheses handling"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple single operations (use direct calculation)
 * - Complex mathematical functions (use specialized libraries)
 * - Real-time computation (use optimized parsers)
 * - Very large expressions (consider streaming evaluation)
 * 
 * ============================================================================
 * 🔄 EXPRESSION EVALUATION VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ INFIX EXPRESSION EVALUATION
 * - Standard mathematical notation (a + b * c)
 * - Handle operator precedence and associativity
 * - Use operator and operand stacks
 * - Support parentheses for grouping
 * 
 * 2️⃣ POSTFIX EXPRESSION EVALUATION
 * - Reverse Polish Notation (a b c * +)
 * - No parentheses needed, natural stack evaluation
 * - Process left to right with single stack
 * - Simpler to evaluate than infix expressions
 * 
 * 3️⃣ RECURSIVE DESCENT PARSING
 * - Parse expressions using recursive functions
 * - Handle nested structures naturally
 * - Each operator precedence level has its own function
 * - Clean separation of parsing logic
 * 
 * 4️⃣ STRING EXPRESSION DECODING
 * - Decode nested string patterns (k[string])
 * - Handle multiple levels of nesting
 * - Use stack to track nested contexts
 * - Expand strings according to repetition counts
 * 
 * 5️⃣ PATH SIMPLIFICATION
 * - Simplify filesystem paths (/a/./b/../c)
 * - Handle special path components (., ..)
 * - Use stack to track directory navigation
 * - Produce canonical path representation
 * 
 * 6️⃣ GRAPH-BASED EVALUATION
 * - Evaluate expressions with variable dependencies
 * - Build dependency graph for evaluation order
 * - Handle division by zero and cycle detection
 * - Support complex relationship queries
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 OPERATOR PRECEDENCE TABLE:
 * ```
 * Level 1 (Highest): (), [] - Parentheses, brackets
 * Level 2: *, /, % - Multiplication, division, modulo
 * Level 3: +, - - Addition, subtraction
 * Level 4 (Lowest): =, !=, <, > - Comparison operators
 * ```
 * 
 * 🔹 SHUNTING YARD ALGORITHM:
 * ```
 * 1. Scan expression from left to right
 * 2. Numbers: push to output
 * 3. Operators: handle precedence and push to operator stack
 * 4. Left parenthesis: push to operator stack
 * 5. Right parenthesis: pop operators until left parenthesis
 * 6. End: pop all remaining operators
 * ```
 * 
 * 🔹 POSTFIX EVALUATION:
 * ```
 * 1. Scan postfix expression from left to right
 * 2. Operand: push to stack
 * 3. Operator: pop two operands, compute, push result
 * 4. End: stack top contains final result
 * ```
 * 
 * 🔹 RECURSIVE DESCENT STRUCTURE:
 * ```
 * expression → term (('+' | '-') term)*
 * term → factor (('*' | '/') factor)*
 * factor → number | '(' expression ')'
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY EXPRESSION TYPE
 * - What format is the expression in? (infix, postfix, custom)
 * - What operators and precedence rules apply?
 * - Are there special symbols or nested structures?
 * 
 * STEP 2: CHOOSE EVALUATION STRATEGY
 * - Direct stack evaluation for postfix expressions
 * - Two-stack approach for infix expressions
 * - Recursive descent for complex nested structures
 * 
 * STEP 3: HANDLE PARSING DETAILS
 * - How to extract numbers and operators?
 * - How to handle whitespace and formatting?
 * - What are the edge cases and error conditions?
 * 
 * STEP 4: IMPLEMENT COMPUTATION LOGIC
 * - How to perform each operation safely?
 * - How to handle division by zero and overflow?
 * - How to maintain precision and data types?
 * 
 * STEP 5: OPTIMIZE FOR REQUIREMENTS
 * - Memory usage vs. computation speed trade-offs
 * - Error handling and validation requirements
 * - Support for additional operators or functions
 * 
 * ============================================================================
 * 🎨 EXPRESSION EVALUATION TEMPLATES
 * ============================================================================
 */
public class ExpressionEvaluationReadingGuide {
    
    /**
     * 🧮 BASIC CALCULATOR TEMPLATE (Infix without parentheses)
     * Evaluate expressions with +, -, *, / operators
     */
    public static int basicCalculatorTemplate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char operator = '+'; // Start with addition
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0'); // Build multi-digit number
            }
            
            // Process operator or end of string
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                switch (operator) {
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': stack.push(stack.pop() * num); break;
                    case '/': stack.push(stack.pop() / num); break;
                }
                operator = c;
                num = 0;
            }
        }
        
        // Sum all values in stack
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    /**
     * 🔢 POSTFIX EVALUATION TEMPLATE
     * Evaluate expressions in postfix notation
     */
    public static int postfixEvaluationTemplate(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();
                int result = performOperation(a, b, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || 
               "*".equals(token) || "/".equals(token);
    }
    
    private static int performOperation(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    /**
     * 🏗️ RECURSIVE DESCENT TEMPLATE
     * Parse and evaluate expressions with precedence
     */
    public static class RecursiveDescentTemplate {
        private String expression;
        private int index;
        
        public int evaluate(String expr) {
            expression = expr.replaceAll(" ", "");
            index = 0;
            return parseExpression();
        }
        
        private int parseExpression() {
            int result = parseTerm();
            
            while (index < expression.length()) {
                char op = expression.charAt(index);
                if (op == '+' || op == '-') {
                    index++;
                    int term = parseTerm();
                    result = (op == '+') ? result + term : result - term;
                } else {
                    break;
                }
            }
            
            return result;
        }
        
        private int parseTerm() {
            int result = parseFactor();
            
            while (index < expression.length()) {
                char op = expression.charAt(index);
                if (op == '*' || op == '/') {
                    index++;
                    int factor = parseFactor();
                    result = (op == '*') ? result * factor : result / factor;
                } else {
                    break;
                }
            }
            
            return result;
        }
        
        private int parseFactor() {
            if (expression.charAt(index) == '(') {
                index++; // Skip '('
                int result = parseExpression();
                index++; // Skip ')'
                return result;
            }
            
            // Parse number
            int start = index;
            if (expression.charAt(index) == '-') {
                index++;
            }
            while (index < expression.length() && 
                   Character.isDigit(expression.charAt(index))) {
                index++;
            }
            
            return Integer.parseInt(expression.substring(start, index));
        }
    }
    
    /**
     * 🔤 STRING DECODING TEMPLATE
     * Decode nested string patterns like k[string]
     */
    public static String stringDecodingTemplate(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder current = new StringBuilder();
        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(count);
                stringStack.push(current);
                count = 0;
                current = new StringBuilder();
            } else if (c == ']') {
                int repeatCount = countStack.pop();
                StringBuilder temp = stringStack.pop();
                for (int i = 0; i < repeatCount; i++) {
                    temp.append(current);
                }
                current = temp;
            } else {
                current.append(c);
            }
        }
        
        return current.toString();
    }
    
    /**
     * 📂 PATH SIMPLIFICATION TEMPLATE
     * Simplify filesystem paths
     */
    public static String pathSimplificationTemplate(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        
        for (String component : components) {
            if (component.equals("") || component.equals(".")) {
                continue; // Skip empty and current directory
            } else if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); // Go up one directory
                }
            } else {
                stack.push(component); // Regular directory
            }
        }
        
        if (stack.isEmpty()) {
            return "/";
        }
        
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        return result.toString();
    }
    
    /**
     * 🌐 GRAPH EVALUATION TEMPLATE
     * Evaluate expressions with variable dependencies
     */
    public static double graphEvaluationTemplate(String[] equations, double[] values,
                                                String[] queries) {
        // Build graph of variable relationships
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.length; i++) {
            String[] vars = equations[i].split("/");
            String a = vars[0], b = vars[1];
            double value = values[i];
            
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, value);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / value);
        }
        
        // Example: Find path from source to target using DFS
        // This is a simplified template - actual implementation would handle queries
        return dfsEvaluate(graph, "source", "target", new HashSet<>());
    }
    
    private static double dfsEvaluate(Map<String, Map<String, Double>> graph,
                                     String source, String target, Set<String> visited) {
        if (!graph.containsKey(source)) return -1.0;
        if (source.equals(target)) return 1.0;
        
        visited.add(source);
        for (Map.Entry<String, Double> neighbor : graph.get(source).entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                double result = dfsEvaluate(graph, neighbor.getKey(), target, visited);
                if (result != -1.0) {
                    return result * neighbor.getValue();
                }
            }
        }
        visited.remove(source);
        
        return -1.0;
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Operator Precedence
     * Always handle precedence correctly
     */
    public static void precedenceExample() {
        // Multiplication and division before addition and subtraction
        // Use separate stacks or recursive descent for proper handling
        // Be careful with associativity (left-to-right vs right-to-left)
    }
    
    /**
     * ❌ PITFALL 2: Number Parsing Errors
     * Handle multi-digit numbers and negative numbers correctly
     */
    public static void numberParsingExample() {
        // Build multi-digit numbers: num = num * 10 + (c - '0')
        // Handle negative numbers and leading zeros
        // Consider integer overflow for large numbers
    }
    
    /**
     * ❌ PITFALL 3: Parentheses Mismatching
     * Ensure proper parentheses handling
     */
    public static void parenthesesExample() {
        // Always match opening and closing parentheses
        // Handle nested parentheses correctly
        // Validate balanced parentheses before evaluation
    }
    
    /**
     * ❌ PITFALL 4: Division by Zero
     * Handle mathematical edge cases
     */
    public static void divisionExample() {
        // Check for division by zero before performing operation
        // Handle floating-point precision issues
        // Consider special values (infinity, NaN)
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Parsing Strategy
     * - Use recursive descent for complex nested expressions
     * - Use stack-based evaluation for simple arithmetic
     * - Consider two-pass approach for complex precedence rules
     */
    
    /**
     * 🎯 TIP 2: Handle Edge Cases Early
     * - Empty expressions, single numbers, invalid operators
     * - Whitespace handling and input validation
     * - Overflow and underflow conditions
     */
    
    /**
     * 🎯 TIP 3: Optimize for Common Cases
     * - Cache frequently used subexpressions
     * - Use efficient string parsing techniques
     * - Consider preprocessing for repeated evaluations
     */
    
    /**
     * 🎯 TIP 4: Design for Extensibility
     * - Support adding new operators easily
     * - Separate parsing from evaluation logic
     * - Use strategy pattern for different operation types
     */
    
    /**
     * 🎯 TIP 5: Test Thoroughly
     * - Test all operator combinations and precedence
     * - Test nested structures and edge cases
     * - Verify results against known mathematical evaluations
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC EXPRESSION EVALUATION:
     * - Basic Calculator II (no parentheses)
     * - Evaluate Reverse Polish Notation
     * - Valid Parentheses
     * - Simplify Path
     * 
     * 🟡 INTERMEDIATE EXPRESSION EVALUATION:
     * - Basic Calculator (with parentheses)
     * - Decode String
     * - Evaluate Division
     * - Different Ways to Add Parentheses
     * 
     * 🔴 ADVANCED EXPRESSION EVALUATION:
     * - Expression Add Operators
     * - Parse Lisp Expression
     * - Basic Calculator III
     * - Mini Parser
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE EXPRESSION EVALUATION WHEN:
     * ✅ Need to parse and compute mathematical expressions
     * ✅ Handle operator precedence and associativity
     * ✅ Process nested structures (parentheses, brackets)
     * ✅ Implement calculator functionality
     * ✅ Decode or expand string patterns
     * 
     * AVOID EXPRESSION EVALUATION WHEN:
     * ❌ Simple single operations (use direct calculation)
     * ❌ Complex mathematical functions (use libraries)
     * ❌ Real-time requirements (use optimized parsers)
     * ❌ Very large expressions (consider streaming)
     * 
     * OPTIMIZE EXPRESSION EVALUATION WITH:
     * 🚀 Choose appropriate parsing strategy
     * 🚀 Handle operator precedence correctly
     * 🚀 Validate input and handle edge cases
     * 🚀 Use efficient data structures (stacks, maps)
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Calculator applications (scientific, graphing, basic)
     * - Spreadsheet formula evaluation (Excel, Google Sheets)
     * - Programming language interpreters and compilers
     * - Mathematical software (Mathematica, MATLAB, R)
     * - Database query engines (SQL expression evaluation)
     * - Configuration file parsers (JSON, XML, YAML)
     * - Template engines (Mustache, Handlebars, Jinja)
     * - Rule engines (business rules, validation rules)
     */
    
    public static void main(String[] args) {
        System.out.println("🧮 EXPRESSION EVALUATION PATTERN - READING GUIDE");
        System.out.println("================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Basic Calculator
        String expr1 = "3+2*2";
        int result1 = basicCalculatorTemplate(expr1);
        System.out.println("Basic Calculator (" + expr1 + "): " + result1);
        
        // Postfix Evaluation
        String[] postfix = {"2", "1", "+", "3", "*"};
        int result2 = postfixEvaluationTemplate(postfix);
        System.out.println("Postfix Evaluation: " + result2);
        
        // Recursive Descent
        RecursiveDescentTemplate parser = new RecursiveDescentTemplate();
        int result3 = parser.evaluate("(2+3)*4");
        System.out.println("Recursive Descent: " + result3);
        
        // String Decoding
        String encoded = "3[a2[c]]";
        String decoded = stringDecodingTemplate(encoded);
        System.out.println("String Decoding (" + encoded + "): " + decoded);
        
        // Path Simplification
        String path = "/a/./b/../c/";
        String simplified = pathSimplificationTemplate(path);
        System.out.println("Path Simplification (" + path + "): " + simplified);
        
        System.out.println("\n✅ Key Expression Evaluation Principles:");
        System.out.println("1. Parse expressions systematically (left-to-right or recursive)");
        System.out.println("2. Handle operator precedence and associativity correctly");
        System.out.println("3. Use stacks for temporary storage of operands and operators");
        System.out.println("4. Process nested structures (parentheses) appropriately");
        System.out.println("5. Handle edge cases: division by zero, overflow, invalid input");
        System.out.println("6. Separate parsing logic from evaluation logic for clarity");
        System.out.println("7. Validate input and provide meaningful error messages");
    }
} 