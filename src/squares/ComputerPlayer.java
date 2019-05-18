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



public class ComputerPlayer extends Player {

    int diff;
    
    public ComputerPlayer(Game game, String name, Color color)
    {
        super(game, name, color);
        diff = game.difficulty;
        
    }

    public ComputerPlayer(Game game, ComputerPlayer player)
    {
        super(game, player);
        diff = player.diff;
    }
    
    
    
    
    public MoveScore findScoreForThisMove(Tile t, Tile[][] board)
    {
        
        int row;
        int col;
        
        int size  = game.tileGridCount;
        
        ArrayList<Square> squares = new ArrayList<Square>();
        
        int thisRow = t.row;
        int thisCol = t.col;
        
        for (row = 0; row < size; row++)
        {
            for (col = 0; col < size; col++)
            {
                if (thisRow == row && thisCol == col)
                {
                    // do nothing
                }
                else
                {
                    
                    Tile tmpSquare = board[thisRow][thisCol];
                    int over = thisRow - row;
                    int up = thisCol - col;
                    Corner c1 = new Corner(game, thisRow, thisCol);
                    Corner c2 = new Corner(game, row, col);
                    Corner c3 = new Corner(game, row + up, col - over);
                    Corner c4 = new Corner(game, thisRow + up, thisCol - over);
                    
                    Square s = new Square(game, c1, c2, c3, c4);
                    s.validate();
                    if (s.isValid)
                    {
                        squares.add(s);
                    }
                    else
                    {
                    }
                }
            }
        }
        
        int score = 0;
        for (Square bs : squares)
        {
            score = score + bs.size;
        }
        
        MoveScore ret = new MoveScore(t, score);
        
        return ret;
    }

    public ArrayList<MoveScore> getMoveScores(Tile[][] board)
    {
        
        int row;
        int col;
        
        int size  = game.tileGridCount;
        
        Tile tmpSquare;
        ArrayList<Tile> allPossibleMoves = new ArrayList<Tile>();
        
        for (row = 0; row < size; row++)
        {
            for (col = 0; col < size; col++)
            {
                tmpSquare = board[row][col];
                if (tmpSquare.owner == null)
                {
                    allPossibleMoves.add(tmpSquare);
                }
            }
        }
        
        int allPossibleMovesCount = allPossibleMoves.size();
        // if (game.debug) System.out.println("All Possible Moves Count: " + allPossibleMovesCount);
        // if (game.debug) System.out.println("Difficulty: " + diff);
        float percent = (100.0f - ( (Squares.DIFF_LEVEL_7 - diff) * 14.3f));
        int whichPossibleMovesCount = (int) (allPossibleMovesCount * (percent/100.0f));
        if (whichPossibleMovesCount <= 0) whichPossibleMovesCount = 1;
        if (whichPossibleMovesCount >= allPossibleMovesCount) whichPossibleMovesCount = allPossibleMovesCount;
        // if (game.debug) System.out.println("Which Possible Moves Count: " + whichPossibleMovesCount);
        Random rand = new Random();
        int thisIdx = 0;
        
        ArrayList<Tile> whichPossibleMoves = new ArrayList<Tile>();
        int tmpCounter = whichPossibleMovesCount;
        while (tmpCounter > 0)
        {
            thisIdx = rand.nextInt(allPossibleMoves.size());
            Tile s = allPossibleMoves.remove(thisIdx);
            // if (game.debug) s.print();
            whichPossibleMoves.add(s);
            tmpCounter--;
        }
        
        if (whichPossibleMoves.size() != whichPossibleMovesCount)
        {
            System.out.println("ERROR in which possible moves count");
            System.exit(-3);
        }

        ArrayList<MoveScore> moveScores = new ArrayList<MoveScore>();

        for (Tile s : whichPossibleMoves)
        {

            // Find the best plays
            Player thisPlayer = null;
            Player otherPlayer = null;
            MoveScore ms = null;

            thisPlayer = game.current;
            if (game.p1 == thisPlayer)
            {
                otherPlayer = game.p2;
            }
            else
            {
                otherPlayer = game.p1;
            }

            // find the best play for this player
            s.owner = thisPlayer;
            ms = findScoreForThisMove(s, board);
            moveScores.add(ms);
            s.owner = null;

            // find the best play for this player
            s.owner = otherPlayer;
            ms = findScoreForThisMove(s, board);
            moveScores.add(ms);
            s.owner = null;
        }
        
        return moveScores;
    }

    @Override
    public void makeMove()
    {
        Game game = this.game;
        
        
        // Since this is a computer, wait for a sec to start
        
        StartComputerTurn start = new StartComputerTurn(this);
        start.startTurn();
        
    }
    
    public void finishMove()
    {
        
        if (game.debug)
        {
            ComputerPlayer c = (ComputerPlayer) game.current;
            System.out.println(game.current.name  + "'s difficulty = " + c.diff);
        }
        ArrayList<MoveScore> moveScores = getMoveScores(game.board.tiles);
        
        if (moveScores == null || moveScores.size() == 0)
        {
            return;
        }
        
        if (game.debug)
        {
            System.out.println("Found " + moveScores.size() + " possible moves for " + game.current.name);
            for (MoveScore m : moveScores)
            {
                // m.s.print();
                // System.out.println(m.score);
            }
        }
        
        Tile ret = null;
        int retScore = -1;

        for (MoveScore m : moveScores)
        {
            if (m.score > retScore)
            {
                ret = m.s;
                retScore = m.score;
            }
        }
        
        ArrayList<MoveScore> maxMoveScores = new ArrayList<MoveScore>();

        for (MoveScore m : moveScores)
        {
            if (m.score == retScore)
            {
                maxMoveScores.add(m);
            }
        }

        if (game.debug)
        {
            System.out.println("Found " + maxMoveScores.size() + " max possible moves for " + game.current.name);
            for (MoveScore m : maxMoveScores)
            {
                // m.s.print();
                // System.out.println(m.score);
            }
        }

        
        Random rand = new Random();
        int idx = rand.nextInt(maxMoveScores.size());
        MoveScore maxMoveScore = maxMoveScores.get(idx);

        if (game.debug)
        {
            System.out.println("Found the following best possible move for " + game.current.name);
            maxMoveScore.s.print();
            System.out.println("with a score of " + maxMoveScore.score);
        }

        
        if (maxMoveScore == null || maxMoveScore.s == null)
        {
            System.out.println("ERROR: no valid move");
            System.exit(-6);
        }
        
        game.board.selected = maxMoveScore.s;

        int points = game.board.playMove();

        ShowHumanScore showScore = new ShowHumanScore(game, points);
        showScore.showSore();

        /*
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

            if (game.board != null) game.board.dispose();

            ResultDialog resultDialog = new ResultDialog(game);
            resultDialog.buildAndShow();
        }
        
        */
        
        return ;
    }
    
}


    
    
