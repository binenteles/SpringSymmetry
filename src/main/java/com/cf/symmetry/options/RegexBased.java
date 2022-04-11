package com.cf.symmetry.options;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBased extends Evaluator {
    @Override
    public boolean isSymmetric(String str) {
        if (isInvalid(str)) {
            return false;
        }

        if (str.length() == 2) {
            return regexMatches(str);
        }

        String remainedString = str.substring(1, str.length() - 1);
        return regexMatches(str) && isSymmetric(remainedString);
    }

    private boolean regexMatches(String str) {
        Pattern pattern = Pattern.compile("(^\\(.*?\\)$)|(^\\[.*?]$)|(^\\{.*?}$)");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}

