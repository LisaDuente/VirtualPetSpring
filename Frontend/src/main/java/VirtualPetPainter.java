import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VirtualPetPainter implements Painter {
    ConnectionManager connect = new ConnectionManager();
    private int petX;
    private int petY;
    private boolean walkingRight;
    private String moveState;
    private final File[] WALKRIGHT;
    private final File[] WALKLEFT;
    private int walkingSprite;

    public VirtualPetPainter(){
        this.WALKRIGHT = new File[2];
        this.WALKRIGHT[0] = new File("Frontend/src/main/resources/TestWalkingR.png");
        this.WALKRIGHT[1] = new File("Frontend/src/main/resources/TestWalkingR1.png");

        this.WALKLEFT = new File[2];
        this.WALKLEFT[0] = new File("Frontend/src/main/resources/TestWalking1.png");
        this.WALKLEFT[1] = new File("Frontend/src/main/resources/TestWalking2.png");
    }

    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {

        this.petX = Integer.parseInt(connect.sendGetRequest("getX"));
        this.petY = Integer.parseInt(connect.sendGetRequest("getY"));
        this.walkingRight = Boolean.parseBoolean(connect.sendGetRequest("isWalkingRight"));
        this.moveState = connect.sendGetRequest("getMoveState");
        this.walkingSprite = Integer.parseInt(connect.sendGetRequest("chooseWalkingSprite"));
        System.out.println(this.walkingSprite);


        switch(moveState){
            case "\"WALKING\"":
                if(!walkingRight){
                    try {
                        BufferedImage pet = ImageIO.read(this.WALKRIGHT[this.walkingSprite]);
                        g.drawImage(pet,petX,petY,null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        BufferedImage pet = ImageIO.read(this.WALKLEFT[this.walkingSprite]);
                        g.drawImage(pet,petX,petY,null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "\"IDLE\"":
                break;
            case "\"PLAYING\"":
                break;
            case"\"SLEEPING\"":
                break;
            case"\"CHEERING\"":
                break;
            default:
                System.out.println("Error::VirtualPetPainter::Paint");

        }




    }
}
