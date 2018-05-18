import java.io.IOException;
import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main", args);
        System.out.println("Welcome to Black Jack!");
        Game game = new Game();
        try {
            game.run();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void settings() {
        size(640, 480);
    }

    public void setup() {
        background(102);
    }

    public void draw() {
        stroke(255);
        if (mousePressed) {
            line(mouseX, mouseY, pmouseX, pmouseY);
        }
    }
}
