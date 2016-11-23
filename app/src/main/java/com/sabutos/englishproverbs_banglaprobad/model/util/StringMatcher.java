package com.sabutos.englishproverbs_banglaprobad.model.util;

/**
 * Created by devil on 20-Oct-16.
 */

public class StringMatcher {
    public static boolean match(String value, String keyword) {
        if (value == null || keyword == null)
            return false;
        if (keyword.length() > value.length())
            return false;

        int i = 0, j = 0;
        do {
            int vi = value.charAt(i);
            int kj = keyword.charAt(j);
            if (isEnglish(vi) && isInitialSound(kj)) {
            } else {
                if (vi == kj) {
                    i++;
                    j++;
                } else if (j > 0)
                    break;
                else
                    i++;
            }
        } while (i < value.length() && j < keyword.length());

        return (j == keyword.length())? true : false;
    }

    private static boolean isEnglish(int i) {
        return false;
    }

    private static boolean isInitialSound(int i) {
        return false;
    }
}
