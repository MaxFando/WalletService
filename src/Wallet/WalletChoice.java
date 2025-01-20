package Wallet;

import java.util.Arrays;

public enum WalletChoice {
    ADD_INCOME("1"),
    ADD_EXPENSE("2"),
    SET_BUDGET("3"),
    VIEW_WALLET("4"),
    LOGOUT("5"),
    INVALID("");

    private final String value;

    WalletChoice(String value) {
        this.value = value;
    }

    public static WalletChoice fromValue(String value) {
        return Arrays.stream(values())
                .filter(choice -> choice.value.equals(value))
                .findFirst()
                .orElse(INVALID);
    }
}
