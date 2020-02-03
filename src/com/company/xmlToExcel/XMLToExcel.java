package com.company.xmlToExcel;

import com.company.jdbc.lmfr.SimulationOffer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
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
        /*String nameDefaultFile = "xml/20200203S86787.xml";*/
        String priceLine = "";

        try {// Creating a Workbook
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet spreadSheet = wb.createSheet("General");

            for(int i=0; i<14;i++) {
                if (i<2 || i > 4)spreadSheet.setColumnWidth(i, 256 * 25);
                else spreadSheet.setColumnWidth(i, 156 * 25);
            }


            // Parsing XML Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(nameDefaultFile);

            // Creating Rows
            HSSFRow row = spreadSheet.createRow(0);
            HSSFCell cell = row.createCell(0);

            String[] entete = {"IDENTIFIANT", "DETAIL_PRESTATION", "QUANTITE", "TARIF_UNITAIRE"
                    , "TARIF_PRESTATION", "TYPE_PRESTATION", "PRESTATION_DE", "TVA_REDUITE",
                    "TVA_INTER", "TVA_NORMALE", "CODE_49", "COD_TYPE_PRESTATION", "TEMP_POSE", "ORDRE"};

            DataFormat df = wb.createDataFormat();

            HSSFFont font1 = wb.createFont();
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font1.setColor(HSSFColor.GREY_80_PERCENT.index);
            HSSFFont font2 = wb.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font2.setColor(HSSFColor.ORANGE.index);
            HSSFFont font3 = wb.createFont();
            font3.setColor(HSSFColor.GREY_80_PERCENT.index);


            HSSFCellStyle style1 = wb.createCellStyle();
            style1.setDataFormat(df.getFormat("#,##0.00 €"));
            style1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style1.setFont(font2);

            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style2.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style2.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style2.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style2.setFont(font2);

            HSSFCellStyle style3 = null;
            style3 = wb.createCellStyle();
            style3.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style3.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style3.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style3.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style3.setFont(font1);

            HSSFCellStyle style4 = wb.createCellStyle();
            style4.setDataFormat(df.getFormat("#,##0.00 €"));
            style4.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style4.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style4.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style4.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style4.setFont(font3);

            CellStyle style5;
            style5 = wb.createCellStyle();
            style5.setAlignment(CellStyle.ALIGN_CENTER);
            style5.setFont(font3);

            HSSFCellStyle style6 = wb.createCellStyle();
            style6.setFont(font3);

            HSSFCellStyle style7 = wb.createCellStyle();
            style7.setDataFormat(df.getFormat("#,##0.00 €"));
            style7.setFont(font3);

            HSSFCellStyle style8 = wb.createCellStyle();
            style8.setAlignment(CellStyle.ALIGN_CENTER);
            style8.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style8.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style8.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style8.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style8.setFont(font2);



            for (int a = 0; a < entete.length; a++) {
                cell = row.createCell(a);
                cell.setCellValue(entete[a]);
                cell.setCellStyle(style8);
            }

            /**
             * Récupération du nombre de lignes de prix
             */
            String nbLines = "count(//*[@name='fpPriceHtChiffragePrecisSurrogate']" +
                    "//Value[not(preceding-sibling::Value = .)])";
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression nbLine = xPath.compile(nbLines);
            String nbPrice = xPath.compile(nbLines).evaluate(xmlDocument, XPathConstants.STRING).toString();

            int nbPriceLines = Integer.parseInt(nbPrice);

            String[] priceElement = new String[0];
            for (int i = 1; i < nbPriceLines + 1; i++) {
                /**
                 * Récupération des lignes de prix
                 */
                String cible = "//*[@name='fpPriceHtChiffragePrecisSurrogate']//Value[" + i + "]/longTextValue";
                XPathExpression expr = xPath.compile(cible);

                HSSFRow row1 = spreadSheet.createRow(i);

                priceLine = xPath.compile(cible).evaluate(xmlDocument, XPathConstants.STRING).toString();
                priceElement = priceLine.split("#");

                for (int j = 0; j < priceElement.length; j++) {
                    cell = row1.createCell(j);

                    if (j == 3 || j == 4) {
                        double price = Double.parseDouble(priceElement[j]);
                        cell.setCellValue(price);
                        cell.setCellStyle(style7);
                    } else if (j==2 || j > 6) {
                        int quantity = Integer.parseInt(priceElement[j]);
                        cell.setCellValue(quantity);
                        cell.setCellStyle(style5);
                    }else{
                        cell.setCellValue(priceElement[j]);
                        cell.setCellStyle(style6);
                    }
                }
            }
            Sheet sheet = wb.getSheetAt(0);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            boolean tvaReduceAllowed = true;
            boolean tvaInterAllowed = true;

            for (int i = 2; i < nbPriceLines + 2; i++) {
                CellReference cellReferenceReduce = new CellReference("H" + i);
                Row ligneReduce = sheet.getRow(cellReferenceReduce.getRow());
                Cell celluleReduce = ligneReduce.getCell(cellReferenceReduce.getCol());
                CellValue cellValueReduce = evaluator.evaluate(celluleReduce);
                String cReduceString = celluleReduce.toString();

                CellReference cellReferenceInter = new CellReference("I" + i);
                Row ligneInter = sheet.getRow(cellReferenceReduce.getRow());
                Cell celluleInter = ligneInter.getCell(cellReferenceInter.getCol());
                CellValue cellValueInter = evaluator.evaluate(celluleInter);
                String cInterString = celluleInter.toString();

                if (cReduceString.equals("0.0")) tvaReduceAllowed = false;
                if (cInterString.equals("0.0")) tvaInterAllowed = false;

            }
            /**
             * Calcule du prix total HT
             */
            int index = nbPriceLines + 3;
            HSSFRow rowTotalHT = spreadSheet.createRow(index);
            cell = rowTotalHT.createCell(3);
            cell.setCellValue("Prix HT");
            cell.setCellStyle(style3);
            cell = rowTotalHT.createCell(4);
            cell.setCellFormula("SUM(E2:E" + index + ")");
            cell.setCellStyle(style4);

            HSSFCellStyle hiddenstyle = wb.createCellStyle();
            hiddenstyle.setHidden(true);

            /**
             * Calcule du prix total en TVA Réduite
             */
            index++;
            HSSFRow rowTotalReduite = spreadSheet.createRow(index);
            cell = rowTotalReduite.createCell(3);
            cell.setCellValue("Prix TVA 5.5%");
            cell.setCellStyle(style3);
            cell = rowTotalReduite.createCell(4);
            cell.setCellFormula("E" + index + "*1.055");
            cell.setCellStyle(style4);
            if (tvaReduceAllowed == false) rowTotalReduite.setZeroHeight(true);
            else cell.setCellStyle(style1);

            /**
             * Calcule du prix total en TVA Intermédiaire
             */
            index++;
            HSSFRow rowTotalInter = spreadSheet.createRow(index);
            cell = rowTotalInter.createCell(3);
            cell.setCellValue("Prix TVA 10%");
            if (tvaReduceAllowed == false) cell.setCellStyle(style2);
            else cell.setCellStyle(style3);
            cell = rowTotalInter.createCell(4);
            cell.setCellFormula("E" + index + "*1.1");
            cell.setCellStyle(style4);
            if (tvaInterAllowed == false) rowTotalInter.setZeroHeight(true);
            else cell.setCellStyle(style1);
            /**
             * Calcule du prix total en TVA Normale
             */
            index++;
            HSSFRow rowTotalNormale = spreadSheet.createRow(index);
            cell = rowTotalNormale.createCell(3);
            cell.setCellValue("Prix TVA 20%");
            if (tvaReduceAllowed == false && tvaInterAllowed == false) cell.setCellStyle(style2);
            else cell.setCellStyle(style3);
            cell = rowTotalNormale.createCell(4);
            cell.setCellFormula("E" + index + "*1.2");
            cell.setCellStyle(style4);
            if (tvaReduceAllowed == true && tvaInterAllowed == true) cell.setCellStyle(style1);

            // Outputting to Excel spreadsheet
            /*FileOutputStream fos = new FileOutputStream(new File("xls/PRICE_FROM_20200203S86787.xls"));*/
            FileOutputStream fos = new FileOutputStream(new File("xls/PRICE_FROM_" +
                    SimulationOffer.simulationCode + ".xls"));
            wb.write(fos);
            fos.flush();
            fos.close();

            File fichier = new File("xls/PRICE_FROM_" + SimulationOffer.simulationCode + ".xls");
            /*File fichier = new File("xls/PRICE_FROM_20200203S86787.xls");*/
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
