class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] score = new int[n];
        Arrays.fill(score, 1);

        for(int i=1;i<n;i++){
            if(ratings[i] > ratings[i-1]){
                score[i] = score[i-1]+1;
            }
        }
        for(int i=n-2;i>=0;i--){
            if(ratings[i] > ratings[i+1]){
                score[i] = Math.max(score[i], score[i+1] + 1);

            }
        }
        int total = 0;
        for(int i=0;i<n;i++){
            total += score[i];
        }
        return total;
    }
}