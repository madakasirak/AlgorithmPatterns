package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🌳 BINARY TREE TRAVERSAL PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary tree traversal algorithms
 * for systematically visiting each node exactly once in specific orders. These algorithms
 * demonstrate both depth-first (DFS) and breadth-first (BFS) approaches using recursive
 * and iterative methods, providing different perspectives on tree data organization
 * and serving as foundations for more complex tree algorithms.
 * 
 * Pattern Focus: Systematic node visitation, order preservation, complete exploration
 * Time Complexity: O(n) for all traversals where n is number of nodes
 * Space Complexity: O(h) for DFS recursion/stack, O(w) for BFS queue where h=height, w=width
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

public class TreeTraversal {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Tree Traversal Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Binary Tree Inorder Traversal
     * 
     * Problem: Return inorder traversal of binary tree values
     * LeetCode: https://leetcode.com/problems/binary-tree-inorder-traversal/
     * 
     * Approach: Left → Root → Right order
     * - Recursive: natural implementation following definition
     * - Iterative: use stack to simulate recursion
     * - Morris: constant space using threading technique
     * 
     * Time: O(n), Space: O(h) recursive/iterative, O(1) Morris
     */
    
    // Recursive Solution - Most Natural
    public static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    
    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        inorderHelper(node.left, result);    // Visit left subtree
        result.add(node.val);               // Process current node
        inorderHelper(node.right, result);   // Visit right subtree
    }
    
    // Iterative Solution - Explicit Stack
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node of current subtree
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process current node
            current = stack.pop();
            result.add(current.val);
            
            // Move to right subtree
            current = current.right;
        }
        
        return result;
    }
    
    // Morris Traversal - Constant Space
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        
        while (current != null) {
            if (current.left == null) {
                // No left subtree, process current and go right
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Create thread
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Remove thread and process current
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Binary Tree Preorder Traversal
     * 
     * Problem: Return preorder traversal of binary tree values
     * LeetCode: https://leetcode.com/problems/binary-tree-preorder-traversal/
     * 
     * Approach: Root → Left → Right order
     * - Process root first, then recursively traverse subtrees
     * - Useful for tree copying, serialization, prefix expressions
     * 
     * Time: O(n), Space: O(h) recursive/iterative, O(1) Morris
     */
    
    // Recursive Solution
    public static List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    
    private static void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        result.add(node.val);               // Process current node
        preorderHelper(node.left, result);  // Visit left subtree
        preorderHelper(node.right, result); // Visit right subtree
    }
    
    // Iterative Solution
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            
            // Push right first, then left (stack is LIFO)
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        
        return result;
    }
    
    // Morris Traversal
    public static List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        
        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Create thread and process current
                    result.add(current.val);
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Remove thread
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Binary Tree Postorder Traversal
     * 
     * Problem: Return postorder traversal of binary tree values
     * LeetCode: https://leetcode.com/problems/binary-tree-postorder-traversal/
     * 
     * Approach: Left → Right → Root order
     * - Process children before parent
     * - Useful for tree deletion, postfix expressions, dependency resolution
     * 
     * Time: O(n), Space: O(h) for recursion/stack
     */
    
    // Recursive Solution
    public static List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }
    
    private static void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        postorderHelper(node.left, result);  // Visit left subtree
        postorderHelper(node.right, result); // Visit right subtree
        result.add(node.val);               // Process current node
    }
    
    // Iterative Solution - Two Stacks
    public static List<Integer> postorderTraversalTwoStacks(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        
        stack1.push(root);
        
        // First stack: modified preorder (root, right, left)
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        
        // Second stack: reverse to get postorder
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        
        return result;
    }
    
    // Iterative Solution - Single Stack
    public static List<Integer> postorderTraversalSingleStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisited = null;
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode peekNode = stack.peek();
                
                // If right child exists and hasn't been processed
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        
        return result;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Tree Traversal Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Binary Tree Level Order Traversal
     * 
     * Problem: Return level order traversal of binary tree values
     * LeetCode: https://leetcode.com/problems/binary-tree-level-order-traversal/
     * 
     * Approach: Breadth-First Search (BFS)
     * - Process nodes level by level from left to right
     * - Use queue to maintain processing order
     * - Track level boundaries for grouping
     * 
     * Time: O(n), Space: O(w) where w is maximum width
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                // Add children for next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    // Alternative: Level Order using Two Queues
    public static List<List<Integer>> levelOrderTwoQueues(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> currentLevel = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        
        currentLevel.offer(root);
        
        while (!currentLevel.isEmpty()) {
            List<Integer> levelValues = new ArrayList<>();
            
            // Process all nodes in current level
            while (!currentLevel.isEmpty()) {
                TreeNode node = currentLevel.poll();
                levelValues.add(node.val);
                
                // Add children to next level
                if (node.left != null) nextLevel.offer(node.left);
                if (node.right != null) nextLevel.offer(node.right);
            }
            
            result.add(levelValues);
            
            // Swap queues for next iteration
            Queue<TreeNode> temp = currentLevel;
            currentLevel = nextLevel;
            nextLevel = temp;
        }
        
        return result;
    }
    
    /**
     * 🟡 MEDIUM: Binary Tree Zigzag Level Order Traversal
     * 
     * Problem: Return zigzag level order traversal of binary tree values
     * LeetCode: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     * 
     * Approach: Modified BFS with alternating direction
     * - Odd levels: left to right
     * - Even levels: right to left
     * - Use deque for efficient both-ends insertion
     * 
     * Time: O(n), Space: O(w) where w is maximum width
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(node.val);
                } else {
                    currentLevel.add(0, node.val); // Add to beginning
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight; // Alternate direction
        }
        
        return result;
    }
    
    // Alternative: Zigzag using Deque
    public static List<List<Integer>> zigzagLevelOrderDeque(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        boolean leftToRight = true;
        
        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node;
                
                if (leftToRight) {
                    node = deque.pollFirst();
                    currentLevel.add(node.val);
                    
                    if (node.left != null) deque.offerLast(node.left);
                    if (node.right != null) deque.offerLast(node.right);
                } else {
                    node = deque.pollLast();
                    currentLevel.add(node.val);
                    
                    if (node.right != null) deque.offerFirst(node.right);
                    if (node.left != null) deque.offerFirst(node.left);
                }
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Tree Traversal Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Binary Tree Right Side View
     * 
     * Problem: Return values of nodes you can see from right side
     * 
     * Approach: Level order traversal, capture rightmost at each level
     * - BFS with level tracking
     * - Alternative: DFS with depth tracking
     * 
     * Time: O(n), Space: O(h) for DFS, O(w) for BFS
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // If this is the rightmost node at current level
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    // DFS Alternative for Right Side View
    public static List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightSideViewHelper(root, 0, result);
        return result;
    }
    
    private static void rightSideViewHelper(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;
        
        // First time visiting this depth level
        if (depth == result.size()) {
            result.add(node.val);
        }
        
        // Visit right first to ensure rightmost node is captured
        rightSideViewHelper(node.right, depth + 1, result);
        rightSideViewHelper(node.left, depth + 1, result);
    }
    
    /**
     * 🔴 HARD: Binary Tree Vertical Order Traversal
     * 
     * Problem: Return vertical order traversal of binary tree
     * 
     * Approach: Column-based grouping with coordinate system
     * - Assign column coordinates: left child = col-1, right child = col+1
     * - Group nodes by column, then by row, then by value
     * - Use TreeMap for sorted column order
     * 
     * Time: O(n log n), Space: O(n)
     */
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        // Map: column -> (row -> sorted values)
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        
        // DFS to populate coordinate map
        dfsVertical(root, 0, 0, map);
        
        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, List<Integer>> rowMap : map.values()) {
            List<Integer> column = new ArrayList<>();
            for (List<Integer> values : rowMap.values()) {
                Collections.sort(values); // Sort values at same position
                column.addAll(values);
            }
            result.add(column);
        }
        
        return result;
    }
    
    private static void dfsVertical(TreeNode node, int row, int col,
                                  TreeMap<Integer, TreeMap<Integer, List<Integer>>> map) {
        if (node == null) return;
        
        map.computeIfAbsent(col, k -> new TreeMap<>())
           .computeIfAbsent(row, k -> new ArrayList<>())
           .add(node.val);
        
        dfsVertical(node.left, row + 1, col - 1, map);
        dfsVertical(node.right, row + 1, col + 1, map);
    }
    
    /**
     * 🔴 HARD: Binary Tree Boundary Traversal
     * 
     * Problem: Return boundary of binary tree in anticlockwise direction
     * 
     * Approach: Three-part traversal
     * 1. Left boundary (excluding leaves)
     * 2. All leaf nodes (left to right)
     * 3. Right boundary (excluding leaves, bottom to top)
     * 
     * Time: O(n), Space: O(h)
     */
    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        // Add root if not a leaf
        if (!isLeaf(root)) {
            result.add(root.val);
        }
        
        // Add left boundary
        addLeftBoundary(root.left, result);
        
        // Add all leaves
        addLeaves(root, result);
        
        // Add right boundary
        addRightBoundary(root.right, result);
        
        return result;
    }
    
    private static boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
    
    private static void addLeftBoundary(TreeNode node, List<Integer> result) {
        while (node != null && !isLeaf(node)) {
            result.add(node.val);
            node = (node.left != null) ? node.left : node.right;
        }
    }
    
    private static void addRightBoundary(TreeNode node, List<Integer> result) {
        List<Integer> temp = new ArrayList<>();
        while (node != null && !isLeaf(node)) {
            temp.add(node.val);
            node = (node.right != null) ? node.right : node.left;
        }
        
        // Add in reverse order
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }
    
    private static void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        if (isLeaf(node)) {
            result.add(node.val);
            return;
        }
        
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }
    
    /**
     * 🔴 HARD: Serialize and Deserialize Binary Tree
     * 
     * Problem: Design algorithm to serialize and deserialize binary tree
     * 
     * Approach: Preorder traversal with null markers
     * - Serialize: preorder with null placeholders
     * - Deserialize: reconstruct using queue of values
     * 
     * Time: O(n), Space: O(n)
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
            
            if (NULL_MARKER.equals(val)) {
                return null;
            }
            
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = deserializeHelper(queue);
            node.right = deserializeHelper(queue);
            
            return node;
        }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🌳 BINARY TREE TRAVERSAL PATTERN - PRACTICE PROBLEMS");
        System.out.println("====================================================");
        
        // Create sample tree for testing
        //       3
        //      / \
        //     9   20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        System.out.println("\n📖 Tree Structure:");
        System.out.println("       3");
        System.out.println("      / \\");
        System.out.println("     9   20");
        System.out.println("        /  \\");
        System.out.println("       15   7");
        
        // Test Basic Traversals
        System.out.println("\n🟢 BASIC TRAVERSALS:");
        
        // Inorder Traversal
        System.out.println("Inorder Recursive: " + inorderTraversalRecursive(root));
        System.out.println("Inorder Iterative: " + inorderTraversalIterative(root));
        System.out.println("Inorder Morris: " + inorderTraversalMorris(root));
        
        // Preorder Traversal
        System.out.println("Preorder Recursive: " + preorderTraversalRecursive(root));
        System.out.println("Preorder Iterative: " + preorderTraversalIterative(root));
        System.out.println("Preorder Morris: " + preorderTraversalMorris(root));
        
        // Postorder Traversal
        System.out.println("Postorder Recursive: " + postorderTraversalRecursive(root));
        System.out.println("Postorder Two Stacks: " + postorderTraversalTwoStacks(root));
        System.out.println("Postorder Single Stack: " + postorderTraversalSingleStack(root));
        
        // Test Level-Based Traversals
        System.out.println("\n🟡 LEVEL-BASED TRAVERSALS:");
        
        // Level Order Traversal
        System.out.println("Level Order: " + levelOrder(root));
        System.out.println("Level Order (Two Queues): " + levelOrderTwoQueues(root));
        
        // Zigzag Level Order
        System.out.println("Zigzag Level Order: " + zigzagLevelOrder(root));
        System.out.println("Zigzag (Deque): " + zigzagLevelOrderDeque(root));
        
        // Test Advanced Traversals
        System.out.println("\n🔴 ADVANCED TRAVERSALS:");
        
        // Right Side View
        System.out.println("Right Side View (BFS): " + rightSideView(root));
        System.out.println("Right Side View (DFS): " + rightSideViewDFS(root));
        
        // Vertical Order Traversal
        System.out.println("Vertical Traversal: " + verticalTraversal(root));
        
        // Boundary Traversal
        System.out.println("Boundary Traversal: " + boundaryTraversal(root));
        
        // Serialization
        Codec codec = new Codec();
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized Inorder: " + inorderTraversalRecursive(deserialized));
        
        System.out.println("\n✅ Key Binary Tree Traversal Principles:");
        System.out.println("1. Visit each node exactly once without missing any");
        System.out.println("2. Follow specific ordering rules based on problem requirements");
        System.out.println("3. Choose appropriate implementation (recursive/iterative/Morris)");
        System.out.println("4. DFS uses stack (explicit or recursion), BFS uses queue");
        System.out.println("5. Inorder gives sorted sequence for BST");
        System.out.println("6. Preorder useful for tree copying and serialization");
        System.out.println("7. Postorder useful for tree deletion and bottom-up processing");
        System.out.println("8. Level order useful for level-wise analysis and shortest paths");
    }
} 