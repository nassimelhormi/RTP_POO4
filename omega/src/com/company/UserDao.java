package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

    public Connection connect = ConnectionDatabase.getInstance();
    public ArrayList<User> getAllUsers() throws SQLException;
    public User getUserById(int id) throws SQLException;
    public boolean getUserByLogin(String login) throws SQLException;
    public void createUser(User user) throws SQLException;
    public String getCountry(String login) throws SQLException;
    public String getRole(String login) throws SQLException;
    public boolean signinByLogin(String login, String password) throws SQLException;
}
