package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.entity.EvalRequest;
import com.cf.symmetry.factory.SymmetryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EvaluatorService {

    private final SymmetryFactory symmetryFactory;
    private final EvalRequest evalRequest;

    @Autowired
    public EvaluatorService(SymmetryFactory symmetryFactory, EvalRequest evalRequest) {
        this.symmetryFactory = symmetryFactory;
        this.evalRequest = evalRequest;
    }

    public EvalResponse provideResponse(EvalRequest evalRequest) {
        EvalRequest request = evalRequest.setDefaultMethod(evalRequest);
        boolean symmetric = symmetryFactory.getEvaluator(request.getMethod()).isSymmetric(request.getStr());

        if (symmetric) {
            return new EvalResponse(String.format("String %s is symmetric. Method used: %s", request.getStr(), request.getMethod()));
        }

        return new EvalResponse(String.format("String %s is not symmetric. Method used: %s", request.getStr(), request.getMethod()));
    }

}
