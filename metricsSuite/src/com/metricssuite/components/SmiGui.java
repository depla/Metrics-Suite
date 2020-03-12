package com.metricssuite.components;

import com.metricssuite.model.Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SmiGui extends JPanel
{
    //constant strings for header and buttons
    private static final String SMI_HEADER = "Software Maturity Index";
    private static final String ADD_ROW = "Add row";
    private static final String COMPUTE_INDEX = "Compute Index";
    private static final Vector<String> COLUMN_NAMES = new Vector<>();
    //reference to a table
    private DefaultTableModel mPassedSmiDefaultTableModel;

    //components
    private JLabel smiHeaderLabel;
    private JTable smiJTable;
    private JScrollPane smiJScrollPane;
    private JButton addRowButton;
    private JButton computeIndexButton;

    public SmiGui(Project project)
    {
        COLUMN_NAMES.add("SMI");
        COLUMN_NAMES.add("Modules Added");
        COLUMN_NAMES.add("Modules Changed");
        COLUMN_NAMES.add("Modules Deleted");
        COLUMN_NAMES.add("Total Modules");


        //instantiate components
        smiHeaderLabel = new JLabel(SMI_HEADER);

        //***********************************************************************
        // this line causes the DefaultTableModel in Project to not be serializable anymore
        // causes NotSerializableException

        //mPassedSmiDefaultTableModel.setColumnIdentifiers(COLUMN_NAMES);
        //***********************************************************************
        smiJTable = new JTable();

       mPassedSmiDefaultTableModel = (DefaultTableModel) smiJTable.getModel();

       if(project.getSMI() == null) {
           project.setSMI(mPassedSmiDefaultTableModel.getDataVector());
           mPassedSmiDefaultTableModel.setColumnIdentifiers(COLUMN_NAMES);
       }else{
           mPassedSmiDefaultTableModel.setDataVector(project.getSMI(), COLUMN_NAMES);
       }

        //set the names of the columns


        //make table visible
        smiJTable.setPreferredScrollableViewportSize(new Dimension(450, 320));
        smiJTable.setFillsViewportHeight(true);

        smiJScrollPane = new JScrollPane(smiJTable);

        addRowButton = new JButton(ADD_ROW);
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vector<Object> data = new Vector<>();
                data.add(0);
                data.add(0);
                data.add(0);
                data.add(0);
                data.add(0);
                mPassedSmiDefaultTableModel.addRow(data);

            }
        });

        computeIndexButton = new JButton(COMPUTE_INDEX);
        computeIndexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        add(smiHeaderLabel);
        add(smiJScrollPane);
        add(addRowButton);
        add(computeIndexButton);

    }

}
