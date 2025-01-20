package User;

import java.util.Arrays;

public enum UserChoice {
    LOGIN("1"),
    REGISTER("2"),
    EXIT("3"),
    INVALID("");

    private final String value;

    UserChoice(String value) {
        this.value = value;
    }

    public static UserChoice fromValue(String value) {
        return Arrays.stream(values())
                .filter(choice -> choice.value.equals(value))
                .findFirst()
                .orElse(INVALID);
    }
}
