package com.company.dragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabelContentDemo extends JFrame{

    public LabelContentDemo(){
        setTitle("Drag'n Drop avec un JLabel !");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(2,2));
        pan.setBackground(Color.white);

        JLabel srcLib = new JLabel("Source de drag : ", JLabel.RIGHT);
        JLabel src = new JLabel("Texte à déplacer !");

        //--------------------------------------------------------
        //On utilise notre nouvel objet MyTransferHandle
        src.setTransferHandler(new MyTransferHandler());

        src.addMouseListener(new MouseAdapter(){

            public void mousePressed(MouseEvent e){
                System.out.println("EVENT !");
                JComponent lab = (JComponent)e.getSource();
                TransferHandler handle = lab.getTransferHandler();
                handle.exportAsDrag(lab, e, TransferHandler.COPY);
            }
        });
        //--------------------------------------------------------

        JLabel destLib = new JLabel("Destination de drag : ", JLabel.RIGHT);
        JTextField dest = new JTextField();

        dest.setDragEnabled(true);

        pan.add(srcLib);
        pan.add(src);
        pan.add(destLib);
        pan.add(dest);

        setContentPane(pan);
        setVisible(true);
    }
}