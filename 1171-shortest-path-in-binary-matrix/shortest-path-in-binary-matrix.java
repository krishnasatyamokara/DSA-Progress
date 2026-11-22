
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;

        // Check starting and ending cells
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }

        // 8 directions
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        boolean[][] visited = new boolean[n][n];

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0});
        visited[0][0] = true;

        int distance = 1;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int[] cell = q.poll();

                int row = cell[0];
                int col = cell[1];

                // Destination reached
                if (row == n-1 && col == n-1) {
                    return distance;
                }

                // Explore 8 directions
                for (int d = 0; d < 8; d++) {

                    int nr = row + dr[d];
                    int nc = col + dc[d];

                    // Check boundaries and visited
                    if (nr >= 0 && nr < n &&
                        nc >= 0 && nc < n &&
                        grid[nr][nc] == 0 &&
                        !visited[nr][nc]) {

                        visited[nr][nc] = true;

                        q.add(new int[]{nr, nc});
                    }
                }
            }

            distance++;
        }

        return -1;
    }
}
