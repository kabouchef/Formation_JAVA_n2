package com.company.autresConteneurs;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

//CTRL + SHIFT + O pour générer les imports nécessaires
public class BorderDemo extends JFrame {

    private String[] list = {
            "Bevel Border",
            "Etched Border",
            "Line Border",
            "Matted Border",
            "Raised Bevel Border",
            "Title Border",
            "Compound Border"
    };

    private Border[] listBorder = {
            BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.red),
            BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY),
            BorderFactory.createLineBorder(Color.green),
            BorderFactory.createMatteBorder(5, 2, 5, 2, Color.MAGENTA),
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createTitledBorder("Titre"),
            BorderFactory.createCompoundBorder(
                    BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.blue),
                    BorderFactory.createMatteBorder(5, 2, 5, 2, Color.MAGENTA)
            )
    };

    public BorderDemo(){
        this.setTitle("Les bordures font la fête !");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 200);

        JPanel pan = new JPanel();
        for(int i = 0; i < list.length; i++){
            JLabel lib = new JLabel(list[i]);
            lib.setPreferredSize(new Dimension(150, 50));
            lib.setBorder(listBorder[i]);
            lib.setAlignmentX(JLabel.CENTER);
            lib.setHorizontalAlignment(JLabel.CENTER);
            pan.add(lib);
        }

        this.getContentPane().add(pan);
    }

    public static void main(String[] args){
        BorderDemo demo = new BorderDemo();
        demo.setVisible(true);
    }
}