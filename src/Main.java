import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import controlP5.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends PApplet {
    private PImage img;
    private String path;
    private String name;
    private String[] filenames;
    private int state = 0;

    ControlP5 cp5;
    Textfield myTextfield;
    PFont f;

    public Main() {
        name = "";
        path = "/Users/caseyrharding/IdeaProjects/BlackJackVisuals/src/CardGameImages";
    }

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        noLoop();
        filenames = loadFilenames(path);
        ArrayList<String> files = new ArrayList<String>(Arrays.asList(filenames));
        System.out.println (files);

        img = loadImage(path + "/cardTable.png");
        img.resize(width, height);

        f = createFont("Arial", 16, true);

        textAlign (CENTER, CENTER);
        textSize (30);
        fill(0);
        cp5 = new ControlP5(this);

        myTextfield = cp5.addTextfield("Name")
        .setPosition (100, 200)
        .setSize(200, 20)
        .setFocus(true)
        ;

        myTextfield.keepFocus(true);
    }

    String[] loadFilenames(String path) {
        File folder = new File (path);
        return folder.list();
    }

    public void draw() {
        background(img);
        textFont(f, 36);
        fill(255);

        switch(state) {
            case 0:
                text(cp5.get(Textfield.class,"Name").getText(), 360,130);
                break;
            case 1:
                myTextfield.remove();
                text("Hello, " + name, 100, 100);
                    Game game = new Game(name, this);
                    game.run();
                break;
        }
    }

    public void Name(String theText) {
        name = theText;
        state++;
    }
}
