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

	private boolean move = true;
	public void act(Environment enviro){
		if(move){
			setForce(pos.inverse());
			applyVelocity();
			System.out.println("move");
		}
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!(this.name == indexedEnt.name)){
				float dx = this.getPosition().x() - indexedEnt.getPosition().x();
				float dy = this.getPosition().y() - indexedEnt.getPosition().y();
				float dz = this.getPosition().z() - indexedEnt.getPosition().z();

				if(dx * dx + dy * dy + dz * dz <= this.boundingBox.getWidth() * this.boundingBox.getHeight() * this.boundingBox.getDepth()){
					System.out.println("collision");
					move = false;
				}
			}
		}
	}
	
}
