package com.dsalgo.AlgoPatterns.BinarySearch;

import java.util.*;

/**
 * 🔍 BINARY SEARCH PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary search problems.
 * Binary search is a fundamental technique for efficiently searching sorted data
 * and solving optimization problems with logarithmic time complexity.
 * 
 * Pattern Focus: Search and optimization with divide-and-conquer approach
 * Time Complexity: Generally O(log n) for search operations
 * Space Complexity: O(1) for iterative implementations, O(log n) for recursive
 */
public class BinarySearch {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Foundational Binary Search Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Binary Search (Classic)
     * 
     * Problem: Search for target value in sorted array.
     * 
     * Approach: Standard binary search algorithm
     * - Initialize left and right boundaries
     * - Compare middle element with target
     * - Adjust boundaries based on comparison
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevent overflow
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Target not found
    }
    
    /**
     * 🟢 EASY: Search Insert Position
     * 
     * Problem: Find index where target should be inserted in sorted array.
     * 
     * Approach: Lower bound binary search
     * - Find first position where element >= target
     * - If target exists, return its index
     * - If not, return insertion position
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * 🟢 EASY: First Bad Version
     * 
     * Problem: Find first bad version using isBadVersion API.
     * 
     * Approach: Lower bound binary search for first "true"
     * - Search for first version that returns true
     * - Use binary search to minimize API calls
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int firstBadVersion(int n) {
        int left = 1, right = n;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                right = mid; // First bad version might be mid
            } else {
                left = mid + 1; // Bad version is after mid
            }
        }
        
        return left;
    }
    
    // Mock API for First Bad Version problem
    private static boolean isBadVersion(int version) {
        // This would be provided by the problem
        return version >= 4; // Example: versions 4+ are bad
    }
    
    /**
     * 🟢 EASY: Sqrt(x) - Integer Square Root
     * 
     * Problem: Find integer square root of x.
     * 
     * Approach: Binary search on answer space [0, x]
     * - Search for largest number whose square <= x
     * - Use binary search to avoid overflow
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int mySqrt(int x) {
        if (x == 0) return 0;
        
        int left = 1, right = x;
        int result = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid <= x / mid) { // Avoid overflow: mid * mid <= x
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Valid Perfect Square
     * 
     * Problem: Check if given number is a perfect square.
     * 
     * Approach: Binary search on square root
     * - Search for number whose square equals target
     * - Use binary search to avoid overflow issues
     * 
     * Time: O(log n), Space: O(1)
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        
        long left = 1, right = num;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * 🟢 EASY: Find Smallest Letter Greater Than Target
     * 
     * Problem: Find smallest character in sorted array that is greater than target.
     * 
     * Approach: Upper bound binary search with circular array handling
     * - Find first character > target
     * - Handle wrap-around case (return first character if no greater found)
     * 
     * Time: O(log n), Space: O(1)
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Wrap around if no greater character found
        return letters[left % letters.length];
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Core Binary Search Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Find First and Last Position of Element
     * 
     * Problem: Find range [start, end] of target in sorted array.
     * 
     * Approach: Two binary searches (lower and upper bound)
     * - Find first occurrence using lower bound
     * - Find last occurrence using upper bound - 1
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        
        return new int[]{first, last};
    }
    
    private static int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return (left < nums.length && nums[left] == target) ? left : -1;
    }
    
    private static int findLast(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return (left > 0 && nums[left - 1] == target) ? left - 1 : -1;
    }
    
    /**
     * 🟡 MEDIUM: Search in Rotated Sorted Array
     * 
     * Problem: Search target in rotated sorted array (no duplicates).
     * 
     * Approach: Modified binary search with rotation detection
     * - Determine which half is sorted
     * - Check if target is in sorted half
     * - Adjust search space accordingly
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int searchRotated(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Determine which half is sorted
            if (nums[left] <= nums[mid]) { // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Target in left half
                } else {
                    left = mid + 1; // Target in right half
                }
            } else { // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Target in right half
                } else {
                    right = mid - 1; // Target in left half
                }
            }
        }
        
        return -1;
    }
    
    /**
     * 🟡 MEDIUM: Find Peak Element
     * 
     * Problem: Find any peak element (greater than neighbors).
     * 
     * Approach: Binary search based on slope
     * - Compare mid with mid+1 to determine slope
     * - Move toward higher side (peak must exist there)
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1; // Peak is on the right
            } else {
                right = mid; // Peak is on the left or at mid
            }
        }
        
        return left;
    }
    
    /**
     * 🟡 MEDIUM: Find Minimum in Rotated Sorted Array
     * 
     * Problem: Find minimum element in rotated sorted array.
     * 
     * Approach: Binary search with rotation point detection
     * - Compare mid with right to determine rotation
     * - Minimum is at rotation point
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1; // Minimum is in right half
            } else {
                right = mid; // Minimum is in left half or at mid
            }
        }
        
        return nums[left];
    }
    
    /**
     * 🟡 MEDIUM: Search a 2D Matrix
     * 
     * Problem: Search target in row and column sorted matrix.
     * 
     * Approach: Treat 2D matrix as 1D sorted array
     * - Map 1D index to 2D coordinates
     * - Use standard binary search
     * 
     * Time: O(log(m*n)), Space: O(1)
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * 🟡 MEDIUM: Koko Eating Bananas (Answer Binary Search)
     * 
     * Problem: Find minimum eating speed to finish bananas in h hours.
     * 
     * Approach: Binary search on eating speed
     * - Speed range: [1, max(piles)]
     * - Check if speed k allows finishing in h hours
     * 
     * Time: O(n * log(max)), Space: O(1)
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().orElse(1);
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canFinish(piles, mid, h)) {
                right = mid; // Try smaller speed
            } else {
                left = mid + 1; // Need faster speed
            }
        }
        
        return left;
    }
    
    private static boolean canFinish(int[] piles, int speed, int h) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed; // Ceiling division
        }
        return hours <= h;
    }
    
    /**
     * 🟡 MEDIUM: Capacity to Ship Packages
     * 
     * Problem: Find minimum ship capacity to ship packages in D days.
     * 
     * Approach: Binary search on capacity
     * - Capacity range: [max(weights), sum(weights)]
     * - Check if capacity allows shipping in D days
     * 
     * Time: O(n * log(sum)), Space: O(1)
     */
    public static int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().orElse(1);
        int right = Arrays.stream(weights).sum();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canShip(weights, mid, days)) {
                right = mid; // Try smaller capacity
            } else {
                left = mid + 1; // Need larger capacity
            }
        }
        
        return left;
    }
    
    private static boolean canShip(int[] weights, int capacity, int days) {
        int currentWeight = 0, daysNeeded = 1;
        
        for (int weight : weights) {
            if (currentWeight + weight > capacity) {
                daysNeeded++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }
        }
        
        return daysNeeded <= days;
    }
    
    /**
     * 🟡 MEDIUM: Peak Index in a Mountain Array
     * 
     * Problem: Find peak index in mountain array (strictly increasing then decreasing).
     * 
     * Approach: Binary search based on slope comparison
     * - Compare current element with next element
     * - Move toward ascending side until peak is found
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1; // Peak is on the right
            } else {
                right = mid; // Peak is on the left or at mid
            }
        }
        
        return left;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Binary Search Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Median of Two Sorted Arrays
     * 
     * Problem: Find median of two sorted arrays in O(log(min(m,n))) time.
     * 
     * Approach: Binary search on partition
     * - Partition both arrays such that left halves have same size
     * - Ensure all elements in left <= all elements in right
     * - Find correct partition using binary search
     * 
     * Time: O(log(min(m,n))), Space: O(1)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        
        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;
            
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];
            
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Correct partition found
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1; // Too far right in nums1
            } else {
                left = partition1 + 1; // Too far left in nums1
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
    
    /**
     * 🔴 HARD: Split Array Largest Sum
     * 
     * Problem: Split array into m subarrays to minimize largest sum.
     * 
     * Approach: Binary search on answer (maximum sum)
     * - Answer range: [max(nums), sum(nums)]
     * - Check if we can split with given maximum sum
     * 
     * Time: O(n * log(sum)), Space: O(1)
     */
    public static int splitArray(int[] nums, int m) {
        int left = Arrays.stream(nums).max().orElse(0);
        int right = Arrays.stream(nums).sum();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canSplit(nums, m, mid)) {
                right = mid; // Try smaller maximum sum
            } else {
                left = mid + 1; // Need larger maximum sum
            }
        }
        
        return left;
    }
    
    private static boolean canSplit(int[] nums, int m, int maxSum) {
        int subarrays = 1, currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                subarrays++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }
        
        return subarrays <= m;
    }
    
    /**
     * 🔴 HARD: Find K-th Smallest Pair Distance
     * 
     * Problem: Find k-th smallest distance between pairs.
     * 
     * Approach: Binary search on distance + counting
     * - Sort array first
     * - Binary search on distance [0, max-min]
     * - Count pairs with distance <= mid using two pointers
     * 
     * Time: O(n log n + n log(max-min)), Space: O(1)
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1] - nums[0];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (countPairs(nums, mid) >= k) {
                right = mid; // Try smaller distance
            } else {
                left = mid + 1; // Need larger distance
            }
        }
        
        return left;
    }
    
    private static int countPairs(int[] nums, int distance) {
        int count = 0, left = 0;
        
        for (int right = 1; right < nums.length; right++) {
            while (nums[right] - nums[left] > distance) {
                left++;
            }
            count += right - left;
        }
        
        return count;
    }
    
    /**
     * 🔴 HARD: Minimize Max Distance to Gas Station
     * 
     * Problem: Add k gas stations to minimize maximum distance.
     * 
     * Approach: Binary search on maximum distance
     * - Use floating-point binary search
     * - For each distance, calculate stations needed
     * 
     * Time: O(n * log(max/precision)), Space: O(1)
     */
    public static double minmaxGasDist(int[] stations, int k) {
        double left = 0, right = 0;
        
        // Find maximum initial distance
        for (int i = 1; i < stations.length; i++) {
            right = Math.max(right, stations[i] - stations[i - 1]);
        }
        
        double precision = 1e-6;
        while (right - left > precision) {
            double mid = left + (right - left) / 2;
            
            if (canAchieve(stations, k, mid)) {
                right = mid; // Try smaller maximum distance
            } else {
                left = mid; // Need larger maximum distance
            }
        }
        
        return left;
    }
    
    private static boolean canAchieve(int[] stations, int k, double maxDist) {
        int stationsNeeded = 0;
        
        for (int i = 1; i < stations.length; i++) {
            double distance = stations[i] - stations[i - 1];
            stationsNeeded += (int) (distance / maxDist);
        }
        
        return stationsNeeded <= k;
    }
    
    // ============================================================================
    // 📋 ALLOCATION PROBLEMS - Resource Distribution Optimization
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Allocate Minimum Number of Pages (Book Allocation)
     * 
     * Problem: Allocate books to students such that maximum pages assigned to any student is minimized.
     * 
     * Approach: Binary search on maximum pages + feasibility check
     * - Search range: [max(pages), sum(pages)]
     * - For each mid value, check if allocation is possible
     * - Greedy allocation: give books to current student until limit exceeded
     * 
     * Time: O(n * log(sum)), Space: O(1)
     */
    public static int allocateBooks(int[] pages, int students) {
        if (students > pages.length) return -1; // More students than books
        
        int left = Arrays.stream(pages).max().orElse(0); // At least max pages per student
        int right = Arrays.stream(pages).sum(); // All pages to one student
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canAllocateBooks(pages, students, mid)) {
                result = mid;
                right = mid - 1; // Try to minimize maximum pages
            } else {
                left = mid + 1; // Need more pages per student
            }
        }
        
        return result;
    }
    
    private static boolean canAllocateBooks(int[] pages, int students, int maxPages) {
        int studentsUsed = 1;
        int currentPages = 0;
        
        for (int page : pages) {
            if (currentPages + page <= maxPages) {
                currentPages += page;
            } else {
                studentsUsed++;
                currentPages = page;
                if (studentsUsed > students) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * 🟢 EASY: Minimize Maximum Pair Sum in Array
     * 
     * Problem: Pair elements to minimize the maximum sum among all pairs.
     * 
     * Approach: Greedy pairing (sort + pair smallest with largest)
     * - Sort the array
     * - Pair first element with last, second with second-last, etc.
     * - Return maximum sum among all pairs
     * 
     * Time: O(n log n), Space: O(1)
     */
    public static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            maxSum = Math.max(maxSum, nums[left] + nums[right]);
            left++;
            right--;
        }
        
        return maxSum;
    }
    
    /**
     * 🔴 HARD: Divide Chocolate
     * 
     * Problem: Cut chocolate into k+1 pieces to maximize the minimum total sweetness.
     * 
     * Approach: Binary search on minimum sweetness + feasibility check
     * - Search range: [1, sum(sweetness)]
     * - For each mid value, check if k cuts can achieve this minimum
     * - Greedy cutting: make cut when current sum >= mid
     * 
     * Time: O(n * log(sum)), Space: O(1)
     */
    public static int maximizeMinSweetness(int[] sweetness, int k) {
        int left = 1; // Minimum possible sweetness
        int right = Arrays.stream(sweetness).sum(); // All sweetness in one piece
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canAchieveMinSweetness(sweetness, k, mid)) {
                result = mid;
                left = mid + 1; // Try to maximize minimum sweetness
            } else {
                right = mid - 1; // Reduce minimum sweetness requirement
            }
        }
        
        return result;
    }
    
    private static boolean canAchieveMinSweetness(int[] sweetness, int cuts, int minSweetness) {
        int pieces = 0;
        int currentSum = 0;
        
        for (int sweet : sweetness) {
            currentSum += sweet;
            if (currentSum >= minSweetness) {
                pieces++;
                currentSum = 0;
            }
        }
        
        return pieces >= cuts + 1; // Need k+1 pieces total
    }
    
    // ============================================================================
    // 🔍 RANGE SEARCH PROBLEMS - Optimization within Given Ranges
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Find Kth Smallest Element in a Sorted Matrix
     * 
     * Problem: Find kth smallest element in n x n matrix where each row and column is sorted.
     * 
     * Approach: Binary search on values + counting elements
     * - Binary search on range [matrix[0][0], matrix[n-1][n-1]]
     * - For each mid value, count elements <= mid
     * - Adjust range based on count vs k
     * 
     * Time: O(n * log(max-min)), Space: O(1)
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessEqual(matrix, mid);
            
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private static int countLessEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int row = n - 1, col = 0; // Start from bottom-left
        
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1; // All elements in this column up to row
                col++;
            } else {
                row--;
            }
        }
        
        return count;
    }
    
    /**
     * 🟢 EASY: Maximum Average Subarray I
     * 
     * Problem: Find contiguous subarray of length k with maximum average.
     * 
     * Approach: Sliding window technique (not binary search, but related to range optimization)
     * - Calculate initial sum of first k elements
     * - Slide window and update sum
     * - Track maximum sum found
     * 
     * Time: O(n), Space: O(1)
     */
    public static double findMaxAverage(int[] nums, int k) {
        // Calculate sum of first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        int maxSum = sum;
        
        // Slide the window
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i]; // Remove left, add right
            maxSum = Math.max(maxSum, sum);
        }
        
        return (double) maxSum / k;
    }
    
    /**
     * 🟡 MEDIUM: Find Minimum in Rotated Sorted Array II (with duplicates)
     * 
     * Problem: Find minimum element in rotated sorted array that may contain duplicates.
     * 
     * Approach: Modified binary search with duplicate handling
     * - When nums[mid] == nums[right], cannot determine which half to search
     * - Reduce search space by decrementing right pointer
     * - Otherwise, use same logic as original problem
     * 
     * Time: O(log n) average, O(n) worst case, Space: O(1)
     */
    public static int findMinWithDuplicates(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1; // Minimum is in right half
            } else if (nums[mid] < nums[right]) {
                right = mid; // Minimum is in left half or at mid
            } else {
                // nums[mid] == nums[right], cannot determine which half
                // Reduce search space by one element
                right--;
            }
        }
        
        return nums[left];
    }
    
    // ============================================================================
    // 🔢 COUNTING OCCURRENCES - Frequency Analysis and Element Counting
    // ============================================================================
    
    /**
     * 🟢 EASY: Count Negative Numbers in a Sorted Matrix
     * 
     * Problem: Count negative numbers in row and column sorted matrix.
     * 
     * Approach: Optimized traversal using sorted properties
     * - Start from top-right corner (or bottom-left)
     * - Use sorted properties to eliminate rows/columns efficiently
     * - Count negatives encountered during traversal
     * 
     * Time: O(m + n), Space: O(1)
     */
    public static int countNegatives(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int row = 0, col = cols - 1; // Start from top-right
        int count = 0;
        
        while (row < rows && col >= 0) {
            if (grid[row][col] < 0) {
                // All elements in this column below current row are negative
                count += rows - row;
                col--; // Move to previous column
            } else {
                // Current element is non-negative, move to next row
                row++;
            }
        }
        
        return count;
    }
    
    /**
     * 🟡 MEDIUM: Search a 2D Matrix II
     * 
     * Problem: Search target in matrix where rows and columns are sorted.
     * 
     * Approach: Optimized search using sorted properties
     * - Start from top-right corner (or bottom-left)
     * - Use comparison to eliminate row or column in each step
     * - Continue until target found or search space exhausted
     * 
     * Time: O(m + n), Space: O(1)
     */
    public static boolean searchMatrix2D(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        int row = 0, col = matrix[0].length - 1; // Start from top-right
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--; // Target is smaller, move left
            } else {
                row++; // Target is larger, move down
            }
        }
        
        return false;
    }
    
    /**
     * 🟡 MEDIUM: Count Primes (Sieve of Eratosthenes)
     * 
     * Problem: Count number of prime numbers less than n.
     * 
     * Approach: Sieve of Eratosthenes algorithm
     * - Create boolean array to mark non-prime numbers
     * - For each prime, mark its multiples as non-prime
     * - Count remaining unmarked (prime) numbers
     * 
     * Time: O(n log log n), Space: O(n)
     */
    public static int countPrimes(int n) {
        if (n <= 2) return 0;
        
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime
        
        // Sieve of Eratosthenes
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // Mark multiples of i as non-prime
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // Count primes
        int count = 0;
        for (boolean prime : isPrime) {
            if (prime) count++;
        }
        
        return count;
    }
    
    /**
     * 🟡 MEDIUM: Count Occurrences in Range (Using Binary Search)
     * 
     * Problem: Count occurrences of target in sorted array using first/last position.
     * 
     * Approach: Binary search for first and last occurrence
     * - Find first occurrence using lower bound
     * - Find last occurrence using upper bound
     * - Count = last - first + 1 (if target exists)
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int countOccurrences(int[] nums, int target) {
        int first = findFirst(nums, target);
        if (first == -1) return 0; // Target not found
        
        int last = findLast(nums, target);
        return last - first + 1;
    }
    
    // Helper methods already implemented in searchRange function above
    // Using the same findFirst and findLast methods
    
    /**
     * 🟢 EASY: Count Elements Less Than or Equal to Target
     * 
     * Problem: Count elements <= target in sorted array using binary search.
     * 
     * Approach: Upper bound binary search
     * - Find first position where element > target
     * - Count = position (all elements before this position are <= target)
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int countLessEqual(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left; // Number of elements <= target
    }
    
    /**
     * 🟢 EASY: Count Elements Greater Than Target
     * 
     * Problem: Count elements > target in sorted array using binary search.
     * 
     * Approach: Lower bound binary search for target + 1
     * - Find first position where element > target
     * - Count = array.length - position
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int countGreater(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums.length - left; // Number of elements > target
    }
    
    // ============================================================================
    // ⛰️ BITONIC ARRAY SEARCH - Mountain and Bitonic Array Search Patterns
    // ============================================================================
    
    /**
     * 🔴 HARD: Find in Mountain Array
     * 
     * Problem: Find target in mountain array (strictly increasing then decreasing).
     * 
     * Approach: Three-phase binary search
     * - Phase 1: Find peak index using binary search
     * - Phase 2: Search in increasing part (left side)
     * - Phase 3: Search in decreasing part (right side) if not found in left
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int findInMountainArray(int target, int[] mountainArr) {
        // Phase 1: Find peak index
        int peakIndex = findPeakInMountainArray(mountainArr);
        
        // Phase 2: Search in increasing part (0 to peak)
        int leftResult = binarySearchIncreasing(mountainArr, target, 0, peakIndex);
        if (leftResult != -1) {
            return leftResult; // Found in left part, return immediately
        }
        
        // Phase 3: Search in decreasing part (peak to end)
        return binarySearchDecreasing(mountainArr, target, peakIndex, mountainArr.length - 1);
    }
    
    private static int findPeakInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1; // Peak is on the right
            } else {
                right = mid; // Peak is on the left or at mid
            }
        }
        
        return left;
    }
    
    private static int binarySearchIncreasing(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    private static int binarySearchDecreasing(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                left = mid + 1; // In decreasing array, go right for smaller values
            } else {
                right = mid - 1; // In decreasing array, go left for larger values
            }
        }
        
        return -1;
    }
    
    /**
     * 🟡 MEDIUM: Longest Mountain in Array
     * 
     * Problem: Find length of longest mountain (strictly increasing then decreasing).
     * 
     * Approach: Two-pass algorithm with expansion
     * - For each potential peak, expand left and right
     * - Count consecutive increasing elements on left
     * - Count consecutive decreasing elements on right
     * - Mountain length = left + right + 1 (if both left and right > 0)
     * 
     * Time: O(n), Space: O(1)
     */
    public static int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;
        
        int maxLength = 0;
        
        for (int i = 1; i < n - 1; i++) {
            // Check if current element can be a peak
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                // Expand left to count increasing sequence
                int left = i - 1;
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }
                
                // Expand right to count decreasing sequence
                int right = i + 1;
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }
                
                // Calculate mountain length
                int mountainLength = right - left + 1;
                maxLength = Math.max(maxLength, mountainLength);
                
                // Optimization: skip to end of current mountain
                i = right;
            }
        }
        
        return maxLength;
    }
    
    /**
     * 🔴 HARD: Maximum Value at a Given Index in a Bounded Array
     * 
     * Problem: Construct array where arr[index] is maximized, all elements positive,
     * sum <= maxSum, and adjacent elements differ by at most 1.
     * 
     * Approach: Binary search on maximum value + validation
     * - Search for maximum possible value at given index
     * - For each candidate value, calculate minimum sum required
     * - Use arithmetic progression to calculate sum efficiently
     * 
     * Time: O(log(maxSum)), Space: O(1)
     */
    public static int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        int result = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canAchieveValue(n, index, maxSum, mid)) {
                result = mid;
                left = mid + 1; // Try larger value
            } else {
                right = mid - 1; // Try smaller value
            }
        }
        
        return result;
    }
    
    private static boolean canAchieveValue(int n, int index, int maxSum, int value) {
        long totalSum = value; // Start with the value at index
        
        // Calculate sum for left side
        int leftElements = index;
        if (leftElements > 0) {
            if (value > leftElements) {
                // Arithmetic progression: (value-1) + (value-2) + ... + (value-leftElements)
                totalSum += ((long)(value - 1) + (value - leftElements)) * leftElements / 2;
            } else {
                // Some elements will be 1: (value-1) + (value-2) + ... + 1 + 1 + ... + 1
                totalSum += ((long)(value - 1) * value) / 2;
                totalSum += leftElements - (value - 1);
            }
        }
        
        // Calculate sum for right side
        int rightElements = n - index - 1;
        if (rightElements > 0) {
            if (value > rightElements) {
                // Arithmetic progression: (value-1) + (value-2) + ... + (value-rightElements)
                totalSum += ((long)(value - 1) + (value - rightElements)) * rightElements / 2;
            } else {
                // Some elements will be 1: (value-1) + (value-2) + ... + 1 + 1 + ... + 1
                totalSum += ((long)(value - 1) * value) / 2;
                totalSum += rightElements - (value - 1);
            }
        }
        
        return totalSum <= maxSum;
    }
    
    /**
     * 🟡 MEDIUM: Find Valley in Bitonic Array
     * 
     * Problem: Find minimum element in bitonic array (decreasing then increasing).
     * 
     * Approach: Modified binary search for valley detection
     * - Compare mid with adjacent elements to determine valley location
     * - Move toward decreasing side until valley is found
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int findValley(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[mid + 1]) {
                right = mid; // Valley is on the right or at mid
            } else {
                left = mid + 1; // Valley is on the right
            }
        }
        
        return left; // Return valley index
    }
    
    /**
     * 🟡 MEDIUM: Search in Rotated Bitonic Array
     * 
     * Problem: Search target in rotated bitonic array.
     * 
     * Approach: Multi-phase search
     * - Find rotation point or peak
     * - Determine which part target might be in
     * - Apply appropriate search (increasing/decreasing)
     * 
     * Time: O(log n), Space: O(1)
     */
    public static int searchRotatedBitonic(int[] arr, int target) {
        // First find the peak or rotation point
        int peak = findPeakInRotatedBitonic(arr);
        
        // Search in different segments based on array structure
        // This is a simplified version - real implementation would need
        // to handle various rotation scenarios
        
        // Try searching in increasing part
        int result = binarySearchIncreasing(arr, target, 0, peak);
        if (result != -1) return result;
        
        // Try searching in decreasing part
        return binarySearchDecreasing(arr, target, peak, arr.length - 1);
    }
    
    private static int findPeakInRotatedBitonic(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Compare with next element to find peak
            if (mid < arr.length - 1 && arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // ============================================================================
    // 🧪 TEST METHODS
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🔍 BINARY SEARCH PATTERN - PRACTICE PROBLEMS");
        System.out.println("=============================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        System.out.println("\n1. Binary Search (Classic):");
        int[] sortedArray = {-1, 0, 3, 5, 9, 12};
        System.out.println("Array: " + Arrays.toString(sortedArray));
        System.out.println("Search for 9: Index " + search(sortedArray, 9));
        System.out.println("Search for 2: Index " + search(sortedArray, 2));
        
        System.out.println("\n2. Search Insert Position:");
        int[] insertArray = {1, 3, 5, 6};
        System.out.println("Array: " + Arrays.toString(insertArray));
        System.out.println("Insert position for 5: " + searchInsert(insertArray, 5));
        System.out.println("Insert position for 2: " + searchInsert(insertArray, 2));
        
        System.out.println("\n3. First Bad Version:");
        System.out.println("Versions 1-10, first bad is 4: " + firstBadVersion(10));
        
        System.out.println("\n4. Sqrt(x):");
        System.out.println("Sqrt of 4: " + mySqrt(4));
        System.out.println("Sqrt of 8: " + mySqrt(8));
        
        System.out.println("\n5. Valid Perfect Square:");
        System.out.println("Is 16 a perfect square? " + isPerfectSquare(16));
        System.out.println("Is 15 a perfect square? " + isPerfectSquare(15));
        
        System.out.println("\n6. Find Smallest Letter Greater Than Target:");
        char[] letters = {'c', 'f', 'j'};
        System.out.println("Letters: " + Arrays.toString(letters) + ", Target: 'a'");
        System.out.println("Next greatest letter: " + nextGreatestLetter(letters, 'a'));
        System.out.println("Next greatest letter: " + nextGreatestLetter(letters, 'c'));
        System.out.println("Next greatest letter: " + nextGreatestLetter(letters, 'j'));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        System.out.println("\n7. Find First and Last Position:");
        int[] rangeArray = {5, 7, 7, 8, 8, 10};
        System.out.println("Array: " + Arrays.toString(rangeArray));
        System.out.println("Range of 8: " + Arrays.toString(searchRange(rangeArray, 8)));
        System.out.println("Range of 6: " + Arrays.toString(searchRange(rangeArray, 6)));
        
        System.out.println("\n8. Search in Rotated Sorted Array:");
        int[] rotatedArray = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Array: " + Arrays.toString(rotatedArray));
        System.out.println("Search for 0: Index " + searchRotated(rotatedArray, 0));
        System.out.println("Search for 3: Index " + searchRotated(rotatedArray, 3));
        
        System.out.println("\n9. Find Peak Element:");
        int[] peakArray = {1, 2, 3, 1};
        System.out.println("Array: " + Arrays.toString(peakArray));
        System.out.println("Peak element index: " + findPeakElement(peakArray));
        
        System.out.println("\n10. Koko Eating Bananas:");
        int[] piles = {3, 6, 7, 11};
        System.out.println("Piles: " + Arrays.toString(piles) + ", Hours: 8");
        System.out.println("Minimum eating speed: " + minEatingSpeed(piles, 8));
        
        System.out.println("\n11. Peak Index in a Mountain Array:");
        int[] mountainArray = {0, 1, 0};
        System.out.println("Array: " + Arrays.toString(mountainArray));
        System.out.println("Peak index: " + peakIndexInMountainArray(mountainArray));
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        System.out.println("\n12. Median of Two Sorted Arrays:");
        int[] nums1 = {1, 3}, nums2 = {2};
        System.out.println("Array1: " + Arrays.toString(nums1));
        System.out.println("Array2: " + Arrays.toString(nums2));
        System.out.println("Median: " + findMedianSortedArrays(nums1, nums2));
        
        System.out.println("\n13. Split Array Largest Sum:");
        int[] splitArray = {7, 2, 5, 10, 8};
        System.out.println("Array: " + Arrays.toString(splitArray) + ", m: 2");
        System.out.println("Minimum largest sum: " + splitArray(splitArray, 2));
        
        System.out.println("\n14. Find K-th Smallest Pair Distance:");
        int[] distanceArray = {1, 3, 1};
        System.out.println("Array: " + Arrays.toString(distanceArray) + ", k: 1");
        System.out.println("Smallest distance: " + smallestDistancePair(distanceArray, 1));
        
        System.out.println("\n15. Minimize Max Distance to Gas Station:");
        int[] gasStations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Stations: " + Arrays.toString(gasStations) + ", k: 9");
        System.out.println("Minimum max distance: " + minmaxGasDist(gasStations, 9));
        
        System.out.println("\n📋 ALLOCATION PROBLEMS:");
        System.out.println("========================");
        int[] books = {10, 20, 30, 40};
        System.out.println("Books: " + Arrays.toString(books) + ", Students: 2");
        System.out.println("Minimum pages per student: " + allocateBooks(books, 2));

        int[] minPairSumArray = {1, 3, 2, 4};
        System.out.println("Array for minPairSum: " + Arrays.toString(minPairSumArray));
        System.out.println("Minimize max pair sum: " + minPairSum(minPairSumArray));

        int[] sweetness = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Sweetness: " + Arrays.toString(sweetness) + ", k: 3");
        System.out.println("Maximize minimum sweetness: " + maximizeMinSweetness(sweetness, 3));
        
        System.out.println("\n🔍 RANGE SEARCH PROBLEMS:");
        System.out.println("========================");
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println("Matrix: " + Arrays.deepToString(matrix));
        System.out.println("Kth smallest (k=8): " + kthSmallest(matrix, 8));

        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Max Average Subarray (k=3): " + findMaxAverage(nums, 3));

        int[] numsWithDuplicates = {3, 3, 1, 3};
        System.out.println("Array with duplicates: " + Arrays.toString(numsWithDuplicates));
        System.out.println("Min with duplicates (k=1): " + findMinWithDuplicates(numsWithDuplicates));
        
        System.out.println("\n🔢 COUNTING OCCURRENCES:");
        System.out.println("========================");
        int[][] grid = {{-1, -1}, {-1, -1}};
        System.out.println("Grid: " + Arrays.deepToString(grid));
        System.out.println("Count negatives: " + countNegatives(grid));

        int[] matrix2DArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Matrix 2D array: " + Arrays.toString(matrix2DArray));
        System.out.println("Search in Matrix II (target=5): " + searchMatrix2D(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 5));

        System.out.println("Count primes (n=10): " + countPrimes(10));

        int[] numsForCountOccurrences = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("Array for countOccurrences (target=2): " + Arrays.toString(numsForCountOccurrences));
        System.out.println("Count occurrences of 2: " + countOccurrences(numsForCountOccurrences, 2));

        System.out.println("Count elements <= 3: " + countLessEqual(numsForCountOccurrences, 3));
        System.out.println("Count elements > 3: " + countGreater(numsForCountOccurrences, 3));
        
        System.out.println("\n⛰️ BITONIC ARRAY SEARCH:");
        System.out.println("========================");
        int[] mountainArray2 = {1, 2, 3, 4, 5, 3, 1};
        System.out.println("Mountain Array: " + Arrays.toString(mountainArray2));
        System.out.println("Find in Mountain Array (target=3): " + findInMountainArray(3, mountainArray2));
        System.out.println("Find in Mountain Array (target=6): " + findInMountainArray(6, mountainArray2));
        System.out.println("Longest Mountain: " + longestMountain(mountainArray2));

        int[] bitonicArray = {1, 3, 5, 4, 2};
        System.out.println("Bitonic Array: " + Arrays.toString(bitonicArray));
        System.out.println("Find Valley: " + findValley(bitonicArray));
        System.out.println("Search in Rotated Bitonic (target=4): " + searchRotatedBitonic(bitonicArray, 4));

        System.out.println("\n✅ All Binary Search problems implemented!");
        System.out.println("Time Complexities: O(log n) for most, O(n log n) for some");
        System.out.println("Space Complexities: O(1) for iterative, O(log n) for recursive");
    }
} 