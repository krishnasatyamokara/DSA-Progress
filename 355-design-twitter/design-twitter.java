class Twitter {

    class Tweet {
        int tweetId;
        int time;
        Tweet next;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    HashMap<Integer, HashSet<Integer>> following;
    HashMap<Integer, Tweet> tweets;
    int timestamp;

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {

        Tweet newTweet = new Tweet(tweetId, timestamp++);

        newTweet.next = tweets.get(userId);

        tweets.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Tweet> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(b.time, a.time));

        List<Integer> result = new ArrayList<>();

        // Add user's own latest tweet
        if (tweets.containsKey(userId)) {
            pq.offer(tweets.get(userId));
        }

        // Add latest tweets of followed users
        if (following.containsKey(userId)) {

            for (int followee : following.get(userId)) {

                if (tweets.containsKey(followee)) {
                    pq.offer(tweets.get(followee));
                }
            }
        }

        // Get 10 most recent tweets
        while (!pq.isEmpty() && result.size() < 10) {

            Tweet current = pq.poll();

            result.add(current.tweetId);

            // Add next older tweet
            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {

        if (followerId == followeeId) {
            return;
        }

        following.putIfAbsent(followerId, new HashSet<>());

        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}
