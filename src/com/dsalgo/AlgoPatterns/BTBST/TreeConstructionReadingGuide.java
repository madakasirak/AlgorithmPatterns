package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🏗️ TREE CONSTRUCTION PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS TREE CONSTRUCTION?
 * ============================================================================
 * 
 * Tree Construction involves building binary trees from given input data such as
 * traversal sequences (inorder, preorder, postorder), level order data, or other
 * structural information. This pattern requires understanding the mathematical
 * relationships between different tree traversals and how unique trees can be
 * reconstructed from minimal information.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. UNIQUENESS: Inorder + (Preorder OR Postorder) uniquely determines a tree
 * 2. ROOT IDENTIFICATION: Preorder gives root first, postorder gives root last
 * 3. BOUNDARY CALCULATION: Inorder position divides left and right subtrees
 * 4. RECURSIVE DECOMPOSITION: Solve for subtrees using same logic
 * 
 * 🏗️ CONSTRUCTION METAPHOR:
 * Think of tree construction as "reverse engineering a blueprint":
 * - You have the final result (traversal sequence)
 * - You need to recreate the original structure (tree)
 * - Each piece of information gives clues about the structure
 * - Combine clues systematically to rebuild the whole
 * 
 * ============================================================================
 * 🎯 WHEN TO USE TREE CONSTRUCTION
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Building trees from traversal sequences
 * - Reconstructing trees from serialized data
 * - Converting between different tree representations
 * - Validating tree structure consistency
 * - Implementing tree serialization/deserialization
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Construct binary tree from..."
 * - "Build tree using inorder and preorder"
 * - "Reconstruct tree from..."
 * - "Deserialize tree from..."
 * - "Given traversals, build tree"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Tree already exists (use traversal patterns)
 * - Only need to process existing tree (use tree algorithms)
 * - Building from unclear or insufficient data
 * - Performance-critical real-time construction (consider caching)
 * 
 * ============================================================================
 * 🔄 TREE CONSTRUCTION VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TRAVERSAL-BASED CONSTRUCTION
 * - Build from preorder + inorder
 * - Build from postorder + inorder
 * - Build from preorder + postorder (with restrictions)
 * 
 * 2️⃣ LEVEL-ORDER CONSTRUCTION
 * - Build from level-order traversal
 * - Handle null values in serialization
 * - Queue-based breadth-first construction
 * 
 * 3️⃣ STRING-BASED CONSTRUCTION
 * - Deserialize from string representation
 * - Handle various encoding formats
 * - Parse structured tree data
 * 
 * 4️⃣ ARRAY-BASED CONSTRUCTION
 * - Build from array representation
 * - Handle complete vs. incomplete trees
 * - Index-based parent-child relationships
 * 
 * 5️⃣ SPECIAL TREE CONSTRUCTION
 * - Build BST from sorted array
 * - Construct balanced trees
 * - Create optimal tree structures
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 TRAVERSAL RELATIONSHIPS:
 * ```
 * INORDER: Left → Root → Right (gives left/right boundaries)
 * PREORDER: Root → Left → Right (gives root for each subtree)
 * POSTORDER: Left → Right → Root (gives root from the end)
 * LEVEL-ORDER: Breadth-first (gives structure level by level)
 * ```
 * 
 * 🔹 CONSTRUCTION ALGORITHM PATTERN:
 * ```
 * 1. IDENTIFY ROOT: Use preorder (first) or postorder (last)
 * 2. FIND BOUNDARIES: Locate root in inorder to split left/right
 * 3. CALCULATE SIZES: Determine left and right subtree sizes
 * 4. RECURSE: Build left and right subtrees with correct ranges
 * 5. CONNECT: Link root with constructed subtrees
 * ```
 * 
 * 🔹 INDEX MANAGEMENT:
 * ```
 * Preorder Index: Moves forward (root → left → right)
 * Postorder Index: Moves backward (right → left → root)
 * Inorder Ranges: Define subtree boundaries
 * HashMap Optimization: Quick inorder position lookup
 * ```
 * 
 * ============================================================================
 * 📋 CONSTRUCTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: VALIDATE INPUT
 * - Check if construction is possible
 * - Verify array lengths match
 * - Ensure data consistency
 * 
 * STEP 2: CHOOSE ALGORITHM
 * - Identify available traversal data
 * - Select appropriate construction method
 * - Consider optimization needs
 * 
 * STEP 3: SET UP DATA STRUCTURES
 * - Create index mappings (HashMap for inorder)
 * - Initialize index pointers
 * - Prepare helper data structures
 * 
 * STEP 4: IMPLEMENT RECURSIVE CONSTRUCTION
 * - Define base cases (empty ranges)
 * - Identify root from preorder/postorder
 * - Split ranges based on inorder position
 * - Recursively construct subtrees
 * 
 * STEP 5: OPTIMIZE AND VALIDATE
 * - Use HashMap for O(1) lookups
 * - Handle edge cases (single node, empty tree)
 * - Validate constructed tree if needed
 * 
 * ============================================================================
 * 🎨 CONSTRUCTION TEMPLATES
 * ============================================================================
 */

public class TreeConstructionReadingGuide {
    
    /**
     * 🏗️ TRAVERSAL-BASED CONSTRUCTION TEMPLATES
     */
    public static class TraversalBasedConstruction {
        
        /**
         * Construct Binary Tree from Preorder and Inorder Traversal
         * Strategy: Use preorder for root identification, inorder for partitioning
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
            
            // Root is first element in preorder
            int rootVal = preorder[preStart];
            TreeNode root = new TreeNode(rootVal);
            
            // Find root position in inorder
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
         * Construct Binary Tree from Inorder and Postorder Traversal
         * Strategy: Use postorder for root identification, inorder for partitioning
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
            
            // Root is last element in postorder
            int rootVal = postorder[postEnd];
            TreeNode root = new TreeNode(rootVal);
            
            int rootIndex = inorderMap.get(rootVal);
            int leftSubtreeSize = rootIndex - inStart;
            
            root.left = buildHelperInPost(inorder, inStart, rootIndex - 1,
                                         postorder, postStart, postStart + leftSubtreeSize - 1,
                                         inorderMap);
            root.right = buildHelperInPost(inorder, rootIndex + 1, inEnd,
                                          postorder, postStart + leftSubtreeSize, postEnd - 1,
                                          inorderMap);
            
            return root;
        }
        
        /**
         * Construct Binary Tree from Preorder and Postorder Traversal
         * Strategy: Works for full binary trees, use preorder for roots
         */
        public static TreeNode buildTreePreorderPostorder(int[] preorder, int[] postorder) {
            return buildHelperPrePost(preorder, 0, preorder.length - 1,
                                     postorder, 0, postorder.length - 1);
        }
        
        private static TreeNode buildHelperPrePost(int[] preorder, int preStart, int preEnd,
                                                  int[] postorder, int postStart, int postEnd) {
            if (preStart > preEnd) return null;
            
            TreeNode root = new TreeNode(preorder[preStart]);
            
            if (preStart == preEnd) return root;
            
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
            
            root.left = buildHelperPrePost(preorder, preStart + 1, preStart + leftSubtreeSize,
                                          postorder, postStart, leftChildIndex);
            root.right = buildHelperPrePost(preorder, preStart + leftSubtreeSize + 1, preEnd,
                                           postorder, leftChildIndex + 1, postEnd - 1);
            
            return root;
        }
    }
    
    /**
     * 🏗️ ARRAY-BASED CONSTRUCTION TEMPLATES
     */
    public static class ArrayBasedConstruction {
        
        /**
         * Construct Binary Tree from Level Order Array
         * Strategy: Use array indexing relationships for parent-child connections
         */
        public static TreeNode buildTreeFromLevelOrder(Integer[] arr) {
            if (arr == null || arr.length == 0 || arr[0] == null) return null;
            
            TreeNode root = new TreeNode(arr[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            
            int i = 1;
            while (!queue.isEmpty() && i < arr.length) {
                TreeNode current = queue.poll();
                
                // Add left child
                if (i < arr.length && arr[i] != null) {
                    current.left = new TreeNode(arr[i]);
                    queue.offer(current.left);
                }
                i++;
                
                // Add right child
                if (i < arr.length && arr[i] != null) {
                    current.right = new TreeNode(arr[i]);
                    queue.offer(current.right);
                }
                i++;
            }
            
            return root;
        }
        
        /**
         * Construct Balanced BST from Sorted Array
         * Strategy: Use middle element as root, recursively build subtrees
         */
        public static TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
        }
        
        private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
            if (left > right) return null;
            
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            
            root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
            root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
            
            return root;
        }
        
        /**
         * Construct Complete Binary Tree from Array
         * Strategy: Use complete tree indexing properties
         */
        public static TreeNode arrayToCompleteTree(int[] arr) {
            if (arr.length == 0) return null;
            return arrayToCompleteTreeHelper(arr, 0);
        }
        
        private static TreeNode arrayToCompleteTreeHelper(int[] arr, int index) {
            if (index >= arr.length) return null;
            
            TreeNode root = new TreeNode(arr[index]);
            root.left = arrayToCompleteTreeHelper(arr, 2 * index + 1);
            root.right = arrayToCompleteTreeHelper(arr, 2 * index + 2);
            
            return root;
        }
    }
    
    /**
     * 🏗️ SERIALIZED STRING CONSTRUCTION TEMPLATES
     */
    public static class SerializedStringConstruction {
        
        /**
         * Deserialize Binary Tree from Preorder String
         * Strategy: Use preorder traversal with null markers
         */
        public static TreeNode deserializePreorder(String data) {
            if (data == null || data.isEmpty()) return null;
            
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserializePreorderHelper(queue);
        }
        
        private static TreeNode deserializePreorderHelper(Queue<String> queue) {
            if (queue.isEmpty()) return null;
            
            String val = queue.poll();
            if ("null".equals(val)) return null;
            
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = deserializePreorderHelper(queue);
            root.right = deserializePreorderHelper(queue);
            
            return root;
        }
        
        /**
         * Deserialize Binary Tree from Level Order String
         * Strategy: Use BFS reconstruction with queue
         */
        public static TreeNode deserializeLevelOrder(String data) {
            if (data == null || data.isEmpty() || data.equals("[]")) return null;
            
            String[] values = data.substring(1, data.length() - 1).split(",");
            if (values.length == 0 || "null".equals(values[0].trim())) return null;
            
            TreeNode root = new TreeNode(Integer.parseInt(values[0].trim()));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            
            int i = 1;
            while (!queue.isEmpty() && i < values.length) {
                TreeNode current = queue.poll();
                
                // Process left child
                if (i < values.length && !"null".equals(values[i].trim())) {
                    current.left = new TreeNode(Integer.parseInt(values[i].trim()));
                    queue.offer(current.left);
                }
                i++;
                
                // Process right child
                if (i < values.length && !"null".equals(values[i].trim())) {
                    current.right = new TreeNode(Integer.parseInt(values[i].trim()));
                    queue.offer(current.right);
                }
                i++;
            }
            
            return root;
        }
    }
    
    /**
     * 🏗️ CONSTRAINT-BASED CONSTRUCTION TEMPLATES
     */
    public static class ConstraintBasedConstruction {
        
        /**
         * Construct BST from Preorder Traversal
         * Strategy: Use BST property to determine left/right placement
         */
        public static TreeNode bstFromPreorder(int[] preorder) {
            return bstFromPreorderHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, new int[]{0});
        }
        
        private static TreeNode bstFromPreorderHelper(int[] preorder, int min, int max, int[] index) {
            if (index[0] >= preorder.length) return null;
            
            int val = preorder[index[0]];
            if (val < min || val > max) return null;
            
            index[0]++;
            TreeNode root = new TreeNode(val);
            root.left = bstFromPreorderHelper(preorder, min, val, index);
            root.right = bstFromPreorderHelper(preorder, val, max, index);
            
            return root;
        }
        
        /**
         * Construct Maximum Binary Tree
         * Strategy: Use maximum element as root, recursively build subtrees
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
    }
    
    /**
     * 🏗️ SPECIALIZED CONSTRUCTION TEMPLATES
     */
    public static class SpecializedConstruction {
        
        /**
         * Clone Binary Tree
         * Strategy: Deep copy using any traversal
         */
        public static TreeNode cloneTree(TreeNode root) {
            if (root == null) return null;
            
            TreeNode cloned = new TreeNode(root.val);
            cloned.left = cloneTree(root.left);
            cloned.right = cloneTree(root.right);
            
            return cloned;
        }
        
        /**
         * Mirror Binary Tree
         * Strategy: Swap left and right children recursively
         */
        public static TreeNode mirrorTree(TreeNode root) {
            if (root == null) return null;
            
            TreeNode mirrored = new TreeNode(root.val);
            mirrored.left = mirrorTree(root.right);  // Swap left and right
            mirrored.right = mirrorTree(root.left);
            
            return mirrored;
        }
        
        /**
         * Construct Tree from Postfix Expression
         * Strategy: Use stack to build expression tree
         */
        public static TreeNode buildExpressionTree(String[] postfix) {
            Stack<TreeNode> stack = new Stack<>();
            
            for (String token : postfix) {
                TreeNode node = new TreeNode(token.charAt(0));
                
                if (isOperator(token)) {
                    node.right = stack.pop();
                    node.left = stack.pop();
                }
                
                stack.push(node);
            }
            
            return stack.isEmpty() ? null : stack.pop();
        }
        
        private static boolean isOperator(String token) {
            return "+".equals(token) || "-".equals(token) || 
                   "*".equals(token) || "/".equals(token);
        }
        
        /**
         * Merge Two Binary Trees
         * Strategy: Recursively merge corresponding nodes
         */
        public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) return t2;
            if (t2 == null) return t1;
            
            TreeNode merged = new TreeNode(t1.val + t2.val);
            merged.left = mergeTrees(t1.left, t2.left);
            merged.right = mergeTrees(t1.right, t2.right);
            
            return merged;
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Index Management
     * Careful tracking of array indices and boundaries is crucial
     */
    public static void indexManagementExample() {
        // Always validate array bounds before accessing
        // Use proper start/end boundaries for recursive calls
        // Consider off-by-one errors in index calculations
    }
    
    /**
     * ❌ PITFALL 2: Inefficient Lookups
     * Use hash maps for O(1) lookups instead of linear search
     */
    public static void lookupOptimizationExample() {
        // Create HashMap for inorder indices
        // Avoid repeated linear searches in arrays
        // Cache computed values when beneficial
    }
    
    /**
     * ❌ PITFALL 3: Null Handling Issues
     * Proper handling of null values in serialized formats
     */
    public static void nullHandlingExample() {
        // Check for null markers in serialized strings
        // Handle empty arrays and edge cases
        // Validate input before processing
    }
    
    /**
     * ❌ PITFALL 4: Incorrect Subtree Size Calculations
     * Accurate calculation of left/right subtree sizes
     */
    public static void subtreeSizeExample() {
        // Verify subtree size calculations
        // Account for root position in partitioning
        // Handle single-node and empty subtrees
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Optimal Construction Strategy
     * - Preorder + Inorder: most common and efficient
     * - Use HashMap for O(1) index lookups
     * - Consider iterative approaches for very deep trees
     */
    
    /**
     * 🎯 TIP 2: Optimize for Input Characteristics
     * - Balanced trees: simpler recursive approaches work well
     * - Skewed trees: consider iterative methods to avoid stack overflow
     * - Large inputs: prioritize time and space efficiency
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases Gracefully
     * - Empty inputs, single nodes, duplicate values
     * - Invalid traversal sequences, malformed serialized data
     * - Memory constraints and performance requirements
     */
    
    /**
     * 🎯 TIP 4: Validate Construction Results
     * - Verify tree structure matches input constraints
     * - Check that traversals of constructed tree match input
     * - Ensure tree properties (BST, completeness) are preserved
     */
    
    /**
     * 🎯 TIP 5: Consider Alternative Representations
     * - Different serialization formats for different use cases
     * - Compressed representations for large trees
     * - Streaming construction for very large datasets
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC CONSTRUCTION PROBLEMS:
     * - Construct Binary Tree from Preorder and Inorder
     * - Construct Binary Tree from Inorder and Postorder
     * - Convert Sorted Array to Binary Search Tree
     * - Serialize and Deserialize Binary Tree
     * 
     * 🟡 INTERMEDIATE CONSTRUCTION PROBLEMS:
     * - Construct Binary Tree from Preorder and Postorder
     * - Maximum Binary Tree
     * - Construct BST from Preorder Traversal
     * - Merge Two Binary Trees
     * 
     * 🔴 ADVANCED CONSTRUCTION PROBLEMS:
     * - Construct Binary Tree from String with Bracket Representation
     * - All Possible Full Binary Trees
     * - Construct Expression Tree from Postfix
     * - Optimize Tree Construction for Large Datasets
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE CONSTRUCTION APPROACH BASED ON:
     * ✅ Input format: traversals, arrays, strings, constraints
     * ✅ Tree properties: BST, complete, balanced, arbitrary
     * ✅ Performance requirements: time/space complexity, streaming
     * ✅ Validation needs: uniqueness, correctness, property preservation
     * 
     * OPTIMIZE FOR:
     * 🚀 Index management: HashMap lookups, boundary tracking
     * 🚀 Recursive efficiency: proper base cases, tail recursion
     * 🚀 Memory usage: iterative alternatives, in-place construction
     * 🚀 Error handling: invalid inputs, malformed data
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Database index reconstruction (B+ trees, search structures)
     * - Compiler syntax tree building (parsing, AST construction)
     * - File system recovery (directory tree reconstruction)
     * - Graphics and game development (scene graph construction)
     * - Machine learning (decision tree construction from data)
     * - Network routing (spanning tree construction)
     * - Data serialization (tree persistence and recovery)
     * - Version control systems (merge tree construction)
     */
    
    public static void main(String[] args) {
        System.out.println("🏗️ BINARY TREE CONSTRUCTION PATTERN - READING GUIDE");
        System.out.println("===================================================");
        
        System.out.println("\n📖 Construction Examples:");
        
        // Example 1: Preorder + Inorder Construction
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode tree1 = TraversalBasedConstruction.buildTreePreorderInorder(preorder, inorder);
        System.out.println("Built tree from preorder + inorder traversals");
        
        // Example 2: Array to BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = ArrayBasedConstruction.sortedArrayToBST(sortedArray);
        System.out.println("Built balanced BST from sorted array");
        
        // Example 3: Level Order Array
        Integer[] levelOrder = {1, 2, 3, null, 4, null, 5};
        TreeNode tree2 = ArrayBasedConstruction.buildTreeFromLevelOrder(levelOrder);
        System.out.println("Built tree from level order array");
        
        // Example 4: Serialized String
        String serialized = "1,2,null,null,3,4,null,null,5,null,null";
        TreeNode tree3 = SerializedStringConstruction.deserializePreorder(serialized);
        System.out.println("Deserialized tree from preorder string");
        
        // Example 5: BST from Preorder
        int[] bstPreorder = {8, 5, 1, 7, 10, 12};
        TreeNode bstTree = ConstraintBasedConstruction.bstFromPreorder(bstPreorder);
        System.out.println("Built BST from preorder traversal");
        
        // Example 6: Maximum Binary Tree
        int[] maxTreeArray = {3, 2, 1, 6, 0, 5};
        TreeNode maxTree = ConstraintBasedConstruction.constructMaximumBinaryTree(maxTreeArray);
        System.out.println("Built maximum binary tree from array");
        
        System.out.println("\n✅ Key Binary Tree Construction Principles:");
        System.out.println("1. Understand traversal relationships for unique tree identification");
        System.out.println("2. Use recursive decomposition to break into subtree problems");
        System.out.println("3. Manage indices and boundaries efficiently during construction");
        System.out.println("4. Optimize lookups with hash maps for O(1) access");
        System.out.println("5. Handle edge cases (null values, empty inputs, single nodes)");
        System.out.println("6. Validate constructed trees against input constraints");
        System.out.println("7. Choose appropriate construction strategy based on input format");
    }
} 