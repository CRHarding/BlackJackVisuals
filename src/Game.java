import processing.core.PApplet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

class Game {
    private User player;
    private User computer;
    private Deck d;
    private Hit hit;
    private String name;
    private HashMap<String, String> state;
    private ArrayList<Card> loadPlayerCards;
    private ArrayList<Card> loadComputerCards;
    private String errors;
    private String messages;
    private int money;
    private int numberOfDecks;

    Game(String name, HashMap<String, String> state) {
        this.player = new User ("", 0);
        this.computer = new User ("Dealer", 0);
        loadPlayerCards = new ArrayList<>();
        loadComputerCards = new ArrayList<>();
        money = 0;
        numberOfDecks = 2;
        this.name = name;
        this.state = new HashMap<>();
        this.errors = "";
        this.messages = "";
        this.state = state;
        Deck d = new Deck(numberOfDecks);
        loadPlayerCards = player.setupBlackjack(d);
        loadComputerCards = computer.setupBlackjack(d);
        hit = new Hit(computer, player, d, loadPlayerCards, loadComputerCards);
    }

    HashMap<String, String> getState() {
        return state;
    }

    void setState(HashMap<String, String> state) {
        this.state = state;
    }

    String getErrors() {
        return errors;
    }

    String getMessages() {
        return messages;
    }

    ArrayList<Card> getPlayerCards() {
        return this.player.getHand();
    }

    ArrayList<Card> getComputerCards() {
        return this.computer.getHand();
    }

    void setPlayerMoney(int money) {
        player.setMoney(player.getMoney() - money);
    }

    int getPlayerScore() {
        return player.getTotal();
    }

    int getComputerScore() {
        return computer.getTotal();
    }

    void getMoney() {
        FileManagement file = new FileManagement();
        Properties saveFile = null;
        try {
            saveFile = file.readFile ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        name = state.get("name");
        name = name.toLowerCase ();

        if (saveFile.containsKey(name)) {
            money = Integer.parseInt(saveFile.get(name).toString());
            state.put("founduser", "true");
            if (money <= 0) {
                state.put("Errors", "true");
                errors = "Golly, " + player.getName() + " looks like you're gonna have to change some money. How much would you like to add?";
            } else {
                errors = "";
                messages = "Welcome back, " + player.getName() + "! You currently have: $" + money;
            }
            errors = "";
        }
    }

    void hit() {
        loadPlayerCards = hit.playerHit();
    }

    void stay() {
        loadComputerCards = hit.computerHit();
    }

    void run() {
        state.put("getmoney", "false");
        player.setMoney(money);
    }

    void save() {
        FileManagement file = new FileManagement();
        Properties saveFile = null;
        try {
            saveFile = file.readFile ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        player.setName(player.getName().toLowerCase());

        saveFile.remove (player.getName());

        saveFile.setProperty(player.getName(), Integer.toString(player.getMoney()));

        try {
            file.writeFile();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
