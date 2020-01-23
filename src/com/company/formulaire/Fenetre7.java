package com.company.formulaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre7 extends JFrame {
    private JButton bouton = new JButton("Appel à la ZDialog");

    public Fenetre7() {
        this.setTitle("Ma JFrame");
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(bouton);
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ZDialog zd = new ZDialog(null, "Coucou les ZérOs", true);
                ZDialogInfo zInfo = zd.showZDialog();
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, zInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.setVisible(true);
    }


}