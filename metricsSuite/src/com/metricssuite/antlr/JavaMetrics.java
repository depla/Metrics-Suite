package com.metricssuite.antlr;

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaMetrics
{
    static Set<String> uniqueKeywords;
    static Set<String> uniqueIdentifiers;

    static Set<Symbol> uIDSym;

    static Set<String> uniqueConstants;
    static Set<String> uniqueSpecial;

    static Set<String> uniqueOperator;
    static Set<String>  uniqueOperand;

    static Set<String> mccabeValues;

    public JavaMetrics(){
        mccabeValues = new LinkedHashSet<String>();
        uniqueKeywords = new LinkedHashSet<String>();
        uniqueIdentifiers= new LinkedHashSet<String>();
        uIDSym=new LinkedHashSet<Symbol>();
        uniqueConstants = new LinkedHashSet<String>();
        uniqueSpecial = new LinkedHashSet<String>();
        uniqueOperator = new LinkedHashSet<>();
        uniqueOperand = new LinkedHashSet<>();

    }

    public int uniqueConstantsCount(){
        return uniqueConstants.size();
    }

    public static Set<String> getUniqueConstants(){
      return uniqueConstants;

    }

    public int uniqueKeywordsCount(){
        return uniqueKeywords.size();
    }

    public static Set<String> getUniqueKeywords(){
        return uniqueKeywords;
    }


    public int uniqueSpecialCount(){
        return uniqueSpecial.size();
    }

    public static Set<String> getUniqueSpecial(){
        return uniqueSpecial;
    }

    public int UniqueIndentifersCount(){
        return uniqueIdentifiers.size();
    }
    public static Set<String> getUniqueIndentifers(){
        return uniqueIdentifiers;
    }

    public int mccabeValuesCount(){
        return mccabeValues.size();
    }
    public static Set<String> getMcCabeValues(){
        return mccabeValues;
    }
    public static Set<Symbol> getSymbol(){return uIDSym;}
    public int uniqueOperatorCount(){
        return uniqueOperator.size();
    }
    public static Set<String> getUniqueOperator(){
        return uniqueOperator;
    }
}
