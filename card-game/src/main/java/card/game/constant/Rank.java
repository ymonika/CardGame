package card.game.constant;

public enum Rank {
    TWO(13), THREE(12), FOUR(11), FIVE(10), SIX(9), SEVEN(8), EIGHT(7), NINE(6), TEN(5), JACK(4), QUEEN(3), KING(2), ACE(1);
    private Integer value;

    Rank(Integer rankValue){
        this.value = rankValue;
    }
    public Integer getValue(){
        return this.value;
    }
}
