package cinema;

public enum MenuOption {
    SHOW_SEATS(1, "Show the seats"),
    BUY_TICKET(2, "Buy a ticket"),
    STATISTICS(3, "Statistics"),
    EXIT(0, "Exit");

    private final int optionNumber;
    private final String description;

    MenuOption(int optionNumber, String description) {
        this.optionNumber = optionNumber;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getOptionNumber() {
        return optionNumber;
    }
    
    public static MenuOption getMenuOptionByOptionNumber(int optionNumber) {
        for (MenuOption option : values()) {
            if (option.optionNumber == optionNumber) {
                return option;
            }
        }
        return null;
    }
}
