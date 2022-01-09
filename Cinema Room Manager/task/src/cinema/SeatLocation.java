package cinema;

public class SeatLocation {
    private int rowNumber;
    private int seatNumber;

    public SeatLocation(int rowNumber, int seatNumber) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getRowIndex() {
        return rowNumber - 1;
    }

    public int getSeatIndex() {
        return seatNumber - 1;
    }
}
