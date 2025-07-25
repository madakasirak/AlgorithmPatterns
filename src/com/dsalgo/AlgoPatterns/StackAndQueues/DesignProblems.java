package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 🏗️ DESIGN PROBLEMS (STACK/QUEUE SYSTEMS) - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of design problems that involve
 * creating custom data structures and systems using stacks, queues, and other
 * fundamental structures. These problems test system design skills, API design,
 * requirement analysis, and the ability to optimize for specific use cases while
 * maintaining clean, intuitive interfaces.
 * 
 * Pattern Focus: System design, data structure composition, API design, optimization
 * Time Complexity: Varies by operation and design choices
 * Space Complexity: Optimized for specific use case requirements
 */
public class DesignProblems {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Design Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Implement Queue Using Stacks
     * 
     * Problem: Implement FIFO queue using only LIFO stacks
     * LeetCode: https://leetcode.com/problems/implement-queue-using-stacks/
     * 
     * Approach: Two-stack design with input/output separation
     * - inputStack: receives all new elements
     * - outputStack: serves elements in FIFO order
     * - Transfer from input to output when output is empty
     * 
     * Time: push O(1), pop O(1) amortized, peek O(1) amortized, empty O(1)
     * Space: O(n) for storing elements
     */
    public static class MyQueue {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;
        
        public MyQueue() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        
        public void push(int x) {
            inputStack.push(x);
        }
        
        public int pop() {
            peek(); // Ensure outputStack has elements
            return outputStack.pop();
        }
        
        public int peek() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }
        
        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }
    
    /**
     * 🟢 EASY: Implement Stack Using Queues
     * 
     * Problem: Implement LIFO stack using only FIFO queues
     * LeetCode: https://leetcode.com/problems/implement-stack-using-queues/
     * 
     * Approach: Single queue with rotation
     * - Push: Add element and rotate queue to make it last
     * - Pop: Simply remove front element
     * - Maintain LIFO order through rotation
     * 
     * Time: push O(n), pop O(1), top O(1), empty O(1)
     * Space: O(n) for storing elements
     */
    public static class MyStack {
        private Queue<Integer> queue;
        
        public MyStack() {
            queue = new LinkedList<>();
        }
        
        public void push(int x) {
            int size = queue.size();
            queue.offer(x);
            // Rotate queue to make new element accessible from front
            for (int i = 0; i < size; i++) {
                queue.offer(queue.poll());
            }
        }
        
        public int pop() {
            return queue.poll();
        }
        
        public int top() {
            return queue.peek();
        }
        
        public boolean empty() {
            return queue.isEmpty();
        }
    }
    
    /**
     * 🟢 EASY: Design Circular Queue
     * 
     * Problem: Design circular queue with fixed size
     * LeetCode: https://leetcode.com/problems/design-circular-queue/
     * 
     * Approach: Array-based implementation with head/tail pointers
     * - Use modular arithmetic for wraparound
     * - Track size to distinguish empty vs full
     * - Efficient O(1) operations
     * 
     * Time: All operations O(1)
     * Space: O(k) for fixed size buffer
     */
    public static class MyCircularQueue {
        private int[] buffer;
        private int head;
        private int tail;
        private int size;
        private int capacity;
        
        public MyCircularQueue(int k) {
            buffer = new int[k];
            capacity = k;
            head = 0;
            tail = 0;
            size = 0;
        }
        
        public boolean enQueue(int value) {
            if (size == capacity) {
                return false;
            }
            buffer[tail] = value;
            tail = (tail + 1) % capacity;
            size++;
            return true;
        }
        
        public boolean deQueue() {
            if (size == 0) {
                return false;
            }
            head = (head + 1) % capacity;
            size--;
            return true;
        }
        
        public int Front() {
            return size == 0 ? -1 : buffer[head];
        }
        
        public int Rear() {
            return size == 0 ? -1 : buffer[(tail - 1 + capacity) % capacity];
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isFull() {
            return size == capacity;
        }
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Design Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Design Browser History
     * 
     * Problem: Design browser with back/forward navigation
     * LeetCode: https://leetcode.com/problems/design-browser-history/
     * 
     * Approach: Two stacks for history and future
     * - history: stores previous pages
     * - future: stores forward pages (cleared on new visit)
     * - current: tracks current page
     * 
     * Time: All operations O(1) per step
     * Space: O(n) for storing history
     */
    public static class BrowserHistory {
        private Stack<String> history;
        private Stack<String> future;
        private String current;
        
        public BrowserHistory(String homepage) {
            history = new Stack<>();
            future = new Stack<>();
            current = homepage;
        }
        
        public void visit(String url) {
            history.push(current);
            current = url;
            future.clear(); // Clear future when visiting new page
        }
        
        public String back(int steps) {
            while (steps > 0 && !history.isEmpty()) {
                future.push(current);
                current = history.pop();
                steps--;
            }
            return current;
        }
        
        public String forward(int steps) {
            while (steps > 0 && !future.isEmpty()) {
                history.push(current);
                current = future.pop();
                steps--;
            }
            return current;
        }
    }
    
    /**
     * 🟡 MEDIUM: Design Hit Counter
     * 
     * Problem: Count hits in the last 300 seconds
     * LeetCode: https://leetcode.com/problems/design-hit-counter/
     * 
     * Approach: Queue-based sliding window
     * - Store timestamps in queue
     * - Remove old timestamps when counting
     * - Maintain only relevant time window
     * 
     * Time: hit O(1), getHits O(n) worst case, O(1) amortized
     * Space: O(n) for storing timestamps
     */
    public static class HitCounter {
        private Queue<Integer> timestamps;
        
        public HitCounter() {
            timestamps = new LinkedList<>();
        }
        
        public void hit(int timestamp) {
            timestamps.offer(timestamp);
        }
        
        public int getHits(int timestamp) {
            // Remove timestamps older than 300 seconds
            while (!timestamps.isEmpty() && 
                   timestamp - timestamps.peek() >= 300) {
                timestamps.poll();
            }
            return timestamps.size();
        }
    }
    
    /**
     * 🟡 MEDIUM: Design Twitter
     * 
     * Problem: Design simplified Twitter with timeline functionality
     * LeetCode: https://leetcode.com/problems/design-twitter/
     * 
     * Approach: Maps + Priority Queue for timeline generation
     * - following: Map of user to set of followees
     * - tweets: Map of user to list of tweets with timestamps
     * - Use priority queue to merge recent tweets
     * 
     * Time: postTweet O(1), getNewsFeed O(n log n), follow/unfollow O(1)
     * Space: O(users + tweets + relationships)
     */
    public static class Twitter {
        private Map<Integer, Set<Integer>> following;
        private Map<Integer, List<int[]>> tweets; // [tweetId, timestamp]
        private int timestamp;
        
        public Twitter() {
            following = new HashMap<>();
            tweets = new HashMap<>();
            timestamp = 0;
        }
        
        public void postTweet(int userId, int tweetId) {
            tweets.computeIfAbsent(userId, k -> new ArrayList<>())
                  .add(new int[]{tweetId, timestamp++});
        }
        
        public List<Integer> getNewsFeed(int userId) {
            // Priority queue to get most recent tweets
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]); // [userId, tweetIdx, timestamp]
            
            // Add user's own tweets
            if (tweets.containsKey(userId)) {
                List<int[]> userTweets = tweets.get(userId);
                if (!userTweets.isEmpty()) {
                    int lastIdx = userTweets.size() - 1;
                    int[] lastTweet = userTweets.get(lastIdx);
                    pq.offer(new int[]{userId, lastIdx, lastTweet[1]});
                }
            }
            
            // Add followed users' tweets
            if (following.containsKey(userId)) {
                for (int followeeId : following.get(userId)) {
                    if (tweets.containsKey(followeeId)) {
                        List<int[]> followeeTweets = tweets.get(followeeId);
                        if (!followeeTweets.isEmpty()) {
                            int lastIdx = followeeTweets.size() - 1;
                            int[] lastTweet = followeeTweets.get(lastIdx);
                            pq.offer(new int[]{followeeId, lastIdx, lastTweet[1]});
                        }
                    }
                }
            }
            
            List<Integer> result = new ArrayList<>();
            while (!pq.isEmpty() && result.size() < 10) {
                int[] curr = pq.poll();
                int currUserId = curr[0];
                int tweetIdx = curr[1];
                
                result.add(tweets.get(currUserId).get(tweetIdx)[0]);
                
                // Add next tweet from same user if exists
                if (tweetIdx > 0) {
                    int[] prevTweet = tweets.get(currUserId).get(tweetIdx - 1);
                    pq.offer(new int[]{currUserId, tweetIdx - 1, prevTweet[1]});
                }
            }
            
            return result;
        }
        
        public void follow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                following.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
            }
        }
        
        public void unfollow(int followerId, int followeeId) {
            if (following.containsKey(followerId)) {
                following.get(followerId).remove(followeeId);
            }
        }
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Design Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Design Snake Game
     * 
     * Problem: Design snake game with movement and food consumption
     * LeetCode: https://leetcode.com/problems/design-snake-game/
     * 
     * Approach: Deque for snake body + Set for fast collision detection
     * - body: Deque to efficiently add head and remove tail
     * - bodySet: Set for O(1) collision detection
     * - foodIndex: Track current food position
     * 
     * Time: move O(1)
     * Space: O(snake_length + food_count)
     */
    public static class SnakeGame {
        private Deque<int[]> body;
        private Set<String> bodySet;
        private int[][] food;
        private int foodIndex;
        private int width;
        private int height;
        private int score;
        
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            this.foodIndex = 0;
            this.score = 0;
            
            body = new LinkedList<>();
            bodySet = new HashSet<>();
            
            // Initialize snake at (0,0)
            body.offer(new int[]{0, 0});
            bodySet.add("0,0");
        }
        
        public int move(String direction) {
            int[] head = body.peekLast();
            int newRow = head[0];
            int newCol = head[1];
            
            // Calculate new head position
            switch (direction) {
                case "U": newRow--; break;
                case "D": newRow++; break;
                case "L": newCol--; break;
                case "R": newCol++; break;
            }
            
            // Check wall collision
            if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width) {
                return -1;
            }
            
            // Check if eating food
            boolean ateFood = false;
            if (foodIndex < food.length && 
                newRow == food[foodIndex][0] && newCol == food[foodIndex][1]) {
                ateFood = true;
                foodIndex++;
                score++;
            }
            
            // Remove tail if not eating food
            if (!ateFood) {
                int[] tail = body.pollFirst();
                bodySet.remove(tail[0] + "," + tail[1]);
            }
            
            // Check self collision (after removing tail)
            String newHeadKey = newRow + "," + newCol;
            if (bodySet.contains(newHeadKey)) {
                return -1;
            }
            
            // Add new head
            body.offerLast(new int[]{newRow, newCol});
            bodySet.add(newHeadKey);
            
            return score;
        }
    }
    
    /**
     * 🔴 HARD: LRU Cache (Bonus)
     * 
     * Problem: Design LRU cache with O(1) operations
     * 
     * Approach: HashMap + Doubly Linked List
     * - map: Key to node mapping for O(1) access
     * - Doubly linked list: Maintain access order
     * - Move to head on access, remove from tail when full
     * 
     * Time: get O(1), put O(1)
     * Space: O(capacity)
     */
    public static class LRUCache {
        private class Node {
            int key, value;
            Node prev, next;
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        
        private Map<Integer, Node> map;
        private Node head, tail;
        private int capacity;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            
            // Create dummy head and tail
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                moveToHead(node);
                return node.value;
            }
            return -1;
        }
        
        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                if (map.size() >= capacity) {
                    removeTail();
                }
                Node newNode = new Node(key, value);
                addToHead(newNode);
                map.put(key, newNode);
            }
        }
        
        private void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
        
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }
        
        private void removeTail() {
            Node last = tail.prev;
            removeNode(last);
            map.remove(last.key);
        }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🏗️ DESIGN PROBLEMS (STACK/QUEUE SYSTEMS) - PRACTICE PROBLEMS");
        System.out.println("=============================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY DESIGN PROBLEMS:");
        
        // Queue Using Stacks
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("Queue using stacks - peek: " + queue.peek()); // 1
        System.out.println("Queue using stacks - pop: " + queue.pop());   // 1
        System.out.println("Queue using stacks - empty: " + queue.empty()); // false
        
        // Stack Using Queues
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println("Stack using queues - top: " + stack.top());   // 2
        System.out.println("Stack using queues - pop: " + stack.pop());   // 2
        System.out.println("Stack using queues - empty: " + stack.empty()); // false
        
        // Circular Queue
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        System.out.println("Circular queue - enQueue(1): " + circularQueue.enQueue(1)); // true
        System.out.println("Circular queue - enQueue(2): " + circularQueue.enQueue(2)); // true
        System.out.println("Circular queue - enQueue(3): " + circularQueue.enQueue(3)); // true
        System.out.println("Circular queue - enQueue(4): " + circularQueue.enQueue(4)); // false
        System.out.println("Circular queue - front: " + circularQueue.Front()); // 1
        System.out.println("Circular queue - rear: " + circularQueue.Rear());   // 3
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM DESIGN PROBLEMS:");
        
        // Browser History
        BrowserHistory browser = new BrowserHistory("leetcode.com");
        browser.visit("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        System.out.println("Browser back(1): " + browser.back(1));    // facebook.com
        System.out.println("Browser back(1): " + browser.back(1));    // google.com
        System.out.println("Browser forward(1): " + browser.forward(1)); // facebook.com
        browser.visit("linkedin.com");
        System.out.println("Browser forward(2): " + browser.forward(2)); // linkedin.com
        System.out.println("Browser back(2): " + browser.back(2));    // google.com
        System.out.println("Browser back(7): " + browser.back(7));    // leetcode.com
        
        // Hit Counter
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        System.out.println("Hit counter at time 4: " + hitCounter.getHits(4));   // 3
        hitCounter.hit(300);
        System.out.println("Hit counter at time 300: " + hitCounter.getHits(300)); // 4
        System.out.println("Hit counter at time 301: " + hitCounter.getHits(301)); // 3
        
        // Twitter
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        System.out.println("User 1 news feed: " + twitter.getNewsFeed(1)); // [5]
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println("User 1 news feed: " + twitter.getNewsFeed(1)); // [6, 5]
        twitter.unfollow(1, 2);
        System.out.println("User 1 news feed: " + twitter.getNewsFeed(1)); // [5]
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD DESIGN PROBLEMS:");
        
        // Snake Game
        int[][] food = {{1,2},{0,1}};
        SnakeGame snakeGame = new SnakeGame(3, 2, food);
        System.out.println("Snake move R: " + snakeGame.move("R")); // 0
        System.out.println("Snake move D: " + snakeGame.move("D")); // 0
        System.out.println("Snake move R: " + snakeGame.move("R")); // 1 (ate food)
        System.out.println("Snake move U: " + snakeGame.move("U")); // 1
        System.out.println("Snake move L: " + snakeGame.move("L")); // 2 (ate food)
        System.out.println("Snake move U: " + snakeGame.move("U")); // -1 (collision)
        
        // LRU Cache
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println("LRU get(1): " + lruCache.get(1));    // 1
        lruCache.put(3, 3); // evicts key 2
        System.out.println("LRU get(2): " + lruCache.get(2));    // -1 (not found)
        lruCache.put(4, 4); // evicts key 1
        System.out.println("LRU get(1): " + lruCache.get(1));    // -1 (not found)
        System.out.println("LRU get(3): " + lruCache.get(3));    // 3
        System.out.println("LRU get(4): " + lruCache.get(4));    // 4
        
        System.out.println("\n✅ Key Design Principles:");
        System.out.println("1. Analyze requirements and operation patterns carefully");
        System.out.println("2. Choose appropriate underlying data structures");
        System.out.println("3. Design clean, intuitive APIs");
        System.out.println("4. Optimize for common use cases");
        System.out.println("5. Handle edge cases and error conditions");
        System.out.println("6. Consider memory and performance trade-offs");
        System.out.println("7. Test with realistic usage scenarios");
        System.out.println("8. Maintain consistent behavior across operations");
    }
} 