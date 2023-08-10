package de.luiskun.game;

import de.luiskun.game.ui.Screen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Screen.SCREEN_WIDTH + 14,Screen.SCREEN_HEIGHT + 38);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        Screen s = new Screen();
        frame.add(s);
        frame.setVisible(true);

        s.startGameThread();

    }
}