package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🌳 BINARY TREE TRAVERSAL PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BINARY TREE TRAVERSAL PATTERN?
 * ============================================================================
 * 
 * Binary Tree Traversal Pattern involves systematically visiting each node in a
 * binary tree exactly once, following specific ordering rules. This fundamental
 * pattern is the foundation for most tree algorithms and provides different
 * perspectives on tree data based on the visitation order.
 * 
 * The pattern encompasses both depth-first (DFS) and breadth-first (BFS) approaches,
 * each serving different algorithmic needs and providing unique insights into tree
 * structure and data organization.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. SYSTEMATIC VISITATION: Visit each node exactly once without missing any
 * 2. ORDER PRESERVATION: Follow specific ordering rules (inorder, preorder, postorder, level)
 * 3. COMPLETE EXPLORATION: Ensure all nodes are accessible and processed
 * 4. FLEXIBLE IMPLEMENTATION: Support both recursive and iterative approaches
 * 
 * 🌳 BINARY TREE TRAVERSAL METAPHOR:
 * Think of tree traversal as "exploring a family tree":
 * - Preorder: meet the person, then visit their children (top-down exploration)
 * - Inorder: visit left family, meet the person, visit right family (sorted exploration)
 * - Postorder: visit all descendants first, then meet the person (bottom-up exploration)
 * - Level order: meet all people at each generation level by level (breadth-first exploration)
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BINARY TREE TRAVERSAL PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Visiting all nodes systematically without missing any
 * - Processing tree data in specific order requirements
 * - Tree serialization and deserialization
 * - Expression tree evaluation and manipulation
 * - Tree structure analysis and validation
 * - Path finding and tree search operations
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Visit each node exactly once"
 * - "Process all nodes in tree"
 * - "Inorder/preorder/postorder traversal"
 * - "Level by level processing"
 * - "Tree serialization"
 * - "Expression evaluation"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need specific node without full traversal (use tree search)
 * - Only need tree properties (use tree analysis patterns)
 * - Path-based operations (use path finding patterns)
 * - Tree modification during traversal (consider different patterns)
 * 
 * ============================================================================
 * 🔄 BINARY TREE TRAVERSAL PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ DEPTH-FIRST TRAVERSALS (DFS)
 * 
 * 🔹 PREORDER TRAVERSAL (Root → Left → Right)
 * - Visit root first, then left subtree, then right subtree
 * - Use case: Tree copying, prefix expression evaluation, tree serialization
 * - Memory: O(h) for recursion stack where h is tree height
 * 
 * 🔹 INORDER TRAVERSAL (Left → Root → Right)
 * - Visit left subtree, then root, then right subtree
 * - Use case: BST sorted output, expression trees, validation
 * - Memory: O(h) for recursion stack where h is tree height
 * 
 * 🔹 POSTORDER TRAVERSAL (Left → Right → Root)
 * - Visit left subtree, then right subtree, then root
 * - Use case: Tree deletion, postfix expression evaluation, tree analysis
 * - Memory: O(h) for recursion stack where h is tree height
 * 
 * 2️⃣ BREADTH-FIRST TRAVERSALS (BFS)
 * 
 * 🔹 LEVEL ORDER TRAVERSAL (Level by Level)
 * - Visit all nodes at depth 0, then depth 1, then depth 2, etc.
 * - Use case: Level-wise processing, shortest path, tree analysis
 * - Memory: O(w) where w is maximum width of tree
 * 
 * 🔹 ZIGZAG LEVEL ORDER TRAVERSAL
 * - Alternate direction for each level (left-to-right, then right-to-left)
 * - Use case: Special formatting, tree visualization, pattern problems
 * - Memory: O(w) where w is maximum width of tree
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 RECURSIVE DFS TEMPLATE:
 * ```
 * def traverse(node):
 *     if not node:
 *         return
 *     
 *     # Preorder: process here
 *     traverse(node.left)   # Recurse left
 *     # Inorder: process here
 *     traverse(node.right)  # Recurse right
 *     # Postorder: process here
 * ```
 * 
 * 🔹 ITERATIVE DFS WITH STACK:
 * ```
 * stack = [root]
 * while stack:
 *     node = stack.pop()
 *     process(node)
 *     if node.right: stack.append(node.right)
 *     if node.left: stack.append(node.left)
 * ```
 * 
 * 🔹 ITERATIVE BFS WITH QUEUE:
 * ```
 * queue = [root]
 * while queue:
 *     node = queue.pop(0)
 *     process(node)
 *     if node.left: queue.append(node.left)
 *     if node.right: queue.append(node.right)
 * ```
 * 
 * 🔹 MORRIS TRAVERSAL (CONSTANT SPACE):
 * ```
 * Use threading to navigate tree without recursion or stack
 * Temporarily modify tree structure to create navigation links
 * Restore original structure during traversal
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY TRAVERSAL REQUIREMENTS
 * - What order is needed for processing nodes?
 * - Is recursive or iterative approach preferred?
 * - Are there memory constraints (consider Morris traversal)?
 * 
 * STEP 2: CHOOSE TRAVERSAL TYPE
 * - Preorder: for top-down processing, copying, serialization
 * - Inorder: for sorted output (BST), expression evaluation
 * - Postorder: for bottom-up processing, deletion, calculation
 * - Level order: for level-wise processing, shortest paths
 * 
 * STEP 3: SELECT IMPLEMENTATION APPROACH
 * - Recursive: simpler code, natural for tree problems
 * - Iterative: explicit control, memory optimization
 * - Morris: constant space when memory is critical
 * 
 * STEP 4: HANDLE EDGE CASES
 * - Empty tree (null root)
 * - Single node tree
 * - Unbalanced trees (consider stack overflow)
 * 
 * STEP 5: OPTIMIZE FOR REQUIREMENTS
 * - Space complexity (Morris vs recursive vs iterative)
 * - Time complexity (all are O(n) but constants differ)
 * - Code maintainability and readability
 * 
 * ============================================================================
 * 🎨 BINARY TREE TRAVERSAL TEMPLATES
 * ============================================================================
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

public class TreeTraversalReadingGuide {
    
    /**
     * 🔹 PREORDER TRAVERSAL TEMPLATES
     * Visit: Root → Left → Right
     */
    public static class PreorderTraversalTemplates {
        
        // Recursive Approach - Most Natural
        public static List<Integer> preorderRecursive(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preorderHelper(root, result);
            return result;
        }
        
        private static void preorderHelper(TreeNode node, List<Integer> result) {
            if (node == null) return;
            
            result.add(node.val);           // Process root
            preorderHelper(node.left, result);   // Recurse left
            preorderHelper(node.right, result);  // Recurse right
        }
        
        // Iterative Approach - Explicit Stack
        public static List<Integer> preorderIterative(TreeNode root) {
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
        
        // Morris Traversal - Constant Space
        public static List<Integer> preorderMorris(TreeNode root) {
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
                        // Create thread
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
    }
    
    /**
     * 🔹 INORDER TRAVERSAL TEMPLATES
     * Visit: Left → Root → Right
     */
    public static class InorderTraversalTemplates {
        
        // Recursive Approach
        public static List<Integer> inorderRecursive(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            inorderHelper(root, result);
            return result;
        }
        
        private static void inorderHelper(TreeNode node, List<Integer> result) {
            if (node == null) return;
            
            inorderHelper(node.left, result);    // Recurse left
            result.add(node.val);               // Process root
            inorderHelper(node.right, result);   // Recurse right
        }
        
        // Iterative Approach
        public static List<Integer> inorderIterative(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;
            
            while (current != null || !stack.isEmpty()) {
                // Go to leftmost node
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
        
        // Morris Traversal
        public static List<Integer> inorderMorris(TreeNode root) {
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
    }
    
    /**
     * 🔹 POSTORDER TRAVERSAL TEMPLATES
     * Visit: Left → Right → Root
     */
    public static class PostorderTraversalTemplates {
        
        // Recursive Approach
        public static List<Integer> postorderRecursive(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            postorderHelper(root, result);
            return result;
        }
        
        private static void postorderHelper(TreeNode node, List<Integer> result) {
            if (node == null) return;
            
            postorderHelper(node.left, result);   // Recurse left
            postorderHelper(node.right, result);  // Recurse right
            result.add(node.val);                // Process root
        }
        
        // Iterative Approach - Two Stacks
        public static List<Integer> postorderIterativeTwoStacks(TreeNode root) {
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
        
        // Iterative Approach - Single Stack
        public static List<Integer> postorderIterativeSingleStack(TreeNode root) {
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
    }
    
    /**
     * 🔹 LEVEL ORDER TRAVERSAL TEMPLATES
     * Visit: Level by Level (BFS)
     */
    public static class LevelOrderTraversalTemplates {
        
        // Standard Level Order
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                List<Integer> currentLevel = new ArrayList<>();
                
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    currentLevel.add(node.val);
                    
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                
                result.add(currentLevel);
            }
            
            return result;
        }
        
        // Zigzag Level Order
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
        
        // Level Order using Two Queues
        public static List<List<Integer>> levelOrderTwoQueues(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            
            Queue<TreeNode> currentLevel = new LinkedList<>();
            Queue<TreeNode> nextLevel = new LinkedList<>();
            
            currentLevel.offer(root);
            
            while (!currentLevel.isEmpty()) {
                List<Integer> levelValues = new ArrayList<>();
                
                while (!currentLevel.isEmpty()) {
                    TreeNode node = currentLevel.poll();
                    levelValues.add(node.val);
                    
                    if (node.left != null) nextLevel.offer(node.left);
                    if (node.right != null) nextLevel.offer(node.right);
                }
                
                result.add(levelValues);
                
                // Swap queues
                Queue<TreeNode> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
            
            return result;
        }
    }
    
    /**
     * 🔹 SPECIALIZED TRAVERSAL TEMPLATES
     */
    public static class SpecializedTraversalTemplates {
        
        // Vertical Order Traversal
        public static List<List<Integer>> verticalTraversal(TreeNode root) {
            if (root == null) return new ArrayList<>();
            
            // Map: column -> (row -> sorted values)
            TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
            
            // DFS to populate map
            dfsVertical(root, 0, 0, map);
            
            List<List<Integer>> result = new ArrayList<>();
            for (TreeMap<Integer, List<Integer>> rowMap : map.values()) {
                List<Integer> column = new ArrayList<>();
                for (List<Integer> values : rowMap.values()) {
                    Collections.sort(values);
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
        
        // Diagonal Traversal
        public static List<List<Integer>> diagonalTraversal(TreeNode root) {
            if (root == null) return new ArrayList<>();
            
            Map<Integer, List<Integer>> diagonalMap = new HashMap<>();
            dfs(root, 0, diagonalMap);
            
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < diagonalMap.size(); i++) {
                result.add(diagonalMap.get(i));
            }
            
            return result;
        }
        
        private static void dfs(TreeNode node, int diagonal, Map<Integer, List<Integer>> map) {
            if (node == null) return;
            
            map.computeIfAbsent(diagonal, k -> new ArrayList<>()).add(node.val);
            
            dfs(node.left, diagonal + 1, map);  // Left increases diagonal
            dfs(node.right, diagonal, map);     // Right maintains diagonal
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Stack Overflow in Deep Trees
     * Recursive approaches can cause stack overflow in very deep trees
     */
    public static void stackOverflowExample() {
        // Use iterative approaches for very deep trees
        // Consider Morris traversal for constant space
        // Be aware of recursion depth limits
    }
    
    /**
     * ❌ PITFALL 2: Incorrect Order in Iterative Implementations
     * Easy to get wrong order when converting from recursive to iterative
     */
    public static void orderExample() {
        // For preorder: push right first, then left
        // For postorder: use two stacks or track last visited
        // For inorder: go left first, then process, then right
    }
    
    /**
     * ❌ PITFALL 3: Memory Leaks in Morris Traversal
     * Forgetting to restore original tree structure
     */
    public static void morrisExample() {
        // Always remove threads after using them
        // Restore original tree structure
        // Handle edge cases in threading logic
    }
    
    /**
     * ❌ PITFALL 4: Level Order Edge Cases
     * Not handling empty levels or single nodes correctly
     */
    public static void levelOrderExample() {
        // Check for null root before starting
        // Handle single node trees
        // Ensure all children are added to queue
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Approach
     * - Recursive: simple, natural for tree problems
     * - Iterative: better control, avoids stack overflow
     * - Morris: constant space when memory is critical
     */
    
    /**
     * 🎯 TIP 2: Understand Order Requirements
     * - Preorder: top-down processing, copying, serialization
     * - Inorder: sorted output for BST, expression evaluation
     * - Postorder: bottom-up processing, deletion, calculation
     * - Level order: level-wise processing, shortest paths
     */
    
    /**
     * 🎯 TIP 3: Optimize for Constraints
     * - Space-constrained: use Morris traversal
     * - Time-critical: recursive usually fastest
     * - Deep trees: use iterative to avoid stack overflow
     */
    
    /**
     * 🎯 TIP 4: Handle Edge Cases
     * - Always check for null root
     * - Consider single node trees
     * - Test with unbalanced trees
     */
    
    /**
     * 🎯 TIP 5: Combine Traversals
     * - Use multiple traversals for complex problems
     * - Combine DFS and BFS for different perspectives
     * - Consider specialized traversals for specific needs
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC TRAVERSAL PROBLEMS:
     * - Binary Tree Inorder Traversal
     * - Binary Tree Preorder Traversal
     * - Binary Tree Postorder Traversal
     * - Binary Tree Level Order Traversal
     * 
     * 🟡 INTERMEDIATE TRAVERSAL PROBLEMS:
     * - Binary Tree Zigzag Level Order Traversal
     * - Binary Tree Right Side View
     * - Binary Tree Vertical Order Traversal
     * - Binary Tree Diagonal Traversal
     * 
     * 🔴 ADVANCED TRAVERSAL PROBLEMS:
     * - Serialize and Deserialize Binary Tree
     * - Binary Tree Maximum Path Sum
     * - Construct Binary Tree from Traversals
     * - Morris Traversal Implementation
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE TRAVERSAL TYPE:
     * ✅ Preorder: Top-down processing, tree copying, serialization
     * ✅ Inorder: BST sorted output, expression evaluation
     * ✅ Postorder: Bottom-up processing, tree deletion, calculation
     * ✅ Level order: Level-wise processing, shortest path, BFS needs
     * 
     * CHOOSE IMPLEMENTATION:
     * 🔄 Recursive: Simple code, natural tree processing
     * 🔄 Iterative: Better control, memory optimization
     * 🔄 Morris: Constant space, complex implementation
     * 
     * OPTIMIZE FOR:
     * 🚀 Space complexity: Morris > Iterative > Recursive
     * 🚀 Code simplicity: Recursive > Iterative > Morris
     * 🚀 Deep trees: Iterative > Morris > Recursive
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - File system traversal (directory trees)
     * - Expression tree evaluation (mathematical expressions)
     * - Syntax tree processing (compilers, interpreters)
     * - DOM tree manipulation (web development)
     * - Decision tree traversal (machine learning)
     * - Organizational hierarchy processing (business applications)
     * - Game tree exploration (AI, game development)
     * - Database index traversal (B+ trees, search optimization)
     */
    
    public static void main(String[] args) {
        System.out.println("🌳 BINARY TREE TRAVERSAL PATTERN - READING GUIDE");
        System.out.println("=================================================");
        
        // Create a sample tree for demonstration
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        System.out.println("\n📖 Traversal Examples for Tree:");
        System.out.println("       1");
        System.out.println("      / \\");
        System.out.println("     2   3");
        System.out.println("    / \\");
        System.out.println("   4   5");
        
        // Preorder Examples
        System.out.println("\n🔹 PREORDER (Root → Left → Right):");
        System.out.println("Recursive: " + PreorderTraversalTemplates.preorderRecursive(root));
        System.out.println("Iterative: " + PreorderTraversalTemplates.preorderIterative(root));
        System.out.println("Morris: " + PreorderTraversalTemplates.preorderMorris(root));
        
        // Inorder Examples
        System.out.println("\n🔹 INORDER (Left → Root → Right):");
        System.out.println("Recursive: " + InorderTraversalTemplates.inorderRecursive(root));
        System.out.println("Iterative: " + InorderTraversalTemplates.inorderIterative(root));
        System.out.println("Morris: " + InorderTraversalTemplates.inorderMorris(root));
        
        // Postorder Examples
        System.out.println("\n🔹 POSTORDER (Left → Right → Root):");
        System.out.println("Recursive: " + PostorderTraversalTemplates.postorderRecursive(root));
        System.out.println("Two Stacks: " + PostorderTraversalTemplates.postorderIterativeTwoStacks(root));
        System.out.println("Single Stack: " + PostorderTraversalTemplates.postorderIterativeSingleStack(root));
        
        // Level Order Examples
        System.out.println("\n🔹 LEVEL ORDER:");
        System.out.println("Standard: " + LevelOrderTraversalTemplates.levelOrder(root));
        System.out.println("Zigzag: " + LevelOrderTraversalTemplates.zigzagLevelOrder(root));
        System.out.println("Two Queues: " + LevelOrderTraversalTemplates.levelOrderTwoQueues(root));
        
        System.out.println("\n✅ Key Binary Tree Traversal Principles:");
        System.out.println("1. Visit each node exactly once without missing any");
        System.out.println("2. Follow specific ordering rules based on requirements");
        System.out.println("3. Choose appropriate implementation (recursive/iterative/Morris)");
        System.out.println("4. Consider space and time complexity constraints");
        System.out.println("5. Handle edge cases (null trees, single nodes, deep trees)");
        System.out.println("6. Use right traversal type for specific problem needs");
        System.out.println("7. Combine different traversals for complex algorithms");
    }
} 