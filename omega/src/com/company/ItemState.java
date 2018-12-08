package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;

class ItemState implements ItemListener
{
    private AbstractStaticSingleton singleton;
    private String textLogin;
    private JPanel panel;

    public ItemState(String textLogin, JPanel panel) {
        this.textLogin = textLogin;
        if (panel != null) { System.out.println("Panel n'est pas NULL"); }
        this.panel = panel;
    }

    public void itemStateChanged(ItemEvent e)
    {
        this.singleton = null;

        AbstractStaticSingleton singleton = null;
        UserDao userDao = new UserDaoImpl();
        System.out.println("Login : " + textLogin);
        System.out.println("événement déclenché sur : " + e.getItem());
        try {
            if (userDao.getCountry(textLogin).equals("FRANCE"))
            {
                System.out.println("L'utilisateur est en France");
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
            singleton.ReadAllFile(this.panel);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//        try {
//            if (userDao.getRole(textLogin).equals("CADRE"))
//            {
//                JTable table = csv.GetTable();
//                JLabel totalWeight = new JLabel("Poids total : " + csv.GetTotalWeight(table));
//                JLabel totalCut = new JLabel("Taille total : " + csv.GetTotalCut(table));
//                JLabel imcTotal = new JLabel("IMC : " + csv.CalculTotalImc(csv.GetTotalWeight(table), csv.GetTotalCut(table)));
//                this.panel.add(totalWeight);
//                this.panel.add(totalCut);
//                this.panel.add(imcTotal);
//            }
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        }
    }

    public AbstractStaticSingleton GetSingleton()
    {
        return this.singleton;
    }
}