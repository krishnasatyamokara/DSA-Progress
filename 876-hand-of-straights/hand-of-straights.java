import java.util.*;

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num : hand) {
            pq.add(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && map.get(pq.peek()) == 0) {
                pq.poll();
            }
            if (pq.isEmpty()) break;

            int first = pq.poll();

            for (int i = 0; i < groupSize; i++) {
                int num = first + i;
                if (!map.containsKey(num) || map.get(num) == 0) return false;
                map.put(num, map.get(num) - 1);
            }
        }
        return true;
    }
}
