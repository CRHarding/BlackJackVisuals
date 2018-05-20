import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import controlP5.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main extends PApplet {
    private PImage backgroundImage;
    private PImage betCircleImage;
    private PImage cornerImage;
    private PImage cornerImage2;
    private PImage cornerImage3;
    private PImage cornerImage4;
    private PImage smallBet;
    private PImage mediumBet;
    private PImage largeBet;
    private HashMap<String, PImage> images;
    private String path;
    private String name;
    private Game game;
    private HashMap<String, String> state;
    private int count;
    private String money;

    private ArrayList<Card> playerCards;
    private ArrayList<Card> computerCards;

    private boolean loadCards;
    private boolean showComputerCards;
    private boolean bet;
    private boolean hitOrStay;
    private boolean errors;
    private String errorMessage;
    private int playerBetAmount;

    private ArrayList<PImage> playerCardImages;
    private ArrayList<PImage> computerCardImages;

    private ControlP5 cp5;
    private Textfield myTextfield;
    private Numberbox numbers;
    private Bang bang;
    private Bang hit;
    private Bang betButton;
    private Bang stay;

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
        images = new HashMap<>();
        loadCards = false;
        playerCardImages = new ArrayList<>();
        computerCardImages = new ArrayList<>();
        showComputerCards = false;
        bet = false;
        hitOrStay = false;
        errors = false;
        errorMessage = "";
        playerBetAmount = 0;
    }

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
//        noLoop();
        File dir = new File(path);
        File[] fileList = dir.listFiles();

        ArrayList<File> files = new ArrayList<>(Arrays.asList(fileList != null ? fileList : new File[0]));

        for (File file : files) {
            images.put (String.valueOf (file), loadImage (path + "/" + file.getName ()));
        }

        backgroundImage = loadImage(path + "/cardTable.png");
        backgroundImage.resize(width, height);

        betCircleImage = loadImage(path + "/betting-circle.png");
        betCircleImage.resize(450, 450);

        cornerImage = loadImage(path + "/honor_clubs.png");
        cornerImage.resize(350, 250);

        cornerImage2 = loadImage(path + "/honor_diamond.png");
        cornerImage2.resize(350, 250);

        cornerImage3 = loadImage(path + "/honor_heart-14.png");
        cornerImage3.resize(350, 250);

        cornerImage4 = loadImage(path + "/honors_spade-14.png");
        cornerImage4.resize(350, 250);

        smallBet = loadImage(path + "/small-bet.png");
        smallBet.resize(75, 75);

        mediumBet = loadImage(path + "/Chips.png");
        mediumBet.resize(75, 75);

        largeBet = loadImage(path +"/big-bet.png");
        largeBet.resize(75, 75);

        f = createFont("Arial", 16, true);

        textAlign (CENTER, CENTER);
        textSize (30);
        fill(0);
        cp5 = new ControlP5(this);

        myTextfield = cp5.addTextfield("Name")
        .setPosition (width/2-175, 100)
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
                int playerMoney = (int) theEvent.getController ().getValue ();
                if (playerMoney <= 0) {
                    errors = true;
                    errorMessage = "Ya gotta have at least some money \n ya dingus...";
                } else {
                    state.put("money", Integer.toString (playerMoney));
                    errors = false;
                }
            }

            if (theEvent.getController ().getName ().equals ("addMoney")) {
                if (!errors  && Integer.parseInt(state.get("money")) > 0) {
                    loadCards = true;
                    game.setState (state);
                    numbers.remove ();
                    bang.remove ();
                    money = state.get ("money");
                    bet = true;
                    game.run ();
                    state.put ("screen", "run");
                }
            }

            if (theEvent.getController ().getName ().equals ("BetAmount")) {
                int currentMoney = Integer.parseInt(money);
                int betMoney = (int) theEvent.getController ().getValue ();

                if (betMoney > currentMoney) {
                    errors = true;
                    errorMessage = "You don't have that kinda dough\n ya dingus...";
                }

                if (betMoney <= 0) {
                    errors = true;
                    errorMessage = "Ya gotta bet more than 0 ya dingus...";
                }

                if (betMoney < currentMoney && betMoney > 0) {
                    errors = false;
                    game.setPlayerMoney (betMoney);
                    playerBetAmount = betMoney;
                }
            }

            if (theEvent.getController ().getName ().equals("addBet")) {
                if (!errors && playerBetAmount > 0) {
                    hitOrStay = true;
                }
            }

            if (theEvent.getController ().getName ().equals ("Hit")) {
                game.hit();
                playerCards.clear();
                playerCardImages.clear();
                playerCards = game.getPlayerCards ();
                loadCards = true;
            }

            if (theEvent.getController ().getName ().equals ("Stay")) {
                game.stay();
                computerCards.clear();
                computerCardImages.clear();
                computerCards = game.getComputerCards ();
                playerCards.clear();
                playerCardImages.clear();
                playerCards = game.getPlayerCards ();
                showComputerCards = true;
                loadCards = true;
            }
        }
    }

    public void draw() {
        background(backgroundImage);
        image(betCircleImage, width/2 - (450/2), height/2 - (450/2));
        image(cornerImage, width-350, height-250);
        image(cornerImage2, 0, 0);
        image(cornerImage3, width-350, 0);
        image(cornerImage4, 0, height-250);

        textFont(f, 36);
        fill(255);

        switch(state.get("screen")) {
            case "main":
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
                        text(game.getErrors(), 50, 250);
                    }
                    if (state.containsKey("messages")) {
                        text(game.getMessages(), 150, 250);
                    }
                }
                break;
            case "run":
                state = game.getState();
                text("Welcome, ", width - (state.get("name").length() * 25), 450);
                text(state.get("name"), width - (state.get("name").length() * 25), 500);
                text("Current dough:", 125, 500);

                int moneyAsInteger = Integer.parseInt(money);
                text("$" + moneyAsInteger, 50, 550);

                if (loadCards) {
                    playerCards = game.getPlayerCards ();
                    computerCards = game.getComputerCards();

                    String[] cardFileNames = changeCardToFilename (playerCards);
                    String[] computerCardFileNames = changeCardToFilename (computerCards);

                    for (int i = 0; i < playerCards.size(); i++) {
                        playerCardImages.add (i, images.get(cardFileNames[i]));
                    }

                    for (int i = 0; i < computerCards.size(); i++) {
                        computerCardImages.add (i, images.get(computerCardFileNames[i]));
                    }

                    loadCards = false;
                }

                if (!loadCards) {
                    playerCards = game.getPlayerCards ();
                    computerCards = game.getComputerCards();
                    int placement = (width - (325)) / playerCardImages.size ();
                    for (int i = 0; i < playerCardImages.size (); i++) {
                        PImage img = playerCardImages.get (i);
                        img.resize (150, 250);
                        image (img, placement, 750);
                        placement = placement + (i + 150);
                    }
                }

                if (showComputerCards) {
                    int placement = (width - (325)) / computerCardImages.size();
                    for (int i = 0; i < computerCardImages.size (); i++) {
                        PImage img = computerCardImages.get(i);
                        img.resize(150, 250);
                        image (img, placement, 50);
                        placement = placement + (i + 150);
                    }
                } else {
                    int placement = (width - (325)) / 2;
                    for (int i = 0; i < 2; i++) {
                        PImage img = images.get (path + "/" + "red_back.png");
                        img.resize (150, 250);
                        image (img, placement, 50);
                        placement = placement + (i + 150);
                    }
                }

                if (bet) {
                    numbers = cp5.addNumberbox ("BetAmount")
                            .setPosition (500, 500)
                            .setSize (60, 35);
                    betButton = cp5.addBang("addBet")
                            .setPosition(550, 500)
                            .setSize(40, 40);
                    bet = false;
                }

                if (hitOrStay) {
                    numbers.remove();
                    betButton.remove();
                    hit = cp5.addBang("Hit")
                            .setPosition(500, 500)
                            .setSize(40, 40);
                    stay = cp5.addBang("Stay")
                            .setPosition(550, 500)
                            .setSize(40, 40);
                    hitOrStay = false;
                    loadCards = false;
                }
        }
        if (errors) {
            text(errorMessage, 350, 750);
        }
    }

    private String[] changeCardToFilename(ArrayList<Card> hand) {
        String[] cardFileNames = new String[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            String cardFile = hand.get(i).toString();
            cardFile = cardFile.replace(' ', '_');
            cardFile = cardFile.concat(".png");
            cardFile = path + "/" + cardFile;
            cardFileNames[i] = cardFile;
        }
        return cardFileNames;
    }

    private void addMoneyBox() {
        if (count < 1) {
            game.getMoney();
            myTextfield.remove ();
            numbers = cp5.addNumberbox ("Money").setPosition (width/2-175, 50).setSize (350, 35);
            bang = cp5.addBang("addMoney")
                    .setPosition(width/2-175, 100)
                    .setSize(40, 40);
            count++;
        }
    }
}
