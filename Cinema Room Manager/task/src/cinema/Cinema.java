package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        int rowCount = showIntPrompt("Enter the number of rows: ");
        int seatsPerRow = showIntPrompt("Enter the number of seats in each row: ");

        CinemaRoom room = new CinemaRoom(seatsPerRow, rowCount);
        SalesCalculator calculator = new SalesCalculator(room);

        MenuOption menuOption = getMenuOption();

        while (menuOption != MenuOption.EXIT) {
            switch (menuOption) {
                case SHOW_SEATS:
                    showSeatLayout(room);
                    break;
                case BUY_TICKET:
                    buyTicket(room, calculator);
                    break;
                case STATISTICS:
                    showStatistics(room, calculator);
            }

            menuOption = getMenuOption();
        }
    }

    private static void buyTicket(CinemaRoom room, SalesCalculator calculator) {
        SeatLocation seatLocation;

        while (true) {
            System.out.println();
            int rowNumber = showIntPrompt("Enter a row number:");
            int seatNumber = showIntPrompt("Enter a seat number in that row:");

            seatLocation = new SeatLocation(rowNumber, seatNumber);

            try {
                room.markSeatLocation(seatLocation);
                break;
            } catch (IndexOutOfBoundsException | SeatAlreadyTakenException exception) {
                System.out.println();
                System.out.println(exception.getMessage());
            }
        }

        int seatPrice = calculator.calculateSeatPrice(seatLocation);
        showSeatPrice(seatPrice);
    }

    private static void showStatistics(CinemaRoom room, SalesCalculator calculator) {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d%n", room.getTicketsSoldCount());
        System.out.printf("Percentage: %.2f%% %n", calculator.calculatePercentageTicketsSold());
        System.out.printf("Current income: $%d%n", calculator.getCurrentIncome());
        System.out.printf("Total income: $%d%n", calculator.calculateMaxProfit());
    }

    private static void showSeatPrice(int seatPrice) {
        System.out.printf("\nTicket price: $%d%n", seatPrice);
    }

    private static void showSeatLayout(CinemaRoom room) {
        System.out.println("\nCinema:");
        System.out.println(room.getColumnNumbers());

        for (String row: room.getRoomAsStringArray()) {
            System.out.println(row);
        }

        System.out.println();
    }

    private static MenuOption getMenuOption() {
        System.out.println(" ");
        for (MenuOption menuOption : MenuOption.values()) {
            System.out.printf("%d. %s%n", menuOption.getOptionNumber(), menuOption.getDescription());
        }

        int menuOptionNumber = showIntPrompt(null);

        return  MenuOption.getMenuOptionByOptionNumber(menuOptionNumber);
    }

    private static int showIntPrompt(String question) {
        Scanner scanner = new Scanner(System.in);

        if (question != null) {
            System.out.println(question);
        }

        return scanner.nextInt();
    }
}