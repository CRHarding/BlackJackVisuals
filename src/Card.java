public class Card {
    private int rank;
    private int score;
    private String suit;

    Card(int num) {
        rank = num % 13;
        score = rank;
        if (rank == 0) {
            rank = 1;
            score = 1;
        }

        switch (num % 4) {
            case 0:
                suit = "Clubs";
                break;
            case 1:
                suit = "Diamonds";
                break;
            case 2:
                suit = "Hearts";
                break;
            case 3:
                suit = "Spades";
                break;
            default:
                System.out.println("Incorrect modulus for rank % 13 --> " + num % 4);
        }
    }

    public String toString() {
        if (this.rank != 1 && this.rank < 11) return this.rank + " of " + this.suit;
        if (this.rank == 1) return "Ace of " + this.suit;
        switch (this.rank) {
            case 11:
                return "Jack of " + this.suit;
            case 12:
                return "Queen of " + this.suit;
            case 13:
                return "King of " + this.suit;
            default:
                return "Incorrect input for this.rank in card toString method";
        }
    }

    int getValue() {
        return this.score;
    }
}
