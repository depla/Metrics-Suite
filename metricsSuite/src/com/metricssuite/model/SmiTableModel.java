package com.metricssuite.model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class SmiTableModel extends AbstractTableModel {

    private Vector<String> COLUMN_NAMES = new Vector<>();
    private Vector<Vector<String>> smi;
    private JPanel jPanel;

    public SmiTableModel(JPanel j, Vector<Vector<String>> smi){
        COLUMN_NAMES.add("SMI");
        COLUMN_NAMES.add("Modules Added");
        COLUMN_NAMES.add("Modules Changed");
        COLUMN_NAMES.add("Modules Deleted");
        COLUMN_NAMES.add("Total Modules");
        this.smi = smi;
        this.jPanel = j;
    }

    public Vector<String> getCOLUMN_NAMES() {
        return COLUMN_NAMES;
    }

    public void setCOLUMN_NAMES(Vector<String> COLUMN_NAMES) {
        this.COLUMN_NAMES = COLUMN_NAMES;
    }

    public Vector<Vector<String>> getSmi() {
        return smi;
    }

    @Override
    public int getRowCount() {
        return smi.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.size();
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return smi.get(rowIndex).get(columnIndex);
    }
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    public String getColumnName(int col) {
        return getCOLUMN_NAMES().get(col);
    }

    public void setValueAt(Object value, int row, int col) {

        try {
            String valS = (String)value;
            Integer valI = Integer.parseInt(valS);
            smi.get(row).set(col, String.valueOf(valI));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(jPanel,
                    "Must be an integer.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public boolean isCellEditable(int row, int col) {

        if(row < smi.size()-1)
            return false;

        return true;
    }


    public void addRow(Vector<String> data) {
        smi.add(data);
        fireTableDataChanged();
    }

}
