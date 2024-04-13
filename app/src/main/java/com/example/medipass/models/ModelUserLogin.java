package com.example.medipass.models;

public class ModelUserLogin {
    private String id;
    private String prenom;
    private String nom;
    private String mail;
    private String pwd;
    private String date;
    private String numero;
    private String otherNumero;
    private String pays;
    private String ville;
    private String quartier;
    private String adress;
    private String photo;

    public ModelUserLogin() {
    }

    public ModelUserLogin(String id, String prenom, String nom, String mail, String pwd, String date,String numero,
                          String otherNumero, String pays, String ville, String quartier,String adress,String photo) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.pwd = pwd;
        this.date = date;
        this.numero = numero;
        this.otherNumero = otherNumero;
        this.pays = pays;
        this.ville = ville;
        this.quartier = quartier;
        this.adress = adress;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOtherNumero() {
        return otherNumero;
    }

    public void setOtherNumero(String otherNumero) {
        this.otherNumero = otherNumero;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
