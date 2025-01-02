package com.codeforcodecontroleur2.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Data
@Document(collection = "Rapport_Reclamation")
public class RapportReclamtion {

    @Id
    private String id;
    @Field("type")
    private String type;
    @Field("societe")
    private String societe;
    @Field("nom")
    private String nom;
    @Field("matricule")
    private String matricule;
    @Field("msg")
    private String msg;
    @Field("date_aujourdhui")
    private String dateaujourdhui;
    @Field("heure_aujourdhui")
    private String heureaujourdhui;
    @Field("matriculecontroleur")
    private String matriculecontroleur;
    @Field("matriculebinome")
    private String matriculebinome;
    @Field("heure")
    private String heure;
    @Field("StatusRapport")
    private String statusRapport;
    @Field("Status_reclamation")
    private String statusreclamation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDateaujourdhui() {
        return dateaujourdhui;
    }

    public void setDateaujourdhui(String dateaujourdhui) {
        this.dateaujourdhui = dateaujourdhui;
    }

    public String getHeureaujourdhui() {
        return heureaujourdhui;
    }

    public void setHeureaujourdhui(String heureaujourdhui) {
        this.heureaujourdhui = heureaujourdhui;
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getStatusRapport() {
        return statusRapport;
    }

    public void setStatusRapport(String statusRapport) {
        this.statusRapport = statusRapport;
    }

    public String getStatusreclamation() {
        return statusreclamation;
    }

    public void setStatusreclamation(String statusreclamation) {
        this.statusreclamation = statusreclamation;
    }
}











