package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 PRIORITY QUEUE DESIGN PROBLEMS PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS PRIORITY QUEUE DESIGN PROBLEMS PATTERN?
 * ============================================================================
 * 
 * Priority Queue Design Problems Pattern focuses on designing custom data structures
 * and systems using priority queues as the core component. This pattern is essential
 * for system design interviews, real-world applications, and complex data structure
 * implementations where priority-based processing, efficient ordering, and optimal
 * resource management are crucial for system performance and functionality.
 * 
 * The pattern leverages heap-based priority queues for designing social media feeds,
 * hit counters, browser history systems, gaming leaderboards, and other sophisticated
 * data structures that require priority-based operations, temporal ordering, and
 * efficient top-k queries with optimal time and space complexity characteristics.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. SYSTEM DESIGN: Architect complete systems using priority queues as foundation
 * 2. PRIORITY MANAGEMENT: Efficiently handle multiple priority criteria simultaneously
 * 3. TEMPORAL ORDERING: Manage time-based events and expiration mechanisms
 * 4. SCALABILITY: Design for large-scale systems with performance constraints
 * 
 * 🎯 DESIGN PROBLEMS METAPHOR:
 * Think of Priority Queue Design as "smart system architecture":
 * - Social feed: prioritize posts by relevance, time, and user engagement
 * - Traffic controller: manage vehicle flow by priority lanes and timing
 * - Hospital triage: patient processing by severity and arrival time
 * - Stock exchange: order matching by price and timestamp priority
 * 
 * ============================================================================
 * 🎯 WHEN TO USE PRIORITY QUEUE DESIGN PROBLEMS PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Designing social media systems with feed prioritization
 * - Building real-time analytics and monitoring systems
 * - Creating gaming systems with leaderboards and rankings
 * - Implementing browser history with frequency-based suggestions
 * - Designing task scheduling and resource allocation systems
 * - Building recommendation engines with relevance scoring
 * - Creating event-driven systems with temporal ordering
 * - Implementing rate limiting and throttling mechanisms
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Design a system that..."
 * - "Implement a data structure for..."
 * - "Create a ranking/leaderboard system"
 * - "Build a recommendation engine"
 * - "Design a feed aggregation system"
 * - "Implement time-based analytics"
 * - "Create a priority-based scheduler"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple CRUD operations without prioritization
 * - Static data without dynamic updates
 * - Single-user systems without ranking needs
 * - Systems without time-sensitive operations
 * 
 * ============================================================================
 * 🔄 DESIGN PROBLEMS PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ SOCIAL MEDIA DESIGN
 * - Tweet timeline generation with relevance scoring
 * - User interaction prioritization
 * - Trending topics identification
 * - Content recommendation systems
 * 
 * 2️⃣ ANALYTICS AND MONITORING
 * - Hit counter with time-based aggregation
 * - Real-time metrics tracking
 * - Performance monitoring systems
 * - Alert prioritization mechanisms
 * 
 * 3️⃣ GAMING AND LEADERBOARDS
 * - Dynamic ranking systems
 * - Score-based player matching
 * - Achievement tracking
 * - Tournament bracket management
 * 
 * 4️⃣ BROWSER AND NAVIGATION
 * - History management with frequency weighting
 * - Bookmark prioritization
 * - Search suggestion ranking
 * - Cache management with LRU/LFU hybrid
 * 
 * 5️⃣ RESOURCE MANAGEMENT
 * - Task scheduling with deadlines
 * - Load balancing with priority
 * - Resource allocation optimization
 * - Queue management systems
 * 
 * 6️⃣ REAL-TIME SYSTEMS
 * - Event processing with priorities
 * - Stream aggregation and filtering
 * - Time-window based analytics
 * - Dynamic threshold management
 * 
 * ============================================================================
 * 🧠 CORE DESIGN ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 SOCIAL MEDIA FEED ALGORITHM:
 * ```
 * Feed Generation with Priority Queue:
 *   1. Collect posts from followed users
 *   2. Score posts based on: recency, engagement, user relationship
 *   3. Use max-heap to maintain top posts by composite score
 *   4. Implement pagination with score-based cursors
 *   5. Update scores dynamically based on user interactions
 * 
 * Time: O(log n) per post insertion, O(k log n) for top k posts
 * ```
 * 
 * 🔹 TIME-BASED HIT COUNTER ALGORITHM:
 * ```
 * Sliding Window Hit Counter:
 *   1. Use priority queue to store (timestamp, count) pairs
 *   2. Remove expired entries beyond time window
 *   3. Aggregate counts within valid time range
 *   4. Optimize with bucketing for large-scale systems
 * 
 * Time: O(log n) per hit, O(log n) for count query
 * ```
 * 
 * 🔹 DYNAMIC LEADERBOARD ALGORITHM:
 * ```
 * Real-time Ranking System:
 *   1. Use max-heap for top players by score
 *   2. Maintain hash map for player ID to heap node mapping
 *   3. Support score updates with heap restructuring
 *   4. Implement rank queries with efficient indexing
 * 
 * Time: O(log n) per score update, O(1) for top-k query
 * ```
 * 
 * 🔹 BROWSER HISTORY WITH PRIORITY ALGORITHM:
 * ```
 * Frequency-based History Management:
 *   1. Combine recency and frequency for URL scoring
 *   2. Use priority queue for suggestion ranking
 *   3. Implement LRU eviction for memory management
 *   4. Support prefix search with trie + priority queue
 * 
 * Time: O(log n) per access, O(k log n) for top k suggestions
 * ```
 * 
 * ============================================================================
 * 📋 DESIGN FRAMEWORK AND METHODOLOGY
 * ============================================================================
 * 
 * STEP 1: REQUIREMENT ANALYSIS
 * - Identify priority criteria and ranking factors
 * - Define scalability requirements and constraints
 * - Analyze read/write patterns and frequency
 * 
 * STEP 2: DATA MODEL DESIGN
 * - Choose appropriate priority queue configurations
 * - Design data structures for auxiliary operations
 * - Plan for data persistence and recovery
 * 
 * STEP 3: API DESIGN
 * - Define core operations with time complexity goals
 * - Plan for batch operations and bulk updates
 * - Design pagination and streaming interfaces
 * 
 * STEP 4: SCALABILITY PLANNING
 * - Implement sharding strategies for large datasets
 * - Design caching layers for frequently accessed data
 * - Plan for horizontal scaling and load distribution
 * 
 * STEP 5: OPTIMIZATION AND MONITORING
 * - Implement performance monitoring and alerting
 * - Design A/B testing frameworks for algorithm tuning
 * - Plan for graceful degradation under high load
 * 
 * ============================================================================
 * 🎨 DESIGN PROBLEM TEMPLATES AND ARCHITECTURES
 * ============================================================================
 */

public class DesignProblemsReadingGuide {
    
    /**
     * 🏆 SOCIAL MEDIA DESIGN TEMPLATES
     */
    public static class SocialMediaDesign {
        
        /**
         * Twitter Feed Design with Priority Scoring
         * Strategy: Combine multiple factors for tweet relevance scoring
         */
        public static class TwitterDesign {
            
            static class Tweet {
                int tweetId;
                int userId;
                int timestamp;
                String content;
                int engagementScore;
                
                Tweet(int tweetId, int userId, int timestamp, String content) {
                    this.tweetId = tweetId;
                    this.userId = userId;
                    this.timestamp = timestamp;
                    this.content = content;
                    this.engagementScore = 0;
                }
                
                // Calculate composite priority score
                double getPriorityScore(int currentTime, Set<Integer> followedUsers) {
                    double recencyScore = 1.0 / (1 + currentTime - timestamp);
                    double engagementBonus = engagementScore * 0.1;
                    double relationshipBonus = followedUsers.contains(userId) ? 1.0 : 0.5;
                    
                    return recencyScore + engagementBonus + relationshipBonus;
                }
            }
            
            private Map<Integer, Set<Integer>> userFollows; // userId -> set of followed users
            private Map<Integer, List<Tweet>> userTweets; // userId -> user's tweets
            private int timestamp;
            
            public TwitterDesign() {
                userFollows = new HashMap<>();
                userTweets = new HashMap<>();
                timestamp = 0;
            }
            
            public void postTweet(int userId, String content) {
                userTweets.computeIfAbsent(userId, k -> new ArrayList<>())
                         .add(new Tweet(timestamp++, userId, timestamp, content));
            }
            
            public List<Integer> getNewsFeed(int userId) {
                // Max-heap for top tweets by priority score
                java.util.PriorityQueue<Tweet> feedHeap = new java.util.PriorityQueue<>(
                    (a, b) -> Double.compare(
                        b.getPriorityScore(timestamp, userFollows.getOrDefault(userId, new HashSet<>())),
                        a.getPriorityScore(timestamp, userFollows.getOrDefault(userId, new HashSet<>()))
                    )
                );
                
                // Add user's own tweets
                if (userTweets.containsKey(userId)) {
                    feedHeap.addAll(userTweets.get(userId));
                }
                
                // Add tweets from followed users
                Set<Integer> followed = userFollows.getOrDefault(userId, new HashSet<>());
                for (int followedId : followed) {
                    if (userTweets.containsKey(followedId)) {
                        feedHeap.addAll(userTweets.get(followedId));
                    }
                }
                
                // Extract top 10 tweets
                List<Integer> result = new ArrayList<>();
                for (int i = 0; i < Math.min(10, feedHeap.size()); i++) {
                    result.add(feedHeap.poll().tweetId);
                }
                
                return result;
            }
            
            public void follow(int followerId, int followeeId) {
                userFollows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
            }
            
            public void unfollow(int followerId, int followeeId) {
                Set<Integer> followed = userFollows.get(followerId);
                if (followed != null) {
                    followed.remove(followeeId);
                }
            }
        }
    }
    
    /**
     * 📊 ANALYTICS AND MONITORING TEMPLATES
     */
    public static class AnalyticsDesign {
        
        /**
         * Hit Counter with Time-Based Aggregation
         * Strategy: Sliding window approach with priority queue for cleanup
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
            private Map<Integer, Integer> hitBuckets; // timestamp -> hit count
            private int totalHits;
            private final int TIME_WINDOW = 300; // 5 minutes
            
            public HitCounter() {
                hitQueue = new java.util.PriorityQueue<>((a, b) -> a.timestamp - b.timestamp);
                hitBuckets = new HashMap<>();
                totalHits = 0;
            }
            
            public void hit(int timestamp) {
                // Clean up expired hits
                cleanupExpiredHits(timestamp);
                
                // Add new hit
                hitBuckets.put(timestamp, hitBuckets.getOrDefault(timestamp, 0) + 1);
                if (hitBuckets.get(timestamp) == 1) {
                    hitQueue.offer(new Hit(timestamp, 1));
                }
                totalHits++;
            }
            
            public int getHits(int timestamp) {
                cleanupExpiredHits(timestamp);
                return totalHits;
            }
            
            private void cleanupExpiredHits(int currentTimestamp) {
                while (!hitQueue.isEmpty() && 
                       hitQueue.peek().timestamp <= currentTimestamp - TIME_WINDOW) {
                    Hit expiredHit = hitQueue.poll();
                    int expiredCount = hitBuckets.remove(expiredHit.timestamp);
                    totalHits -= expiredCount;
                }
            }
        }
        
        /**
         * Real-time Metrics Aggregator
         * Strategy: Time-bucketed metrics with priority-based alerting
         */
        public static class MetricsAggregator {
            
            static class Metric {
                String metricName;
                double value;
                int timestamp;
                int priority;
                
                Metric(String metricName, double value, int timestamp, int priority) {
                    this.metricName = metricName;
                    this.value = value;
                    this.timestamp = timestamp;
                    this.priority = priority;
                }
            }
            
            private Map<String, java.util.PriorityQueue<Metric>> metricQueues;
            private java.util.PriorityQueue<Metric> alertQueue; // Max-heap by priority
            private Map<String, Double> thresholds;
            
            public MetricsAggregator() {
                metricQueues = new HashMap<>();
                alertQueue = new java.util.PriorityQueue<>((a, b) -> b.priority - a.priority);
                thresholds = new HashMap<>();
            }
            
            public void recordMetric(String metricName, double value, int timestamp) {
                Metric metric = new Metric(metricName, value, timestamp, 0);
                
                metricQueues.computeIfAbsent(metricName, 
                    k -> new java.util.PriorityQueue<>((a, b) -> a.timestamp - b.timestamp))
                    .offer(metric);
                
                // Check for threshold violations
                Double threshold = thresholds.get(metricName);
                if (threshold != null && value > threshold) {
                    metric.priority = (int) (value / threshold * 10); // Priority based on severity
                    alertQueue.offer(metric);
                }
            }
            
            public double getAverageMetric(String metricName, int timeWindow, int currentTime) {
                java.util.PriorityQueue<Metric> queue = metricQueues.get(metricName);
                if (queue == null) return 0.0;
                
                double sum = 0;
                int count = 0;
                
                // Create temporary list to avoid modifying original queue
                List<Metric> validMetrics = new ArrayList<>();
                while (!queue.isEmpty()) {
                    Metric m = queue.poll();
                    if (m.timestamp >= currentTime - timeWindow) {
                        validMetrics.add(m);
                        sum += m.value;
                        count++;
                    }
                }
                
                // Restore queue
                queue.addAll(validMetrics);
                
                return count > 0 ? sum / count : 0.0;
            }
            
            public List<Metric> getTopAlerts(int k) {
                List<Metric> alerts = new ArrayList<>();
                java.util.PriorityQueue<Metric> tempQueue = new java.util.PriorityQueue<>(alertQueue);
                
                for (int i = 0; i < k && !tempQueue.isEmpty(); i++) {
                    alerts.add(tempQueue.poll());
                }
                
                return alerts;
            }
            
            public void setThreshold(String metricName, double threshold) {
                thresholds.put(metricName, threshold);
            }
        }
    }
    
    /**
     * 🎮 GAMING AND LEADERBOARD TEMPLATES
     */
    public static class GamingDesign {
        
        /**
         * Dynamic Leaderboard with Real-time Updates
         * Strategy: Dual data structure approach for efficient ranking
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
            
            private java.util.PriorityQueue<Player> leaderboardHeap; // Max-heap by score
            private Map<String, Player> playerMap; // playerId -> Player object
            private TreeMap<Integer, Set<String>> scoreToPlayers; // score -> set of players
            private int timestamp;
            
            public Leaderboard() {
                leaderboardHeap = new java.util.PriorityQueue<>((a, b) -> {
                    if (a.score != b.score) return b.score - a.score; // Higher score first
                    return a.lastUpdated - b.lastUpdated; // Earlier timestamp breaks ties
                });
                playerMap = new HashMap<>();
                scoreToPlayers = new TreeMap<>(Collections.reverseOrder());
                timestamp = 0;
            }
            
            public void addScore(String playerId, int score) {
                timestamp++;
                
                Player existingPlayer = playerMap.get(playerId);
                if (existingPlayer != null) {
                    // Remove from old score bucket
                    Set<String> oldScorePlayers = scoreToPlayers.get(existingPlayer.score);
                    oldScorePlayers.remove(playerId);
                    if (oldScorePlayers.isEmpty()) {
                        scoreToPlayers.remove(existingPlayer.score);
                    }
                    
                    // Update score
                    existingPlayer.score += score;
                    existingPlayer.lastUpdated = timestamp;
                } else {
                    existingPlayer = new Player(playerId, score, timestamp);
                    playerMap.put(playerId, existingPlayer);
                }
                
                // Add to new score bucket
                scoreToPlayers.computeIfAbsent(existingPlayer.score, k -> new HashSet<>())
                             .add(playerId);
                
                // Note: For efficiency, we rebuild heap only when querying top players
            }
            
            public List<String> top(int k) {
                // Rebuild heap from current scores
                leaderboardHeap.clear();
                leaderboardHeap.addAll(playerMap.values());
                
                List<String> topPlayers = new ArrayList<>();
                java.util.PriorityQueue<Player> tempHeap = new java.util.PriorityQueue<>(leaderboardHeap);
                
                for (int i = 0; i < k && !tempHeap.isEmpty(); i++) {
                    topPlayers.add(tempHeap.poll().playerId);
                }
                
                return topPlayers;
            }
            
            public int getRank(String playerId) {
                Player player = playerMap.get(playerId);
                if (player == null) return -1;
                
                int rank = 1;
                for (Map.Entry<Integer, Set<String>> entry : scoreToPlayers.entrySet()) {
                    if (entry.getKey() > player.score) {
                        rank += entry.getValue().size();
                    } else if (entry.getKey() == player.score) {
                        // Count players with same score but earlier timestamp
                        for (String pid : entry.getValue()) {
                            if (!pid.equals(playerId) && 
                                playerMap.get(pid).lastUpdated < player.lastUpdated) {
                                rank++;
                            }
                        }
                        break;
                    }
                }
                
                return rank;
            }
            
            public void reset(String playerId) {
                Player player = playerMap.get(playerId);
                if (player != null) {
                    // Remove from score bucket
                    Set<String> scorePlayers = scoreToPlayers.get(player.score);
                    scorePlayers.remove(playerId);
                    if (scorePlayers.isEmpty()) {
                        scoreToPlayers.remove(player.score);
                    }
                    
                    // Reset score
                    player.score = 0;
                    player.lastUpdated = ++timestamp;
                    
                    // Add to score 0 bucket
                    scoreToPlayers.computeIfAbsent(0, k -> new HashSet<>()).add(playerId);
                }
            }
        }
    }
    
    /**
     * 🌐 BROWSER AND NAVIGATION TEMPLATES
     */
    public static class BrowserDesign {
        
        /**
         * Browser History with Frequency-based Suggestions
         * Strategy: Combine LRU with frequency scoring for intelligent suggestions
         */
        public static class BrowserHistory {
            
            static class HistoryEntry {
                String url;
                int accessCount;
                int lastAccessed;
                double popularityScore;
                
                HistoryEntry(String url, int lastAccessed) {
                    this.url = url;
                    this.accessCount = 1;
                    this.lastAccessed = lastAccessed;
                    updateScore();
                }
                
                void updateAccess(int timestamp) {
                    this.accessCount++;
                    this.lastAccessed = timestamp;
                    updateScore();
                }
                
                private void updateScore() {
                    // Combine frequency and recency for popularity score
                    this.popularityScore = Math.log(accessCount + 1) * (1.0 / (1 + System.currentTimeMillis() - lastAccessed));
                }
            }
            
            private String currentUrl;
            private List<String> history;
            private int currentIndex;
            private Map<String, HistoryEntry> urlFrequency;
            private java.util.PriorityQueue<HistoryEntry> suggestionQueue;
            private int timestamp;
            
            public BrowserHistory(String homepage) {
                this.currentUrl = homepage;
                this.history = new ArrayList<>();
                history.add(homepage);
                this.currentIndex = 0;
                this.urlFrequency = new HashMap<>();
                this.suggestionQueue = new java.util.PriorityQueue<>(
                    (a, b) -> Double.compare(b.popularityScore, a.popularityScore)
                );
                this.timestamp = 0;
                
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
                currentUrl = url;
                
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
                currentUrl = history.get(currentIndex);
                
                // Update access for navigation
                HistoryEntry entry = urlFrequency.get(currentUrl);
                if (entry != null) {
                    entry.updateAccess(timestamp++);
                }
                
                return currentUrl;
            }
            
            public String forward(int steps) {
                currentIndex = Math.min(history.size() - 1, currentIndex + steps);
                currentUrl = history.get(currentIndex);
                
                // Update access for navigation
                HistoryEntry entry = urlFrequency.get(currentUrl);
                if (entry != null) {
                    entry.updateAccess(timestamp++);
                }
                
                return currentUrl;
            }
            
            public List<String> getTopSuggestions(int k) {
                // Rebuild suggestion queue with current scores
                suggestionQueue.clear();
                for (HistoryEntry entry : urlFrequency.values()) {
                    entry.updateScore(); // Refresh scores
                    suggestionQueue.offer(entry);
                }
                
                List<String> suggestions = new ArrayList<>();
                java.util.PriorityQueue<HistoryEntry> tempQueue = new java.util.PriorityQueue<>(suggestionQueue);
                
                for (int i = 0; i < k && !tempQueue.isEmpty(); i++) {
                    suggestions.add(tempQueue.poll().url);
                }
                
                return suggestions;
            }
            
            public List<String> searchSuggestions(String prefix) {
                java.util.PriorityQueue<HistoryEntry> matchQueue = new java.util.PriorityQueue<>(
                    (a, b) -> Double.compare(b.popularityScore, a.popularityScore)
                );
                
                for (HistoryEntry entry : urlFrequency.values()) {
                    if (entry.url.startsWith(prefix)) {
                        entry.updateScore();
                        matchQueue.offer(entry);
                    }
                }
                
                List<String> suggestions = new ArrayList<>();
                while (!matchQueue.isEmpty() && suggestions.size() < 5) {
                    suggestions.add(matchQueue.poll().url);
                }
                
                return suggestions;
            }
        }
    }
    
    // ============================================================================
    // 🚨 COMMON DESIGN PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Inefficient Priority Calculation
     * Recalculate priorities only when necessary, cache when possible
     */
    public static void priorityCalculationExample() {
        // Correct: Cache priority scores and update incrementally
        class CachedPriorityItem {
            int value;
            double cachedPriority;
            boolean priorityValid;
            
            double getPriority() {
                if (!priorityValid) {
                    cachedPriority = calculatePriority();
                    priorityValid = true;
                }
                return cachedPriority;
            }
            
            private double calculatePriority() {
                // Expensive priority calculation
                return Math.log(value + 1) * System.currentTimeMillis();
            }
        }
        
        // Incorrect: Recalculating priority on every comparison
    }
    
    /**
     * ❌ PITFALL 2: Memory Leaks with Time-based Data
     * Always implement cleanup mechanisms for time-sensitive data
     */
    public static void memoryLeakExample() {
        // Define TimestampedEntry locally before usage
        class TimestampedEntry {
            long timestamp;
            Object data;
            
            TimestampedEntry(Object data) {
                this.data = data;
                this.timestamp = System.currentTimeMillis();
            }
        }
        
        // Correct: Periodic cleanup of expired data
        class TimeAwareSystem {
            private java.util.PriorityQueue<TimestampedEntry> dataQueue;
            private final long EXPIRY_TIME = 3600000; // 1 hour
            
            public void cleanupExpiredData() {
                long currentTime = System.currentTimeMillis();
                while (!dataQueue.isEmpty() && 
                       currentTime - dataQueue.peek().timestamp > EXPIRY_TIME) {
                    dataQueue.poll();
                }
            }
        }
        
        // Incorrect: No cleanup mechanism leads to memory leaks
    }
    
    // ============================================================================
    // 💡 DESIGN OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Lazy Evaluation for Performance
     * Defer expensive operations until absolutely necessary
     */
    
    /**
     * 🎯 STRATEGY 2: Hybrid Data Structures
     * Combine priority queues with other structures for optimal performance
     */
    
    /**
     * 🎯 STRATEGY 3: Batch Operations
     * Group multiple updates together to amortize overhead
     */
    
    // ============================================================================
    // 🧭 DESIGN DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE DESIGN APPROACH BASED ON:
     * 
     * SCALE REQUIREMENTS:
     * ✅ Small scale (< 10K users) → Simple priority queue designs
     * ✅ Medium scale (10K-1M users) → Optimized single-machine solutions
     * ✅ Large scale (> 1M users) → Distributed priority queue systems
     * 
     * CONSISTENCY REQUIREMENTS:
     * ✅ Strong consistency → Centralized priority queue with locks
     * ✅ Eventual consistency → Distributed queues with merge strategies
     * ✅ Weak consistency → Approximate priority queues for speed
     * 
     * LATENCY REQUIREMENTS:
     * ✅ Low latency (< 1ms) → In-memory priority queues with caching
     * ✅ Medium latency (< 100ms) → Persistent queues with write-through
     * ✅ High latency acceptable → Batch processing with priority queues
     */
    
    public static void main(String[] args) {
        System.out.println("🎯 PRIORITY QUEUE DESIGN PROBLEMS PATTERN - READING GUIDE");
        System.out.println("=========================================================");
        
        // Test Social Media Design
        System.out.println("\n📱 SOCIAL MEDIA DESIGN:");
        SocialMediaDesign.TwitterDesign twitter = new SocialMediaDesign.TwitterDesign();
        twitter.postTweet(1, "Hello World!");
        twitter.postTweet(2, "Good Morning!");
        twitter.follow(1, 2);
        List<Integer> feed = twitter.getNewsFeed(1);
        System.out.println("User 1's feed: " + feed);
        
        // Test Hit Counter
        System.out.println("\n📊 HIT COUNTER:");
        AnalyticsDesign.HitCounter counter = new AnalyticsDesign.HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println("Hits at timestamp 4: " + counter.getHits(4)); // 3
        counter.hit(300);
        System.out.println("Hits at timestamp 300: " + counter.getHits(300)); // 4
        System.out.println("Hits at timestamp 301: " + counter.getHits(301)); // 1
        
        // Test Leaderboard
        System.out.println("\n🎮 LEADERBOARD:");
        GamingDesign.Leaderboard leaderboard = new GamingDesign.Leaderboard();
        leaderboard.addScore("alice", 100);
        leaderboard.addScore("bob", 90);
        leaderboard.addScore("alice", 50); // alice now has 150
        System.out.println("Top 2 players: " + leaderboard.top(2)); // [alice, bob]
        System.out.println("Alice's rank: " + leaderboard.getRank("alice")); // 1
        
        // Test Browser History
        System.out.println("\n🌐 BROWSER HISTORY:");
        BrowserDesign.BrowserHistory browser = new BrowserDesign.BrowserHistory("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        browser.visit("facebook.com"); // Revisit increases frequency
        System.out.println("Back 1 step: " + browser.back(1)); // youtube.com
        System.out.println("Forward 1 step: " + browser.forward(1)); // facebook.com
        System.out.println("Top suggestions: " + browser.getTopSuggestions(3));
        
        System.out.println("\n✅ Key Design Principles:");
        System.out.println("1. Use priority queues as foundation for complex system architectures");
        System.out.println("2. Combine multiple data structures for optimal performance");
        System.out.println("3. Implement efficient cleanup mechanisms for time-sensitive data");
        System.out.println("4. Cache expensive priority calculations when possible");
        System.out.println("5. Design for scalability with appropriate data structure choices");
        System.out.println("6. Consider consistency vs performance trade-offs in distributed systems");
        System.out.println("7. Implement monitoring and alerting for system health");
    }
} 