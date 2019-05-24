/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

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


public class StartComputerTurn  
{
    ComputerPlayer player;
    Board board;
    Game game;

    public StartComputerTurn(ComputerPlayer player)
    {
        this.player = player;
        this.game = player.game;
        this.board = game.board;
    }
    
    public void startTurn()  
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
                    player.finishMove();
              
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
