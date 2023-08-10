package de.luiskun.game.ui;

import de.luiskun.game.Helpers;
import de.luiskun.game.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class PlayField {
    private int[][] tiles;
    private boolean[][] alreadyMoved;
    private int size;
    private int fieldWidth = 430;
    private int yFieldBegin = 235;
    private int xFieldBegin = 35;
    public int selectedButton = 0;
    private BufferedImage[] imgs;
    public int score = 0;
    public boolean gameOver = false;

    int cellWidth;

    public PlayField(int size) {
        this.size = size;
        this.tiles = new int[size][size];
        cellWidth = fieldWidth / (this.size);

        alreadyMoved = new boolean[size][size];

        loadText();

        placeRandomTile();
    }

    public void setSize(int size) {
        this.size = size;
        this.tiles = new int[size][size];
        cellWidth = fieldWidth / (this.size);
        alreadyMoved = new boolean[size][size];
        loadText();
    }

    public void newGame() {
        gameOver = false;
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                tiles[x][y] = 0;
            }
        }

        placeRandomTile();
    }

    private void loadText() {
        imgs = new BufferedImage[11];
        for(int i = 0; i < imgs.length; i++) {
            imgs[i] = Helpers.SetupImage("res/text/" + (i+1), cellWidth-10, cellWidth-10);
        }
    }

    private void placeRandomTile() {
        Random rd = new Random();
        int percent = rd.nextInt(100);

        if(!isFull()) {
            while(true) {
                int x = rd.nextInt(size);
                int y = rd.nextInt(size);
                if(tiles[x][y] == 0) {
                    if(percent < 90) {
                        // 2
                        tiles[x][y] = 2;
                        break;
                    } else {
                        // 4
                        tiles[x][y] = 4;
                        break;
                    }
                }
            }
        }
    }

    public void rightPressed() {
        alreadyMoved = new boolean[size][size];
        ArrayList<Tile> sortedTiles = new ArrayList<Tile>();

        for(int x = size-1; x >= 0; x--) {
            for(int y = 0; y < size; y++) {
                if(tiles[x][y] != 0) {
                    Tile t = new Tile(x, y, tiles[x][y]);
                    sortedTiles.add(t);
                    tiles[x][y] = 0;
                }
            }
        }

        for(Tile t: sortedTiles) {
            moveTile(t, 1);
        }

        placeRandomTile();
    }

    public void leftPressed() {
        alreadyMoved = new boolean[size][size];
        ArrayList<Tile> sortedTiles = new ArrayList<Tile>();

        for(int x = 0; x <size; x++) {
            for(int y = 0; y < size; y++) {
                if(tiles[x][y] != 0) {
                    Tile t = new Tile(x, y, tiles[x][y]);
                    sortedTiles.add(t);
                    tiles[x][y] = 0;
                }
            }
        }

        for(Tile t: sortedTiles) {
            moveTile(t, 3);
        }

        placeRandomTile();
    }

    public void downPressed() {
        alreadyMoved = new boolean[size][size];
        ArrayList<Tile> sortedTiles = new ArrayList<Tile>();

            for(int y = size-1; y >= 0; y--) {
                for(int x = 0; x < size; x++) {
                if(tiles[x][y] != 0) {
                    Tile t = new Tile(x, y, tiles[x][y]);
                    sortedTiles.add(t);
                    tiles[x][y] = 0;
                }
            }
        }

        for(Tile t: sortedTiles) {
            moveTile(t, 2);
        }

        placeRandomTile();
    }

    public void upPressed() {
        alreadyMoved = new boolean[size][size];
        ArrayList<Tile> sortedTiles = new ArrayList<Tile>();

        for(int y = 0; y <size; y++) {
            for(int x = 0; x < size; x++) {
                if(tiles[x][y] != 0) {
                    Tile t = new Tile(x, y, tiles[x][y]);
                    sortedTiles.add(t);
                    tiles[x][y] = 0;
                }
            }
        }

        for(Tile t: sortedTiles) {
            moveTile(t, 0);
        }

        placeRandomTile();
    }

    private void moveTile(Tile t, int dir) {
        final int currentX = t.getX(); // 3
        final int currentY = t.getY(); // 1
        final int currentValue = t.getValue();
        int verschiebung = 0;
        int newX = 0, newY = 0;
        int newValue = currentValue;

            switch (dir) {
                case 0:
                    // Hoch
                    verschiebung = -1;
                    newX = currentX;

                    while(true) {
                        newY = currentY+verschiebung;

                        // Wenn rechts out of Bounds
                        if(newY<0) {
                            newY++;
                            break;
                        }

                        // Wenn Feld gleichen Wert und noch nicht gemoved
                        if(!alreadyMoved[newX][newY] && tiles[newX][newY] == currentValue) {
                            newValue=2*currentValue;
                            alreadyMoved[newX][newY] = true;
                            score+=newValue;
                            break;
                        }

                        // Wenn Feld anders belegt
                        if(tiles[newX][newY] != 0) {
                            newY++;
                            break;
                        }

                        verschiebung--;
                    }
                    break;
                case 1:
                    // Rechts
                    verschiebung = 1;
                    newY = currentY;

                    while(true) {
                        newX = currentX+verschiebung;

                        // Wenn rechts out of Bounds
                        if(newX>=size) {
                            newX--;
                            break;
                        }

                        // Wenn Feld gleichen Wert und noch nicht gemoved
                        if(!alreadyMoved[newX][newY] && tiles[newX][newY] == currentValue) {
                            newValue=2*currentValue;
                            alreadyMoved[newX][newY] = true;
                            score+=newValue;
                            break;
                        }

                        // Wenn Feld anders belegt
                        if(tiles[newX][newY] != 0) {
                            newX--;
                            break;
                        }

                        verschiebung++;
                    }
                    break;
                case 2:
                    // Runter
                    verschiebung = 1;
                    newX = currentX;

                    while(true) {
                        newY = currentY+verschiebung;

                        // Wenn rechts out of Bounds
                        if(newY>=size) {
                            newY--;
                            break;
                        }

                        // Wenn Feld gleichen Wert und noch nicht gemoved
                        if(!alreadyMoved[newX][newY] && tiles[newX][newY] == currentValue) {
                            newValue=2*currentValue;
                            alreadyMoved[newX][newY] = true;
                            score+=newValue;
                            break;
                        }

                        // Wenn Feld anders belegt
                        if(tiles[newX][newY] != 0) {
                            newY--;
                            break;
                        }

                        verschiebung++;
                    }

                    break;
                case 3:
                    // Links
                    verschiebung = -1;
                    newY = currentY;

                    while(true) {
                        newX = currentX+verschiebung;

                        // Wenn rechts out of Bounds
                        if(newX<0) {
                            newX++;
                            break;
                        }

                        // Wenn Feld gleichen Wert und noch nicht gemoved
                        if(!alreadyMoved[newX][newY] && tiles[newX][newY] == currentValue) {
                            newValue=2*currentValue;
                            alreadyMoved[newX][newY] = true;
                            score+=newValue;
                            break;
                        }

                        // Wenn Feld anders belegt
                        if(tiles[newX][newY] != 0) {
                            newX++;
                            break;
                        }

                        verschiebung--;
                    }
                    break;
            }

            tiles[newX][newY] = newValue;
        }

    public boolean isLost() {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(isPossibleMove(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPossibleMove(int x, int y) {

        //Oben
        int newPos = y-1;
        if(newPos >= 0) {
            if(tiles[x][newPos] == tiles[x][y] || tiles[x][newPos] == 0) {
                return true;
            }
        }
        // Unten
        newPos = y+1;
        if(newPos < size) {
            if(tiles[x][newPos] == tiles[x][y] || tiles[x][newPos] == 0) {
                return true;
            }
        }
        //Links
        newPos = x-1;
        if(newPos >= 0) {
            if(tiles[newPos][y] == tiles[x][y] || tiles[newPos][y] == 0) {
                return true;
            }
        }
        // Unten
        newPos = x+1;
        if(newPos < size) {
            if(tiles[newPos][y] == tiles[x][y] || tiles[newPos][y] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isFull() {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(tiles[x][y] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void draw(Graphics2D g2) {
        drawControl(g2);
        drawScore(g2);
        drawField(g2);
        if(gameOver) {
            drawGameOver(g2);
        }
    }

    private void drawControl(Graphics2D g2) {

        Color iconColor = new Color(1, 0.5f, 0.1f);
        // Home
        if(selectedButton == 1) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(10, 10, 70, 70, 20, 20);
        g2.setColor(iconColor);
        g2.fillRect(30, 45, 30, 25);
        g2.fillPolygon(new Polygon(new int[] {20, 45, 70}, new int[] {45, 20, 45}, 3));

        // Restart
        iconColor = Color.ORANGE;
        if(selectedButton == 2) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(90, 10, 70, 70, 20, 20);
        int x = 100, y = 20, width = 50, height = 50, thickness = 5;
        g2.setColor(iconColor);
        g2.fillOval(x,y,width,height);
        if(selectedButton == 2) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillOval(x+thickness,y+thickness,width-2*thickness,height-2*thickness);
        g2.fillRect(134,22, 19, 14);
        g2.setColor(iconColor);
        g2.fillPolygon(new Polygon(new int[] {136, 138, 155}, new int[] {25, 45, 31}, 3));

        // Scores
        iconColor = Color.YELLOW;
        if(selectedButton == 3) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRoundRect(170, 10, 70, 70, 20, 20);

        g2.setColor(iconColor);
        g2.fillOval(180, 10, 51, 48);
        if(selectedButton == 3) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillOval(182, 11, 47, 45);
        g2.fillRect(170, 10, 70, 21);
        g2.setColor(iconColor);
        g2.fillOval(188, 5, 35, 54);
        g2.fillRect(181, 31, 50, 3);
        g2.setColor(Screen.background);
        g2.fillRect(188, 5, 35, 5);
        if(selectedButton == 3) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRect(188, 10, 35, 13);
        g2.setColor(iconColor);
        g2.fillPolygon(new Polygon(new int[] {194, 194, 189}, new int[] {58, 70, 70}, 3));
        g2.fillPolygon(new Polygon(new int[] {216, 216, 221}, new int[] {58, 70, 70}, 3));
        g2.fillRect(194, 58, 22, 12);
    }

    private void drawScore(Graphics2D g2) {

        int width = 150;
        int xPos = 475-width;
        int yPos = 225-55;

        g2.setColor(Color.BLACK);
        g2.fillRoundRect(xPos, yPos, width, 50, 25, 25);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        int textX = getXForCenteredText(""+score, width, g2);

        g2.drawString(""+score, xPos+textX, yPos+35);

    }

    private void drawField(Graphics2D g2) {

        g2.setColor(Color.BLACK);
        g2.fillRoundRect(25, 225, 450, 450, 40, 40);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(xFieldBegin, yFieldBegin, fieldWidth, fieldWidth, 35, 35);

        g2.setColor(Color.BLACK);
        for(int i = 0; i <= size; i++) {
            g2.fillRect(xFieldBegin + (i*cellWidth) - 5, yFieldBegin, 10, fieldWidth);
            g2.fillRect(xFieldBegin, yFieldBegin + (i*cellWidth) - 5, fieldWidth, 10);
        }


        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(tiles[x][y] != 0) {
                    g2.drawImage(getImage(tiles[x][y]), xFieldBegin+5 + (x*cellWidth), yFieldBegin+5+(y*cellWidth), null);
                    //g2.drawString(""+tiles[x][y], xFieldBegin + (x*cellWidth) + xCenter, yFieldBegin + y*cellWidth + 70);
                }
            }
        }
    }

    private void drawGameOver(Graphics2D g2) {
        Color color = new Color(1, 0, 0, 0.25f);
        g2.setColor(color);
        g2.fillRect(xFieldBegin+5, yFieldBegin+5, fieldWidth-10, fieldWidth-10);

        // Restart
        g2.setColor(new Color(0,0,0, 0.9f));
        g2.fillRoundRect(xFieldBegin+(fieldWidth/2)-100, yFieldBegin+(fieldWidth/2)-150, 200, 100, 25, 25);
        g2.fillRoundRect(xFieldBegin+(fieldWidth/2)-100, yFieldBegin+(fieldWidth/2)+50, 200, 100, 25, 25);
        if(selectedButton == 4) {
            g2.setColor(new Color(0,1,0,0.9f));
        } else {
            g2.setColor(new Color(1,1,1,0.9f));
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        int tx = getXForCenteredText("Restart", 200, g2);
        g2.drawString("Restart", xFieldBegin+(fieldWidth/2)-100 + tx, yFieldBegin+(fieldWidth/2)-150 + 65);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
        tx = getXForCenteredText("Save Score", 200, g2);
        if(selectedButton == 5) {
            g2.setColor(new Color(0,1,0,0.9f));
        } else {
            g2.setColor(new Color(1,1,1,0.9f));
        }
        g2.drawString("Save Score", xFieldBegin+(fieldWidth/2)-100 + tx, yFieldBegin+(fieldWidth/2)+43 + 67);
    }

    private BufferedImage getImage(int num) {
        int zaehler = 0;
        int pos = 2;
        while(true) {
            if(num==pos) {
                break;
            }
            pos=pos*2;
            zaehler++;
        }
        return imgs[zaehler];
    }

    public int getXForCenteredText(String text, int width, Graphics2D g2) {
        return width / 2 - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth()) / 2;
    }


}
