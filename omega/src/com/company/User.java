package com.company;

public class User {

    private String  email;
    private String  login;
    private String  password;
    private int     id;
    private int     id_country;

    // Constructeur
    User(String login, String email, String password, int id_country)
    {
        this.email = email;
        this.login = login;
        this.password = password;
        this.id_country = id_country;
    }

    // Getter
    public String getEmail()
    {
        return email;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
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
    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.login = password;
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
