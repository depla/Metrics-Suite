package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionPoint extends JPanel implements ActionListener {

    private InputOutputPanel eiPanel;
    private InputOutputPanel eoPanel;
    private InputOutputPanel externalInquiriesPanel;
    private InputOutputPanel ilfPanel;
    private InputOutputPanel eifPanel;
    private String dummy = "";

    public FunctionPoint(){

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
        eiPanel = new InputOutputPanel("External Inputs");
        eoPanel = new InputOutputPanel("External Outputs");
        externalInquiriesPanel = new InputOutputPanel("External Inquiries");
        ilfPanel = new InputOutputPanel("Internal Logical Files");
        eifPanel = new InputOutputPanel("External Interface Files");
        add(wf);
        add(labeling);
        add(eiPanel);
        add(eoPanel);
        add(externalInquiriesPanel);
        add(ilfPanel);
        add(eifPanel);

        JButton button = new JButton("Compute Fp");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dummy = eiPanel.getTextfield().getText();
                System.out.print(dummy);

            }
        });

        add(button);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
