package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Entity {

    GamePanel gp;
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2,left1,left2,right1,right2;
    public BufferedImage axeUp1, axeUp2, axeDown1, axeDown2, axeLeft1, axeLeft2, axeRight1, axeRight2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int life;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle axeArea = new Rectangle (0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn =false;

    public int invincibleCounter=0;
    public boolean invincible;

    boolean axing = false;


    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public BufferedImage image;
    public String name;
    public boolean collision = false;





    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction () {}

    public void speak() {
        // Loop until we find a non-null dialogue or wrap around to the beginning
        while (dialogueIndex < dialogues.length && dialogues[dialogueIndex] == null) {
            dialogueIndex++;
        }

        // If the dialogueIndex reaches the end or exceeds the array length, reset it
        if (dialogueIndex >= dialogues.length) {
            dialogueIndex = 0;
        }

        // Display the current dialogue if it's not null
        if (dialogues[dialogueIndex] != null) {
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            dialogueIndex++;
        }

        // Ensure dialogueIndex wraps around correctly if it reaches the end
        if (dialogueIndex >= dialogues.length) {
            dialogueIndex = 0;
        }

        // Adjust direction for interaction feedback
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }


    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkEntity(this, gp.iTile);

        if(collisionOn == false){
            switch(direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum==1){
                spriteNum=2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
        if(invincible == true){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void setDialogues(String[] dialogues) {
        this.dialogues = dialogues;
    }


    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.worldX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.worldY){
            switch (direction){
                case "up":
                    if (spriteNum==1){image = up1;}
                    if(spriteNum ==2){image = up2;}
                    break;
                case "down":
                    if (spriteNum==1){image = down1;}
                    if(spriteNum ==2){image = down2;}
                    break;
                case "left":
                    if (spriteNum==1){image = left1;}
                    if(spriteNum ==2){image = left2;}
                    break;
                case "right":
                    if (spriteNum==1){image = right1;}
                    if(spriteNum ==2){image = right2;}
                    break;
            }
            if (invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
            }
            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
    }

    public BufferedImage setup (String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

}
