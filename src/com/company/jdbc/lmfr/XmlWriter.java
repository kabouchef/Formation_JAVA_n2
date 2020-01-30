package com.company.jdbc.lmfr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class XmlWriter {

    private String codeOffre="";

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
            Path cible = Paths.get("xml/" + codeOffre + ".xml");

            Files.copy(source, cible, StandardCopyOption.REPLACE_EXISTING);

            // Get the file
            File copiedFile = new File(codeOffre+".xml");

            // Check if the specified file
            // Exists or not
            if (copiedFile.exists())
                System.out.println("Le fichier a bien été copié.");
            else
                System.out.println("Does not Exists");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
