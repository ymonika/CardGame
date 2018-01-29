package card.game.entity;

import card.game.constant.Rank;
import card.game.constant.Suit;

public class Card implements Comparable<Card> {

    private Rank rank;
    private Suit suit;
    private Boolean isActive=true;

    public Card() {
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.isActive = true;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int compareTo(Card card) {
       int compareValue = 0;
           compareValue = this.getSuit().getValue() - card.getSuit().getValue();
           if (compareValue == 0) {
               compareValue = this.getRank().getValue() - card.getRank().getValue();
           }
        return compareValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (getRank() != card.getRank()) return false;
        return getSuit() == card.getSuit();
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                ", isActive=" + isActive +
                '}';
    }
}
