import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{

    //Screen settings
    final int originalTitleSize = 16;
    final int scale = 3;

    final int tileSize = originalTitleSize*scale;
    final int maxScreenCol = 16;
    final int MaxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*MaxScreenRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

}
