package com.metricssuite.menu;

import com.metricssuite.components.NewProjectWindow;
import com.metricssuite.components.languageSelection;
import com.metricssuite.model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    Project p;
    //Language l;

    public Menu() {
        getContentPane().setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        this.createTab();
        getContentPane().add(BorderLayout.CENTER, tabbedPane);
        this.setJMenuBar(createMenuBar());

        setTitle("Metrics Suite");
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar(); //create menubar

        JMenu file = new JMenu("File"); //create file menu and items

        JMenuItem newProject = new JMenuItem("New");
        newProject.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        file.add(newProject);
        file.add(open);
        file.add(save);
        file.add(exit);

        JMenu edit = new JMenu("Edit"); //create edit menu and items

        JMenu preferences = new JMenu("Preferences"); //create preferences menu and items
        JMenuItem languages = new JMenuItem("languages");
        languages.addActionListener(this);
        preferences.add(languages);

        JMenu metrics = new JMenu("Metrics"); //create metrics menu and items
        JMenuItem functionPoints = new JMenuItem("Function Points");
        functionPoints.addActionListener(this);
        metrics.add(functionPoints);

        JMenu help = new JMenu("Help"); //create help menu and items

        menuBar.add(file);  //add menus to menubar
        menuBar.add(edit);
        menuBar.add(preferences);
        menuBar.add(metrics);
        menuBar.add(help);

        return menuBar;
    }
    public void actionPerformed(ActionEvent e) {

        String i = e.getActionCommand();
        switch (i){
            case "New":
                System.out.println("New Project");
                new NewProjectWindow(this);
                //if true p = new Project
                // p = null;
                break;
            case "Open":
                System.out.println("open");
                break;
            case "languages":
                // l = new languageSelection();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
    protected void createTab() {
        tabbedPane.addTab( "Function Points", new JFrame().getContentPane());
    }
    public static void main(String []args) {
        new Menu();
    }
}
