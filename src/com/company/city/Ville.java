package com.company.city;

public class Ville {
    private String nomVille;
    private String nomPays;
    private int nbreHabitants;
    private char categorie;

    //Variables publiques qui comptent les instances
    public static int nbreInstances = 0;

    //Variable privée qui comptera aussi les instances
    public static int nbreInstancesBis = 0;

    public Ville(){
        this.nomVille="Inconnu";
        this.nomPays="Inconnu";
        this.nbreHabitants=0;
        this.setCategorie();
        //On incrémente nos variables à chaque appel aux constructeurs
        nbreInstances++;
        nbreInstancesBis++;

    }

    public Ville(String pNom, int pNbre, String pPays) throws  NombreHabitantException, NomVilleException
    {
        if(pNbre < 0)
            throw new NombreHabitantException(pNbre);
        if(pNom.length() < 3)
            throw new NomVilleException("le nom de la ville est inférieur à 3 caractères ! nom = " + pNom);
        else
        {
            nbreInstances++;
            nbreInstancesBis++;

            this.nomVille = pNom;
            this.nomPays = pPays;
            this.nbreHabitants = pNbre;
            this.setCategorie();
        }
    }

    public String getNom() {
        return nomVille;
    }

    public void setNom(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public int getNombreHabitants() {
        return nbreHabitants;
    }

    public void setNombreHabitants(int nbreHabitants) {
        this.nbreHabitants = nbreHabitants;
    }

    public static int getNombreInstancesBis()
    {
        return nbreInstancesBis;
    }

    //Définit la catégorie de la ville
    private void setCategorie() {

        int bornesSuperieures[] = {0, 1000, 10000, 100000, 500000, 1000000, 5000000, 10000000};
        char categories[] = {'?', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        int i = 0;
        while (i < bornesSuperieures.length && this.nbreHabitants > bornesSuperieures[i])
            i++;

        this.categorie = categories[i];
    }

    //Retourne la description de la ville
    public String toString(){
        return "\t"+this.nomVille+" est une ville de "+this.nomPays+ ", elle comporte : "+this.nbreHabitants+" habitant(s) => elle est donc de catégorie : "+this.categorie;
    }

    //Retourne une chaîne de caractères selon le résultat de la comparaison
    public String comparer(Ville v1){
        String str = new String();

        if (v1.getNombreHabitants() > this.nbreHabitants)
            str = v1.getNom()+" est une ville plus peuplée que "+this.nomVille;

        else
            str = this.nomVille+" est une ville plus peuplée que "+v1.getNom();

        return str;
    }
}
