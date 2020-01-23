package com.company.fenetre;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fenetre extends JFrame{

    private Panneau pan = new Panneau();
    private Bouton bouton = new Bouton("Go");
    private Bouton bouton2 = new Bouton("Stop");
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Le JLabel");
    private int compteur = 0;
    private boolean animated = true;
    private boolean backX, backY;
    private int x, y;
    private Thread t;

    public Fenetre(){
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.CENTER);
        bouton.addActionListener(new BoutonListener());
        bouton.setEnabled(false);
        bouton2.addActionListener(new Bouton2Listener());

        JPanel south = new JPanel();
        south.add(bouton);
        south.add(bouton2);
        container.add(south, BorderLayout.SOUTH);
        Font police = new Font("Tahoma", Font.BOLD, 16);
        label.setFont(police);
        label.setForeground(Color.blue);
        label.setHorizontalAlignment(JLabel.CENTER);
        container.add(label, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
        go();
    }

    private void go(){
        //Les coordonnées de départ de notre rond
        x = pan.getPosX();
        y = pan.getPosY();
        //Dans cet exemple, j'utilise une boucle while
        //Vous verrez qu'elle fonctionne très bien
        while(this.animated){
            if(x < 1)backX = false;
            if(x > pan.getWidth()-50)backX = true;
            if(y < 1)backY = false;
            if(y > pan.getHeight()-50)backY = true;
            if(!backX)pan.setPosX(++x);
            else pan.setPosX(--x);
            if(!backY) pan.setPosY(++y);
            else pan.setPosY(--y);
            pan.repaint();

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class BoutonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            animated = true;
            t = new Thread(new PlayAnimation());
            t.start();
            bouton.setEnabled(false);
            bouton2.setEnabled(true);

        }
    }

    class Bouton2Listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            animated = false;
            bouton.setEnabled(true);
            bouton2.setEnabled(false);
        }
    }

    class PlayAnimation implements Runnable{
        public void run(){
            go();
        }
    }

}