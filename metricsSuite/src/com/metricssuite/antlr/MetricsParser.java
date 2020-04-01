package com.metricssuite.antlr;

import java.io.File;
import java.io.IOException;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class MetricsParser
{
    public static String parse(File selectedFile) throws IOException, RecognitionException
    {
        String fileName;
        fileName = selectedFile.toString();
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

        System.out.println("Parsing file: " + fileName);

        JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(selectedFile.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaJavaParser parser = new JavaJavaParser(tokens);

        parser.compilationUnit();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("File name: ").append(fileName).append("\n");
        stringBuilder.append("File length in bytes: ").append(selectedFile.length()).append("\n");

        return stringBuilder.toString();
    }
}
