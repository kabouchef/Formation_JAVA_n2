package com.company.city;

public class Capitale extends Ville {

    private String monument;

    //Constructeur par défaut
    public Capitale(){
        //Ce mot clé appelle le constructeur de la classe mère
        super();
        monument = "aucun";
    }

    public Capitale(String nom, int hab, String pays, String monument) throws  NombreHabitantException, NomVilleException{
        super(nom, hab, pays);
        this.monument=monument;
    }

    public String toString(){
        String str =  super.toString() + "\n \t ==>>" + this.monument+ " en est un monument";
        System.out.println("Invocation de super.decrisToi()");

        return str;
    }

    public String getMonument() {
        return monument;
    }

    public void setMonument(String monument) {
        this.monument = monument;
    }
}