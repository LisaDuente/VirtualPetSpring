import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel {

    List<Painter> layerPainters;
    VirtualPetPainter petPainter;
    BackgroundPainter backgroundPainter;

    public PaintPanel(){
        this.layerPainters = new ArrayList<Painter>();
        this.petPainter = new VirtualPetPainter();
        this.backgroundPainter = new BackgroundPainter("Frontend/src/main/resources/Background/Test.png",0,0);
        this.layerPainters.add(this.petPainter);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.backgroundPainter.paint((Graphics2D)g,this,this.getWidth(),this.getHeight());
        this.petPainter.paint((Graphics2D)g,this,this.getWidth(),this.getHeight());
        Toolkit.getDefaultToolkit().sync();
        repaint();
        /*
        //if you want to have more than one layer
        for(Painter painter : layerPainters){
            painter.paint((Graphics2D) g,this,width,hight);
        }

         */
    }

}
