package com.metricssuite.components;

import com.metricssuite.model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectWindow extends JFrame implements ActionListener
{
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 300;
    private static final String TITLE = "New Project";
    private static final String HEADER_TITLE = "CECS 543 Metrics Suite New Project";
    private static final String PROJECT_NAME_LABEL = "Project Name:";
    private static final String PRODUCT_NAME_LABEL = "Product Name:";
    private static final String CREATOR_NAME_LABEL = "Creator Name:";
    private static final String COMMENTS_LABEL = "Comments:";
    private static final String OK_BUTTON_TEXT = "Ok";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";

    private boolean newProjectStatus = false;
    private Project project = null;

    //main panel that holds everything
    private JPanel mainPanel;

    //components for the header
    private JLabel headerLabel;
    private JPanel headerPanel;

    //components for the 3 input fields, project name, product name, and creator
    private JLabel projectNameLabel;
    private JLabel productNameLabel;
    private JLabel creatorNameLabel;
    private JTextField projectNameTextField;
    private JTextField productNameTextField;
    private JTextField creatorNameTextField;
    private JPanel inputPanel;

    //components for the comment section
    private JLabel commentsLabel;
    private JTextArea commentsTextArea;
    private JScrollPane commentsScrollPane;
    private JPanel commentsPanel;

    //components for buttons
    private JButton okButton;
    private JButton cancelButton;
    private JPanel buttonPanel;

    public NewProjectWindow(JFrame mainFrame)
    {
        super(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new BorderLayout());

        //set up main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        add(mainPanel);

        //set up header
        headerLabel = new JLabel(HEADER_TITLE);
        headerPanel = new JPanel();
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel);

        //set up input fields
        projectNameLabel = new JLabel(PROJECT_NAME_LABEL);
        productNameLabel = new JLabel(PRODUCT_NAME_LABEL);
        creatorNameLabel = new JLabel(CREATOR_NAME_LABEL);
        projectNameLabel.setHorizontalAlignment(JLabel.CENTER);
        productNameLabel.setHorizontalAlignment(JLabel.CENTER);
        creatorNameLabel.setHorizontalAlignment(JLabel.CENTER);
        projectNameTextField = new JTextField();
        productNameTextField = new JTextField();
        creatorNameTextField = new JTextField();
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(projectNameLabel);
        inputPanel.add(projectNameTextField);
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameTextField);
        inputPanel.add(creatorNameLabel);
        inputPanel.add(creatorNameTextField);
        mainPanel.add(inputPanel);

        //set up the comment section
        commentsLabel = new JLabel(COMMENTS_LABEL);
        commentsTextArea = new JTextArea();
        commentsTextArea.setLineWrap(true);
        commentsScrollPane = new JScrollPane(commentsTextArea);
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new GridLayout(2, 1));
        commentsPanel.add(commentsLabel);
        commentsPanel.add(commentsScrollPane);
        mainPanel.add(commentsPanel);

        //set up the buttons
        okButton = new JButton(OK_BUTTON_TEXT);
        okButton.addActionListener(this);
        cancelButton = new JButton(CANCEL_BUTTON_TEXT);
        cancelButton.addActionListener(this);
        buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        setLocationRelativeTo(mainFrame);

        setVisible(true);

    }

    public boolean getNewProjectStatus()
    {
        return newProjectStatus;
    }

    public Project getProject()
    {
        return project;
    }

    public void actionPerformed(ActionEvent e)
    {
        String eventText = e.getActionCommand();

        switch(eventText)
        {
            case OK_BUTTON_TEXT:
                System.out.println(OK_BUTTON_TEXT);
                if(checkTextFieldsContents())
                {
                    createNewProject();
                    newProjectStatus = true;
                    System.out.println(project.toString());
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Please enter all the name fields.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case CANCEL_BUTTON_TEXT:
                System.out.println(CANCEL_BUTTON_TEXT);
                cancelNewProject();
                newProjectStatus = false;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + eventText);
        }
    }

    private boolean checkTextFieldsContents()
    {
        if(projectNameTextField.getText().isEmpty() ||
            productNameTextField.getText().isEmpty() ||
            creatorNameTextField.getText().isEmpty())
        {
            return false;
        }

        return true;
    }

    private void createNewProject()
    {
        project = new Project();

        project.setProjectName(projectNameTextField.getText());
        project.setProductName(productNameTextField.getText());
        project.setCreatorName(creatorNameTextField.getText());
        project.setComments(commentsTextArea.getText());

        //create a ms file
        String fileName = projectNameTextField.getText() + ".ms";

        project.writeProject(fileName);

        this.setVisible(false);
    }

    private void cancelNewProject()
    {
        dispose();
    }
}
