package squares;

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

    public ShowHumanScore(Game game)
    {
        this.game = game;
        this.board = game.board;
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
                Thread.sleep(1000); 
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
