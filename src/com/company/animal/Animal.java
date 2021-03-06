package com.company.animal;

abstract class Animal {

    protected String couleur;
    protected int poids;

    public void manger(){
        System.out.println("Je mange de la viande.");
    }

    public void boire(){
        System.out.println("Je bois de l'eau !");
    }

    abstract void deplacement();

    abstract void crier();

    public String toString(){
        String str = "Je suis un objet de la " + this.getClass() + ", je suis " + this.couleur + ", je pèse " + this.poids;
        return str;
    }
}