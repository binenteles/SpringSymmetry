package com.cf.symmetry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvalResponse {
    private String message;

    public EvalResponse(String message) {
        this.message = message;
    }
}
