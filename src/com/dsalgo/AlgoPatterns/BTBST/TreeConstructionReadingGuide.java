package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🏗️ BINARY TREE CONSTRUCTION PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BINARY TREE CONSTRUCTION PATTERN?
 * ============================================================================
 * 
 * Binary Tree Construction Pattern involves creating binary trees from various
 * forms of input data, including traversal sequences, arrays, serialized strings,
 * and other structured representations. This pattern is essential for tree
 * reconstruction, deserialization, and building trees that satisfy specific
 * structural or ordering constraints.
 * 
 * The pattern requires understanding the relationship between different traversal
 * orders and how they can uniquely determine tree structure when combined properly.
 * It also involves handling edge cases, optimizing construction algorithms, and
 * ensuring the resulting tree matches the intended specification.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. TRAVERSAL RELATIONSHIPS: Understand how different traversals uniquely identify trees
 * 2. RECURSIVE DECOMPOSITION: Break tree construction into subtree problems
 * 3. INDEX MANAGEMENT: Efficiently track positions in input sequences
 * 4. CONSTRAINT SATISFACTION: Build trees that meet specified requirements
 * 
 * 🏗️ BINARY TREE CONSTRUCTION METAPHOR:
 * Think of tree construction as "architectural blueprints":
 * - Traversal sequences: different views of the same building (floor plan, elevation, cross-section)
 * - Construction process: assembling the building from these coordinated views
 * - Constraints: building codes and requirements that must be satisfied
 * - Optimization: efficient construction methods that minimize time and resources
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BINARY TREE CONSTRUCTION PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Reconstructing trees from traversal sequences or serialized data
 * - Building trees that satisfy specific structural constraints
 * - Deserializing trees from storage or transmission formats
 * - Creating trees from array representations or level-order data
 * - Implementing tree persistence and recovery mechanisms
 * - Converting between different tree representations
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Construct binary tree from..."
 * - "Build tree from traversal sequences"
 * - "Reconstruct tree from serialized data"
 * - "Create tree satisfying constraints"
 * - "Deserialize binary tree"
 * - "Tree construction from array"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple tree modifications (use tree manipulation patterns)
 * - Tree searching without construction (use search patterns)
 * - Tree validation only (use validation patterns)
 * - Dynamic tree updates (use tree modification patterns)
 * 
 * ============================================================================
 * 🔄 BINARY TREE CONSTRUCTION PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TRAVERSAL-BASED CONSTRUCTION
 * - Use combinations of traversal sequences to uniquely determine tree structure
 * - Preorder + Inorder: most common, root identification + left/right partitioning
 * - Inorder + Postorder: root at end, similar partitioning strategy
 * - Preorder + Postorder: works for full binary trees, requires special handling
 * 
 * 2️⃣ ARRAY-BASED CONSTRUCTION
 * - Build trees from array representations (complete binary trees)
 * - Level-order arrays: parent-child relationships through indexing
 * - Sorted arrays: create balanced BSTs through recursive partitioning
 * - Custom array formats: handle specific encoding schemes
 * 
 * 3️⃣ SERIALIZED STRING CONSTRUCTION
 * - Reconstruct trees from string representations
 * - Preorder with null markers: straightforward recursive reconstruction
 * - Level-order serialization: BFS-based reconstruction
 * - Custom encoding schemes: handle compressed or specialized formats
 * 
 * 4️⃣ CONSTRAINT-BASED CONSTRUCTION
 * - Build trees satisfying specific structural or value constraints
 * - BST construction: maintain ordering properties during construction
 * - Balanced tree construction: ensure height constraints
 * - Custom constraint satisfaction: handle problem-specific requirements
 * 
 * 5️⃣ INCREMENTAL CONSTRUCTION
 * - Build trees one node at a time with specific insertion rules
 * - BST insertion: maintain ordering while inserting nodes
 * - Heap construction: maintain heap properties during construction
 * - Custom insertion rules: handle specialized tree types
 * 
 * 6️⃣ TRANSFORMATION-BASED CONSTRUCTION
 * - Convert one tree representation to another
 * - Mirror tree construction: create reflected versions
 * - Tree merging: combine multiple trees following rules
 * - Subtree extraction: create new trees from existing subtrees
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 PREORDER + INORDER CONSTRUCTION:
 * ```
 * 1. First element in preorder is root
 * 2. Find root position in inorder
 * 3. Elements before root in inorder = left subtree
 * 4. Elements after root in inorder = right subtree
 * 5. Recursively construct left and right subtrees
 * ```
 * 
 * 🔹 INORDER + POSTORDER CONSTRUCTION:
 * ```
 * 1. Last element in postorder is root
 * 2. Find root position in inorder
 * 3. Partition inorder into left and right subtrees
 * 4. Recursively construct subtrees with corresponding postorder parts
 * ```
 * 
 * 🔹 ARRAY TO TREE CONSTRUCTION:
 * ```
 * For index i:
 * - Left child at index 2*i + 1
 * - Right child at index 2*i + 2
 * - Parent at index (i-1)/2
 * ```
 * 
 * 🔹 SERIALIZED STRING RECONSTRUCTION:
 * ```
 * 1. Parse serialized string into tokens
 * 2. Use queue or index tracking for reconstruction
 * 3. Handle null markers appropriately
 * 4. Recursively build subtrees
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: ANALYZE INPUT FORMAT
 * - What type of input data is provided?
 * - How is the tree structure encoded in the input?
 * - What constraints or properties must be preserved?
 * 
 * STEP 2: IDENTIFY CONSTRUCTION STRATEGY
 * - Which traversal combinations are available?
 * - What algorithm best fits the input format?
 * - How to handle edge cases and null values?
 * 
 * STEP 3: DESIGN RECURSIVE DECOMPOSITION
 * - How to break the problem into subtree constructions?
 * - What information is needed for each recursive call?
 * - How to manage indices and boundaries effectively?
 * 
 * STEP 4: IMPLEMENT INDEX MANAGEMENT
 * - How to track positions in input sequences?
 * - What data structures help with efficient lookups?
 * - How to avoid redundant computations?
 * 
 * STEP 5: OPTIMIZE AND VALIDATE
 * - Can construction be optimized for time or space?
 * - How to validate the constructed tree?
 * - What are the complexity trade-offs?
 * 
 * ============================================================================
 * 🎨 BINARY TREE CONSTRUCTION TEMPLATES
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