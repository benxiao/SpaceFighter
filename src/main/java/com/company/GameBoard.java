package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


/**
 * Created by ranxiao on 25/2/17.
 */
public class GameBoard extends JPanel implements ActionListener, KeyListener{
    private Timer timer;
    private GameScene scene;
    private int width, height;
    private Set<Integer> keysPressed = new HashSet<>();
    private FighterJet jet;
    private List<Bullet> bullets;
    private static final int numOfBullets = 40;
    private int bulletIndex = 0;
    private static final int numOfBadGuys = 4;


    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        bullets = new ArrayList<>();
        scene = new GameScene(width, height);
        for(int i=0; i!=numOfBullets; i++){
            Bullet bullet = new Bullet(scene, -100, -100, 3);
            bullets.add(bullet);
        }

        for(int i=0; i!=numOfBadGuys; i++){
            BadGuy badGuy = new BadGuy(scene, -100,-100);
        }

        jet = new FighterJet(scene, width/2, height-100, 1);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void actionPerformed(ActionEvent e) {
        scene.moveObjects();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        scene.drawObjects(g);
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard(300, 600);
        JFrame jf = new JFrame();
        jf.add(board);
        jf.setTitle("SpaceFighter");
        jf.setSize(board.getWidth(), board.getHeight());
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public synchronized void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
        for (int c : keysPressed) {
            switch (c) {
                case KeyEvent.VK_UP:
                    jet.moveForward();
                    break;
                case KeyEvent.VK_LEFT:
                    jet.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    jet.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    jet.moveBack();
                    break;
                case KeyEvent.VK_F:
                    bullets.get(bulletIndex).reanimate(jet.x, jet.y-4);
                    bulletIndex = (bulletIndex+1) % numOfBullets;
                    bullets.get(bulletIndex).reanimate(jet.x+jet.width, jet.y-4);
                    bulletIndex = (bulletIndex+1) % numOfBullets;
                    break;
                default:
                    break;
            }
        }
    }

    public void keyTyped(KeyEvent e) {/* Not used */}

    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        keysPressed.remove(c);
        switch (c) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_DOWN:
                jet.stop();
                break;
            default:
                break;
        }
    }
}
