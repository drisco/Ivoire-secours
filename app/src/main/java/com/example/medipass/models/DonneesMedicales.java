package com.example.medipass.models;

public class DonneesMedicales {
    private String id;
    private String nomSecoureur;
    private String prenomSecoureur;
    private String numeroSecoureur;
    private String autreNumeroSecoureur;
    private String relationAvecSecoureur;
    private String sexeVictime;
    private String dateNaissanceVictime;
    private String adressVictime;
    private String groupeSanguinVictime;
    private String poidsVictime;
    private String allergies;
    private String mediRegulierVictime;
    private String nomAssureure;
    private String numeroSecuriteSocial;
    private String policeNumero;
    private String directionMedicale;
    private String numeroMedecin;
    private String CompriPreexistant;

    public DonneesMedicales() {
    }

    public DonneesMedicales(String id, String nomSecoureur, String prenomSecoureur, String numeroSecoureur, String autreNumeroSecoureur
            , String relationAvecSecoureur, String sexeVictime, String dateNaissanceVictime, String adressVictime, String groupeSanguinVictime, String poidsVictime,String allergies
            , String mediRegulierVictime, String nomAssureure, String numeroSecuriteSocial, String policeNumero, String directionMedicale,String numeroMedecin,String CompriPreexistant) {
        this.id = id;
        this.nomSecoureur = nomSecoureur;
        this.prenomSecoureur = prenomSecoureur;
        this.numeroSecoureur = numeroSecoureur;
        this.autreNumeroSecoureur = autreNumeroSecoureur;
        this.relationAvecSecoureur = relationAvecSecoureur;
        this.sexeVictime = sexeVictime;
        this.dateNaissanceVictime = dateNaissanceVictime;
        this.adressVictime = adressVictime;
        this.groupeSanguinVictime = groupeSanguinVictime;
        this.poidsVictime = poidsVictime;
        this.allergies = allergies;
        this.mediRegulierVictime = mediRegulierVictime;
        this.nomAssureure = nomAssureure;
        this.numeroSecuriteSocial = numeroSecuriteSocial;
        this.policeNumero = policeNumero;
        this.directionMedicale = directionMedicale;
        this.numeroMedecin = numeroMedecin;
        this.CompriPreexistant = CompriPreexistant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomSecoureur() {
        return nomSecoureur;
    }

    public void setNomSecoureur(String nomSecoureur) {
        this.nomSecoureur = nomSecoureur;
    }

    public String getPrenomSecoureur() {
        return prenomSecoureur;
    }

    public void setPrenomSecoureur(String prenomSecoureur) {
        this.prenomSecoureur = prenomSecoureur;
    }

    public String getNumeroSecoureur() {
        return numeroSecoureur;
    }

    public void setNumeroSecoureur(String numeroSecoureur) {
        this.numeroSecoureur = numeroSecoureur;
    }

    public String getAutreNumeroSecoureur() {
        return autreNumeroSecoureur;
    }

    public void setAutreNumeroSecoureur(String autreNumeroSecoureur) {
        this.autreNumeroSecoureur = autreNumeroSecoureur;
    }

    public String getRelationAvecSecoureur() {
        return relationAvecSecoureur;
    }

    public void setRelationAvecSecoureur(String relationAvecSecoureur) {
        this.relationAvecSecoureur = relationAvecSecoureur;
    }

    public String getSexeVictime() {
        return sexeVictime;
    }

    public void setSexeVictime(String sexeVictime) {
        this.sexeVictime = sexeVictime;
    }

    public String getDateNaissanceVictime() {
        return dateNaissanceVictime;
    }

    public void setDateNaissanceVictime(String dateNaissanceVictime) {
        this.dateNaissanceVictime = dateNaissanceVictime;
    }

    public String getAdressVictime() {
        return adressVictime;
    }

    public void setAdressVictime(String adressVictime) {
        this.adressVictime = adressVictime;
    }

    public String getGroupeSanguinVictime() {
        return groupeSanguinVictime;
    }

    public void setGroupeSanguinVictime(String groupeSanguinVictime) {
        this.groupeSanguinVictime = groupeSanguinVictime;
    }

    public String getPoidsVictime() {
        return poidsVictime;
    }

    public void setPoidsVictime(String poidsVictime) {
        this.poidsVictime = poidsVictime;
    }


    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }


    public String getMediRegulierVictime() {
        return mediRegulierVictime;
    }

    public void setMediRegulierVictime(String mediRegulierVictime) {
        this.mediRegulierVictime = mediRegulierVictime;
    }

    public String getNomAssureure() {
        return nomAssureure;
    }

    public void setNomAssureure(String nomAssureure) {
        this.nomAssureure = nomAssureure;
    }

    public String getNumeroSecuriteSocial() {
        return numeroSecuriteSocial;
    }

    public void setNumeroSecuriteSocial(String numeroSecuriteSocial) {
        this.numeroSecuriteSocial = numeroSecuriteSocial;
    }

    public String getPoliceNumero() {
        return policeNumero;
    }

    public void setPoliceNumero(String policeNumero) {
        this.policeNumero = policeNumero;
    }

    public String getDirectionMedicale() {
        return directionMedicale;
    }

    public void setDirectionMedicale(String directionMedicale) {
        this.directionMedicale = directionMedicale;
    }

    public String getNumeroMedecin() {
        return numeroMedecin;
    }

    public void setNumeroMedecin(String numeroMedecin) {
        this.numeroMedecin = numeroMedecin;
    }

    public String getCompriPreexistant() {
        return CompriPreexistant;
    }

    public void setCompriPreexistant(String compriPreexistant) {
        CompriPreexistant = compriPreexistant;
    }
}
