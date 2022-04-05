package com.cf.symmetry.service;

import com.cf.symmetry.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {
    @Autowired
    private SymmetryFactory symmetryFactory;

    public boolean checkEntries(String str, String method){
        Type methodEvaluation = Type.valueOf(method);
        boolean result;

        if(str.isEmpty()){
            throw new IllegalArgumentException("Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX");
        } else if(method.isEmpty()){
            result = symmetryFactory.getEvaluator(Type.FOR).isSymmetric(str);
        } else {
            switch (methodEvaluation){
                case FOR -> result = symmetryFactory.getEvaluator(Type.FOR).isSymmetric(str);
                case STACK -> result = symmetryFactory.getEvaluator(Type.STACK).isSymmetric(str);
                case WHILE -> result = symmetryFactory.getEvaluator(Type.WHILE).isSymmetric(str);
                case REGEX -> result = symmetryFactory.getEvaluator(Type.REGEX).isSymmetric(str);
                default -> throw new IllegalArgumentException("Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX");
            }
        }
        return result;
    }

}
