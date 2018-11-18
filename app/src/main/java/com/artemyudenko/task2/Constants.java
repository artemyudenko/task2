package com.artemyudenko.task2;

public enum Constants {
    LOCATION("LOCATION"),
    NAME("NAME");

    Constants(String s) {
        this.key = s;
    }

    private String key;

    public String getKey() {
        return key;
    }
}
