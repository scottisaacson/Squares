/****************************************************************************
 * 2019 The Precept Group, LLC. 
 * See LICENSE for license information.
 ***************************************************************************/

package squares;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;

    

public class Result {
    
    ArrayList<Score> players;
    ArrayList<Score> winners;
    String first;
    Game.GAMEOVER gameover;
 
    public Result (Game game)
    {
        
        gameover = game.gameover;
        first = game.first.name;
        
        ArrayList<Player> ps = new ArrayList<Player>();
        ps.add(game.p1);
        ps.add(game.p2);
        this.players = new ArrayList<Score>();
        this.winners = new ArrayList<Score>();
        
        
        if (gameover == Game.GAMEOVER.YES)
        {
            for (Player p : ps)
            {
                Score s = new Score(p.name, p.score);
                players.add(s);
            }

            int maxScore = -1;
            Score maxPlayer = null;
            while (players.size() > 0)
            {
                maxScore = -1;
                maxPlayer = null;
                for (Score s : players)
                {
                    if (s.score > maxScore)
                    {
                        maxScore = s.score;
                        maxPlayer = s;
                    }
                }
                winners.add(maxPlayer);
                players.remove(maxPlayer);
            }
        
        }
        else if (gameover == Game.GAMEOVER.QUIT)
        {
            
            // 
        }
            
        
    }
    
    public void print()
    {
                
        StringBuffer sb = new StringBuffer();

        boolean first = true;
        while (winners.size() > 0)
        {
            if (!first)
            {
                sb.append(", ");
            }
            Score s = winners.remove(0);
            sb.append(s.player);
            first = false;
        }
        // System.out.println(sb.toString());

    }
    
}

