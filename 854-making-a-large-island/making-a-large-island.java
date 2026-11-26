class Solution {

    class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        DisjointSet(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        int findUPar(int node) {
            if (node == parent.get(node)) {
                return node;
            }

            int ulp = findUPar(parent.get(node));
            parent.set(node, ulp);
            return ulp;
        }

        void unionBySize(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (size.get(ulp_u) < size.get(ulp_v)) {
                parent.set(ulp_u, ulp_v);
                size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
            } else {
                parent.set(ulp_v, ulp_u);
                size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
            }
        }
    }

    boolean isValid(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    public int largestIsland(int[][] grid) {

        int n = grid.length;

        DisjointSet ds = new DisjointSet(n * n);

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // Step 1: Connect adjacent 1s
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 0) continue;

                for (int ind = 0; ind < 4; ind++) {

                    int newr = row + dr[ind];
                    int newc = col + dc[ind];

                    if (isValid(newr, newc, n)
                            && grid[newr][newc] == 1) {

                        int nodeNo = row * n + col;
                        int adjNodeNo = newr * n + newc;

                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
        }

        // Step 2: Try changing every 0 into 1
        int mx = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) continue;

                HashSet<Integer> components = new HashSet<>();

                for (int ind = 0; ind < 4; ind++) {

                    int newr = row + dr[ind];
                    int newc = col + dc[ind];

                    if (isValid(newr, newc, n)
                            && grid[newr][newc] == 1) {

                        components.add(
                            ds.findUPar(newr * n + newc)
                        );
                    }
                }

                int sizeTotal = 0;

                for (Integer parent : components) {
                    sizeTotal += ds.size.get(parent);
                }

                mx = Math.max(mx, sizeTotal + 1);
            }
        }

        // Step 3: Handle the case where all cells are already 1
        for (int cellNo = 0; cellNo < n * n; cellNo++) {

            mx = Math.max(
                mx,
                ds.size.get(ds.findUPar(cellNo))
            );
        }

        return mx;
    }
}
