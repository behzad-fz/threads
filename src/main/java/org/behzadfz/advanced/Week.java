package org.behzadfz.advanced;

public enum Week {
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private String value;
    Week(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
