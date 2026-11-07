class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {

        if (hand.length % groupSize != 0) {
            return false;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        // Step 1: Insert elements into PQ and update frequencies
        for (int num : hand) {
            pq.offer(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Form groups
        while (!pq.isEmpty()) {

            // Skip elements whose frequency is zero
            while (!pq.isEmpty() && map.get(pq.peek()) == 0) {
                pq.poll();
            }

            if (pq.isEmpty()) {
                break;
            }

            int first = pq.poll();

            ArrayList<Integer> group = new ArrayList<>();

            // Step 3: Collect consecutive elements
            for (int i = 0; i < groupSize; i++) {

                int num = first + i;

                if (map.getOrDefault(num, 0) == 0) {
                    return false;
                }

                group.add(num);

                map.put(num, map.get(num) - 1);
            }
        }

        return true;
    }
}