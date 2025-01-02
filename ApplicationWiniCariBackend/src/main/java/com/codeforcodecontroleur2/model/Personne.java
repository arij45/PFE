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
@Document(collection = "Personne")

public class Personne {

    @Id
    private String id;
    @Field("matriculecontroleur")
    private String matriculecontroleur;
    @Field("societe")
    private String societe;
    @Field("nom")
    private String nom;
    @Field("numero")
    private String numero;
    @Field("code_secret")
    private String codesecret;


    public String getCodesecret() {
        return codesecret;
    }

    public void setCodesecret(String codesecret) {
        this.codesecret = codesecret;
    }

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }
}








