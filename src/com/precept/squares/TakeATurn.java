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
import java.util.concurrent.*;
  
/*  
public class TakeATurn implements Runnable 
{  
    Game game;
    
    public TakeATurn(Game game)
    {
        this.game = game;
    }
    
    public void run()
    {
        if (game.debug) System.out.println("Starting " + game.current.name + "'s turn...");
        Board board = game.board;

        if (board.selected != null) board.selected.highlight = false;
        board.selected =  null;

        Player player = game.current;

        // board.selected = player.selectMove();
        
        if (board.selected != null)
        {
            if (game.debug) System.out.println(game.current.name + " chose " + board.selected );
            int ret = board.playMove();
        }

        if (game.debug) System.out.println("Finishing " + game.current.name + "'s turn...");
        return;
    } 
  
} 

*/