package com.metricssuite.components;

import javax.swing.*;

public class TextInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField)input).getText();
        try{
            Integer value = Integer.valueOf(text);
            return (value >= 0);
        } catch (Exception e){
            System.out.println("not a valid number");
            return false;
        }
    }
}
