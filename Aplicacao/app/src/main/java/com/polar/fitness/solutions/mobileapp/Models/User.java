
package com.polar.fitness.solutions.mobileapp.Models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String street;
    private int zip_code;
    private String area;
    private int phone_number;
    private int nif;



    private enum gender{
        Masculino,
        Feminimo,
        Outro
    }
    private enum subscription{
        Inativo,
        Ativo
    }

    //Construtor
    public User(int id, String username, String email, String street, int zip_code, String area, int phone_number, int nif){
        this.id = id;
        this.username =  username;
        this.email = email;
        this.street = street;
        this.zip_code = zip_code;
        this.area = area;
        this.phone_number = phone_number;
        this.nif = nif;
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }
}
