package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
//    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        try{
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
    }

    public void showMessage (String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState){

        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

//        if (gameFinished == true){
//
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//
//            String text;
//            int textLength;
//            int x;
//            int y;
//
//            text = "You found the treasure";
//            textLength = (int)g2.getFontMetrics().getStringBounds (text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - (gp.tileSize*3);
//            g2.drawString(text, x, y);
//
//            text = "Time spent: "+dFormat.format(playTime)+"!";
//            textLength = (int)g2.getFontMetrics().getStringBounds (text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize*4);
//            g2.drawString(text, x, y);
//
//            g2.setFont(arial_80B);
//            g2.setColor(Color.yellow);
//            text = "Congratulations";
//            textLength = (int)g2.getFontMetrics().getStringBounds (text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize*3);
//            g2.drawString(text, x, y);
//
//            gp.gameThread = null;
//
//        } else{
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x "+gp.player.hasKey,74,65);
//
//            playTime += (double)1/60;
//            g2.drawString ("Time:"+dFormat.format(playTime), gp.tileSize*11,65);
//
//            if (messageOn == true){
//
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
//
//                messageCounter++;
//
//                if (messageCounter>120){
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
    }

    public void drawTitleScreen(){

        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        //title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Blue Boy Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //shadow
        g2.setColor((Color.GRAY));
        g2.drawString(text,x+5,y+5);
        //text
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        //blue boy image
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x,y,gp.tileSize*2, gp.tileSize*2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "Start Game";
        x = getXforCenteredText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public void drawDialogueScreen(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth-(gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line: currentDialogue.split("/n")){
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
