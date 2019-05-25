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

class Board extends JDialog {

    Game game;
    
    public Tile selected;
    public Tile previous;
    public ArrayList<Square> squares;
    public Tile[][] tiles;

    public JButton undo;
    public JButton quit;
    
    public JLabel status;
    public JLabel points;
    public JLabel p1Name;
    public JLabel p2Name;
    public JLabel p1Score;
    public JLabel p2Score;
    public TileGridPanel boardPanel;
    
    
    public Board (Game game, Dialog.ModalityType type) {

        super(game.frame, "Board", Dialog.ModalityType.MODELESS);
        this.game = game;
        game.board = this;
        this.selected = null;
        this.previous = null;
        
        
        
        squares = null;
        
        tiles = new Tile[game.tileGridCount][game.tileGridCount];
        for (int r = 0; r < game.tileGridCount; r++)
        {
            for (int c = 0; c < game.tileGridCount; c++)
            {
                tiles[r][c] = new Tile(game, r, c, null);
            }
        }
    }
       
    
    public void initialBoard() {
        
        Container pane = getContentPane();
        pane.setBackground(Squares.myback1);
 
        pane.setLayout(null);
        Insets paneInsets = pane.getInsets();
        Insets outerInsets = getInsets();


        int heightGap = 20;
        int widthGap = 20;
        
        int nameAndScoresHeight = 50;
        int nameAndScoresHeightGap = heightGap;
        int tilesGridSize = 400;
        int tilesGridlHeight = tilesGridSize;
        int tilesGridHeightGap = heightGap;
        int statusHeight = 50;
        int statusHeightGap = heightGap;
        int controlsHeight = 40;

        
        int fullHeight = heightGap + nameAndScoresHeight + nameAndScoresHeightGap + tilesGridlHeight +
                tilesGridHeightGap + statusHeight + statusHeightGap + controlsHeight + heightGap;



        int tilesGridWidth = tilesGridSize;
        int fullWidth = widthGap + tilesGridWidth + widthGap;
        
        // scores

        int top = heightGap;
        int left = widthGap;
        
        Player p1 = game.p1;
        Player p2 = game.p2;
        
        int playerNameWidth = 140;
        int nameScoreGap = 10;
        int playerScoreWidth = 40;
        int namesAndScoresHeight = 40;
        
        
        p1Name = new JLabel(p1.name);
        p1Name.setHorizontalAlignment(SwingConstants.CENTER);
        p1Name.setBackground(p1.color);
        p1Name.setOpaque(true);
        p1Name.setFont(Squares.myfont14);
        p1Name.setForeground(Color.WHITE);
        Dimension p1NameDim = new Dimension(playerNameWidth, namesAndScoresHeight);
        p1Name.setPreferredSize(p1NameDim);
        p1Name.setMinimumSize(p1NameDim);
        p1Name.setMaximumSize(p1NameDim);

        p1Score = new JLabel(Integer.toString(p1.score));
        p1Score.setHorizontalAlignment(SwingConstants.CENTER);
        p1Score.setBackground(Color.white);
        p1Score.setOpaque(true);
        p1Score.setFont(Squares.myfont14);
        p1Score.setForeground(Color.black);
        Dimension p1ScoreDim = new Dimension(playerScoreWidth, namesAndScoresHeight);
        p1Score.setPreferredSize(p1ScoreDim);
        p1Score.setMinimumSize(p1ScoreDim);
        p1Score.setMaximumSize(p1ScoreDim);

        p2Name = new JLabel(p2.name);
        p2Name.setHorizontalAlignment(SwingConstants.CENTER);
        p2Name.setBackground(p2.color);
        p2Name.setOpaque(true);
        p2Name.setFont(Squares.myfont14);
        p2Name.setForeground(Color.WHITE);
        Dimension p2NameDim = new Dimension(playerNameWidth, namesAndScoresHeight);
        p2Name.setPreferredSize(p2NameDim);
        p2Name.setMinimumSize(p2NameDim);
        p2Name.setMaximumSize(p2NameDim);

        p2Score = new JLabel(Integer.toString(p2.score));
        p2Score.setHorizontalAlignment(SwingConstants.CENTER);
        p2Score.setBackground(Color.white);
        p2Score.setOpaque(true);
        p2Score.setFont(Squares.myfont14);
        p2Score.setForeground(Color.black);
        Dimension p2ScoreDim = new Dimension(playerScoreWidth, namesAndScoresHeight);
        p2Score.setPreferredSize(p2ScoreDim);
        p2Score.setMinimumSize(p2ScoreDim);
        p2Score.setMaximumSize(p2ScoreDim);


        int scoresWidth = playerNameWidth + nameScoreGap + playerScoreWidth + 
                playerNameWidth + nameScoreGap + playerScoreWidth; 
        int scoresWidthGap = (tilesGridWidth - scoresWidth) / 3;
        
        left = widthGap + scoresWidthGap;
        p1Name.setBounds(left, top, playerNameWidth, namesAndScoresHeight);
        p1Name.setVisible(true);
        pane.add(p1Name);
        
        left = left + playerNameWidth + nameScoreGap;
        p1Score.setBounds(left, top, playerScoreWidth, namesAndScoresHeight);
        p1Score.setVisible(true);
        pane.add(p1Score);
        
        left = left + playerScoreWidth + scoresWidthGap;
        p2Name.setBounds(left, top, playerNameWidth, namesAndScoresHeight);
        p2Name.setVisible(true);
        pane.add(p2Name);
        
        left = left + playerNameWidth + nameScoreGap;
        p2Score.setBounds(left, top, playerScoreWidth, namesAndScoresHeight);
        p2Score.setVisible(true);
        pane.add(p2Score);

        // Board Panel

        top = top + nameAndScoresHeight + nameAndScoresHeightGap;
        left = widthGap;

        boardPanel = new TileGridPanel(game, tilesGridSize, true);
        boardPanel.setSize(tilesGridWidth, tilesGridlHeight);
        boardPanel.setBackground(Squares.myback1);
        boardPanel.setBounds(left, top, tilesGridWidth, tilesGridlHeight);
        boardPanel.buildAndShow();
        pane.add(boardPanel);
        
        // Status
        
        top = top + tilesGridlHeight + tilesGridHeightGap;
        left = widthGap;

        int statusWidth = 200;
        int pointsWidth = 150;
        int pointsHeight = statusHeight;

        int statusRowWidth = statusWidth + pointsWidth;
        int statusRowGap = (tilesGridWidth - statusRowWidth) / 3;
        
        left = widthGap + statusRowGap;
        
        String stutusString = null;
        if (game.current == null || game.current.name == null)
        {
             stutusString = "empty";
        }
        else
        {
            stutusString = game.current.name + "'s Turn";
        }
        status = new JLabel(stutusString);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        Color statusColor = null;
        if (game.current == null || game.current.name == null)
        {
             statusColor = Color.white;
        }
        else
        {
            statusColor = game.current.color;
        }
        status.setBackground(statusColor);
        status.setOpaque(true);
        status.setFont(Squares.myfont18);
        status.setForeground(Color.WHITE);
        Dimension statusDim = status.getPreferredSize();
        statusDim.width = statusWidth;
        statusDim.height = statusHeight;
        status.setPreferredSize(statusDim);
        status.setMinimumSize(statusDim);
        status.setMaximumSize(statusDim);
        status.setBounds(left, top, statusWidth, statusHeight);
        status.setVisible(true);
        pane.add(status);
        
        left = left + statusWidth + statusRowGap;

        String pointsString = null;
        if (game.current == null || game.current.name == null)
        {
             pointsString = "0 Points";
        }
        else
        {
            pointsString = "0 Points";
        }
        points = new JLabel(pointsString);
        points.setHorizontalAlignment(SwingConstants.CENTER);
        Color pointsColor = null;
        if (game.current == null || game.current.name == null)
        {
             pointsColor = Color.WHITE;
        }
        else
        {
            pointsColor = Color.WHITE;
        }
        points.setBackground(pointsColor);
        points.setOpaque(true);
        points.setFont(Squares.myfont18);
        // points.setForeground(Color.BLACK);
        Dimension pointsDim = points.getPreferredSize();
        pointsDim.width = pointsWidth;
        pointsDim.height = pointsHeight;
        points.setPreferredSize(pointsDim);
        points.setMinimumSize(pointsDim);
        points.setMaximumSize(pointsDim);
        points.setBounds(left, top, pointsWidth, pointsHeight);
        points.setVisible(true);
        pane.add(points);
        
        // bottom buttons    

        top = top + statusHeight + heightGap;
        
        int quitWidth = 80;
        int okWidth = 80;
        int controlsWidth = quitWidth + okWidth;
        int controlsGap = (tilesGridWidth - controlsWidth) / 3;

        left = widthGap + controlsGap;
        
        quit = new JButton();
        quit.setText("QUIT");
        quit.setFont(Squares.myfont12);
        quit.addActionListener(new BoardQuitActionListener());        
        quit.setBounds(left, top, quitWidth, controlsHeight);
        quit.setVisible(true);
        pane.add(quit);
        
        left = left + quitWidth + controlsGap;
        undo = new JButton();
        undo.setText("UNDO");
        undo.setFont(Squares.myfont12);
        undo.addActionListener(new BoardUndoActionListener());
        undo.setBounds(left, top, okWidth, controlsHeight);
        undo.setVisible(true);
        pane.add(undo);
        
        
        setSize(fullWidth + 20, fullHeight + 50);
        setLocation(0, 0);                

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2)-(getSize().width/2), (dim.height/2)-(getSize().height/2));                
        
        setVisible(true);

    }
    
    
    public int playMove()
    {
        int ret = 0;
        
        if (game.gameover == Game.GAMEOVER.NO)
        {
            if (selected == null)
            {
                System.out.println("ERROR: no move selected");
                System.exit(-1);
            }
            
            if (selected != null) previous = selected;
            
            
            selected.highlight = true;
            selected.owner = game.current;

            findBoardSquares();
            

            int thisScore = thisScore();
            game.current.score += thisScore;

            Move m = new Move(game.current, selected, thisScore);
            game.moves.add(m);
            
            if (thisScore == 0)
            {
                game.board.points.setText("0 Points");
                game.board.points.setBackground(Color.WHITE);
            }
            else if (thisScore == 1)
            {
                game.board.points.setText(Integer.toString(thisScore) + " Point");
                game.board.points.setBackground(Color.ORANGE);
            }
            else
            {
                game.board.points.setText(Integer.toString(thisScore) + " Points");
                game.board.points.setBackground(Color.ORANGE);
            }

            ret = thisScore;
            
            if (game.debug)
            {
                System.out.println("Found " +  squares.size() + " squares for a score of " + thisScore);
                System.out.println(game.current.name + "'s new score is " + game.current.score);
            }

            p1Score.setText(Integer.toString(game.p1.score));
            p2Score.setText(Integer.toString(game.p2.score));

            HumanPlayer h = null;
            if (game.current instanceof HumanPlayer)
            {
                h = (HumanPlayer) game.current;
                h.undoActive = false;
            }
            
            
            repaint();
            
        }
        
        return ret;
    }

    public void setCurrentPlayer(Player current)
    {
        status.setBackground(current.color);
        status.setText(current.name + "'s Turn");
        game.board.points.setText("");

        p1Score.setText(Integer.toString(game.p1.score));
        p2Score.setText(Integer.toString(game.p2.score));

        repaint();

    }


    class BoardUndoActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            HumanPlayer h = null;
            if (game.current instanceof HumanPlayer)
            {
                h = (HumanPlayer) game.current;
            }
            
            if ( (game.moves.size() > 1) && (h != null) && (h.undoActive == true) )
            {
                Move last = game.moves.get(game.moves.size() - 1);
                last.tile.owner = null;
                last.tile.highlight = false;
                last.player.score = last.player.score - last.score;
                game.moves.remove(game.moves.size() - 1);

                last = game.moves.get(game.moves.size() - 1);
                last.tile.owner = null;
                last.tile.highlight = false;
                last.player.score = last.player.score - last.score;
                game.moves.remove(game.moves.size() - 1);

                p1Score.setText(Integer.toString(game.p1.score));
                p2Score.setText(Integer.toString(game.p2.score));

                repaint();
                
            }
                
        }
    }    

    class BoardQuitActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    
    
    
    public void findBoardSquares()
    {
        
       squares = new ArrayList<Square>();
        
        int thisRow = selected.row;
        int thisCol = selected.col;
        
        for (int row = 0; row < game.tileGridCount; row++)
        {
            for (int col = 0; col < game.tileGridCount; col++)
            {
                if (thisRow == row && thisCol == col)
                {
                    // do nothing
                }
                else
                {
                    
                    Tile t = tiles[thisRow][thisCol];
                    // if (game.debug) System.out.println("selected = " + thisRow + ", " + thisCol);
                    // if (game.debug) t.print();

                    int over = thisRow - row;
                    int up = thisCol - col;
                    Corner c1 = new Corner(game, thisRow, thisCol);
                    Corner c2 = new Corner(game, row, col);
                    Corner c3 = new Corner(game, row + up, col - over);
                    Corner c4 = new Corner(game, thisRow + up, thisCol - over);
                    
                    Square s = new Square(game, c1, c2, c3, c4);
                    // if (game.debug) System.out.println("Found a possible board square: ");
                    // if (game.debug) s.print();
                    s.validate();
                    if (s.isValid)
                    {
                        if (game.debug)
                        {
                            // System.out.println("Found a valid square to score");
                            // System.out.print(s.corners.get(0).toString());
                        }
                        squares.add(s);
                    }
                }
            }
        }
        
    }
    
    
    public int thisScore()
    {
        if (squares != null && squares.size() == 0)
        {
            return 0;
        }
        int retval = 0;
        for (Square bs : squares)
        {
            retval = retval + bs.size;
        }
        return retval;
    }
}


