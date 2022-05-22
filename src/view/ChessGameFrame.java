package view;
import controller.GameController;
import model.ChessColor;

import service.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;


public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static ChessColor color = ChessColor.BLACK;
    private static int time =20;
    public TextArea area;
    private static Timer timer = new Timer();
    public static JLabel playerLabel;

    public ChessGameFrame(int width, int height) {
        setTitle("国际象棋");
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        addChessboard();

        addTextArea();
        addBeginButton();
        playerLabel = addTurn();
        addReloadButton();
        addLoadButton();
        addMusicButton();
        addPictureButton();
        addSaveButton();
        bgInit1();

    }
    private void addSaveButton(){
        JButton button = new JButton("save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 350);
        button.setSize(100, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }


    private void addTextArea(){
        String ans = gameController.getChessboard().getCurrentColor().getName();
        area = new TextArea(ans);
        area.setText(" Time:"+time);
        area.setLocation(HEIGTH,HEIGTH/10+550);
        area.setSize(200,60);
        area.setFont(new Font("Rock",Font.BOLD,20));
        add(area);
    }
    private void timeCount() {
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                time--;
                area.setText(" Time:"+time);
                if (time==0) {
                    gameController.getChessboard().swapColor();
                    time = 20;
                }
            }
        },0,1000);
    }
    public static void time(){
        timer.schedule(new TimerTask() {
            int num =0;
            @Override
            public void run() {
                if (num==0){
                    time=20;
                    num++;
                }
            }
        },0,1000);
    }

    private void bgInit1() {
        Icon i = new ImageIcon("./images/back2.jpeg");
        JLabel jLabel = new JLabel(i);
        jLabel.setBounds(-80,-100,1500,1260);
        this.add(jLabel);
    }

    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
    }

    private JLabel addTurn() {
        JLabel statusLabel = new JLabel("Black");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
        return statusLabel;
    }

    public static void setPlayerLabel(String s){
        playerLabel.setText(s);
    }

    private void addReloadButton() {
        JButton button = new JButton("New Game");
        button.addActionListener((e) -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
            setVisible(false);
        });

        button.setLocation(HEIGTH, HEIGTH / 10 + 100);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 200);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addMusicButton() {
        JButton button = new JButton("Music");
        button.setLocation(HEIGTH, HEIGTH / 10 + 300);
        button.setSize(100, 30);
        button.setFont(new Font("Rockwell", Font.PLAIN, 20));
        add(button);

        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        button.addActionListener(e -> {
            System.out.println("Music");
            String path = "./images/backMusic.wav"; // 必须是wav格式
            MusicPlayer musicPlayer = null;
            try {
                musicPlayer = new MusicPlayer(path, true);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            if (flag.get() == false) {
                flag.set(true);
                MusicPlayer.type = 1;
                musicPlayer.play();
            } else {
                flag.set(false);
                MusicPlayer.type = 0;
            }
        });
    }

    private void addPictureButton() {
        JButton button = new JButton("更换主题");
        button.addActionListener((e) -> {

            Icon i = new ImageIcon("./images/back1.jpeg");
            JLabel jLabel = new JLabel(i);
            jLabel.setBounds(-80,-100,1500,1260);
            this.add(jLabel);
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 400);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addBeginButton() {
        JButton jButton = new JButton("TimeTable");
        jButton.setLocation(HEIGTH, HEIGTH / 10 + 500);
        jButton.setSize(200, 60);
        jButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        jButton.addActionListener((e) -> {
            timer = new Timer();
            timeCount();
        });
        add(jButton);
    }
}
