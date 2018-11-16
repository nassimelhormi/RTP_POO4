package com.company;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.AbstractAction;

public class ActionWindow extends AbstractAction {

    private Window window;

    public ActionWindow(Window window, String texte)
    {
        super(texte);

        this.window = window;
    }

    public void actionPerformed(ActionEvent e)
    {
        UserDao userDao = new UserDaoImpl();
        String textLogin = window.getTextLogin().getText();
        String textPassword = window.getTextPassword().getText();

        if (textLogin == null || textLogin.isEmpty() || textPassword == null || textPassword.isEmpty())
        {
            window.getLabel().setText("Those fields could not be empty !");
        }

        // login
        try {
            if (userDao.signinByLogin(textLogin, textPassword))
            {
                window.getLabel().setText("Ok go next");
            }
            else
            {
                window.getLabel().setText("Wrong login or password");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}