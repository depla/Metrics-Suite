package com.metricssuite.model;

import javafx.util.Pair;

public class FunctionPoint {

    private Pair<Integer, String> ei;
    private Pair<Integer, String> eo;
    private Pair<Integer, String> externalI;
    private Pair<Integer, String> ilf;
    private Pair<Integer, String> eif;
    private int[] vaf = null;
    private int totalCount;


    private String language;
    public FunctionPoint(String language){

        totalCount = 0;
        this.language = language;
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

    public Pair<Integer, String> getEi() {
        return ei;
    }

    public void setEi(Pair<Integer, String> ei) {
        this.ei = ei;
    }

    public Pair<Integer, String> getEo() {
        return eo;
    }

    public void setEo(Pair<Integer, String> eo) {
        this.eo = eo;
    }

    public Pair<Integer, String> getExternalI() {
        return externalI;
    }

    public void setExternalI(Pair<Integer, String> externalI) {
        this.externalI = externalI;
    }

    public Pair<Integer, String> getIlf() {
        return ilf;
    }

    public void setIlf(Pair<Integer, String> ilf) {
        this.ilf = ilf;
    }

    public Pair<Integer, String> getEif() {
        return eif;
    }

    public void setEif(Pair<Integer, String> eif) {
        this.eif = eif;
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
