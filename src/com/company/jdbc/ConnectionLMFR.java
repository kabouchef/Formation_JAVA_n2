package com.company.jdbc;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLMFR {

    /**
     * URL de connection
     */
    private static String url = "jdbc:oracle:thin:@rfrlmdboaag01:1521:roaag01";

    /**
     * Nom du user
     */
    private static String user = "oaa";

    /**
     * Mot de passe du user
     */
    private static String passwd = "oaa";

    /**
     * Objet Connection
     */
    private static Connection connect;

    /**
     * Méthode qui va retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */
    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connect;
    }
}
