package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🪞 BINARY TREE MIRROR & SYMMETRY PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary tree mirror and symmetry algorithms
 * for checking symmetric properties, inverting tree structures, transforming tree orientations,
 * and validating balance characteristics. These algorithms demonstrate recursive tree manipulation,
 * structural comparison techniques, and symmetry validation methods for various tree-based
 * computational problems requiring geometric and structural analysis.
 * 
 * Pattern Focus: Tree mirroring, symmetry checking, structural validation, recursive comparison
 * Time Complexity: Generally O(n) where n is number of nodes in the tree
 * Space Complexity: O(h) for recursion stack where h is height of the tree
 */

// TreeNode class is already defined in TreeConstruction.java in the same package

// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class TreeMirrorSymmetry {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Mirror & Symmetry Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Invert Binary Tree
     * LeetCode: https://leetcode.com/problems/invert-binary-tree/
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
    
    /**
     * 🟢 EASY: Symmetric Tree
     * LeetCode: https://leetcode.com/problems/symmetric-tree/
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    
    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        
        return left.val == right.val &&
               isMirror(left.left, right.right) &&
               isMirror(left.right, right.left);
    }
    
    /**
     * 🟢 EASY: Convert Sorted Array to Binary Search Tree
     * LeetCode: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
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
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Mirror & Symmetry Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Flip Equivalent Binary Trees
     * LeetCode: https://leetcode.com/problems/flip-equivalent-binary-trees/
     */
    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
               (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
    
    /**
     * 🟡 MEDIUM: Convert Sorted List to Binary Search Tree
     * LeetCode: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        ListNode prev = null, slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        
        if (prev != null) {
            prev.next = null;
            root.left = sortedListToBST(head);
        }
        
        root.right = sortedListToBST(slow.next);
        
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("🪞 BINARY TREE MIRROR & SYMMETRY PATTERN - PRACTICE PROBLEMS");
        System.out.println("=============================================================");
        
        // Test implementations
        TreeNode symmetricTree = new TreeNode(1);
        symmetricTree.left = new TreeNode(2);
        symmetricTree.right = new TreeNode(2);
        symmetricTree.left.left = new TreeNode(3);
        symmetricTree.left.right = new TreeNode(4);
        symmetricTree.right.left = new TreeNode(4);
        symmetricTree.right.right = new TreeNode(3);
        
        System.out.println("Symmetric tree check: " + isSymmetric(symmetricTree));
        
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode bstFromArray = sortedArrayToBST(sortedArray);
        System.out.println("Created balanced BST from sorted array");
        
        System.out.println("\n✅ Key Principles:");
        System.out.println("1. Use recursive comparison for symmetry detection");
        System.out.println("2. Apply structural transformation for mirroring");
        System.out.println("3. Build balanced trees from sorted data");
        System.out.println("4. Handle null nodes properly in comparisons");
    }
} 