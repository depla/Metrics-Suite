package com.metricssuite.antlr;

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaMetrics
{
    static Set<String> uniqueKeywords = new LinkedHashSet<String>();
    static Set<String> uniqueIdentifiers= new LinkedHashSet<String>();

    static Set<Symbol> uIDSym=new LinkedHashSet<Symbol>();

    static Set<String> uniqueConstants = new LinkedHashSet<String>();
    static Set<String> uniqueSpecial = new LinkedHashSet<String>();

    static Set<String> uniqueOperator = new LinkedHashSet<>();
    static Set<String>  uniqueOperand = new LinkedHashSet<>();

    static Set<String> mccabeValues;

    public JavaMetrics(){
        mccabeValues = new LinkedHashSet<String>();
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

}
