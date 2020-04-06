package com.metricssuite.antlr;

public class Symbol
{
    private String identifier;
    private String primitiveType;
    private String classOrMethodName;

    public Symbol(String identifier, String primitiveType, String classOrMethodName)
    {
        this.identifier = identifier;
        this.primitiveType = primitiveType;
        this.classOrMethodName = classOrMethodName;
    }
}
