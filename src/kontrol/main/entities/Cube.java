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
	public void setVelocity(float x, float y, float z){
		vel = new Velocity(x, y, z);
	}
	public void act(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!isCollidedWith(indexedEnt) && (vel.getXVelocity()!=0 && vel.getYVelocity()!=0 && vel.getZVelocity()!=0)){
				applyVelocity();
			}
			else{
				System.out.println("Collide");
			}
		}
	}
	
}
