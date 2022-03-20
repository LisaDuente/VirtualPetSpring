import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VirtualPetPainter implements Painter {
    ConnectionManager connect = new ConnectionManager();

    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {

        int petX = Integer.parseInt(connect.sendGetRequest("getX"));
        int petY = Integer.parseInt(connect.sendGetRequest("getY"));
        boolean walkingRight = Boolean.parseBoolean(connect.sendGetRequest("isWalkingRight"));
        if(!walkingRight){
            File petFile = new File("Frontend/src/main/resources/TestWalkingR.gif");
            try {
                BufferedImage pet = ImageIO.read(petFile);
                g.drawImage(pet,petX,petY,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            File petFile = new File("Frontend/src/main/resources/TestWalking.gif");
            try {
                BufferedImage pet = ImageIO.read(petFile);
                g.drawImage(pet,petX,petY,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
