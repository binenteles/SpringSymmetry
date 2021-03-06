package com.cf.symmetry.options;

import com.cf.symmetry.service.requirements.Reader;

public class WhileBased extends Evaluator {


  public WhileBased(Reader reader) {
    super(reader);
  }

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
      boolean charsNotMatch = getReader().compareCharacters(left, right);
      if (charsNotMatch) {
        return false;
      }

    }
    return true;
  }
}
