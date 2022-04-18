package com.cf.symmetry.entity;

import com.cf.symmetry.factory.MethodEvaluation;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EvalRequest {
    @NotNull(message = "Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX")
    private String str;
    private MethodEvaluation method;

    public EvalRequest setDefaultMethod(EvalRequest evalRequest){
        if (evalRequest.getMethod() == null) {
            evalRequest.setMethod(MethodEvaluation.FOR);
        }
        return evalRequest;
    }

}
