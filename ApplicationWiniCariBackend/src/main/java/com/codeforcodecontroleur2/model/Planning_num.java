package com.codeforcodecontroleur2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@ToString

public class Planning_num {
    @Id
    private String id ;
    private String matriculecontroleur;
    private String matriculebinome;
    private String nomcontroleur;
    private String nombinome;
    private String bus;
    private String ligne;
    private String station;
    private String parametre;
    private String heure;
    private String date;
    private String numero;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatriculecontroleur() {
        return matriculecontroleur;
    }

    public void setMatriculecontroleur(String matriculecontroleur) {
        this.matriculecontroleur = matriculecontroleur;
    }

    public String getMatriculebinome() {
        return matriculebinome;
    }

    public void setMatriculebinome(String matriculebinome) {
        this.matriculebinome = matriculebinome;
    }

    public String getNomcontroleur() {
        return nomcontroleur;
    }

    public void setNomcontroleur(String nomcontroleur) {
        this.nomcontroleur = nomcontroleur;
    }

    public String getNombinome() {
        return nombinome;
    }

    public void setNombinome(String nombinome) {
        this.nombinome = nombinome;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getParametre() {
        return parametre;
    }

    public void setParametre(String parametre) {
        this.parametre = parametre;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
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
}
