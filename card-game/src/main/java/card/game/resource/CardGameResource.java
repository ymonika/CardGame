package card.game.resource;


import card.game.PlayersDto;
import card.game.service.CardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path(value = "cardgame")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class CardGameResource {

    @Autowired
    private CardGameService cardGameService;

    @POST
    public ResponseEntity startCardGame(PlayersDto players) {
        return ResponseEntity.ok(cardGameService.gameService(players));
    }

}
