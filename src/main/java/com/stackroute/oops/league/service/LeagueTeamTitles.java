package com.stackroute.oops.league.service;

/**
 * Enum to store the four team titles
 * Contains one field description and a parameterized constructor to initialize it
 * Modify this code by adding description to each enum constants
 */
public enum LeagueTeamTitles {
    HIPHOP("Hiphop"), WIN2WIN("Win2Win"),
    HAPPYFEET("Happy FEET"), LUCKYSTRIKE("Lucky Strike");

    String value;

    LeagueTeamTitles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString(){
        return this.getValue();
    }
}
