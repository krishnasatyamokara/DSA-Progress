class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int n = ratings.length;
        int totalCandies = 1; // Give the first child 1 candy
        
        int up = 0;
        int down = 0;
        int peak = 0;
        
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // Climbing Up
                up++;
                peak = up;
                down = 0; // Reset down slope
                totalCandies += (up + 1);
            } 
            else if (ratings[i] < ratings[i - 1]) {
                // Falling Down
                down++;
                up = 0; // Reset up slope
                totalCandies += down;
                
                // If the downward slope is longer than the peak height,
                // we must retroactively add 1 candy to boost the peak.
                if (down > peak) {
                    totalCandies++;
                }
            } 
            else {
                // Flat Ground
                up = 0;
                down = 0;
                peak = 0;
                totalCandies += 1; // Just give the baseline candy
            }
        }
        
        return totalCandies;
    }
}
