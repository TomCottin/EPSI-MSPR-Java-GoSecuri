package com.groupe5.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agent {

    private String prenom, nom, identification, mdp;

    private List<String> equipements;

    public Agent(){
        this.prenom = "";
        this.nom = "";
        this.identification = "";
        this.mdp = "";
        this.equipements = new ArrayList<String>();
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getNom(){
        return this.nom;
    }

    public String getIdentification(){
        return this.identification;
    }

    public String getIdentifiant(){
        return this.prenom.toLowerCase().charAt(0) + this.nom.toLowerCase();
    }

    public List<String> getEquipements(){
        return this.equipements;
    }

    public String getMdp(){
        return this.mdp;
    }

    public void fillData(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            TextToHtml txtToHtml = new TextToHtml();
            String[] contentList = txtToHtml.getFileContent(fileName).split("\n");
            this.nom = contentList[0];
            this.prenom = contentList[1];
            this.identification = contentList[2];
            this.mdp = contentList[3];
            this.equipements = Arrays.asList(Arrays.copyOfRange(contentList, 5, contentList.length));
        } 
    }

    public String toString(){
        return "(prenom : " + this.prenom + ", " + "nom : " + this.nom + ", " +
         "identication : " + this.identification + ", " +
          "equipements : " + this.equipements + ")";
    }
    
}
