class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        
        // Edge case: endWord must exist in the dictionary
        if (!dict.contains(endWord)) {
            return res;
        }
        
        dict.add(beginWord);
        Map<String, List<String>> parents = new HashMap<>(); // Tracks valid previous words for each node
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(beginWord, endWord, dict, parents, distance);
        
        // Only run DFS if endWord was actually reached
        if (distance.containsKey(endWord)) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, beginWord, parents, path, res);
        }
        
        return res;
    }

    // BFS: Build shortest-path parent graph from beginWord -> endWord
    private void bfs(String beginWord, String endWord, Set<String> dict,
                     Map<String, List<String>> parents, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean foundEnd = false;

        while (!queue.isEmpty() && !foundEnd) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                int curDist = distance.get(cur);

                for (String neighbor : getNeighbors(cur, dict)) {
                    // First time visiting this neighbor
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDist + 1);
                        parents.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(cur);
                        if (neighbor.equals(endWord)) {
                            foundEnd = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    } 
                    // Already visited at the exact same shortest distance
                    else if (distance.get(neighbor) == curDist + 1) {
                        parents.get(neighbor).add(cur);
                    }
                }
            }
        }
    }

    // Generate all valid 1-letter transformations in dict
    private List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[i] = c;
                String nextWord = new String(chars);
                if (dict.contains(nextWord)) {
                    neighbors.add(nextWord);
                }
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    // DFS: Backtrack from endWord -> beginWord so every path is guaranteed to succeed
    private void dfs(String cur, String beginWord, Map<String, List<String>> parents,
                     List<String> path, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            res.add(validPath);
            return;
        }

        if (!parents.containsKey(cur)) return;

        for (String parent : parents.get(cur)) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, res);
            path.remove(path.size() - 1);
        }
    }
}
