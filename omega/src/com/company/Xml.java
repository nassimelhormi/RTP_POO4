package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Xml extends AbstractStaticSingleton {

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
        File xmlFile = new File("ressources/personnesXml.xml");

        return xmlFile;
    }

    @Override
    public void ReadAllFile(JPanel panel) throws IOException {
        File xmlFile = this.LoadPath();
        JTable table = new JTable();
        int id = 1;
        String[] st = new String[8];
        String header[] = {"idPersonne", "Prenom", "Nom", "Poids", "Taille", "Rue", "Ville", "Code Postal"};

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;

        try {
            doc = dBuilder.parse(xmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(header);
        NodeList nList = doc.getElementsByTagName("personne");
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                st[0] = String.valueOf(id);
                st[1] = eElement.getElementsByTagName("prenom").item(0).getTextContent();
                st[2] = eElement.getElementsByTagName("nom").item(0).getTextContent();
                st[3] = eElement.getElementsByTagName("poids").item(0).getTextContent();
                st[4] = eElement.getElementsByTagName("taille").item(0).getTextContent();
                st[5] = eElement.getElementsByTagName("rue").item(0).getTextContent();
                st[6] = eElement.getElementsByTagName("ville").item(0).getTextContent();
                st[7] = eElement.getElementsByTagName("codePostal").item(0).getTextContent();
                model.addRow(st);
            }
            id++;
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
