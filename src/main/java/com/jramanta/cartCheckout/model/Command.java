package com.jramanta.cartCheckout.model;

public enum Command {
    CHECKOUT("[A-Z]( [A-Z])*"),
    UPDATE("([A-Z]) (\\d+(\\.\\d+)?)( (\\d+) (\\d+(\\.\\d+)?))?"),
    HELP("HELP"),
    QUIT("QUIT");

    private final String description;

    Command(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
