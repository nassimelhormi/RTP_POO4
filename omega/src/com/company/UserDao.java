package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

    public Connection connect = ConnectionDatabase.getInstance();
    public ArrayList<User> getAllUsers() throws SQLException;
    public User getUserById(int id) throws SQLException;
    public User getUserByLogin(String login) throws SQLException;
    public void createUser(User user) throws SQLException;
    public boolean signinByLogin(String login, String password) throws SQLException;
}
