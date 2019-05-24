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


public class ShowHumanScore  
{
    Board board;
    Game game;
    int points;

    public ShowHumanScore(Game game, int points)
    {
        this.game = game;
        this.board = game.board;
        this.points = points;
    }
    
    public void showSore()  
    { 
        startThread();  
    }

    private void startThread()  
    { 

        SwingWorker sw1 = new SwingWorker<String, Integer>()  
        { 

            @Override
            protected String doInBackground() throws Exception  
            { 
                int wait = 1000; //2000
                if (points == 0) wait = 300;  //500
                Thread.sleep(wait); 
                String res = "Done"; 
                return res; 
            } 

            @Override
            protected void process(java.util.List<Integer> chunks) 
            { 
                //
            } 

            @Override
            protected void done()  
            { 
                try 
                {
                    
                    if (game.board.previous != null) game.board.previous.highlight = false;
                    // if (game.board.selected != null) game.board.previous = game.board.selected;
                    
                    game.board.squares = null;
                    game.board.points.setText("0 Points");
                    game.board.points.setBackground(Color.WHITE);
                    
                    game.board.repaint();
                    

                    game.isOver();

                    if (game.gameover == Game.GAMEOVER.NO)
                    {
                        if (game.debug) System.out.println ("... Finishing " + game.current.name + "'s turn");
                        game.setNextPlayer();
                        game.takeATurn();
                    }
                    else
                    {

                        if (game.debug) System.out.println("Game Over");

                        if (board != null) board.dispose();

                        ResultDialog resultDialog = new ResultDialog(game);
                        resultDialog.buildAndShow();
                    }
                }  
                catch (Exception e)  
                { 
                    e.printStackTrace(); 
                    System.exit(-5);
                }  
            } 
        }; 

        // executes the swingworker on worker thread 
        sw1.execute();  
    } 

}
