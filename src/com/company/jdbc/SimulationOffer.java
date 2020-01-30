package com.company.jdbc;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class SimulationOffer {

    public void SimulationOffer() {
        System.out.println("ERREUR : Aucune une requête n'a été soumise");
    }

    public void SimulationOffer(String query) {
        try {

            long start = System.currentTimeMillis();
            Statement state = ConnectionLMFR.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet res = state.executeQuery(query);


            ResultSetMetaData meta = res.getMetaData();

            Object[] column = new Object[meta.getColumnCount()];

            for (int i = 1; i <= meta.getColumnCount(); i++)
                column[i - 1] = meta.getColumnName(i);

            res.last();
            int rowCount = res.getRow();
            Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

            //On revient au départ
            res.beforeFirst();
            int j = 1;

            //On remplit le tableau d'Object[][]
            while (res.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++){
                    data[j - 1][i - 1] = res.getObject(i);
                    //Retrieving the data
                    System.out.println("Contents of the table are: ");
                    System.out.println("XML_CONF : "+res.getString("XML_CONF"));
                    Clob clob = res.getClob("XML_CONF");
                    Reader r = clob.getCharacterStream();
                    StringBuffer buffer = new StringBuffer();
                    int ch;
                    while ((ch = r.read())!=-1) {
                        buffer.append(""+(char)ch);
                    }
                    System.out.println("Contents: "+buffer.toString());
                    System.out.println(" ");

                }

                j++;
            }

            //On ferme le tout
            res.close();
            state.close();

            long totalTime = System.currentTimeMillis() - start;

/*            //On enlève le contenu de notre conteneur
            result.removeAll();
            //On y ajoute un JTable
            result.add(new JScrollPane(new JTable(data, column)), BorderLayout.CENTER);
            result.add(new JLabel("La requête à été exécuter en " + totalTime + " ms et a retourné " + rowCount + " ligne(s)"), BorderLayout.SOUTH);
            //On force la mise à jour de l'affichage
            result.revalidate();*/

        } catch (SQLException e) {
            //Dans le cas d'une exception, on affiche une pop-up et on efface le contenu
/*            result.removeAll();
            result.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
            result.revalidate();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
