package card.game.service;

import card.game.PlayersDto;
import card.game.entity.Player;
import card.game.rules.CardRules;
import card.game.rules.HighCardRule;
import card.game.rules.ThreeOfAKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;

@Service
public class CardGameService {

    /*@Autowired
    private HighCardRule highCardRule;
    @Autowired
    private ThreeOfAKind threeOfAKind;
    */
    LinkedList<CardRules> rules = new LinkedList<>(Arrays.asList(new ThreeOfAKind(), new HighCardRule()));
    public static String MATCH_DRAWN_MESSAGE = "Match was drawn";
    public static String WINNER_PLAYER = " has won";

    public String gameService(PlayersDto players){
        if(players.getPlayers().size() < 1){

        }
        Player winnerPlayer = null;
        String winnerPlayerId = MATCH_DRAWN_MESSAGE;
        for(CardRules rule: rules) {
           winnerPlayer = rule.applyRule(players.getPlayers().get(0),players.getPlayers().get(1));
            if(winnerPlayer != null){
                break;
            }
        }
        if (winnerPlayer != null){
            winnerPlayerId = winnerPlayer.getId() + WINNER_PLAYER;
        }
        return winnerPlayerId;
    }
}