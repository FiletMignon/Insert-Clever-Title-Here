package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Bullet extends Entity{
	
	private Position shootTo;
	
	public Bullet(Position shootFrom, Position shootTo, Environment enviro){
		super("bullet.png", new BoundingBox(0.05f, 0.05f, 0.05f), shootFrom, enviro);
		this.shootTo = new Position(shootTo.x(), shootTo.y(), shootTo.z());
		this.mass = 5;
		this.setForce(this.shootTo, 10f);
	}
	public void act(Environment enviro){
		applyVelocity();
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!(indexedEnt instanceof Bullet) && this != indexedEnt){
				if(isCollidedWith(indexedEnt)){
					System.out.println(name + " has collided with " + indexedEnt.name);
					if(indexedEnt instanceof SpawnedCube){
						indexedEnt.removeSelfFromEnviroment(enviro);
					} else{
						removeSelfFromEnviroment(enviro);
					}
					return;
				}
			}
		}
	}
}
