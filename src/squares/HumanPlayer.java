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



public class HumanPlayer extends Player {

    public HumanPlayer(Game game, String name, Color color)
    {
        super(game, name, color);
    }

    public HumanPlayer(Game game, HumanPlayer player)
    {
        super(game, player);
    }
    
    @Override
    public void makeMove()
    {
        
        WaitForHumanSelection wait = new WaitForHumanSelection(game);
        wait.waitForSelection();
        
    }

}

