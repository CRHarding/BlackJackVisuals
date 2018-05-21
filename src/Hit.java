import java.util.ArrayList;

class Hit {
    private User computer;
    private User player;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> computerHand;
    private Deck d;

    Hit(User computer, User player, Deck d, ArrayList<Card> playerHand, ArrayList<Card> computerHand) {
        this.computer = computer;
        this.player = player;
        this.d = d;
        this.playerHand = playerHand;
        this.computerHand = computerHand;
    }

    ArrayList<Card> computerHit() {
        while (computer.getTotal() < 16) {
            computerHand = computer.addCard(d);
            int computerTotal = computer.getTotal();
            if (computerTotal > 21 && computerTotal != 50) {
                System.out.println("Dealer busted!.");
            }
        }
        return computerHand;
    }

    ArrayList<Card> playerHit() {
        playerHand = player.addCard(d);
        return playerHand;
    }
}
