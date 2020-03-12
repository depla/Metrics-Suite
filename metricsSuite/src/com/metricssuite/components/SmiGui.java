package com.metricssuite.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SmiGui extends JPanel
{
    //constant strings for header and buttons
    private static final String SMI_HEADER = "Software Maturity Index";
    private static final String ADD_ROW = "Add row";
    private static final String COMPUTE_INDEX = "Compute Index";

    //reference to a table
    private DefaultTableModel smiTable;

    private JLabel smiHeaderLabel;
    private JButton addRowButton;
    private JButton computeIndexButton;

    public SmiGui()
    {
        //instantiate components
        smiHeaderLabel = new JLabel(SMI_HEADER);
        addRowButton = new JButton(ADD_ROW);
        computeIndexButton = new JButton(COMPUTE_INDEX);

        add(smiHeaderLabel);
        add(addRowButton);
        add(computeIndexButton);

    }

}
