package com.company;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public ArrayList<User> getAllUsers() throws SQLException
    {
        ResultSet result = this .connect
                .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                ).executeQuery(
                        "SELECT * FROM users WHERE 1 = 1"
                );

        ArrayList<User> users = new ArrayList<>();
        while(result.next())
        {
            User user = new User(
                    result.getString("login"),
                    result.getString("password"),
                    result.getString("country"),
                    result.getString("role")
            );
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {

        ResultSet result = this .connect
                .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                ).executeQuery(
                        "SELECT * FROM users WHERE id_user = " + id
                );

        User user = null;
        if(result.next())
        {
            user = new User(
                    result.getString("login"),
                    result.getString("password"),
                    result.getString("country"),
                    result.getString("role")
            );
        }
        return user;
    }

    @Override
    public boolean getUserByLogin(String login) throws SQLException {

        ResultSet result = this .connect
                .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                ).executeQuery(
                        String.format("SELECT * FROM users WHERE login = '%s'", login)
                );

        boolean res = false;
        if(result.next())
        {
            res = true;
        }
        return res;
    }

    @Override
    public void createUser(User user) throws SQLException
    {
        PreparedStatement prepare = this.connect.
                prepareStatement("INSERT INTO users (login, password, country, role) VALUES (?, ?, ?, ?)");
        prepare.setString (1, user.getLogin());
        prepare.setString   (2, user.getPassword());
        prepare.setString   (3, user.getCountry());
        prepare.setString   (4, user.getRole());

        System.out.println(user.getLogin());
        System.out.println(user.getRole());
        prepare.execute();
    }

    @Override
    public String getCountry(String login) throws SQLException
    {
        String country = null;
        ResultSet result = null;
        try {
            result = this .connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            String.format("SELECT country FROM users WHERE login = '%s'", login)
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result.next())
        {
            country = result.getString("country");
        }

        return country;
    }

    @Override
    public String getRole(String login) throws SQLException
    {
        String country = null;
        ResultSet result = null;
        try {
            result = this .connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            String.format("SELECT role FROM users WHERE login = '%s'", login)
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result.next())
        {
            country = result.getString("role");
        }

        return country;
    }

    @Override
    public boolean signinByLogin(String login, String password) throws SQLException
    {
        ResultSet result = this .connect
                .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                ).executeQuery(
                        String.format("SELECT * FROM users WHERE login = '%s' AND password = '%s'", login, password)
                );

        return result.next();
    }
}
