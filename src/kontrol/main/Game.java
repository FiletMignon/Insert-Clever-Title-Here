package kontrol.main;

import kontrol.main.entities.Player;
import kontrol.main.entities.Wall;
import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Game {
	public Game(){
		Screen screen = new Screen(800, 600, "GravCube");
		float l = 500;
		float w = 500;
		float h = 500;
		screen.addEntity(new Wall("walls.png", new BoundingBox(w, 1, l), new Position(0, h/2, 0), screen.getEnvironment()));
		screen.addEntity(new Wall("walls.png", new BoundingBox(w, 1, l), new Position(0, -h/2, 0), screen.getEnvironment()));
		screen.addEntity(new Wall("walls.png", new BoundingBox(1, h, l), new Position(w/2, 0, 0), screen.getEnvironment()));
		screen.addEntity(new Wall("walls.png", new BoundingBox(1, h, l), new Position(-w/2, 0, 0), screen.getEnvironment()));
		screen.addEntity(new Wall("walls.png", new BoundingBox(w, h, 1), new Position(0, 0, l/2), screen.getEnvironment()));
		screen.addEntity(new Wall("walls.png", new BoundingBox(w, h, 1), new Position(0, 0, -l/2), screen.getEnvironment()));
		screen.addEntity(new Wall("box.png", new BoundingBox(2,2,2), new Position(0,0,0), screen.getEnvironment()));
		screen.addPlayer(new Player("", new Position(0, 0, 0), "Player", screen.getEnvironment()));
		screen.run();
	}
	
	public static void main(String[] args){
		new Game();
	}
}
