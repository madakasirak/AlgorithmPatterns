package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 🎭 TWO STACKS PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS TWO STACKS PATTERN?
 * ============================================================================
 * 
 * The Two Stacks Pattern involves using two separate stacks to achieve specific
 * behaviors, maintain orders, or perform operations efficiently that would be
 * difficult with a single stack. This pattern is powerful for simulating other
 * data structures (like queues), maintaining auxiliary information (like minimums),
 * and handling specialized operations that require dual-stack coordination.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. SEPARATION: Use each stack for a distinct purpose or phase
 * 2. COORDINATION: Coordinate operations between stacks strategically
 * 3. EFFICIENCY: Achieve better time complexity through dual-stack design
 * 4. SIMULATION: Simulate behaviors of other data structures
 * 
 * 🎭 TWO STACKS METAPHOR:
 * Think of two stacks as "specialized assistants":
 * - Primary stack: handles main operations and data storage
 * - Secondary stack: maintains auxiliary information or order
 * - Working together: they provide capabilities neither could alone
 * - Coordination: operations are distributed based on efficiency needs
 * 
 * ============================================================================
 * 🎯 WHEN TO USE TWO STACKS PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Simulating queue behavior using two stacks
 * - Maintaining auxiliary information (min/max values)
 * - Implementing undo/redo functionality
 * - Handling operations that require order reversal
 * - Managing different phases of computation
 * - Optimizing specific operation combinations
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Implement queue using stacks"
 * - "Maintain minimum/maximum efficiently"
 * - "Undo/redo operations"
 * - "Reverse order processing"
 * - "Two-phase operations"
 * - "Auxiliary information tracking"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Single stack sufficient (don't overcomplicate)
 * - Need random access (use arrays or lists)
 * - Complex multi-stack interactions (use specialized structures)
 * - Memory constraints are critical (consider space overhead)
 * 
 * ============================================================================
 * 🔄 TWO STACKS PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ INPUT/OUTPUT STACK SEPARATION
 * - Input stack: receives new elements
 * - Output stack: serves elements in desired order
 * - Transfer: move elements between stacks when needed
 * - Example: Queue implementation using two stacks
 * 
 * 2️⃣ MAIN/AUXILIARY STACK COORDINATION
 * - Main stack: primary data storage
 * - Auxiliary stack: maintains metadata (min/max/state)
 * - Synchronization: operations maintain consistency
 * - Example: Min Stack with O(1) minimum queries
 * 
 * 3️⃣ PHASE-BASED DUAL STACKS
 * - Stack A: handles first phase of operation
 * - Stack B: handles second phase of operation
 * - Sequential: operations alternate between phases
 * - Example: Expression evaluation with operator/operand separation
 * 
 * 4️⃣ BACKUP/RESTORE STACK PATTERN
 * - Primary stack: current state
 * - Backup stack: previous states for restoration
 * - Checkpoint: save state to backup stack
 * - Example: Undo/redo functionality implementation
 * 
 * 5️⃣ FILTER/PROCESS STACK COMBINATION
 * - Filter stack: temporary storage for filtering
 * - Process stack: final processed results
 * - Pipeline: elements flow through filter to process
 * - Example: String processing with backspace operations
 * 
 * 6️⃣ SIMULATION STACK PAIRING
 * - Both stacks work together to simulate another data structure
 * - Operations are distributed based on efficiency requirements
 * - Maintains behavioral contracts of simulated structure
 * - Example: Deque simulation, priority queue variants
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 QUEUE SIMULATION ALGORITHM:
 * ```
 * Enqueue: Always push to input stack
 * Dequeue: 
 *   - If output stack empty, transfer all from input to output
 *   - Pop from output stack
 * ```
 * 
 * 🔹 MIN STACK MAINTENANCE:
 * ```
 * Push: 
 *   - Push value to main stack
 *   - Push min(value, current_min) to min stack
 * Pop:
 *   - Pop from both main and min stacks
 * ```
 * 
 * 🔹 BACKSPACE SIMULATION:
 * ```
 * Process character:
 *   - If '#': pop from stack (if not empty)
 *   - Else: push character to stack
 * Compare: Compare final stack contents
 * ```
 * 
 * 🔹 UNDO/REDO PATTERN:
 * ```
 * Execute operation:
 *   - Save current state to undo stack
 *   - Clear redo stack
 * Undo:
 *   - Save current state to redo stack
 *   - Restore state from undo stack
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY THE NEED FOR TWO STACKS
 * - What specific behavior or efficiency is required?
 * - Can a single stack achieve the desired functionality?
 * - What auxiliary information needs to be maintained?
 * 
 * STEP 2: DEFINE STACK ROLES
 * - What is the primary purpose of each stack?
 * - How will operations be distributed between stacks?
 * - What coordination mechanisms are needed?
 * 
 * STEP 3: DESIGN OPERATION FLOW
 * - How do elements flow between the two stacks?
 * - When should transfers occur between stacks?
 * - What triggers coordination between stacks?
 * 
 * STEP 4: IMPLEMENT COORDINATION LOGIC
 * - How to maintain consistency between stacks?
 * - What invariants must be preserved?
 * - How to handle edge cases (empty stacks, transfers)?
 * 
 * STEP 5: OPTIMIZE FOR PERFORMANCE
 * - Which operations should be optimized (amortized vs worst-case)?
 * - How to minimize unnecessary transfers or operations?
 * - What space-time trade-offs are acceptable?
 * 
 * ============================================================================
 * 🎨 TWO STACKS PATTERN TEMPLATES
 * ============================================================================
 */
public class TwoStacksReadingGuide {
    
    /**
     * 🔄 QUEUE SIMULATION TEMPLATE
     * Implement FIFO queue using two LIFO stacks
     */
    public static class QueueSimulationTemplate {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;
        
        public QueueSimulationTemplate() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        
        public void enqueue(int x) {
            inputStack.push(x); // Always add to input stack
        }
        
        public int dequeue() {
            if (outputStack.isEmpty()) {
                // Transfer all elements from input to output
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop(); // Remove from output stack
        }
        
        public boolean isEmpty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }
    
    /**
     * 📊 MIN STACK TEMPLATE
     * Maintain minimum element with O(1) access
     */
    public static class MinStackTemplate {
        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;
        
        public MinStackTemplate() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int x) {
            mainStack.push(x);
            // Push current minimum to min stack
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek()); // Maintain same size
            }
        }
        
        public void pop() {
            if (!mainStack.isEmpty()) {
                mainStack.pop();
                minStack.pop();
            }
        }
        
        public int top() {
            return mainStack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    /**
     * 🎯 UNDO/REDO TEMPLATE
     * Implement undo/redo functionality using two stacks
     */
    public static class UndoRedoTemplate {
        private Stack<String> undoStack;
        private Stack<String> redoStack;
        private String currentState;
        
        public UndoRedoTemplate(String initialState) {
            undoStack = new Stack<>();
            redoStack = new Stack<>();
            currentState = initialState;
        }
        
        public void executeOperation(String newState) {
            undoStack.push(currentState); // Save current state
            currentState = newState;
            redoStack.clear(); // Clear redo history
        }
        
        public void undo() {
            if (!undoStack.isEmpty()) {
                redoStack.push(currentState); // Save current for redo
                currentState = undoStack.pop(); // Restore previous state
            }
        }
        
        public void redo() {
            if (!redoStack.isEmpty()) {
                undoStack.push(currentState); // Save current for undo
                currentState = redoStack.pop(); // Restore future state
            }
        }
        
        public String getCurrentState() {
            return currentState;
        }
    }
    
    /**
     * 🔤 STRING PROCESSING TEMPLATE
     * Process strings with backspace operations
     */
    public static class StringProcessingTemplate {
        
        public static String processBackspaces(String s) {
            Stack<Character> stack = new Stack<>();
            
            for (char c : s.toCharArray()) {
                if (c == '#') {
                    if (!stack.isEmpty()) {
                        stack.pop(); // Backspace operation
                    }
                } else {
                    stack.push(c); // Regular character
                }
            }
            
            // Build result string
            StringBuilder result = new StringBuilder();
            for (char c : stack) {
                result.append(c);
            }
            
            return result.toString();
        }
        
        public static boolean compareWithBackspaces(String s, String t) {
            return processBackspaces(s).equals(processBackspaces(t));
        }
    }
    
    /**
     * 🎮 GAME SCORE TEMPLATE
     * Track scores with special operations using stack
     */
    public static class GameScoreTemplate {
        private Stack<Integer> scores;
        
        public GameScoreTemplate() {
            scores = new Stack<>();
        }
        
        public void processOperation(String op) {
            if (op.equals("C")) {
                // Cancel last score
                if (!scores.isEmpty()) {
                    scores.pop();
                }
            } else if (op.equals("D")) {
                // Double last score
                if (!scores.isEmpty()) {
                    scores.push(scores.peek() * 2);
                }
            } else if (op.equals("+")) {
                // Sum of last two scores
                if (scores.size() >= 2) {
                    int last = scores.pop();
                    int secondLast = scores.peek();
                    scores.push(last); // Restore last
                    scores.push(last + secondLast);
                }
            } else {
                // Regular score
                scores.push(Integer.parseInt(op));
            }
        }
        
        public int getTotalScore() {
            int total = 0;
            for (int score : scores) {
                total += score;
            }
            return total;
        }
    }
    
    /**
     * 🔧 STACK WITH INCREMENT TEMPLATE
     * Implement stack with efficient increment operation
     */
    public static class StackWithIncrementTemplate {
        private int[] stack;
        private int[] increments; // Lazy increment tracking
        private int top;
        private int maxSize;
        
        public StackWithIncrementTemplate(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
            increments = new int[maxSize];
            top = -1;
        }
        
        public void push(int x) {
            if (top + 1 < maxSize) {
                top++;
                stack[top] = x;
            }
        }
        
        public int pop() {
            if (top == -1) return -1;
            
            // Apply increment and propagate down
            int result = stack[top] + increments[top];
            if (top > 0) {
                increments[top - 1] += increments[top];
            }
            increments[top] = 0;
            top--;
            
            return result;
        }
        
        public void increment(int k, int val) {
            if (top >= 0) {
                // Apply increment lazily to the k-th element from bottom
                int applyIndex = Math.min(k - 1, top);
                increments[applyIndex] += val;
            }
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Unnecessary Complexity
     * Don't use two stacks when one stack suffices
     */
    public static void complexityExample() {
        // Only use two stacks when you need:
        // - Different access patterns
        // - Auxiliary information tracking
        // - Simulation of other data structures
        // - Phase-based processing
    }
    
    /**
     * ❌ PITFALL 2: Inefficient Transfers
     * Minimize unnecessary transfers between stacks
     */
    public static void transferExample() {
        // Transfer elements only when necessary
        // Use amortized analysis to evaluate efficiency
        // Consider lazy evaluation for expensive operations
    }
    
    /**
     * ❌ PITFALL 3: Inconsistent State
     * Maintain consistency between stacks
     */
    public static void consistencyExample() {
        // Ensure operations maintain invariants
        // Synchronize related operations
        // Handle edge cases (empty stacks, overflows)
    }
    
    /**
     * ❌ PITFALL 4: Memory Overhead
     * Consider space overhead of dual stacks
     */
    public static void memoryExample() {
        // Two stacks use more memory than one
        // Consider space-efficient alternatives
        // Evaluate trade-offs between time and space
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Stack Roles Carefully
     * - Assign clear, distinct purposes to each stack
     * - Consider access patterns and operation frequencies
     * - Design for the most common use cases
     */
    
    /**
     * 🎯 TIP 2: Optimize Transfer Operations
     * - Use lazy transfers when possible
     * - Batch operations to minimize transfers
     * - Consider amortized complexity analysis
     */
    
    /**
     * 🎯 TIP 3: Maintain Clear Invariants
     * - Define what each stack represents at all times
     * - Ensure operations preserve stack relationships
     * - Document preconditions and postconditions
     */
    
    /**
     * 🎯 TIP 4: Handle Edge Cases Gracefully
     * - Empty stacks, full stacks, single elements
     * - Transfer operations with insufficient elements
     * - Boundary conditions and error states
     */
    
    /**
     * 🎯 TIP 5: Consider Alternative Designs
     * - Can you achieve the same with different data structures?
     * - Are there simpler single-stack solutions?
     * - What are the trade-offs with other approaches?
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC TWO STACKS PROBLEMS:
     * - Implement Queue using Stacks
     * - Min Stack
     * - Baseball Game
     * - Backspace String Compare
     * 
     * 🟡 INTERMEDIATE TWO STACKS PROBLEMS:
     * - Design Stack with Increment Operation
     * - Evaluate Reverse Polish Notation
     * - Valid Parentheses
     * - Implement Stack using Queues
     * 
     * 🔴 ADVANCED TWO STACKS PROBLEMS:
     * - Maximum Frequency Stack
     * - Design Browser History
     * - Undo/Redo System
     * - Expression Evaluation with Variables
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE TWO STACKS PATTERN WHEN:
     * ✅ Need to simulate queue behavior with stacks
     * ✅ Require auxiliary information (min/max) with O(1) access
     * ✅ Implementing undo/redo functionality
     * ✅ Different phases of processing require separate storage
     * ✅ Need to reverse order or maintain multiple orderings
     * 
     * AVOID TWO STACKS PATTERN WHEN:
     * ❌ Single stack provides adequate functionality
     * ❌ Memory constraints are critical
     * ❌ Random access is required
     * ❌ Operations don't benefit from dual-stack design
     * 
     * OPTIMIZE TWO STACKS PATTERN WITH:
     * 🚀 Clear role separation between stacks
     * 🚀 Efficient transfer strategies
     * 🚀 Lazy evaluation for expensive operations
     * 🚀 Amortized analysis for performance guarantees
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Text editors (undo/redo functionality)
     * - Web browsers (back/forward navigation)
     * - Calculator applications (operation history)
     * - Game development (state management, score tracking)
     * - Database systems (transaction rollback)
     * - Compiler design (symbol table management)
     * - Operating systems (process state management)
     * - Version control systems (branching and merging)
     */
    
    public static void main(String[] args) {
        System.out.println("🎭 TWO STACKS PATTERN - READING GUIDE");
        System.out.println("=====================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Queue Simulation
        QueueSimulationTemplate queue = new QueueSimulationTemplate();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Queue simulation - dequeue: " + queue.dequeue()); // 1
        System.out.println("Queue simulation - dequeue: " + queue.dequeue()); // 2
        
        // Min Stack
        MinStackTemplate minStack = new MinStackTemplate();
        minStack.push(3);
        minStack.push(1);
        minStack.push(2);
        System.out.println("Min stack - minimum: " + minStack.getMin()); // 1
        minStack.pop();
        System.out.println("Min stack - minimum after pop: " + minStack.getMin()); // 1
        
        // Undo/Redo
        UndoRedoTemplate editor = new UndoRedoTemplate("initial");
        editor.executeOperation("typed hello");
        editor.executeOperation("typed world");
        System.out.println("Current state: " + editor.getCurrentState()); // "typed world"
        editor.undo();
        System.out.println("After undo: " + editor.getCurrentState()); // "typed hello"
        editor.redo();
        System.out.println("After redo: " + editor.getCurrentState()); // "typed world"
        
        // String Processing
        boolean result = StringProcessingTemplate.compareWithBackspaces("ab#c", "ad#c");
        System.out.println("Backspace comparison: " + result); // true
        
        // Game Score
        GameScoreTemplate game = new GameScoreTemplate();
        game.processOperation("5");
        game.processOperation("2");
        game.processOperation("C");
        game.processOperation("D");
        game.processOperation("+");
        System.out.println("Total score: " + game.getTotalScore()); // 30
        
        // Stack with Increment
        StackWithIncrementTemplate incStack = new StackWithIncrementTemplate(3);
        incStack.push(1);
        incStack.push(2);
        incStack.increment(2, 100);
        System.out.println("Pop with increment: " + incStack.pop()); // 102
        System.out.println("Pop with increment: " + incStack.pop()); // 101
        
        System.out.println("\n✅ Key Two Stacks Principles:");
        System.out.println("1. Assign clear, distinct purposes to each stack");
        System.out.println("2. Coordinate operations between stacks strategically");
        System.out.println("3. Optimize for common operation patterns");
        System.out.println("4. Maintain consistency and invariants between stacks");
        System.out.println("5. Consider amortized complexity for transfer operations");
        System.out.println("6. Handle edge cases (empty stacks, transfers, boundaries)");
        System.out.println("7. Evaluate space-time trade-offs of dual-stack design");
    }
} 