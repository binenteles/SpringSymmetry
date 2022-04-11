package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.entity.EvalRequest;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.mapper.EvalRequestMapper;
import com.cf.symmetry.EvalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {
    @Autowired
    private SymmetryFactory symmetryFactory;

    @Autowired
    private EvalRequestMapper evalRequestMapper;

    public EvalResponse checkStrSymmetry(EvalRequestDto evalRequestDto) {
        EvalRequest evalRequest = evalRequestMapper.mapToEntity(evalRequestDto);
        boolean symmetric = symmetryFactory.getEvaluator(evalRequest.getMethod()).isSymmetric(evalRequest.getStr());

        if(symmetric){
            return new EvalResponse(String.format("String %s is symmetric. Method used: %s", evalRequest.getStr(), evalRequest.getMethod()));
        }

        return new EvalResponse(String.format("String %s is not symmetric. Method used: %s", evalRequest.getStr(), evalRequest.getMethod()));
    }


}
