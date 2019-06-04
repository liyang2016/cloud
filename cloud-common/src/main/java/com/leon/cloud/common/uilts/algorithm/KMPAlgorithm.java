package com.leon.cloud.common.uilts.algorithm;

import com.pyjaor.common.utils.ArraysUtils;

/**
 * Created by leon on 2018/9/7.
 */
public class KMPAlgorithm {


    public static void main(String[] args) {
        String testK="abcdeabcdfabxa";
        ArraysUtils.printArrays(calculateK(testK));

    }


    public static boolean matchString(String target, String mode) {

        return true;
    }

    public static int[] calculateK(String mode) {
        String newMode="x"+mode;
        int[] K = new int[newMode.length()];
        int i = 1;
        int j = 0;
        K[1] = 0;
        while (i < mode.length()) {
            if (j==0||newMode.charAt(i) == newMode.charAt(j)) {
                i++;
                j++;
                K[i] = j;
            } else {
                j = K[j];
            }
        }
        return K;
    }


    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
}
