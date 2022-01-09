package cinema;

public class SalesCalculator {
    private final CinemaRoom room;
    private int currentIncome;
    private final int FLAT_PRICE_SEATS_LIMIT = 60;
    private final int SEAT_FLAT_PRICE = 10;
    private final int SEAT_DISCOUNT_PRICE = 8;

    public SalesCalculator(CinemaRoom room) {
        this.room = room;
        this.currentIncome = 0;
    }

    public int calculateMaxProfit() {
        int totalSeats = room.getTotalSeats();

        if (totalSeats <= FLAT_PRICE_SEATS_LIMIT) {
            return totalSeats * SEAT_FLAT_PRICE;
        } else {
            return calculateSplitRoomProfit();
        }
    }

    public double calculatePercentageTicketsSold() {
        int seatsSold = room.getTicketsSoldCount();
        int totalSeats = room.getTotalSeats();

        return (seatsSold / (double) totalSeats) * 100.0;
    }

    private int calculateSplitRoomProfit() {
        int rowsCount = room.getRowsInRoom();
        int seatsPerRow = room.getSeatsPerRow();
        double halfRowsCount = rowsCount / 2.0;

        if (rowsCount % 2 == 0) {
            return (int) ( seatsPerRow * (halfRowsCount * SEAT_FLAT_PRICE + halfRowsCount * SEAT_DISCOUNT_PRICE));
        } else {
            return (int) (seatsPerRow * (Math.floor(halfRowsCount) * SEAT_FLAT_PRICE + Math.ceil(halfRowsCount) * SEAT_DISCOUNT_PRICE));
        }
    }

    public int calculateSeatPrice(SeatLocation seat) {
        int totalSeats = room.getTotalSeats();

        if (totalSeats <= FLAT_PRICE_SEATS_LIMIT) {
            currentIncome += SEAT_FLAT_PRICE;
            return SEAT_FLAT_PRICE;
        } else {
            int price = calculateSplitRoomSeatPrice(seat);
            currentIncome += price;
            return price;
        }
    }

    private int calculateSplitRoomSeatPrice(SeatLocation seat) {
        int rowsCount = room.getRowsInRoom();
        double halfRowsCount = rowsCount / 2.0;
        int lastFlatPriceRowNumber = (int) Math.floor(halfRowsCount);

        return seat.getRowNumber() <= lastFlatPriceRowNumber ? SEAT_FLAT_PRICE : SEAT_DISCOUNT_PRICE;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }
}

