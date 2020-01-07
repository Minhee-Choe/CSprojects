package RogueLike;

import FileManager.FileManager;
import WorldMap.WorldMap;
import Config.Config;

public class RogueLike {
	public static void main(String[] args) {
		WorldMap gameMap = new WorldMap(); 
		FileManager file = new FileManager(); 
		file.loadMap(Config.MAP_FILENAME); 
		char[][] map = file.buildMap();
		gameMap.initialize(map); 
		gameMap.run();
	}
}
