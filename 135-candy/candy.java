class Solution {
    public int candy(int[] ratings) {
        // int n = ratings.length;
        // int[] score = new int[n];
        // Arrays.fill(score, 1);

        // for(int i=1;i<n;i++){
        //     if(ratings[i] > ratings[i-1]){
        //         score[i] = score[i-1]+1;
        //     }
        // }
        // for(int i=n-2;i>=0;i--){
        //     if(ratings[i] > ratings[i+1]){
        //         score[i] = Math.max(score[i], score[i+1] + 1);

        //     }
        // }
        // int total = 0;
        // for(int i=0;i<n;i++){
        //     total += score[i];
        // }
        // return total; --> my approach

        int n = ratings.length;
        int up = 0, down = 0, peak = 0;
        int totCandies = 1;
        for(int i=1;i<n;i++){
            if(ratings[i] > ratings[i-1]){
                up++;
                down = 0;
                peak = up;
                totCandies += (up+1);
            }else if(ratings[i] < ratings[i-1]){
                down++;
                up = 0;
                totCandies += down;
                if(down > peak) totCandies++;
            }else{
                up = 0;
                down = 0;
                peak = 0;
                totCandies++;
            }

        }
        return totCandies;
    }
}