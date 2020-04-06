package com.metricssuite.model;

import com.metricssuite.components.languageSelection;
import javafx.util.Pair;


import java.io.Serializable;
import java.util.*;

public class FunctionPoint implements Serializable {

    private int eivalue = 0;
    private int eovalue = 0;
    private int externalInquiries = 0;
    private int ilfvalue = 0;
    private int eifvalue = 0;
    private String eiWeight = "";
    private String eoWeight = "";
    private String externalInqWeight = "";
    private String ilfWeight = "";
    private String eifWeight = "";

    private int[] vaf;
    private int totalCount;
    private double functionPoint;
    private String language = "";
    private String name = "no_name";

    public FunctionPoint(){

        totalCount = 0;
        functionPoint = 0;
        vaf = new int[14];
        for (int i = 0; i < 14; i++) {
            vaf[i] = 0;
        }
    }

    public double getFunctionPoint() {
        return functionPoint;
    }

    public void setFunctionPoint(double functionPoint) {
        this.functionPoint = functionPoint;
    }

    public int getEivalue() {
        return eivalue;
    }

    public void setEivalue(int eivalue) {
        this.eivalue = eivalue;
    }

    public int getEovalue() {
        return eovalue;
    }

    public void setEovalue(int eovalue) {
        this.eovalue = eovalue;
    }

    public int getExternalInquiries() {
        return externalInquiries;
    }

    public void setExternalInquiries(int externalInquiries) {
        this.externalInquiries = externalInquiries;
    }

    public int getIlfvalue() {
        return ilfvalue;
    }

    public void setIlfvalue(int ilfvalue) {
        this.ilfvalue = ilfvalue;
    }

    public int getEifvalue() {
        return eifvalue;
    }

    public void setEifvalue(int eifvalue) {
        this.eifvalue = eifvalue;
    }

    public String getEiWeight() {
        return eiWeight;
    }

    public void setEiWeight(String eiWeight) {
        this.eiWeight = eiWeight;
    }

    public String getEoWeight() {
        return eoWeight;
    }

    public void setEoWeight(String eoWeight) {
        this.eoWeight = eoWeight;
    }

    public String getExternalInqWeight() {
        return externalInqWeight;
    }

    public void setExternalInqWeight(String externalInqWeight) {
        this.externalInqWeight = externalInqWeight;
    }

    public String getIlfWeight() {
        return ilfWeight;
    }

    public void setIlfWeight(String ilfWeight) {
        this.ilfWeight = ilfWeight;
    }

    public String getEifWeight() {
        return eifWeight;
    }

    public void setEifWeight(String eifWeight) {
        this.eifWeight = eifWeight;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int[] getVaf() {
        return vaf;
    }

    public void setVaf(int[] vaf) {
        this.vaf = vaf;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double computeFP(){

        return this.totalCount * (.65 + .01 * this.getVafTotal());
    }

    public int getVafTotal(){
        if(this.vaf == null)
            return 0;
        int sum = 0;
        for (int value : vaf) {
            sum += value;
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionPoint that = (FunctionPoint) o;

        return eivalue == that.eivalue &&
                eovalue == that.eovalue &&
                externalInquiries == that.externalInquiries &&
                ilfvalue == that.ilfvalue &&
                eifvalue == that.eifvalue &&
                totalCount == that.totalCount &&
                Double.compare(that.functionPoint, functionPoint) == 0 &&
                eiWeight.equals(that.eiWeight) &&
                eoWeight.equals(that.eoWeight) &&
                externalInqWeight.equals(that.externalInqWeight) &&
                ilfWeight.equals(that.ilfWeight) &&
                eifWeight.equals(that.eifWeight) &&
                Arrays.equals(vaf, that.vaf) &&
                language.equals(that.language);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(eivalue, eovalue, externalInquiries, ilfvalue, eifvalue, eiWeight, eoWeight,
                externalInqWeight, ilfWeight, eifWeight, totalCount, functionPoint, language);
        result = 31 * result + Arrays.hashCode(vaf);
        return result;
    }

    @Override
    public String toString(){
        return name;
    }
}
