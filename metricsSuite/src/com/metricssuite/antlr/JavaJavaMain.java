package com.metricssuite.antlr;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JavaJavaMain {
    public static void main(String[] args) throws IOException, RecognitionException {
        // Create an input character stream
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("/Users/alexnassif/IdeaProjects/Metrics-Suite/metricsSuite/src/com/metricssuite/components/FunctionPointGui.java"));
        // Create a lexer that feeds from that stream
        JavaJavaLexer lexer = new JavaJavaLexer(input);
        // Create a stream of tokens fed by the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Create a parser that feeds off the token stream
        JavaJavaParser parser = new JavaJavaParser(tokens);
        // Begin parsing at compilationUnit()
        parser.compilationUnit();


    }
}
