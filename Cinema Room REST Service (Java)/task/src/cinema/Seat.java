package cinema;

public record Seat(int row, int column) {
    public int getPrice() {
        return row <= 4 ? 10 : 8;
    }
}
