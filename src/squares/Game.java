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
import squares.Squares;

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

    public SetupDialogNew setupDialog;
    public JFrame frame;
    public Board board;
    
    public boolean debug = false;
    
    public int tileGridCount;
    public int difficulty;
    
    public boolean interactive = false;
   
    
    
    public Game(JFrame frame, int boardSize, boolean debug) 
    {
    
        
        this.frame = frame;
        this.debug = debug;
        this.board = null;
        this.tileGridCount = boardSize;
        this.difficulty = Squares.DIFF_LEVEL_3;
        initPlayers();
        setNextPlayer();
        first = current;
        gameover = GAMEOVER.NO;
       
    }
    
    public Game(Game game) 
    {
    
        
        this.frame = game.frame;
        this.debug = game.debug;
        this.board = null;
        this.tileGridCount = game.tileGridCount;
        this.difficulty = game.difficulty;
        initPlayers(game.p1, game.p2);
        setNextPlayer();
        first = current;
        gameover = GAMEOVER.NO;
       
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

        // if (board.selected != null) board.selected.highlight = false;
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
        
        /*
        board.selected = player.selectMove();
        
        if (board.selected != null)
        {
            if (debug) System.out.println(current.name + " chose " + board.selected );
            
            board.playMove();
        }
        */

        // if (debug) System.out.println("Finishing " + current.name + "'s turn...");
      
        return;
    }
    public void initPlayers()
    {
        
        HumanPlayer p1 = new HumanPlayer(this, "Scott", Color.RED);
        this.p1 = p1;
        
        ComputerPlayer p2 = new ComputerPlayer(this, "Nina", Color.BLACK);
        p2.diff = Squares.DIFF_LEVEL_1;
        this.p2 = p2;
        
    }

    

    /* public void initPlayers()
    {
        
        ComputerPlayer p1 = new ComputerPlayer(this, "Nina", Color.RED);
        p1.diff = Squares6.DIFF_LEVEL_7;
        this.p1 = p1;
        
        *
        HumanPlayer p2 = new HumanPlayer(this, "Scott", Color.BLACK);
        this.p2 = p2;
        *
        ComputerPlayer p2 = new ComputerPlayer(this, "Scott", Color.BLACK);
        p2.diff = Squares6.DIFF_LEVEL_3;
        this.p2 = p2;
        
    }
    */
    
    public void initPlayers(Player p1, Player p2)
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


