package com.metricssuite.components;

import javax.swing.*;
import java.awt.*;

public class HalMcMetricGui extends JPanel
{
    private JTextArea parseTextArea;
    private JScrollPane parseScrollPane;

    public HalMcMetricGui(String parsedData)
    {
        setLayout(new BorderLayout());

        parseTextArea = new JTextArea(parsedData);
        parseTextArea.setLineWrap(true);
        parseTextArea.setEditable(false);
        parseScrollPane = new JScrollPane(parseTextArea);

        add(parseScrollPane, BorderLayout.CENTER);
    }

}
