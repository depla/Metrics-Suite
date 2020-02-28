package com.metricssuite.components;

import com.metricssuite.model.FunctionPoint;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class FunctionPointGui extends JPanel implements ActionListener {

    private InputOutputPanel eiPanel;
    private InputOutputPanel eoPanel;
    private InputOutputPanel externalInquiriesPanel;
    private InputOutputPanel ilfPanel;
    private InputOutputPanel eifPanel;
    private String dummy = "Java";
    private FunctionPoint functionPoint;
    private static int eivalue = 0;
    private static int eovalue = 0;
    private static int externalInquiries = 0;
    private static int ilfvalue = 0;
    private static int eifvalue = 0;


    public FunctionPointGui(){
        functionPoint = new FunctionPoint(new languageSelection());
        JPanel wf = new JPanel();
        wf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        wf.setLayout(new FlowLayout());
        JLabel wfLabel = new JLabel("             Weighting Factors");
        wf.add(wfLabel);

        JPanel labeling = new JPanel();
        labeling.setLayout(new BoxLayout(labeling, BoxLayout.X_AXIS));
        JLabel label1 = new JLabel();
        label1.setMaximumSize(new Dimension(100, label1.getMinimumSize().height));
        JLabel labelSimple = new JLabel("Simple    Average    Complex");

        labeling.add(label1);
        labeling.add(labelSimple);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        eiPanel = new InputOutputPanel("External Inputs", new String[] {"3", "4", "6"});
        eoPanel = new InputOutputPanel("External Outputs", new String[] {"4", "5", "7"});
        externalInquiriesPanel = new InputOutputPanel("External Inquiries", new String[] {"3", "4", "6"});
        ilfPanel = new InputOutputPanel("Internal Logical Files", new String[] {"7", "10", "15"});
        eifPanel = new InputOutputPanel("External Interface Files", new String[] {"5", "7", "10"});
        add(wf);
        add(labeling);
        add(eiPanel);
        add(eoPanel);
        add(externalInquiriesPanel);
        add(ilfPanel);
        add(eifPanel);

        JPanel calcPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 0.5;

        JLabel totalLbl = new JLabel("Total Count");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        calcPanel.add(totalLbl, constraints);

        JTextField totalTextfield = new JTextField();
        constraints.insets = new Insets(0,0,0,10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 0;
        calcPanel.add(totalTextfield, constraints);
       // constraints.insets = new Insets(0,0,0,0);
        JButton functionPointBtn = new JButton("Compute FP");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        calcPanel.add(functionPointBtn, constraints);

        JTextField fpTextfield = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 1;
        calcPanel.add(fpTextfield, constraints);

        JButton vfBtn = new JButton("Value Adjustments");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 2;
        calcPanel.add(vfBtn, constraints);

        JTextField vfTextfield = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 2;
        calcPanel.add(vfTextfield, constraints);

        JButton computeSizeBtn = new JButton("Compute Size");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 3;
        calcPanel.add(computeSizeBtn, constraints);

        JLabel langLbl = new JLabel("Current Language");
        langLbl.setHorizontalAlignment(JLabel.RIGHT);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        calcPanel.add(langLbl, constraints);

        JTextField languageTextfield = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 3;
        calcPanel.add(languageTextfield, constraints);

        JTextField computeSizeTextfield = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 3;
        calcPanel.add(computeSizeTextfield, constraints);

        JButton changeLangBtn = new JButton("Change Language");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 4;
        calcPanel.add(changeLangBtn, constraints);

        add(calcPanel);

        /*eiPanel.getTextfield().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(eiPanel.getTextfield().getText());
                eivalue = Integer.parseInt(eiPanel.getTextfield().getText());
                functionPoint.setEivalue(eivalue);

            }
        });

        eoPanel.getTextfield().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eovalue = Integer.parseInt(eoPanel.getTextfield().getText());
                functionPoint.setEovalue(eovalue);
            }
        });
        externalInquiriesPanel.getTextfield().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                externalInquiries = Integer.parseInt(externalInquiriesPanel.getTextfield().getText());
                functionPoint.setExternalInquiries(externalInquiries);
            }
        });

        ilfPanel.getTextfield().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ilfvalue = Integer.parseInt(ilfPanel.getTextfield().getText());
                functionPoint.setIlfvalue(ilfvalue);
            }
        });
        eifPanel.getTextfield().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eifvalue = Integer.parseInt(eifPanel.getTextfield().getText());
                functionPoint.setEifvalue(eifvalue);
            }
        });*/

        eiPanel.getTotal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Enumeration<AbstractButton> buttons = eiPanel.getButtonGroup().getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        functionPoint.setEivalue(Integer.parseInt(eiPanel.getTextfield().getText()));
                        functionPoint.setEiWeight(button.getText());
                        eivalue = functionPoint.getEivalue() * Integer.parseInt(button.getText());
                        eiPanel.getTotal().setText(String.valueOf(eivalue));
                    }
                }

            }
        });

        eoPanel.getTotal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Enumeration<AbstractButton> buttons = eoPanel.getButtonGroup().getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        functionPoint.setEovalue(Integer.parseInt(eoPanel.getTextfield().getText()));
                        functionPoint.setEoWeight(button.getText());
                        eovalue = functionPoint.getEovalue() * Integer.parseInt(button.getText());
                        eoPanel.getTotal().setText(String.valueOf(eovalue));
                    }
                }

            }
        });

        externalInquiriesPanel.getTotal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Enumeration<AbstractButton> buttons = externalInquiriesPanel.getButtonGroup().getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        functionPoint.setExternalInquiries(Integer.parseInt(externalInquiriesPanel.getTextfield().getText()));
                        functionPoint.setExternalInqWeight(button.getText());
                        externalInquiries = functionPoint.getExternalInquiries() * Integer.parseInt(button.getText());
                        externalInquiriesPanel.getTotal().setText(String.valueOf(externalInquiries));
                    }
                }

            }
        });

        ilfPanel.getTotal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Enumeration<AbstractButton> buttons = ilfPanel.getButtonGroup().getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        functionPoint.setIlfvalue(Integer.parseInt(ilfPanel.getTextfield().getText()));
                        functionPoint.setIlfWeight(button.getText());
                        ilfvalue = functionPoint.getIlfvalue() * Integer.parseInt(button.getText());
                        ilfPanel.getTotal().setText(String.valueOf(ilfvalue));
                    }
                }

            }
        });

        eifPanel.getTotal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Enumeration<AbstractButton> buttons = eifPanel.getButtonGroup().getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        functionPoint.setEifvalue(Integer.parseInt(eifPanel.getTextfield().getText()));
                        functionPoint.setEifWeight(button.getText());
                        eifvalue = functionPoint.getEifvalue() * Integer.parseInt(button.getText());
                        eifPanel.getTotal().setText(String.valueOf(eifvalue));
                    }
                }

            }
        });

        totalTextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalTextfield.setText(String.valueOf(computeTotal()));
            }
        });



    }

    public static int computeTotal(){

        return eivalue + eovalue + externalInquiries + eifvalue + ilfvalue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(500, 500));
        FunctionPointGui p = new FunctionPointGui();
        frame.add(p);
        frame.setVisible(true);
    }
}
