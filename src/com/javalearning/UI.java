package com.javalearning;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B; // not recommend doing this in a loop because create object cost resources
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    double playTime;

    DecimalFormat dFormat = new DecimalFormat("#0.00"); //set numerical format

    public boolean isGameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {
        if (isGameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x, y;

            text = "You Found the Treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            x = gp.screenWidth/2 -textLength/2;
            y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "CONGRATULATIONS!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            x = gp.screenWidth/2 -textLength/2;
            y = gp.screenHeight/2 + gp.tileSize*2;
            g2.drawString(text,x,y);

            gp.gameThread = null;


        }else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasKeys, 74,65); //y means the baseline of the string

            //TIME
            playTime += (double) 1/60;
            g2.drawString("TIme" + dFormat.format(playTime), gp.tileSize*11, 65);

            if(messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F)); //change the size of the font
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageTimer++;

                if (messageTimer > 120) {
                    messageTimer = 0;
                    messageOn = false;
                }
            }
        }

    }


}
