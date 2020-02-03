package com.company.jdbc.lmfr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class XmlWriter {

    private String codeOffre = "";

    public XmlWriter(String stringToXML) {
        String nameDefaultFile = "xml/XML_CONF.xml";
        File f = new File(nameDefaultFile);

        Path source = Paths.get(nameDefaultFile);

        try {
            FileWriter fw = new FileWriter(f);
            XmlFormatter formatter = new XmlFormatter();
            fw.write(formatter.format(stringToXML));

            fw.flush();
            fw.close();

            XmlReader xmlReader = new XmlReader();
            codeOffre = xmlReader.XmlReader();
            // Get the file
            String path = "xml/" + codeOffre + ".xml";

            File ancien = new File(nameDefaultFile);
            File nouveau = new File(path);
            ancien.renameTo(nouveau);

            // Check if the specified file
            // Exists or not
            if (nouveau.exists())
                System.out.println("Le fichier XML a bien été créé.");
            else
                System.out.println("Le fichier XML n'a pas été créé...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
