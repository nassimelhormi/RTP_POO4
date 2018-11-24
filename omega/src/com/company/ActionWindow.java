package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ActionWindow extends AbstractAction {

    private Window window;
    private JPanel panel;

    public ActionWindow(Window window, JPanel panel, String texte)
    {
        super(texte);

        this.window = window;
        this.panel = panel;
    }

    private void ClearWindow(JPanel panel)
    {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        UserDao userDao = new UserDaoImpl();
        Csv csvFile = new Csv();
        String textLogin = window.getTextLogin().getText();
        String textPassword = window.getTextPassword().getText();

        if (textLogin == null || textLogin.isEmpty() || textPassword == null || textPassword.isEmpty())
        {
            window.getLabel().setText("Those fields could not be empty !");
        }

        // login
        try
        {
            if (userDao.signinByLogin(textLogin, textPassword))
            {
                this.ClearWindow(this.panel); // clear panel
                if (userDao.getCountry(textLogin).equals("FRANCE"))
                {
                    csvFile.ReadAllFile(panel);

                    if (userDao.getRole(textLogin).equals("CADRE"))
                    {
                        JTable table = csvFile.GetTable();
                        JLabel totalWeight = new JLabel("Poids total : " + csvFile.GetTotalWeight(table));
                        JLabel totalCut = new JLabel("Taille total : " + csvFile.GetTotalCut(table));
                        JLabel imcTotal = new JLabel("IMC : " + csvFile.CalculTotalImc(csvFile.GetTotalWeight(table), csvFile.GetTotalCut(table)));
                        panel.add(totalWeight);
                        panel.add(totalCut);
                        panel.add(imcTotal);
                    }
                }

            }
            else
            {
                window.getLabel().setText("Wrong login or password");
            }
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
    }
}