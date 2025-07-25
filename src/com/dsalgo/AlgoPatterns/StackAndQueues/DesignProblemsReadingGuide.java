package com.dsalgo.AlgoPatterns.Stack;

import java.util.*;

/**
 * 🏗️ DESIGN PROBLEMS (STACK/QUEUE SYSTEMS) - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT ARE DESIGN PROBLEMS FOR STACK/QUEUE SYSTEMS?
 * ============================================================================
 * 
 * Design Problems involve creating custom data structures and systems by
 * combining fundamental data structures like stacks and queues. These problems
 * test your ability to understand requirements, choose appropriate underlying
 * structures, design clean APIs, handle edge cases, and optimize for specific
 * use cases. The key is to translate real-world system requirements into
 * efficient algorithmic solutions using basic building blocks.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. REQUIREMENT ANALYSIS: Understand what operations are needed
 * 2. STRUCTURE COMPOSITION: Combine basic structures effectively
 * 3. API DESIGN: Create clean, intuitive interfaces
 * 4. OPTIMIZATION: Choose efficient implementations for use cases
 * 
 * 🏗️ DESIGN METAPHOR:
 * Think of design problems as "architectural blueprints":
 * - Understand the building requirements (operations needed)
 * - Choose the right materials (stacks, queues, arrays)
 * - Design the structure (how components interact)
 * - Optimize for usage patterns (performance requirements)
 * 
 * ============================================================================
 * 🎯 WHEN TO USE DESIGN PROBLEM APPROACH
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Implementing custom data structures
 * - System design with specific constraints
 * - Converting between different data structure behaviors
 * - Real-world application simulations
 * - Performance-optimized custom containers
 * - API design and interface creation
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Design and implement..."
 * - "Create a data structure that..."
 * - "Implement [X] using [Y]"
 * - "Design a system for..."
 * - "Support the following operations..."
 * - "Optimize for [specific operations]"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple algorithmic problems (use direct algorithms)
 * - Pure mathematical computations (use formulas)
 * - One-time operations (don't need custom structures)
 * - Standard library sufficient (no custom design needed)
 * 
 * ============================================================================
 * 🔄 DESIGN PROBLEM VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ DATA STRUCTURE CONVERSION
 * - Implement one structure using another
 * - Examples: Queue using Stacks, Stack using Queues
 * - Focus on maintaining behavioral contracts
 * 
 * 2️⃣ ENHANCED FUNCTIONALITY
 * - Add new features to existing structures
 * - Examples: Min Stack, Max Queue
 * - Maintain efficiency while adding capabilities
 * 
 * 3️⃣ SYSTEM SIMULATION
 * - Model real-world systems and behaviors
 * - Examples: Browser History, Hit Counter
 * - Focus on realistic operation patterns
 * 
 * 4️⃣ CIRCULAR/BOUNDED STRUCTURES
 * - Fixed-size structures with wraparound
 * - Examples: Circular Queue, Ring Buffer
 * - Optimize for space and performance
 * 
 * 5️⃣ MULTI-COMPONENT SYSTEMS
 * - Complex systems with multiple interacting parts
 * - Examples: Twitter Timeline, Snake Game
 * - Coordinate multiple data structures
 * 
 * 6️⃣ TIME-BASED STRUCTURES
 * - Handle time-sensitive operations
 * - Examples: Hit Counter, Time-based Cache
 * - Manage temporal data efficiently
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND DESIGN PATTERNS
 * ============================================================================
 * 
 * 🔹 DESIGN PROCESS:
 * ```
 * 1. ANALYZE: What operations are required?
 * 2. IDENTIFY: What are the performance constraints?
 * 3. CHOOSE: Which underlying structures to use?
 * 4. DESIGN: How do operations map to underlying structures?
 * 5. OPTIMIZE: How to achieve required performance?
 * 6. VALIDATE: Does design handle all edge cases?
 * ```
 * 
 * 🔹 STRUCTURE SELECTION CRITERIA:
 * ```
 * Stack: LIFO operations, backtracking, expression evaluation
 * Queue: FIFO operations, BFS, streaming, scheduling
 * Deque: Both ends access, sliding window operations
 * Array: Random access, fixed size, cache-friendly
 * List: Dynamic size, insertion/deletion flexibility
 * Map: Key-value lookup, fast access by identifier
 * ```
 * 
 * 🔹 COMMON DESIGN PATTERNS:
 * ```
 * Adapter Pattern: Convert interface of one structure to another
 * Composite Pattern: Combine multiple structures for complex functionality
 * Strategy Pattern: Different implementations for different use cases
 * Observer Pattern: Notify components of state changes
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: UNDERSTAND REQUIREMENTS
 * - What operations must be supported?
 * - What are the expected time/space complexities?
 * - Are there any special constraints or optimizations needed?
 * 
 * STEP 2: ANALYZE OPERATION PATTERNS
 * - Which operations are most frequent?
 * - Are there any operation dependencies?
 * - What are the expected data access patterns?
 * 
 * STEP 3: CHOOSE UNDERLYING STRUCTURES
 * - What basic structures best support required operations?
 * - How can structures be combined effectively?
 * - What trade-offs exist between different approaches?
 * 
 * STEP 4: DESIGN API AND INTERNAL STRUCTURE
 * - How should the public interface look?
 * - How do operations map to underlying structure operations?
 * - How to handle edge cases and error conditions?
 * 
 * STEP 5: IMPLEMENT AND OPTIMIZE
 * - Implement core operations efficiently
 * - Add error handling and validation
 * - Optimize for common use cases
 * 
 * STEP 6: TEST AND VALIDATE
 * - Test all operations and edge cases
 * - Verify time/space complexity requirements
 * - Validate API usability and correctness
 * 
 * ============================================================================
 * 🎨 DESIGN PROBLEM TEMPLATES
 * ============================================================================
 */
public class DesignProblemsReadingGuide {
    
    /**
     * 🔄 DATA STRUCTURE CONVERSION TEMPLATE
     * Convert behavior of one structure using another
     */
    public static class DataStructureConversionTemplate {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;
        
        public DataStructureConversionTemplate() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        
        // Template for queue operations using stacks
        public void enqueue(int x) {
            inputStack.push(x);
        }
        
        public int dequeue() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop();
        }
        
        public boolean isEmpty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }
    
    /**
     * 🎯 ENHANCED FUNCTIONALITY TEMPLATE
     * Add features while maintaining base structure behavior
     */
    public static class EnhancedFunctionalityTemplate {
        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;
        
        public EnhancedFunctionalityTemplate() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int x) {
            mainStack.push(x);
            // Maintain minimum value tracking
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }
        
        public int pop() {
            int popped = mainStack.pop();
            if (popped == minStack.peek()) {
                minStack.pop();
            }
            return popped;
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    /**
     * 🌐 SYSTEM SIMULATION TEMPLATE
     * Model real-world system behaviors
     */
    public static class SystemSimulationTemplate {
        private Stack<String> history;
        private Stack<String> future;
        private String current;
        
        public SystemSimulationTemplate(String homepage) {
            history = new Stack<>();
            future = new Stack<>();
            current = homepage;
        }
        
        public void visit(String url) {
            history.push(current);
            current = url;
            future.clear(); // Clear future when visiting new page
        }
        
        public String back(int steps) {
            while (steps > 0 && !history.isEmpty()) {
                future.push(current);
                current = history.pop();
                steps--;
            }
            return current;
        }
        
        public String forward(int steps) {
            while (steps > 0 && !future.isEmpty()) {
                history.push(current);
                current = future.pop();
                steps--;
            }
            return current;
        }
    }
    
    /**
     * 🔄 CIRCULAR STRUCTURE TEMPLATE
     * Fixed-size structures with wraparound behavior
     */
    public static class CircularStructureTemplate {
        private int[] buffer;
        private int head;
        private int tail;
        private int size;
        private int capacity;
        
        public CircularStructureTemplate(int k) {
            buffer = new int[k];
            capacity = k;
            head = 0;
            tail = 0;
            size = 0;
        }
        
        public boolean enqueue(int value) {
            if (size == capacity) return false;
            
            buffer[tail] = value;
            tail = (tail + 1) % capacity;
            size++;
            return true;
        }
        
        public boolean dequeue() {
            if (size == 0) return false;
            
            head = (head + 1) % capacity;
            size--;
            return true;
        }
        
        public int front() {
            return size == 0 ? -1 : buffer[head];
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isFull() {
            return size == capacity;
        }
    }
    
    /**
     * ⏰ TIME-BASED TEMPLATE
     * Handle time-sensitive operations efficiently
     */
    public static class TimeBasedTemplate {
        private Queue<Integer> timestamps;
        
        public TimeBasedTemplate() {
            timestamps = new LinkedList<>();
        }
        
        public void hit(int timestamp) {
            timestamps.offer(timestamp);
        }
        
        public int getHits(int timestamp) {
            // Remove timestamps older than 300 seconds
            while (!timestamps.isEmpty() && 
                   timestamp - timestamps.peek() >= 300) {
                timestamps.poll();
            }
            return timestamps.size();
        }
    }
    
    /**
     * 🏗️ MULTI-COMPONENT TEMPLATE
     * Complex systems with multiple interacting parts
     */
    public static class MultiComponentTemplate {
        private Map<Integer, Set<Integer>> following;
        private Map<Integer, List<Integer>> tweets;
        private int tweetIdCounter;
        
        public MultiComponentTemplate() {
            following = new HashMap<>();
            tweets = new HashMap<>();
            tweetIdCounter = 0;
        }
        
        public void postTweet(int userId, int tweetId) {
            tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(tweetId);
        }
        
        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            
            // Add user's own tweets
            if (tweets.containsKey(userId)) {
                List<Integer> userTweets = tweets.get(userId);
                for (int i = userTweets.size() - 1; i >= 0 && maxHeap.size() < 10; i--) {
                    maxHeap.offer(new int[]{userId, userTweets.get(i)});
                }
            }
            
            // Add followed users' tweets
            if (following.containsKey(userId)) {
                for (int followeeId : following.get(userId)) {
                    if (tweets.containsKey(followeeId)) {
                        List<Integer> followeeTweets = tweets.get(followeeId);
                        for (int i = followeeTweets.size() - 1; i >= 0 && maxHeap.size() < 10; i--) {
                            maxHeap.offer(new int[]{followeeId, followeeTweets.get(i)});
                        }
                    }
                }
            }
            
            List<Integer> result = new ArrayList<>();
            while (!maxHeap.isEmpty() && result.size() < 10) {
                result.add(maxHeap.poll()[1]);
            }
            
            return result;
        }
        
        public void follow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                following.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
            }
        }
        
        public void unfollow(int followerId, int followeeId) {
            if (following.containsKey(followerId)) {
                following.get(followerId).remove(followeeId);
            }
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Ignoring Edge Cases
     * Always handle empty structures, boundary conditions
     */
    public static void edgeCaseExample() {
        // Handle empty stacks/queues
        // Check bounds for circular structures
        // Validate input parameters
        // Handle concurrent modifications if applicable
    }
    
    /**
     * ❌ PITFALL 2: Inefficient Structure Choice
     * Choose structures that optimize for required operations
     */
    public static void structureChoiceExample() {
        // Use Stack for LIFO, Queue for FIFO
        // Use Deque when both ends access needed
        // Use appropriate maps for fast lookups
        // Consider memory vs. time trade-offs
    }
    
    /**
     * ❌ PITFALL 3: Poor API Design
     * Design intuitive, consistent interfaces
     */
    public static void apiDesignExample() {
        // Use clear, descriptive method names
        // Handle error cases appropriately
        // Maintain consistent return types
        // Document expected behavior clearly
    }
    
    /**
     * ❌ PITFALL 4: Missing Optimization Opportunities
     * Optimize for common operation patterns
     */
    public static void optimizationExample() {
        // Amortize expensive operations
        // Use lazy evaluation when possible
        // Cache frequently accessed data
        // Choose appropriate data structure sizes
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Analyze Operation Frequencies
     * - Optimize for most common operations
     * - Accept slower performance for rare operations
     * - Use amortized analysis for time complexity
     */
    
    /**
     * 🎯 TIP 2: Consider Memory Usage Patterns
     * - Use circular buffers for fixed-size requirements
     * - Implement lazy deletion for better performance
     * - Consider memory fragmentation in long-running systems
     */
    
    /**
     * 🎯 TIP 3: Design for Extensibility
     * - Use interfaces for pluggable implementations
     * - Separate concerns clearly
     * - Design for future requirement changes
     */
    
    /**
     * 🎯 TIP 4: Handle Concurrency Considerations
     * - Consider thread safety if needed
     * - Document synchronization requirements
     * - Use appropriate locking strategies
     */
    
    /**
     * 🎯 TIP 5: Validate Design with Use Cases
     * - Test with realistic usage patterns
     * - Verify performance under load
     * - Ensure API meets user expectations
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC DESIGN PROBLEMS:
     * - Implement Queue Using Stacks
     * - Implement Stack Using Queues
     * - Design Circular Queue
     * - Min Stack / Max Stack
     * 
     * 🟡 INTERMEDIATE DESIGN PROBLEMS:
     * - Design Browser History
     * - Design Hit Counter
     * - Design Twitter Timeline
     * - LRU Cache Implementation
     * 
     * 🔴 ADVANCED DESIGN PROBLEMS:
     * - Design Snake Game
     * - Design File System
     * - Design Rate Limiter
     * - Design Distributed Cache
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE DESIGN PROBLEM APPROACH WHEN:
     * ✅ Need to implement custom data structures
     * ✅ Combining multiple basic structures
     * ✅ Specific performance requirements exist
     * ✅ Modeling real-world systems
     * ✅ Creating reusable components
     * 
     * AVOID DESIGN PROBLEM APPROACH WHEN:
     * ❌ Standard library structures sufficient
     * ❌ One-time algorithmic computation
     * ❌ Simple data transformations
     * ❌ No clear structure requirements
     * 
     * OPTIMIZE DESIGN PROBLEMS WITH:
     * 🚀 Choose appropriate underlying structures
     * 🚀 Optimize for common operation patterns
     * 🚀 Use lazy evaluation and caching
     * 🚀 Design clean, intuitive APIs
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Web browser implementation (history, tabs, bookmarks)
     * - Text editor systems (undo/redo, clipboard)
     * - Game development (state management, inventory)
     * - Operating systems (process scheduling, memory management)
     * - Database systems (buffer pools, transaction logs)
     * - Network protocols (packet buffering, flow control)
     * - Compiler design (symbol tables, expression parsing)
     * - Distributed systems (message queues, load balancing)
     */
    
    public static void main(String[] args) {
        System.out.println("🏗️ DESIGN PROBLEMS (STACK/QUEUE SYSTEMS) - READING GUIDE");
        System.out.println("=========================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Data Structure Conversion
        DataStructureConversionTemplate queue = new DataStructureConversionTemplate();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println("Queue using stacks - dequeue: " + queue.dequeue());
        
        // Enhanced Functionality
        EnhancedFunctionalityTemplate minStack = new EnhancedFunctionalityTemplate();
        minStack.push(3);
        minStack.push(1);
        minStack.push(2);
        System.out.println("Min stack - minimum: " + minStack.getMin());
        
        // System Simulation
        SystemSimulationTemplate browser = new SystemSimulationTemplate("home.com");
        browser.visit("page1.com");
        browser.visit("page2.com");
        System.out.println("Browser after back(1): " + browser.back(1));
        
        // Circular Structure
        CircularStructureTemplate circularQueue = new CircularStructureTemplate(3);
        circularQueue.enqueue(1);
        circularQueue.enqueue(2);
        System.out.println("Circular queue front: " + circularQueue.front());
        
        // Time-based Structure
        TimeBasedTemplate hitCounter = new TimeBasedTemplate();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        System.out.println("Hits at time 4: " + hitCounter.getHits(4));
        
        System.out.println("\n✅ Key Design Principles:");
        System.out.println("1. Analyze requirements and operation patterns carefully");
        System.out.println("2. Choose appropriate underlying data structures");
        System.out.println("3. Design clean, intuitive APIs");
        System.out.println("4. Optimize for common use cases");
        System.out.println("5. Handle edge cases and error conditions");
        System.out.println("6. Consider memory and performance trade-offs");
        System.out.println("7. Test with realistic usage scenarios");
    }
} 