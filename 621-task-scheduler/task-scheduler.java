class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char ch : tasks){
            freq[ch-'A']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<26;i++){
            if(freq[i] > 0){
                pq.offer(freq[i]);
            }
        }

        int time = 0;
        while(!pq.isEmpty()){
            int taskCnt = 0;
            int cycle = n+1;
            List<Integer> store = new ArrayList<>();

            while(!pq.isEmpty() && cycle-->0){
                int currFreq = pq.poll();
                if(currFreq > 1){
                    store.add(currFreq-1);
                }
                taskCnt++;
            }
            store.forEach(pq::offer);
            time += (pq.isEmpty() ? taskCnt : n+1);
        }
        return time;
    }
}