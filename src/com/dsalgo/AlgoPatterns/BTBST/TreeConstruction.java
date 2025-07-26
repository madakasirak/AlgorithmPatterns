package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🏗️ BINARY TREE CONSTRUCTION PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary tree construction algorithms
 * for building trees from various input formats including traversal sequences, arrays,
 * and serialized data. These algorithms demonstrate recursive decomposition, index management,
 * constraint satisfaction, and optimization techniques for efficient tree reconstruction
 * from different data representations and structural specifications.
 * 
 * Pattern Focus: Tree reconstruction, traversal relationships, constraint satisfaction
 * Time Complexity: Generally O(n) for tree construction where n is number of nodes
 * Space Complexity: O(n) for tree storage plus O(h) for recursion where h is tree height
 */

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TreeConstruction {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Tree Construction Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Construct Binary Tree from Preorder and Inorder Traversal
     * 
     * Problem: Build binary tree from preorder and inorder traversal arrays
     * LeetCode: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * 
     * Approach: Use preorder for root identification, inorder for left/right partitioning
     * - First element in preorder is always the root
     * - Find root position in inorder to determine left and right subtrees
     * - Recursively construct left and right subtrees with corresponding ranges
     * 
     * Time: O(n), Space: O(n) for hashmap + O(h) for recursion
     */
    public static TreeNode buildTreePreorderInorder(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        
        // Create map for O(1) inorder index lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildHelper(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1, inorderMap);
    }
    
    private static TreeNode buildHelper(int[] preorder, int preStart, int preEnd,
                                      int[] inorder, int inStart, int inEnd,
                                      Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        
        // Root is first element in current preorder range
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        // Find root position in inorder for subtree partitioning
        int rootIndex = inorderMap.get(rootVal);
        int leftSubtreeSize = rootIndex - inStart;
        
        // Recursively build left and right subtrees
        root.left = buildHelper(preorder, preStart + 1, preStart + leftSubtreeSize,
                               inorder, inStart, rootIndex - 1, inorderMap);
        root.right = buildHelper(preorder, preStart + leftSubtreeSize + 1, preEnd,
                                inorder, rootIndex + 1, inEnd, inorderMap);
        
        return root;
    }
    
    /**
     * 🟢 EASY: Construct Binary Tree from Inorder and Postorder Traversal
     * 
     * Problem: Build binary tree from inorder and postorder traversal arrays
     * LeetCode: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * 
     * Approach: Use postorder for root identification (last element), inorder for partitioning
     * - Last element in postorder is always the root
     * - Find root position in inorder to determine subtree boundaries
     * - Recursively construct subtrees with proper index management
     * 
     * Time: O(n), Space: O(n) for hashmap + O(h) for recursion
     */
    public static TreeNode buildTreeInorderPostorder(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return buildHelperInPost(inorder, 0, inorder.length - 1,
                                postorder, 0, postorder.length - 1, inorderMap);
    }
    
    private static TreeNode buildHelperInPost(int[] inorder, int inStart, int inEnd,
                                             int[] postorder, int postStart, int postEnd,
                                             Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd || postStart > postEnd) return null;
        
        // Root is last element in current postorder range
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        
        int rootIndex = inorderMap.get(rootVal);
        int leftSubtreeSize = rootIndex - inStart;
        
        // Build left subtree first, then right subtree
        root.left = buildHelperInPost(inorder, inStart, rootIndex - 1,
                                     postorder, postStart, postStart + leftSubtreeSize - 1,
                                     inorderMap);
        root.right = buildHelperInPost(inorder, rootIndex + 1, inEnd,
                                      postorder, postStart + leftSubtreeSize, postEnd - 1,
                                      inorderMap);
        
        return root;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Tree Construction Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Construct Binary Tree from Preorder and Postorder Traversal
     * 
     * Problem: Build binary tree from preorder and postorder traversal arrays
     * LeetCode: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
     * 
     * Approach: Works for full binary trees, use preorder for roots and postorder for subtree sizes
     * - First element in preorder is root
     * - Second element in preorder is left child (if exists)
     * - Find left child in postorder to determine subtree boundaries
     * - Note: Solution may not be unique for incomplete binary trees
     * 
     * Time: O(n²) due to linear search, Space: O(h) for recursion
     */
    public static TreeNode buildTreePreorderPostorder(int[] preorder, int[] postorder) {
        return buildHelperPrePost(preorder, 0, preorder.length - 1,
                                 postorder, 0, postorder.length - 1);
    }
    
    private static TreeNode buildHelperPrePost(int[] preorder, int preStart, int preEnd,
                                              int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd) return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        
        if (preStart == preEnd) return root; // Single node
        
        // Find left child in postorder to determine subtree sizes
        int leftChildVal = preorder[preStart + 1];
        int leftChildIndex = -1;
        
        for (int i = postStart; i <= postEnd; i++) {
            if (postorder[i] == leftChildVal) {
                leftChildIndex = i;
                break;
            }
        }
        
        int leftSubtreeSize = leftChildIndex - postStart + 1;
        
        // Recursively build left and right subtrees
        root.left = buildHelperPrePost(preorder, preStart + 1, preStart + leftSubtreeSize,
                                      postorder, postStart, leftChildIndex);
        root.right = buildHelperPrePost(preorder, preStart + leftSubtreeSize + 1, preEnd,
                                       postorder, leftChildIndex + 1, postEnd - 1);
        
        return root;
    }
    
    /**
     * 🟡 MEDIUM: Daily Temperatures
     * 
     * Problem: Find next warmer temperature for each day
     * LeetCode: https://leetcode.com/problems/daily-temperatures/
     * 
     * Approach: Monotonic stack to find next greater element
     * - Use stack to maintain indices of temperatures in decreasing order
     * - For each temperature, pop smaller temperatures and calculate distances
     * - Push current index to stack for future processing
     * 
     * Time: O(n), Space: O(n)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Pop indices with smaller temperatures
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex; // Distance to warmer day
            }
            
            stack.push(i); // Push current index
        }
        
        return result; // Remaining indices have no warmer day (already 0)
    }
    
    /**
     * 🟡 MEDIUM: Remove Duplicate Letters
     * 
     * Problem: Remove duplicate letters so result is smallest in lexicographical order
     * LeetCode: https://leetcode.com/problems/remove-duplicate-letters/
     * 
     * Approach: Greedy with monotonic stack
     * - Count frequency of each character
     * - Use stack to build result, maintaining lexicographical order
     * - Remove larger characters if they appear later and current is smaller
     * - Track characters already in result to avoid duplicates
     * 
     * Time: O(n), Space: O(1) - fixed alphabet size
     */
    public static String removeDuplicateLetters(String s) {
        int[] count = new int[26]; // Count frequency of each character
        boolean[] inStack = new boolean[26]; // Track characters in result
        
        // Count character frequencies
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            count[c - 'a']--; // Decrease remaining count
            
            if (inStack[c - 'a']) continue; // Skip if already in result
            
            // Remove larger characters that appear later
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                char removed = stack.pop();
                inStack[removed - 'a'] = false;
            }
            
            stack.push(c);
            inStack[c - 'a'] = true;
        }
        
        // Build result string
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Tree Construction Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Construct BST from Preorder Traversal
     * 
     * Problem: Build BST from preorder traversal (unique solution due to BST property)
     * 
     * Approach: Use BST property with bounds checking
     * - Each node has min/max bounds based on BST property
     * - Process preorder elements respecting bounds
     * - Skip elements that violate bounds for current subtree
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorderHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, new int[]{0});
    }
    
    private static TreeNode bstFromPreorderHelper(int[] preorder, int min, int max, int[] index) {
        if (index[0] >= preorder.length) return null;
        
        int val = preorder[index[0]];
        if (val < min || val > max) return null; // Violates BST bounds
        
        index[0]++; // Consume current element
        TreeNode root = new TreeNode(val);
        
        // Left subtree: values must be less than current
        root.left = bstFromPreorderHelper(preorder, min, val, index);
        // Right subtree: values must be greater than current
        root.right = bstFromPreorderHelper(preorder, val, max, index);
        
        return root;
    }
    
    /**
     * 🔴 HARD: Maximum Binary Tree
     * 
     * Problem: Construct tree where each node is maximum of its subtree range
     * 
     * Approach: Divide and conquer with maximum element as root
     * - Find maximum element in current range
     * - Use maximum as root, recursively build left and right subtrees
     * - Left subtree from elements before max, right subtree from elements after max
     * 
     * Time: O(n²) worst case, O(n log n) average, Space: O(h)
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaxBTHelper(nums, 0, nums.length - 1);
    }
    
    private static TreeNode constructMaxBTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        
        // Find maximum element and its index
        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = constructMaxBTHelper(nums, left, maxIndex - 1);
        root.right = constructMaxBTHelper(nums, maxIndex + 1, right);
        
        return root;
    }
    
    /**
     * 🔴 HARD: Serialize and Deserialize Binary Tree
     * 
     * Problem: Design algorithm to serialize and deserialize binary tree
     * 
     * Approach: Preorder traversal with null markers
     * - Serialize: preorder traversal with special null markers
     * - Deserialize: reconstruct tree using queue of serialized values
     * - Handle null nodes explicitly for complete reconstruction
     * 
     * Time: O(n) for both operations, Space: O(n)
     */
    public static class Codec {
        private static final String NULL_MARKER = "null";
        private static final String DELIMITER = ",";
        
        // Encodes a tree to a single string
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }
        
        private void serializeHelper(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
                return;
            }
            
            sb.append(node.val).append(DELIMITER);
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
        
        // Decodes your encoded data to tree
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
            return deserializeHelper(queue);
        }
        
        private TreeNode deserializeHelper(Queue<String> queue) {
            String val = queue.poll();
            
            if (NULL_MARKER.equals(val)) return null;
            
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = deserializeHelper(queue);
            node.right = deserializeHelper(queue);
            
            return node;
        }
    }
    
    /**
     * 🔴 HARD: Convert Sorted Array to Balanced BST
     * 
     * Problem: Build height-balanced BST from sorted array
     * 
     * Approach: Divide and conquer with middle element as root
     * - Choose middle element as root to ensure balance
     * - Recursively build left subtree from left half
     * - Recursively build right subtree from right half
     * - Results in perfectly balanced BST
     * 
     * Time: O(n), Space: O(log n) for recursion
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }
    
    private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2; // Avoid overflow
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        
        return root;
    }
    
    /**
     * 🔴 HARD: Merge Two Binary Trees
     * 
     * Problem: Merge two binary trees by adding overlapping node values
     * 
     * Approach: Simultaneous traversal with value addition
     * - Traverse both trees simultaneously
     * - Add values for overlapping nodes
     * - Use existing nodes when one tree is null
     * - Create new nodes for merged result
     * 
     * Time: O(min(m,n)), Space: O(min(m,n)) for recursion
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        
        // Create new node with merged values
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        
        return merged;
    }
    
    /**
     * 🔴 HARD: Construct Binary Tree from String with Brackets
     * 
     * Problem: Build tree from string representation with parentheses
     * 
     * Approach: Stack-based parsing with bracket matching
     * - Parse numbers and handle negative values
     * - Use stack to track parent-child relationships
     * - Left child appears first, right child appears second
     * - Handle nested parentheses for subtree construction
     * 
     * Time: O(n), Space: O(h) for stack
     */
    public static TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;
        
        while (i < s.length()) {
            if (s.charAt(i) == ')') {
                stack.pop(); // Close current subtree
                i++;
            } else if (s.charAt(i) == '(') {
                i++; // Open new subtree
            } else {
                // Parse number (handle negative values)
                int start = i;
                if (s.charAt(i) == '-') i++;
                while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
                
                int val = Integer.parseInt(s.substring(start, i));
                TreeNode node = new TreeNode(val);
                
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                }
                
                stack.push(node);
            }
        }
        
        return stack.isEmpty() ? null : stack.peek();
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    // Helper method to print tree in level order for testing
    public static List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add(null);
            }
        }
        
        // Remove trailing nulls for cleaner output
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("🏗️ BINARY TREE CONSTRUCTION PATTERN - PRACTICE PROBLEMS");
        System.out.println("=========================================================");
        
        // Test Basic Construction Problems
        System.out.println("\n🟢 BASIC TREE CONSTRUCTION PROBLEMS:");
        
        // Preorder + Inorder Construction
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode tree1 = buildTreePreorderInorder(preorder, inorder);
        System.out.println("Preorder + Inorder: " + levelOrderTraversal(tree1));
        
        // Inorder + Postorder Construction
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode tree2 = buildTreeInorderPostorder(inorder, postorder);
        System.out.println("Inorder + Postorder: " + levelOrderTraversal(tree2));
        
        // Test Intermediate Problems
        System.out.println("\n🟡 INTERMEDIATE TREE CONSTRUCTION PROBLEMS:");
        
        // Preorder + Postorder Construction
        int[] preorder2 = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder2 = {4, 5, 2, 6, 7, 3, 1};
        TreeNode tree3 = buildTreePreorderPostorder(preorder2, postorder2);
        System.out.println("Preorder + Postorder: " + levelOrderTraversal(tree3));
        
        // Daily Temperatures
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] dailyResult = dailyTemperatures(temperatures);
        System.out.println("Daily Temperatures: " + Arrays.toString(dailyResult));
        
        // Remove Duplicate Letters
        String duplicateString = "bcabc";
        String removedDuplicates = removeDuplicateLetters(duplicateString);
        System.out.println("Remove Duplicates '" + duplicateString + "': " + removedDuplicates);
        
        String duplicateString2 = "cbacdcbc";
        String removedDuplicates2 = removeDuplicateLetters(duplicateString2);
        System.out.println("Remove Duplicates '" + duplicateString2 + "': " + removedDuplicates2);
        
        // Test Advanced Problems
        System.out.println("\n🔴 ADVANCED TREE CONSTRUCTION PROBLEMS:");
        
        // BST from Preorder
        int[] bstPreorder = {8, 5, 1, 7, 10, 12};
        TreeNode bstTree = bstFromPreorder(bstPreorder);
        System.out.println("BST from Preorder: " + levelOrderTraversal(bstTree));
        
        // Maximum Binary Tree
        int[] maxTreeArray = {3, 2, 1, 6, 0, 5};
        TreeNode maxTree = constructMaximumBinaryTree(maxTreeArray);
        System.out.println("Maximum Binary Tree: " + levelOrderTraversal(maxTree));
        
        // Serialization
        Codec codec = new Codec();
        String serialized = codec.serialize(tree1);
        System.out.println("Serialized tree: " + serialized);
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized tree: " + levelOrderTraversal(deserialized));
        
        // Sorted Array to BST
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode balancedBST = sortedArrayToBST(sortedArray);
        System.out.println("Balanced BST: " + levelOrderTraversal(balancedBST));
        
        // Merge Trees
        TreeNode tree4 = buildTreePreorderInorder(new int[]{1, 3, 2}, new int[]{3, 1, 2});
        TreeNode tree5 = buildTreePreorderInorder(new int[]{2, 1, 3}, new int[]{1, 2, 3});
        TreeNode mergedTree = mergeTrees(tree4, tree5);
        System.out.println("Merged Trees: " + levelOrderTraversal(mergedTree));
        
        // String to Tree
        String treeString = "4(2(3)(1))(6(5))";
        TreeNode stringTree = str2tree(treeString);
        System.out.println("String to Tree '" + treeString + "': " + levelOrderTraversal(stringTree));
        
        System.out.println("\n✅ Key Binary Tree Construction Principles:");
        System.out.println("1. Understand traversal relationships for unique tree identification");
        System.out.println("2. Use recursive decomposition to break into subtree problems");
        System.out.println("3. Manage indices and boundaries efficiently during construction");
        System.out.println("4. Optimize lookups with hash maps for O(1) index access");
        System.out.println("5. Handle edge cases (null values, empty inputs, single nodes)");
        System.out.println("6. Use monotonic stacks for next greater/smaller element problems");
        System.out.println("7. Apply greedy strategies with stack for lexicographical optimization");
        System.out.println("8. Validate constructed trees against input constraints and properties");
    }
} 