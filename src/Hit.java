import java.util.Scanner;

class Hit {
    private User computer;
    private User player;
    private Deck d;
    private Scanner reader;
    private int playerBet;

    Hit(User computer, User player, Deck d, Scanner reader, int playerBet) {
        this.computer = computer;
        this.player = player;
        this.d = d;
        this.reader = reader;
        this.playerBet = playerBet;
    }

    private void computerHit() {
        while (computer.getTotal() < 16) {
            System.out.println("This is dealer total points --> " + computer.getTotal());
            computer.addCard(d);
            int computerTotal = computer.getTotal();
            System.out.println("This is dealer total points --> " + computerTotal);
            if (computerTotal > 21 && computerTotal != 50) {
                System.out.println("Dealer busted!.");
            } else {
                System.out.println("This is dealer total points --> " + computer.getTotal());
                System.out.println ("Current Cards: " + computer.toString ());
            }
        }
    }

    boolean playerHit() {
        boolean hit = true;
        boolean playerWin;
        while (hit) {
            System.out.println("This is your total points --> " + player.getTotal());
            System.out.println("Would you like to hit or stay?");
            String choice = reader.next().toLowerCase();
            if (choice.equals("h") || choice.equals("hit")) {
                player.addCard(d);
                int playerTotal = player.getTotal();
                System.out.println("This is your total points --> " + playerTotal);
                if (playerTotal > 21 && playerTotal != 50) {
                    System.out.println("You busted, sorry chump.");
                    player.setMoney(playerBet * -1);
                    return false;
                } else {
                    System.out.println("This is your total points --> " + player.getTotal());
                    System.out.println ("Current Cards: " + player.toString ());
                }
            } else if (choice.equals("s") || choice.equals("stay")) {
                hit = false;
            }
        }
        computerHit();
        playerWin = findWin();
        return playerWin;
    }

    private boolean findWin() {
        System.out.println("Computer hand:");
        System.out.println(computer.toString());
        System.out.println ("Player total--->" + player.getTotal());
        System.out.println ("Computer total--->" + computer.getTotal());
        if (player.getTotal() == 50) {
            System.out.println ("YOU GOT BLACKJACK" + player.getName().toUpperCase() + "!!!");
            player.setMoney(playerBet * 4);
            return true;
        }else if (player.getTotal() > computer.getTotal()) {
            player.setMoney(playerBet * 2);
            return true;
        } else {
            player.setMoney(playerBet * -1);
            return false;
        }
    }
}
