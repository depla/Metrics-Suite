package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VAF implements ActionListener {
    private JFrame frame;
    private JPanel panel,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,
            panel10,panel11, panel12,panel13,panel14;
    private JPanel bottom;
    private JComboBox row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14;
    private List<Integer> list;
    private JButton done;

    public VAF(){
        list = new ArrayList<>();
        frame = new JFrame();
        frame.setTitle("Value Adjustment Factors");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        frame.setSize(new Dimension(800, 800));
        frame.setLayout(new GridLayout(16,1));

        panel.add(new JLabel("Assign a value from 0 to 5 for each of the following Value Adjustment Factors:"));
        panel1 = printLabelRow("Does the system require reliable backup and recovery processes? ",row1);
        panel2 = printLabelRow("Are specialized data communications required to transfer information to or from the application? ",row2);
        panel3 = printLabelRow("Are there distributed processing functions?",row3);
        panel4 = printLabelRow("Is performance ctritical?", row4);
        panel5 = printLabelRow("Will the system run in an existing, heavily utilized operational environment?", row5);
        panel6 = printLabelRow("Does the system require online data entry",row6);
        panel7 = printLabelRow("Does the online data entry require the input transaction to be built over multiple screens or operations ?", row7);
        panel8 = printLabelRow("Are the internal logical files updated online?",row8);
        panel9 = printLabelRow("Are the input, output, files or inquiries complex", row9);
        panel10 = printLabelRow("Is the internal processing complex? ",row10);
        panel11 = printLabelRow("Is the code designed to be reusable?",row11);
        panel12 = printLabelRow("Are conversion and installation included the design",row12);
        panel13 = printLabelRow("Is the system designed of multiple installation in different organizations?", row13);
        panel14 = printLabelRow("Is the application designed to facilitate change and for ease of use by the user?",row14);
        bottom= new JPanel();

        done = new JButton("Done");
        JButton cancel = new JButton("Cancel");
        //done.addActionListener(this);
        cancel.addActionListener(this);
        bottom.add(done);
        bottom.add(cancel);
        frame.add(panel);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);
        frame.add(panel7);
        frame.add(panel8);
        frame.add(panel9);
        frame.add(panel10);
        frame.add(panel11);
        frame.add(panel12);
        frame.add(panel13);
        frame.add(panel14);
        frame.add(bottom);


        frame.pack();
        frame.setVisible(false);

    }

    public JButton getDone() {
        return done;
    }

    public void setDone(JButton done) {
        this.done = done;
    }

    private JPanel printLabelRow(String st, JComboBox list){

        JPanel panel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.X_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.X_AXIS));

        JLabel label = new JLabel(st);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(label);
        list = dropdown();
        list.addActionListener(this);
        list.setMaximumSize(new Dimension(80, 50));
        list.setAlignmentX((Component.RIGHT_ALIGNMENT));
        rightPanel.add(list);

        panel.add(leftPanel,BorderLayout.LINE_START);
        panel.add(rightPanel,BorderLayout.LINE_END);
        return panel;
    }
    private JComboBox<Integer> dropdown(){
        JComboBox<Integer> list = new JComboBox<>();
        list.addItem(0);
        list.addItem(1);
        list.addItem(2);
        list.addItem(3);
        list.addItem(4);
        list.addItem(5);
        return list;

    }

    public void actionPerformed(ActionEvent e){
        String i = e.getActionCommand();
        if (i.equalsIgnoreCase("Done")) {
            setVisibility(false);
        } else if(i.equalsIgnoreCase("cancel")) {
            this.setVisibility(false);
        } else {
            JComboBox cb = (JComboBox) e.getSource();
            int value = (int) cb.getSelectedItem();
            list.add(value);
            System.out.println(value);
        }

    }
    public List<Integer> getVAFValue(){
        return this.list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public void setVisibility(boolean b){
        frame.setVisible(b);
    }




}
