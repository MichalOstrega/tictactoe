package tictactoe;

public enum NumbersOfField {
    ONE(0, 0), TWO(0, 1), THREE(0, 2), FOUR(1, 0), FIVE(1, 1), SIX(1, 2), SEVEN(2, 0), EIGHT(2, 1), NINE(2, 2), ZERO(3, 3);
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    NumbersOfField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }
}


