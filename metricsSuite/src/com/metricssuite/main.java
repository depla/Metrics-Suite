/*
    The application driver class
 */

package com.metricssuite;

import com.metricssuite.menu.FunctionPointGui;

public class main {

    public static void main(String[] args){
        //start the application
        init();
    }

    static void init(){
        //initialize the startScreen
        new FunctionPointGui();
    }
}
