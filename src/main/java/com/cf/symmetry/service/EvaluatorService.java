package com.cf.symmetry.service;

import com.cf.symmetry.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {
    @Autowired
    private SymmetryFactory symmetryFactory;

    private Entries entries;

    public void setEntries(Entries entries) {
        this.entries = entries;
    }

    public String getSymmetry() {
        checkEmptyString(entries.getStr());
        if (hasEvaluationMethod()) {
            return String.format("String %s is symmetric. Used method: %s", entries.getStr(), entries.getMethod());
        }
        return String.format("String %s is not symmetric. Used method: %s", entries.getStr(), entries.getMethod());
    }

    private void checkEmptyString(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Please provide a valid string. Method is optional; options are: FOR, WHILE, STACK, REGEX");
        }
    }

    private boolean hasEvaluationMethod() {
        final String input = entries.getStr();
        return switch (entries.getMethod()) {
            case "FOR" -> symmetryFactory.getEvaluator(Type.FOR).isSymmetric(input);
            case "STACK" -> symmetryFactory.getEvaluator(Type.STACK).isSymmetric(input);
            case "WHILE" -> symmetryFactory.getEvaluator(Type.WHILE).isSymmetric(input);
            case "REGEX" -> symmetryFactory.getEvaluator(Type.REGEX).isSymmetric(input);
            default -> throw new IllegalArgumentException("Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX");
        };

    }

}
