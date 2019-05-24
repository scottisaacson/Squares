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

public class SetupDialogNew extends JDialog {

    public ArrayList<SetupPlayer> setupPlayers;
    public JFrame frame;
    public String name;
    public Game game;
    int boardSize;
    int difficulty;
    JButton ok;
    JButton apply;
    
    JLabel  boardSizeLabel;
    JTextField boardSizeText;
    JLabel  difficultyLabel;
    JTextField difficultyText;
 
    public SetupDialogNew(Game game) 
    {
        super(game.frame, "Setup", Dialog.ModalityType.APPLICATION_MODAL);
        
        this.frame = game.frame;
        this.game = game;
        
        this.setupPlayers = new ArrayList<SetupPlayer>();
        
        this.boardSize = game.tileGridCount;
        this.difficulty = game.difficulty;
        
    }
    
    public void buildAndShow() {
        
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.setBackground(Squares.myback1);

        int top = 10;
        int left = 10;
        int height = 40;
        
        boardSizeLabel = new JLabel("Board Size:");
        boardSizeLabel.setBackground(Squares.myback1);
        boardSizeLabel.setFont(Squares.myfont14);
        boardSizeLabel.setVisible(true);
        Dimension boardSizeLabelDim = boardSizeLabel.getPreferredSize();
        boardSizeLabel.setBounds(left, top,  boardSizeLabelDim.width, height);
        pane.add(boardSizeLabel);
        
        boardSizeText = new JTextField(Integer.toString(boardSize));
        boardSizeText.setFont(Squares.myfont14);
        boardSizeText.setVisible(true);
        Dimension boardSizeTextDim = boardSizeText.getPreferredSize();
        boardSizeTextDim.width = 40;
        boardSizeText.setPreferredSize(boardSizeTextDim);
        boardSizeText.setBounds((left + boardSizeLabelDim.width + 10), top, boardSizeTextDim.width, height);
        pane.add(boardSizeText);
        

        top = top + 50;


        difficultyLabel = new JLabel("Difficulty:");
        difficultyLabel.setBackground(Squares.myback1);
        difficultyLabel.setFont(Squares.myfont14);
        difficultyLabel.setVisible(true);
        Dimension difficultyLabelDim = difficultyLabel.getPreferredSize();
        difficultyLabel.setBounds(left, top,  boardSizeLabelDim.width, height);
        pane.add(difficultyLabel);
        
        difficultyText = new JTextField(Integer.toString(difficulty));
        difficultyText.setFont(Squares.myfont14);
        difficultyText.setVisible(true);
        Dimension difficultyTextDim = difficultyText.getPreferredSize();
        difficultyTextDim.width = 40;
        difficultyText.setPreferredSize(difficultyTextDim);
        difficultyText.setBounds((left + boardSizeLabelDim.width + 10), top, difficultyTextDim.width, height);
        pane.add(difficultyText);

        top= top + 50;
        
        layoutPlayers(top);
        
        top = top + (50 * setupPlayers.size()) + 20;
        
        JButton ok = new JButton("OK");
        ok.setFont(Squares.myfont14);
        ok.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {

                    apply();
                    
                    if (game.setupDialog != null) game.setupDialog.dispose();
                    game.setupDialog = null;
                }
            });
        ok.setBounds(10, top, 100, height);
        ok.setVisible(true);
        pane.add(ok);
        
        top = top + 110;
        
        Insets outerInsets = getInsets();
        setSize(
            400 + outerInsets.left + outerInsets.right,
            top + outerInsets.top + outerInsets.bottom);
        
        setVisible(true);
    }

    public void layoutPlayers(int top)
    {

        if (setupPlayers != null && setupPlayers.size() > 0)
        {
            for (SetupPlayer sp : setupPlayers)
            {
                sp.setVisible(false);
                remove(sp);
            }
        }
        
        setupPlayers.clear();
        
        int count = 1;
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(game.p1);
        players.add(game.p2);
        
        for (Player p : players)
        {
            SetupPlayer sp = new SetupPlayer(count, p);
            sp.setBounds(10, top + ((count - 1) * 50), 300, 40);
            sp.setVisible(true);
            setupPlayers.add(sp);
            add(sp);
            count++;
        }
       
        // this.repaint();
    }
    
    
    
    public void apply()
    {

        boardSize = Integer.parseInt(boardSizeText.getText());
        if (boardSize <= 4) boardSize = 4;
        if (boardSize >= 12) boardSize = 12;

        difficulty = Integer.parseInt(difficultyText.getText());
        if (difficulty <= 1) difficulty = 1;
        if (difficulty >= 7) difficulty = 7;

        game.tileGridCount = boardSize;
        game.difficulty = difficulty;
        
        SetupPlayer sp = setupPlayers.get(0);
        String name = sp.nameText.getText();
        if (sp.playerType.isSelected())
        {
            ComputerPlayer p = new ComputerPlayer(game, name, sp.player.color);
            p.diff = difficulty;
            game.p1 = p;
        }
        else
        {
            HumanPlayer p = new HumanPlayer(game, name, sp.player.color);
            game.p1 = p;
        }
        
        sp = setupPlayers.get(1);
        name = sp.nameText.getText();
        if (sp.playerType.isSelected())
        {
            ComputerPlayer p = new ComputerPlayer(game, name, sp.player.color);
            p.diff = difficulty;
            game.p2 = p;
        }
        else
        {
            HumanPlayer p = new HumanPlayer(game, name, sp.player.color);
            game.p2 = p;
        }
        
        
      
    }

}

