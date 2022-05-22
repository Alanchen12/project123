package view;

import javax.swing.*;
import java.awt.*;

public class MainChuang extends JFrame {
    public MainChuang() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("国际象棋");
        setBounds(size.width/3,size.height/3,size.width/2,size.height/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container=this.getContentPane();
        container.setLayout(null);
        this.setLayout(null);
        setVisible(true);

        JButton startGame=new JButton("开始游戏");
        startGame.setFont(new Font("R", 2, 20));
        startGame.setSize(200,50);
        this.add(startGame);
        startGame.setLocation(410, 255);
        startGame.addActionListener((e) -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000,760);
            mainFrame.setVisible(true);
            setVisible(false);
        });

        this.add(startGame);
        JLabel image;
        container.add(image=new JLabel(new ImageIcon("images/back.jpeg")));
        image.setBounds(0,0,800,600);
        this.setResizable(false);
    }
}
