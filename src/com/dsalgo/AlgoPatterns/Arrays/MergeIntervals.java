package com.dsalgo.AlgoPatterns.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Merge Intervals is a pattern used to deal with overlapping intervals.
 * 
 * It is used to solve problems involving ranges, scheduling, calendar events,
 * and time-based conflicts. The pattern typically involves sorting intervals
 * and then merging or manipulating overlapping ones.
 * 
 * Look for problems involving intervals or ranges of values, such as time events,
 * scheduling, or overlapping events.
 * 
 * Consider tasks where you need to combine or compare intervals, merge overlapping
 * intervals, or find intersections between them.
 * 
 * Look for problems where the input involves intervals represented as pairs of
 * start and end points, and the task revolves around combining or manipulating
 * these intervals.
 * 
 * Example:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 */
public class MergeIntervals {

    public static void main(String[] args) {
        // Test Merge Intervals problems
        System.out.println("=== MERGE INTERVALS PROBLEMS TEST ===");
        
        // Test Basic Merge
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] merged = merge(intervals);
        System.out.println("Merged intervals: " + Arrays.deepToString(merged));
        
        // Test Insert Interval
        int[][] existing = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        int[][] inserted = insert(existing, newInterval);
        System.out.println("After insertion: " + Arrays.deepToString(inserted));
        
        // Test Meeting Rooms
        int[][] meetings = {{0,30},{5,10},{15,20}};
        System.out.println("Can attend all meetings: " + canAttendMeetings(meetings));
        System.out.println("Min meeting rooms needed: " + minMeetingRooms(meetings));
        
        // Test Interval Intersections
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        int[][] intersections = intervalIntersection(firstList, secondList);
        System.out.println("Intersections: " + Arrays.deepToString(intersections));
    }

    // ==================================================================================
    //                          MERGE INTERVALS PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Merge Intervals
     * DIFFICULTY: Easy
     * PATTERN: Basic Merge Intervals
     * 
     * DESCRIPTION:
     * Given an array of intervals where intervals[i] = [start_i, end_i], merge all 
     * overlapping intervals, and return an array of the non-overlapping intervals 
     * that cover all the intervals in the input.
     * 
     * APPROACH:
     * 1. Sort intervals by start time
     * 2. Initialize with first interval
     * 3. For each subsequent interval, check if it overlaps with current
     * 4. If overlapping, merge them; otherwise, add to result
     * 
     * TIME COMPLEXITY: O(n log n) - due to sorting
     * SPACE COMPLEXITY: O(n) - for result array
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        List<int[]> merged = new ArrayList<>();
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
                // Non-overlapping interval, add new one
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    /**
     * PROBLEM: Meeting Rooms I
     * DIFFICULTY: Easy
     * PATTERN: Basic Interval Overlap Detection
     * 
     * DESCRIPTION:
     * Given an array of meeting time intervals where intervals[i] = [start_i, end_i], 
     * determine if a person could attend all meetings.
     * 
     * APPROACH:
     * 1. Sort intervals by start time
     * 2. Check if any adjacent intervals overlap
     * 3. If overlap found, return false
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length <= 1) return true;
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            // Check if current meeting starts before previous ends
            if (intervals[i][0] < intervals[i-1][1]) {
                return false; // Overlap detected
            }
        }
        
        return true;
    }
    
    /**
     * PROBLEM: Summary Ranges
     * DIFFICULTY: Easy
     * PATTERN: Range Formation
     * 
     * DESCRIPTION:
     * You are given a sorted unique integer array nums. Return the smallest 
     * sorted list of ranges that cover all the numbers in the array exactly.
     * 
     * APPROACH:
     * 1. Iterate through sorted array
     * 2. Track start and end of current range
     * 3. When gap found, close current range and start new one
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1) excluding result
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) return result;
        
        int start = nums[0];
        int end = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                // Continuous range, extend end
                end = nums[i];
            } else {
                // Gap found, add current range
                if (start == end) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + end);
                }
                start = end = nums[i];
            }
        }
        
        // Add final range
        if (start == end) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + end);
        }
        
        return result;
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Insert Interval
     * DIFFICULTY: Medium
     * PATTERN: Insert and Merge
     * 
     * DESCRIPTION:
     * You are given an array of non-overlapping intervals intervals where 
     * intervals[i] = [start_i, end_i] represent the start and the end of the 
     * ith interval and intervals is sorted in ascending order by start_i. 
     * You are also given an interval newInterval = [start, end] that represents 
     * the start and end of another interval. Insert newInterval into intervals 
     * such that intervals is still sorted in ascending order by start_i and 
     * intervals still does not have any overlapping intervals.
     * 
     * APPROACH:
     * 1. Add all intervals that end before newInterval starts
     * 2. Merge all overlapping intervals with newInterval
     * 3. Add all remaining intervals
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        
        // Add all intervals that end before newInterval starts (no overlap)
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Merge all overlapping intervals with newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Add all remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    /**
     * PROBLEM: Non-overlapping Intervals
     * DIFFICULTY: Medium
     * PATTERN: Greedy Interval Selection
     * 
     * DESCRIPTION:
     * Given an array of intervals intervals where intervals[i] = [start_i, end_i], 
     * return the minimum number of intervals you need to remove to make the rest 
     * of the intervals non-overlapping.
     * 
     * APPROACH:
     * 1. Sort by end time (greedy: keep intervals that end earliest)
     * 2. Track end time of last kept interval
     * 3. Count intervals that need to be removed
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        
        // Sort by end time for greedy approach
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        
        int count = 0;
        int end = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                // Overlapping interval, need to remove it
                count++;
            } else {
                // Non-overlapping, update end time
                end = intervals[i][1];
            }
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Meeting Rooms II
     * DIFFICULTY: Medium
     * PATTERN: Resource Allocation with Heap
     * 
     * DESCRIPTION:
     * Given an array of meeting time intervals intervals where 
     * intervals[i] = [start_i, end_i], return the minimum number of 
     * conference rooms required.
     * 
     * APPROACH:
     * 1. Sort meetings by start time
     * 2. Use min-heap to track end times of ongoing meetings
     * 3. For each meeting, check if any room becomes free
     * 4. Heap size represents number of rooms needed
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Min-heap to track end times of ongoing meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            // If current meeting starts after earliest ending meeting
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll(); // Room becomes free
            }
            // Add current meeting's end time
            minHeap.add(intervals[i][1]);
        }
        
        return minHeap.size();
    }
    
    /**
     * PROBLEM: Interval List Intersections
     * DIFFICULTY: Medium
     * PATTERN: Two Pointers with Intervals
     * 
     * DESCRIPTION:
     * You are given two lists of closed intervals, firstList and secondList, 
     * where firstList[i] = [start_i, end_i] and secondList[j] = [start_j, end_j]. 
     * Each list of intervals is pairwise disjoint and in sorted order.
     * Return the intersection of these two interval lists.
     * 
     * APPROACH:
     * 1. Use two pointers for both lists
     * 2. Find intersection of current intervals
     * 3. Move pointer of interval that ends first
     * 
     * TIME COMPLEXITY: O(m + n)
     * SPACE COMPLEXITY: O(min(m, n)) for result
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < firstList.length && j < secondList.length) {
            // Calculate intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            
            // If valid intersection exists
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
     * PROBLEM: Remove Covered Intervals
     * DIFFICULTY: Medium
     * PATTERN: Interval Coverage
     * 
     * DESCRIPTION:
     * Given an array intervals where intervals[i] = [li, ri] represent the 
     * interval [li, ri), remove all intervals that are covered by another 
     * interval in the list. Return the number of remaining intervals.
     * 
     * APPROACH:
     * 1. Sort by start time, then by end time (descending)
     * 2. Track the maximum end time seen so far
     * 3. If current interval is covered, increment removal count
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        // Sort by start time, then by end time (descending for same start)
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int count = 0;
        int end = 0;
        
        for (int[] interval : intervals) {
            if (interval[1] > end) {
                // This interval is not covered
                count++;
                end = interval[1];
            }
            // If interval[1] <= end, this interval is covered, so don't count it
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Car Pooling
     * DIFFICULTY: Medium
     * PATTERN: Sweep Line Algorithm
     * 
     * DESCRIPTION:
     * There is a car with capacity empty seats. The vehicle only drives east 
     * (i.e., it cannot turn around and drive west). You are given the integer 
     * capacity and an array trips where trips[i] = [numPassengers_i, from_i, to_i] 
     * indicates that the ith trip has numPassengers_i passengers and the locations 
     * to pick them up and drop them off are from_i and to_i respectively. 
     * The locations are given as the number of kilometers due east from the car's initial location.
     * 
     * APPROACH:
     * 1. Create events for passenger pickup (+) and drop-off (-)
     * 2. Sort events by location
     * 3. Process events and track current passenger count
     * 4. Check if capacity is ever exceeded
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        List<int[]> events = new ArrayList<>();
        
        // Create events: [location, passengerChange]
        for (int[] trip : trips) {
            events.add(new int[]{trip[1], trip[0]});    // Pick up passengers
            events.add(new int[]{trip[2], -trip[0]});   // Drop off passengers
        }
        
        // Sort by location, process drop-offs before pick-ups at same location
        events.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        int currentPassengers = 0;
        for (int[] event : events) {
            currentPassengers += event[1];
            if (currentPassengers > capacity) {
                return false;
            }
        }
        
        return true;
    }
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Employee Free Time
     * DIFFICULTY: Hard
     * PATTERN: Merge All Intervals + Find Gaps
     * 
     * DESCRIPTION:
     * We are given a list schedule of employees, which represents the working time 
     * for each employee. Each employee has a list of non-overlapping Intervals, 
     * and these intervals are in sorted order. Return the list of finite intervals 
     * representing common, positive-length free time for all employees.
     * 
     * APPROACH:
     * 1. Collect all intervals from all employees
     * 2. Sort and merge overlapping intervals
     * 3. Find gaps between merged intervals
     * 
     * TIME COMPLEXITY: O(n log n) where n is total intervals
     * SPACE COMPLEXITY: O(n)
     */
    public static int[][] employeeFreeTime(int[][][] schedule) {
        List<int[]> allIntervals = new ArrayList<>();
        
        // Collect all work intervals
        for (int[][] employee : schedule) {
            for (int[] interval : employee) {
                allIntervals.add(interval);
            }
        }
        
        // Sort by start time
        allIntervals.sort((a, b) -> a[0] - b[0]);
        
        // Merge overlapping intervals
        List<int[]> merged = new ArrayList<>();
        int[] current = allIntervals.get(0);
        merged.add(current);
        
        for (int[] interval : allIntervals) {
            if (current[1] >= interval[0]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                current = interval;
                merged.add(current);
            }
        }
        
        // Find gaps between merged intervals
        List<int[]> freeTime = new ArrayList<>();
        for (int i = 1; i < merged.size(); i++) {
            if (merged.get(i-1)[1] < merged.get(i)[0]) {
                freeTime.add(new int[]{merged.get(i-1)[1], merged.get(i)[0]});
            }
        }
        
        return freeTime.toArray(new int[freeTime.size()][]);
    }
    
    /**
     * PROBLEM: My Calendar III
     * DIFFICULTY: Hard
     * PATTERN: Sweep Line with Event Counting
     * 
     * DESCRIPTION:
     * A k-booking happens when k events have some non-empty intersection 
     * (i.e., there is some time that is common to all k events.)
     * You are given some events [start, end), after each given event, 
     * return an integer k representing the maximum k-booking between all 
     * the previously given events.
     * 
     * APPROACH:
     * 1. Use TreeMap to store events in sorted order
     * 2. For each booking, increment start time and decrement end time
     * 3. Calculate maximum concurrent bookings
     * 
     * TIME COMPLEXITY: O(n²) per booking
     * SPACE COMPLEXITY: O(n)
     */
    static class MyCalendarThree {
        private java.util.TreeMap<Integer, Integer> timeline;
        
        public MyCalendarThree() {
            timeline = new java.util.TreeMap<>();
        }
        
        public int book(int start, int end) {
            // Increment at start, decrement at end
            timeline.put(start, timeline.getOrDefault(start, 0) + 1);
            timeline.put(end, timeline.getOrDefault(end, 0) - 1);
            
            // Calculate maximum concurrent bookings
            int ongoing = 0, maxBookings = 0;
            for (int count : timeline.values()) {
                ongoing += count;
                maxBookings = Math.max(maxBookings, ongoing);
            }
            
            return maxBookings;
        }
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to check if two intervals overlap
     */
    private static boolean overlaps(int[] interval1, int[] interval2) {
        return interval1[0] <= interval2[1] && interval2[0] <= interval1[1];
    }
    
    /**
     * Helper method to merge two overlapping intervals
     */
    private static int[] mergeTwo(int[] interval1, int[] interval2) {
        return new int[]{
            Math.min(interval1[0], interval2[0]),
            Math.max(interval1[1], interval2[1])
        };
    }
    
    /**
     * Helper method to print intervals for debugging
     */
    private static void printIntervals(int[][] intervals) {
        System.out.println(Arrays.deepToString(intervals));
    }
} 