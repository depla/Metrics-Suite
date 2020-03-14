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

    //panel for buttons
    JPanel buttonPanel;

    public SmiGui(Project project)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        mPassedSmiDefaultTableModel = new SmiTableModel(this, project.getSMI());

        //instantiate components
        smiHeaderLabel = new JLabel(SMI_HEADER);

        smiJTable = new JTable(mPassedSmiDefaultTableModel);
        //smiJTable.setDefaultEditor(Integer.class, new IntegerEditor(0, Integer.MAX_VALUE));

        //make table visible
        smiJTable.setPreferredScrollableViewportSize(new Dimension(450, 320));
        smiJTable.setFillsViewportHeight(true);

        smiJScrollPane = new JScrollPane(smiJTable);

        addRowButton = new JButton(ADD_ROW);
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vector<String> data = new Vector<>();
                data.add("");
                data.add("0");
                data.add("0");
                data.add("0");
                data.add("");
                mPassedSmiDefaultTableModel.addRow(data);
                mPassedSmiDefaultTableModel.fireTableDataChanged();

            }
        });

        computeIndexButton = new JButton(COMPUTE_INDEX);
        computeIndexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int total;
                if(mPassedSmiDefaultTableModel.getSmi().size() == 1){
                    total = 0;
                }else{
                    int secontToLast = mPassedSmiDefaultTableModel.getSmi().size() - 2;
                    Vector<String> secondToLastRow = mPassedSmiDefaultTableModel.getSmi().get(secontToLast);
                    total = Integer.parseInt(secondToLastRow.get(4));
                }
                Vector<String> lastRow =  mPassedSmiDefaultTableModel.getLastRow();
                int modulesAdded = Integer.parseInt(lastRow.get(1));
                int modulesChanged = Integer.parseInt(lastRow.get(2));
                int modulesDeleted = Integer.parseInt(lastRow.get(3));
                //Integer totalModules = Integer.parseInt(lastRow.get(4));
                lastRow.set(4, String.valueOf(total + modulesAdded - modulesDeleted));

                int totalModules = Integer.parseInt(lastRow.get(4));

                double smi = ((totalModules - (modulesAdded + modulesChanged + modulesDeleted)) * 1.0)/totalModules;

                lastRow.set(0, String.valueOf(smi));
                mPassedSmiDefaultTableModel.fireTableDataChanged();


            }
        });

        smiHeaderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(smiHeaderLabel);
        add(smiJScrollPane);

        buttonPanel = new JPanel();
        buttonPanel.add(addRowButton);
        buttonPanel.add(computeIndexButton);
        add(buttonPanel);

    }

}
