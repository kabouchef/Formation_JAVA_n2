package com.company;

import com.company.jdbc.lmfr.SimulationOffer;
import com.company.xmlToExcel.XMLToExcel;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {

        SimulationOffer req = new SimulationOffer();
        req.SimulationOffer();

/*        XMLToExcel test = new XMLToExcel();
        test.generateExcel();*/


    }


}
