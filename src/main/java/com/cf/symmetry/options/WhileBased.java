package com.cf.symmetry.options;

import com.cf.symmetry.Evaluator;
import com.cf.symmetry.requirements.Requirements;

public class WhileBased extends Evaluator {
    @Override
    public boolean isSymmetric(String str) {
        char[] arr = str.toCharArray();
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            char start = arr[i];
            char end = arr[j];
            i++;
            j--;
            if (Requirements.matchCharactersWithRequirementPair(start, end)) {
                return false;
            }

        }
        return true;
    }
}
