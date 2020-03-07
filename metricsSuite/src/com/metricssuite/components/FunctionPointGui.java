package com.metricssuite.components;

import com.metricssuite.model.FunctionPoint;
import com.metricssuite.model.Project;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FunctionPointGui extends JPanel {

    private InputOutputPanel eiPanel;
    private InputOutputPanel eoPanel;
    private InputOutputPanel externalInquiriesPanel;
    private InputOutputPanel ilfPanel;
    private InputOutputPanel eifPanel;
    private JLabel totalLbl;
    private JTextField totalTextfield;
    private JButton functionPointBtn;
    private JTextField fpTextfield;
    private JButton vfBtn;
    private JTextField vfTextfield;
    private JButton computeSizeBtn;
    private JLabel langLbl;
    private JTextField languageTextfield;
    private JTextField computeSizeTextfield;
    private JButton changeLangBtn;
    private FunctionPoint functionPoint;
    private Project project;
    private VAF vaf;
    private languageSelection languageSelection;

    public FunctionPointGui(Project p, languageSelection language) {
        this.project = p;
        this.languageSelection = language;
        functionPoint = new FunctionPoint();
        vaf = new VAF();
        functionPoint.setVaf(vaf.getVAFValue());
        project.addFunctionPoint(functionPoint);
        initGui();
        initListeners();
    }


    public FunctionPointGui(Project p, FunctionPoint fp, languageSelection language) {

        this.functionPoint = fp;
        this.project = p;
        this.languageSelection = language;
        vaf = new VAF();
        initGui();
        initListeners();

        eiPanel.getTextfield().setText(String.valueOf(this.functionPoint.getEivalue()));
        eoPanel.getTextfield().setText(String.valueOf(this.functionPoint.getEovalue()));
        externalInquiriesPanel.getTextfield().setText(String.valueOf(this.functionPoint.getExternalInquiries()));
        ilfPanel.getTextfield().setText(String.valueOf(this.functionPoint.getIlfvalue()));
        eifPanel.getTextfield().setText(String.valueOf(this.functionPoint.getEifvalue()));

        String weight = this.functionPoint.getEiWeight();

        setWeights(eiPanel, weight);

        weight = this.functionPoint.getEoWeight();

        setWeights(eoPanel, weight);

        weight = this.functionPoint.getExternalInqWeight();

        setWeights(externalInquiriesPanel, weight);

        weight = this.functionPoint.getIlfWeight();

        setWeights(ilfPanel, weight);

        weight = this.functionPoint.getEifWeight();

        setWeights(eifPanel, weight);

        totalTextfield.setText(String.valueOf(functionPoint.getTotalCount()));
        languageTextfield.setText(functionPoint.getLanguage());
        fpTextfield.setText(String.valueOf(functionPoint.getFunctionPoint()));

    }

    private void initGui() {

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
        eiPanel = new InputOutputPanel("External Inputs", new String[]{"3", "4", "6"});
        eoPanel = new InputOutputPanel("External Outputs", new String[]{"4", "5", "7"});
        externalInquiriesPanel = new InputOutputPanel("External Inquiries", new String[]{"3", "4", "6"});
        ilfPanel = new InputOutputPanel("Internal Logical Files", new String[]{"7", "10", "15"});
        eifPanel = new InputOutputPanel("External Interface Files", new String[]{"5", "7", "10"});
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

        totalLbl = new JLabel("Total Count");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        calcPanel.add(totalLbl, constraints);

        totalTextfield = new JTextField();
        totalTextfield.setEditable(false);
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 0;
        calcPanel.add(totalTextfield, constraints);
        // constraints.insets = new Insets(0,0,0,0);
        functionPointBtn = new JButton("Compute FP");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        calcPanel.add(functionPointBtn, constraints);

        fpTextfield = new JTextField();
        fpTextfield.setEditable(false);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 1;
        calcPanel.add(fpTextfield, constraints);
        fpTextfield.setEditable(false);

        vfBtn = new JButton("Value Adjustments");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 2;
        calcPanel.add(vfBtn, constraints);

        vfTextfield = new JTextField();
        vfTextfield.setEditable(false);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 2;
        calcPanel.add(vfTextfield, constraints);

        computeSizeBtn = new JButton("Compute Size");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 3;
        calcPanel.add(computeSizeBtn, constraints);

        langLbl = new JLabel("Current Language");
        langLbl.setHorizontalAlignment(JLabel.RIGHT);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        calcPanel.add(langLbl, constraints);

        languageTextfield = new JTextField();
        languageTextfield.setEditable(false);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 3;
        calcPanel.add(languageTextfield, constraints);

        computeSizeTextfield = new JTextField();
        computeSizeTextfield.setEditable(false);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 3;
        calcPanel.add(computeSizeTextfield, constraints);

        changeLangBtn = new JButton("Change Language");
        constraints.fill = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 4;
        calcPanel.add(changeLangBtn, constraints);

        add(calcPanel);
    }

    private void setWeights(InputOutputPanel panel, String weight){

        for (Enumeration<AbstractButton> buttons = panel.getButtonGroup().getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.getText().equals(weight)) {
                button.setSelected(true);
            }
        }
    }

    private void initListeners(){

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
                functionPoint.setFunctionPoint(functionPoint.computeFP());
                fpTextfield.setText(String.valueOf(functionPoint.computeFP()));


            }
        });


        vfBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaf.setVisibility(true);

            }
        });

        vaf.getDone().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionPoint.setVaf(vaf.getVAFValue());
                vfTextfield.setText(String.valueOf(functionPoint.getVafTotal()));

            }
        });

        changeLangBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageSelection.setVisibility(true);
            }
        });

        languageTextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageTextfield.setText(languageSelection.getLangauge());
            }
        });

        computeSizeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> list = languageSelection.getLinesOfCode();
                computeSizeTextfield.setText(String.valueOf(list.get(0) * functionPoint.computeFP()));
            }
        });

        totalTextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int total = computeTotal();
                totalTextfield.setText(String.valueOf(total));
                functionPoint.setTotalCount(total);
            }
        });
    }

    public int computeTotal() {

        return eiPanel.computedTotal() + eoPanel.computedTotal() + externalInquiriesPanel.computedTotal() + eifPanel.computedTotal()
                + ilfPanel.computedTotal();
    }

}
