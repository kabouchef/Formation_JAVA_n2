package com.company.dragAndDrop;


import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

//CTRL + SHIFT + O pour générer les imports
public class MyTransferHandler extends TransferHandler{

    public boolean canImport(TransferHandler.TransferSupport info) {
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
    }

    public boolean importData(TransferHandler.TransferSupport support){
        if(!canImport(support))
            return false;

        Transferable data = support.getTransferable();
        String str = "";

        try {
            str = (String)data.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel lab = (JLabel)support.getComponent();
        lab.setText(str);

        return false;
    }

    protected void exportDone(JComponent c, Transferable t, int action){
        if(action == MOVE){
            JLabel lab = (JLabel)c;
            String text = lab.getText();
            int indice = Integer.parseInt(text.substring(text.length()-1, text.length()));
            lab.setText(text.substring(0, text.length()-1) + (++indice));
        }
    }

    protected Transferable createTransferable(JComponent c) {
        return new StringSelection(((JLabel)c).getText());
    }

    public int getSourceActions(JComponent c) {
        return MOVE;
    }
}