package com.cf.symmetry.options;

import com.cf.symmetry.service.requirements.Validate;


public class ForBased extends Evaluator {

    @Override
    public boolean isSymmetric(String str) {
        if (isInvalid(str)) {
            return false;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            char start = str.charAt(i);
            char end = str.charAt(str.length() - 1 - i);
            boolean charsNotMatch = Validate.compareCharacters(start, end);

            if (charsNotMatch) {
                return false;
            }
        }
        return true;
    }
}
