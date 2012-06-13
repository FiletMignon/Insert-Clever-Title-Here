package kontrol.main.entities;

import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Wall extends Entity {

	public Wall(String texture, BoundingBox boundingBox, Position pos) {
		super(texture, boundingBox, pos);
	}

}
