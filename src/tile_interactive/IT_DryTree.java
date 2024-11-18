package tile_interactive;

import main.GamePanel;

public class IT_DryTree extends InteractiveTile{
    GamePanel gp;
    public IT_DryTree(GamePanel gp, int col, int row){
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interactive/drytree",gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public void playSE(){
        gp.playerSE(5);
    }
    public InteractiveTile getDestroyedForm() {
        // Check if the tree being cut is at column 20 and row 20
        if (worldX == 13 * gp.tileSize && worldY == 37 * gp.tileSize) {
            return new TBF_Obj(gp, worldX / gp.tileSize, worldY / gp.tileSize);
        } else {
            return new IT_Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
        }
    }
}
