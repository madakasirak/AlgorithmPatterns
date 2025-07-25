package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 PRIORITY QUEUE CONSTRUCTION AND MANIPULATION PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of priority queue construction
 * and manipulation algorithms for string reorganization, task scheduling, array
 * reconstruction, and sequence optimization. These algorithms demonstrate frequency-based
 * priority assignment, constraint satisfaction, greedy optimization strategies, and
 * temporal scheduling techniques using heap-based data structures for optimal performance.
 * 
 * Pattern Focus: Frequency optimization, constraint satisfaction, greedy construction, scheduling
 * Time Complexity: Generally O(n log k) where n = elements, k = unique types
 * Space Complexity: O(k) for frequency tracking, O(n) for result construction
 */

public class ConstructionManipulation {
    
    /**
     * Task Scheduler - CPU Task Execution with Cooling Period
     * 
     * Given a char array representing tasks CPU need to do. It contains capital
     * letters A to Z where different letters represent different tasks. Tasks
     * could be done without original order. Each task takes one unit of time.
     * For each unit of time, CPU could finish one task or be idle. However,
     * there is a non-negative cooling time n that means between two same tasks,
     * there must be at least n units of time that CPU are doing different tasks or just be idle.
     * 
     * Strategy: Use max-heap for frequency-based task selection with cooling management
     * Time: O(n log k) where n = total tasks, k = unique task types
     * Space: O(k) for frequency tracking and cooling queue
     * 
     * LeetCode: https://leetcode.com/problems/task-scheduler/
     */
    public static int leastInterval(char[] tasks, int n) {
        // Count task frequencies
        Map<Character, Integer> taskCount = new HashMap<>();
        for (char task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }
        
        // Max-heap for task selection by frequency
        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(
            Collections.reverseOrder()
        );
        maxHeap.addAll(taskCount.values());
        
        int totalTime = 0;
        
        while (!maxHeap.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            int cycleTime = 0;
            
            // Execute tasks for one cooling cycle (n+1 time units)
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    int freq = maxHeap.poll();
                    if (freq > 1) {
                        tempList.add(freq - 1); // Remaining frequency
                    }
                    cycleTime++;
                }
            }
            
            // Add remaining tasks back to heap
            maxHeap.addAll(tempList);
            
            // Add time: if more tasks remain, we need full cycle (n+1), else just cycleTime
            totalTime += maxHeap.isEmpty() ? cycleTime : n + 1;
        }
        
        return totalTime;
    }
    
    /**
     * Alternative Task Scheduler Implementation with Detailed Scheduling
     */
    public static class DetailedTaskScheduler {
        
        static class TaskInfo {
            char taskType;
            int frequency;
            int lastExecuted;
            
            TaskInfo(char taskType, int frequency) {
                this.taskType = taskType;
                this.frequency = frequency;
                this.lastExecuted = -1;
            }
        }
        
        public static List<Character> scheduleTasksDetailed(char[] tasks, int n) {
            // Count frequencies
            Map<Character, Integer> taskCount = new HashMap<>();
            for (char task : tasks) {
                taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
            }
            
            // Priority queue for available tasks
            java.util.PriorityQueue<TaskInfo> availableTasks = new java.util.PriorityQueue<>(
                (a, b) -> {
                    if (a.frequency != b.frequency) return b.frequency - a.frequency;
                    return a.taskType - b.taskType; // Tie-breaking
                }
            );
            
            // Initialize task infos
            for (Map.Entry<Character, Integer> entry : taskCount.entrySet()) {
                availableTasks.offer(new TaskInfo(entry.getKey(), entry.getValue()));
            }
            
            List<Character> schedule = new ArrayList<>();
            Queue<TaskInfo> coolingTasks = new LinkedList<>();
            int currentTime = 0;
            
            while (!availableTasks.isEmpty() || !coolingTasks.isEmpty()) {
                // Check if any cooling tasks can be made available
                while (!coolingTasks.isEmpty() && 
                       currentTime - coolingTasks.peek().lastExecuted > n) {
                    TaskInfo cooledTask = coolingTasks.poll();
                    if (cooledTask.frequency > 0) {
                        availableTasks.offer(cooledTask);
                    }
                }
                
                if (!availableTasks.isEmpty()) {
                    // Execute highest priority available task
                    TaskInfo selectedTask = availableTasks.poll();
                    schedule.add(selectedTask.taskType);
                    selectedTask.lastExecuted = currentTime;
                    selectedTask.frequency--;
                    
                    if (selectedTask.frequency > 0) {
                        coolingTasks.offer(selectedTask);
                    }
                } else {
                    // CPU idle
                    schedule.add('*'); // Idle indicator
                }
                
                currentTime++;
            }
            
            return schedule;
        }
    }
    
    /**
     * Rearrange String k Distance Apart
     * 
     * Given a non-empty string s and an integer k, rearrange the string such that
     * the same characters are at least distance k from each other. All input strings
     * are given in lowercase letters. If it is not possible to rearrange the string,
     * return an empty string "".
     * 
     * Strategy: Use max-heap with cooling period to maintain distance constraints
     * Time: O(n log k) where n = string length, k = unique characters
     * Space: O(k) for frequency tracking and cooling queue
     * 
     * LeetCode: https://leetcode.com/problems/rearrange-string-k-distance-apart/
     */
    public static String rearrangeString(String s, int k) {
        if (k <= 1) return s;
        
        // Count character frequencies
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Check if rearrangement is possible
        int maxFreq = Collections.max(charCount.values());
        if (maxFreq > (s.length() + k - 1) / k) {
            return ""; // Impossible due to frequency constraints
        }
        
        // Max-heap for character selection by frequency
        java.util.PriorityQueue<Character> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> {
                int freqCompare = charCount.get(b) - charCount.get(a);
                if (freqCompare != 0) return freqCompare;
                return a - b; // Lexicographical tie-breaking
            }
        );
        maxHeap.addAll(charCount.keySet());
        
        StringBuilder result = new StringBuilder();
        Queue<Character> cooldownQueue = new LinkedList<>();
        
        while (!maxHeap.isEmpty()) {
            char selected = maxHeap.poll();
            result.append(selected);
            
            // Decrease frequency
            charCount.put(selected, charCount.get(selected) - 1);
            cooldownQueue.offer(selected);
            
            // Maintain cooling window of size k
            if (cooldownQueue.size() >= k) {
                char cooledChar = cooldownQueue.poll();
                if (charCount.get(cooledChar) > 0) {
                    maxHeap.offer(cooledChar);
                }
            }
        }
        
        return result.length() == s.length() ? result.toString() : "";
    }
    
    /**
     * Reorganize String - Avoid Adjacent Duplicates
     * 
     * Given a string S, check if the letters can be rearranged so that two
     * characters that are adjacent to each other are not the same. If possible,
     * output any possible result. If not possible, return the empty string.
     * 
     * Strategy: Use max-heap to always pick most frequent available character
     * Time: O(n log k) where n = string length, k = unique characters
     * Space: O(k) for frequency tracking
     * 
     * LeetCode: https://leetcode.com/problems/reorganize-string/
     */
    public static String reorganizeString(String s) {
        // Count character frequencies
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Check if reorganization is possible
        int maxFreq = Collections.max(charCount.values());
        if (maxFreq > (s.length() + 1) / 2) {
            return ""; // Impossible to reorganize
        }
        
        // Max-heap based on character frequency
        java.util.PriorityQueue<Character> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> {
                int freqCompare = charCount.get(b) - charCount.get(a);
                if (freqCompare != 0) return freqCompare;
                return a - b; // Lexicographical order for tie-breaking
            }
        );
        maxHeap.addAll(charCount.keySet());
        
        StringBuilder result = new StringBuilder();
        
        while (maxHeap.size() >= 2) {
            // Take two most frequent characters to avoid adjacency
            char first = maxHeap.poll();
            char second = maxHeap.poll();
            
            result.append(first).append(second);
            
            // Decrease counts and re-add if still available
            charCount.put(first, charCount.get(first) - 1);
            charCount.put(second, charCount.get(second) - 1);
            
            if (charCount.get(first) > 0) maxHeap.offer(first);
            if (charCount.get(second) > 0) maxHeap.offer(second);
        }
        
        // Handle remaining character
        if (!maxHeap.isEmpty()) {
            char remaining = maxHeap.poll();
            if (charCount.get(remaining) > 1) {
                return ""; // Multiple instances of same character left
            }
            result.append(remaining);
        }
        
        return result.toString();
    }
    
    /**
     * Distant Barcodes - Ensure No Adjacent Duplicates in Array
     * 
     * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
     * Rearrange the barcodes so that no two adjacent barcodes are equal.
     * You may return any answer, and it is guaranteed an answer exists.
     * 
     * Strategy: Frequency-based placement with alternating positions
     * Time: O(n log k) where n = array length, k = unique barcodes
     * Space: O(k) for frequency tracking
     * 
     * LeetCode: https://leetcode.com/problems/distant-barcodes/
     */
    public static int[] rearrangeBarcodes(int[] barcodes) {
        // Count frequencies
        Map<Integer, Integer> count = new HashMap<>();
        for (int code : barcodes) {
            count.put(code, count.getOrDefault(code, 0) + 1);
        }
        
        // Max-heap for frequency-based selection
        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> {
                int freqCompare = count.get(b) - count.get(a);
                if (freqCompare != 0) return freqCompare;
                return a - b; // Smaller value first for tie-breaking
            }
        );
        maxHeap.addAll(count.keySet());
        
        int[] result = new int[barcodes.length];
        int index = 0;
        
        // Place most frequent element first at even positions
        int mostFrequent = maxHeap.poll();
        while (count.get(mostFrequent) > 0) {
            result[index] = mostFrequent;
            count.put(mostFrequent, count.get(mostFrequent) - 1);
            index += 2;
            
            // Switch to odd positions when even positions are filled
            if (index >= barcodes.length) {
                index = 1;
            }
        }
        
        // Place remaining elements
        while (!maxHeap.isEmpty()) {
            int current = maxHeap.poll();
            while (count.get(current) > 0) {
                result[index] = current;
                count.put(current, count.get(current) - 1);
                index += 2;
                
                if (index >= barcodes.length) {
                    index = 1;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Rearrange Words in a Sentence by Length
     * 
     * Given a sentence text, you want to rearrange the words so that all words
     * of the same length are grouped together and ordered from shortest to longest.
     * Words should remain in their original relative order if they have the same length.
     * 
     * Strategy: Stable sorting using priority queue with custom comparator
     * Time: O(n log n) where n = number of words
     * Space: O(n) for word storage
     * 
     * LeetCode: https://leetcode.com/problems/rearrange-words-in-a-sentence/
     */
    public static String arrangeWords(String text) {
        // Split into words and normalize case
        String[] words = text.toLowerCase().split(" ");
        
        // Custom comparator: by length first, then by original order (stable)
        java.util.PriorityQueue<int[]> wordQueue = new java.util.PriorityQueue<>((a, b) -> {
            int lengthCompare = Integer.compare(words[a[0]].length(), words[b[0]].length());
            if (lengthCompare != 0) return lengthCompare;
            return Integer.compare(a[0], b[0]); // Original index for stability
        });
        
        // Add word indices to priority queue
        for (int i = 0; i < words.length; i++) {
            wordQueue.offer(new int[]{i}); // Store original index
        }
        
        // Reconstruct sentence
        StringBuilder result = new StringBuilder();
        boolean first = true;
        
        while (!wordQueue.isEmpty()) {
            int wordIndex = wordQueue.poll()[0];
            String word = words[wordIndex];
            
            if (first) {
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                first = false;
            } else {
                result.append(" ");
            }
            
            result.append(word);
        }
        
        return result.toString();
    }
    
    /**
     * BONUS: Remove Duplicate Letters with Lexicographical Order
     * Construction problem using greedy strategy with stack
     */
    public static String removeDuplicateLetters(String s) {
        // Count frequency of each character
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']--; // Decrease remaining count
            
            if (inStack[c - 'a']) {
                continue; // Already in result
            }
            
            // Remove larger characters if they appear later and current char is smaller
            while (!stack.isEmpty() && 
                   stack.peek() > c && 
                   count[stack.peek() - 'a'] > 0) {
                char removed = stack.pop();
                inStack[removed - 'a'] = false;
            }
            
            stack.push(c);
            inStack[c - 'a'] = true;
        }
        
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    /**
     * BONUS: Advanced String Construction with Multiple Constraints
     */
    public static class AdvancedStringConstructor {
        
        static class CharInfo {
            char character;
            int frequency;
            int priority;
            int lastUsed;
            
            CharInfo(char character, int frequency, int priority) {
                this.character = character;
                this.frequency = frequency;
                this.priority = priority;
                this.lastUsed = -1;
            }
        }
        
        public static String constructString(Map<Character, Integer> charFreq, 
                                           int minDistance, 
                                           int maxLength,
                                           Map<Character, Integer> priorities) {
            // Priority queue with multi-criteria comparison
            java.util.PriorityQueue<CharInfo> constructionQueue = new java.util.PriorityQueue<>((a, b) -> {
                // Primary: custom priority (descending)
                if (a.priority != b.priority) return b.priority - a.priority;
                
                // Secondary: frequency (descending)
                if (a.frequency != b.frequency) return b.frequency - a.frequency;
                
                // Tertiary: lexicographical order (ascending)
                return a.character - b.character;
            });
            
            // Initialize character infos
            for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
                char ch = entry.getKey();
                int freq = entry.getValue();
                int priority = priorities.getOrDefault(ch, 1);
                constructionQueue.offer(new CharInfo(ch, freq, priority));
            }
            
            StringBuilder result = new StringBuilder();
            Queue<CharInfo> cooldownQueue = new LinkedList<>();
            
            while (!constructionQueue.isEmpty() && result.length() < maxLength) {
                // Check if any cooling characters can be made available
                while (!cooldownQueue.isEmpty() && 
                       result.length() - cooldownQueue.peek().lastUsed >= minDistance) {
                    CharInfo cooledChar = cooldownQueue.poll();
                    if (cooledChar.frequency > 0) {
                        constructionQueue.offer(cooledChar);
                    }
                }
                
                if (!constructionQueue.isEmpty()) {
                    CharInfo selected = constructionQueue.poll();
                    result.append(selected.character);
                    selected.lastUsed = result.length() - 1;
                    selected.frequency--;
                    
                    if (selected.frequency > 0) {
                        cooldownQueue.offer(selected);
                    }
                } else {
                    // No available characters, might need to terminate early
                    break;
                }
            }
            
            return result.toString();
        }
    }
    
    /**
     * BONUS: Dynamic Task Scheduler with Priorities and Dependencies
     */
    public static class DynamicTaskScheduler {
        
        static class Task {
            String taskId;
            int priority;
            int duration;
            int cooldownPeriod;
            Set<String> dependencies;
            int lastExecuted;
            
            Task(String taskId, int priority, int duration, int cooldownPeriod) {
                this.taskId = taskId;
                this.priority = priority;
                this.duration = duration;
                this.cooldownPeriod = cooldownPeriod;
                this.dependencies = new HashSet<>();
                this.lastExecuted = -1;
            }
        }
        
        private Map<String, Task> tasks;
        private java.util.PriorityQueue<Task> readyQueue;
        private Set<String> completedTasks;
        private int currentTime;
        
        public DynamicTaskScheduler() {
            tasks = new HashMap<>();
            readyQueue = new java.util.PriorityQueue<>((a, b) -> {
                // Higher priority first
                if (a.priority != b.priority) return b.priority - a.priority;
                // Shorter duration first for tie-breaking
                return a.duration - b.duration;
            });
            completedTasks = new HashSet<>();
            currentTime = 0;
        }
        
        public void addTask(String taskId, int priority, int duration, int cooldownPeriod) {
            Task task = new Task(taskId, priority, duration, cooldownPeriod);
            tasks.put(taskId, task);
            updateReadyQueue();
        }
        
        public void addDependency(String taskId, String dependencyId) {
            Task task = tasks.get(taskId);
            if (task != null) {
                task.dependencies.add(dependencyId);
                updateReadyQueue();
            }
        }
        
        public String executeNextTask() {
            updateReadyQueue(); // Refresh available tasks
            
            if (readyQueue.isEmpty()) {
                currentTime++; // Idle time
                return null;
            }
            
            Task nextTask = readyQueue.poll();
            nextTask.lastExecuted = currentTime;
            currentTime += nextTask.duration;
            completedTasks.add(nextTask.taskId);
            
            return nextTask.taskId;
        }
        
        private void updateReadyQueue() {
            readyQueue.clear();
            
            for (Task task : tasks.values()) {
                if (isTaskReady(task)) {
                    readyQueue.offer(task);
                }
            }
        }
        
        private boolean isTaskReady(Task task) {
            // Check if task is completed
            if (completedTasks.contains(task.taskId)) {
                return false;
            }
            
            // Check dependencies
            for (String dep : task.dependencies) {
                if (!completedTasks.contains(dep)) {
                    return false;
                }
            }
            
            // Check cooldown period
            if (task.lastExecuted >= 0 && 
                currentTime - task.lastExecuted < task.cooldownPeriod) {
                return false;
            }
            
            return true;
        }
        
        public List<String> getExecutionPlan() {
            List<String> plan = new ArrayList<>();
            DynamicTaskScheduler simulator = new DynamicTaskScheduler();
            
            // Copy current state to simulator
            for (Task task : tasks.values()) {
                simulator.addTask(task.taskId, task.priority, task.duration, task.cooldownPeriod);
                for (String dep : task.dependencies) {
                    simulator.addDependency(task.taskId, dep);
                }
            }
            
            // Simulate execution
            while (!simulator.readyQueue.isEmpty() || 
                   simulator.completedTasks.size() < simulator.tasks.size()) {
                String executed = simulator.executeNextTask();
                if (executed != null) {
                    plan.add(executed);
                } else {
                    plan.add("IDLE");
                }
                
                // Prevent infinite loop
                if (plan.size() > 1000) break;
            }
            
            return plan;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🎯 PRIORITY QUEUE CONSTRUCTION AND MANIPULATION - PRACTICE IMPLEMENTATIONS");
        System.out.println("=======================================================================");
        
        // Test Task Scheduler
        System.out.println("\n⏰ TESTING TASK SCHEDULER:");
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int result1 = leastInterval(tasks1, 2);
        System.out.println("Minimum intervals for ['A','A','A','B','B','B'] with n=2: " + result1); // 8
        
        char[] tasks2 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int result2 = leastInterval(tasks2, 2);
        System.out.println("Minimum intervals for mixed tasks with n=2: " + result2); // 16
        
        // Test Detailed Task Scheduler
        List<Character> schedule = DetailedTaskScheduler.scheduleTasksDetailed(tasks1, 2);
        System.out.println("Detailed schedule: " + schedule);
        
        // Test Rearrange String k Distance Apart
        System.out.println("\n🔤 TESTING REARRANGE STRING K DISTANCE:");
        String rearranged1 = rearrangeString("aabbcc", 3);
        System.out.println("Rearrange 'aabbcc' with k=3: " + rearranged1); // "abcabc"
        
        String rearranged2 = rearrangeString("aaabc", 3);
        System.out.println("Rearrange 'aaabc' with k=3: " + rearranged2); // "" (impossible)
        
        String rearranged3 = rearrangeString("aaadbbcc", 2);
        System.out.println("Rearrange 'aaadbbcc' with k=2: " + rearranged3); // "abacabcd"
        
        // Test Reorganize String
        System.out.println("\n📝 TESTING REORGANIZE STRING:");
        String reorganized1 = reorganizeString("aab");
        System.out.println("Reorganize 'aab': " + reorganized1); // "aba"
        
        String reorganized2 = reorganizeString("aaab");
        System.out.println("Reorganize 'aaab': " + reorganized2); // "" (impossible)
        
        // Test Distant Barcodes
        System.out.println("\n🏷️ TESTING DISTANT BARCODES:");
        int[] barcodes1 = {1,1,1,2,2,2};
        int[] result3 = rearrangeBarcodes(barcodes1);
        System.out.println("Rearrange barcodes [1,1,1,2,2,2]: " + Arrays.toString(result3));
        
        int[] barcodes2 = {1,1,1,1,2,2,3};
        int[] result4 = rearrangeBarcodes(barcodes2);
        System.out.println("Rearrange barcodes [1,1,1,1,2,2,3]: " + Arrays.toString(result4));
        
        // Test Arrange Words
        System.out.println("\n📚 TESTING ARRANGE WORDS:");
        String arranged1 = arrangeWords("Leetcode makes me happy");
        System.out.println("Arrange 'Leetcode makes me happy': " + arranged1); // "Me makes happy Leetcode"
        
        String arranged2 = arrangeWords("Keep calm and code on");
        System.out.println("Arrange 'Keep calm and code on': " + arranged2); // "On and keep calm code"
        
        // Test Remove Duplicate Letters
        System.out.println("\n🔠 TESTING REMOVE DUPLICATE LETTERS:");
        String dedup1 = removeDuplicateLetters("bcabc");
        System.out.println("Remove duplicates from 'bcabc': " + dedup1); // "abc"
        
        String dedup2 = removeDuplicateLetters("cbacdcbc");
        System.out.println("Remove duplicates from 'cbacdcbc': " + dedup2); // "acdb"
        
        // Test Advanced String Constructor
        System.out.println("\n🔧 TESTING ADVANCED STRING CONSTRUCTOR:");
        Map<Character, Integer> charFreq = new HashMap<>();
        charFreq.put('a', 3);
        charFreq.put('b', 2);
        charFreq.put('c', 1);
        
        Map<Character, Integer> priorities = new HashMap<>();
        priorities.put('a', 10);
        priorities.put('b', 5);
        priorities.put('c', 1);
        
        String advanced = AdvancedStringConstructor.constructString(charFreq, 2, 6, priorities);
        System.out.println("Advanced construction: " + advanced);
        
        // Test Dynamic Task Scheduler
        System.out.println("\n⚡ TESTING DYNAMIC TASK SCHEDULER:");
        DynamicTaskScheduler scheduler = new DynamicTaskScheduler();
        scheduler.addTask("A", 10, 2, 3);
        scheduler.addTask("B", 5, 1, 2);
        scheduler.addTask("C", 8, 3, 1);
        scheduler.addDependency("C", "A"); // C depends on A
        
        List<String> plan = scheduler.getExecutionPlan();
        System.out.println("Execution plan: " + plan.subList(0, Math.min(10, plan.size())));
        
        System.out.println("\n✅ Construction and Manipulation Pattern Completed!");
        System.out.println("Key Construction Principles:");
        System.out.println("1. Use frequency-based priority for optimal arrangement");
        System.out.println("2. Implement cooling periods for distance constraints");
        System.out.println("3. Check feasibility before attempting construction");
        System.out.println("4. Use greedy strategies with priority queues");
        System.out.println("5. Handle multiple constraints with sophisticated comparators");
        System.out.println("6. Consider both local and global optimization in decisions");
        System.out.println("7. Implement robust edge case handling for constraint violations");
    }
} 