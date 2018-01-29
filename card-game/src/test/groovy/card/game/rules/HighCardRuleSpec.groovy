package card.game.rules

import card.game.constant.Rank
import card.game.constant.Suit
import card.game.entity.Card
import card.game.entity.Player
import spock.lang.Specification

class HighCardRuleSpec extends Specification {

    HighCardRule highCardRule;
    
    def setup(){
        highCardRule = new HighCardRule()
    }
    
    def "applyHighestCardRule - should return player with highest Rank & Suit"(){
        given:
        Card card1 = new Card(Rank.TEN, Suit.CLUB);
        Card card2 = new Card(Rank.JACK, Suit.HEART);
        Card card3 = new Card(Rank.JACK, Suit.DIAMOND);

        Card card4 = new Card(Rank.NINE, Suit.CLUB);
        Card card5 = new Card(Rank.THREE, Suit.HEART);
        Card card6 = new Card(Rank.EIGHT, Suit.DIAMOND);
        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();
        cards1.add(card1); cards1.add(card2); cards1.add(card3);
        cards2.add(card4); cards2.add(card5); cards2.add(card6);

        /*
        P1 (John) :  TEN-CLUB, JACK-HEART, JACK-DIAMOND
        P2 (Bob) :  NINE-CLUB, THREE-HEART, EIGHT-DIAMOND
        John has won by high card: JACK – HEART
        */
        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)
        0 * _

        when:
        Player player = highCardRule.applyRule(p1, p2)

        then:
        println player
        assert player == p1 && player.highestCard.equals(card2)
    }


    def "applyHighestCardRule - should return Phill - player with highest Rank & Suit"(){
        given:
        Card card1 = new Card(Rank.TEN, Suit.CLUB);
        Card card2 = new Card(Rank.THREE, Suit.HEART);
        Card card3 = new Card(Rank.JACK, Suit.DIAMOND);

        Card card4 = new Card(Rank.NINE, Suit.CLUB);
        Card card5 = new Card(Rank.THREE, Suit.HEART);
        Card card6 = new Card(Rank.JACK, Suit.SPADE);

        Card card7 = new Card(Rank.THREE, Suit.CLUB);
        Card card8 = new Card(Rank.SIX, Suit.HEART);
        Card card9 = new Card(Rank.JACK, Suit.HEART);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();
        TreeSet<Card> cards3 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);
        cards2.add(card4); cards2.add(card5); cards2.add(card6);
        cards3.add(card7); cards3.add(card8); cards3.add(card9);

        /*
         P1 (John) :  TEN-CLUB, THREE-HEART, JACK-DIAMOND
         P2 (Bob) :  NINE-CLUB, THREE-HEART, JACK-SPADE
         P2 (Phill) :  THREE-CLUB, SIX-HEART, JACK-HEART
             Phill has won by high card: JACK - HEART
         */
        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)
        Player p3 = new Player("Phill", cards3)
        0 * _

        when:
        Player player = highCardRule.applyRule(p1, p2)
        Player player2 = highCardRule.applyRule(player, p3)

        then:
        println player
        println player2
        assert player2 == p3 && player2.highestCard.equals(card9)
    }

    def "applyHighestCardRule - should compare 5 cards"(){
        given:
        Card card1 = new Card(Rank.TEN, Suit.CLUB);
        Card card2 = new Card(Rank.JACK, Suit.HEART);
        Card card3 = new Card(Rank.JACK, Suit.DIAMOND);
        Card card4 = new Card(Rank.KING, Suit.HEART);
        Card card5 = new Card(Rank.ACE, Suit.SPADE);

        Card card6 = new Card(Rank.NINE, Suit.CLUB);
        Card card7 = new Card(Rank.THREE, Suit.HEART);
        Card card8 = new Card(Rank.EIGHT, Suit.DIAMOND);
        Card card9 = new Card(Rank.KING, Suit.HEART);
        Card card10 = new Card(Rank.ACE, Suit.SPADE);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);
        cards1.add(card4); cards1.add(card5);
        cards2.add(card6);
        cards2.add(card7); cards2.add(card8); cards2.add(card9); cards2.add(card10);

        /*
    P1 (John) :  TEN-CLUB, JACK-HEART, JACK-DIAMOND, KING-HEART, ACE-SPADE
    P2 (Bob) :  NINE-CLUB, THREE-HEART, EIGHT-DIAMOND, KING-HEART, ACE-SPADE
    John by JACK-HEART
         */
        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)
        0 * _

        when:
        Player player = highCardRule.applyRule(p1, p2)

        then:
        println player
        assert player == p1
    }


    def "applyHighestCardRule - isSameCards - should return true if all cards have same Rank & Suit"(){
        given:
        Card card1 = new Card(Rank.TEN, Suit.CLUB);
        Card card2 = new Card(Rank.THREE, Suit.HEART);
        Card card3 = new Card(Rank.JACK, Suit.DIAMOND);

        Card card4 = new Card(Rank.TEN, Suit.CLUB);
        Card card5 = new Card(Rank.THREE, Suit.HEART);
        Card card6 = new Card(Rank.JACK, Suit.DIAMOND);

        TreeSet<Card> cards1 = new TreeSet<>();
        TreeSet<Card> cards2 = new TreeSet<>();

        cards1.add(card1); cards1.add(card2); cards1.add(card3);
        cards2.add(card4); cards2.add(card5); cards2.add(card6);

        /*
         TEN-CLUB, JACK-HEART, JACK-DIAMOND\
         Match was drawn
         */
        Player p1 = new Player("John", cards1)
        Player p2 = new Player("Bob", cards2)
        0 * _

        when:
        Boolean isSameCards = highCardRule.isSameCards(p1, p2)

        then:
        println isSameCards
        assert isSameCards
    }
}
