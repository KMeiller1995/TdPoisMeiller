package com.example.meill.dijoncenter.Models;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by meill on 18/12/2017.
 */

public class Parcours {

    int idParcours;
    UUID idLieuCine;
    UUID idLieuRest;
    String Nom;
    Date DATETIME;


    public Parcours(int idParcours, UUID idLieuCine, UUID idLieuRest, String nom, Date DATETIME) {
        this.idParcours = idParcours;
        this.idLieuCine = idLieuCine;
        this.idLieuRest = idLieuRest;
        Nom = nom;
        this.DATETIME = DATETIME;
    }



    public int getIdParcours() {
        return idParcours;
    }

    public void setIdParcours(int idParcours) {
        this.idParcours = idParcours;
    }

    public UUID getIdLieuCine() {
        return idLieuCine;
    }

    public void setIdLieuCine(UUID idLieuCine) {
        this.idLieuCine = idLieuCine;
    }

    public UUID getIdLieuRest() {
        return idLieuRest;
    }

    public void setIdLieuRest(UUID idLieuRest) {
        this.idLieuRest = idLieuRest;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Date getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(Date DATETIME) {
        this.DATETIME = DATETIME;
    }


}
