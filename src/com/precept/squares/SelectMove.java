/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package com.precept.squares;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

class SelectMove extends JDialog {

    // public static final int BOARDSIZE = 8;
    public static final int GAP = 15;
    public static final int TOP = GAP;
    public static final int LEFT = GAP;

    Game game;
    Board board;
    
    TileGridPanel boardPanel;

    // Square selected;

    JButton ok;
    JButton quit;
    JLabel title;
    
    public SelectMove (Game game) {
        
        super(game.frame, "Select Move", Dialog.ModalityType.APPLICATION_MODAL);
        this.game = game;
        this.board = game.board;
        // selected = null;
    }
        
    public void buildAndShow () {
        
        Container pane = getContentPane();
        pane.setBackground(Squares.myback1);
        // setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);        
 
        pane.setLayout(null);
        Insets paneInsets = pane.getInsets();
        Insets outerInsets = getInsets();

        int boardPanelSize = 500;
        int boardPanelWidth = boardPanelSize;
        int boardPanelHeight = boardPanelSize;

        int fullWidth = boardPanelWidth + 20;
        int fullHeight = 90 + boardPanelHeight + 20  + 60 + 60 + 60;
        
        // Title
        
        int top = 20;
        int left = 10;

        
        title = new JLabel(game.current.name + "'s Turn");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(game.current.color);
        title.setOpaque(true);
        title.setFont(Squares.myfont18);
        title.setForeground(Color.WHITE);
        Dimension titleDim = title.getPreferredSize();
        titleDim.width += 100;
        titleDim.height = 50;
        title.setPreferredSize(titleDim);
        title.setMinimumSize(titleDim);
        title.setMaximumSize(titleDim);
        title.setBounds( (fullWidth - (titleDim.width)) / 2, top, titleDim.width, 50);
        title.setVisible(true);
        pane.add(title);
        
        // Board Panel

        top = top + 70;

        board.squares = new ArrayList<Square>();
        boardPanel = new TileGridPanel(game, boardPanelSize, true);
        boardPanel.setSize(boardPanelWidth, boardPanelHeight);
        boardPanel.setBackground(Squares.myback1);
        boardPanel.setBounds(0, top, boardPanelWidth, boardPanelHeight);
        boardPanel.buildAndShow();
        pane.add(boardPanel);
        
        // bottom buttons    
        
        top = top + boardPanelHeight + 20;
    
        
        int thisTop = top;
        int thisWidth = 100;
        int thisHeight = 50;
        int thisGap = 0;
        int thisLeft = 0;
        thisGap = (boardPanelWidth - 100 - 100) / 3;
        thisLeft = thisGap;
        
        quit = new JButton();
        quit.setText("QUIT");
        quit.setFont(Squares.myfont14);
        quit.addActionListener(new BoardQuitActionListener());        
        quit.setBounds(thisLeft, thisTop, thisWidth, thisHeight);
        quit.setVisible(true);
        pane.add(quit);
        
        thisLeft = thisGap + 100 + thisGap;
   
        
        
        setSize(fullWidth, fullHeight);
        setLocation(fullWidth + 50, 0);
        

        setVisible(true);

    }

    class BoardQuitActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            game.gameover = Game.GAMEOVER.QUIT;
            setVisible(false);
        }
    }    

    
}


