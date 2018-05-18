public class User {
    private String name;
    private Hand hand;
    private int money;

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

    void addCard(Deck d) { hand.addCard(d.deal()); }

    void setupBlackjack(Deck d) {
        hand.addCard(d.deal());
        hand.addCard(d.deal());
    }

    void resetHand() {
        this.hand = new Hand();
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
