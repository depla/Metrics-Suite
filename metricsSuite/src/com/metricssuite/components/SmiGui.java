package com.metricssuite.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SmiGui extends JPanel
{
    //constant strings for header and buttons
    private static final String SMI_HEADER = "Software Maturity Index";
    private static final String ADD_ROW = "Add row";
    private static final String COMPUTE_INDEX = "Compute Index";
    private static final String [] COLUMN_NAMES = {"SMI", "Modules Added", "Modules Changed", "Modules Deleted",
            "Total Modules"};

    //reference to a table
    private DefaultTableModel mPassedSmiDefaultTableModel;

    //components
    private JLabel smiHeaderLabel;
    private JTable smiJTable;
    private JScrollPane smiJScrollPane;
    private JButton addRowButton;
    private JButton computeIndexButton;

    public SmiGui(DefaultTableModel passedDefaultTable)
    {
        //connect the passed smi table to the reference in this class
        this.mPassedSmiDefaultTableModel = passedDefaultTable;

        //instantiate components
        smiHeaderLabel = new JLabel(SMI_HEADER);

        //set the names of the columns

        //***********************************************************************
        // this line causes the DefaultTableModel in Project to not be serializable anymore
        // causes NotSerializableException

        //mPassedSmiDefaultTableModel.setColumnIdentifiers(COLUMN_NAMES);
        //***********************************************************************

        smiJTable = new JTable(mPassedSmiDefaultTableModel);

        //make table visible
        smiJTable.setPreferredScrollableViewportSize(new Dimension(450, 320));
        smiJTable.setFillsViewportHeight(true);

        smiJScrollPane = new JScrollPane(smiJTable);
        addRowButton = new JButton(ADD_ROW);
        computeIndexButton = new JButton(COMPUTE_INDEX);


        add(smiHeaderLabel);
        add(smiJScrollPane);
        add(addRowButton);
        add(computeIndexButton);

    }

}
