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



public class HumanPlayer extends Player {

    boolean canUndo;
    boolean undoActive;
    
    public HumanPlayer(Game game, String name, Color color)
    {
        super(game, name, color);
        canUndo = true;
        undoActive = false;
    }

    public HumanPlayer(Game game, HumanPlayer player)
    {
        super(game, player);
        canUndo = true;
        undoActive = false;
    }
    
    @Override
    public void makeMove()
    {
        
        if (canUndo) undoActive = true;
        WaitForHumanSelection wait = new WaitForHumanSelection(game);
        wait.waitForSelection();
        
    }

}

