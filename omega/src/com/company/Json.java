package com.company;

import netscape.javascript.JSObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.json.*;

public class Json extends AbstractStaticSingleton  {

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
        String filePath = "ressources/personnesJson.json";
        File file = new File(filePath);

        return file;
    }

    @Override
    public void ReadAllFile(JPanel panel) throws IOException
    {
        JTable table = new JTable();

        String[] st = new String[8];
        String header[] = {"idPersonne", "Prenom", "Nom", "Poids", "Taille", "Rue", "Ville", "Code Postal"};
        try {
            BufferedReader br = new BufferedReader(new FileReader("ressources/personnesJson.json"));

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setColumnIdentifiers(header);

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
                line = line.replace("[", "").replace("]", "");
                JSONObject obj = new JSONObject(line);
                st[0] = String.valueOf(obj.getInt("id"));
                st[1] = obj.getString("prenom");
                st[2] = obj.getString("nom");
                st[3] = String.valueOf(obj.getDouble("poids"));
                st[4] = String.valueOf(obj.getDouble("taille"));
                st[5] = obj.getString("rue");
                st[6] = obj.getString("ville");
                st[7] = obj.getString("codePostal");
                model.addRow(st);
            }
        } catch(Exception e) {
            e.printStackTrace();
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
