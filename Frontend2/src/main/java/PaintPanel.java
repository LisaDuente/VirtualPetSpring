import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel {

    VirtualPetPainter petPainter;
    BackgroundPainter backgroundPainter;

    public PaintPanel(){
        this.petPainter = new VirtualPetPainter();
        this.backgroundPainter = new BackgroundPainter("src/main/resources/Background/Test.png",0,0);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //order is crucial
        this.backgroundPainter.paint((Graphics2D)g,this,this.getWidth(),this.getHeight());
        this.petPainter.paint((Graphics2D)g,this,this.getWidth(),this.getHeight());
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

}
