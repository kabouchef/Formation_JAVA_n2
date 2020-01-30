package com.company.jdbc.oapV2;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OapV2Connection {
    /**
     * URL de connection
     */
    private static String url = "jdbc:postgresql://pg-postgres-hmbu-d-oap.aivencloud.com:12833/oap_data?sslmode=require";

    /**
     * Nom du user
     */
    private static String user = "oap_data";

    /**
     * Mot de passe du user
     */
    private static String passwd = "smiq4tflouwxdeis";

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
