package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
//    public int hasKey = 0;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();

        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 25;
        solidArea.height = 25;

        axeArea.width = 25;
        axeArea.height = 25;

        setDefaultValues();
        getPlayerImage();
        getPlayerAxeImage();

    }
    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2",gp.tileSize, gp.tileSize);
    }

    public void getPlayerAxeImage(){
        axeUp1 = setup("/player/boy_axe_up_1",gp.tileSize, gp.tileSize*2);
        axeUp2 = setup("/player/boy_axe_up_2",gp.tileSize, gp.tileSize*2);
        axeDown1 = setup("/player/boy_axe_down_1",gp.tileSize, gp.tileSize*2);
        axeDown2 = setup("/player/boy_axe_down_2",gp.tileSize, gp.tileSize*2);
        axeLeft1 = setup("/player/boy_axe_left_1",gp.tileSize*2, gp.tileSize);
        axeLeft2 = setup("/player/boy_axe_left_2",gp.tileSize*2, gp.tileSize);
        axeRight1 = setup("/player/boy_axe_right_1",gp.tileSize*2, gp.tileSize);
        axeRight2 = setup("/player/boy_axe_right_2",gp.tileSize*2, gp.tileSize);
    }

    public void update(){

        if(axing == true){
            axing();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";

            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            // if collision is false, player can move
            if(collisionOn == false && keyH.enterPressed == false){
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

            gp.keyH.enterPressed = false;

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
        }
        else{
            standCounter++;
            if(standCounter==20){
                spriteNum = 1;
                standCounter = 0;
            }
        }

    }

    public void axing(){
        spriteCounter++;
        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter >5 && spriteCounter<=25){
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch(direction){
                case "up": worldY -= axeArea.height; break;
                case "down": worldY += axeArea.height; break;
                case "left": worldX -= axeArea.width; break;
                case "right": worldX += axeArea.width; break;
            }

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = axeArea.width;
            solidArea.height = axeArea.height;

        }
        if (spriteCounter>25){
            spriteNum = 1;
            spriteCounter = 0;
            axing = false;
        }
    }

    public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[i].destructible == true && gp.iTile[i].invincible == false){
            gp.iTile[i].playSE();
            gp.iTile[i].life--;
            gp.iTile[i].invincible = true;

            if(gp.iTile[i].life == 0){
                gp.iTile[i] = gp.iTile[i].getDestroyedForm();
            }
        }
    }

    public void pickUpObject(int i){
        if ( i!= 999){



//           String objectName = gp.obj[i].name;
//
//           switch(objectName){
//               case "Key":
//                   gp.playerSE(1);
//                   hasKey++;
//                   gp.obj[i] = null;
//                   gp.ui.showMessage("You got a key");
//
//                   break;
//               case "Door":
//                   if (hasKey>0){
//                       gp.playerSE(3);
//                       gp.obj[i] = null;
//                       hasKey--;
//                       gp.ui.showMessage("You opened the door");
//                   } else{
//                       gp.ui.showMessage("You need a key");
//                   }
//                   break;
//               case "Boots":
//                   gp.playerSE(2);
//                   speed+=1;
//                   gp.obj[i] = null;
//                   gp.ui.showMessage("Speed up!");
//                   break;
//               case "Chest":
//                   gp.ui.gameFinished = true;
//                   gp.stopMusic();
//                   gp.playerSE(4);
//                   break;
//            }
        }
    }

    public void interactNPC(int i){
        if (gp.keyH.enterPressed){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else{
                axing = true;
            }
        }
//        if (gp.keyH.enterPressed) {
//            if (i != 999) {
//                // Check if the NPC is the cat
//                if (gp.npc[i] != null && gp.npc[i].name.equals("Cat")) {
//                    gp.ui.foundFinalObj();  // End the game when the cat is touched
//                } else {
//                    gp.gameState = gp.dialogueState;
//                    gp.npc[i].speak();
//                }
//            } else {
//                axing = true;
//            }
//        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if (axing == false){
                    if (spriteNum==1){image = up1;}
                    if(spriteNum ==2){image = up2;}
                }
                if (axing == true){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum==1){image = axeUp1;}
                    if(spriteNum ==2){image = axeUp2;}
                }
                break;

            case "down":
                if (axing == false){
                    if (spriteNum==1){image = down1;}
                    if(spriteNum ==2){image = down2;}
                }
                if (axing == true){
                    if (spriteNum==1){image = axeDown1;}
                    if(spriteNum ==2){image = axeDown2;}
                }
                break;

            case "left":
                if (axing == false){
                    if (spriteNum==1){image = left1;}
                    if(spriteNum ==2){image = left2;}
                }
                if (axing == true){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum==1){image = axeLeft1;}
                    if(spriteNum ==2){image = axeLeft2;}
                }
                break;

            case "right":
                if (axing == false){
                    if (spriteNum==1){image = right1;}
                    if(spriteNum ==2){image = right2;}
                }
                if (axing == true){
                    if (spriteNum==1){image = axeRight1;}
                    if(spriteNum ==2){image = axeRight2;}
                }
                break;
        }
        g2.drawImage(image,tempScreenX,tempScreenY,null);
//        g2.drawRect(screenX+solidArea.x,screenY+solidArea.y,solidArea.width,solidArea.height);

    }
}
