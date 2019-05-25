/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package com.precept.squares;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class Move {

    Player player;
    int score;
    Tile tile;
    
    
    public Move(Player p, Tile t, int s)
    {
        this.player = p;
        this.tile = t;
        this.score = s;
    }
    
}
