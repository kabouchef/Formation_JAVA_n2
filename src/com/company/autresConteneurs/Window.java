package com.company.autresConteneurs;

//CTRL + SHIFT + O pour générer les imports nécessaires

import javax.swing.*;
import java.awt.*;

public class Window extends JWindow{

    public Window(){
        setSize(220, 165);
        setLocationRelativeTo(null);
        JPanel pan = new JPanel();
        JLabel img = new JLabel(new ImageIcon("images/warning.png"));
        img.setVerticalAlignment(JLabel.CENTER);
        img.setHorizontalAlignment(JLabel.CENTER);
        pan.setBorder(BorderFactory.createLineBorder(Color.blue));
        pan.add(img);
        getContentPane().add(pan);
    }
}
