package card.game;

import card.game.entity.Player;

import java.util.List;

public class PlayersDto {

    private List<Player> players;

    public PlayersDto() {
    }

    public PlayersDto(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "PlayersDto{" +
                "players=" + players +
                '}';
    }
}
