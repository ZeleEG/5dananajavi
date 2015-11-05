package com.petdananajavi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Schema for input JSON file for 5 dana na Javi competition.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "players"
})
public class Tournament {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("players")
    private List<Player> players = new ArrayList<Player>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The players
     */
    @JsonProperty("players")
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * 
     * (Required)
     * 
     * @param players
     *     The players
     */
    @JsonProperty("players")
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void printPlayers() {
    	for (Player p : players) {
    		StringBuilder s = new StringBuilder();
    		s.append(p.getFirstname()).append(" ").append(p.getLastname()).append("(").append(p.getPos()).append(") ").append(" - ").append(p.calculatePlayerStats());
    		System.out.println(s.toString());
    	}
    	System.out.print("MVP: ");
    	System.out.println(getMVP().getFirstname());
    }
    public Player getMVP() {
    	int localMax = -1;
    	int temp = -1;
    	Player MVP = null;
    	for (Player p : players) {
    		temp = p.calculatePlayerStats();
    		if (localMax < temp) {
				localMax = temp;
				MVP = p;
			}
    	}
    	return MVP;
    }
    public Player getBestForPosition(String pos, List<Player> plyrs) {
    	int localMax = -1;
    	int temp = -1;
		Player DTMember = null;
    	for (Player p : plyrs) {
    		List<String> playerPositions = p.getPos();
    		for (String ps : playerPositions) {
    			if (ps.equals(pos)) {
    				temp = p.calculatePlayerStats();
    				if (localMax < temp) {
    					localMax = temp;
    					DTMember = p;
    				}
    			}
    		}
    	}
    	return DTMember;
    }
    
    public List<Player> getDreamTeam() {
    	List<Player> dreamTeam = new ArrayList<Player>();
    	List<String> teamList = new ArrayList<String>();
    	Player candidate = null;
    	String[] positions = {"PG", "SG", "SF", "PF", "C"};
    	System.out.println("Dream team: ");
    	for (String s : positions) {
    		List<Player> tmpPlayers = new ArrayList<Player>();
    		tmpPlayers.addAll(players);
    		while(tmpPlayers.size() > 0) { 
    			candidate = getBestForPosition(s, tmpPlayers);
    			if (candidate == null) break;
        		if (!candidate.inTeam(teamList)) {
        			dreamTeam.add(candidate);
        			teamList.add(candidate.getTeam());
            		break;
        		}
        		else {
        			tmpPlayers.remove(candidate);
        		}
    		}
    	}
    	return dreamTeam;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
