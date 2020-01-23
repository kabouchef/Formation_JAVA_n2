package com.company.tableau;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//CTRL + SHIFT + O pour générer les imports
public class ButtonRenderer extends JButton implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
        //On écrit dans le bouton ce que contient la cellule
        setText((value != null) ? value.toString() : "");
        //On retourne notre bouton
        return this;
    }
}