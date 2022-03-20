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
        File petFile = new File("Frontend/src/main/resources/Test.png");
        int petX = Integer.parseInt(connect.sendGetRequest("getX"));
        int petY = Integer.parseInt(connect.sendGetRequest("getY"));

        try {
            BufferedImage pet = ImageIO.read(petFile);
            g.drawImage(pet,petX,petY,null);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
