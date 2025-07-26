package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🪞 BINARY TREE MIRROR & SYMMETRY PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BINARY TREE MIRROR & SYMMETRY PATTERN?
 * ============================================================================
 * 
 * Binary Tree Mirror & Symmetry Pattern involves operations and analysis related
 * to tree reflection, symmetry detection, and structural transformations. This
 * pattern encompasses inverting trees (creating mirror images), checking for
 * symmetrical properties, determining tree equivalence under various transformations,
 * and building balanced structures from sorted data.
 * 
 * The pattern requires understanding recursive tree comparisons, structural
 * transformations, and the mathematical properties of tree symmetry. It's
 * fundamental for tree manipulation, structural analysis, and creating
 * balanced tree structures from linear data.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. RECURSIVE COMPARISON: Compare subtrees recursively for symmetry and equivalence
 * 2. STRUCTURAL TRANSFORMATION: Transform tree structure while preserving data
 * 3. SYMMETRY DETECTION: Identify symmetric properties through pattern recognition
 * 4. BALANCED CONSTRUCTION: Build balanced trees from sorted linear structures
 * 
 * 🪞 BINARY TREE MIRROR & SYMMETRY METAPHOR:
 * Think of tree mirror & symmetry as "architectural reflection":
 * - Mirror transformation: creating a reflected copy of a building
 * - Symmetry checking: verifying if a building is symmetric about its center axis
 * - Flip equivalence: determining if buildings are the same after allowed transformations
 * - Balanced construction: building perfectly balanced structures from linear materials
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BINARY TREE MIRROR & SYMMETRY PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Determining if a tree is symmetric or mirror-symmetric
 * - Creating inverted or mirrored versions of existing trees
 * - Checking tree equivalence under allowed transformations
 * - Building balanced BSTs from sorted arrays or linked lists
 * - Structural tree analysis and comparison problems
 * - Tree normalization and canonical form problems
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Symmetric binary tree"
 * - "Invert binary tree" or "mirror image"
 * - "Flip equivalent trees"
 * - "Convert sorted array/list to BST"
 * - "Tree reflection" or "tree symmetry"
 * - "Balanced tree construction"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple tree traversal without structural analysis (use traversal patterns)
 * - Tree search operations (use search patterns)
 * - Dynamic tree modifications (use modification patterns)
 * - Path-based problems (use path-finding patterns)
 * 
 * ============================================================================
 * 🔄 BINARY TREE MIRROR & SYMMETRY PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TREE INVERSION/MIRRORING
 * - Complete inversion: swap all left and right children recursively
 * - Partial inversion: selective mirroring based on conditions
 * - In-place transformation: modify existing tree structure
 * - Copy-based transformation: create new mirrored tree
 * 
 * 2️⃣ SYMMETRY DETECTION
 * - Perfect symmetry: tree is identical to its mirror image
 * - Structural symmetry: shape is symmetric, values may differ
 * - Value symmetry: symmetric positions have equal values
 * - Path symmetry: symmetric paths exist in tree structure
 * 
 * 3️⃣ TREE EQUIVALENCE UNDER TRANSFORMATIONS
 * - Flip equivalence: trees equivalent under allowed flip operations
 * - Rotation equivalence: trees equivalent under rotations
 * - Canonical form: normalize trees to standard representation
 * - Isomorphism detection: structural equivalence regardless of values
 * 
 * 4️⃣ BALANCED TREE CONSTRUCTION
 * - Array to balanced BST: use middle element strategy
 * - Linked list to balanced BST: convert linear to logarithmic structure
 * - Sorted data to balanced tree: maintain sorting while achieving balance
 * - Height-balanced construction: ensure optimal height properties
 * 
 * 5️⃣ RECURSIVE STRUCTURAL ANALYSIS
 * - Subtree comparison: compare left and right subtrees
 * - Pattern matching: identify structural patterns recursively
 * - Property verification: check structural properties through recursion
 * - Transformation validation: verify correctness of structural changes
 * 
 * 6️⃣ OPTIMIZATION AND NORMALIZATION
 * - Space-optimal transformations: minimize memory usage
 * - Time-optimal algorithms: achieve best possible time complexity
 * - Canonical representations: standardize tree forms
 * - Structure optimization: improve tree balance and properties
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 TREE INVERSION ALGORITHM:
 * ```
 * def invert(node):
 *     if not node:
 *         return None
 *     
 *     # Swap left and right children
 *     node.left, node.right = node.right, node.left
 *     
 *     # Recursively invert subtrees
 *     invert(node.left)
 *     invert(node.right)
 *     
 *     return node
 * ```
 * 
 * 🔹 SYMMETRY CHECK ALGORITHM:
 * ```
 * def isSymmetric(root):
 *     return isMirror(root.left, root.right)
 * 
 * def isMirror(left, right):
 *     if not left and not right:
 *         return True
 *     if not left or not right:
 *         return False
 *     
 *     return (left.val == right.val and
 *             isMirror(left.left, right.right) and
 *             isMirror(left.right, right.left))
 * ```
 * 
 * 🔹 ARRAY TO BALANCED BST:
 * ```
 * def sortedArrayToBST(nums):
 *     if not nums:
 *         return None
 *     
 *     mid = len(nums) // 2
 *     root = TreeNode(nums[mid])
 *     
 *     root.left = sortedArrayToBST(nums[:mid])
 *     root.right = sortedArrayToBST(nums[mid+1:])
 *     
 *     return root
 * ```
 * 
 * 🔹 FLIP EQUIVALENCE CHECK:
 * ```
 * def flipEquiv(root1, root2):
 *     if not root1 and not root2:
 *         return True
 *     if not root1 or not root2 or root1.val != root2.val:
 *         return False
 *     
 *     # Check both normal and flipped configurations
 *     return ((flipEquiv(root1.left, root2.left) and flipEquiv(root1.right, root2.right)) or
 *             (flipEquiv(root1.left, root2.right) and flipEquiv(root1.right, root2.left)))
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY SYMMETRY/MIRROR REQUIREMENTS
 * - What type of symmetry or transformation is needed?
 * - Is this about detection, transformation, or construction?
 * - What are the constraints and expected properties?
 * 
 * STEP 2: CHOOSE APPROPRIATE ALGORITHM
 * - Recursive comparison for symmetry detection
 * - Structural transformation for inversion/mirroring
 * - Balanced construction for sorted data conversion
 * - Equivalence checking for transformation validation
 * 
 * STEP 3: DESIGN RECURSIVE STRATEGY
 * - What are the base cases for recursion?
 * - How to handle null nodes and edge cases?
 * - What comparisons or transformations are needed?
 * 
 * STEP 4: IMPLEMENT COMPARISON/TRANSFORMATION LOGIC
 * - How to compare corresponding positions?
 * - What transformations preserve required properties?
 * - How to handle structural modifications safely?
 * 
 * STEP 5: OPTIMIZE AND VALIDATE
 * - Can the algorithm be optimized for time or space?
 * - How to validate correctness of transformations?
 * - What are the complexity trade-offs?
 * 
 * ============================================================================
 * 🎨 BINARY TREE MIRROR & SYMMETRY TEMPLATES
 * ============================================================================
 */

// Definition for a binary tree node

// Definition for singly-linked list

public class TreeMirrorSymmetryReadingGuide {
    
    /**
     * 🪞 TREE INVERSION/MIRRORING TEMPLATES
     */
    public static class TreeInversionTemplates {
        
        /**
         * Invert Binary Tree - Recursive Approach
         * Strategy: Swap left and right children at each node recursively
         */
        public static TreeNode invertTreeRecursive(TreeNode root) {
            if (root == null) return null;
            
            // Swap left and right children
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            
            // Recursively invert subtrees
            invertTreeRecursive(root.left);
            invertTreeRecursive(root.right);
            
            return root;
        }
        
        /**
         * Invert Binary Tree - Iterative Approach
         * Strategy: Use queue for level-by-level inversion
         */
        public static TreeNode invertTreeIterative(TreeNode root) {
            if (root == null) return null;
            
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                
                // Swap left and right children
                TreeNode temp = current.left;
                current.left = current.right;
                current.right = temp;
                
                // Add children to queue for processing
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            
            return root;
        }
        
        /**
         * Create Mirror Copy (Non-destructive)
         * Strategy: Create new tree that is mirror of original
         */
        public static TreeNode createMirrorCopy(TreeNode root) {
            if (root == null) return null;
            
            TreeNode mirror = new TreeNode(root.val);
            mirror.left = createMirrorCopy(root.right);  // Swap during creation
            mirror.right = createMirrorCopy(root.left);
            
            return mirror;
        }
    }
    
    /**
     * 🔍 SYMMETRY DETECTION TEMPLATES
     */
    public static class SymmetryDetectionTemplates {
        
        /**
         * Check if Binary Tree is Symmetric
         * Strategy: Compare left and right subtrees as mirrors
         */
        public static boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            return isMirror(root.left, root.right);
        }
        
        private static boolean isMirror(TreeNode left, TreeNode right) {
            // Both null - symmetric
            if (left == null && right == null) return true;
            
            // One null, other not - not symmetric
            if (left == null || right == null) return false;
            
            // Values must match and subtrees must be mirrors
            return left.val == right.val &&
                   isMirror(left.left, right.right) &&
                   isMirror(left.right, right.left);
        }
        
        /**
         * Check Symmetry - Iterative Approach
         * Strategy: Use queue to compare corresponding positions
         */
        public static boolean isSymmetricIterative(TreeNode root) {
            if (root == null) return true;
            
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root.left);
            queue.offer(root.right);
            
            while (!queue.isEmpty()) {
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();
                
                if (left == null && right == null) continue;
                if (left == null || right == null) return false;
                if (left.val != right.val) return false;
                
                // Add children in mirror order
                queue.offer(left.left);
                queue.offer(right.right);
                queue.offer(left.right);
                queue.offer(right.left);
            }
            
            return true;
        }
        
        /**
         * Check Structural Symmetry (ignore values)
         * Strategy: Compare only tree structure, not values
         */
        public static boolean isStructurallySymmetric(TreeNode root) {
            if (root == null) return true;
            return isStructuralMirror(root.left, root.right);
        }
        
        private static boolean isStructuralMirror(TreeNode left, TreeNode right) {
            if (left == null && right == null) return true;
            if (left == null || right == null) return false;
            
            return isStructuralMirror(left.left, right.right) &&
                   isStructuralMirror(left.right, right.left);
        }
    }
    
    /**
     * 🔄 TREE EQUIVALENCE TEMPLATES
     */
    public static class TreeEquivalenceTemplates {
        
        /**
         * Check Flip Equivalent Binary Trees
         * Strategy: Trees are equivalent if they match in normal or flipped form
         */
        public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) return true;
            if (root1 == null || root2 == null) return false;
            if (root1.val != root2.val) return false;
            
            // Check both normal and flipped configurations
            return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                   (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
        }
        
        /**
         * Check Tree Isomorphism
         * Strategy: Trees are isomorphic if they have same structure
         */
        public static boolean areIsomorphic(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) return true;
            if (root1 == null || root2 == null) return false;
            
            // Check both direct mapping and swapped mapping
            return (areIsomorphic(root1.left, root2.left) && areIsomorphic(root1.right, root2.right)) ||
                   (areIsomorphic(root1.left, root2.right) && areIsomorphic(root1.right, root2.left));
        }
        
        /**
         * Normalize Tree to Canonical Form
         * Strategy: Convert tree to standard form for comparison
         */
        public static TreeNode normalizeTree(TreeNode root) {
            if (root == null) return null;
            
            TreeNode left = normalizeTree(root.left);
            TreeNode right = normalizeTree(root.right);
            
            // Ensure left subtree is "smaller" than right for canonical form
            if (shouldSwap(left, right)) {
                root.left = right;
                root.right = left;
            } else {
                root.left = left;
                root.right = right;
            }
            
            return root;
        }
        
        private static boolean shouldSwap(TreeNode left, TreeNode right) {
            if (left == null) return false;
            if (right == null) return true;
            
            // Define ordering criteria for canonical form
            return compareSubtrees(left, right) > 0;
        }
        
        private static int compareSubtrees(TreeNode a, TreeNode b) {
            if (a == null && b == null) return 0;
            if (a == null) return -1;
            if (b == null) return 1;
            
            if (a.val != b.val) return a.val - b.val;
            
            int leftCmp = compareSubtrees(a.left, b.left);
            if (leftCmp != 0) return leftCmp;
            
            return compareSubtrees(a.right, b.right);
        }
    }
    
    /**
     * 🏗️ BALANCED TREE CONSTRUCTION TEMPLATES
     */
    public static class BalancedConstructionTemplates {
        
        /**
         * Convert Sorted Array to Balanced BST
         * Strategy: Use middle element as root for perfect balance
         */
        public static TreeNode sortedArrayToBST(int[] nums) {
            return arrayToBSTHelper(nums, 0, nums.length - 1);
        }
        
        private static TreeNode arrayToBSTHelper(int[] nums, int left, int right) {
            if (left > right) return null;
            
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            
            root.left = arrayToBSTHelper(nums, left, mid - 1);
            root.right = arrayToBSTHelper(nums, mid + 1, right);
            
            return root;
        }
        
        /**
         * Convert Sorted Linked List to Balanced BST
         * Strategy: Find middle using slow/fast pointers
         */
        public static TreeNode sortedListToBST(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return new TreeNode(head.val);
            
            // Find middle node using slow/fast pointer technique
            ListNode prev = null, slow = head, fast = head;
            
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            
            // Middle node becomes root
            TreeNode root = new TreeNode(slow.val);
            
            // Split list and recursively build subtrees
            if (prev != null) {
                prev.next = null; // Break left part
                root.left = sortedListToBST(head);
            }
            
            root.right = sortedListToBST(slow.next);
            
            return root;
        }
        
        /**
         * Convert Sorted List to BST - In-order Simulation
         * Strategy: Simulate in-order traversal for O(n) solution
         */
        public static TreeNode sortedListToBSTInorder(ListNode head) {
            int length = getLength(head);
            ListNode[] current = {head}; // Use array to pass by reference
            return buildBSTInorder(current, 0, length - 1);
        }
        
        private static TreeNode buildBSTInorder(ListNode[] current, int start, int end) {
            if (start > end) return null;
            
            int mid = start + (end - start) / 2;
            
            // Build left subtree first
            TreeNode left = buildBSTInorder(current, start, mid - 1);
            
            // Process current node (in-order position)
            TreeNode root = new TreeNode(current[0].val);
            current[0] = current[0].next;
            
            // Build right subtree
            TreeNode right = buildBSTInorder(current, mid + 1, end);
            
            root.left = left;
            root.right = right;
            
            return root;
        }
        
        private static int getLength(ListNode head) {
            int length = 0;
            while (head != null) {
                length++;
                head = head.next;
            }
            return length;
        }
    }
    
    /**
     * 🔧 SPECIALIZED SYMMETRY OPERATIONS
     */
    public static class SpecializedSymmetryOperations {
        
        /**
         * Find Symmetric Pairs in Tree
         * Strategy: Identify nodes that are in symmetric positions
         */
        public static List<int[]> findSymmetricPairs(TreeNode root) {
            List<int[]> pairs = new ArrayList<>();
            if (root == null) return pairs;
            
            findSymmetricPairsHelper(root.left, root.right, pairs);
            return pairs;
        }
        
        private static void findSymmetricPairsHelper(TreeNode left, TreeNode right, List<int[]> pairs) {
            if (left == null || right == null) return;
            
            pairs.add(new int[]{left.val, right.val});
            
            findSymmetricPairsHelper(left.left, right.right, pairs);
            findSymmetricPairsHelper(left.right, right.left, pairs);
        }
        
        /**
         * Count Symmetric Subtrees
         * Strategy: Count subtrees that are symmetric
         */
        public static int countSymmetricSubtrees(TreeNode root) {
            if (root == null) return 0;
            
            int count = 0;
            if (SymmetryDetectionTemplates.isSymmetric(root)) count++;
            
            count += countSymmetricSubtrees(root.left);
            count += countSymmetricSubtrees(root.right);
            
            return count;
        }
        
        /**
         * Make Tree Symmetric
         * Strategy: Transform tree to make it symmetric by selective modifications
         */
        public static TreeNode makeSymmetric(TreeNode root) {
            if (root == null) return null;
            
            // Strategy: Keep left subtree, mirror it to create right subtree
            root.right = createMirrorCopy(root.left);
            
            return root;
        }
        
        private static TreeNode createMirrorCopy(TreeNode node) {
            if (node == null) return null;
            
            TreeNode mirror = new TreeNode(node.val);
            mirror.left = createMirrorCopy(node.right);
            mirror.right = createMirrorCopy(node.left);
            
            return mirror;
        }
        
        /**
         * Verify Symmetric Transformation
         * Strategy: Check if transformation preserves required properties
         */
        public static boolean verifySymmetricTransformation(TreeNode original, TreeNode transformed) {
            return SymmetryDetectionTemplates.isSymmetric(transformed) && 
                   preservesStructuralIntegrity(original, transformed);
        }
        
        private static boolean preservesStructuralIntegrity(TreeNode a, TreeNode b) {
            if (a == null && b == null) return true;
            if (a == null || b == null) return false;
            
            return preservesStructuralIntegrity(a.left, b.left) && 
                   preservesStructuralIntegrity(a.right, b.right);
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Mirror Comparison
     * When checking symmetry, compare left.left with right.right and left.right with right.left
     */
    public static void mirrorComparisonExample() {
        // Correct: left.left vs right.right, left.right vs right.left
        // Incorrect: left.left vs right.left, left.right vs right.right
    }
    
    /**
     * ❌ PITFALL 2: Null Handling in Recursion
     * Properly handle null nodes in recursive comparisons
     */
    public static void nullHandlingExample() {
        // Both null: return true (symmetric)
        // One null: return false (not symmetric)
        // Neither null: continue comparison
    }
    
    /**
     * ❌ PITFALL 3: Destructive vs Non-destructive Operations
     * Distinguish between modifying original tree vs creating new tree
     */
    public static void destructiveOperationExample() {
        // In-place inversion modifies original tree
        // Mirror copy creates new tree without modifying original
    }
    
    /**
     * ❌ PITFALL 4: Linked List Modification During Conversion
     * Be careful not to lose list structure during BST conversion
     */
    public static void listModificationExample() {
        // Break list connections carefully
        // Use techniques like slow/fast pointers or in-order simulation
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Approach for Symmetry
     * - Recursive: natural and clean for most symmetry problems
     * - Iterative: better control over space complexity
     * - In-place: when memory is constrained
     */
    
    /**
     * 🎯 TIP 2: Optimize Balanced Tree Construction
     * - Array to BST: O(n) time with proper indexing
     * - List to BST: O(n log n) with middle finding, O(n) with in-order simulation
     * - Consider space-time trade-offs
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases in Equivalence Checking
     * - Empty trees, single nodes, different structures
     * - Value equality vs structural equality
     * - Consider all possible flip configurations
     */
    
    /**
     * 🎯 TIP 4: Validate Transformation Results
     * - Check if result maintains required properties
     * - Verify structural integrity after modifications
     * - Test with various tree shapes and sizes
     */
    
    /**
     * 🎯 TIP 5: Consider Space-Time Trade-offs
     * - In-place vs copy-based transformations
     * - Recursive vs iterative implementations
     * - Memory usage vs computational complexity
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC MIRROR & SYMMETRY PROBLEMS:
     * - Invert Binary Tree
     * - Symmetric Tree
     * - Convert Sorted Array to Binary Search Tree
     * - Same Tree
     * 
     * 🟡 INTERMEDIATE MIRROR & SYMMETRY PROBLEMS:
     * - Flip Equivalent Binary Trees
     * - Convert Sorted List to Binary Search Tree
     * - Univalued Binary Tree
     * - Binary Tree Tilt
     * 
     * 🔴 ADVANCED MIRROR & SYMMETRY PROBLEMS:
     * - Count Complete Tree Nodes
     * - Binary Tree Cameras
     * - Flip Binary Tree To Match Preorder Traversal
     * - Distribute Coins in Binary Tree
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE APPROACH BASED ON:
     * ✅ Problem type: detection, transformation, construction, equivalence
     * ✅ Tree properties: symmetry, balance, structure
     * ✅ Performance requirements: time/space complexity, in-place vs copy
     * ✅ Input format: array, linked list, existing tree
     * 
     * OPTIMIZE FOR:
     * 🚀 Recursive elegance: clean and natural recursive solutions
     * 🚀 Space efficiency: in-place transformations when possible
     * 🚀 Time complexity: optimal algorithms for large inputs
     * 🚀 Code clarity: readable and maintainable implementations
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Computer graphics (3D model mirroring and symmetry)
     * - Image processing (symmetry detection and transformation)
     * - Game development (procedural content generation)
     * - Database indexing (balanced tree construction from sorted data)
     * - Computer vision (object symmetry analysis)
     * - Architecture software (building symmetry verification)
     * - Data structure optimization (converting linear to logarithmic access)
     * - Algorithm design (tree balancing and optimization)
     */
    
    public static void main(String[] args) {
        System.out.println("🪞 BINARY TREE MIRROR & SYMMETRY PATTERN - READING GUIDE");
        System.out.println("=========================================================");
        
        // Create sample trees for demonstration
        TreeNode symmetricTree = createSymmetricTree();
        TreeNode asymmetricTree = createAsymmetricTree();
        
        System.out.println("\n📖 Template Examples:");
        
        // Tree Inversion
        TreeNode originalTree = createSampleTree();
        TreeNode invertedTree = TreeInversionTemplates.invertTreeRecursive(cloneTree(originalTree));
        System.out.println("Original tree inverted successfully");
        
        // Symmetry Detection
        boolean isSymmetric1 = SymmetryDetectionTemplates.isSymmetric(symmetricTree);
        boolean isSymmetric2 = SymmetryDetectionTemplates.isSymmetric(asymmetricTree);
        System.out.println("Symmetric tree check: " + isSymmetric1); // true
        System.out.println("Asymmetric tree check: " + isSymmetric2); // false
        
        // Flip Equivalence
        TreeNode tree1 = createSampleTree();
        TreeNode tree2 = createFlippedVersion(tree1);
        boolean flipEquivalent = TreeEquivalenceTemplates.flipEquiv(tree1, tree2);
        System.out.println("Trees are flip equivalent: " + flipEquivalent);
        
        // Array to BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bstFromArray = BalancedConstructionTemplates.sortedArrayToBST(sortedArray);
        System.out.println("Created balanced BST from sorted array");
        
        // List to BST
        ListNode sortedList = createSortedList();
        TreeNode bstFromList = BalancedConstructionTemplates.sortedListToBST(sortedList);
        System.out.println("Created balanced BST from sorted list");
        
        // Specialized Operations
        List<int[]> symmetricPairs = SpecializedSymmetryOperations.findSymmetricPairs(symmetricTree);
        System.out.println("Found " + symmetricPairs.size() + " symmetric pairs");
        
        int symmetricSubtrees = SpecializedSymmetryOperations.countSymmetricSubtrees(originalTree);
        System.out.println("Symmetric subtrees count: " + symmetricSubtrees);
        
        System.out.println("\n✅ Key Binary Tree Mirror & Symmetry Principles:");
        System.out.println("1. Use recursive comparison for symmetry detection");
        System.out.println("2. Apply structural transformation for mirroring operations");
        System.out.println("3. Implement balanced construction from sorted data");
        System.out.println("4. Handle null nodes properly in recursive algorithms");
        System.out.println("5. Consider both in-place and copy-based transformations");
        System.out.println("6. Validate transformation results for correctness");
        System.out.println("7. Optimize for space and time complexity based on requirements");
    }
    
    // Helper methods for demonstration
    private static TreeNode createSymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        return root;
    }
    
    private static TreeNode createAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        return root;
    }
    
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }
    
    private static TreeNode createFlippedVersion(TreeNode original) {
        // Create a version with some flips for testing
        TreeNode flipped = cloneTree(original);
        // Apply some flips...
        return flipped;
    }
    
    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode clone = new TreeNode(root.val);
        clone.left = cloneTree(root.left);
        clone.right = cloneTree(root.right);
        return clone;
    }
    
    private static ListNode createSortedList() {
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);
        return head;
    }
} 