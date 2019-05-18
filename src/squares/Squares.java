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


public class Squares 
{
    //  Globals
    
    public static final Color myback1 = new Color(80, 140, 80);
    // public static final Color myback1 = new Color(50, 100, 50);
    public static final Color mydarkgrey = new Color(35, 35, 35);
    
    public static final Font myfont12 = new Font("Ariel", Font.BOLD, 12);
    public static final Font myfont14 = new Font("Ariel", Font.BOLD, 14);
    public static final Font myfont18 = new Font("Ariel", Font.BOLD, 18);
  
    public static final String Resources = "c:\\java\\Resources\\";  
    
    public static final int BOARDSIZE = 8;
    public static final int DIFF_LEVEL_1 = 1;
    public static final int DIFF_LEVEL_2 = 2;
    public static final int DIFF_LEVEL_3 = 3;
    public static final int DIFF_LEVEL_4 = 4;
    public static final int DIFF_LEVEL_5 = 5;
    public static final int DIFF_LEVEL_6 = 6;
    public static final int DIFF_LEVEL_7 = 7;
    
    public static boolean debug;
    public static Game game;
    
    public static JFrame frame;

    public static void main(String[] args) 
    {
        debug = false;
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                frame = new JFrame("Squares Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                game = null;

                Container pane = frame.getContentPane();
                pane.setLayout(null);
                pane.setBackground(mydarkgrey);
                Insets paneInsets = pane.getInsets();

                int controlsHeight = 40;
                int initialGap = 10;

                int imageWidth = 400;
                int imageHeight = 300;
                
                MyImage mi = new MyImage();
                mi.setVisible(true);
                mi.setBounds(initialGap, initialGap, imageWidth, imageHeight);
                Dimension imageDim = new Dimension(imageWidth, imageHeight);
                mi.setSize(imageDim);
                mi.setMaximumSize(imageDim);
                mi.setMinimumSize(imageDim);
                pane.add(mi);

                
                int quitWidth = 80;
                int setupWidth = 100;
                int playWidth = 80;
                
                int usedWidth = quitWidth + setupWidth + playWidth;
                int controlsGap = (imageWidth - usedWidth) / 2;

                int top = initialGap + imageHeight + initialGap;
                int left = initialGap;
                
                JButton quitButton = new JButton("QUIT");
                quitButton.setBounds(left, top, quitWidth, controlsHeight);
                quitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (game != null)
                        {
                            if (game.setupDialog != null)
                            {
                                game.setupDialog.dispose();
                                game.setupDialog = null;
                            }
                        }
                        System.exit(0);
                    }
                });
                pane.add(quitButton);

                left = initialGap + quitWidth + controlsGap;

                JButton setupButton = new JButton("SETUP");
                setupButton.setBounds(left, top, setupWidth, controlsHeight);
                setupButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (game == null)
                        {
                            game = new Game(frame, Squares.BOARDSIZE, debug);
                        }
                        else
                        {
                            Game newgame = new Game(game);
                            if (game.setupDialog != null)
                            {
                                game.setupDialog.dispose();
                                game.setupDialog = null;
                            }
                            game = newgame;
                        }

                        game.setupDialog = new SetupDialogNew(game);
                        game.setupDialog.buildAndShow();
                    }
                });
                pane.add(setupButton);

                left = initialGap + quitWidth + controlsGap + setupWidth + controlsGap;

                JButton playButton = new JButton("PLAY");
                playButton.setBounds(left, top, playWidth, controlsHeight);
                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        if (game == null)
                        {
                            game = new Game(frame, Squares.BOARDSIZE, debug);
                        }
                        else
                        {
                            Game newgame = new Game(game);
                            if (game.setupDialog != null)
                            {
                                game.setupDialog.dispose();
                                game.setupDialog = null;
                            }
                            game = newgame;
                        }

                        game.playGame();

                    }
                });
                pane.add(playButton);
                
                int fullHeight = top + controlsHeight + initialGap;
                int fullWidth = initialGap + imageWidth + initialGap;
                int fullWidth2 = initialGap + quitWidth + controlsGap + setupWidth + controlsGap + playWidth + initialGap;
                
                Insets outerInsets = frame.getInsets();
                frame.setSize(
                        fullWidth + 20,
                        fullHeight + 40);

                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);                

                frame.setVisible(true);

            }
        });
    }

}

