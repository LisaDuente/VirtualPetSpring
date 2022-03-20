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

    public PaintPanel(){
        layerPainters = new ArrayList<Painter>();
        petPainter = new VirtualPetPainter();

        layerPainters.add(petPainter);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        petPainter.paint((Graphics2D)g,this,this.getWidth(),this.getHeight());
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
