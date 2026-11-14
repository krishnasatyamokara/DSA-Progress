class Solution {

    private void dfs(int row, int col, boolean[][] visited,
                     int[][] image, int initialColor, int color) {

        visited[row][col] = true;
        image[row][col] = color;

        int m = image.length;
        int n = image[0].length;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {

            int newRow = row + dRow[k];
            int newCol = col + dCol[k];

            if (newRow >= 0 && newRow < m &&
                newCol >= 0 && newCol < n &&
                !visited[newRow][newCol] &&
                image[newRow][newCol] == initialColor) {

                dfs(newRow, newCol, visited, image,
                    initialColor, color);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int initialColor = image[sr][sc];

        if (initialColor == color) return image;

        int m = image.length;
        int n = image[0].length;

        boolean[][] visited = new boolean[m][n];

        dfs(sr, sc, visited, image, initialColor, color);

        return image;
    }
}