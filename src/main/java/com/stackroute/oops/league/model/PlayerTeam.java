package com.stackroute.oops.league.model;

import java.io.Serializable;

/**
 * This class contains four fields playerId,teamTitle,season and experience.
 * This is a subclass of Serializable and Comparable interface
 */
public class PlayerTeam implements Serializable, Comparable<PlayerTeam> {

    private String playerId ;
    private String teamTitle;


    //Parameterized Constructor to initialize all properties


    public PlayerTeam(String playerId, String teamTitle) {
        this.playerId = playerId;
        this.teamTitle = teamTitle;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    /**
     * This overridden method should return player details in below format
     * Player{playerId=xxxxx, teamTitle=xxxxxx}
     */
    @Override
    public String toString() {
        return "PlayerTeam{" +
                "playerId='" + playerId +
                ", teamTitle='" + teamTitle +
                '}';
    }

    //Overridden compare method based on playerId
    @Override
    public int compareTo(PlayerTeam playerTeam) {
        return (this.playerId.compareTo(playerTeam.playerId));
    }
}
