package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectWindow extends JFrame implements ActionListener
{
    private String projectName;
    private String productName;
    private String creatorName;
    private String comments;
    private boolean newProject = false;
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
        buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        setLocationRelativeTo(mainFrame);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if the button is ok --> store name
        String i = e.getActionCommand();
        System.out.println(i);
        if(i == "Ok"){
            this.setProjectName();
            this.setProductName();
            this.setCreatorName();
            newProject = true;
        }
    }
    private void setProjectName(){
        this.projectName = projectNameTextField.getText();
        System.out.println(getProjectName());
    }
    private void setProductName(){
        this.productName= productNameTextField.getText();
        System.out.println(getProductName());
    }
    private void setCreatorName(){
        this.creatorName = creatorNameTextField.getText();
        System.out.println(getCreatorName());
    }
    private void setComments(){
        this.comments = commentsTextArea.getText();
    }

    public String getProjectName(){
        return projectName;
    }
    public String getProductName(){
        return productName;
    }

    public String getCreatorName(){
        return creatorName;
    }
    public boolean isNewProject(){
        return newProject;
    }
}
