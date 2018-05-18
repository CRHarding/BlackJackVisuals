import java.util.ArrayList;

class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private int size = 0;

    void addCard(Card newCard) {
        this.hand.add(newCard);
        size = size + 1;
    }

    int getTotal() {
        int handScore = 0;
        if (this.size == 2 && (this.hand.get(0).getValue() == 1 && this.hand.get(1).getValue() > 10) || (this.hand.get(0).getValue() > 10) && this.hand.get(1).getValue() == 1) {
            handScore = 50;
        } else {
            for (int i = 0; i < this.size; i++) {
                Card card = this.hand.get(i);
                if (card.getValue () == 1 && handScore + 11 < 21) {
                    handScore = handScore + 11;
                } else {
                    if (card.getValue() > 10)
                        handScore = handScore + 10;
                    else {
                        handScore = handScore + card.getValue ();
                    }
                }
            }
        }
        return handScore;
    }

    int getSize() {
        return this.size;
    }

    Card getCard(int place) {
        return this.hand.get(place);
    }
}
