package card.game.rules;

import card.game.entity.Card;
import card.game.entity.Player;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.TreeSet;

import static java.util.stream.Collectors.toCollection;

@Component
public class HighCardRule implements CardRules {

    public Player applyRule(Player p1, Player p2){
        Player winnerPlayer = null;
        if(isSameCards(p1,p2)){
            return winnerPlayer;
        }
        int compareValue = p1.getHighestCard().compareTo(p2.getHighestCard());
        if (compareValue == 0){
            Iterator<Card> p1Cards = p1.getCards().stream().filter(card -> card.getActive()).collect(toCollection(TreeSet::new)).iterator();
            Iterator<Card> p2Cards = p2.getCards().stream().filter(card -> card.getActive()).collect(toCollection(TreeSet::new)).iterator();
            while(p1Cards.hasNext()){
                compareValue = p1Cards.next().compareTo(p2Cards.next());
                if(compareValue != 0){
                    break;
                }
            }
        }
        if (compareValue > 0) {
            winnerPlayer = p2;
        } else if (compareValue < 0) {
            winnerPlayer = p1;
        }
        return winnerPlayer;
    }

    private Boolean isSameCards(Player p1, Player p2){
        return p1.getCards().equals(p2.getCards());
    }
}
