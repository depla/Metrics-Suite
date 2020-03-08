package com.metricssuite.model;

import com.metricssuite.components.languageSelection;
import javafx.util.Pair;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionPoint implements Serializable {

    private int eivalue = 0;
    private int eovalue = 0;
    private int externalInquiries = 0;
    private int ilfvalue = 0;
    private int eifvalue = 0;
    private String eiWeight;
    private String eoWeight;
    private String externalInqWeight;
    private String ilfWeight;
    private String eifWeight;

    private int[] vaf;
    private int totalCount;
    private double functionPoint;
    private String language = "java";

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


}
