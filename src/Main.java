import java.io.IOException;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    private PImage img;
    public static void main(String[] args) {
        PApplet.main("Main", args);
        System.out.println("Welcome to Black Jack!");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        img = loadImage("/Users/caseyrharding/IdeaProjects/BlackJackVisuals/src/CardGameImages/cardTable.png");
        img.resize(width, height);
        Game game = new Game();
        game.run();
    }

    public void draw() {
        background(img);
    }
}
