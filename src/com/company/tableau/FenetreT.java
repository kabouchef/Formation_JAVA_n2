package com.company.tableau;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CTRL + SHIFT + O pour générer les imports
public class FenetreT extends JFrame {

    private JTable tableau;
    private JButton change = new JButton("Changer la taille");
    //Contenu de notre combo
    private String[] comboData = {"Très bien", "Bien", "Mal"};

    public FenetreT(){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTable");
        this.setSize(600, 180);
        //Données de notre tableau
        Object[][] data = {
                {"Cysboy", "6boy", comboData[0], new Boolean(true)},
                {"BZHHydde", "BZH", comboData[0], new Boolean(false)},
                {"IamBow", "BoW", comboData[0], new Boolean(false)},
                {"FunMan", "Year", comboData[0], new Boolean(true)}
        };
        String  title[] = {"Pseudo", "Age", "Taille", "OK ?"};
        JComboBox combo = new JComboBox(comboData);

        //Nous devons utiliser un modèle d'affichage spécifique pour pallier les bugs d'affichage !
        ZModel zModel = new ZModel(data, title);

        this.tableau = new JTable(zModel);
        this.tableau.setRowHeight(30);
        this.tableau.getColumn("Age").setCellRenderer(new ButtonRenderer());
        this.tableau.getColumn("Age").setCellEditor(new ButtonEditor(new JCheckBox()));
        //On définit l'éditeur par défaut pour la cellule
        //En lui spécifiant quel type d'affichage prendre en compte
        this.tableau.getColumn("Taille").setCellEditor(new  DefaultCellEditor(combo));
        this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
    }

    class ZModel extends AbstractTableModel{
        private Object[][] data;
        private String[] title;

        //Constructeur
        public ZModel(Object[][] data, String[] title){
            this.data = data;
            this.title = title;
        }

        //Retourne le titre de la colonne à l'indice spécifié
        public String getColumnName(int col) {
            return this.title[col];
        }

        //Retourne le nombre de colonnes
        public int getColumnCount() {
            return this.title.length;
        }

        //Retourne le nombre de lignes
        public int getRowCount() {
            return this.data.length;
        }

        //Retourne la valeur à l'emplacement spécifié
        public Object getValueAt(int row, int col) {
            return this.data[row][col];
        }

        //Définit la valeur à l'emplacement spécifié
        public void setValueAt(Object value, int row, int col) {
            //On interdit la modification sur certaines colonnes !
            if(!this.getColumnName(col).equals("Age") && !this.getColumnName(col).equals("Suppression"))
                this.data[row][col] = value;
        }

        //Retourne la classe de la donnée de la colonne
        public Class getColumnClass(int col){
            //On retourne le type de la cellule à la colonne demandée
            //On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
            //On choisit donc la première ligne
            return this.data[0][col].getClass();
        }

        public boolean isCellEditable(int row, int col){
            return true;
        }
    }
}