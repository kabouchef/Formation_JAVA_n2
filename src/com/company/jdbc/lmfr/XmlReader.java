package com.company.jdbc.lmfr;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;


public class XmlReader {
    String offerCode = "";
    String nameDefaultFile = "xml/XML_CONF.xml";


    /**
     * @return offerCode;
     */
    public String XmlReader() {
        try {

            /*FileInputStream fXmlFile = new FileInputStream (nameDefaultFile);*/
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            /**
             * xmlDocument correspond au document xml parsé
             */
            Document xmlDocument = dBuilder.parse(nameDefaultFile);

            /**
             * Recherche du resultat du xpath dans le xmlDocument
             */
            String expression = "//*[@cpe=\"CPE.Settings.Session.CodeOffre\"]/@value";
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xPath.compile(expression);

            /**
             * @offerCode : Récupération du numéro de simulation de l'offre
             */
            offerCode = xPath.compile(expression).evaluate(xmlDocument, XPathConstants.STRING).toString();
            System.out.println("Numéro de simulation : " + offerCode);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerCode;
    }
}
