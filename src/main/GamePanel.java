package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize*scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;


    //World Setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public int endGameDelayCounter = 0; // Add this to track delay time
    public final int endGameDelayLimit = 80; // Adjust as needed (0.5 sec at 60 FPS)


    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entiity and object
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    ArrayList<Entity> entityList = new ArrayList<>();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int finishState = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setInteractiveTile();
//        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime-lastTime)/drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
                drawCount ++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            ui.updatePlayTime();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
                }
            }
        } else if (gameState == finishState) {
            // Increment the delay counter
            endGameDelayCounter++;
            if (endGameDelayCounter > endGameDelayLimit) {
                gameThread = null; // Stop the game thread after the delay
            }
        }

        if (gameState == pauseState) {
            // Handle pause state updates if needed
        }
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileM.draw(g2);

            for (InteractiveTile tile : iTile) {
                if (tile != null) {
                    tile.draw(g2);
                }
            }

            entityList.add(player);
            for (Entity npcEntity : npc) {
                if (npcEntity != null) {
                    entityList.add(npcEntity);
                }
            }
            for (Entity object : obj) {
                if (object != null) {
                    entityList.add(object);
                }
            }

            Collections.sort(entityList, Comparator.comparingInt(e -> e.worldY));
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            entityList.clear();

            ui.draw(g2);

            // Draw the congratulatory message on top of the last frame
            if (gameState == finishState) {
                ui.showCongratulatoryMessage(g2);
            }
        }

        g2.dispose();
    }




    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playerSE(int i){
        se.setFile(i);
        se.play();
    }

}
