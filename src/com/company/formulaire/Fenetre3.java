package com.company.formulaire;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Fenetre3 extends JFrame {

    private Panneau3 pan = new Panneau3();
    private JButton bouton = new JButton("Go");
    private JButton bouton2 = new JButton("Stop");
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Choix de la forme");
    private int compteur = 0;
    private boolean animated = true;
    private boolean backX, backY;
    private int x, y;
    private Thread t;
    private JComboBox combo = new JComboBox();

    private JCheckBox morph = new JCheckBox("Morphing");

    public Fenetre3() {
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.CENTER);
        bouton.addActionListener(new BoutonListener());
        bouton2.addActionListener(new Bouton2Listener());
        bouton2.setEnabled(false);
        JPanel south = new JPanel();
        south.add(bouton);
        south.add(bouton2);
        container.add(south, BorderLayout.SOUTH);
        combo.addItem("ROND");
        combo.addItem("CARRE");
        combo.addItem("TRIANGLE");
        combo.addItem("ETOILE");
        combo.addActionListener(new FormeListener3());
        morph.addActionListener(new MorphListener3());

        JPanel top = new JPanel();
        top.add(label);
        top.add(combo);
        top.add(morph);
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void go() {
        x = pan.getPosX();
        y = pan.getPosY();
        while (this.animated) {

            //Si le mode morphing est activé, on utilise la taille actuelle de la forme
            if (pan.isMorph()) {
                if (x < 1) backX = false;
                if (x > pan.getWidth() - pan.getDrawSize()) backX = true;
                if (y < 1) backY = false;
                if (y > pan.getHeight() - pan.getDrawSize()) backY = true;
            }
            //Sinon, on fait comme d'habitude
            else {
                if (x < 1) backX = false;
                if (x > pan.getWidth() - 50) backX = true;
                if (y < 1) backY = false;
                if (y > pan.getHeight() - 50) backY = true;
            }

            if (!backX) pan.setPosX(++x);
            else pan.setPosX(--x);
            if (!backY) pan.setPosY(++y);
            else pan.setPosY(--y);
            pan.repaint();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class BoutonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            animated = true;
            t = new Thread(new PlayAnimation3());
            t.start();
            bouton.setEnabled(false);
            bouton2.setEnabled(true);
        }
    }

    class Bouton2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous arrêter l'animation ?",
                    "Arrêt de l'animation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option != JOptionPane.NO_OPTION &&
                    option != JOptionPane.CANCEL_OPTION &&
                    option != JOptionPane.CLOSED_OPTION) {
                animated = false;
                bouton.setEnabled(true);
                bouton2.setEnabled(false);
            }
        }
    }
    class PlayAnimation3 implements Runnable {
        public void run() {
            go();
        }
    }

    class FormeListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pan.setForme(combo.getSelectedItem().toString());
        }
    }

    class MorphListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Si la case est cochée, on active le mode morphing
            if (morph.isSelected()) pan.setMorph(true);
                //Sinon, on ne fait rien
            else pan.setMorph(false);
        }
    }

}