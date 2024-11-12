package tile_interactive;

import main.GamePanel;
import entity.Entity;

public class TBF_Obj extends InteractiveTile {
    GamePanel gp;

    public TBF_Obj(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        // Load the cat image (make sure the path is correct)
        down1 = setup("/tiles_interactive/final_obj", gp.tileSize, gp.tileSize);
    }

    @Override
    public void update() {
        // Check if the player collides with the cat (TBF_Obj)
        if (gp.player.solidArea.intersects(this.solidArea)) {
            System.out.println("Collision detected!");
            // If the player touches the cat, end the game
            gp.ui.foundFinalObj();
            System.out.println("Game state: " + gp.gameState);// Call the UI method to end the game
        }

        // Optionally, handle invincibility logic (if needed)
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
