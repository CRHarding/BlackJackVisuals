import java.util.Scanner;

class Bet {
    private Scanner reader;
    private User player;

    Bet(Scanner reader, User player) {
        this.reader = reader;
        this.player = player;
    }

    int bet() {
        int playerBet;
        do {
            playerBet = reader.nextInt();
            if (playerBet < 0) System.out.println ("You've gotta bet more than 0 ya dingus!");
            if (playerBet > player.getMoney())
                System.out.println ("We both know that you don't have that kinda dough...");
        } while (playerBet < 0 || playerBet > player.getMoney());
        return playerBet;
    }
}
