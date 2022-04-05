package com.cf.symmetry.options;

import com.cf.symmetry.Evaluator;
import com.cf.symmetry.service.requirements.Requirement;
import com.cf.symmetry.service.requirements.Requirements;


import java.util.Stack;

public class StackBased extends Evaluator {
    @Override
    public boolean isSymmetric(String str) {
        Stack<Character> stack = new Stack<>();

        if (hasOpenBracketsInTheRightSide(str) || hasClosedBracketsInTheLeftSide(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (isOpenBracket(current)) {
                stack.push(current);

            } else if (Requirements.compareCharacters(stack.pop(), current)) {
                return false;
            }

        }
        return true;

    }

    private boolean hasOpenBracketsInTheRightSide(String str) {
        for (int i = str.length() / 2; i < str.length(); i++) {
            char current = str.charAt(i);
            if (isOpenBracket(current)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasClosedBracketsInTheLeftSide(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            char current = str.charAt(i);
            if (isClosedBracket(current)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOpenBracket(char input) {
        return Requirements.readRequirementPairs().stream().map(Requirement::getLeftChar).toList().contains(input);
    }


    private boolean isClosedBracket(char input) {
        return Requirements.readRequirementPairs().stream().map(Requirement::getRightChar).toList().contains(input);
    }


}
