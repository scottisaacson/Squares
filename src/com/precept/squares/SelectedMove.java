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

class SelectedMove extends JDialog {

    // public static final int BOARDSIZE = 8;
    public static final int GAP = 15;
    public static final int TOP = GAP;
    public static final int LEFT = GAP;
    
    ArrayList<Square> squares = null;
    
    
    Square played;
    TileGridPanel boardPanel;
    JFrame frame;
    Game game;

    JButton ok;
    JButton quit;
    JLabel title;
    JLabel p1Name;
    JLabel p2Name;
    JLabel p1Score;
    JLabel p2Score;
    
    public SelectedMove (Game game) {
        
        // super(frame, "Board", Dialog.ModalityType.MODELESS);
        super(game.frame, "Selected Move", Dialog.ModalityType.APPLICATION_MODAL);
        this.game = game;
        this.frame = game.frame;
        played = null;
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

        
        title = new JLabel(game.current.name + "'s Move");
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

        boardPanel = new TileGridPanel(game, boardPanelSize, false);
        boardPanel.setSize(boardPanelWidth, boardPanelHeight);
        boardPanel.setBackground(Squares.myback1);
        boardPanel.setBounds(0, top, boardPanelWidth, boardPanelHeight);
        boardPanel.buildAndShow();
        pane.add(boardPanel);
        
        // bottom buttons    

        top = top + boardPanelHeight + 20;
        int  thisLeft;

        //top = top + 60;
        
        int thisTop = top;
        int thisWidth = 100;
        int thisHeight = 50;
        int thisGap = 0;
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
        
        ok = new JButton();
        ok.setText("OK");
        ok.setFont(Squares.myfont14);
        ok.addActionListener(new BoardOKActionListener());
        ok.setBounds(thisLeft, thisTop, thisWidth, thisHeight);
        ok.setVisible(true);
        pane.add(ok);
        
        setSize(fullWidth, fullHeight);
        setLocation(fullWidth + 50, 0);                
        
        // repaint();
        //if (game.interactive == true)
        //{
            setVisible(true);
        //}

    }

    class BoardOKActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }    

    class BoardQuitActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            game.gameover = Game.GAMEOVER.QUIT;
            setVisible(false);
        }
    }    

    public int thisScore()
    {
        if (squares != null && squares.size() == 0)
        {
            return 0;
        }
        int retval = 0;
        for (Square s : squares)
        {
            retval = retval + s.size;
        }
        return retval;
    }


    
}


