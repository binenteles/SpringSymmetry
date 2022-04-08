package com.cf.symmetry;

import com.cf.symmetry.service.requirements.Requirements;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public abstract class Evaluator {
    public abstract boolean isSymmetric(String str);

    public final boolean evaluate(String str) {
        return !isStringNullOrEmpty(str) && !isValidWithoutSpaces(str)
                && isEven(str) && containsOnlyRequiredCharacters(str) && isSymmetric(str);
    }

    private boolean isStringNullOrEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }

    private boolean isValidWithoutSpaces(String str) {
        return str.trim().length() < 2;
    }

    private boolean isEven(String str) {
        return str.length() % 2 == 0;
    }

    private boolean containsOnlyRequiredCharacters(String str) {
        Set<Character> set = new HashSet<>();
        Requirements.readRequirementPairs().forEach(req -> {
            set.add(req.getLeftChar());
            set.add(req.getRightChar());
        });
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!set.contains(c)) {
                return false;
            }
        }
        return true;
    }


}
