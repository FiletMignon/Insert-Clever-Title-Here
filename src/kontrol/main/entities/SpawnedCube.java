package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class SpawnedCube extends Cube {
	public SpawnedCube(String texture, BoundingBox boundingBox, Position pos, Environment enviro) {
		super(texture, boundingBox, pos, enviro);
	}

	public void act(Environment enviro){
//		int entityAmount = enviro.getEntityAmount();
//		for(int i = 0; i < entityAmount; i++){
//			Entity indexedEnt = enviro.getEntity(i);
//			if(!(this == indexedEnt)){
//				if(!isCollidedWith(indexedEnt)){
//					float dx = (this.getPosition().x() - indexedEnt.getPosition().x());
//					float dy = (this.getPosition().y() - indexedEnt.getPosition().y());
//					float dz = (this.getPosition().z() - indexedEnt.getPosition().z());
//					if(dx > dy && dx > dz){
//						this.getPosition().setX(this.getPosition().x() + (this.boundingBox.getWidth()/2 + indexedEnt.boundingBox.getWidth()/2 - dx)/2);
//					}else if(dy > dx && dy > dz){
//						this.getPosition().setY(this.getPosition().y() + (this.boundingBox.getHeight()/2 + indexedEnt.boundingBox.getHeight()/2 - dy)/2);
//					}else if(dz > dx && dz > dy){
//						this.getPosition().setZ(this.getPosition().z() + (this.boundingBox.getDepth()/2 + indexedEnt.boundingBox.getDepth()/2 - dz)/2);
//					}
//				}else{
//					System.out.println(name + " has collided with " + indexedEnt.name);
//				}
//			}else{
//			}
//		}
	}
}
