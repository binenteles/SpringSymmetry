package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.factory.MethodEvaluation;
import com.cf.symmetry.options.Evaluator;
import com.cf.symmetry.options.ForBased;
import com.cf.symmetry.options.RegexBased;
import com.cf.symmetry.options.StackBased;
import com.cf.symmetry.options.WhileBased;
import com.cf.symmetry.service.requirements.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SymmetryFactory {

  private final Reader reader;

  @Autowired
  public SymmetryFactory(Reader reader) {
    this.reader = reader;
  }

  public Evaluator getEvaluator(MethodEvaluation methodEvaluation) {
    return switch (methodEvaluation) {
      case FOR -> new ForBased(reader);
      case STACK -> new StackBased(reader);
      case REGEX -> new RegexBased(reader);
      case WHILE -> new WhileBased(reader);
    };

  }
}
