package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VAF {
    private JFrame frame;
    private JPanel panel,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11, panel12,panel13,panel14;
    private JPanel bottom;


    public VAF(){
        frame = new JFrame();
        frame.setTitle("Value Adjustment Factors");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        frame.setSize(new Dimension(800, 800));
        frame.setLayout(new GridLayout(16,1));

        panel.add(new JLabel("Assign a value from 0 to 5 for each of the following Value Adjustment Factors:"));
        panel1 = printLabelRow("Does the system require reliable backup and recovery processes? ");
        panel2 = printLabelRow("Are specialized data communications required to transfer information to or from the application? ");
        panel3 = printLabelRow("Are there distributed processing functions?");
        panel4 = printLabelRow("Is performance ctritical?");
        panel5 = printLabelRow("Will the system run in an existing, heavily utilized operational environment?");
        panel6 = printLabelRow("Does the system require online data entry");
        panel7 = printLabelRow("Does the online data entry require the input transaction to be built over multiple screens or operations ?");
        panel8 = printLabelRow("Are the internal logical files updated online?");
        panel9 = printLabelRow("Are the input, output, files or inquiries complex");
        panel10 = printLabelRow("Is the internal processing complex? ");
        panel11 = printLabelRow("Is the code designed to be reusable?");
        panel12 = printLabelRow("Are conversion and installation included the design");
        panel13 = printLabelRow("Is the system designed of multiple installation in different organizations?");
        panel14 = printLabelRow("Is the application designed to facilitate change and for ease of use by the user?");
        bottom= new JPanel();

        JButton done = new JButton("Done");
        JButton cancel = new JButton("Cancel");
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
        frame.setVisible(true);

    }
    private JPanel printLabelRow(String st){
        JPanel panel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.X_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.X_AXIS));

        JLabel label = new JLabel(st);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(label);

        JComboBox list = dropdown();
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

    private void actionPerformed(ActionEvent e){
        JComboBox cb = (JComboBox) e.getSource();
        int value = (int) cb.getSelectedItem();
        System.out.println(value);
    }




}
