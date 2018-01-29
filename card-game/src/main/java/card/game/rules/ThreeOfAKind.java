package card.game.rules;

import card.game.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class ThreeOfAKind implements CardRules {

    public Player applyRule(Player p1, Player p2){
        Player winnerPlayer = null;
        if(p1.getRankCount() != p1.getCards().size() && p2.getRankCount() != p2.getCards().size()  ){
            disableSameRankCards(p1); disableSameRankCards(p2);
            return new HighCardRule().applyRule(p1,p2);
        }
        if(p1.isSameRankCards() && p2.isSameRankCards()){
            int compareValue =  p1.getHighestCard().compareTo(p2.getHighestCard());
            switch (compareValue){
                case 0:
                    break;
                case -1:
                    winnerPlayer = p2; break;
                default:
                    winnerPlayer = p1; break;
            }
        } else if (p1.isSameRankCards()) {
            winnerPlayer = p1;
        } if (p2.isSameRankCards()) {
            winnerPlayer = p2;
        }
        return  winnerPlayer;
    }

    private void disableSameRankCards(Player p1){
        p1.getCards().stream().forEach(card -> {
            if (card.getRank() == p1.getSameCardRank()){
                card.setActive(false);
            }
        });
    }

}
