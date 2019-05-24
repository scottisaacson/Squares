/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package squares;


import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

class TilePanel extends JPanel
{
    
    Game game;
    Board board;
    boolean active;
    
    Tile tile;
    Color background;
    
    int tileSize;
    
    int left;
    int top;
    
    public TilePanel(Board board, Tile tile, int tileSize, boolean active)
    {
        super();
        this.board = board;
        this.game = board.game;
        this.tile = tile;
        this.tileSize = tileSize;
        this.active = active;
        
        this.left = (tile.col * (tileSize + TileGridPanel.GAP));
        this.top = (tile.row * (tileSize + TileGridPanel.GAP));
    }

    
    public void buildAndShow()
    {
       
        Dimension d = new Dimension(tileSize, tileSize);
        setLayout(null);
        setMinimumSize(d);
        setMaximumSize(d);
        setPreferredSize(d);
        setSize(d);
        setBounds(left, top, tileSize, tileSize);
   
        if (tile.owner == null)
        {
            background = Color.white; // new Color(234, 234, 234);
        }
        /*
        else if (square.highlight == true)
        {
            background = Color.MAGENTA;
        }
        */
        else
        {
            background = tile.owner.color;
        }
        setBackground(background);
        
        if (active) addMouseListener(new SelectTileListener());        
        
        setVisible(true);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Color c = g.getColor();
        Color background = Color.GRAY;
        if (tile == null || tile.owner == null)
        {
            background = new Color(222, 226, 133); //Color.GRAY;
        }
        else
        {
            background = tile.owner.color;
        }
        g.setColor(background);
        g.fillRect(0, 0, tileSize, tileSize);
        
        if (tile.highlight)
        {
            g.setColor(Color.MAGENTA);
            g.fillRect(0, 0, tileSize, 5);
            g.fillRect(tileSize - 5, 0, tileSize, tileSize);
            g.fillRect(0, tileSize - 5, tileSize, tileSize);
            g.fillRect(0, 0, 5, tileSize);
        }
        g.setColor(c);
        
    }


    class SelectTileListener implements MouseListener {


        public void mouseClicked(MouseEvent e) 
        {
            processMouseEvent(e);
        }
        public void mousePressed(MouseEvent e) 
        {
            processMouseEvent(e);
        }
        public void mouseReleased(MouseEvent e) 
        {
        }
        public void mouseEntered(MouseEvent e) 
        {
        }
        public void mouseExited(MouseEvent e) 
        {
        }
          
        public void processMouseEvent(MouseEvent e)
        {
              
            TilePanel thisTilePanel = (TilePanel) e.getComponent();

            Container c1 = thisTilePanel.getParent(); // BoardPanel   
            Container c2 = c1.getParent();  // Pane
            Container c3 = c2.getParent();   // Display
            Container c4 = c3.getParent();   // Root

            // SelectMove selectMove = (SelectMove) c4.getParent(); // SelectMove
            Board board = (Board) c4.getParent(); // Board
            
            // check to see if this has alread been played
            if (thisTilePanel.tile.owner == null)
            {
                board.selected = thisTilePanel.tile;
                
                // board.playMove();
                
                // selectMove.dispose();
                //board.dispose();
            }
        } 
    }
}


