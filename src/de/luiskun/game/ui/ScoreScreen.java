package de.luiskun.game.ui;

import java.awt.*;

public class ScoreScreen {

    Screen s;
    String content;

    public boolean backSelected = false;

    public ScoreScreen(Screen s) {
        this.s = s;
    }

    public void setScores() {
        content = s.fh.read("res/scores.txt");
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fillRect(0,0,Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);
        String[] user = content.split("\n");
        g2.setColor(Color.BLACK);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
        int y = 50;

        for(int i = 0; i < user.length; i++) {
            String[] s = user[i].split(",");
            String text = s [0] + " - " + s[1];
            int tx = this.s.pf.getXForCenteredText(text, Screen.SCREEN_WIDTH, g2);
            g2.drawString(text, tx, y);
            y+=50;
        }

        if(backSelected) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }

        g2.fillRoundRect(10, Screen.SCREEN_HEIGHT-60, 100, 50, 25, 25);

        int x = s.pf.getXForCenteredText("Back", 100, g2);
        g2.setColor(Color.WHITE);
        g2.drawString("Back", 10+x, Screen.SCREEN_HEIGHT-60+38);

    }

}
