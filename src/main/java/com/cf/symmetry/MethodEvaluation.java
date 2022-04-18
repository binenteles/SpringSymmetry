package com.cf.symmetry;

public enum MethodEvaluation {
    FOR("METHOD_FOR"),
    WHILE("METHOD_WHILE"),
    STACK("METHOD_STACK"),
    REGEX("METHOD_REGEX");

    private final String method;


    MethodEvaluation(String method) {
        this.method = method;
    }


    public String getMethod(){
        return method;
    }


}
