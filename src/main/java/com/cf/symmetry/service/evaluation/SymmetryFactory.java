package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.factory.MethodEvaluation;
import com.cf.symmetry.options.*;
import org.springframework.stereotype.Service;

@Service
public final class SymmetryFactory {

  public Evaluator getEvaluator(MethodEvaluation methodEvaluation) {
    return switch (methodEvaluation) {
      case FOR -> new ForBased();
      case STACK -> new StackBased();
      case REGEX -> new RegexBased();
      case WHILE -> new WhileBased();
    };

  }
}
