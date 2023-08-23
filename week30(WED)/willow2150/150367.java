class Solution {
    public int[] solution(long[] numbers) {
        final int EXPRESSIBLE = 1;
        final int INEXPRESSIBLE = 0;

        int[] expressible = new int[numbers.length];

        for (int numberIdx = 0; numberIdx < numbers.length; numberIdx++) {
            long number = numbers[numberIdx];
            String binStr = Long.toBinaryString(number);
            String dummy;
            int binStrLen = binStr.length();
            int fullBinStrLen = 1;
            int bit = binStrLen;

            while (0 < bit) {
                fullBinStrLen <<= 1;
                bit >>= 1;
            }
            fullBinStrLen--;

            dummy = "0".repeat(fullBinStrLen - binStrLen);
            
            if (canExpress(dummy + binStr, 0, fullBinStrLen)) {
                expressible[numberIdx] = EXPRESSIBLE;
            } else {
                expressible[numberIdx] = INEXPRESSIBLE;
            }
        }

        return expressible;
    }

    public boolean canExpress(String binStr, int left, int right) {
        if (left + 1 >= right) {
            return true;
        }
        int mid = (left + right) >> 1;
        if (binStr.charAt(mid) == '0') {
            for (int index = left; index < right; index++) {
                if (binStr.charAt(index) == '0') {
                    continue;
                }
                return false;
            }
            return true;
        }
        return canExpress(binStr, left, mid) && canExpress(binStr, mid + 1, right);
    }
}
