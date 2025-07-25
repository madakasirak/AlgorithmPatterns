package com.dsalgo.AlgoPatterns.Arrays;

/**
 * ==================================================================================
 *                          MERGE INTERVALS PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * The Merge Intervals pattern is used to deal with overlapping intervals. It's a
 * fundamental technique for solving problems involving ranges, scheduling, calendar
 * events, and time-based conflicts. The pattern typically involves sorting intervals
 * and then merging or manipulating overlapping ones.
 * 
 * ==================================================================================
 *                             WHEN TO USE MERGE INTERVALS
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Problems involving intervals or ranges of values
 * ✅ Time events, scheduling, or calendar problems
 * ✅ Overlapping events or ranges
 * ✅ Tasks where you need to combine or compare intervals
 * ✅ Finding intersections between intervals
 * ✅ Input involves pairs of start and end points
 * ✅ Need to merge overlapping intervals
 * ✅ Resource allocation or booking systems
 * 
 * 🚨 RED FLAGS (when NOT to use):
 * ❌ Problems with single values (not ranges)
 * ❌ No overlap detection needed
 * ❌ Complex multi-dimensional constraints
 * ❌ Problems requiring specific ordering beyond start times
 * 
 * ==================================================================================
 *                            MERGE INTERVALS VARIATIONS
 * ==================================================================================
 */
public class MergeIntervalsReadingGuide {

    /**
     * ================================================================================
     *                           VARIATION 1: BASIC MERGE INTERVALS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Sort intervals by start time
     * 2. Initialize with first interval
     * 3. For each subsequent interval:
     *    - If it overlaps with current, merge them
     *    - Otherwise, add current to result and start new interval
     * 
     * USE CASES:
     * - Merge overlapping meetings
     * - Combine time ranges
     * - Calendar conflict resolution
     * - Resource booking optimization
     */
    
    // EXAMPLE: Basic Merge Intervals
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // Sort by start time
        java.util.Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        java.util.List<int[]> merged = new java.util.ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);
        
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            
            if (currentEnd >= nextStart) {
                // Overlapping intervals, merge them
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Non-overlapping, add new interval
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * ================================================================================
     *                           VARIATION 2: INSERT INTERVAL
     * ================================================================================
     * 
     * PATTERN:
     * 1. Add all intervals that end before new interval starts
     * 2. Merge all overlapping intervals with new interval
     * 3. Add all remaining intervals
     * 
     * USE CASES:
     * - Adding new meeting to calendar
     * - Inserting booking into schedule
     * - Dynamic interval insertion
     */
    
    // EXAMPLE: Insert Interval
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        java.util.List<int[]> result = new java.util.ArrayList<>();
        int i = 0;
        
        // Add all intervals that end before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Add remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }

    /**
     * ================================================================================
     *                         VARIATION 3: INTERVAL INTERSECTIONS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Use two pointers for two interval lists
     * 2. Find intersection of current intervals
     * 3. Move pointer of interval that ends first
     * 
     * USE CASES:
     * - Finding common available times
     * - Calendar intersection
     * - Resource conflict detection
     */
    
    // EXAMPLE: Interval List Intersections
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        java.util.List<int[]> result = new java.util.ArrayList<>();
        int i = 0, j = 0;
        
        while (i < firstList.length && j < secondList.length) {
            // Find intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            
            if (start <= end) {
                result.add(new int[]{start, end});
            }
            
            // Move pointer of interval that ends first
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }

    /**
     * ================================================================================
     *                      VARIATION 4: NON-OVERLAPPING INTERVALS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Sort by end time (greedy approach)
     * 2. Keep intervals that don't overlap with previously selected
     * 3. Count removals needed
     * 
     * USE CASES:
     * - Minimum meeting cancellations
     * - Resource optimization
     * - Scheduling conflicts resolution
     */
    
    // EXAMPLE: Non-overlapping Intervals (Minimum removals)
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        
        // Sort by end time for greedy approach
        java.util.Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        
        int count = 0;
        int end = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                // Overlapping interval, remove it
                count++;
            } else {
                // Non-overlapping, update end
                end = intervals[i][1];
            }
        }
        
        return count;
    }

    /**
     * ================================================================================
     *                          VARIATION 5: MEETING ROOMS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Sort by start time
     * 2. Use min-heap to track end times
     * 3. For each meeting, check if room is free
     * 
     * USE CASES:
     * - Conference room booking
     * - Resource allocation
     * - Parallel task scheduling
     */
    
    // EXAMPLE: Meeting Rooms II (Minimum rooms needed)
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // Sort by start time
        java.util.Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Min-heap to track end times
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            // If meeting starts after earliest ending meeting, reuse room
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            // Add current meeting's end time
            minHeap.add(intervals[i][1]);
        }
        
        return minHeap.size();
    }

    /**
     * ================================================================================
     *                            ADVANCED PATTERNS & TRICKS
     * ================================================================================
     */
    
    // SWEEP LINE ALGORITHM for multiple intervals
    public static int maxConcurrentMeetings(int[][] intervals) {
        java.util.List<int[]> events = new java.util.ArrayList<>();
        
        // Create events: +1 for start, -1 for end
        for (int[] interval : intervals) {
            events.add(new int[]{interval[0], 1});    // Meeting starts
            events.add(new int[]{interval[1], -1});   // Meeting ends
        }
        
        // Sort events by time, process ends before starts at same time
        events.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        int maxConcurrent = 0;
        int currentConcurrent = 0;
        
        for (int[] event : events) {
            currentConcurrent += event[1];
            maxConcurrent = Math.max(maxConcurrent, currentConcurrent);
        }
        
        return maxConcurrent;
    }

    /**
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. WRONG SORTING: Not sorting by appropriate field (start vs end time)
     * 2. BOUNDARY CONDITIONS: Handling edge cases like touching intervals
     * 3. OVERLAP LOGIC: Incorrect overlap detection (>= vs > for boundaries)
     * 4. GREEDY CHOICE: Wrong greedy strategy (end time vs start time sorting)
     * 5. MUTATION ISSUES: Modifying original intervals unintentionally
     * 
     * 💡 PRO TIPS:
     * 1. Always sort intervals first (usually by start time)
     * 2. Decide on boundary handling: are touching intervals overlapping?
     * 3. For optimization problems, consider sorting by end time (greedy)
     * 4. Use heap for tracking multiple resources (meeting rooms)
     * 5. Consider sweep line algorithm for complex interval problems
     * 6. Draw timeline diagrams to visualize overlap conditions
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Merge Intervals
     * - Meeting Rooms I
     * - Summary Ranges
     * - Data Stream as Disjoint Intervals
     * 
     * 🟡 MEDIUM:
     * - Insert Interval
     * - Non-overlapping Intervals
     * - Meeting Rooms II
     * - Interval List Intersections
     * - Remove Covered Intervals
     * - Partition Labels
     * - Car Pooling
     * 
     * 🔴 HARD:
     * - Employee Free Time
     * - My Calendar III
     * - Falling Squares
     * - Range Module
     * - Count of Range Sum
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Do I have intervals (start, end pairs)? → Consider interval patterns
     * 2. Need to merge overlapping intervals? → Basic merge pattern
     * 3. Need to insert new interval? → Insert interval pattern
     * 4. Need to find intersections? → Two-pointer intersection pattern
     * 5. Need to minimize conflicts? → Sort by end time + greedy
     * 6. Need to track multiple resources? → Use heap with sorted start times
     * 7. Complex overlap analysis? → Consider sweep line algorithm
     * 
     * TIME COMPLEXITY: Usually O(n log n) due to sorting
     * SPACE COMPLEXITY: O(n) for result or heap storage
     * 
     * Remember: Interval problems are fundamentally about time management and
     * resource allocation. Think about the real-world scenario being modeled!
     */
} 