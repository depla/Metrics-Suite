package com.metricssuite.antlr;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;


public class MetricsParser
{
    static JavaMetrics metrics;
    static StringBuilder stringBuilder;

    static int uniqueOperators = 0;
    static int uniqueOperands = 0;
    static int totalOperators = 0;
    static int totalOperands = 0;
    static int programLength = 0;
    static int programVocab = 0;
    static double volume = 0;
    static double difficulty = 0;
    static double effort = 0;
    static double time = 0;
    static int numBugs = 0;

    public MetricsParser(){
        metrics = new JavaMetrics();
        stringBuilder = new StringBuilder();
    }

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

        //Halstead calculation
        uniqueOperators = metrics.uniqueSpecialCount()+metrics.uniqueKeywordsCount();
        uniqueOperands = metrics.uniqueConstantsCount()+ metrics.UniqueIndentifersCount();
        totalOperators = parser.specialcount;
        totalOperands = lexer.constantcount + parser.identcount;
        programLength = totalOperands + totalOperators;
        programVocab = uniqueOperands + uniqueOperators ;
        volume = programLength * (Math.log(programVocab)/Math.log(2));
        difficulty = (totalOperators/2)*(totalOperands/uniqueOperands);
        effort = volume * difficulty ;
        time = effort/18 ;
        numBugs = (int)volume/3000;
        Set<String> mccabeValues = new LinkedHashSet<>();
        mccabeValues = metrics.getMcCabeValues();


        stringBuilder.append("File name: ").append(fileName).append("\n");
        stringBuilder.append("File length in bytes: ").append(selectedFile.length()).append("\n");
        stringBuilder.append("File white space: ").append(lexer.ws).append("\n");
        stringBuilder.append("File comment space in bytes:").append(lexer.commentcount).append("\n");
        stringBuilder.append("Comment percentage of file: ").append(100*lexer.commentcount/selectedFile.length()).append("%\n");
        stringBuilder.append("Halstead metrics: \n");
        stringBuilder.append("  Unique operators: "+ uniqueOperators).append("\n");
        stringBuilder.append("  Unique operands: "+ uniqueOperands).append("\n");
        stringBuilder.append("  Total operators: "+ totalOperators).append("\n");
        stringBuilder.append("  Total operands: "+ totalOperands).append("\n");
        stringBuilder.append("  Program Length (N)= ").append(programLength).append("\n");
        stringBuilder.append("  Program vocabulary(n)= ").append(programVocab).append("\n");
        stringBuilder.append("  Volume = ").append(volume).append("\n");
        stringBuilder.append("  Difficulty = ").append(difficulty).append("\n");
        stringBuilder.append("  Effort = ").append(effort).append("\n");
        stringBuilder.append("  Bugs expected = ").append(numBugs).append("\n\n\n\n");
        stringBuilder.append("McCabe's Cyclomatic Complexity ").append("\n");

        for(String sd : mccabeValues){
            stringBuilder.append("  "+ sd).append("\n");
        }

///for debuggin only
        //Halstead Metrics
        System.out.println("unique constant constants: "+ metrics.uniqueConstantsCount());
        System.out.println("unique keyword constants: "+ metrics.uniqueKeywordsCount());
        System.out.println("unique operators: "+ metrics.uniqueSpecialCount());
        System.out.println("*********");


        Set<String> specialCount = new LinkedHashSet<>();
        specialCount = metrics.getUniqueSpecial();
        System.out.println("operators: ");
        for(String sp : specialCount){
            System.out.print(sp+" ");
        }
        System.out.println("");

        Set<String> uniqueConstant = new LinkedHashSet<>();
        uniqueConstant= metrics.getUniqueConstants();

        System.out.println("unique constants: ");
        for(String ss : uniqueConstant){
            System.out.print(ss+" ");
        }
        System.out.println(" ");

        Set<String> uniqueKeywords = new LinkedHashSet<>();
        uniqueKeywords= metrics.getUniqueKeywords();
        System.out.println("unique keywords:");
        for(String sss : uniqueKeywords){
            System.out.print(sss+"  ");
        }
        System.out.println("");

        Set<String> uniqueIndentifers = new LinkedHashSet<>();
        uniqueIndentifers = metrics.getUniqueIndentifers();
        System.out.println("unique indentifiers");
        for(String ssdd : uniqueIndentifers){
            System.out.print(ssdd+"  ");
        }
        System.out.println("");





        return stringBuilder.toString();
    }
}
