package com.cf.symmetry.entity;

import com.cf.symmetry.factory.MethodEvaluation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class Request {
    @NotNull(message = "Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX")
    private String str;

    private MethodEvaluation method;

    public Request setDefaultMethod(Request request){
        if (request.getMethod() == null) {
            request.setMethod(MethodEvaluation.FOR);
        }
        return request;
    }

}
