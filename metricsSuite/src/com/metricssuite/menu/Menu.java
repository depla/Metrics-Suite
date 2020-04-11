package com.metricssuite.menu;

import com.metricssuite.antlr.JavaMetrics;
import com.metricssuite.antlr.MetricsParser;
import com.metricssuite.components.*;
import com.metricssuite.model.FunctionPoint;
import com.metricssuite.model.Project;
import org.antlr.runtime.RecognitionException;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Menu extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane;
    private NewProjectWindow projectWindow;
    private Project project;
    private languageSelection language;
    private FunctionPointGui functionPoint;
    private ArrayList openedTab = new ArrayList<>();;
    //private static VAF  v;

    public Menu() {
        getContentPane().setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        getContentPane().add(BorderLayout.CENTER, tabbedPane);
        this.setJMenuBar(createMenuBar());

        setTitle("CECS 543 Metrics Suite");
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 500));

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                checkSaveOnExit();
            }
        });

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
        JMenuItem languages = new JMenuItem("Languages");
        languages.addActionListener(this);
        preferences.add(languages);

        JMenu metrics = new JMenu("Metrics"); //create metrics menu and items
        JMenuItem functionPoints = new JMenuItem("Function Points");
        JMenuItem smi = new JMenuItem("SMI");
        functionPoints.addActionListener(this);
        smi.addActionListener(this);
        metrics.add(functionPoints);
        metrics.add(smi);

        JMenu projectCode = new JMenu("Project Code");
        JMenuItem addCode = new JMenuItem("Add code");
        JMenuItem projectCodeStatistics = new JMenuItem("Project code statistics");
        addCode.addActionListener(this);
        projectCodeStatistics.addActionListener(this);
        projectCode.add(addCode);
        projectCode.add(projectCodeStatistics);

        JMenu help = new JMenu("Help"); //create help menu and items

        menuBar.add(file);  //add menus to menubar
        menuBar.add(edit);
        menuBar.add(preferences);
        menuBar.add(metrics);
        menuBar.add(projectCode);
        menuBar.add(help);

        file.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("File");

                if(project == null)
                {
                    save.setEnabled(false);
                }
                else
                {
                    save.setEnabled(true);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        metrics.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("Metrics");

                if(project == null)
                {
                    functionPoints.setEnabled(false);
                    smi.setEnabled(false);
                }
                else
                {
                    functionPoints.setEnabled(true);
                    smi.setEnabled(true);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        projectCode.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("Project Code");

                if(project == null)
                {
                    addCode.setEnabled(false);
                    projectCodeStatistics.setEnabled(false);
                }
                else
                {
                    addCode.setEnabled(true);

                    if(project.getSelectedFiles().size() != 0)
                    {
                        projectCodeStatistics.setEnabled(true);
                    }
                    else
                    {
                        projectCodeStatistics.setEnabled(false);
                    }
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        return menuBar;
    }
    public void actionPerformed(ActionEvent e) {

        String i = e.getActionCommand();
        switch (i){
            case "New":
                System.out.println("New Project");
                //check if there is a project open or not
                //there is not one open yet
                if(project == null)
                {
                    //create a new project
                    Project project = new Project();
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
                try {
                    createFileChooser();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (RecognitionException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Save":
                System.out.println("Save");
                saveProject();
                //System.out.println(project.getSMI().size());
                break;
            case "Languages":

                language.setVisibility(true);
                break;
            
            case "Function Points":
                createTab();
                //language.setLang("");
                break;

            case "SMI":
                System.out.println("SMI");
                createSmiTab();
                break;

            case "Add code":
                System.out.println("Add code");
                projectCodeFileChooser();
                break;

            case "Project code statistics":
                System.out.println("Project code statistics");
                try {
                    projectCodeStatistics();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (RecognitionException ex) {
                    ex.printStackTrace();
                }
                break;

            case "Exit":
                System.out.println("Exit");
                checkSaveOnExit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }

    private void projectCodeStatistics() throws IOException, RecognitionException {
        System.out.println(project.getSelectedFiles().toString());

        createHalMcMetricsTabs(project.getSelectedFiles());
    }

    private void projectCodeFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        File currentDirectory = new File(System.getProperty("user.dir"));

        fileChooser.setCurrentDirectory(currentDirectory);
        fileChooser.setDialogTitle("Choose a file(s) to open");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("java files", "java");
        fileChooser.setFileFilter(filter);

        File [] selectedFiles;

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            selectedFiles = fileChooser.getSelectedFiles();

            //prints out the absolute paths of the selected files
            System.out.println(Arrays.toString(selectedFiles));

            ArrayList<File> fileArrayList = new ArrayList<File>();
            Collections.addAll(fileArrayList, selectedFiles);

            //add the selected files into the project
            project.addSelectedFiles(fileArrayList);
        }
    }

    private void checkSaveOnExit()
    {
        //check if changes were made
        //returns true if no changes were made
        if(checkForChanges())
        {
            //close program
            System.exit(0);
        }
        //this part means that there were changes
        else
        {
            //ask user if they want to save or discard
            int choice = createSaveChoiceDialog();

            //they chose to save
            if(choice == 1)
            {
                saveProject();
            }

            //if they chose the red x button, choice becomes -1
            //so do not exit if it is -1
            if(choice != -1)
            {
                System.exit(0);
            }
        }
    }

    private int createSaveChoiceDialog()
    {
        String[] options = {"Discard changes", "Save"};

        int choice = JOptionPane.showOptionDialog(this,
                "Would you like to save or discard your changes?",
                "Save or discard changes?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        return choice;
    }

    /**
     * Checks to see if any changes were made
     *
     * @return true if they are the same (no changes), false if there were changes
     */
    private boolean checkForChanges()
    {
        //no project open so just return true by default
        if(project == null)
        {
            return true;
        }

        File testFile = new File(project.getProjectName() + ".ms");
        boolean fileExists = testFile.exists();

        Project originalProject;

        if(fileExists)
        {
            originalProject = Project.readProject(project.getProjectName() + ".ms");
        }
        else
        {
            System.out.println("checkForChanges() : .ms file does not exist yet");
            return false;
        }

        if(originalProject.equals(project))
        {
            System.out.println("checkForChanges() : They are the same");
            return true;
        }

        System.out.println("checkForChanges() : They are NOT the same");
        return false;

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

    private void createSmiTab()
    {
        //check to see if project is open
        if(project == null)
        {
            JOptionPane.showMessageDialog(this,
                    "Please open a project first.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //check if there is an smi already in the project
        else if(project.getSMI() != null)
        {
            //project already has smi, show error message
            JOptionPane.showMessageDialog(this,
                    "Project has an SMI already.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //project does not have smi yet
        else
        {
            //create the smi
            project.createSMI();

            tabbedPane.addTab( "SMI", new SmiGui(project));
        }

    }

    private void createSmiTab(Project project)
    {
        tabbedPane.addTab("SMI", new SmiGui(project));
    }

    public void createHalMcMetricsTabs(ArrayList<File> selectedFiles) throws IOException, RecognitionException {

        String fileName;
        for(int i = 0; i < selectedFiles.size(); i++)
        {
            fileName = selectedFiles.get(i).toString();
            String currentFilename= selectedFiles.get(i).getName().replaceAll(".java","");
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            if(!openedTab.contains(currentFilename)){
                openedTab.add(currentFilename);

                MetricsParser metricParser = new MetricsParser();
                tabbedPane.addTab(fileName, new HalMcMetricGui(metricParser.parse(selectedFiles.get(i))));
            }
        }

    }

    private void setTabsFromSaved() throws IOException, RecognitionException {

        for(FunctionPoint point: project.getFunctionPointArrayList())
            createTab(point);

        //create smi tab
        if(project.getSMI() != null)
        {   System.out.println("row count from saved smi: " + project.getSMI().size());
            createSmiTab(project);
        }

        //create halstead/mccabe tabs
        if(project.getSelectedFiles() != null)
        {

            createHalMcMetricsTabs(project.getSelectedFiles());
        }
    }

    private void createFileChooser() throws IOException, RecognitionException {
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
        setTitle("CECS 543 Metrics Suite - " + project.getProjectName());
    }

    public void clearTabs()
    {
        tabbedPane.removeAll();
    }


//    private void clearTab(String tabName) {
//        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
//            String tabTitle = tabbedPane.getTitleAt(i);
//            if (tabTitle.equals(tabName)) {
//                tabbedPane.remove(i);
//                break;
//            }
//        }
//    }

    public static void main(String []args) {
        new Menu();
    }
}
