package de.luiskun.game.ui;

import java.awt.*;

public class TitleScreen {

    Screen s;
    public int commandNum = 0;

    public TitleScreen(Screen s) {
        this.s = s;
    }

    public void draw(Graphics2D g2) {

        int y = 100;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        if(commandNum == 1) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(Screen.SCREEN_WIDTH/2-75, y, 150, 75, 25, 25);
        String text = "2x2";
        g2.setColor(Color.WHITE);
        int x = s.getXForCenteredText(text, 150, g2);
        g2.drawString(text, Screen.SCREEN_WIDTH/2-75+x, y+50);


        y+= 100;
        if(commandNum == 2) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(Screen.SCREEN_WIDTH/2-75, y, 150, 75, 25, 25);
        text = "3x3";
        g2.setColor(Color.WHITE);
        x = s.getXForCenteredText(text, 150, g2);
        g2.drawString(text, Screen.SCREEN_WIDTH/2-75+x, y+50);

        y+= 100;
        if(commandNum == 3) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(Screen.SCREEN_WIDTH/2-75, y, 150, 75, 25, 25);
        text = "4x4";
        g2.setColor(Color.WHITE);
        x = s.getXForCenteredText(text, 150, g2);
        g2.drawString(text, Screen.SCREEN_WIDTH/2-75+x, y+50);

        y+= 100;
        if(commandNum == 4) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(Screen.SCREEN_WIDTH/2-75, y, 150, 75, 25, 25);
        text = "5x5";
        g2.setColor(Color.WHITE);
        x = s.getXForCenteredText(text, 150, g2);
        g2.drawString(text, Screen.SCREEN_WIDTH/2-75+x, y+50);

        y+= 100;
        if(commandNum == 5) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(Screen.SCREEN_WIDTH/2-75, y, 150, 75, 25, 25);
        text = "6x6";
        g2.setColor(Color.WHITE);
        x = s.getXForCenteredText(text, 150, g2);
        g2.drawString(text, Screen.SCREEN_WIDTH/2-75+x, y+50);

    }

}
