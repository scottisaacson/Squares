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

public class ResultDialog extends JDialog {

    public Game game;
    public Result result;
    public JFrame frame;
    public String name;
    public int size;
    public ArrayList<Player> players;
 
    public ResultDialog(Game game) 
    {
        // super(game.frame, "Result");
        super(game.frame, "Result", Dialog.ModalityType.APPLICATION_MODAL);
        this.frame = game.frame;
        this.result = game.getResult();
        this.size = this.result.winners.size();
    }
    
    public void buildAndShow() {
        
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.setBackground(Squares.mydarkgrey);


        
        if (result.gameover == Game.GAMEOVER.YES)
        {
            int top = 20;
    
            var title = new JLabel("GAME OVER");
            title.setBackground(Color.white);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setVerticalAlignment(JLabel.CENTER);        
            title.setOpaque(true);
            title.setFont(Squares.myfont18);
            title.setBounds(20, top, 435, 50);
            title.setVisible(true);
            pane.add(title);

            top = top + 60;

            boolean first = true;
            for (Score s : result.winners)
            {
                JLabel pl = new JLabel(s.player + "  ");
                pl.setBackground(Color.white);
                pl.setHorizontalAlignment(JLabel.RIGHT);
                pl.setVerticalAlignment(JLabel.CENTER);        
                pl.setOpaque(true);
                pl.setFont(Squares.myfont18);
                pl.setBounds(20, top, 100, 50);
                pl.setVisible(true);
                pane.add(pl);

                JLabel pv = new JLabel(Integer.toString(s.score));
                if (first)
                {
                    pv.setBackground(Color.orange);
                }
                else
                {
                    pv.setBackground(Color.white);
                }
                first = false;
                pv.setHorizontalAlignment(JLabel.CENTER);
                pv.setVerticalAlignment(JLabel.CENTER);        
                pv.setOpaque(true);
                pv.setFont(Squares.myfont18);
                pv.setBounds(20 + 100 + 20, top, 50, 50);
                pv.setVisible(true);
                pane.add(pv);

                top = top + 60;
            }
        }
        else
        {
            int top = 20;
    
            var title = new JLabel("GAME QUIT");
            title.setBackground(Color.white);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setVerticalAlignment(JLabel.CENTER);        
            title.setOpaque(true);
            title.setFont(Squares.myfont18);
            title.setBounds(20, top, 435, 50);
            title.setVisible(true);
            pane.add(title);
            
            top = top + 70;
            
        }
        
        JButton ok = new JButton("OK");
        ok.setFont(Squares.myfont14);
        ok.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // game = new Game(game);
                    dispose();
                }
            });
        Dimension od = ok.getPreferredSize();
        ok.setBounds(350, ((size * 50) + 150), 75, 50);
        ok.setVisible(true);
        pane.add(ok);
        
        Insets outerInsets = getInsets();
        setSize(
                510 + outerInsets.left + outerInsets.right,
                ((size * 50) + 250) + outerInsets.top + outerInsets.bottom);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);                
        
        setVisible(true);

    }
}

