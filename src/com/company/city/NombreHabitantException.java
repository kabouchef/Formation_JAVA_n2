package com.company.city;

public class NombreHabitantException extends Exception{
    public NombreHabitantException(int nbre)
    {
        System.out.println("Instanciation avec un nombre d'habitants négatif.");
        System.out.println("\t => " + nbre);
    }
}
