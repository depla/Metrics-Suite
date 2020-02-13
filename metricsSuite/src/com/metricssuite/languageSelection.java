package com.metricssuite;

import javax.swing.*;

public class languageSelection {
    private JFrame frame;
    private JPanel panel;

    private JCheckBox Checkbox1;
    private JCheckBox CheckBox2;
    private JCheckBox CheckBox3;
    private JCheckBox CheckBox4;
    private JCheckBox CheckBox5;
    private JCheckBox CheckBox6;
    private JCheckBox CheckBox7;
    private JCheckBox CheckBox8;
    private JCheckBox CheckBox9;
    private JCheckBox CheckBox10;
    private JCheckBox CheckBox11;
    private JCheckBox CheckBox12;
    private JLabel label1;

    public languageSelection(){
        frame = new JFrame();
        frame.setTitle("Language Selection");
        panel = new JPanel();
        panel.add(label1);
        panel.add(Checkbox1);
        panel.add(CheckBox2);
        panel.add(CheckBox3);
        panel.add(CheckBox4);
        panel.add(CheckBox5);
        panel.add(CheckBox6);
        panel.add(CheckBox7);
        panel.add(CheckBox8);
        panel.add(CheckBox9);
        panel.add(CheckBox10);
        panel.add(CheckBox11);
        panel.add(CheckBox12);
        frame.add(panel);
        frame.setSize(200,400);

        frame.setVisible(true);
    }
}
