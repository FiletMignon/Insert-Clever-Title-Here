package kontrol.main;

import kontrol.main.entities.Wall;
import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Driver {
	public Driver(){
		Screen screen = new Screen(800, 600, "GravCube");
		float l = 20;
		float w = 30;
		float h = 40;
		screen.addEntity(new Wall("", new BoundingBox(w, 1, l), new Position(0, h/2, 0)));
		screen.addEntity(new Wall("", new BoundingBox(w, 1, l), new Position(0, -h/2, 0)));
		screen.addEntity(new Wall("", new BoundingBox(1, h, l), new Position(w/2, 0, 0)));
		screen.addEntity(new Wall("", new BoundingBox(1, h, l), new Position(-w/2, 0, 0)));
		screen.addEntity(new Wall("", new BoundingBox(w, h, 1), new Position(0, 0, l/2)));
		screen.addEntity(new Wall("", new BoundingBox(w, h, 1), new Position(0, 0, -l/2)));
	}
	
	public static void main(String[] args){
		new Driver();
	}
}
