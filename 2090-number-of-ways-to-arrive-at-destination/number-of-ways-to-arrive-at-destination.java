
class Solution {
    public int countPaths(int n, int[][] roads) {

        int mod = (int)1e9 + 7;

        ArrayList<int[]>[] adj = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int wt = road[2];

            adj[u].add(new int[]{v, wt});
            adj[v].add(new int[]{u, wt});
        }

        long[] dist = new long[n];
        int[] ways = new int[n];

        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        pq.offer(new long[]{0, 0});

        while(!pq.isEmpty()) {

            long[] curr = pq.poll();

            long d = curr[0];
            int node = (int)curr[1];

            if(d > dist[node]) continue;

            for(int[] it : adj[node]) {

                int adjNode = it[0];
                int wt = it[1];

                if(d + wt < dist[adjNode]) {

                    dist[adjNode] = d + wt;
                    ways[adjNode] = ways[node];

                    pq.offer(new long[]{dist[adjNode], adjNode});
                }

                else if(d + wt == dist[adjNode]) {

                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1];
    }
}
