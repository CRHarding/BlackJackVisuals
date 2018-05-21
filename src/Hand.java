import java.util.ArrayList;

class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private int size = 0;

    ArrayList<Card> addCard(Card newCard) {
        hand.add(newCard);
        size = size + 1;
        return hand;
    }

    void reset() {
        hand = new ArrayList<>();
        size = 0;
    }

    int getTotal() {
        int handScore = 0;
        if (size == 2 && (hand.get(0).getValue() == 1 && hand.get(1).getValue() > 10) || (hand.get(0).getValue() > 10) && hand.get(1).getValue() == 1) {
            handScore = 50;
        } else {
            for (int i = 0; i < size; i++) {
                Card card = hand.get (i);
                if (card.getValue () == 1) {
                    if (card.getValue () == 1 && handScore + 11 < 21) {
                        handScore = handScore + 11;
                    } else {
                        if (card.getValue () > 10) handScore = handScore + 10;
                        else {
                            handScore = handScore + card.getValue ();
                        }
                    }
                }
//            if (handScore > 21) {
//                if (hand.contains());
//            }
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
