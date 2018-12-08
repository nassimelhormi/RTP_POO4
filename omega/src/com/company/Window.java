package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.SQLException;


public class Window extends JFrame {

    private JTextField textLogin;
    private JPasswordField textPassword;
    private JLabel label;
    private JComboBox<String> country;
    private JComboBox<String> role;

    public Window(){
        super();

        build();//On initialise notre fenêtre
    }

    private void build(){
        setTitle("Omega by big_ls"); //On donne un titre à l'application
        setSize(700,700); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(true); //On permet le redimensionnement
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        UserDao userDao = new UserDaoImpl();
        panel.setLayout(new FlowLayout());

        textLogin = new JTextField();
        textLogin.setColumns(10);

        textPassword = new JPasswordField();
        textPassword.setColumns(10);

        country = new JComboBox<>();
        country.addItem("FRANCE");
        country.addItem("ENGLAND");
        country.addItem("USA");

        role = new JComboBox<>();
        role.addItem("CADRE");
        role.addItem("MEDECIN");

        panel.add(textLogin);
        panel.add(textPassword);
        panel.add(country);
        panel.add(role);

        label = new JLabel("");

        panel.add(label);

        JButton signin = new JButton(new ActionWindow(this, panel,"Sign In"));
        JButton signup = new JButton("Sign Up");

        signup.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    System.out.println(textLogin.getText());
                    if (userDao.getUserByLogin(String.valueOf(textLogin.getText())))
                    {
                        label.setText("This user already exist");
                        System.out.println("User exist");
                        panel.add(label);
                    }
                    else
                    {
                        User user = new User(textLogin.getText(), textPassword.getText(), country.getSelectedItem().toString(), role.getSelectedItem().toString());
                        userDao.createUser(user);
                        label.setText("Registered");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        panel.add(signin);
        panel.add(signup);

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

