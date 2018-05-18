import java.io.IOException;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    private PImage img;
    private String myText;

    public Main() {
        myText = "What is your name?";
    }

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
        textAlign (CENTER, CENTER);
        textSize (30);
        fill(0);
//        Game game = new Game();
//        game.run();
    }

    public void draw() {
        background(img);
        text(myText, 0, 0, width, height);
    }

    public void keyPressed() {
        if (keyCode == BACKSPACE) {
            if (myText.length() > 0) {
                myText = myText.substring(0, myText.length()-1);
            }
        } else if (keyCode == DELETE) {
            myText = "";
        } else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT) {
            myText = myText + key;
        }
    }
}
