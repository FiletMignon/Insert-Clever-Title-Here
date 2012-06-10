package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.physics.BoundingBox;
import kontrol.main.physics.Velocity;
import kontrol.main.util.Position;


public class Cube extends Entity{
	public Cube(String texture, Position pos) {
		super(texture, new BoundingBox(1,1,1), pos);
		vel = new Velocity();
	}

	public void act(Environment enviro){
		force.setForce(0f,0f,-16f);
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!isCollidedWith(indexedEnt)){
				applyVelocity();
			}
			else{
				this.vel.setVelocity(0, 0, 0);
			//	System.out.println("Collide");
			}
		}
	}
	
}
