package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.dto.EvalRequest;
import com.cf.symmetry.factory.MethodEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {

  private final SymmetryFactory symmetryFactory;

  @Autowired
  public EvaluatorService(SymmetryFactory symmetryFactory) {
    this.symmetryFactory = symmetryFactory;
  }

  public EvalResponse evaluate(EvalRequest evalRequest) {
    MethodEvaluation method = evalRequest.getMethod();
    String string = evalRequest.getStr();
    boolean symmetric = symmetryFactory.getEvaluator(method).isSymmetric(string);

    if (symmetric) {
      return new EvalResponse(
          String.format("String %s is symmetric. Method used: %s", string, method));
    }

    return new EvalResponse(
        String.format("String %s is not symmetric. Method used: %s", string, method));
  }

}
