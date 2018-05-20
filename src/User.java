import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name;
    private Hand hand;
    private int money;
    private HashMap<String, String> state;
    private ArrayList<Card> returnHand;

    public User(String name, int money) {
        this.name = name;
        this.money = money;
        hand = new Hand ();
    }

    String getName() {
        return this.name;
    }

    void setName(String name) { this.name = name; }

    int getMoney() {
        return this.money;
    }

    void setMoney(int amount) {
        this.money = this.money + amount;
    }

    ArrayList<Card> addCard(Deck d) {
        returnHand = hand.addCard(d.deal());
        return returnHand;
    }

    ArrayList<Card> setupBlackjack(Deck d) {
        returnHand = new ArrayList<> ();
        Card card1 = d.deal();
        Card card2 = d.deal();
        hand.addCard(card1);
        hand.addCard(card2);
        returnHand.add(card1);
        returnHand.add(card2);
        return returnHand;
    }

    Hand getHand() {
        return hand;
    }

    void resetHand() {
        this.hand = new Hand();
        this.returnHand = new ArrayList<>();
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < this.hand.getSize(); i++) {
            if (i != this.hand.getSize() - 1) {
                if (this.hand.getCard(i + 1).toString().contains("Ace")) {
                    returnString = returnString + this.hand.getCard(i) + " and an ";
                } else {
                    returnString = returnString + this.hand.getCard(i) + " and a ";
                }
            } else {
                returnString = returnString + this.hand.getCard(i);
            }
        }
        return returnString;
    }

    int getTotal() {
        return this.hand.getTotal();
    }
}
