package de.luiskun.game;

import de.luiskun.game.ui.Screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    Screen s;

    public MouseHandler(Screen s) {
        this.s = s;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(s.gameState) {
            case Screen.playState:
                if(s.pf.selectedButton == 4) {
                    s.pf.newGame();
                    s.pf.score = 0;
                } else if(s.pf.selectedButton == 5) {
                    s.sh.setVisible(true);
                } else if (s.pf.selectedButton == 2) {
                    s.pf.newGame();
                    s.pf.score = 0;
                } else if (s.pf.selectedButton == 3) {
                    s.sc.setScores();
                    s.gameState = Screen.scoreState;
                } else if (s.pf.selectedButton == 1) {
                    s.gameState = Screen.titleState;
                }
                break;
            case Screen.scoreState:
                if(s.sc.backSelected) {
                    s.gameState = Screen.playState;
                }
                break;
            case Screen.titleState:
                switch (s.ts.commandNum) {
                    case 1:
                        s.pf.setSize(2);
                        s.pf.newGame();
                        s.gameState = Screen.playState;
                        break;
                    case 2:
                        s.pf.setSize(3);
                        s.pf.newGame();
                        s.gameState = Screen.playState;
                        break;
                    case 3:
                        s.pf.setSize(4);
                        s.pf.newGame();
                        s.gameState = Screen.playState;
                        break;
                    case 4:
                        s.pf.setSize(5);
                        s.pf.newGame();
                        s.gameState = Screen.playState;
                        break;
                    case 5:
                        s.pf.setSize(6);
                        s.pf.newGame();
                        s.gameState = Screen.playState;
                        break;
                }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
