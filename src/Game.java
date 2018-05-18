import processing.core.PApplet;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

class Game {
    PApplet p;
    private User player;
    private User computer;
    private Deck d;
    private boolean playAgain;
    private int money;
    private int numberOfDecks;

    Game(String name, PApplet p) {
        this.p = p;
        this.player = new User ("", 0);
        this.computer = new User ("Dealer", 0);
        playAgain = true;
        money = 0;
        numberOfDecks = 0;
    }

    void run() {
        Scanner reader = new Scanner (System.in);
        System.out.println("What is your name?");

        String name = reader.nextLine();
        player.setName(name);

        FileManagement file = new FileManagement();
        Properties saveFile = null;
        try {
            saveFile = file.readFile ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        name = name.toLowerCase ();
        if (saveFile.containsKey(name)) {
            money = Integer.parseInt(saveFile.get(name).toString());
            if (money <= 0) {
                System.out.println ("Golly, " + player.getName() + " looks like you're gonna have to change some money. How much would you like to add?");
                while (money <= 0) {
                    money = reader.nextInt();
                    if (money <= 0) System.out.println("You've gotta have some dough, buddy...");
                }
            } else {
                System.out.println ("Welcome back, " + player.getName() + "! You currently have: $" + money);
            }
        } else System.out.println ("Welcome " + player.getName() + ", how much money would you like to convert into chips?");

        while (money <= 0) {
            money = reader.nextInt();
            if (money <= 0) System.out.println("You've gotta have some dough, buddy...");
        }

        player.setMoney(money);

        System.out.println("How many decks will you be playing with?");
        numberOfDecks = reader.nextInt();
        d = new Deck(numberOfDecks);

        while (player.getMoney() > 0 && playAgain) {
            player.setupBlackjack(d);
            computer.setupBlackjack(d);

            Bet b = new Bet(reader, player);
            int playerBet = b.bet();
            Hit hit = new Hit(computer, player, d, reader, playerBet);
            boolean playerWin = hit.playerHit();
            printPlayer();

            if (playerWin) {
                System.out.println("You won!! You have $" + player.getMoney() + " left.");
            } else {
                System.out.println("You lost. You have $" + player.getMoney() + " left");
            }

            if (player.getMoney() == 0) {
                System.out.println ("Gosh, " + player.getName() + ", I guess you're gonna have to come back when you have more dough...");
            } else {
                System.out.println ("Well, " + player.getName () + ", would you like to play again? Yes or No");
                String wantToPlay = reader.next ().toLowerCase ();
                playAgain = wantToPlay.equals ("y") || wantToPlay.equals ("yes");
                if (playAgain) {
                    resetGame ();
                }
            }
        }

        player.setName(player.getName().toLowerCase());

        saveFile.remove (player.getName());

        saveFile.setProperty(player.getName(), Integer.toString(player.getMoney()));

        try {
            file.writeFile();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        reader.close();
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
