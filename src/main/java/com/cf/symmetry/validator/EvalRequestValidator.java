package com.cf.symmetry.validator;

import com.cf.symmetry.dto.EvalRequestDto;
import com.cf.symmetry.exceptions.ApiRequestException;
import org.springframework.stereotype.Service;

@Service
public class EvalRequestValidator {

    public void validate(EvalRequestDto evalRequestDto) {

        if (evalRequestDto.getStr() == null || evalRequestDto.getStr().isEmpty()) {
            throw new ApiRequestException("Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX");
        }
    }
}
