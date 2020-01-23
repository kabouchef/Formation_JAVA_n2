/*
package com.company.formulaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre10 extends JFrame {
    private Panneau4 pan = new Panneau4();
    private JPanel container = new JPanel();
    private int compteur = 0;
    private boolean animated = true;
    private boolean backX, backY;
    private int x, y;
    private Thread t;

    private JMenuBar menuBar = new JMenuBar();

    private JMenu animation = new JMenu("Animation"),
            forme = new JMenu("Forme"),
            typeForme = new JMenu("Type de forme"),
            aPropos = new JMenu("À propos");

    private JMenuItem lancer = new JMenuItem("Lancer l'animation"),
            arreter = new JMenuItem("Arrêter l'animation"),
            quitter = new JMenuItem("Quitter"),
            aProposItem = new JMenuItem("?");

    private JCheckBoxMenuItem morph = new JCheckBoxMenuItem("Morphing");
    private JRadioButtonMenuItem carre = new JRadioButtonMenuItem("Carré"),
            rond = new JRadioButtonMenuItem("Rond"),
            triangle = new JRadioButtonMenuItem("Triangle"),
            etoile = new JRadioButtonMenuItem("Etoile");

    private ButtonGroup bg = new ButtonGroup();

    //Création de notre barre d'outils
    private JToolBar toolBar = new JToolBar();

    //Les boutons de la barre d'outils
    private JButton   play = new JButton(new ImageIcon("images/play.jpg")),
            cancel = new JButton(new ImageIcon("images/stop.jpg")),
            square = new JButton(new ImageIcon("images/carré.jpg")),
            tri = new JButton(new ImageIcon("images/triangle.jpg")),
            circle = new JButton(new ImageIcon("images/rond.jpg")),
            star = new JButton(new ImageIcon("images/étoile.jpg"));

    private Color fondBouton = Color.white;
    private FormeListener fListener = new FormeListener();

    public Fenetre10() {
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.CENTER);

        this.setContentPane(container);
        this.initMenu();
        this.initToolBar();
        this.setVisible(true);
    }

    private void initToolBar(){
        this.cancel.setEnabled(false);
        this.cancel.addActionListener(stopAnimation);
        this.cancel.setBackground(fondBouton);
        this.play.addActionListener(startAnimation);
        this.play.setBackground(fondBouton);

        this.toolBar.add(play);
        this.toolBar.add(cancel);
        this.toolBar.addSeparator();

        //Ajout des Listeners
        this.circle.addActionListener(fListener);
        this.circle.setBackground(fondBouton);
        this.toolBar.add(circle);

        this.square.addActionListener(fListener);
        this.square.setBackground(fondBouton);
        this.toolBar.add(square);

        this.tri.setBackground(fondBouton);
        this.tri.addActionListener(fListener);
        this.toolBar.add(tri);

        this.star.setBackground(fondBouton);
        this.star.addActionListener(fListener);
        this.toolBar.add(star);

        this.add(toolBar, BorderLayout.NORTH);
    }

    private void initMenu(){
        //Menu Animation
        //Ajout du listener pour lancer l'animation
        lancer.addActionListener(new StartAnimationListener());
        animation.add(lancer);

        //Ajout du listener pour arrêter l'animation
        arreter.addActionListener(new StopAnimationListener());
        arreter.setEnabled(false);
        animation.add(arreter);

        animation.addSeparator();
        quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        animation.add(quitter);

        //Menu Forme

        bg.add(carre);
        bg.add(triangle);
        bg.add(rond);
        bg.add(etoile);

        //On crée un nouvel écouteur, inutile de créer 4 instances différentes
        FormeListener fl = new FormeListener();
        carre.addActionListener(fl);
        rond.addActionListener(fl);
        triangle.addActionListener(fl);
        etoile.addActionListener(fl);

        typeForme.add(rond);
        typeForme.add(carre);
        typeForme.add(triangle);
        typeForme.add(etoile);

        rond.setSelected(true);

        forme.add(typeForme);

        //Ajout du listener pour le morphing
        morph.addActionListener(new MorphListener());
        forme.add(morph);

        //Menu À propos

        //Ajout de ce que doit faire le "?"
        aProposItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane jop = new JOptionPane();
                ImageIcon img = new ImageIcon("images/cysboy.gif");
                String mess = "Merci ! \n J'espère que vous vous amusez bien !\n";
                mess += "Je crois qu'il est temps d'ajouter des accélérateurs et des "+" mnémoniques dans tout ça…\n";
                mess += "\n Allez, GO les ZérOs !";
                jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE, img);
            }
        });
        aPropos.add(aProposItem);

        //Ajout des menus dans la barre de menus
        menuBar.add(animation);
        menuBar.add(forme);
        menuBar.add(aPropos);

        //Ajout des menus dans la barre de menus et ajout de mnémoniques !
        animation.setMnemonic('A');
        menuBar.add(animation);

        forme.setMnemonic('F');
        menuBar.add(forme);

        aPropos.setMnemonic('P');
        menuBar.add(aPropos);
        //Ajout de la barre de menus sur la fenêtre
        this.setJMenuBar(menuBar);

        //Ajout de la barre de menus sur la fenêtre
        this.setJMenuBar(menuBar);


    }


    private void go() {
        // Les coordonnées de départ de notre rond
        int x = pan.getPosX(), y = pan.getPosY();
        // Le booléen pour savoir si l'on recule ou non sur l'axe x
        boolean backX = false;
        // Le booléen pour savoir si l'on recule ou non sur l'axe y
        boolean backY = false;

        // Dans cet exemple, j'utilise une boucle while
        // Vous verrez qu'elle fonctionne très bien
        while (true) {
            // Si la coordonnée x est inférieure à 1, on avance
            if (x < 1)
                backX = false;
            // Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
            if (x > pan.getWidth() - 50)
                backX = true;
            // Idem pour l'axe y
            if (y < 1)
                backY = false;
            if (y > pan.getHeight() - 50)
                backY = true;

            // Si on avance, on incrémente la coordonnée
            // backX est un booléen, donc !backX revient à écrire
            // if (backX == false)
            if (!backX)
                pan.setPosX(++x);
                // Sinon, on décrémente
            else
                pan.setPosX(--x);
            // Idem pour l'axe Y
            if (!backY)
                pan.setPosY(++y);
            else
                pan.setPosY(--y);

            // On redessine notre Panneau
            pan.repaint();
            // Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class StartAnimationListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            //Toujours la même boîte de dialogue…

            if(option == JOptionPane.OK_OPTION){
                lancer.setEnabled(false);
                arreter.setEnabled(true);

                //ON AJOUTE L'INSTRUCTION POUR LE MENU CONTEXTUEL
                //************************************************
                launch.setEnabled(false);
                stop.setEnabled(true);

                play.setEnabled(false);
                cancel.setEnabled(true);

                animated = true;
                t = new Thread(new PlayAnimation());
                t.start();
            }
        }
    }

    */
/**
     * Écouteur du menu Quitter
     * @author CHerby
     *//*

    class StopAnimationListener  implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            //Toujours la même boîte de dialogue…

            if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){
                animated = false;
                //On remplace nos boutons par nos MenuItem
                lancer.setEnabled(true);
                arreter.setEnabled(false);

                //ON AJOUTE L'INSTRUCTION POUR LE MENU CONTEXTUEL
                //************************************************
                launch.setEnabled(true);
                stop.setEnabled(false);

                play.setEnabled(true);
                cancel.setEnabled(false);
            }
        }
    }

    class PlayAnimation implements Runnable {
        public void run() {
            go();
        }
    }

    class FormeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            //Si l'action vient d'un bouton radio du menu
            if(e.getSource().getClass().getName().equals("javax.swing.JRadioButtonMenuItem"))
                pan.setForme(((JRadioButtonMenuItem)e.getSource()).getText());
            else{
                if(e.getSource() == square){
                    carre.doClick();
                }
                else if(e.getSource() == tri){
                    triangle.doClick();
                }
                else if(e.getSource() == star){
                    etoile.doClick();
                }
                else{
                    rond.doClick();
                }
            }
        }
    }

    //Écoute le changement de couleur du fond
    class CouleurFondListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == vertBack)
                pan.setCouleurFond(Color.green);
            else if (e.getSource() == bleuBack)
                pan.setCouleurFond(Color.blue);
            else if(e.getSource() == rougeBack)
                pan.setCouleurFond(Color.red);
            else
                pan.setCouleurFond(Color.white);
        }
    }

    //Écoute le changement de couleur du fond
    class CouleurFormeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == vert)
                pan.setCouleurForme(Color.green);
            else if (e.getSource() == bleu)
                pan.setCouleurForme(Color.blue);
            else if(e.getSource() == rouge)
                pan.setCouleurForme(Color.red);
            else
                pan.setCouleurForme(Color.white);
        }
    }

    */
/**
     * Écoute le menu Morphing
     * @author CHerby
     *//*

    class MorphListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //Si la case est cochée, activation du mode morphing
            if(morph.isSelected()) pan.setMorph(true);
                //Sinon rien !
            else pan.setMorph(false);
        }
    }
}
*/
