package com.metricssuite.antlr;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.Scanner;
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
    static double numBugs = 0;

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

        //******************************************************************************
        //Trying to compute comment bytes
        Scanner scanner = new Scanner(selectedFile);

        StringBuilder sb = new StringBuilder();

        //read each line in the file and form a string
        while(scanner.hasNextLine())
        {
            sb.append(scanner.nextLine()).append("\n");
        }

        String stringFromFile = sb.toString();

        //remove all comments using REGEX
        String clean = stringFromFile.replaceAll
                ("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");

        //print out length of the strings,
        System.out.println("length with comments: " + stringFromFile.length());
        System.out.println("length without comments: " + clean.length());
        //System.out.println(clean);

        int numCommentsBytes = stringFromFile.length() - clean.length();


        //******************************************************************************


        //Halstead Metrics calculation
        uniqueOperators = metrics.uniqueSpecialCount()+metrics.uniqueKeywordsCount();//good
        uniqueOperands = metrics.uniqueConstantsCount()+ metrics.UniqueIndentifersCount();//good
        totalOperators = parser.specialcount+ parser.keywordCount;//off for xxx.java
        totalOperands = lexer.constantcount + parser.identcount;//good
        programLength = totalOperands + totalOperators;
        programVocab = uniqueOperands + uniqueOperators ;
        volume = programLength * (Math.log(programVocab)/Math.log(2));
        difficulty = (uniqueOperators/2)*(totalOperands/uniqueOperands);
        effort = volume * difficulty ;
        time = effort/18 ;
        double time_min = time/60;
        double time_hr = time/3600;
        double time_month= time_hr/8/20;
        numBugs = volume/3000;
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        Set<String> mccabeValues = new LinkedHashSet<>();
        mccabeValues = metrics.getMcCabeValues();


        stringBuilder.append("File name: ").append(fileName).append("\n");
        stringBuilder.append("File length in bytes: ").append(selectedFile.length()).append("\n");
        stringBuilder.append("File white space: ").append(lexer.ws).append("\n");
        stringBuilder.append("File comment space in bytes:").append(numCommentsBytes).append("\n");
        stringBuilder.append("Comment percentage of file: ").append(df.format(100* (double)numCommentsBytes/selectedFile.length())).append("%\n");
        stringBuilder.append("Halstead metrics: \n");
        stringBuilder.append("  Unique operators: "+ uniqueOperators).append("\n");
        stringBuilder.append("  Unique operands: "+ uniqueOperands).append("\n");
        stringBuilder.append("  Total operators: "+ totalOperators).append("\n");
        stringBuilder.append("  Total operands: "+ totalOperands).append("\n");
        stringBuilder.append("  Program Length (N)= ").append(programLength).append("\n");
        stringBuilder.append("  Program vocabulary(n)= ").append(programVocab).append("\n");
        stringBuilder.append("  Volume = ").append(df.format(volume)).append("\n");
        stringBuilder.append("  Difficulty = ").append(difficulty).append("\n");
        stringBuilder.append("  Effort = ").append(df.format(effort));
        stringBuilder.append(" Time = "+df.format(time)+"( "+ df.format(time_min) +" minutes or "+df.format(time_hr)+
                " hours or "+ df.format(time_month)+" person months)\n");
        stringBuilder.append("  Bugs expected = ").append(numBugs).append("\n\n\n\n");
        stringBuilder.append("McCabe's Cyclomatic Complexity ").append("\n");

        for(String sd : mccabeValues){
            stringBuilder.append("  "+ sd).append("\n");
        }




        //for debuggin only
        //Halstead Metrics
//        System.out.println("unique constant constants: "+ metrics.uniqueConstantsCount());
//        System.out.println("unique keyword constants: "+ metrics.uniqueKeywordsCount());
//        System.out.println("unique operators: "+ metrics.uniqueSpecialCount());
//        System.out.println("*********");
//        Set<String> specialCount = new LinkedHashSet<>();
//        specialCount = metrics.getUniqueSpecial();
//        System.out.println("operators: ");
//        for(String sp : specialCount){
//            System.out.print(sp +" ");
//        }
//        System.out.println("");
//
//        Set<String> uniqueOperator = new LinkedHashSet<>();
//        uniqueOperator = metrics.getUniqueOperator();
//        System.out.println("extra operators: ");
//        for(String spp : uniqueOperator){
//            System.out.print(spp+ " ");
//        }
//        System.out.println("");
//
//        Set<String> uniqueConstant = new LinkedHashSet<>();
//        uniqueConstant= metrics.getUniqueConstants();
//
//        System.out.println("unique constants: ");
//        for(String ss : uniqueConstant){
//            System.out.print(ss+" ");
//        }
//        System.out.println(" ");
//
//        Set<String> uniqueKeywords = new LinkedHashSet<>();
//        uniqueKeywords= metrics.getUniqueKeywords();
//        System.out.println("unique keywords:");
//        for(String sss : uniqueKeywords){
//            System.out.print(sss+"  ");
//        }
//        System.out.println("");
//
//        Set<String> uniqueIndentifers = new LinkedHashSet<>();
//        uniqueIndentifers = metrics.getUniqueIndentifers();
//        System.out.println("unique indentifiers");
//        for(String ssdd : uniqueIndentifers){
//            System.out.print(ssdd+"  ");
//        }
//        System.out.println("");

        return stringBuilder.toString();
    }
}
