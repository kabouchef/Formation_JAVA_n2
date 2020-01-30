package com.company.jdbc;

import java.io.*;
import java.util.Iterator;

public class WritingXML {
    public WritingXML() {
    }
    public WritingXML(String stringToXML) {
        File f = new File("XML_CONF.xml");

        try{
            FileWriter fw = new FileWriter(f);

            fw.write(stringToXML);
            fw.flush();

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*PrintWriter printWriter;
            int n = 5;

        printWriter =  new PrintWriter(
                            new BufferedWriter(
                                new FileWriter(argv[0])));

        printWriter.println(stringToXML);
        printWriter.close();*/

    }
}
