package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.factory.MethodEvaluation;
import com.cf.symmetry.options.*;
import org.springframework.stereotype.Service;

@Service
public final class SymmetryFactory {

    public Evaluator getEvaluator(MethodEvaluation methodEvaluation) {
        return switch (methodEvaluation.getMethod()) {
            case "METHOD_FOR" -> new ForBased();
            case "METHOD_STACK" -> new StackBased();
            case "METHOD_REGEX" -> new RegexBased();
            case "METHOD_WHILE" -> new WhileBased();
            default -> null;
        };

    }
}
