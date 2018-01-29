package card.game.entity

import card.game.constant.Rank
import card.game.constant.Suit
import spock.lang.Specification

class PlayerSpec extends Specification {

    Player player
    TreeSet<Card> cards = new TreeSet<>();

    def setup(){
        player = new Player(
                id: 'John',
                cards:cards
        )
    }

    def "getHighestCard - should return highest card of a Player"(){
        given:
        Card card1 = new Card(Rank.TWO, Suit.DIAMOND); Card card2 = new Card(Rank.TWO, Suit.CLUB); Card card3 = new Card(Rank.TWO, Suit.HEART); Card card4 = new Card(Rank.TWO, Suit.SPADE);
        cards.add(card1); cards.add(card2); cards.add(card3); cards.add(card4);

        when:
        Card card = player.getHighestCard();

        then:
        assert card.suit == Suit.HEART
    }

    def "getHighestCard - should return highest card of a Player if all are of same Suit"(){
        given:
        Card card1 = new Card(Rank.QUEEN, Suit.HEART); Card card2 = new Card(Rank.TWO, Suit.HEART); Card card3 = new Card(Rank.ACE, Suit.HEART); Card card4 = new Card(Rank.JACK, Suit.HEART);
        cards.add(card1); cards.add(card2); cards.add(card3); cards.add(card4);

        when:
        Card card = player.getHighestCard();

        then:
        assert card.rank == Rank.ACE
    }

    def "getRankCount - should return count if player is having >= 3 cards of same RANK "(){
        given:
        Card card1 = new Card(Rank.QUEEN, Suit.HEART); Card card2 = new Card(Rank.QUEEN, Suit.SPADE); Card card3 = new Card(Rank.QUEEN, Suit.CLUB); Card card4 = new Card(Rank.JACK, Suit.HEART);
        cards.add(card1); cards.add(card2); cards.add(card3); cards.add(card4);

        when:
        int count =  player.getRankCount();

        then:
        assert count == 3
    }

    def "isSameRankCards - should return true if all cards are of same rank"(){
        given:
        Card card1 = new Card(Rank.QUEEN, Suit.HEART); Card card2 = new Card(Rank.QUEEN, Suit.SPADE); Card card3 = new Card(Rank.QUEEN, Suit.CLUB); Card card4 = new Card(Rank.QUEEN, Suit.DIAMOND);
        cards.add(card1); cards.add(card2); cards.add(card3); cards.add(card4);

        when:
        Boolean result = player.isSameRankCards();

        then:
        assert result
    }

}
