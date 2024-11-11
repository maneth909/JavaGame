package entity;

import main.GamePanel;

import java.util.Random;


public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage(){
        up1 = setup("/NPC/oldman_up_1");
        up2 = setup("/NPC/oldman_up_2");
        down1 = setup("/NPC/oldman_down_1");
        down2 = setup("/NPC/oldman_down_2");
        left1 = setup("/NPC/oldman_left_1");
        left2 = setup("/NPC/oldman_left_2");
        right1 = setup("/NPC/oldman_right_1");
        right2 = setup("/NPC/oldman_right_2");
    }

    public void setDialogue(){
        dialogues[0] = "Hello, bro";
        dialogues[1] = "Hey, wherea re you from? I am from /nsomehwere u never know exrst";
        dialogues[2] = "lol";
        dialogues[3] = "Goodluck";

    }

    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i<=25){
                direction = "up";
            }
            if(i>25 && i<=50){
                direction = "down";
            }
            if (i>50 && i<=75){
                direction = "left";
            }
            if (i>75 && i <= 100){
                direction = "right";
            }
            actionLockCounter=0;
        }

    }

    public void speak(){
        super.speak();
    }

}