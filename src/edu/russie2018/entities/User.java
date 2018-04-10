/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

/**
 *
 * @author Jawhar
 */
public class User {
    private int id;
    private String User_name;
    private int Enabled;
    private String Email;
    private String Password;
    private String Last_login;
    private String Confirmation_token;
    private String Password_request_at;
    private String Roles;
    private String First_name;
    private int salt;
    private String Last_name;
    private int Likes;

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    private int dislikes;
    private int Solde;

    public User(int id, String User_name, int Enabled, String Email, String Password, String Last_login, String Confirmation_token, String Password_request_at, String Roles, String First_name, String Last_name, String Preferance, int Likes, int dislikes, int Solde) {
        this.id = id;
        this.User_name = User_name;
        this.Enabled = Enabled;
        this.Email = Email;
        this.Password = Password;
        this.Last_login = Last_login;
        this.Confirmation_token = Confirmation_token;
        this.Password_request_at = Password_request_at;
        this.Roles = Roles;
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.Likes = Likes;
        this.dislikes = dislikes;
        this.Solde = Solde;
    }

    public User() {
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", Nom=" + User_name + ", Enabled=" + Enabled + ", Email=" + Email + ", Password=" + Password + ", Last_login=" + Last_login + ", Confirmation_token=" + Confirmation_token + ", Password_request_at=" + Password_request_at + ", Roles=" + Roles + ", First_name=" + First_name + ", Last_name=" + Last_name + ", Likes=" + Likes + ", dislikes=" + dislikes  + ", solde=" + Solde + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String Nom) {
        this.User_name = Nom;
    }

    public int getEnabled() {
        return Enabled;
    }

    public void setEnabled(int Enabled) {
        this.Enabled = Enabled;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getLast_login() {
        return Last_login;
    }

    public void setLast_login(String Last_login) {
        this.Last_login = Last_login;
    }

    public String getConfirmation_token() {
        return Confirmation_token;
    }

    public void setConfirmation_token(String Confirmation_token) {
        this.Confirmation_token = Confirmation_token;
    }

    public String getPassword_request_at() {
        return Password_request_at;
    }

    public void setPassword_request_at(String Password_request_at) {
        this.Password_request_at = Password_request_at;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String Roles) {
        this.Roles = Roles;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String First_name) {
        this.First_name = First_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String Last_name) {
        this.Last_name = Last_name;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int Likes) {
        this.Likes = Likes;
    }

    public int getdislikes() {
        return dislikes;
    }

    public void setdislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getSolde() {
        return Solde;
    }

    public void setSolde(int Solde) {
        this.Solde = Solde;
    }
    
}
