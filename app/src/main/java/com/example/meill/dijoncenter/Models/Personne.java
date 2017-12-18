package com.example.meill.dijoncenter.Models;

import java.util.Date;

/**
 * Created by meill on 04/12/2017.
 */

public class Personne {
    int idPersonne;
    String nomPersonne;
    String prenomPersonne;
    int agePersonne;
    int poidsPersonne;
    Date dateMajPersonne;
    String loginPersonne;

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    public int getAgePersonne() {
        return agePersonne;
    }

    public void setAgePersonne(int agePersonne) {
        this.agePersonne = agePersonne;
    }

    public int getPoidsPersonne() {
        return poidsPersonne;
    }

    public void setPoidsPersonne(int poidsPersonne) {
        this.poidsPersonne = poidsPersonne;
    }

    public Date getDateMajPersonne() {
        return dateMajPersonne;
    }

    public void setDateMajPersonne(Date dateMajPersonne) {
        this.dateMajPersonne = dateMajPersonne;
    }

    public String getLogingPersonne() {
        return loginPersonne;
    }

    public void setLogingPersonne(String logingPersonne) {
        this.loginPersonne = logingPersonne;
    }
}
