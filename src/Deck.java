import java.util.ArrayList;
import java.util.Random;

class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    private int numDealt;

    Deck(int num) {
        for (int i = 1; i <= 52; i++) {
            cards.add(new Card (i));
        }
        shuffle();
        numDealt = 0;
    }

    private void shuffle() {
        int n = this.cards.size();
        Random random = new Random();
        for (int i = 0; i < this.cards.size(); i++) {
            int randomValue = i + random.nextInt(n - i);
            Card randomCard = this.cards.get(randomValue);
            this.cards.set(randomValue, this.cards.get(i));
            this.cards.set(i, randomCard);
        }
    }

    Card deal() {
        Card dealCard = this.cards.remove(numDealt);
        numDealt = numDealt + 1;
        return dealCard;
    }

    int getSize() {
        return cards.size();
    }
}
