package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.entity.Request;
import com.cf.symmetry.factory.MethodEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {

    private final SymmetryFactory symmetryFactory;
    private final Request requestService;

    @Autowired
    public EvaluatorService(SymmetryFactory symmetryFactory, Request request) {
        this.symmetryFactory = symmetryFactory;
        this.requestService = request;
    }

    public EvalResponse evaluate(Request request) {
        Request checkedRequest = requestService.setDefaultMethod(request);
        MethodEvaluation method = checkedRequest.getMethod();
        String string = checkedRequest.getStr();
        boolean symmetric = symmetryFactory.getEvaluator(method).isSymmetric(string);

        if (symmetric) {
            return new EvalResponse(String.format("String %s is symmetric. Method used: %s", string, method));
        }

        return new EvalResponse(String.format("String %s is not symmetric. Method used: %s", string, method));
    }

}
