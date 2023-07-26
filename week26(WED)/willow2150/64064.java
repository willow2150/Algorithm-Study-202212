class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        int userIdListSize = user_id.length;
        int bannedIdListSize = banned_id.length;
        boolean[] checked = new boolean[1 << userIdListSize];
        int[] matchingIdBitSumArray = new int[bannedIdListSize];

        for (int bannedIdIdx = 0; bannedIdIdx < bannedIdListSize; bannedIdIdx++) {
            String bannedId = banned_id[bannedIdIdx];
            for (int userIdIdx = 0; userIdIdx < userIdListSize; userIdIdx++) {
                if (isMatchingString(bannedId, user_id[userIdIdx])) {
                    matchingIdBitSumArray[bannedIdIdx] |= (1 << userIdIdx);
                }
            }
        }

        return findNumOfPossibleCases(matchingIdBitSumArray, checked, 0, 0);
    }

    public static boolean isMatchingString(String stringA, String stringB) {
        final char WILD_CARD = '*';
        int userIdLength = stringB.length();

        if (stringA.length() != userIdLength) {
            return false;
        }

        for (int charIdx = 0; charIdx < userIdLength; charIdx++) {
            if (stringA.charAt(charIdx) == WILD_CARD) {
                continue;
            }
            if (stringA.charAt(charIdx) != stringB.charAt(charIdx)) {
                return false;
            }
        }
        return true;
    }

    public static int findNumOfPossibleCases(
            int[] matchingIdBitSumArray, boolean[] checked, int bitSum, int bannedIdIdx
    ) {
        if (bannedIdIdx == matchingIdBitSumArray.length) {
            if (checked[bitSum]) {
                return 0;
            } else {
                checked[bitSum] = true;
                return 1;
            }
        }

        int numOfPossibleCases = 0;
        int matchingIdBitSum = matchingIdBitSumArray[bannedIdIdx];

        while (matchingIdBitSum != 0) {
            int bit = matchingIdBitSum & -matchingIdBitSum;
            if ((bit & bitSum) == 0) {
                bitSum |= bit;
                numOfPossibleCases += findNumOfPossibleCases(
                        matchingIdBitSumArray, checked, bitSum, bannedIdIdx + 1
                );
                bitSum ^= bit;
            }
            matchingIdBitSum ^= bit;
        }
        return numOfPossibleCases;
    }
}
