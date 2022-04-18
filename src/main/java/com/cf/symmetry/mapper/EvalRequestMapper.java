package com.cf.symmetry.mapper;

import com.cf.symmetry.MethodEvaluation;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.entity.EvalRequest;
import com.cf.symmetry.exceptions.ApiRequestException;
import com.cf.symmetry.validator.EvalRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvalRequestMapper {

    private final EvalRequestValidator evalRequestValidator;

    @Autowired
    public EvalRequestMapper(EvalRequestValidator evalRequestValidator) {
        this.evalRequestValidator = evalRequestValidator;
    }

    public EvalRequest map(EvalRequestDto evalRequestDto) {

        EvalRequest evalRequest = new EvalRequest();
        evalRequest.setStr(evalRequestDto.getStr());
        try {
            if (evalRequestDto.getMethod() == null) {
                evalRequest.setMethod(MethodEvaluation.FOR);
            } else{
                evalRequest.setMethod(MethodEvaluation.valueOf(evalRequestDto.getMethod()));
            }
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX", e);
        }

        return evalRequest;
    }

}
