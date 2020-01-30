package com.company;

import com.company.jdbc.FenetreJDBC;
import com.company.jdbc.SimulationOffer;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*FenetreJDBC fen = new FenetreJDBC();
        fen.setVisible(true);*/

        SimulationOffer req = new SimulationOffer();
        req.SimulationOffer("SELECT XML_CONF FROM T_CONF_STORAGE WHERE CONF_ID = '20200129S51509'");

    }
}
