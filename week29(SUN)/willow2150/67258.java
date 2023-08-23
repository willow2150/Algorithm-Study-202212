import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> gemCounter = new HashMap<>();
        Set<String> gemSet = new HashSet<>();
        int[] result = new int[2];
        int minSectionLength = Integer.MAX_VALUE;
        int numOfGemTypes;

        Collections.addAll(gemSet, gems);
        numOfGemTypes = gemSet.size();

        for (int left = 0, right = 0; right < gems.length; right++) {
            gemCounter.put(gems[right], gemCounter.getOrDefault(gems[right], 0) + 1);
            while (numOfGemTypes == gemCounter.size() && left <= right) {
                if (gemCounter.get(gems[left]) == 1) {
                    int sectionLength = right - left + 1;
                    gemCounter.remove(gems[left]);
                    if (sectionLength < minSectionLength) {
                        result[0] = left + 1;
                        result[1] = right + 1;
                        minSectionLength = sectionLength;
                    }
                    left++;
                    break;
                }
                gemCounter.put(gems[left], gemCounter.get(gems[left]) - 1);
                left++;
            }
        }

        return result;
    }
}
