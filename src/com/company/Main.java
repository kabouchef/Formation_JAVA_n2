package com.company;

import com.company.jdbc.lmfr.SimulationOffer;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String simulationCode = "20200129S51509";


        /**
         * Génération de la date actuel au format yyyyMMdd
         */
        String format = "yyyyMMdd";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        Date date = new Date();

        simulationCode = JOptionPane.showInputDialog("Enter le numéro de la simulation : ",formater.format(date)+"S");


        SimulationOffer req = new SimulationOffer();
        req.SimulationOffer("SELECT XML_CONF FROM T_CONF_STORAGE WHERE CONF_ID = '"+simulationCode+"'");



    }
}
