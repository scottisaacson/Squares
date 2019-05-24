/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package squares;


import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;



public abstract class Player {

    
    Game game;
    String name;
    Color color;
    boolean debug;
    int score;
    
    
    public Player(Game game, String name, Color color)
    {
        this.game = game;
        this.name = name;
        this.color = color;
        this.debug = game.debug;
        this.score = 0;
    }

    public Player(Game game)
    {
        this(game, (Player) null);
    }
    
    public Player(Game game, Player player)
    {
        this.game = game;
        this.name = player.name;
        this.color = player.color;
        this.debug = game.debug;
        this.score = 0;
    }

   
    public abstract void makeMove();
    

}
