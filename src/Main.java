import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import controlP5.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main extends PApplet {
    private PImage img;
    private String path;
    private String name;
    private Game game;
    private HashMap<String, String> state;
    private int count;
    private String money;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> computerCards;

    private ControlP5 cp5;
    private Textfield myTextfield;
    private Numberbox numbers;
    private Bang bang;
    private PFont f;

    public Main() {
        name = "";
        path = "/Users/caseyrharding/IdeaProjects/BlackJackVisuals/src/CardGameImages";
        state = new HashMap<>();
        state.put("screen", "welcome");
        count = 0;
        money = "0";
        playerCards = new ArrayList<>();
        computerCards = new ArrayList<>();
    }

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
//        noLoop();
//        File dir = new File(path);
//        File[] fileList = dir.listFiles();
//
//        ArrayList<File> files = new ArrayList<>(Arrays.asList(fileList));
//        PImage[] images = new PImage[files.size()];
//
//        for (int i = 0; i < files.size(); i++) {
//            images[i] = loadImage(String.valueOf (files.get(i)));
//        }

        img = loadImage(path + "/cardTable.png");
        img.resize(width, height);

        f = createFont("Arial", 16, true);

        textAlign (CENTER, CENTER);
        textSize (30);
        fill(0);
        cp5 = new ControlP5(this);

        myTextfield = cp5.addTextfield("Name")
        .setPosition (400, 400)
        .setSize(350, 35)
        .setFocus(true)
        ;

        myTextfield.keepFocus(true);
        game = new Game(name, this, state);
    }

    public void controlEvent(ControlEvent theEvent) {
        if (theEvent.isController()) {
            if (theEvent.getController().getName().equals("Name")) {
                name = theEvent.getController().getStringValue();
                state.put("name", name);
                state.put("screen", "main");
                game.setState(state);
            }
            if (theEvent.getController ().getName ().equals ("Money")) {
                state.put ("money", Float.toString (theEvent.getController ().getValue ()));
            }
        }
    }

    public void draw() {
        background(img);
        textFont(f, 36);
        fill(255);

        switch(state.get("screen")) {
            case "main":
                text("Hello, " + name, 100, 100);
                state = game.getState();

                if (state.containsKey("founduser")) {
                    if (state.get("founduser").equals("true")) {
                        state.put ("screen", "run");
                    } else {
                        state = game.getState();
                        addMoneyBox ();

                        if (state.containsKey("errors")) {
                            text(game.getErrors(), 250, 250);
                        }
                        if (state.containsKey("messages")) {
                            text(game.getMessages(), 450, 450);
                        }
                    }
                } else {
                    state = game.getState ();
                    addMoneyBox ();

                    if (state.containsKey("errors")) {
                        text(game.getErrors(), 250, 250);
                    }
                    if (state.containsKey("messages")) {
                        text(game.getMessages(), 450, 450);
                    }
                }
                break;
            case "run":
                state = game.getState();
                text(state.get("name"), 150, 150);
                text(money, 250, 250);

                if (state.containsKey("errors")) {
                    text(game.getErrors(), 250, 250);
                }
                if (state.containsKey("messages")) {
                    text(game.getMessages(), 450, 450);
                }
        }
        state = game.getState();
    }

    public void addMoney(ControlEvent theEvent) {
        game.setState (state);
        numbers.remove();
        bang.remove();
        money = state.get("money");
        game.run();
        playerCards = game.getPlayerCards ();
        computerCards = game.getComputerCards ();
        state.put ("screen", "run");
    }

    private void addMoneyBox() {
        if (count < 1) {
            game.getMoney();
            myTextfield.remove ();
            numbers = cp5.addNumberbox ("Money").setPosition (400, 400).setSize (350, 35);
            bang = cp5.addBang("addMoney")
                    .setPosition(450, 450)
                    .setSize(40, 40);
            count++;
        }
    }
}
