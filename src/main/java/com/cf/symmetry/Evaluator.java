package com.cf.symmetry;

import com.cf.symmetry.service.requirements.Validate;


public abstract class Evaluator {
    public abstract boolean isSymmetric(String str);

    public final boolean isInvalid(String str) {
        return missingInput(str) || invalidLength(str) || hasOtherCharacters(str);
    }

    public boolean missingInput(String str) {
        return str == null || str.isEmpty() || str.isBlank();
    }

    public boolean invalidLength(String str) {
        return str.length() % 2 == 1;
    }

    public boolean hasOtherCharacters(String str) {
        return str.codePoints().mapToObj(i -> (char) i).noneMatch(Validate::recognizeChar);
    }

}
