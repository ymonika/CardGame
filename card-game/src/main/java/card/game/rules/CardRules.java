package card.game.rules;

import card.game.entity.Player;

public interface CardRules {

    Player applyRule(Player p1, Player p2);
}
