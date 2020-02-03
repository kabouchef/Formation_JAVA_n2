package com.company.parsingXml;

import com.company.jdbc.lmfr.SimulationOffer;
import org.apache.poi.hssf.usermodel.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLToExcel {
    public void generateExcel() throws ParserConfigurationException, IOException, SAXException {

        String nameDefaultFile = "xml/" + SimulationOffer.simulationCode + ".xml";
        String priceLine = "";

        try {// Creating a Workbook
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet spreadSheet = wb.createSheet("General");

            spreadSheet.setColumnWidth(0, 256 * 25);
            spreadSheet.setColumnWidth(1, 256 * 25);
            spreadSheet.setColumnWidth(2, 156 * 25);
            spreadSheet.setColumnWidth(3, 156 * 25);
            spreadSheet.setColumnWidth(4, 156 * 25);

            // Parsing XML Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(nameDefaultFile);

            // Creating Rows
            HSSFRow row = spreadSheet.createRow(0);
            HSSFCell cell = cell = row.createCell(0);

            String[] entete = {"IDENTIFIANT", "DETAIL_PRESTATION", "QUANTITE", "TARIF_UNITAIRE"
                    , "TARIF_PRESTATION", "TYPE_PRESTATION", "PRESTATION_DE", "TVA_REDUITE",
                    "TVA_INTER", "TVA_NORMALE", "CODE_49", "COD_TYPE_PRESTATION", "TEMP_POSE", "ORDRE"};

            for (int a = 0; a < entete.length; a++) {
                cell = row.createCell(a);
                cell.setCellValue(entete[a].toString());
            }

            /**
             * Récupération du nombre de lignes de prix
             */
            String nbLines = "count(//*[@name='fpPriceHtChiffragePrecisSurrogate']//Value[not(preceding-sibling::Value = .)])";
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression nbLine = xPath.compile(nbLines);
            String nbPrice = xPath.compile(nbLines).evaluate(xmlDocument, XPathConstants.STRING).toString();

            int nbPriceLines = Integer.parseInt(nbPrice);

            for (int i = 1; i < nbPriceLines + 1; i++) {
                /**
                 * Récupération des lignes de prix
                 */
                String cible = "//*[@name='fpPriceHtChiffragePrecisSurrogate']//Value[" + i + "]/longTextValue";
                XPathExpression expr = xPath.compile(cible);

                HSSFRow row1 = spreadSheet.createRow(i);

                priceLine = xPath.compile(cible).evaluate(xmlDocument, XPathConstants.STRING).toString();
                String[] priceElement = priceLine.split("#");

                for (int j = 0; j < priceElement.length; j++) {
                    cell = row1.createCell(j);
                    HSSFCellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
                    cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
                    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
                    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

                    cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("€#,##0.00"));

                    cell.setCellValue(priceElement[j]);

                }
            }

            // Outputting to Excel spreadsheet
            FileOutputStream fos = new FileOutputStream(new File("xls/PRICE_FROM_" + SimulationOffer.simulationCode + ".xls"));
            wb.write(fos);
            fos.flush();
            fos.close();

            File fichier = new File("xls/PRICE_FROM_" + SimulationOffer.simulationCode + ".xls");
            if (fichier.exists())
                System.out.println("Le fichier Excel a bien été créé.");
            else
                System.out.println("Le fichier Excel n'a pas été créé...");

        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out
                    .println("ParserConfigurationException " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAXException " + e.getMessage());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }
}
