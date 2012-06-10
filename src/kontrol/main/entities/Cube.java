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
	private Position forcePos = pos.inverse();
	public void act(Environment enviro){
		setForce(forcePos);
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!isCollidedWith(indexedEnt)){
				applyVelocity();
			}
			else{
				forcePos = new Position(0,0,0);
				System.out.println("Collide");
			}
		}
	}
	
}
