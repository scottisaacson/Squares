package squares;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

class TileGridPanel extends JPanel {
    
    public static final int GAP = 15;
    
    
    public Game game;
    public Board board;
    public int tileGridSize;
    
    public int tileSize;
    public boolean active;
    
    
    public TileGridPanel (Game game, int tileGridSize, boolean active) {
        
        super();
        this.game = game;
        this.board = game.board;
        this.tileGridSize = tileGridSize;
        this.active = active;
    }
        
    public void buildAndShow () {
        
        setBackground(Squares.myback1);

        setLayout(null);

        tileSize = (tileGridSize - (GAP * (game.tileGridCount - 1))) / game.tileGridCount;
        
        for (int r = 0; r < game.tileGridCount; r++)
        {
            for (int c = 0; c < game.tileGridCount; c++)
            {
                Tile t = board.tiles[r][c];
                TilePanel sp = new TilePanel(board, t, tileSize, active);
                sp.buildAndShow();
                add(sp);
            }
        }
        setSize(tileGridSize, tileGridSize);
        setVisible(true);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        
        if (board.squares == null || board.squares.size() == 0)
        {
            return;
        }
       
        Graphics2D g2 = (Graphics2D) g;
        Stroke stroke = g2.getStroke();
        Color c = g2.getColor();
        g2.setStroke(new BasicStroke(5));        
        g.setColor(Color.orange);
        
        int size = tileSize;

        for (Square s : board.squares)
        {
            Corner c1 = s.corners.get(0);
            Corner c2 = s.corners.get(1);
            Corner c3 = s.corners.get(2);
            Corner c4 = s.corners.get(3);

            // c1 to c2
            int p1x = ((size + 15) * c1.col) + (size / 2);
            int p1y = ((size + 15) * c1.row) + (size / 2);
            int p2x = ((size + 15) * c2.col) + (size / 2);
            int p2y = ((size + 15) * c2.row) + (size / 2);
            // System.out.println("C1 to C2: " + p1x + ", " + p1y + "     " + p2x + ", " + p2y);
            g.drawLine(p1x, p1y, p2x, p2y);
            
            
            // c2 to c3
            p1x = ((size + 15) * c2.col) + (size / 2);
            p1y = ((size + 15) * c2.row) + (size / 2);
            p2x = ((size + 15) * c3.col) + (size / 2);
            p2y = ((size + 15) * c3.row) + (size / 2);
            // System.out.println("C2 to C3: " + p1x + ", " + p1y + "     " + p2x + ", " + p2y);
            g.drawLine(p1x, p1y, p2x, p2y);
            
            // c3 to c4
            p1x = ((size + 15) * c3.col) + (size / 2);
            p1y = ((size + 15) * c3.row) + (size / 2);
            p2x = ((size + 15) * c4.col) + (size / 2);
            p2y = ((size + 15) * c4.row) + (size / 2);
            // System.out.println("C3 to C4: " + p1x + ", " + p1y + "     " + p2x + ", " + p2y);
            g.drawLine(p1x, p1y, p2x, p2y);
            
            // c4 to c1
            p1x = ((size + 15) * c4.col) + (size / 2);
            p1y = ((size + 15) * c4.row) + (size / 2);
            p2x = ((size + 15) * c1.col) + (size / 2);
            p2y = ((size + 15) * c1.row) + (size / 2);
            // System.out.println("C4 to C1: " + p1x + ", " + p1y + "     " + p2x + ", " + p2y);
            g.drawLine(p1x, p1y, p2x, p2y);
        }

        g2.setStroke(stroke);
        g2.setColor(c);

    }    
}

