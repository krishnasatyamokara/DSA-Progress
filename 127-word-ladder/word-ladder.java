
import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordDict = new HashSet<>(wordList);

        if (!wordDict.contains(endWord)) {
            return 0;
        }

        Set<String> reached = new HashSet<>();
        reached.add(beginWord);

        int distance = 1;

        while (!reached.contains(endWord)) {

            Set<String> toAdd = new HashSet<>();

            for (String each : reached) {

                char[] chars = each.toCharArray();

                for (int i = 0; i < chars.length; i++) {

                    char original = chars[i];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        chars[i] = ch;

                        String word = new String(chars);

                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }

                    chars[i] = original;
                }
            }

            if (toAdd.isEmpty()) {
                return 0;
            }

            distance++;
            reached = toAdd;
        }

        return distance;
    }
}
