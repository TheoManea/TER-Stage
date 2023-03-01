package com.example.gui_version;

import java.util.regex.Pattern;

public class Reaction {
    private String line = "";
    private String nomEnzyme = "";
    private String[] substrats;
    private String[] produits;
    private String[] data;
    private String regex = "(\"(.*?)\"(\\s)(\\:))((\\s)(\".*?\")(\\s)((\\+)*))(->)((\\s)(\".*?\")(\\s)((\\+)*))(\\|)";


    public Reaction(String initialLine){
        line = initialLine.split("(?<=\\|)")[0];
    }

    /**
     * Arrivé à cette fonction on sait que la réaction est
     * correctement formaté. Cette fonction s'occupe juste
     * d'assigner les valeurs aux attributs
     **/
    public void setAttributes(){
        System.out.println(line);
        try {
            String[] twoPoints = line.split(":");
            nomEnzyme = twoPoints[0];
            String[] arrow = twoPoints[1].split("->");
            substrats = arrow[0].split("\\+");
            String[] vertical = arrow[1].split("\\|");
            produits = vertical[0].split("\\+");
            //data = vertical[1].split("\\|"); // cette ligne ne marche pas lol
        }
        catch (Exception e){
            System.out.println("Erreur dans l'attribution des paramètres");
        }

    }
    public boolean correctlyFormatted(){
        return Pattern.matches(regex,line);
    }
    public String getLine() {
        return line;
    }

    public String getNomEnzyme() {
        return nomEnzyme;
    }

    public String[] getSubstrats() {
        return substrats;
    }

    public String[] getProduits() {
        return produits;
    }

    public String[] getData() {
        return data;
    }



}
