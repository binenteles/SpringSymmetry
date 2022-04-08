package com.cf.symmetry.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvalRequest {
    private String str;
    private String method = "FOR";
}
