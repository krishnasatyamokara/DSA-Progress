class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        // 1. Find the first and last index of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        // 2. Find all valid candidate intervals [start, end]
        List<int[]> intervals = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            for (int i = start; i <= end; i++) {
                int curr = s.charAt(i) - 'a';
                if (first[curr] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // 3. Sort by end index ascending and pick non-overlapping substrings
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }

        return result;
    }
}
