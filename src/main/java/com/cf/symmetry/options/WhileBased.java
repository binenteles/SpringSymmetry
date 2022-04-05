package com.cf.symmetry.options;

import com.cf.symmetry.Evaluator;
import com.cf.symmetry.service.requirements.Requirements;

public class WhileBased extends Evaluator {
    @Override
    public boolean isSymmetric(String str) {
        char[] arr = str.toCharArray();
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            char left = arr[i];
            char right = arr[j];
            i++;
            j--;
            if (Requirements.compareCharacters(left, right)) {
                return false;
            }

        }
        return true;
    }
}
