package com.company;

import com.company.jdbc.lmfr.SimulationOffer;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String simulationCode = "20200129S51509";
        SimulationOffer req = new SimulationOffer();
        req.SimulationOffer("SELECT XML_CONF FROM T_CONF_STORAGE WHERE CONF_ID = '"+simulationCode+"'");


    }
}
