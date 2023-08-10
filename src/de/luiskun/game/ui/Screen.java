package de.luiskun.game.ui;

import de.luiskun.game.FileHandler;
import de.luiskun.game.KeyHandler;
import de.luiskun.game.MouseHandler;

import javax.swing.*;
import java.awt.*;
public class Screen extends JPanel implements Runnable {

    public static final int SCREEN_WIDTH = 500, SCREEN_HEIGHT = 700;

    private int fps = 60;
    private Thread gameThread;

    KeyHandler kh = new KeyHandler(this);
    MouseHandler mh = new MouseHandler(this);
    public FileHandler fh = new FileHandler(this);
    public static final Color background = Color.GRAY;

    public PlayField pf;
    public ScoreHandler sh;
    public ScoreScreen sc;

    public TitleScreen ts;

    public int gameState = titleState;
    public static final int playState = 0;
    public static final int scoreState = 1;

    public static final int titleState = 2;

    public Screen() {
        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(kh);
        this.addMouseListener(mh);

        pf = new PlayField(4);
        sh = new ScoreHandler(this);
        sc = new ScoreScreen(this);
        ts = new TitleScreen(this);
        this.add(sh);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        updateMouse();

    }

    private void updateMouse() {
        if(!sh.isVisible()) {
            // Mouse
            Point mousePosition = this.getMousePosition();

            switch (gameState) {
                case playState:
                    // Home Button
                    if (mousePosition == null) {
                        pf.selectedButton = 0;
                    } else if (mousePosition.x > 10 && mousePosition.x < 80 && mousePosition.y > 10 && mousePosition.y < 80) {
                        pf.selectedButton = 1;
                    } else if (mousePosition.x > 90 && mousePosition.x < 160 && mousePosition.y > 10 && mousePosition.y < 80) {
                        pf.selectedButton = 2;
                    } else if (mousePosition.x > 170 && mousePosition.x < 240 && mousePosition.y > 10 && mousePosition.y < 80) {
                        pf.selectedButton = 3;
                    } else if (pf.gameOver && mousePosition.x > 150 && mousePosition.x < 350 && mousePosition.y > 300 && mousePosition.y < 400) {
                        pf.selectedButton = 4;
                    } else if (pf.gameOver && mousePosition.x > 150 && mousePosition.x < 350 && mousePosition.y > 500 && mousePosition.y < 600) {
                        pf.selectedButton = 5;
                    } else {
                        pf.selectedButton = 0;
                    }
                    break;
                case scoreState:
                    if (mousePosition != null) {
                        if(mousePosition.x > 10 && mousePosition.x < 110 && mousePosition.y > 640 && mousePosition.y < 690) {
                            sc.backSelected = true;
                        } else {
                            sc.backSelected = false;
                        }
                    }
                    break;
                case titleState:
                    if (mousePosition == null) {
                        ts.commandNum = 0;
                    } else if (mousePosition.x > 175 && mousePosition.x < 325 && mousePosition.y > 100 && mousePosition.y < 175) {
                        ts.commandNum = 1;
                    } else if (mousePosition.x > 175 && mousePosition.x < 325 && mousePosition.y > 200 && mousePosition.y < 275) {
                        ts.commandNum = 2;
                    } else if (mousePosition.x > 175 && mousePosition.x < 325 && mousePosition.y > 300 && mousePosition.y < 375) {
                        ts.commandNum = 3;
                    }  else if (mousePosition.x > 175 && mousePosition.x < 325 && mousePosition.y > 400 && mousePosition.y < 475) {
                        ts.commandNum = 4;
                    } else if (mousePosition.x > 175 && mousePosition.x < 325 && mousePosition.y > 500 && mousePosition.y < 575) {
                        ts.commandNum = 5;
                    }else {
                        ts.commandNum
                                = 0;
                    }
                    break;

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(background);
        g2.fillRect(0,0,SCREEN_WIDTH, SCREEN_HEIGHT);

        switch (gameState) {
            case scoreState:
                sc.paint(g2);
                break;
            case playState:
                pf.draw(g2);
                break;
            case titleState:
                ts.draw(g2);
                break;
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps; // 0.0166s
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public int getXForCenteredText(String text, int width, Graphics2D g2) {
        return width / 2 - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth()) / 2;
    }

}
