import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundPainter implements Painter {
    private File background;
    private BufferedImage bg;
    private int x;
    private int y;

    public BackgroundPainter(String path, int x, int y){
        this.x = x;
        this.y = y;
        this.background = new File(path);
        try {
            this.bg = ImageIO.read(this.background);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {
        g.drawImage(this.bg,this.x,this.y,null);
    }
}
