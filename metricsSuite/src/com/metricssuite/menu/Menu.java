package com.metricssuite.menu;

import com.metricssuite.components.FunctionPointGui;
import com.metricssuite.components.NewProjectWindow;
import com.metricssuite.components.languageSelection;
import com.metricssuite.model.FunctionPoint;
import com.metricssuite.model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    private NewProjectWindow projectWindow;
    private Project project;
    private languageSelection language;
    private FunctionPointGui functionPoint;

    //Language l;

    public Menu() {
        getContentPane().setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        getContentPane().add(BorderLayout.CENTER, tabbedPane);
        this.setJMenuBar(createMenuBar());

        setTitle("Metrics Suite");
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        language = new languageSelection();
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
                project = new Project();
                projectWindow = new NewProjectWindow(this, project);

                break;
            case "Open":
                System.out.println("open");
                createFileChooser();
                break;
            case "Save":
                System.out.println("Save");
                saveProject();
            case "languages":

                language.setVisibility(true);
                break;
            
            case "Function Points":
                createTab();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
    private void createTab() {

        tabbedPane.addTab( "Function Points", new FunctionPointGui(project, language));
    }

    private void createTab(FunctionPoint fp){
        tabbedPane.addTab( "Function Points", new FunctionPointGui(project, fp,language));
    }

    private void setTabsFromSaved(){

        for(FunctionPoint point: project.getFunctionPointArrayList())
            createTab(point);
    }

    private void createFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        File currentDirectory = new File(System.getProperty("user.dir"));

        fileChooser.setCurrentDirectory(currentDirectory);
        fileChooser.setDialogTitle("Choose a file to open");

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
            //get the last 3 chars of the absolute path
            String fileExtension = absolutePath.substring(absolutePath.length() - 3);

            //check if the last 3 chars are .ms
            if(fileExtension.equalsIgnoreCase(".ms"))
            {
                System.out.println("chosen ms file: " + fileChooser.getSelectedFile().getAbsolutePath());

                //read the project from the opened file
                project = Project.readProject(absolutePath);
                setTabsFromSaved();

                System.out.println(project.toString());
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Please choose an .ms file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveProject()
    {
        //check if project is null or not
        if(project == null)
        {
            JOptionPane.showMessageDialog(this,
                    "There is no open project. Unable to save.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //else there is a project open
        else
        {
            project.writeProject(project.getProjectName() + ".ms");
        }
    }

    public static void main(String []args) {
        new Menu();
    }
}
