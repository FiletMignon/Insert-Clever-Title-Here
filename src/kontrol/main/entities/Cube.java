package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.physics.BoundingBox;
import kontrol.main.physics.Velocity;
import kontrol.main.util.Position;


public class Cube extends Entity{
	public Cube(String texture, BoundingBox boundingBox, Position pos) {
		super(texture, boundingBox, pos);
		vel = new Velocity();
	}
 
	public void act(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!(this == indexedEnt)){
				if(!isCollidedWith(indexedEnt)){
					applyVelocity();
				System.out.println(name + " has collided with " + indexedEnt.name);
			}
			}else{
			}
		}
	}
}
