package cinema;

import java.util.Arrays;

public class CinemaRoom {
    private final char[][] room;
    private final int seatsPerRow;
    private final int rowsInRoom;
    private int ticketsSoldCount;
    private final char SEAT_MARK = 'B';
    private final String SEAT_TAKEN_ERROR = "That ticket has already been purchased!";

    public CinemaRoom(int seatsPerRow, int rowsInRoom) {
        this.seatsPerRow = seatsPerRow;
        this.rowsInRoom = rowsInRoom;
        this.room = generateEmtpyRoom();
        this.ticketsSoldCount = 0;
    }

    private char[][] generateEmtpyRoom() {
        char[][] room = new char[this.rowsInRoom][this.seatsPerRow];

        for (char[] row : room) {
            Arrays.fill(row, 'S');
        }

        return room;
    }

    public String[] getRoomAsStringArray() {
        String[] roomArray = new String[this.rowsInRoom];

        for (int i = 0; i < this.room.length; i++) {
            String stringRow = new String(room[i]);

            stringRow = stringRow.replaceAll("\\B", " ");

            String stringRowWithNumber = (i + 1) + " " + stringRow;

            roomArray[i] = stringRowWithNumber;
        }

        return roomArray;
    }

    public String getColumnNumbers() {
        StringBuilder columnNumbers = new StringBuilder("  ");

        for (int i = 1; i <= this.seatsPerRow; i++) {
            columnNumbers.append(i).append(" ");
        }

        return columnNumbers.toString();
    }

    public int getTotalSeats() {
        return seatsPerRow * rowsInRoom;
    }

    public int getRowsInRoom() {
        return rowsInRoom;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void markSeatLocation(SeatLocation seat) {
        int rowIndex = seat.getRowIndex();
        int seatIndex = seat.getSeatIndex();

        if (!((rowIndex >= 0 && rowIndex < rowsInRoom) && (seatIndex >= 0 && seatIndex < seatsPerRow))) {
            throw new IndexOutOfBoundsException("Wrong input!");
        }

        char seatStatus = room[rowIndex][seatIndex];

        if (seatStatus == 'S') {
            room[rowIndex][seatIndex] = SEAT_MARK;
        } else {
            throw new SeatAlreadyTakenException(SEAT_TAKEN_ERROR);
        }

        ticketsSoldCount += 1;
    }

    public int getTicketsSoldCount() {
        return ticketsSoldCount;
    }
}
