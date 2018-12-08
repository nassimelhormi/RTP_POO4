package com.company;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;

public class ActionWindow extends AbstractAction {

    private Window window;
    private JPanel panel;

    public ActionWindow(Window window, JPanel panel, String texte) {
        super(texte);

        this.window = window;
        this.panel = panel;
    }

    private void ClearWindow(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox<String> combo = new JComboBox<String>();
        AbstractStaticSingleton singleton = null;
        UserDao userDao = new UserDaoImpl();
        String textLogin = window.getTextLogin().getText();
        String textPassword = window.getTextPassword().getText();
        if (textLogin == null || textLogin.isEmpty() || textPassword == null || textPassword.isEmpty()) {
            window.getLabel().setText("Those fields could not be empty !");
        }

        // login
        try {
            if (userDao.signinByLogin(textLogin, textPassword)) {

                this.ClearWindow(this.panel); // clear panel
                combo.setPreferredSize(new Dimension(100, 20));
                combo.addItem("France");
                combo.addItem("England");
                combo.addItemListener(new ItemState(textLogin, panel));

                if (userDao.getCountry(textLogin).equals("FRANCE"))
                {
                    singleton = SingletonFactory.GetFactory("csv");
                }
                else if (userDao.getCountry(textLogin).equals("ENGLAND"))
                {
                    singleton = SingletonFactory.GetFactory("xml");
                }
                else if (userDao.getCountry(textLogin).equals("USA"))
                {
                    singleton = SingletonFactory.GetFactory("json");
                }

                singleton.ReadAllFile(panel);
                if (userDao.getRole(textLogin).equals("CADRE"))
                {
                    JTable table = singleton.GetTable();
                    JLabel totalWeight = new JLabel("Poids total : " + singleton.GetTotalWeight(table));
                    JLabel totalCut = new JLabel("Taille total : " + singleton.GetTotalCut(table));
                    JLabel imcTotal = new JLabel("IMC : " + singleton.CalculTotalImc(singleton.GetTotalWeight(table), singleton.GetTotalCut(table)));
                    panel.add(totalWeight);
                    panel.add(totalCut);
                    panel.add(imcTotal);
                }

                this.panel.add(combo);
            } else {
                window.getLabel().setText("Wrong login or password");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}