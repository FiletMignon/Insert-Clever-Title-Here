package kontrol.main.entities;

import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Bullet extends Entity{
	public Bullet(Position pos){
		super("Bullet.png", new BoundingBox(0.1f, 0.1f, 0.1f), pos);
	}
}
