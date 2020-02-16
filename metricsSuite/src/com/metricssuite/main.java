/*
    The application driver class
 */

package com.metricssuite;

import com.metricssuite.components.NewProjectWindow;
import com.metricssuite.menu.Menu;

public class main {

    public static void main(String[] args){
        //start the application
        init();
    }

    static void init(){
        //initialize the startScreen
        new Menu();
    }
}
