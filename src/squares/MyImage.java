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

public class MyImage extends JPanel {

    private Image myImage;

    public MyImage() {

        initPanel();
    }

    private void initPanel() {

        // loadImage();
        var dm = new Dimension(500, 300);
        setPreferredSize(dm);
    }

    /*
    private void loadImage() {

        myImage = new ImageIcon(Squares.Resources + "beach.jpg").getImage();
    }
    */

    private void doDrawing(Graphics g) {

        var g2d = (Graphics2D) g;
        
        int x, y;

        // Set the desired font if different from default font

        
        g2d.setColor(Squares.myback1);
        g2d.fillRect(0, 0, 400, 300);

        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        
        Font font = new Font("ARIEL", Font.BOLD, 36);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);

        
        
        // Draw a string such that its base line is at x, y
        // 400 x 300
        x = 110;
        y = 160;
        
        g2d.drawString("SQUARES", x, y);
        // FontMetrics fontMetrics = g2d.getFontMetrics();
        // Draw a string such that the top-left corner is at x, y
        // g2d.drawString("This is another test string", x, y+fontMetrics.getAscent());

        g2d.setColor(Color.red);
        g2d.fillRect(20, 20, 50, 50);
        g2d.fillRect(330, 230, 50, 50);

        g2d.setColor(Color.black);
        g2d.fillRect(330, 20, 50, 50);
        g2d.fillRect(20, 230, 50, 50);
        
        g2d.setStroke(new BasicStroke(5));        
        g2d.setColor(Color.orange);
        
        g.drawLine(45, 45, 45, 255);
        g.drawLine(45, 255, 355, 255);
        g.drawLine(355, 255, 355, 45);
        g.drawLine(355, 45, 45, 45);
        

        
        
        //g2d.drawImage(myImage, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}
