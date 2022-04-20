package com.cf.symmetry.service.requirements;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Pair<T> {

  private T leftChar;
  private T rightChar;

  public boolean checkPair(char left, char right) {
    return getLeftChar().equals(left) && getRightChar().equals(right);
  }
}

