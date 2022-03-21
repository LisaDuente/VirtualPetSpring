import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VirtualPetPainter implements Painter {
    ConnectionManager connect = new ConnectionManager();
    private int petX;
    private int petY;
    private boolean walkingRight;
    private String moveState;
    private final File[] WALK_RIGHT;
    private final File[] WALK_LEFT;
    private final File[] IDLE;
    private int walkingSprite;
    private int idleSprite;

    public VirtualPetPainter(){
        //unfortunately the wrong order
        this.WALK_RIGHT = new File[4];
        this.WALK_RIGHT[3] = new File("Frontend/src/main/resources/WalkingRight/WR1.png");
        this.WALK_RIGHT[2] = new File("Frontend/src/main/resources/WalkingRight/WR2.png");
        this.WALK_RIGHT[1] = new File("Frontend/src/main/resources/WalkingRight/WR3.png");
        this.WALK_RIGHT[0] = new File("Frontend/src/main/resources/WalkingRight/WR4.png");

        this.WALK_LEFT = new File[4];
        this.WALK_LEFT[0] = new File("Frontend/src/main/resources/WalkingLeft/WL4.png");
        this.WALK_LEFT[1] = new File("Frontend/src/main/resources/WalkingLeft/WL3.png");
        this.WALK_LEFT[2] = new File("Frontend/src/main/resources/WalkingLeft/WL2.png");
        this.WALK_LEFT[3] = new File("Frontend/src/main/resources/WalkingLeft/WL1.png");

        this.IDLE = new File[4];
        this.IDLE[0] = new File("Frontend/src/main/resources/Idle/Idle1.png");
        this.IDLE[1] = new File("Frontend/src/main/resources/Idle/Idle2.png");
        this.IDLE[2] = new File("Frontend/src/main/resources/Idle/Idle3.png");
        this.IDLE[3] = new File("Frontend/src/main/resources/Idle/Idle2.png");
    }

    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {

        this.petX = Integer.parseInt(connect.sendGetRequest("getX"));
        this.petY = Integer.parseInt(connect.sendGetRequest("getY"));
        this.walkingRight = Boolean.parseBoolean(connect.sendGetRequest("isWalkingRight"));
        this.moveState = connect.sendGetRequest("getMoveState");
        this.walkingSprite = Integer.parseInt(connect.sendGetRequest("chooseWalkingSprite"));
        this.idleSprite = Integer.parseInt(connect.sendGetRequest("chooseIdleSprite"));


        switch(moveState){
            case "\"WALKING\"":
                if(!walkingRight){
                    try {
                        BufferedImage pet = ImageIO.read(this.WALK_RIGHT[this.walkingSprite]);
                        g.drawImage(pet,petX,petY,null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //use the flipped images when walking left
                }else{
                    try {
                        BufferedImage pet = ImageIO.read(this.WALK_LEFT[this.walkingSprite]);
                        g.drawImage(pet,petX,petY,null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "\"IDLE\"":
                try {
                    BufferedImage pet = ImageIO.read(this.IDLE[this.walkingSprite]);
                    g.drawImage(pet,petX,petY,null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
