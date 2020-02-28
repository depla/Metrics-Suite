package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class languageSelection implements ActionListener {
    private JFrame frame;
    private JPanel panel,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,
            panel10,panel11, panel12;
    private JButton button;
    private HashMap<String,List<Integer>> languages = new HashMap<>();
    private List<Integer>linesOfCode;

    public languageSelection(){
        frame = new JFrame();
        frame.setTitle("Langauge Selection");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        frame.setLayout(new GridLayout(14,1));

        JLabel label= new JLabel("Select a language");
        panel.add(label);
        panel1 = getCheckbox("Assembler",209,203,91,320);
        panel2 = getCheckbox("Ada",154,0,104,205);
        panel3 = getCheckbox("C",148,107,22,704);
        panel4 = getCheckbox( "C++",59,53,20,178 );
        panel5 = getCheckbox("C#",58,59,51,66);
        panel6 = getCheckbox("COBOL",80,78,8,400);
        panel7 = getCheckbox("FORTRAN",90,118,35,0);
        panel8 = getCheckbox("HTML",43,42,35,53);
        panel9 = getCheckbox("Java",55,53,9,214);
        panel10 = getCheckbox("JavaScript",54,55,45,63);
        panel11 = getCheckbox("VBScript",38,37,29,50);
        panel12 = getCheckbox("Visual Basic",50,52,14,276);

        button = new JButton("Done");
        button.addActionListener(this);
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
        frame.add(button);
        frame.setSize(200,400);

        frame.setVisible(true);
    }

    private JPanel getCheckbox(String text, int avg, int median, int low, int high ){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        JCheckBox box = new JCheckBox(text);
        box.addActionListener(this);
        panel.add(box);
        languages.put(text,putLinesOfCode(avg,median,low,high));

        return panel;
    }

    private List<Integer> putLinesOfCode(int avg, int median, int low, int high){
        List<Integer> result = new ArrayList<>();
        result.add(avg);
        result.add(median);
        result.add(low);
        result.add(high);
        return result;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String i = e.getActionCommand();
        if(i == "Done"){
            setVisibility(false);
        }else {
            JCheckBox cb = (JCheckBox) e.getSource();
            System.out.println(cb.getText());
            if(languages.containsKey(cb.getText())){
                linesOfCode = languages.get(cb.getText());
            }
        }
        System.out.println(i);

    }
    public List<Integer> getLinesOfCode(){
        return this.linesOfCode;
    }

    public void setVisibility(boolean b){
        frame.setVisible(b);
    }


}
