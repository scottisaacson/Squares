/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/


package com.precept.squares;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;


class SetupPlayer extends JPanel {
    
    Player player;
    int number;
    JLabel nameLabel;
    JTextField nameText;
    JRadioButton playerType;
    
    public SetupPlayer (int number, Player player) {
        
        super();
        this.number = number;
        this.player = player;

        int height = 40;
        
        this.setLayout(null);

        nameLabel = new JLabel("Player " + this.number);
        nameLabel.setFont(Squares.myfont14);
        nameLabel.setBackground(Squares.myback1);
        nameLabel.setOpaque(true);
        Dimension labelDim = nameLabel.getPreferredSize();
        nameLabel.setSize(90, height);
        nameLabel.setBounds(0, 0, 100, height);
        nameLabel.setVisible(true);
        add(nameLabel);
        
        nameText = new JTextField(player.name);
        nameText.setFont(Squares.myfont14);
        Dimension textDim = nameText.getPreferredSize();
        nameText.setSize(100, height);
        nameText.setBounds(100, 0, 100, height);
        nameText.setVisible(true);
        add(nameText);
        
        playerType = new JRadioButton("Computer");
        if (player instanceof HumanPlayer)
        {
            playerType.setSelected(false);
        }
        else
        {
            playerType.setSelected(true);
        }
        playerType.setFont(Squares.myfont14);
        playerType.setBackground(Squares.myback1);
        playerType.setOpaque(true);
        Dimension td = playerType.getPreferredSize();
        playerType.setSize(200, height);
        playerType.setBounds(200, 0, 200, height);
        playerType.setVisible(true);
        add(playerType);

        setSize(500, height);
        
        setVisible(true);
        
    }
    
    
}

