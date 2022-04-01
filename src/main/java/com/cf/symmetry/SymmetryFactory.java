package com.cf.symmetry;

import com.cf.symmetry.options.ForBased;
import com.cf.symmetry.options.RegexBased;
import com.cf.symmetry.options.StackBased;
import com.cf.symmetry.options.WhileBased;
import org.springframework.stereotype.Service;

@Service
public final class SymmetryFactory {
    public Evaluator getEvaluator(Type symmetricEvaluatorType) {
        return switch (symmetricEvaluatorType) {
            case FOR -> new ForBased();
            case WHILE -> new WhileBased();
            case STACK -> new StackBased();
            case REGEX -> new RegexBased();
        };

    }
}
