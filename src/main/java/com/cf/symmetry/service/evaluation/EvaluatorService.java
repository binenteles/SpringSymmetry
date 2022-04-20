package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.service.RequestService;
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

    public EvalResponse evaluate(RequestService request) {
        MethodEvaluation method = request.getMethod();
        String string = request.getStr();
        boolean symmetric = symmetryFactory.getEvaluator(method).isSymmetric(string);

        if (symmetric) {
            return new EvalResponse(String.format("String %s is symmetric. Method used: %s", string, method));
        }

        return new EvalResponse(String.format("String %s is not symmetric. Method used: %s", string, method));
    }

}
