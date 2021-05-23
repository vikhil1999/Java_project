package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is implementing the PlayerDao interface
 * This class has one field playerList and a String constant for storing file name
 */
public class PlayerDaoImpl implements PlayerDao {
    private static final String PLAYER_FILE_NAME = "src/main/resources/player.csv";
    private List<Player> playerList;

    /**
     * Constructor to initialize an empty ArrayList for playerList
     */
    public PlayerDaoImpl() {

        playerList=new ArrayList<Player>();
    }

    /**
     * Return true if  player object is stored in "player.csv" as comma separated fields successfully
     * when password length is greater than six and yearExpr is greater than zero
     */
    @Override
    public boolean addPlayer(Player player) throws  PlayerAlreadyExistsException{
        try {
            if (findPlayer(player.getPlayerId()) != null) {
                throw new PlayerAlreadyExistsException("Player already exists");
            }
        } catch(PlayerNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (FileWriter write = new FileWriter(PLAYER_FILE_NAME, true);) {
            if(player.getPassword().length()>6 && player.getYearExpr()>0) {
                write.write(player.getPlayerId()+","+player.getPlayerName()+","+player.getPassword()+","+player.getYearExpr()+","+player.getTeamTitle());
                write.append("\n");
                write.flush();
                write.close();
                return true;
            }
        } catch (IOException e) {
            System.out.println("Exception: "+e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //Return the list of player objects by reading data from the file "player.csv"
    @Override
    public List<Player> getAllPlayers() {
        String line;
        String[] arr;
        try (BufferedReader reader = new BufferedReader(new FileReader(PLAYER_FILE_NAME))) {
            while ((line = reader.readLine()) != null) {
                arr = line.split(",");
                playerList.add(new Player(arr[0],arr[1],arr[2],Integer.parseInt(arr[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    /**
     * Return Player object given playerId to search
     */
    @Override
    public Player findPlayer(String playerId) throws PlayerNotFoundException {
        List<Player> players = getAllPlayers();
        int flag=0;
        for (Player p: players) {
            if (p.getPlayerId().equals(playerId)){
                flag=1;
                return p;
            }
        }
        if (flag!=1){
            throw new PlayerNotFoundException("player not found");
        }
        return null;
    }
}
