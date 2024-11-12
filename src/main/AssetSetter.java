package main;

import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import tile_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){


//        gp.obj[0] = new OBJ_Key(gp);
//        gp.obj[0].worldX = 23*gp.tileSize;
//        gp.obj[0].worldY = 7*gp.tileSize;
//
//        gp.obj[1] = new OBJ_Key(gp);
//        gp.obj[1].worldX= 23* gp.tileSize;
//        gp.obj[1].worldY = 40*gp.tileSize;
//
//        gp.obj[2] = new OBJ_Key(gp);
//        gp.obj[2].worldX= 38* gp.tileSize;
//        gp.obj[2].worldY = 8*gp.tileSize;
//
//        gp.obj[3] = new OBJ_Door(gp);
//        gp.obj[3].worldX= 10* gp.tileSize;
//        gp.obj[3].worldY = 11*gp.tileSize;
//
//        gp.obj[4] = new OBJ_Door(gp);
//        gp.obj[4].worldX= 8* gp.tileSize;
//        gp.obj[4].worldY = 28*gp.tileSize;
//
//        gp.obj[5] = new OBJ_Door(gp);
//        gp.obj[5].worldX= 12* gp.tileSize;
//        gp.obj[5].worldY = 22*gp.tileSize;
//
//        gp.obj[6] = new OBJ_Chest(gp);
//        gp.obj[6].worldX= 10* gp.tileSize;
//        gp.obj[6].worldY = 7*gp.tileSize;
//
//        gp.obj[7] = new OBJ_Boots(gp);
//        gp.obj[7].worldX= 37* gp.tileSize;
//        gp.obj[7].worldY = 42*gp.tileSize;
    }
    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
        gp.npc[0].setDialogues(new String[]{
                "Hello, traveler!",
                "This town is full of mysteries.",
                "Stay safe on your journey.",
                "Don't forget to check the old ruins."
        });

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize * 11;
        gp.npc[1].worldY = gp.tileSize * 21;
        gp.npc[1].setDialogues(new String[]{
                "Have you heard about the golden key?",
                "Legends say it unlocks hidden treasures.",
                "Some say it's hidden deep in the forest.",
                "Good luck finding it!"
        });

        gp.npc[2] = new NPC_OldMan(gp);
        gp.npc[2].worldX = gp.tileSize * 31;
        gp.npc[2].worldY = gp.tileSize * 21;
        gp.npc[2].setDialogues(new String[]{
                "The weather is nice today, isn't it?",
                "Many travelers come through here.",
                "Be cautious of the wild beasts at night.",
                "May fortune favor you."
        });

        gp.npc[3] = new NPC_OldMan(gp);
        gp.npc[3].worldX = gp.tileSize * 21;
        gp.npc[3].worldY = gp.tileSize * 11;
        gp.npc[3].setDialogues(new String[]{
                "I used to be an adventurer like you...",
                "Until I took an arrow to the knee.",
                "Now, I enjoy telling stories to travelers.",
                "Want to hear another tale?"
        });

        gp.npc[4] = new NPC_OldMan(gp);
        gp.npc[4].worldX = gp.tileSize * 21;
        gp.npc[4].worldY = gp.tileSize * 31;
        gp.npc[4].setDialogues(new String[]{
                "There are many secrets in this world.",
                "Keep an eye out for hidden paths.",
                "Listen to the wind; it carries whispers.",
                "Your destiny awaits."
        });
    }


    public void setInteractiveTile(){
        int i = 0;
        gp.iTile[i]= new IT_DryTree(gp, 27,12);i++;
        gp.iTile[i]= new IT_DryTree(gp,28,12);i++;
        gp.iTile[i]= new IT_DryTree(gp,29,12);i++;
        gp.iTile[i]= new IT_DryTree(gp,30,12);i++;
        gp.iTile[i]= new IT_DryTree(gp, 31, 12);i++;
        gp.iTile[i]= new IT_DryTree(gp,32,12);i++;
        gp.iTile[i]= new IT_DryTree(gp,33,12);i++;

        gp.iTile[i]= new IT_DryTree(gp, 30,20);i++;
        gp.iTile[i]= new IT_DryTree(gp,30,21);i++;
        gp.iTile[i]= new IT_DryTree(gp,30,22);i++;
        gp.iTile[i]= new IT_DryTree(gp,20,20);i++;
        gp.iTile[i]= new IT_DryTree(gp, 20, 21);i++;
        gp.iTile[i]= new IT_DryTree(gp,20,22);i++;
        gp.iTile[i]= new IT_DryTree(gp,22,24);i++;
        gp.iTile[i]= new IT_DryTree(gp,23,24);i++;
        gp.iTile[i]= new IT_DryTree(gp,24,24);i++;
    }

}
