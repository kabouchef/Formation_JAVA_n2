package com.company.tableau;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CTRL + SHIFT + O pour générer les imports
public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private boolean   isPushed;
    private ButtonListener bListener = new ButtonListener();

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(bListener);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //On affecte le numéro de ligne au listener
        bListener.setRow(row);
        //Idem pour le numéro de colonne
        bListener.setColumn(column);
        //On passe aussi le tableau en paramètre pour des actions potentielles
        bListener.setTable(table);

        //On réaffecte le libellé au bouton
        button.setText( (value == null) ? "" : value.toString() );
        //On renvoie le bouton
        return button;
    }

    //Notre listener pour le bouton
    class ButtonListener implements ActionListener{

        private int column, row;
        private JTable table;
        private int nbre = 0;
        private JButton button;

        public void setColumn(int col){this.column = col;}
        public void setRow(int row){this.row = row;}
        public void setTable(JTable table){this.table = table;}

        public JButton getButton(){return this.button;}

        public void actionPerformed(ActionEvent event) {
            System.out.println("coucou du bouton : " + ((JButton)event.getSource()).getText());
            //On affecte un nouveau libellé à une celulle de la ligne
            ((AbstractTableModel)table.getModel()).setValueAt("New Value " + (++nbre), this.row, (this.column -1));
            //Permet de dire à notre tableau qu'une valeur a changé à l'emplacement déterminé par les valeurs passées en paramètres
            ((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, this.column - 1);
            this.button = ((JButton)event.getSource());
        }
    }
}