import processing.core.PApplet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

class Game {
    PApplet p;
    private User player;
    private User computer;
    private Deck d;
    private Hit hit;
    private String name;
    private HashMap<String, String> state;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> computerCards;
    private String errors;
    private String messages;
    private boolean playAgain;
    private int money;
    private int numberOfDecks;

    Game(String name, PApplet p, HashMap<String, String> state) {
        this.p = p;
        this.player = new User ("", 0);
        this.computer = new User ("Dealer", 0);
        playerCards = new ArrayList<>();
        computerCards = new ArrayList<>();
        playAgain = true;
        money = 0;
        numberOfDecks = 2;
        this.name = name;
        this.p = p;
        this.state = new HashMap<>();
        this.errors = "";
        this.messages = "";
        this.state = state;
        Deck d = new Deck(numberOfDecks);
        playerCards = player.setupBlackjack(d);
        computerCards = computer.setupBlackjack(d);
        hit = new Hit(computer, player, d, playerCards, computerCards);

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

    ArrayList<Card> getPlayerCards() { return playerCards; }

    ArrayList<Card> getComputerCards() {
        return computerCards;
    }

    int playerMoney() {
        return player.getMoney();
    }

    void setPlayerMoney(int money) {
        player.setMoney(player.getMoney() - money);
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

//        if (saveFile.containsKey(name)) {
//            money = Integer.parseInt(saveFile.get(name).toString());
//            state.put("founduser", "true");
//            if (money <= 0) {
//                state.put("Errors", "true");
//                errors = "Golly, " + player.getName() + " looks like you're gonna have to change some money. How much would you like to add?";
//            } else {
//                errors = "";
//                messages = "Welcome back, " + player.getName() + "! You currently have: $" + money;
//            }
//            errors = "";
//        }
    }

    void hit() {
        playerCards = hit.playerHit();
    }

    void stay() {
        computerCards = hit.computerHit();
    }

    void run() {
        state.put("getmoney", "false");
        player.setMoney(money);

//            Bet b = new Bet(reader, player);
//            int playerBet = b.bet();
//            boolean playerWin = hit.playerHit();
//            printPlayer();
//
//            if (playerWin) {
//                System.out.println("You won!! You have $" + player.getMoney() + " left.");
//            } else {
//                System.out.println("You lost. You have $" + player.getMoney() + " left");
//            }
//
//            if (player.getMoney() == 0) {
//                System.out.println ("Gosh, " + player.getName() + ", I guess you're gonna have to come back when you have more dough...");
//            } else {
//                System.out.println ("Well, " + player.getName () + ", would you like to play again? Yes or No");
//                String wantToPlay = reader.next ().toLowerCase ();
//                playAgain = wantToPlay.equals ("y") || wantToPlay.equals ("yes");
//                if (playAgain) {
//                    resetGame ();
//                }
//            }
//        }

//        player.setName(player.getName().toLowerCase());
//
//        saveFile.remove (player.getName());
//
//        saveFile.setProperty(player.getName(), Integer.toString(player.getMoney()));
//
//        try {
//            file.writeFile();
//        } catch (IOException e) {
//            e.printStackTrace ();
//        }
//        reader.close();
    }

    private void printPlayer() {
        System.out.println("Your hand:");
        System.out.println(player.toString());
    }

    private void resetGame() {
        player.resetHand();
        computer.resetHand();
        if (d.getSize() < 15) {
            d = new Deck (numberOfDecks);
        }
    }
}
