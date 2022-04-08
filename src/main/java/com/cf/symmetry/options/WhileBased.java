package com.cf.symmetry.options;

import com.cf.symmetry.Evaluator;
import com.cf.symmetry.service.requirements.Validate;

public class WhileBased extends Evaluator {
    @Override
    public boolean isSymmetric(String str) {
        if (isInvalid(str)) {
            return false;
        }

        char[] arr = str.toCharArray();
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            char left = arr[i];
            char right = arr[j];
            i++;
            j--;
            boolean charsNotMatch = Validate.compareCharacters(left, right);
            if (charsNotMatch) {
                return false;
            }

        }
        return true;
    }
}
