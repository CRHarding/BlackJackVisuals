import java.util.ArrayList;

class Hit {
    private User computer;
    private User player;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> computerHand;
    private Deck d;

    Hit(User computer, User player, Deck d, ArrayList<Card> playerHand, ArrayList<Card> ComputerHand) {
        this.computer = computer;
        this.player = player;
        this.d = d;
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
//        boolean playerWin;
        playerHand = player.addCard(d);
        System.out.println (playerHand.toString());
        return playerHand;
//        int playerTotal = player.getTotal();

//        if (playerTotal > 21 && playerTotal != 50) {
//            return false;
//        }
//
//        playerWin = findWin();
//        return playerWin;
    }

    private boolean findWin() {
        if (player.getTotal() == 50) {
            System.out.println ("YOU GOT BLACKJACK" + player.getName().toUpperCase() + "!!!");
//            player.setMoney(playerBet * 4);
            return true;
        }else if (player.getTotal() > computer.getTotal()) {
//            player.setMoney(playerBet * 2);
            return true;
        } else {
//            player.setMoney(playerBet * -1);
            return false;
        }
    }
}
