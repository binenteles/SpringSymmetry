package com.cf.symmetry.service;

import com.cf.symmetry.Type;
import com.cf.symmetry.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {
    @Autowired
    private SymmetryFactory symmetryFactory;

    public boolean checkStrSymmetry(String str, String method) {
        if (str.isEmpty()) {
            throw new ApiRequestException("Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX");
        }
        try {
            Type methodEvaluation = Type.valueOf(method);
            return switch (methodEvaluation) {
                case FOR -> symmetryFactory.getEvaluator(Type.FOR).isSymmetric(str);
                case STACK ->  symmetryFactory.getEvaluator(Type.STACK).isSymmetric(str);
                case WHILE -> symmetryFactory.getEvaluator(Type.WHILE).isSymmetric(str);
                case REGEX ->  symmetryFactory.getEvaluator(Type.REGEX).isSymmetric(str);
            };
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException("Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX", e);
        }

    }


}
