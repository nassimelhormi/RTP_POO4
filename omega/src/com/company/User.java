package com.company;

public class User {

    private String  email;
    private String  login;
    private String  password;
    private int     id;
    private int     id_country;
    public String   country;
    public String   role;

    // Constructeur
    User(String login, String password, String country, String role)
    {
        this.login = login;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    // Getter

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getCountry()
    {
        return country;
    }

    public String getRole()
    {
        return role;
    }

    public int getId()
    {
        return id;
    }

    public int getIdCountry()
    {
        return id_country;
    }


    // Setter

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setIdCountry(int id_country)
    {
        this.id_country = id_country;
    }

}
