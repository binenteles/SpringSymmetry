package com.cf.symmetry.service.evaluation;

import com.cf.symmetry.MethodEvaluation;
import com.cf.symmetry.options.*;
import org.springframework.stereotype.Service;

@Service
public final class SymmetryFactory {

    public Evaluator getEvaluator(MethodEvaluation symmetricEvaluatorType) {
        return switch (symmetricEvaluatorType) {
            case FOR -> new ForBased();
            case WHILE -> new WhileBased();
            case STACK -> new StackBased();
            case REGEX -> new RegexBased();
        };

    }
}
