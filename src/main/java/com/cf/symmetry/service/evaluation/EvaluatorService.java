package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.EvalResponse;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.entity.EvalRequest;
import com.cf.symmetry.mapper.EvalRequestMapper;
import com.cf.symmetry.validator.EvalRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EvaluatorService {


    private final SymmetryFactory symmetryFactory;

    private final EvalRequestMapper evalRequestMapper;

    private final EvalRequestValidator evalRequestValidator;

    @Autowired
    public EvaluatorService(SymmetryFactory symmetryFactory, EvalRequestMapper evalRequestMapper, EvalRequestValidator evalRequestValidator) {
        this.symmetryFactory = symmetryFactory;
        this.evalRequestMapper = evalRequestMapper;
        this.evalRequestValidator = evalRequestValidator;
    }

    public EvalResponse provideResponse(EvalRequestDto evalRequestDto) {
        evalRequestValidator.validate(evalRequestDto);
        EvalRequest evalRequest = evalRequestMapper.map(evalRequestDto);
        boolean symmetric = symmetryFactory.getEvaluator(evalRequest.getMethod()).isSymmetric(evalRequest.getStr());

        if(symmetric){
            return new EvalResponse(String.format("String %s is symmetric. Method used: %s", evalRequest.getStr(), evalRequest.getMethod()));
        }

        return new EvalResponse(String.format("String %s is not symmetric. Method used: %s", evalRequest.getStr(), evalRequest.getMethod()));
    }


}
