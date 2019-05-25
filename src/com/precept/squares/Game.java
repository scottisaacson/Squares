/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/


package com.precept.squares;

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
import com.precept.squares.Squares;

public class Game {
    
    public enum GAMEOVER {
        NO,
        QUIT,
        YES
    }
    
    public GAMEOVER gameover = GAMEOVER.NO;
    
    public Player p1;
    public Player p2;
    public int cpi = -1;
    public Player current;
    public Player first;
    
    public ArrayList<Move> moves;
    public ArrayList<Move> redos;

    public SetupDialogNew setupDialog;
    public JFrame frame;
    public Board board;
    
    public boolean debug = false;
    
    public int tileGridCount;
    public int difficulty;
    
    public boolean interactive = false;
   
    public Game(JFrame frame) 
    {
        this(frame, Squares.BOARDSIZE, Squares.DIFF_LEVEL_3, null, null, false);
        // this(frame, 6, Squares.DIFF_LEVEL_7, null, null, false);
    }

    
    public Game(JFrame frame, boolean debug) 
    {
        this(frame, Squares.BOARDSIZE, Squares.DIFF_LEVEL_3, null, null, debug);
        // this(frame, 6, Squares.DIFF_LEVEL_7, null, null, debug);
        
    }

    public Game(JFrame frame, int boardSize, int difficulty, Player p1, Player p2, boolean debug) 
    {
        this.frame = frame;
        this.tileGridCount = boardSize;
        this.difficulty = difficulty;
        this.debug = debug;

        this.board = null;
        
        initPlayers(p1, p2);
        
        setNextPlayer();
        first = current;
        gameover = GAMEOVER.NO;

        moves = new ArrayList<Move>();
        redos = new ArrayList<Move>();
       
    }
    
    public Game(Game game) 
    {
        this(game.frame, game.tileGridCount, game.difficulty, game.p1, game.p2, game.debug);
    }
    

    
    public void playGame()
    {

        board = new Board(this, Dialog.ModalityType.APPLICATION_MODAL);
        board.initialBoard();

        if (gameover == GAMEOVER.NO)
        {
            takeATurn();
        }
        else
        {
    
            if (debug) System.out.println("Game Over");

            if (board != null) board.dispose();

            ResultDialog resultDialog = new ResultDialog(this);
            resultDialog.buildAndShow();
        }
            
    }

    /*
    public void takeATurn()
    {
        
        Thread turn = new Thread(new TakeATurn(this));
        
        turn.start();

        try
        {
            turn.join();
        }
        catch (InterruptedException e)
        {
            System.out.println(e.toString());
            gameover = Game.GAMEOVER.QUIT;
        }
        
    }
    */
    
    public void takeATurn()
    {
    
        if (debug) System.out.println("Starting " + current.name + "'s turn...");

        board.selected =  null;

        Player player = current;

        String stutusString = null;
        Color titleColor = null;
        if (current == null || current.name == null)
        {
             stutusString = "empty";
             titleColor = Color.white;
        }
        else
        {
            stutusString = current.name + "'s Turn";
            titleColor = current.color;
        }
        board.status.setText(stutusString);
        board.status.setBackground(titleColor);
       
        
        player.makeMove();
        
      
        return;
    }

    public void initPlayers(Player p1, Player p2)
    {

        if (p1 == null || p2 == null)
        {
            HumanPlayer newp1 = new HumanPlayer(this, "Player 1", Color.RED);
            this.p1 = newp1;

            ComputerPlayer newp2 = new ComputerPlayer(this, "Player 2", Color.BLACK);
            newp2.diff = this.difficulty;
            this.p2 = newp2;
        }
        else
        {
            if (p1 instanceof HumanPlayer)
            {
                HumanPlayer newp1 = new HumanPlayer(this, (HumanPlayer) p1);
                this.p1 = newp1;
            }
            else
            {
                ComputerPlayer newp1 = new ComputerPlayer(this, (ComputerPlayer) p1);
                this.p1 = newp1;
            }

            if (p2 instanceof HumanPlayer)
            {
                HumanPlayer newp2 = new HumanPlayer(this, (HumanPlayer) p2);
                this.p2 = newp2;
            }
            else
            {
                ComputerPlayer newp2 = new ComputerPlayer(this, (ComputerPlayer) p2);
                this.p2 = newp2;
            }
        }
    }

    public void setNextPlayer()
    {
        if (cpi == -1)
        {
            Random rand = new Random();
            cpi = rand.nextInt(2);
            if (cpi == 0) current = p1;
            if (cpi == 1) current = p2;
            first = current;
        }
        else
        {
            cpi++;
            if (cpi == 1)
            {
                current = p2;
            }
            else
            {
                cpi = 0;
                current = p1;
            }
        }
    }
    
    public boolean isOver()
    {
        
        for (int r = 0; r < tileGridCount; r++)
        {
            for (int c = 0; c < tileGridCount; c++)
            {
                if (board.tiles[r][c].owner == null)
                {
                    return false;
                }
            }
        }
        
        gameover = GAMEOVER.YES;
        return true;
    }
    
    public Result getResult()
    {
        Result r = new Result(this);
        
        return r;
        
    }
    
}


