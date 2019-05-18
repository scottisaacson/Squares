package squares;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
import java.text.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;


class Corner {

    public Game game;
    public int row;
    public int col;

    public Corner(Game game, int row, int col)
    {
        this.game = game;
        this.row = row;
        this.col = col;
    }
    
}
