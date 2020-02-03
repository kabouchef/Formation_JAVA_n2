package com.company.jdbc.lmfr;

import com.company.jdbc.serveurs.ServerLMFR;
import com.company.parsingXml.XMLToExcel;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimulationOffer {
    public static String simulationCode = "";

    public String getSimulationCode() {
        return simulationCode;
    }

    public void SimulationOffer() {

        /**
         * Génération de la date actuel au format yyyyMMdd
         */
        String format = "yyyyMMdd";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        java.util.Date date = new Date();

        /**
         * Boite de dialogue : Numéro de simulation
         */
        simulationCode = JOptionPane.showInputDialog("Numéro de la simulation : ", /*formater.format(date) + "S"*/"20190822S24080");



        try {
/*            long start = System.currentTimeMillis();*/
            Statement state = ConnectionLMFR.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            /**
             * Message de récapitulatif : Numéro de simulation et de l'Environnement
             */
            JOptionPane.showMessageDialog(null, "La simulation : " + simulationCode + "\nva être extraite de l'environnement de : "+ ConnectionLMFR.environnement, "Identité", JOptionPane.INFORMATION_MESSAGE);

            ResultSet res = state.executeQuery("SELECT XML_CONF FROM T_CONF_STORAGE WHERE CONF_ID = '" + simulationCode + "'");
            ResultSetMetaData meta = res.getMetaData();

            Object[] column = new Object[meta.getColumnCount()];

            for (int i = 1; i <= meta.getColumnCount(); i++)
                column[i - 1] = meta.getColumnName(i);

            res.last();
            /*int rowCount = res.getRow();*/
            Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

            //On revient au départ
            res.beforeFirst();
            int j = 1;

            //On remplit le tableau d'Object[][]
            while (res.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    data[j - 1][i - 1] = res.getObject(i);

                    Clob clob = res.getClob("XML_CONF");
                    Reader r = clob.getCharacterStream();
                    StringBuffer buffer = new StringBuffer();
                    int ch;
                    while ((ch = r.read()) != -1) {
                        buffer.append("" + (char) ch);
                    }
                    XmlWriter writingXML = new XmlWriter(buffer.toString());
                }
                j++;
            }

            //On ferme le tout
            res.close();
            state.close();

            XMLToExcel excel = new XMLToExcel();
            excel.generateExcel();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
