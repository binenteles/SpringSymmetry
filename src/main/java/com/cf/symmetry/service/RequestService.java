package com.cf.symmetry.service;

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
public class RequestService {
    @NotNull(message = "Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX")
    private String str;

    private MethodEvaluation method = MethodEvaluation.FOR;


}
