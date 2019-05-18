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


public class WaitForHumanSelection  
{
    Board board;
    Game game;

    public WaitForHumanSelection(Game game)
    {
        this.game = game;
        this.board = game.board;
    }
    
    public void waitForSelection()  
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
                // define what thread will do here 
                while (board.selected == null && board.game.gameover == Game.GAMEOVER.NO)  
                { 
                    Thread.sleep(100); 
                    // System.out.println("Value in thread : " + i); 
                    // publish(i); 
                } 

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

                    if (game.gameover != Game.GAMEOVER.NO)
                    {
                        if (game.debug) System.out.println("Game Over");

                        if (board != null) board.dispose();

                        ResultDialog resultDialog = new ResultDialog(game);
                        resultDialog.buildAndShow();
                        
                    }
                    else
                    {
                        int points = board.playMove();
                        
                        ShowHumanScore showScore = new ShowHumanScore(game, points);
                        showScore.showSore();
                        
                        /*
                        game.isOver();
                        
                        if (game.gameover == Game.GAMEOVER.NO)
                        {
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
                        */
                        
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
