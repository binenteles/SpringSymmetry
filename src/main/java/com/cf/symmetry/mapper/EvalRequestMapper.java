package com.cf.symmetry.mapper;

import com.cf.symmetry.MethodEvaluation;
import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.entity.EvalRequest;
import com.cf.symmetry.exceptions.ApiRequestException;
import org.springframework.stereotype.Service;

@Service
public class EvalRequestMapper {
    public EvalRequest mapToEntity(EvalRequestDto evalRequestDto) {
        EvalRequest evalRequest = new EvalRequest();
        if (evalRequestDto.getStr().isEmpty()){
            throw new ApiRequestException("Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX");
        }
        evalRequest.setStr(evalRequestDto.getStr());

        try {
            if (evalRequestDto.getMethod() == null) {
                evalRequest.setMethod(MethodEvaluation.FOR);
            }else {
                evalRequest.setMethod(MethodEvaluation.valueOf(evalRequestDto.getMethod()));
            }
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX", e);
        }

        return evalRequest;
    }

}
