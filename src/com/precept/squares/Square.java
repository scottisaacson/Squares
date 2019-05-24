/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package com.precept.squares;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
import java.text.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;


class Square {

    Game game;
    ArrayList<Corner> corners;
    ArrayList<Tile> tiles;
    
    int size;
    boolean isValid;

    public Square(Game game)
    {
        this.game = game;
        this.isValid = false;
        corners = new ArrayList<Corner>();
        tiles = new ArrayList<Tile>();

   
    }

    public Square(Game game, Corner c1, Corner c2, Corner c3, Corner c4)
    {
        this.game = game;
        corners = new ArrayList<Corner>();
        tiles = new ArrayList<Tile>();
        
        corners.add(c1);
        corners.add(c2);
        corners.add(c3);
        corners.add(c4);

        this.isValid = false;
    }
    
    public void validate()
    {
        isValid = true;
        
        int minrow = game.tileGridCount + 1;
        int maxrow = -1;
        for (Corner c : corners)
        {
            if (c == null)
            {
                // if (game.debug) System.out.println("one of the corners is null.");
                isValid = false;
            }
            if (c.col < 0 || c.col >= game.tileGridCount)
            {
                // if (game.debug) System.out.println("one of the corner's col is out of bounds.");
                isValid = false;
            }
            if (c.row < 0 || c.row >= game.tileGridCount)
            {
                // if (game.debug) System.out.println("one of the corner's col is out of bounds.");
                isValid = false;
            }

            if (isValid)
            {
                if (c.row < minrow) minrow = c.row;
                if (c.row > maxrow) maxrow = c.row;

                Tile t = game.board.tiles[c.row][c.col];
                // if (game.debug) t.print();
                tiles.add(t);
            }
        }

        
        if (isValid)
        {
            Player owner = null;
            for (Tile t : tiles)
            {
                if (t.owner == null)
                {
                    // if (game.debug) System.out.println("one of the tiles has no owner");
                    isValid = false;
                }
                else
                {
                    if (owner == null)
                    {
                        owner = t.owner;
                    }
                    else
                    {
                        if (t.owner != owner)
                        {
                            // if (game.debug) System.out.println("owers are not the same");
                            isValid = false;
                        }
                    }
                }
            }
        }
        
        if (isValid)
        {
            size = (maxrow - minrow) + 1;
        }
        
        return;
    }
    
    public void print()
    {
        
        Corner c1 = corners.get(0);
        Corner c2 = corners.get(1);
        Corner c3 = corners.get(2);
        Corner c4 = corners.get(3);
        
        System.out.println("Corner 1: " + c1.row + ", " + c1.col);
        System.out.println("Corner 2: " + c2.row + ", " + c2.col);
        System.out.println("Corner 3: " + c3.row + ", " + c3.col);
        System.out.println("Corner 4: " + c4.row + ", " + c4.col);
        System.out.println("Size: " + size);
        
        // System.out.println("Size: " + size);
        
    }
    
    
}
