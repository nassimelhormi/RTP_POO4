package com.company;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {

    private JTextField textLogin;
    private JPasswordField textPassword;
    private JLabel label;

    public Window(){
        super();

        build();//On initialise notre fenêtre
    }

    private void build(){
        setTitle("Omega by big_ls"); //On donne un titre à l'application
        setSize(400,400); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(true); //On permet le redimensionnement
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        textLogin = new JTextField();
        textLogin.setColumns(10);

        textPassword = new JPasswordField();
        textPassword.setColumns(10);

        panel.add(textLogin);
        panel.add(textPassword);

        label = new JLabel("Rien pour le moment");

        panel.add(label);

        JButton bouton = new JButton(new ActionWindow(this, "Sign In"));

        panel.add(bouton);

        return panel;
    }

    public JTextField getTextLogin(){
        return textLogin;
    }

    public JPasswordField getTextPassword(){
        return textPassword;
    }

    public JLabel getLabel(){
        return label;
    }
}
