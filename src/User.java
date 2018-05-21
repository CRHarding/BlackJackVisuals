import java.util.ArrayList;

public class User {
    private String name;
    private Hand hand;
    private int money;
    private ArrayList<Card> returnHand;

    User(String name, int money) {
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

    ArrayList<Card> getHand() {
        return returnHand;
    }

    void resetHand() {
        hand.reset();
        returnHand = new ArrayList<>();
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < returnHand.size(); i++) {
            if (i != returnHand.size() - 1) {
                if (returnHand.get(i + 1).toString().contains("Ace")) {
                    returnString = returnString + returnHand.get(i) + " and an ";
                } else {
                    returnString = returnString + returnHand.get(i) + " and a ";
                }
            } else {
                returnString = returnString + returnHand.get(i);
            }
        }
        return returnString;
    }

    int getTotal() {
        return hand.getTotal();
    }
}
