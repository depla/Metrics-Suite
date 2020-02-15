package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;

public class InputOutputPanel extends JPanel {

    private JLabel label;
    private JTextField textfield;
    private JRadioButton simpleRadioButton;
    private JRadioButton avgRadioButton;
    private JRadioButton complexRadioButton;
    private JTextField total;
    private String name;

    public InputOutputPanel(String name, String[] factors){

        this.name = name;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        label = new JLabel(this.name);
        label.setMaximumSize(new Dimension(150, label.getMinimumSize().height));
        textfield = new JTextField();
        textfield.setMaximumSize(new Dimension(50, label.getMinimumSize().height));
        simpleRadioButton = new JRadioButton(factors[0]);
        simpleRadioButton.setMaximumSize(new Dimension(70, simpleRadioButton.getMinimumSize().height));
        avgRadioButton = new JRadioButton(factors[1]);
        avgRadioButton.setMaximumSize(new Dimension(70, avgRadioButton.getMinimumSize().height));
        complexRadioButton = new JRadioButton(factors[2]);
        complexRadioButton.setMaximumSize(new Dimension(70, complexRadioButton.getMinimumSize().height));

        total = new JTextField();
        total.setMaximumSize(new Dimension(50, label.getMinimumSize().height));

        add(label);
        add(textfield);
        add(simpleRadioButton);
        add(avgRadioButton);
        add(complexRadioButton);
        add(total);

    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getTextfield() {
        return textfield;
    }

    public void setTextfield(JTextField textfield) {
        this.textfield = textfield;
    }

    public JRadioButton getSimpleRadioButton() {
        return simpleRadioButton;
    }

    public void setSimpleRadioButton(JRadioButton simpleRadioButton) {
        this.simpleRadioButton = simpleRadioButton;
    }

    public JRadioButton getAvgRadioButton() {
        return avgRadioButton;
    }

    public void setAvgRadioButton(JRadioButton avgRadioButton) {
        this.avgRadioButton = avgRadioButton;
    }

    public JRadioButton getComplexRadioButton() {
        return complexRadioButton;
    }

    public void setComplexRadioButton(JRadioButton complexRadioButton) {
        this.complexRadioButton = complexRadioButton;
    }

    public JTextField getTotal() {
        return total;
    }

    public void setTotal(JTextField total) {
        this.total = total;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
