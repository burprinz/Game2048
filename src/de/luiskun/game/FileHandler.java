package de.luiskun.game;

import de.luiskun.game.ui.ScoreHandler;
import de.luiskun.game.ui.Screen;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    Screen s;

    public FileHandler(Screen s) {
        this.s = s;
    }

    public void saveScore(String name, int score) {
        String p = "res/scores.txt";
        Path filePath = Path.of(p);

        String content = "";

        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            // File read Error
            e.printStackTrace();
        }

        String output = "";
        String[] split = content.split("\n");
        boolean set = false;
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("")) {
                if (!set && score > Integer.parseInt(split[i].split(",")[1])) {
                    output += name + "," + score+"\n";
                    set = true;
                }
                output += split[i];
            }
            output+="\n";
        }

        if(!set) {
            output += name + "," + score;
        }

        try {
            FileWriter myWriter = new FileWriter(p);
            myWriter.write(output);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String read(String path) {
        Path filePath = Path.of(path);

        String content = "";

        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            // File read Error
            e.printStackTrace();
        }

        return content;
    }

}
