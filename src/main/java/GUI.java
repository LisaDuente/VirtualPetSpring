import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    ConnectionManager connector;
    Thread updateStats;

    private JPanel menu;
    private JLabel welcomeText;
    private JLabel info;
    private JTextField insertUserName;
    private JButton enterUserName;
//________________________
private JPanel petPanel;

    private JPanel virtualPet;
    private JTextField stats;

    private JPanel controlArea;
    private JButton feed;
    private JButton sleep;
    private JButton clean;
    private JButton play;
    private JButton calm;

//________________________

    public GUI(){
        this.connector = new ConnectionManager();
    //define menu
        this.menu = new JPanel();
        this.welcomeText = new JLabel("Welcome to Lisa's Virtual Pet!");
        this.info = new JLabel("Please insert your username!");
        this.insertUserName = new JTextField();
        this.enterUserName = new JButton("Enter");

        this.welcomeText.setBounds(50,50,300,50);
        this.info.setBounds(100,200,300,50);
        this.insertUserName.setBounds(100,260,200,50);
        this.enterUserName.setBounds(310,260,150,50);

        this.enterUserName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //load the given user
                //update the label to an error maybe
                toPetPanel();
            }
        });

        this.menu.setVisible(true);
        this.menu.setBounds(0,0,500,500);
        this.menu.setLayout(null);

        this.menu.add(this.welcomeText);
        this.menu.add(this.info);
        this.menu.add(this.insertUserName);
        this.menu.add(this.enterUserName);

        this.add(this.menu);

    //define petPanel
        this.petPanel = new JPanel();
        this.virtualPet = new JPanel();
        this.stats = new JTextField();
        this.controlArea = new JPanel();
        this.feed = new JButton("Feed");
        this.sleep = new JButton("Sleep");
        this.calm = new JButton("Calm");
        this.clean = new JButton("Clean");
        this.play = new JButton("Play");

        this.feed.addActionListener(e -> this.connector.sendGetRequest("feed"));
        this.calm.addActionListener(e -> this.connector.sendGetRequest("calm"));
        this.play.addActionListener(e -> this.connector.sendGetRequest("play"));
        this.clean.addActionListener(e -> this.connector.sendGetRequest("clean"));
        this.sleep.addActionListener((e) -> {this.connector.sendGetRequest("sleep");
            //find a wai to let the updating in the backend rest for some time
            try {
                this.updateStats.wait(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        this.petPanel.setBounds(0,0,500,500);
        this.petPanel.setLayout(null);
        this.virtualPet.setBounds(0,0,500,300);
        this.controlArea.setBounds(0,400,500,50);
        this.stats.setBounds(0,0,500,50);
        this.stats.setText(this.connector.sendGetRequest("getAllPoints"));

        //needs maybe to be synchronized to allow other things on the connection
        this.updateStats = new Thread(() -> {
            while(true){
                this.stats.setText(this.connector.sendGetRequest("getAllPoints"));
               try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.updateStats.start();



        this.controlArea.setLayout(new FlowLayout());
        this.controlArea.add(this.feed);
        this.controlArea.add(this.sleep);
        this.controlArea.add(this.calm);
        this.controlArea.add(this.clean);
        this.controlArea.add(this.play);
        this.controlArea.setVisible(false);

        this.virtualPet.setLayout(null);
        this.virtualPet.add(this.stats);
        this.virtualPet.setVisible(false);

        this.petPanel.add(this.virtualPet);
        this.petPanel.add(this.controlArea);
        this.petPanel.setVisible(false);

        this.add(petPanel);


    //define frame
        this.setSize(500,500);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void toPetPanel(){
        this.menu.setVisible(false);
        this.petPanel.setVisible(true);
        this.virtualPet.setVisible(true);
        this.controlArea.setVisible(true);
    }
}
