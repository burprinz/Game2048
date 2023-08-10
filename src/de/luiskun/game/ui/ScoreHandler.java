package de.luiskun.game.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreHandler extends JPanel {

    Screen s;

    JTextField name;
    JButton saveButton, cancelButton;
    ButtonHandler bh = new ButtonHandler();

    public ScoreHandler(Screen s) {
        this.s = s;
        this.setBounds(Screen.SCREEN_WIDTH/2-125, Screen.SCREEN_HEIGHT/2-90, 250, 180);
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.BLACK));

        name = new JTextField();
        name.setBounds(10,10,230,50);
        this.add(name);

        JLabel l = new JLabel("<html>Mind. 1 Char<br/>Max 20 Chars<br/>No ','</html>");
        l.setBounds(10, 65, 230, 50);
        this.add(l);

        saveButton = new JButton("Save");
        saveButton.setBounds(10, 120, 110, 50);
        saveButton.setActionCommand("s");
        saveButton.addActionListener(bh);
        this.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(130, 120, 110, 50);
        cancelButton.setActionCommand("c");
        cancelButton.addActionListener(bh);
        this.add(cancelButton);

        this.setVisible(false);
    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("s")) {
                if(name.getText().length() >= 1 && name.getText().length() <= 20) {
                    s.fh.saveScore(name.getText(), s.pf.score);
                    s.pf.newGame();
                }
                setVisible(false);
            } else {
                setVisible(false);
            }
        }
    }

}
