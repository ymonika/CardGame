package card.game.rules

import card.game.constant.Rank
import card.game.constant.Suit
import card.game.entity.Card
import card.game.entity.Player
import spock.lang.Specification

import java.util.stream.Collectors

class ThreeOfAKindSpec extends Specification {

    ThreeOfAKind threeOfAKind
    
    def setup(){
        threeOfAKind = new ThreeOfAKind()
    }
    
    def "isSameRank - should return player of highest Rank & Suit"(){
        given:
        Card card1 = new Card(Rank.JACK, Suit.CLUB);
        Card card2 = new Card(Rank.JACK, Suit.HEART);
        Card card3 = new Card(Rank.JACK, Suit.DIAMOND);

        Card card4 = new Card(Rank.ACE, Suit.SPADE);
        Card card5 = new Card(Rank.KING, Suit.HEART);
        Card card6 = new Card(Rank.THREE, Suit.DIAMOND);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);
        cards2.add(card4); cards2.add(card5); cards2.add(card6);

        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)

        when:
        Player player = threeOfAKind.applyRule(p1, p2)

        then:
        assert player == p1
    }

    def "isSameRank - with same JACK & ACE"(){
        given:
        Card card1 = new Card(Rank.JACK, Suit.CLUB);
        Card card2 = new Card(Rank.JACK, Suit.HEART);
        Card card3 = new Card(Rank.JACK, Suit.DIAMOND);

        Card card4 = new Card(Rank.ACE, Suit.SPADE);
        Card card5 = new Card(Rank.ACE, Suit.HEART);
        Card card6 = new Card(Rank.ACE, Suit.DIAMOND);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);
        cards2.add(card4); cards2.add(card5); cards2.add(card6);

        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)

        when:
        Player player = threeOfAKind.applyRule(p1, p2)

        then:
        assert player == p2
    }

    def "sameRankRule - with 5 cards, removing 3 cards as they are of same rank"(){
        given:
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);
        Card card3 = new Card(Rank.ACE, Suit.HEART);
        Card card4 = new Card(Rank.KING, Suit.SPADE);
        Card card5 = new Card(Rank.KING, Suit.CLUB);

        Card card6 = new Card(Rank.ACE, Suit.SPADE);
        Card card7 = new Card(Rank.ACE, Suit.CLUB);
        Card card8 = new Card(Rank.ACE, Suit.DIAMOND);
        Card card9 = new Card(Rank.QUEEN, Suit.CLUB);
        Card card10 = new Card(Rank.FOUR, Suit.SPADE);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);cards1.add(card4); cards1.add(card5);
        cards2.add(card6);cards2.add(card7);cards2.add(card8);cards2.add(card9); cards2.add(card10);

        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)
        /*
        John has won by high card: KING - SPADE
         */

        when:
        Player player = threeOfAKind.applyRule(p1, p2)

        then:
        println player
        assert player.id == p1.id
    }

    def "sameRankRule - with 5 cards, disable 3 cards as they are of same rank, compare remaining 2"(){
        given:
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);
        Card card3 = new Card(Rank.ACE, Suit.HEART);
        Card card4 = new Card(Rank.KING, Suit.SPADE);
        Card card5 = new Card(Rank.TWO, Suit.CLUB);

        Card card6 = new Card(Rank.ACE, Suit.SPADE);
        Card card7 = new Card(Rank.ACE, Suit.CLUB);
        Card card8 = new Card(Rank.ACE, Suit.DIAMOND);
        Card card10 = new Card(Rank.KING, Suit.SPADE);
        Card card9 = new Card(Rank.TEN, Suit.CLUB);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);cards1.add(card4); cards1.add(card5);
        cards2.add(card6);cards2.add(card7);cards2.add(card8);cards2.add(card9); cards2.add(card10);

        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)
        /*
        Bob has won by high card: TEN CLUB
         */

        when:
        Player player = threeOfAKind.applyRule(p1, p2)

        then:
        println player
        assert player.id == p2.id
    }

    def "disableSameRankCards - disable same rank cards"(){
        given: "getRankCount() wil set sameRank variable"
        Card card1 = new Card(Rank.ACE, Suit.SPADE);
        Card card2 = new Card(Rank.ACE, Suit.CLUB);
        Card card3 = new Card(Rank.ACE, Suit.HEART);
        Card card4 = new Card(Rank.KING, Suit.SPADE);
        Card card5 = new Card(Rank.TWO, Suit.CLUB);
        TreeSet<Card> cards1 = new TreeSet<>();
        cards1.add(card1); cards1.add(card2); cards1.add(card3);cards1.add(card4); cards1.add(card5);
        Player p1 = new Player("John", cards1)
        p1.getRankCount()

        when: "disableSameRankCards method is called"
        threeOfAKind.disableSameRankCards(p1)

        then: "should return no. of disabled cards, here it's 3"
        assert p1.getCards().stream().filter({card -> !card.active}).collect(Collectors.counting()) == 3
        assert 1==1
    }
}
