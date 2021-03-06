package com.cf.symmetry.options;

import com.cf.symmetry.service.requirements.Pair;
import com.cf.symmetry.service.requirements.Reader;
import java.util.Stack;

public class StackBased extends Evaluator {

  public StackBased(Reader reader) {
    super(reader);
  }

  @Override
  public boolean isSymmetric(String str) {

    Stack<Character> stack = new Stack<>();
    boolean unexpectedChars =
        hasOpenBracketsInTheRightSide(str) || hasClosedBracketsInTheLeftSide(str);

    if (isInvalid(str) || unexpectedChars) {
      return false;
    }

    for (int i = 0; i < str.length(); i++) {
      char current = str.charAt(i);
      if (isOpenBracket(current)) {
        stack.push(current);

      } else {
        Character firstStackChar = stack.pop();
        boolean stackNotMatchWithCurrentChar = getReader().compareCharacters(firstStackChar,
            current);
        if (stackNotMatchWithCurrentChar) {
          return false;
        }
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
    return getReader().readPairs().stream().map(Pair::getLeftChar).toList().contains(input);
  }

  private boolean isClosedBracket(char input) {
    return getReader().readPairs().stream().map(Pair::getRightChar).toList().contains(input);
  }


}
