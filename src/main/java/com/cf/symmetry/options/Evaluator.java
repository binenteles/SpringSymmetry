package com.cf.symmetry.options;

import com.cf.symmetry.service.requirements.Reader;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class Evaluator {

  private final Reader reader;

  @Autowired
  protected Evaluator(Reader reader) {
    this.reader = reader;
  }

  public Reader getReader() {
    return reader;
  }

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
    return str.codePoints().mapToObj(i -> (char) i).noneMatch(reader::recognizeChar);
  }

}
