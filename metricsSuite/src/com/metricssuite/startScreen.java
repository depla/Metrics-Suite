package com.metricssuite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startScreen implements ActionListener {
    private JPanel panelMain;
    private JFrame frame;
    private JMenuBar menuBar;
    JMenu menu1,menu2,menu3,menu4,menu5, submenu1,submenu2,submenu3;
    JMenuItem menuItem1,menuItem2,menuItem3, menuItem4,menuItem5,menuItem6;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    //Create the menu bar.

    public startScreen(){
        //create new JFrame
        frame = new JFrame();
        frame.setTitle("Metrics Suite");
        menuBar = new JMenuBar();
        //Build the first menu.
        menu1 = new JMenu("File");
        menuItem1 = new JMenuItem("New");
        menuItem2 = new JMenuItem("Open");
        menuItem3 = new JMenuItem("Save");
        menuItem4 = new JMenuItem("Exit");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menu1.add(menuItem4);

        menu2 = new JMenu("Edit");
        menu3 = new JMenu("Preference");
        menuItem5 = new JMenuItem("Language");
        menu3.add(menuItem5);

        menu4 = new JMenu("Metrics");
        submenu1 = new JMenu("Function Points");
        menuItem6 = new JMenuItem("Enter FP data");
        submenu1.add(menuItem6);
        menu4.add(submenu1);
        menuItem6.addActionListener(this);
        menu5 = new JMenu("Help");

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        menuBar.add(menu5);
        frame.setSize(400,500);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        languageSelection language = new languageSelection();

    }
}
