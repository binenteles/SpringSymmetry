package com.cf.symmetry.options;

import com.cf.symmetry.service.requirements.Rule;


public abstract class Evaluator {

    public abstract boolean isSymmetric(String str);

    public final boolean isInvalid(String str) {
        return missingInput(str) || invalidLength(str) || hasOtherCharacters(str);
    }

    private boolean missingInput(String str) {
        return str == null || str.isEmpty() || str.isBlank();
    }

    private boolean invalidLength(String str) {
        return str.length() % 2 == 1;
    }

    private boolean hasOtherCharacters(String str) {
        return str.codePoints().mapToObj(i -> (char) i).noneMatch(Rule::recognizeChar);
    }

}
