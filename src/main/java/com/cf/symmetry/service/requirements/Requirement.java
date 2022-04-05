package com.cf.symmetry.service.requirements;

import org.springframework.stereotype.Service;

@Service
public class Requirement<T> {
    private  T leftChar;
    private  T rightChar;

    public Requirement() {
    }

    public Requirement(T leftChar, T rightChar) {
        this.leftChar = leftChar;
        this.rightChar = rightChar;
    }

    public T getLeftChar() {
        return leftChar;
    }

    public T getRightChar() {

        return rightChar;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "left=" + leftChar +
                ", right=" + rightChar +
                '}';
    }

    public boolean compareChars(char left, char right) {
        return getLeftChar().equals(left) && getRightChar().equals(right);
    }
}

