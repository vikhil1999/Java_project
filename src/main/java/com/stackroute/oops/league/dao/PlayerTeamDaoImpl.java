package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;
import com.stackroute.oops.league.model.PlayerTeam;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This class implements the PlayerTeamDao interface
 * This class has two fields playerTeamSet,playerDao and a String constant for storing file name.
 */
public class PlayerTeamDaoImpl implements PlayerTeamDao {
    private static final String TEAM_FILE_NAME = "src/main/resources/finalteam.csv";
    Set<PlayerTeam> playerSet;
    PlayerDao playerDao;
    /**
     * Constructor to initialize an empty TreeSet and PlayerDao object
     */
    public PlayerTeamDaoImpl() {
        playerDao = new PlayerDaoImpl();
        playerSet=new TreeSet<>();
    }

    /*
    Returns the list of players belonging to a particular teamTitle by reading
    from the file finalteam.csv
     */
    @Override
    public Set<PlayerTeam> getPlayerSetByTeamTitle(String teamTitle) {
        Set<PlayerTeam> players = getAllPlayerTeams();
        if (teamTitle==null||teamTitle==""){
            return null;
        }
        Set<PlayerTeam> playerTeams= players.stream().filter(player -> player.getTeamTitle().equals(teamTitle))
                .collect(Collectors.toSet());

        return playerTeams;
    }

    //Add he given PlayerTeam Object to finalteam.csv file
    @Override
    public boolean addPlayerToTeam(Player player) throws PlayerNotFoundException {

        if (playerDao.findPlayer(player.getPlayerId())==null){
            throw new PlayerNotFoundException("player not found");
        } else {
            try (FileWriter write = new FileWriter(TEAM_FILE_NAME, true)) {
                write.write(player.getPlayerId() + "," + player.getTeamTitle());
                write.append("\n");
                write.flush();
                write.close();
                return true;
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }


    //Return the set of all PlayerTeam by reading the file content from finalteam.csv file
    @Override
    public Set<PlayerTeam> getAllPlayerTeams() {
        String line;
        String[] arr;
        try (BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE_NAME))) {
            while ((line = reader.readLine()) != null) {
                arr = line.split(",");
                playerSet.add(new PlayerTeam(arr[0],arr[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerSet;
    }
}
