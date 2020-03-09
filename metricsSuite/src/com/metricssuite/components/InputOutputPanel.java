package com.metricssuite.components;

import com.metricssuite.model.FunctionPoint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.Enumeration;

public class InputOutputPanel extends JPanel {

    private JLabel label;
    private JTextField textfield;
    private JRadioButton simpleRadioButton;
    private JRadioButton avgRadioButton;
    private JRadioButton complexRadioButton;
    private JTextField total;
    private String name;
    private ButtonGroup buttonGroup;
    private String weight = "Average";
    private FunctionPoint functionPoint;

    public InputOutputPanel(String mName, String[] factors){

        this.name = mName;
        buttonGroup = new ButtonGroup();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        label = new JLabel(this.name);
        label.setMaximumSize(new Dimension(150, label.getMinimumSize().height));
        textfield = new JTextField();
        //textfield.setInputVerifier(new TextInputVerifier());
        textfield.getDocument().addDocumentListener(new IOPanelDocumentListener());

        textfield.setMaximumSize(new Dimension(50, label.getMinimumSize().height));
        simpleRadioButton = new JRadioButton(factors[0]);
        simpleRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        setWeight(button.getText());
                        Integer buttonValue = Integer.parseInt(button.getText());
                        Integer value = 0;

                        try{

                            value = Integer.parseInt(textfield.getText());


                        }catch(NumberFormatException ex) {
                            value = 0;
                        }

                        total.setText(String.valueOf( value * buttonValue));

                        if(name.equalsIgnoreCase("External Inputs")){
                            functionPoint.setEiWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Outputs")){

                            functionPoint.setEoWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Inquiries")){

                            functionPoint.setExternalInqWeight(button.getText());
                        }
                        if(name.equalsIgnoreCase("Internal Logical Files")){

                            functionPoint.setIlfWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Interface Files")){

                            functionPoint.setEifWeight(button.getText());

                        }
                    }
                }

            }
        });
        simpleRadioButton.setMaximumSize(new Dimension(70, simpleRadioButton.getMinimumSize().height));
        avgRadioButton = new JRadioButton(factors[1]);
        avgRadioButton.setSelected(true);
        avgRadioButton.setMaximumSize(new Dimension(70, avgRadioButton.getMinimumSize().height));
        avgRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        setWeight(button.getText());
                        Integer buttonValue = Integer.parseInt(button.getText());

                        Integer value = 0;

                        try{

                            value = Integer.parseInt(textfield.getText());


                        }catch(NumberFormatException ex) {
                            value = 0;
                        }

                        total.setText(String.valueOf( value * buttonValue));
                       // total.setText(String.valueOf(Integer.parseInt(textfield.getText()) * buttonValue));

                        if(name.equalsIgnoreCase("External Inputs")){
                            functionPoint.setEiWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Outputs")){

                            functionPoint.setEoWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Inquiries")){

                            functionPoint.setExternalInqWeight(button.getText());
                        }
                        if(name.equalsIgnoreCase("Internal Logical Files")){

                            functionPoint.setIlfWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Interface Files")){

                            functionPoint.setEifWeight(button.getText());

                        }
                    }
                }
            }
        });
        complexRadioButton = new JRadioButton(factors[2]);
        complexRadioButton.setMaximumSize(new Dimension(70, complexRadioButton.getMinimumSize().height));

        complexRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        setWeight(button.getText());
                        Integer buttonValue = Integer.parseInt(button.getText());

                        Integer value = 0;

                        try{

                            value = Integer.parseInt(textfield.getText());


                        }catch(NumberFormatException ex) {
                            value = 0;
                        }

                        total.setText(String.valueOf( value * buttonValue));
                        //total.setText(String.valueOf(Integer.parseInt(textfield.getText()) * buttonValue));

                        if(name.equalsIgnoreCase("External Inputs")){
                            functionPoint.setEiWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Outputs")){

                            functionPoint.setEoWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Inquiries")){

                            functionPoint.setExternalInqWeight(button.getText());
                        }
                        if(name.equalsIgnoreCase("Internal Logical Files")){

                            functionPoint.setIlfWeight(button.getText());

                        }
                        if(name.equalsIgnoreCase("External Interface Files")){

                            functionPoint.setEifWeight(button.getText());

                        }
                    }
                }
            }
        });
        buttonGroup.add(simpleRadioButton);
        buttonGroup.add(avgRadioButton);
        buttonGroup.add(complexRadioButton);
        total = new JTextField();
        total.setEditable(false);
        total.setMaximumSize(new Dimension(50, label.getMinimumSize().height));

        add(label);
        add(textfield);
        add(simpleRadioButton);
        add(avgRadioButton);
        add(complexRadioButton);
        add(total);

    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    public FunctionPoint getFunctionPoint() {
        return functionPoint;
    }

    public void setFunctionPoint(FunctionPoint functionPoint) {
        this.functionPoint = functionPoint;
    }

    public int computedTotal(){
        if(!total.getText().isEmpty()) {
            System.out.println(Integer.parseInt(total.getText()));
            return Integer.parseInt(total.getText());
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    class IOPanelDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                updateFields(e);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                updateFields(e);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            try {
                updateFields(e);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }

        public void updateFields(DocumentEvent e) throws BadLocationException {
            Document doc = (Document)e.getDocument();
            Integer value = 0;
            if(doc.getLength() != 0) {
                try {
                    value = Integer.parseInt(doc.getText(0, doc.getLength()));
                    if(value < 0){
                        value = 0;
                        JOptionPane.showMessageDialog(InputOutputPanel.this,
                                "Please enter all the name fields.",
                                "Error", JOptionPane.ERROR_MESSAGE);}
                } catch (NumberFormatException n){
                    value = 0;
                    JOptionPane.showMessageDialog(InputOutputPanel.this,
                            "Please enter a digit.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    Integer buttonValue = Integer.parseInt(button.getText());
                    total.setText(String.valueOf(value * buttonValue));

                    if(name.equalsIgnoreCase("External Inputs")){

                        functionPoint.setEivalue(value);

                    }
                    if(name.equalsIgnoreCase("External Outputs")){

                        functionPoint.setEovalue(value);


                    }
                    if(name.equalsIgnoreCase("External Inquiries")){
                        functionPoint.setExternalInquiries(value);

                    }
                    if(name.equalsIgnoreCase("Internal Logical Files")){

                        functionPoint.setIlfvalue(value);


                    }
                    if(name.equalsIgnoreCase("External Interface Files")){

                        functionPoint.setEifvalue(value);


                    }

                }
            }


        }
    }
}
