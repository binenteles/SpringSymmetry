package com.cf.symmetry.entity;

import com.cf.symmetry.MethodEvaluation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvalRequest {

    private String str;
    private MethodEvaluation method;
}
