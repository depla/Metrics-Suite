package com.metricssuite.components;

import com.metricssuite.model.FunctionPoint;
import com.metricssuite.model.Project;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class FunctionPointGui extends JPanel {

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
    private Project project;


    public FunctionPointGui(Project p){
        this.project = p;
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


        totalTextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int total = computeTotal();
                totalTextfield.setText(String.valueOf(total));
                functionPoint.setTotalCount(total);
            }
        });

        functionPointBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set function point ei fields
                int eival = Integer.parseInt(eiPanel.getTextfield().getText());
                functionPoint.setEivalue(eival);
                functionPoint.setEiWeight(eiPanel.getWeight());

                //set function point eo fields
                int eoval = Integer.parseInt(eoPanel.getTextfield().getText());
                functionPoint.setEovalue(eoval);
                functionPoint.setEoWeight(eoPanel.getWeight());

                //set function point external inquiries fields
                int externalInq = Integer.parseInt(externalInquiriesPanel.getTextfield().getText());
                functionPoint.setExternalInquiries(externalInq);
                functionPoint.setExternalInqWeight(externalInquiriesPanel.getWeight());

                //set function point eif fields
                int eifval = Integer.parseInt(eifPanel.getTextfield().getText());
                functionPoint.setEifvalue(eifval);
                functionPoint.setEifWeight(eifPanel.getWeight());

                //set function point ilf fields
                int ilfval = Integer.parseInt(ilfPanel.getTextfield().getText());
                functionPoint.setIlfvalue(ilfval);
                functionPoint.setIlfWeight(ilfPanel.getWeight());

                //set function point field
                fpTextfield.setText(String.valueOf(functionPoint.computeFP()));
            }
        });



    }

    public int computeTotal(){

        return eiPanel.computedTotal()+ eoPanel.computedTotal() + externalInquiriesPanel.computedTotal() + eifPanel.computedTotal()
                + ilfPanel.computedTotal();
    }

}
