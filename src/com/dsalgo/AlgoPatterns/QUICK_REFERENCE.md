# 🎯 **QUICK REFERENCE - INTERVIEW CHEAT SHEET**

*Essential patterns, templates, and complexities for fast lookup during interviews*

---

## ⚡ **PATTERN RECOGNITION GUIDE**

### **🔍 HOW TO IDENTIFY PATTERNS**

| **Problem Description Contains** | **Pattern to Use** | **Key Signal** |
|----------------------------------|-------------------|----------------|
| "Two Sum", "Pair", "Triplet" | Two Pointers | Sorted array, target sum |
| "Substring", "Window", "Contiguous" | Sliding Window | String/array, optimize window |
| "Sorted array", "Find target" | Binary Search | O(log n) requirement |
| "Tree", "Graph", "Connected" | DFS/BFS | Traversal needed |
| "Maximum/Minimum", "Optimize" | Dynamic Programming | Overlapping subproblems |
| "Top K", "Largest/Smallest" | Priority Queue | Heap-based selection |
| "Palindrome", "Reverse" | Two Pointers | Start/end comparison |
| "Frequency", "Count" | HashMap | Counting requirement |
| "Cycle", "Floyd" | Fast/Slow Pointers | Linked list cycle |
| "Stack", "LIFO", "Parentheses" | Stack | Last-in-first-out |

---

## 📝 **ESSENTIAL CODE TEMPLATES**

### **🔢 Arrays - Two Pointers**
```java
public int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) return new int[]{left, right};
        else if (sum < target) left++;
        else right--;
    }
    return new int[]{-1, -1};
}
```

### **🪟 Arrays - Sliding Window**
```java
public int maxSubarraySum(int[] nums, int k) {
    int windowSum = 0, maxSum = 0;
    
    // Initial window
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    maxSum = windowSum;
    
    // Slide window
    for (int i = k; i < nums.length; i++) {
        windowSum = windowSum - nums[i - k] + nums[i];
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}
```

### **🔍 Binary Search**
```java
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

### **🔄 DFS Traversal**
```java
public void dfs(TreeNode root) {
    if (root == null) return;
    
    // Process current node
    System.out.println(root.val);
    
    // Recurse on children
    dfs(root.left);
    dfs(root.right);
}
```

### **🌊 BFS Traversal**
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> level = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

### **🐢🐰 Fast/Slow Pointers**
```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if (slow == fast) return true;
    }
    return false;
}
```

### **📚 Monotonic Stack**
```java
public int[] nextGreaterElement(int[] nums) {
    int[] result = new int[nums.length];
    Stack<Integer> stack = new Stack<>();
    
    for (int i = nums.length - 1; i >= 0; i--) {
        while (!stack.isEmpty() && stack.peek() <= nums[i]) {
            stack.pop();
        }
        
        result[i] = stack.isEmpty() ? -1 : stack.peek();
        stack.push(nums[i]);
    }
    return result;
}
```

### **🧠 Basic Dynamic Programming**
```java
public int fibonacci(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}

// Space-optimized version
public int fibonacciOptimized(int n) {
    if (n <= 1) return n;
    
    int prev2 = 0, prev1 = 1;
    for (int i = 2; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

### **⛰️ Priority Queue (Min Heap)**
```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }
    return minHeap.peek();
}
```

### **🗺️ HashMap Pattern**
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[]{-1, -1};
}
```

---

## ⏱️ **TIME & SPACE COMPLEXITY CHEAT SHEET**

### **📊 Data Structure Operations**

| Data Structure | Access | Search | Insertion | Deletion | Space |
|----------------|--------|--------|-----------|----------|--------|
| Array | O(1) | O(n) | O(n) | O(n) | O(n) |
| Dynamic Array | O(1) | O(n) | O(1)* | O(n) | O(n) |
| Linked List | O(n) | O(n) | O(1) | O(1) | O(n) |
| Stack | O(n) | O(n) | O(1) | O(1) | O(n) |
| Queue | O(n) | O(n) | O(1) | O(1) | O(n) |
| Hash Table | N/A | O(1)* | O(1)* | O(1)* | O(n) |
| Binary Search Tree | O(log n)* | O(log n)* | O(log n)* | O(log n)* | O(n) |
| Heap | N/A | O(n) | O(log n) | O(log n) | O(n) |

### **🔍 Algorithm Complexities**

| Algorithm | Best | Average | Worst | Space |
|-----------|------|---------|-------|--------|
| Binary Search | O(1) | O(log n) | O(log n) | O(1) |
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) |
| DFS | O(V + E) | O(V + E) | O(V + E) | O(V) |
| BFS | O(V + E) | O(V + E) | O(V + E) | O(V) |
| Dijkstra | O((V + E) log V) | O((V + E) log V) | O((V + E) log V) | O(V) |

---

## 🎯 **PATTERN-SPECIFIC COMPLEXITIES**

### **📊 Arrays**
- **Two Pointers**: Time O(n), Space O(1)
- **Sliding Window**: Time O(n), Space O(1)
- **Prefix Sums**: Time O(n), Space O(n)
- **Merge Intervals**: Time O(n log n), Space O(n)

### **🔤 Strings**
- **String Sliding Window**: Time O(n), Space O(k)
- **HashMap Frequency**: Time O(n), Space O(k)
- **Pattern Matching**: Time O(m×n), Space O(m×n)

### **🌳 Trees**
- **Tree Traversal**: Time O(n), Space O(h)
- **Tree Construction**: Time O(n), Space O(n)
- **Path Finding**: Time O(n), Space O(h)

### **📚 Stack/Queue**
- **Monotonic Stack**: Time O(n), Space O(n)
- **Expression Evaluation**: Time O(n), Space O(n)

### **⛰️ Priority Queue**
- **Top K Problems**: Time O(n log k), Space O(k)
- **Merge K Lists**: Time O(n log k), Space O(k)

### **🧠 Dynamic Programming**
- **1D DP**: Time O(n), Space O(n) → O(1)
- **2D DP**: Time O(m×n), Space O(m×n) → O(n)
- **Knapsack**: Time O(n×W), Space O(W)

### **🕸️ Graphs**
- **DFS/BFS**: Time O(V + E), Space O(V)
- **Shortest Path**: Time O((V + E) log V), Space O(V)
- **MST**: Time O(E log E), Space O(V)

---

## 🚨 **COMMON EDGE CASES**

### **🔢 Arrays/Strings**
- Empty array/string: `[]`, `""`
- Single element: `[1]`, `"a"`
- All same elements: `[1,1,1]`, `"aaa"`
- Sorted/reverse sorted: `[1,2,3]`, `[3,2,1]`

### **🔗 Linked Lists**
- Empty list: `null`
- Single node: `1 -> null`
- Cycle: `1 -> 2 -> 3 -> 2` (back to 2)

### **🌳 Trees**
- Empty tree: `null`
- Single node: `TreeNode(1)`
- Only left/right children
- Unbalanced tree (skewed)

### **🔢 Numbers**
- Zero: `0`
- Negative numbers: `-1`, `-100`
- Integer overflow: `Integer.MAX_VALUE`
- Large numbers requiring long

---

## 💡 **INTERVIEW TIPS**

### **🎯 Problem-Solving Approach**
1. **Clarify**: Ask about constraints, edge cases
2. **Examples**: Work through 2-3 examples
3. **Approach**: Explain brute force first
4. **Optimize**: Identify patterns, optimize
5. **Code**: Write clean, readable code
6. **Test**: Walk through with examples

### **⚡ Time Management**
- **2-3 minutes**: Understand problem
- **3-5 minutes**: Discuss approach
- **15-20 minutes**: Code solution
- **3-5 minutes**: Test and debug

### **🗣️ Communication**
- Think out loud
- Explain your approach before coding
- Mention time/space complexity
- Discuss trade-offs
- Ask clarifying questions

### **🔧 Coding Best Practices**
- Use meaningful variable names
- Handle edge cases
- Add comments for complex logic
- Optimize space when possible
- Clean up after solving

---

## 🎨 **COMMON MISTAKES TO AVOID**

### **🚫 Logic Errors**
- Off-by-one errors in loops
- Integer overflow
- Null pointer exceptions
- Array bounds violations

### **🚫 Complexity Issues**
- Not optimizing when asked
- Missing better complexity solutions
- Not considering space optimization

### **🚫 Communication**
- Jumping to code without explanation
- Not handling edge cases
- Silent coding without explanation

---

## 🏆 **FINAL CHECKLIST**

Before submitting your solution:

✅ **Correctness**: Does it solve all test cases?  
✅ **Complexity**: Is it optimal in time/space?  
✅ **Edge Cases**: Are boundary conditions handled?  
✅ **Clean Code**: Is it readable and well-structured?  
✅ **Explanation**: Can you explain your approach clearly?  

---

*Keep this handy during interviews for quick pattern lookup and complexity reference!* 