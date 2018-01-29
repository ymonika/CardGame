package card.game.entity;

import card.game.constant.Rank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class Player {

    private TreeSet<Card> cards;
    private String id;
    private Rank sameCardRank;

    public Player() {
    }

    public Player(String id, TreeSet<Card> cards){
        this.id = id;
        this.cards = cards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TreeSet<Card> getCards() {
        return cards;
    }

    public void setCards(TreeSet<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public Card getHighestCard(){
        Iterator<Card> cardIterator = cards.iterator();
        while(cardIterator.hasNext()){
            Card card = cardIterator.next();
            if(card.getActive()){
                return card;
            }
        }
        return null;
    }

    public Boolean isSameRankCards(){
        Boolean isSameRankCards = false;
        Rank rank = getHighestCard().getRank();
        isSameRankCards = cards.stream().allMatch(card -> card.getRank().equals(rank));
        return isSameRankCards;
    }

    public Integer getRankCount(){
        int countSameRank = 0;
        HashMap<Rank, Integer> ranks = new HashMap<Rank, Integer>(3);
        Iterator<Card> cards = this.getCards().iterator();
        while(cards.hasNext()){
            Rank rank = cards.next().getRank();
            if(ranks.containsKey(rank)) {
                ranks.put(rank, ranks.get(rank)+1);
            } else {
                ranks.put(rank,1);
            }
        }
        Iterator<Rank> iteratorOfRanks = ranks.keySet().iterator();
        while(iteratorOfRanks.hasNext()) {
            Rank rank = iteratorOfRanks.next();
            if(ranks.get(rank) >= 3) {
                countSameRank = ranks.get(rank);
                sameCardRank = rank;
            }
        }
        return countSameRank;
    }

    public Rank getSameCardRank() {
        return sameCardRank;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", cards=" + cards +
                '}';
    }

}

