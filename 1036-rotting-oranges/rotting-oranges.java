import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int cntFresh = 0;

        // ArrayDeque is faster than LinkedList for queues
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    cntFresh++;
                }
            }
        }

        // Edge case: No fresh oranges to rot
        if (cntFresh == 0) return 0;

        int minutes = 0;
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottedThisMinute = false;

            for (int k = 0; k < size; k++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for (int i = 0; i < 4; i++) {
                    int nRow = r + dRow[i];
                    int nCol = c + dCol[i];

                    // Check bounds and if the adjacent orange is fresh (1)
                    if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1) {
                        grid[nRow][nCol] = 2; // Mark as rotten in-place
                        q.offer(new int[]{nRow, nCol});
                        cntFresh--;
                        rottedThisMinute = true;
                    }
                }
            }

            if (rottedThisMinute) {
                minutes++;
            }
        }

        return cntFresh == 0 ? minutes : -1;
    }
}
