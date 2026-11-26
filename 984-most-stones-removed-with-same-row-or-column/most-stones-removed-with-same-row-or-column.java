
import java.util.*;

class Solution {

    class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
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

    public int removeStones(int[][] stones) {

        int maxRow = 0;
        int maxCol = 0;

        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);

        HashMap<Integer, Integer> stoneNodes = new HashMap<>();

        // Step 1: Connect row and column nodes
        for (int[] stone : stones) {

            int nodeRow = stone[0];
            int nodeCol = stone[1] + maxRow + 1;

            ds.unionBySize(nodeRow, nodeCol);

            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        // Step 2: Count connected components
        int cnt = 0;

        for (int node : stoneNodes.keySet()) {
            if (ds.findUPar(node) == node) {
                cnt++;
            }
        }

        // Step 3: Maximum stones removable
        return stones.length - cnt;
    }
}
