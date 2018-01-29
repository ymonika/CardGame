package card.game.constant;

public enum Suit {
    CLUB(4), DIAMOND(3), SPADE(2), HEART(1);
    private Integer value;

    Suit(int Suitvalue) {
        this.value = Suitvalue;
    }

    public Integer getValue(){
        return this.value;
    }
}