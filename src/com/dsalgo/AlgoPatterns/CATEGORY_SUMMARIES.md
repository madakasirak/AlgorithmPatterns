# 📊 **ALGORITHMIC PATTERNS - CATEGORY SUMMARIES**

*Consolidated revision guide for all pattern categories*

---

## 📊 **ARRAYS PATTERNS** (5 Core Patterns)

### **🎯 Pattern Overview**
Arrays form the foundation of algorithmic thinking. Master these 5 patterns to solve 90% of array-based problems.

### **📋 Pattern Summary**

#### **1. Two Pointers** 
- **When**: Sorted arrays, pair/triplet problems, palindromes
- **Approach**: Start/end pointers moving toward each other
- **Time**: O(n), **Space**: O(1)
- **Key Problems**: Two Sum, 3Sum, Remove Duplicates
- **Template**: `left = 0, right = n-1; while(left < right)`

#### **2. Sliding Window**
- **When**: Contiguous subarray, max/min in window
- **Approach**: Expand right, contract left, maintain window
- **Time**: O(n), **Space**: O(1)
- **Key Problems**: Max Subarray, Minimum Window
- **Template**: `for(right); while(condition) left++`

#### **3. Prefix Sums**
- **When**: Range sum queries, subarray problems
- **Approach**: Precompute cumulative sums
- **Time**: O(n) build, O(1) query, **Space**: O(n)
- **Key Problems**: Range Sum, Subarray Sum Equals K
- **Template**: `prefixSum[i] = prefixSum[i-1] + arr[i]`

#### **4. Merge Intervals**
- **When**: Overlapping ranges, scheduling problems
- **Approach**: Sort by start time, merge overlapping
- **Time**: O(n log n), **Space**: O(n)
- **Key Problems**: Merge Intervals, Meeting Rooms
- **Template**: `sort(intervals); if(overlap) merge`

#### **5. Sorting Strategies**
- **When**: Order matters for solution optimization
- **Approach**: Sort then apply other patterns
- **Time**: O(n log n), **Space**: O(1) to O(n)
- **Key Problems**: Sort Colors, Custom comparisons
- **Template**: `Arrays.sort() + pattern application`

---

## 🔤 **STRING PATTERNS** (5 Core Patterns)

### **🎯 Pattern Overview**
String manipulation requires specialized techniques for character-based problems and text processing.

### **📋 Pattern Summary**

#### **1. String Two Pointers**
- **When**: Palindromes, character comparisons
- **Approach**: Compare characters from both ends
- **Time**: O(n), **Space**: O(1)
- **Key Problems**: Valid Palindrome, Reverse Words
- **Template**: `left = 0, right = s.length()-1`

#### **2. String Sliding Window**
- **When**: Substring problems, character constraints
- **Approach**: Expand window, track character frequencies
- **Time**: O(n), **Space**: O(k) for k unique chars
- **Key Problems**: Longest Substring, Minimum Window
- **Template**: `HashMap + sliding window`

#### **3. HashMap Implementation**
- **When**: Frequency counting, character mapping
- **Approach**: Use HashMap for O(1) lookups
- **Time**: O(n), **Space**: O(k)
- **Key Problems**: Anagrams, Character Count
- **Template**: `Map<Character, Integer> freq = new HashMap<>()`

#### **4. String Manipulation**
- **When**: Transformations, parsing, formatting
- **Approach**: StringBuilder for efficiency
- **Time**: O(n), **Space**: O(n)
- **Key Problems**: Reverse String, String to Integer
- **Template**: `StringBuilder sb = new StringBuilder()`

#### **5. Regular Expressions**
- **When**: Pattern matching, validation
- **Approach**: State machines, dynamic programming
- **Time**: O(m*n), **Space**: O(m*n)
- **Key Problems**: Regex Matching, Wildcard Matching
- **Template**: `dp[i][j] = pattern match state`

---

## 🔍 **BINARY SEARCH PATTERNS** (6 Core Patterns)

### **🎯 Pattern Overview**
Binary search extends beyond simple searching to become a powerful optimization technique.

### **📋 Pattern Summary**

#### **1. Basic Binary Search**
- **When**: Sorted array, find target
- **Template**: `while(left <= right) { mid = left + (right-left)/2 }`
- **Time**: O(log n), **Space**: O(1)

#### **2. Range Search**
- **When**: Find first/last occurrence, bounds
- **Template**: Modify condition to find boundaries
- **Time**: O(log n), **Space**: O(1)

#### **3. Allocation Problems**
- **When**: Minimize maximum, distribute resources
- **Template**: Binary search on answer space
- **Time**: O(n * log(max-min)), **Space**: O(1)

#### **4. Counting Occurrences**
- **When**: Count frequency in sorted data
- **Template**: lastPos - firstPos + 1
- **Time**: O(log n), **Space**: O(1)

#### **5. Bitonic Array Search**
- **When**: Mountain arrays, peak finding
- **Template**: Find peak + binary search halves
- **Time**: O(log n), **Space**: O(1)

---

## 🔄 **RECURSION PATTERNS** (4 Core Patterns)

### **🎯 Pattern Overview**
Recursion breaks complex problems into simpler subproblems using function calls.

### **📋 Pattern Summary**

#### **1. Basic Recursive Functions**
- **Template**: `if(baseCase) return; return recursiveCall(smaller)`
- **Examples**: Factorial, Fibonacci, Power

#### **2. Divide & Conquer**
- **Template**: Divide → Solve → Combine
- **Examples**: Merge Sort, Quick Sort, Maximum Subarray

#### **3. Backtracking**
- **Template**: Try → Recurse → Undo (backtrack)
- **Examples**: N-Queens, Sudoku, Permutations

#### **4. Recursive Search**
- **Template**: DFS-style exploration with state
- **Examples**: Tree/Graph traversal, Path finding

---

## 🔗 **LINKED LIST PATTERNS** (4 Core Patterns)

### **🎯 Pattern Overview**
Linked list problems focus on pointer manipulation and traversal techniques.

### **📋 Pattern Summary**

#### **1. Fast and Slow Pointers**
- **When**: Cycle detection, middle finding
- **Template**: `slow = slow.next; fast = fast.next.next`
- **Time**: O(n), **Space**: O(1)

#### **2. Dummy Node Technique**
- **When**: Head manipulation, edge cases
- **Template**: `ListNode dummy = new ListNode(0); dummy.next = head`
- **Time**: O(n), **Space**: O(1)

#### **3. Recursion Technique**
- **When**: Elegant solutions, divide and conquer
- **Template**: Process current + recursive call
- **Time**: O(n), **Space**: O(n) call stack

#### **4. In-Place Reversal**
- **When**: Reverse segments without extra space
- **Template**: `prev, curr, next` pointer manipulation
- **Time**: O(n), **Space**: O(1)

---

## 📚 **STACKS & QUEUES PATTERNS** (6 Core Patterns)

### **🎯 Pattern Overview**
LIFO/FIFO data structures enable efficient order-based processing.

### **📋 Pattern Summary**

#### **1. Design Problems**
- **Focus**: Implement custom data structures
- **Template**: Use underlying arrays/lists with pointers

#### **2. Monotonic Stack**
- **When**: Next greater/smaller element
- **Template**: Maintain increasing/decreasing order
- **Time**: O(n), **Space**: O(n)

#### **3. Expression Evaluation**
- **When**: Arithmetic expressions, parsing
- **Template**: Operator/operand stacks
- **Time**: O(n), **Space**: O(n)

#### **4. Two Stacks**
- **When**: Simulate queue, specific ordering
- **Template**: Input stack + output stack
- **Time**: Amortized O(1) per operation

#### **5. Sliding Window & Monotonic Queue**
- **When**: Window maximum/minimum
- **Template**: Deque maintaining order
- **Time**: O(n), **Space**: O(k)

---

## 🌳 **BINARY TREES & BST PATTERNS** (6 Core Patterns)

### **🎯 Pattern Overview**
Tree algorithms form the backbone of hierarchical data processing.

### **📋 Pattern Summary**

#### **1. Tree Traversal**
- **DFS**: Inorder, Preorder, Postorder (recursive/iterative)
- **BFS**: Level order, zigzag traversal
- **Template**: `Stack for DFS, Queue for BFS`

#### **2. Tree Construction**
- **When**: Build tree from traversals/arrays
- **Template**: Recursively divide and construct
- **Time**: O(n), **Space**: O(n)

#### **3. Mirror & Symmetry**
- **When**: Tree inversion, symmetry checks
- **Template**: Recursive left/right comparison
- **Time**: O(n), **Space**: O(h)

#### **4. Path Sum & Root to Leaf**
- **When**: Path tracking, sum calculations
- **Template**: DFS with path state management
- **Time**: O(n), **Space**: O(h)

#### **5. Traversal & Search**
- **When**: BST properties, LCA problems
- **Template**: Leverage BST ordering properties
- **Time**: O(h) for BST, O(n) for general tree

#### **6. Validation & Properties**
- **When**: BST validation, tree metrics
- **Template**: Recursive property checking
- **Time**: O(n), **Space**: O(h)

---

## ⛰️ **PRIORITY QUEUE PATTERNS** (7 Core Patterns)

### **🎯 Pattern Overview**
Heap-based solutions for optimization and ordering problems.

### **📋 Pattern Summary**

#### **1. Finding Kth Largest/Smallest**
- **Template**: `PriorityQueue<Integer> minHeap = new PriorityQueue<>()`
- **Size**: Maintain heap of size K
- **Time**: O(n log k), **Space**: O(k)

#### **2. Top K Frequent Elements**
- **Template**: Frequency map + min heap
- **Time**: O(n log k), **Space**: O(n + k)

#### **3. Merge K Lists**
- **Template**: Add all heads to heap, process min
- **Time**: O(n log k), **Space**: O(k)

#### **4. Sliding Window/Minimum Window**
- **Template**: Two heaps for median, monotonic deque
- **Time**: O(n log k), **Space**: O(k)

#### **5. Design Problems**
- **Focus**: Custom priority-based systems
- **Template**: Multiple heaps with different criteria

#### **6. Construction and Manipulation**
- **When**: Frequency-based arrangements
- **Template**: Heap + greedy construction

#### **7. With Graphs**
- **When**: Dijkstra's, MST algorithms
- **Template**: Priority queue with distances/weights

---

## 🧠 **DYNAMIC PROGRAMMING PATTERNS** (8 Core Patterns)

### **🎯 Pattern Overview**
Optimization through subproblem solutions and state management.

### **📋 Pattern Summary**

#### **1. Basic Dynamic Programming**
- **Template**: `dp[i] = function(dp[i-1], dp[i-2], ...)`
- **Examples**: Fibonacci, Climbing Stairs, House Robber
- **Optimization**: Rolling variables for space

#### **2. Optimal Substructure**
- **Template**: Optimal solution = combination of optimal subproblems
- **Examples**: LIS, Coin Change, Edit Distance

#### **3. Interval/Range DP**
- **Template**: `dp[i][j] = optimal for interval [i,j]`
- **Examples**: Burst Balloons, Matrix Chain Multiplication

#### **4. Knapsack Problems**
- **Template**: `dp[i][w] = max value with i items, weight w`
- **Types**: 0/1, Unbounded, Multiple knapsacks

#### **5. Prefix Sums**
- **Template**: Precompute for fast range queries
- **Examples**: Subarray Sum Equals K, Range queries

#### **6. Counting Problems**
- **Template**: `dp[i] = number of ways to reach state i`
- **Examples**: Unique Paths, Distinct Subsequences

#### **7. Interval Partitioning**
- **Template**: Optimal interval division strategies
- **Examples**: Non-overlapping Intervals, Partition Labels

#### **8. Probability & Expectations**
- **Template**: `dp[state] = probability/expected value`
- **Examples**: Knight Probability, Game theory

---

## 🕸️ **GRAPH PATTERNS** (7 Core Patterns)

### **🎯 Pattern Overview**
Network algorithms for connectivity, paths, and optimization.

### **📋 Pattern Summary**

#### **1. Finding Connected Components**
- **Template**: DFS/BFS traversal + visited tracking
- **Alternative**: Union-Find data structure
- **Time**: O(V + E), **Space**: O(V)

#### **2. Shortest Path Finding**
- **Unweighted**: BFS
- **Weighted**: Dijkstra's (non-negative), Bellman-Ford (negative)
- **Template**: Priority queue + distance array

#### **3. Cycle Detection**
- **Directed**: DFS with recursion stack
- **Undirected**: DFS with parent tracking
- **Template**: Color-based or parent-based tracking

#### **4. Bipartite Graph Check**
- **Template**: Two-coloring using BFS/DFS
- **Time**: O(V + E), **Space**: O(V)

#### **5. Minimum Spanning Tree**
- **Kruskal's**: Sort edges + Union-Find
- **Prim's**: Priority queue + visited set
- **Time**: O(E log E), **Space**: O(V)

#### **6. Directed Acyclic Graph (DAG) Traversal**
- **Topological Sort**: Kahn's algorithm or DFS-based
- **Template**: In-degree counting or DFS finishing times

#### **7. Graph Coloring**
- **Template**: Greedy coloring with conflict checking
- **Time**: O(V + E), **Space**: O(V)

---

## 🎯 **QUICK REFERENCE TABLE**

| Pattern Category | Must-Know | Time Complexity | Space Complexity | Interview % |
|------------------|-----------|-----------------|------------------|-------------|
| Arrays | Two Pointers, Sliding Window | O(n) | O(1) | 95% |
| Strings | Sliding Window, HashMap | O(n) | O(k) | 85% |
| Binary Search | Basic, Range Search | O(log n) | O(1) | 70% |
| Recursion | DFS, Backtracking | O(2^n) to O(n) | O(h) | 75% |
| LinkedList | Fast/Slow, Reversal | O(n) | O(1) | 70% |
| Stack/Queue | Monotonic, Design | O(n) | O(n) | 80% |
| Trees | Traversal, Properties | O(n) | O(h) | 90% |
| Priority Queue | Top-K, Merge | O(n log k) | O(k) | 75% |
| Dynamic Programming | Basic DP, Knapsack | O(n²) | O(n) | 80% |
| Graphs | DFS/BFS, Shortest Path | O(V + E) | O(V) | 60% |

---

## 💡 **REVISION STRATEGY**

### **🔥 High Priority (Daily)**
- Arrays: Two Pointers, Sliding Window
- Trees: DFS/BFS Traversal
- Dynamic Programming: Basic patterns

### **⚡ Medium Priority (2-3x/week)**
- String algorithms, Binary Search
- LinkedList manipulation, Stack/Queue patterns
- Graph traversal basics

### **🎯 Low Priority (Weekly)**
- Advanced DP patterns, Complex graph algorithms
- Specialized tree problems, Advanced heap usage

---

*Use this guide for systematic revision and pattern recognition practice!* 