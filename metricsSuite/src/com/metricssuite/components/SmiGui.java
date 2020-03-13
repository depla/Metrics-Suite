package com.metricssuite.components;

import com.metricssuite.model.Project;
import com.metricssuite.model.SmiTableModel;

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
    //reference to a table
    private SmiTableModel mPassedSmiDefaultTableModel;

    //components
    private JLabel smiHeaderLabel;
    private JTable smiJTable;
    private JScrollPane smiJScrollPane;
    private JButton addRowButton;
    private JButton computeIndexButton;

    public SmiGui(Project project)
    {
        mPassedSmiDefaultTableModel = new SmiTableModel(project.getSMI());

        //instantiate components
        smiHeaderLabel = new JLabel(SMI_HEADER);

        smiJTable = new JTable(mPassedSmiDefaultTableModel);
        smiJTable.setDefaultEditor(Integer.class, new IntegerEditor(0, Integer.MAX_VALUE));

        //make table visible
        smiJTable.setPreferredScrollableViewportSize(new Dimension(450, 320));
        smiJTable.setFillsViewportHeight(true);

        smiJScrollPane = new JScrollPane(smiJTable);

        addRowButton = new JButton(ADD_ROW);
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vector<Integer> data = new Vector<>();
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
