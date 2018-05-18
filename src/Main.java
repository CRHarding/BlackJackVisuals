import processing.core.PApplet;
import processing.core.PImage;
import controlP5.*;

public class Main extends PApplet {
    private PImage img;
    private String name;
    private int state = 0;

    ControlP5 cp5;
    Textfield myTextfield;

    public Main() {
        name = "";
    }

    public static void main(String[] args) {
        PApplet.main("Main", args);
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
        cp5 = new ControlP5(this);
        myTextfield = cp5.addTextfield("Name")
        .setPosition (100, 200)
        .setSize(200, 20)
        .setFocus(true)
        ;

        myTextfield.keepFocus(true);

//        Game game = new Game();
//        game.run();
    }

    public void draw() {
        background(img);
        text(cp5.get(Textfield.class,"Name").getText(), 360,130);
    }

    public void Name(String theText) {
        println("Controller input-->" + theText);
    }

    void submit(String name) {
        myTextfield.submit();
    }

    void performTextfieldActions() {
        name = myTextfield.getText();
        System.out.println (name);
    }
}
