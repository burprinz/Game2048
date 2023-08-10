package de.luiskun.game;

import de.luiskun.game.ui.Screen;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, shotKeyPressed;

	// Debug
	boolean debugMode = false;

	Screen s;

	public KeyHandler(Screen s) {
		this.s = s;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				s.pf.rightPressed();
				break;
			case KeyEvent.VK_LEFT:
				s.pf.leftPressed();
				break;
			case KeyEvent.VK_UP:
				s.pf.upPressed();
				break;
			case KeyEvent.VK_DOWN:
				s.pf.downPressed();
				break;
		}

		// Debug
		if (code == KeyEvent.VK_T) {
			debugMode = !debugMode;
		}

		if(s.pf.isLost()) {
			s.pf.gameOver = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
	}

}
