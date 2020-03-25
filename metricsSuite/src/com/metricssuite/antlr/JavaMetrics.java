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

    static Set<String> mccabeValues = new LinkedHashSet<String>();
}
