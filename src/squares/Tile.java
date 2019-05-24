/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package squares;

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


class Tile {

    Game game;
    int row;
    int col;
    Player owner;
    boolean highlight;

    public Tile(Game game, int row, int col, Player owner)
    {
        this.game = game;
        this.row = row;
        this.col = col;
        this.owner = owner;
        this.highlight = false;
    }
    
    public void print()
    {
        String ownerName = "null";
        if (owner != null)
        {
            ownerName = owner.name;
        }
        System.out.println("Square: " + ownerName + "," + row + "," + col);
    }
    
}
