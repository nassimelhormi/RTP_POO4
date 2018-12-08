package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Csv extends AbstractStaticSingleton {

    public JTable table;

    @Override
    public JTable GetTable()
    {
        return this.table;
    }

    private void SetTable(JTable table)
    {
        this.table = table;
    }

    @Override
    public File LoadPath()
    {
        String filePath = "ressources/personnesCsv.csv";
        File file = new File(filePath);

        return file;
    }

    @Override
    public void ReadAllFile(JPanel panel) throws IOException
    {
        File file = this.LoadPath();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String firstLine = br.readLine().trim();


        String[] columnsName = firstLine.split(";");
        JTable table = new JTable();

        Object[] tableLines = br.lines().toArray();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnsName);
        for (int i = 0; i < tableLines.length; i++)
        {
            String row = tableLines[i].toString().trim();
            String[] dataRow = row.split(";");
            model.addRow(dataRow);
        }

        this.SetTable(table);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);

    }

    @Override
    public float CalculTotalImc(float weight, float cut)
    {
        return weight / (cut * cut);
    }

    @Override
    public float GetTotalWeight(JTable table)
    {
        float res = 0;
        for(int i = 0; i<table.getModel().getRowCount(); i++)
        {
            res += Float.parseFloat(table.getModel().getValueAt(i, 3).toString().trim());
        }

        return res;
    }

    @Override
    public float GetTotalCut(JTable table)
    {
        float res = 0;
        for(int i = 0; i<table.getModel().getRowCount(); i++)
        {
            res += Float.parseFloat(table.getModel().getValueAt(i, 4).toString().trim());
        }

        return res;
    }
}
