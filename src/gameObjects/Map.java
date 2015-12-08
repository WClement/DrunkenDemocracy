package gameObjects;

import java.util.ArrayList;

public class Map {

	ArrayList<Track> tracks;
	ArrayList<LocationNode> nodes;
	ArrayList<Kingdom> kingdoms;
	
	public Map() {
		tracks = new ArrayList<Track>();
		nodes = new ArrayList<LocationNode>();
		kingdoms = new ArrayList<Kingdom>();
	}
	
	public void addLocationNode(LocationNode locNode) {
		nodes.add(locNode);
		locNode.setId(nodes.indexOf(locNode));
	}
	
	// creates new node and adds it to the map
	public LocationNode newLocationNode() {
		LocationNode newNode = new LocationNode();
		nodes.add(newNode);
		newNode.setId(nodes.indexOf(newNode));
		return newNode;
	}
	
	// create a new Track between two nodes
	public void newTrack(LocationNode start, LocationNode end) {
		Track newTrack = new Track();
		newTrack.setStartNode(start);
		newTrack.setEndNode(end);
		tracks.add(newTrack);
	}
	
	// adds an existing track between two existing location nodes
	public void addTrack(Track trackToAdd, LocationNode start, LocationNode end) {
		if (nodes.contains(start) && nodes.contains(end)) {
			trackToAdd.setStartNode(start);
			trackToAdd.setEndNode(end);
		}
	}
	
	public Track getTrack(int index) {
		return tracks.get(index);
	}
	
	public LocationNode getLocationNode(int index) {
		if (index > nodes.size()-1) {
			return null;
		}
		return nodes.get(index);
	}
	
	public void removeLocationNode(int index) {
		nodes.remove(index);
	}
	
	public ArrayList<LocationNode> getLocationNodes() {
		return nodes;
	}
	
	public ArrayList<Track> getTracks() {
		return tracks;
	}
	
}
