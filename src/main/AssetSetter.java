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
                "Hello, bro!",
                "What brings you here?",
                "I have heard that there is a dog missing",
                "It should be somewhere near you"
        });

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize * 12;
        gp.npc[1].worldY = gp.tileSize * 31;
        gp.npc[1].setDialogues(new String[]{
                "Hi, I am John.",
                "Go to (30, 19) coordinates",
                "Good luck finding it!"
        });

        gp.npc[2] = new NPC_OldMan(gp);
        gp.npc[2].worldX = gp.tileSize * 38;
        gp.npc[2].worldY = gp.tileSize * 10;
        gp.npc[2].setDialogues(new String[]{
                "The weather is nice today, isn't it?",
                "Be cautious of the wild beasts at night.",
                "The dog is somewhere near the water"
        });




        gp.npc[6] = new NPC_OldMan(gp);
        gp.npc[6].worldX = gp.tileSize * 37;
        gp.npc[6].worldY = gp.tileSize * 20;
        gp.npc[6].setDialogues(new String[]{

                "There are many secrets in this world.",
                "Keep an eye out for hidden paths.",
                "Listen to the wind; it carries whispers.",
                "Your destiny awaits."
        });

        gp.npc[7] = new NPC_OldMan(gp);
        gp.npc[7].worldX = gp.tileSize * 30;
        gp.npc[7].worldY = gp.tileSize * 29;
        gp.npc[7].setDialogues(new String[]{
                "I heard the dog barking somewhere at /n(15,35) ",
                "Keep an eye out for hidden paths.",
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

        gp.iTile[i]= new IT_DryTree(gp, 25,31);i++;
        gp.iTile[i]= new IT_DryTree(gp,26,31);i++;
        gp.iTile[i]= new IT_DryTree(gp,27,31);i++;
        gp.iTile[i]= new IT_DryTree(gp,28,31);i++;
        gp.iTile[i]= new IT_DryTree(gp, 29, 31);i++;
        gp.iTile[i]= new IT_DryTree(gp,31,31);i++;
        gp.iTile[i]= new IT_DryTree(gp,30,31);i++;

        gp.iTile[i]= new IT_DryTree(gp, 21,15);i++;
        gp.iTile[i]= new IT_DryTree(gp,20,15);i++;
        gp.iTile[i]= new IT_DryTree(gp,19,15);i++;
        gp.iTile[i]= new IT_DryTree(gp,18,15);i++;
        gp.iTile[i]= new IT_DryTree(gp, 17, 15);i++;
        gp.iTile[i]= new IT_DryTree(gp,16,15);i++;

        gp.iTile[i]= new IT_DryTree(gp, 30,19);i++;
        gp.iTile[i]= new IT_DryTree(gp,30,18);i++;
        gp.iTile[i]= new IT_DryTree(gp,30,17);i++;

        gp.iTile[i]= new IT_DryTree(gp, 37,36);i++;
        gp.iTile[i]= new IT_DryTree(gp,34,34);i++;
        gp.iTile[i]= new IT_DryTree(gp,32,37);i++;

        gp.iTile[i]= new IT_DryTree(gp, 17,37);i++;
        gp.iTile[i]= new IT_DryTree(gp,16,37);i++;
        gp.iTile[i]= new IT_DryTree(gp,15,37);i++;
        gp.iTile[i]= new IT_DryTree(gp, 14,37);i++;
        gp.iTile[i]= new IT_DryTree(gp,13,37);i++;


    }

}
