
package com.polar.fitness.solutions.mobileapp.Models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String password;
    private String username;
    private String email;
    private String street;
    private String zip_code;
    private String area;
    private int phone_number;
    private int nif;

    private String gender;

    private String subscription;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }


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
    public User(int id, String username, String email, String street, String zip_code, String area, int phone_number, int nif){
        this.id = id;
        this.username =  username;
        this.email = email;
        this.street = street;
        this.zip_code = zip_code;
        this.area = area;
        this.phone_number = phone_number;
        this.nif = nif;
    }

    public User(String username, String email,String password, String street, String zip_code, String area, int phone_number, int nif, String gender){
        this.username =  username;
        this.email = email;
        this.password = password;
        this.street = street;
        this.zip_code = zip_code;
        this.area = area;
        this.phone_number = phone_number;
        this.nif = nif;
        this.gender = gender;
    }
    //construtor de informacao de login
    public User(int id, String username, String email, String street, String zip_code, String area, int phone_number, int nif, String gender, String subscription){
        this.id = id;
        this.username =  username;
        this.email = email;
        this.street = street;
        this.zip_code = zip_code;
        this.area = area;
        this.phone_number = phone_number;
        this.nif = nif;
        this.gender = gender;
        this.subscription = subscription;
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

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
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
