package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 PRIORITY QUEUE DESIGN PROBLEMS PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of priority queue design problems
 * for system design, data structure creation, and complex priority-based systems.
 * These algorithms demonstrate social media design, analytics systems, gaming platforms,
 * browser optimization, and real-time monitoring using heap-based architectures for
 * optimal performance and scalability in large-scale distributed systems.
 * 
 * Pattern Focus: System design, priority management, temporal ordering, scalability
 * Time Complexity: Generally O(log n) for heap operations, O(k log n) for top-k queries
 * Space Complexity: O(n) for storing elements, O(k) for bounded priority queues
 */

public class DesignProblems {
    
    /**
     * Design Twitter - Social Media Feed with Priority
     * 
     * Design a simplified version of Twitter where users can post tweets,
     * follow/unfollow another user, and is able to see the 10 most recent
     * tweets in the user's news feed.
     * 
     * Strategy: Use priority queue for feed generation with timestamp-based ordering
     * Time: O(log n) per post, O(n log n) per getNewsFeed call
     * Space: O(n) where n is total number of tweets
     * 
     * LeetCode: https://leetcode.com/problems/design-twitter/
     */
    public static class Twitter {
        
        static class Tweet {
            int tweetId;
            int userId;
            int timestamp;
            Tweet next; // Linked list for user's tweets
            
            Tweet(int tweetId, int userId, int timestamp) {
                this.tweetId = tweetId;
                this.userId = userId;
                this.timestamp = timestamp;
                this.next = null;
            }
        }
        
        private Map<Integer, Set<Integer>> userFollows; // userId -> set of followed users
        private Map<Integer, Tweet> userTweetHead; // userId -> head of tweet linked list
        private int timestamp;
        
        public Twitter() {
            userFollows = new HashMap<>();
            userTweetHead = new HashMap<>();
            timestamp = 0;
        }
        
        public void postTweet(int userId, int tweetId) {
            Tweet newTweet = new Tweet(tweetId, userId, timestamp++);
            Tweet currentHead = userTweetHead.get(userId);
            newTweet.next = currentHead;
            userTweetHead.put(userId, newTweet);
        }
        
        public List<Integer> getNewsFeed(int userId) {
            // Max-heap to get most recent tweets (by timestamp)
            java.util.PriorityQueue<Tweet> feedHeap = new java.util.PriorityQueue<>(
                (a, b) -> b.timestamp - a.timestamp
            );
            
            // Add user's own tweets
            Tweet userTweet = userTweetHead.get(userId);
            if (userTweet != null) {
                feedHeap.offer(userTweet);
            }
            
            // Add tweets from followed users
            Set<Integer> followed = userFollows.getOrDefault(userId, new HashSet<>());
            for (int followedId : followed) {
                Tweet followedTweet = userTweetHead.get(followedId);
                if (followedTweet != null) {
                    feedHeap.offer(followedTweet);
                }
            }
            
            List<Integer> newsFeed = new ArrayList<>();
            
            // Extract top 10 most recent tweets
            while (!feedHeap.isEmpty() && newsFeed.size() < 10) {
                Tweet currentTweet = feedHeap.poll();
                newsFeed.add(currentTweet.tweetId);
                
                // Add next tweet from same user if exists
                if (currentTweet.next != null) {
                    feedHeap.offer(currentTweet.next);
                }
            }
            
            return newsFeed;
        }
        
        public void follow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                userFollows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
            }
        }
        
        public void unfollow(int followerId, int followeeId) {
            Set<Integer> followed = userFollows.get(followerId);
            if (followed != null) {
                followed.remove(followeeId);
            }
        }
    }
    
    /**
     * Design Hit Counter - Time-based Analytics System
     * 
     * Design a hit counter which counts the number of hits received in the past 5 minutes.
     * Each function accepts a timestamp parameter (in seconds granularity) and you may
     * assume that calls are being made to the system in chronological order.
     * 
     * Strategy: Use priority queue for efficient cleanup of expired hits
     * Time: O(log n) per hit, O(log n + k) per getHits where k = expired hits
     * Space: O(n) where n is number of unique timestamps
     * 
     * LeetCode: https://leetcode.com/problems/design-hit-counter/
     */
    public static class HitCounter {
        
        static class Hit {
            int timestamp;
            int count;
            
            Hit(int timestamp, int count) {
                this.timestamp = timestamp;
                this.count = count;
            }
        }
        
        private java.util.PriorityQueue<Hit> hitQueue; // Min-heap by timestamp
        private Map<Integer, Integer> hitCount; // timestamp -> count
        private int totalHits;
        private final int TIME_WINDOW = 300; // 5 minutes in seconds
        
        public HitCounter() {
            hitQueue = new java.util.PriorityQueue<>((a, b) -> a.timestamp - b.timestamp);
            hitCount = new HashMap<>();
            totalHits = 0;
        }
        
        public void hit(int timestamp) {
            // Clean up expired hits first
            cleanupExpiredHits(timestamp);
            
            // Add or update hit count for this timestamp
            hitCount.put(timestamp, hitCount.getOrDefault(timestamp, 0) + 1);
            
            // Add to priority queue if it's a new timestamp
            if (hitCount.get(timestamp) == 1) {
                hitQueue.offer(new Hit(timestamp, 1));
            }
            
            totalHits++;
        }
        
        public int getHits(int timestamp) {
            cleanupExpiredHits(timestamp);
            return totalHits;
        }
        
        private void cleanupExpiredHits(int currentTimestamp) {
            while (!hitQueue.isEmpty()) {
                Hit oldestHit = hitQueue.peek();
                if (oldestHit.timestamp <= currentTimestamp - TIME_WINDOW) {
                    hitQueue.poll();
                    int expiredCount = hitCount.remove(oldestHit.timestamp);
                    totalHits -= expiredCount;
                } else {
                    break; // No more expired hits
                }
            }
        }
    }
    
    /**
     * Design Browser History - Navigation with Frequency-based Suggestions
     * 
     * You have a browser with one tab where you can visit URLs, go back in history,
     * or go forward in history. Implement the BrowserHistory class with frequency tracking.
     * 
     * Strategy: Combine history navigation with priority queue for suggestions
     * Time: O(1) for visit/back/forward, O(log n) for suggestions
     * Space: O(n) where n is number of visited URLs
     * 
     * LeetCode: https://leetcode.com/problems/design-browser-history/
     */
    public static class BrowserHistory {
        
        static class HistoryEntry {
            String url;
            int accessCount;
            int lastAccessed;
            
            HistoryEntry(String url, int lastAccessed) {
                this.url = url;
                this.accessCount = 1;
                this.lastAccessed = lastAccessed;
            }
            
            void updateAccess(int timestamp) {
                this.accessCount++;
                this.lastAccessed = timestamp;
            }
            
            double getPopularityScore(int currentTime) {
                // Combine frequency and recency
                double frequencyScore = Math.log(accessCount + 1);
                double recencyScore = 1.0 / (1 + currentTime - lastAccessed);
                return frequencyScore * recencyScore;
            }
        }
        
        private List<String> history;
        private int currentIndex;
        private Map<String, HistoryEntry> urlFrequency;
        private int timestamp;
        
        public BrowserHistory(String homepage) {
            history = new ArrayList<>();
            history.add(homepage);
            currentIndex = 0;
            urlFrequency = new HashMap<>();
            timestamp = 0;
            urlFrequency.put(homepage, new HistoryEntry(homepage, timestamp++));
        }
        
        public void visit(String url) {
            // Clear forward history
            while (history.size() > currentIndex + 1) {
                history.remove(history.size() - 1);
            }
            
            // Add new URL
            history.add(url);
            currentIndex++;
            
            // Update frequency tracking
            HistoryEntry entry = urlFrequency.get(url);
            if (entry != null) {
                entry.updateAccess(timestamp++);
            } else {
                urlFrequency.put(url, new HistoryEntry(url, timestamp++));
            }
        }
        
        public String back(int steps) {
            currentIndex = Math.max(0, currentIndex - steps);
            String currentUrl = history.get(currentIndex);
            
            // Update access frequency
            HistoryEntry entry = urlFrequency.get(currentUrl);
            if (entry != null) {
                entry.updateAccess(timestamp++);
            }
            
            return currentUrl;
        }
        
        public String forward(int steps) {
            currentIndex = Math.min(history.size() - 1, currentIndex + steps);
            String currentUrl = history.get(currentIndex);
            
            // Update access frequency
            HistoryEntry entry = urlFrequency.get(currentUrl);
            if (entry != null) {
                entry.updateAccess(timestamp++);
            }
            
            return currentUrl;
        }
        
        public List<String> getTopSuggestions(int k) {
            // Priority queue for top suggestions by popularity
            java.util.PriorityQueue<HistoryEntry> suggestionHeap = new java.util.PriorityQueue<>(
                (a, b) -> Double.compare(b.getPopularityScore(timestamp), a.getPopularityScore(timestamp))
            );
            
            suggestionHeap.addAll(urlFrequency.values());
            
            List<String> suggestions = new ArrayList<>();
            for (int i = 0; i < k && !suggestionHeap.isEmpty(); i++) {
                suggestions.add(suggestionHeap.poll().url);
            }
            
            return suggestions;
        }
    }
    
    /**
     * Design Snake Game - Real-time Gaming with Collision Detection
     * 
     * Design a Snake game that is played on a device with screen size height x width.
     * The snake is initially positioned at the top left corner (0,0) with length of 1 unit.
     * 
     * Strategy: Use priority queue for food management and efficient collision detection
     * Time: O(1) for move operation, O(log f) for food management
     * Space: O(s + f) where s = snake length, f = number of food items
     * 
     * LeetCode: https://leetcode.com/problems/design-snake-game/
     */
    public static class SnakeGame {
        
        static class Position {
            int row, col;
            
            Position(int row, int col) {
                this.row = row;
                this.col = col;
            }
            
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Position position = (Position) obj;
                return row == position.row && col == position.col;
            }
            
            @Override
            public int hashCode() {
                return Objects.hash(row, col);
            }
        }
        
        static class Food {
            Position position;
            int value;
            int timeAdded;
            
            Food(Position position, int value, int timeAdded) {
                this.position = position;
                this.value = value;
                this.timeAdded = timeAdded;
            }
        }
        
        private int width, height;
        private Deque<Position> snake;
        private Set<Position> snakeBody;
        private Queue<Food> foodQueue;
        private java.util.PriorityQueue<Food> priorityFood; // Priority by value
        private int score;
        private int gameTime;
        
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.snake = new LinkedList<>();
            this.snakeBody = new HashSet<>();
            this.foodQueue = new LinkedList<>();
            this.priorityFood = new java.util.PriorityQueue<>((a, b) -> b.value - a.value);
            this.score = 0;
            this.gameTime = 0;
            
            // Initialize snake at (0,0)
            Position head = new Position(0, 0);
            snake.offer(head);
            snakeBody.add(head);
            
            // Add food items to queues
            for (int i = 0; i < food.length; i++) {
                Food foodItem = new Food(new Position(food[i][0], food[i][1]), 1, i);
                foodQueue.offer(foodItem);
                priorityFood.offer(foodItem);
            }
        }
        
        public int move(String direction) {
            gameTime++;
            
            Position head = snake.peekFirst();
            Position newHead = getNewHead(head, direction);
            
            // Check boundary collision
            if (newHead.row < 0 || newHead.row >= height || 
                newHead.col < 0 || newHead.col >= width) {
                return -1;
            }
            
            // Check if food is eaten
            boolean ateFood = false;
            if (!foodQueue.isEmpty() && foodQueue.peek().position.equals(newHead)) {
                Food eaten = foodQueue.poll();
                priorityFood.remove(eaten);
                score += eaten.value;
                ateFood = true;
            }
            
            // Move snake
            snake.offerFirst(newHead);
            snakeBody.add(newHead);
            
            // Remove tail if no food eaten
            if (!ateFood) {
                Position tail = snake.pollLast();
                snakeBody.remove(tail);
            }
            
            // Check self-collision (excluding the new head)
            if (snakeBody.size() != snake.size()) {
                return -1; // Self collision
            }
            
            return score;
        }
        
        private Position getNewHead(Position head, String direction) {
            switch (direction) {
                case "U": return new Position(head.row - 1, head.col);
                case "D": return new Position(head.row + 1, head.col);
                case "L": return new Position(head.row, head.col - 1);
                case "R": return new Position(head.row, head.col + 1);
                default: return head;
            }
        }
        
        public List<Position> getHighValueFood(int k) {
            // Get top k highest value food items
            List<Position> highValueFood = new ArrayList<>();
            java.util.PriorityQueue<Food> tempHeap = new java.util.PriorityQueue<>(priorityFood);
            
            for (int i = 0; i < k && !tempHeap.isEmpty(); i++) {
                highValueFood.add(tempHeap.poll().position);
            }
            
            return highValueFood;
        }
    }
    
    /**
     * Design a Leaderboard - Gaming Ranking System
     * 
     * Design a Leaderboard class, which has three functions:
     * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
     * top(K): Return the score sum of the top K players.
     * reset(playerId): Reset the score of the player with the given playerId to 0.
     * 
     * Strategy: Use priority queue for efficient top-K queries with score aggregation
     * Time: O(log n) for addScore/reset, O(K log n) for top
     * Space: O(n) where n is number of players
     * 
     * LeetCode: https://leetcode.com/problems/design-a-leaderboard/
     */
    public static class Leaderboard {
        
        static class Player {
            String playerId;
            int score;
            int lastUpdated;
            
            Player(String playerId, int score, int lastUpdated) {
                this.playerId = playerId;
                this.score = score;
                this.lastUpdated = lastUpdated;
            }
        }
        
        private Map<String, Integer> playerScores;
        private TreeMap<Integer, Set<String>> scoreToPlayers; // score -> set of players
        private int timestamp;
        
        public Leaderboard() {
            playerScores = new HashMap<>();
            scoreToPlayers = new TreeMap<>(Collections.reverseOrder());
            timestamp = 0;
        }
        
        public void addScore(String playerId, int score) {
            timestamp++;
            
            int oldScore = playerScores.getOrDefault(playerId, 0);
            int newScore = oldScore + score;
            
            // Remove from old score bucket
            if (oldScore > 0) {
                Set<String> oldScorePlayers = scoreToPlayers.get(oldScore);
                oldScorePlayers.remove(playerId);
                if (oldScorePlayers.isEmpty()) {
                    scoreToPlayers.remove(oldScore);
                }
            }
            
            // Update player score
            playerScores.put(playerId, newScore);
            
            // Add to new score bucket
            scoreToPlayers.computeIfAbsent(newScore, k -> new HashSet<>()).add(playerId);
        }
        
        public int top(int k) {
            int totalScore = 0;
            int playersCount = 0;
            
            for (Map.Entry<Integer, Set<String>> entry : scoreToPlayers.entrySet()) {
                int score = entry.getKey();
                Set<String> players = entry.getValue();
                
                for (String player : players) {
                    if (playersCount >= k) break;
                    totalScore += score;
                    playersCount++;
                }
                
                if (playersCount >= k) break;
            }
            
            return totalScore;
        }
        
        public void reset(String playerId) {
            timestamp++;
            
            int currentScore = playerScores.getOrDefault(playerId, 0);
            
            if (currentScore > 0) {
                // Remove from current score bucket
                Set<String> scorePlayers = scoreToPlayers.get(currentScore);
                scorePlayers.remove(playerId);
                if (scorePlayers.isEmpty()) {
                    scoreToPlayers.remove(currentScore);
                }
                
                // Reset score
                playerScores.put(playerId, 0);
                
                // Add to score 0 bucket
                scoreToPlayers.computeIfAbsent(0, k -> new HashSet<>()).add(playerId);
            }
        }
        
        public List<String> getTopPlayers(int k) {
            // Get top K players by score (with names)
            java.util.PriorityQueue<Player> topPlayersHeap = new java.util.PriorityQueue<>(
                (a, b) -> {
                    if (a.score != b.score) return b.score - a.score; // Higher score first
                    return a.lastUpdated - b.lastUpdated; // Earlier timestamp breaks ties
                }
            );
            
            for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
                topPlayersHeap.offer(new Player(entry.getKey(), entry.getValue(), timestamp));
            }
            
            List<String> topPlayers = new ArrayList<>();
            for (int i = 0; i < k && !topPlayersHeap.isEmpty(); i++) {
                topPlayers.add(topPlayersHeap.poll().playerId);
            }
            
            return topPlayers;
        }
        
        public int getRank(String playerId) {
            int playerScore = playerScores.getOrDefault(playerId, 0);
            int rank = 1;
            
            for (Map.Entry<Integer, Set<String>> entry : scoreToPlayers.entrySet()) {
                if (entry.getKey() > playerScore) {
                    rank += entry.getValue().size();
                } else if (entry.getKey() == playerScore) {
                    // Same score, check tie-breaking rules if needed
                    break;
                } else {
                    break; // Lower scores
                }
            }
            
            return rank;
        }
    }
    
    /**
     * BONUS: Design Real-time Analytics Dashboard
     * Custom design problem for comprehensive system monitoring
     */
    public static class AnalyticsDashboard {
        
        static class Metric {
            String metricName;
            double value;
            long timestamp;
            String source;
            int priority;
            
            Metric(String metricName, double value, long timestamp, String source, int priority) {
                this.metricName = metricName;
                this.value = value;
                this.timestamp = timestamp;
                this.source = source;
                this.priority = priority;
            }
        }
        
        static class Alert {
            String message;
            int severity;
            long timestamp;
            String source;
            
            Alert(String message, int severity, long timestamp, String source) {
                this.message = message;
                this.severity = severity;
                this.timestamp = timestamp;
                this.source = source;
            }
        }
        
        private Map<String, java.util.PriorityQueue<Metric>> metricQueues;
        private java.util.PriorityQueue<Alert> alertQueue;
        private Map<String, Double> thresholds;
        private Map<String, Long> lastAlertTime;
        private final long ALERT_COOLDOWN = 60000; // 1 minute
        
        public AnalyticsDashboard() {
            metricQueues = new HashMap<>();
            alertQueue = new java.util.PriorityQueue<>((a, b) -> {
                if (a.severity != b.severity) return b.severity - a.severity;
                return Long.compare(b.timestamp, a.timestamp);
            });
            thresholds = new HashMap<>();
            lastAlertTime = new HashMap<>();
        }
        
        public void recordMetric(String metricName, double value, String source) {
            long currentTime = System.currentTimeMillis();
            int priority = calculatePriority(metricName, value);
            
            Metric metric = new Metric(metricName, value, currentTime, source, priority);
            
            metricQueues.computeIfAbsent(metricName, 
                k -> new java.util.PriorityQueue<>((a, b) -> Long.compare(b.timestamp, a.timestamp)))
                .offer(metric);
            
            // Check for threshold violations
            checkThresholdViolation(metricName, value, source, currentTime);
        }
        
        private void checkThresholdViolation(String metricName, double value, String source, long currentTime) {
            Double threshold = thresholds.get(metricName);
            if (threshold == null) return;
            
            if (value > threshold) {
                // Check cooldown period
                Long lastAlert = lastAlertTime.get(metricName);
                if (lastAlert == null || currentTime - lastAlert > ALERT_COOLDOWN) {
                    int severity = (int) Math.min(10, (value / threshold) * 5);
                    String message = String.format("Metric %s exceeded threshold: %.2f > %.2f", 
                                                   metricName, value, threshold);
                    
                    alertQueue.offer(new Alert(message, severity, currentTime, source));
                    lastAlertTime.put(metricName, currentTime);
                }
            }
        }
        
        private int calculatePriority(String metricName, double value) {
            // Priority based on metric type and value
            if (metricName.contains("error")) return 10;
            if (metricName.contains("latency")) return 8;
            if (metricName.contains("cpu") || metricName.contains("memory")) return 6;
            return 1;
        }
        
        public List<Alert> getTopAlerts(int k) {
            List<Alert> topAlerts = new ArrayList<>();
            java.util.PriorityQueue<Alert> tempQueue = new java.util.PriorityQueue<>(alertQueue);
            
            for (int i = 0; i < k && !tempQueue.isEmpty(); i++) {
                topAlerts.add(tempQueue.poll());
            }
            
            return topAlerts;
        }
        
        public double getAverageMetric(String metricName, long timeWindowMs) {
            java.util.PriorityQueue<Metric> queue = metricQueues.get(metricName);
            if (queue == null) return 0.0;
            
            long currentTime = System.currentTimeMillis();
            double sum = 0;
            int count = 0;
            
            for (Metric metric : queue) {
                if (metric.timestamp >= currentTime - timeWindowMs) {
                    sum += metric.value;
                    count++;
                }
            }
            
            return count > 0 ? sum / count : 0.0;
        }
        
        public void setThreshold(String metricName, double threshold) {
            thresholds.put(metricName, threshold);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🎯 PRIORITY QUEUE DESIGN PROBLEMS - PRACTICE IMPLEMENTATIONS");
        System.out.println("===========================================================");
        
        // Test Twitter Design
        System.out.println("\n📱 TESTING TWITTER DESIGN:");
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 10);
        twitter.follow(1, 2);
        
        List<Integer> feed = twitter.getNewsFeed(1);
        System.out.println("User 1's news feed: " + feed);
        
        twitter.unfollow(1, 2);
        feed = twitter.getNewsFeed(1);
        System.out.println("User 1's feed after unfollowing: " + feed);
        
        // Test Hit Counter
        System.out.println("\n📊 TESTING HIT COUNTER:");
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        System.out.println("Hits at timestamp 4: " + hitCounter.getHits(4)); // 3
        
        hitCounter.hit(300);
        System.out.println("Hits at timestamp 300: " + hitCounter.getHits(300)); // 4
        System.out.println("Hits at timestamp 301: " + hitCounter.getHits(301)); // 1
        
        // Test Browser History
        System.out.println("\n🌐 TESTING BROWSER HISTORY:");
        BrowserHistory browser = new BrowserHistory("leetcode.com");
        browser.visit("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        browser.visit("google.com"); // Revisit increases frequency
        
        System.out.println("Back 1 step: " + browser.back(1));
        System.out.println("Forward 1 step: " + browser.forward(1));
        System.out.println("Top suggestions: " + browser.getTopSuggestions(3));
        
        // Test Snake Game
        System.out.println("\n🐍 TESTING SNAKE GAME:");
        int[][] food = {{1,2},{0,1}};
        SnakeGame snake = new SnakeGame(3, 3, food);
        System.out.println("Move right: " + snake.move("R")); // 0
        System.out.println("Move down: " + snake.move("D")); // 0
        System.out.println("Move right: " + snake.move("R")); // 1 (ate food)
        System.out.println("Move up: " + snake.move("U")); // 1
        System.out.println("Move left: " + snake.move("L")); // 2 (ate food)
        
        // Test Leaderboard
        System.out.println("\n🎮 TESTING LEADERBOARD:");
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore("alice", 73);
        leaderboard.addScore("bob", 56);
        leaderboard.addScore("alice", 39); // alice now has 112
        leaderboard.addScore("bob", 51); // bob now has 107
        
        System.out.println("Top 1 score sum: " + leaderboard.top(1)); // 112
        System.out.println("Top 2 scores sum: " + leaderboard.top(2)); // 219
        System.out.println("Top players: " + leaderboard.getTopPlayers(2));
        System.out.println("Alice's rank: " + leaderboard.getRank("alice"));
        
        leaderboard.reset("alice");
        System.out.println("After alice reset - Top 2 sum: " + leaderboard.top(2)); // 107
        
        // Test Analytics Dashboard
        System.out.println("\n📈 TESTING ANALYTICS DASHBOARD:");
        AnalyticsDashboard dashboard = new AnalyticsDashboard();
        dashboard.setThreshold("cpu_usage", 80.0);
        dashboard.setThreshold("error_rate", 5.0);
        
        dashboard.recordMetric("cpu_usage", 45.0, "server1");
        dashboard.recordMetric("cpu_usage", 85.0, "server2"); // Threshold violation
        dashboard.recordMetric("error_rate", 7.5, "api"); // Threshold violation
        
        List<AnalyticsDashboard.Alert> alerts = dashboard.getTopAlerts(5);
        System.out.println("Number of alerts: " + alerts.size());
        for (AnalyticsDashboard.Alert alert : alerts) {
            System.out.println("Alert: " + alert.message + " (Severity: " + alert.severity + ")");
        }
        
        double avgCpu = dashboard.getAverageMetric("cpu_usage", 300000); // 5 minutes window
        System.out.println("Average CPU usage: " + avgCpu);
        
        System.out.println("\n✅ Design Problems Pattern Completed!");
        System.out.println("Key Design Principles:");
        System.out.println("1. Use priority queues for efficient priority-based operations");
        System.out.println("2. Combine multiple data structures for optimal performance");
        System.out.println("3. Implement time-based cleanup for memory efficiency");
        System.out.println("4. Design scalable systems with appropriate data structures");
        System.out.println("5. Handle real-time updates with efficient priority management");
        System.out.println("6. Implement monitoring and alerting for system health");
    }
} 