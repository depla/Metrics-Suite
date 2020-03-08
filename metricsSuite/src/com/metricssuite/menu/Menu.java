package com.metricssuite.menu;

import com.metricssuite.components.FunctionPointGui;
import com.metricssuite.components.NewProjectWindow;
import com.metricssuite.components.VAF;
import com.metricssuite.components.languageSelection;
import com.metricssuite.model.FunctionPoint;
import com.metricssuite.model.Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    private NewProjectWindow projectWindow;
    private Project project;
    private languageSelection language;
    private FunctionPointGui functionPoint;
    //private static VAF  v;

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
                //check if there is a project open or not
                if(project == null)
                {
                    //create a new project
                    project = new Project();
                    projectWindow = new NewProjectWindow(this, project);
                }
                else //one is already open
                {
                    //pass a new null one
                    Project newProject = null;
                    projectWindow = new NewProjectWindow(this, newProject);
                }

                break;
            case "Open":
                System.out.println("open");
                createFileChooser();
                break;
            case "Save":
                System.out.println("Save");
                saveProject();
                break;
            case "languages":

                language.setVisibility(true);
                break;
            
            case "Function Points":
                createTab();
                break;

            case "Exit":
                System.out.println("Exit");
                exitProgramAutoSave();
                //close program
                System.exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
    private void createTab() {

        if(project == null)
        {
            JOptionPane.showMessageDialog(this,
                    "Please open a project first.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
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

        FileNameExtensionFilter filter = new FileNameExtensionFilter("ms files", "ms");
        fileChooser.setFileFilter(filter);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
            //get the last 3 chars of the absolute path
            String fileExtension = absolutePath.substring(absolutePath.length() - 3);

            //check if the last 3 chars are .ms
            if(fileExtension.equalsIgnoreCase(".ms"))
            {
                System.out.println("chosen ms file: " + fileChooser.getSelectedFile().getAbsolutePath());

                //save current project if there is one running
                if(project != null)
                {
                    saveProject();
                }

                //read the project from the opened file
                project = Project.readProject(absolutePath);
                //clear out any old tabs first
                clearTabs();
                setTabsFromSaved();

                //set header name
                setHeaderWithName(project);

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
            System.out.println("Saving : " + project.getProjectName());
            project.writeProject(project.getProjectName() + ".ms");
        }
    }

    private void exitProgramAutoSave()
    {
        //check if project is open
        if(project != null)
        {
            //save it
            project.writeProject(project.getProjectName() + ".ms");
        }
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public Project getProject()
    {
        return project;
    }

    public void save(Project project)
    {
        if(project != null)
        {
            project.writeProject(project.getProjectName() + ".ms");
        }
    }

    public void setHeaderWithName(Project project)
    {
        setTitle("Metrics Suite - " + project.getProjectName());
    }

    public void clearTabs()
    {
        tabbedPane.removeAll();
    }

    public static void main(String []args) {
        new Menu();

    }
}
