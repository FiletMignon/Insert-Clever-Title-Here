package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.physics.Acceleration;
import kontrol.main.physics.BoundingBox;
import kontrol.main.physics.Force;
import kontrol.main.physics.Velocity;
import kontrol.main.render.Model;
import kontrol.main.util.Position;

import org.lwjgl.opengl.GL11;

public abstract class Entity {
	protected BoundingBox boundingBox;
	protected Position pos;
	protected Velocity vel;
	protected Acceleration acc;
	protected Force force;
	protected float mass;
	protected String name;
	protected Position gravity;
	protected Position last;
	protected Model model;
	
	protected static long totalEntities = 1;
	
	/**
	 * 
	 * @param texture File path in the source folder to locate the texture
	 * @param boundingBox The bounding box of the entity
	 * @param pos The position of the entity
	 */
	public Entity(String texturePath, BoundingBox boundingBox, Position pos, Environment enviro){
		this.boundingBox = boundingBox;
		this.pos = pos;
		vel = new Velocity();
		acc = new Acceleration();
		force = new Force();
		mass = 100;
		name = this.getClass().getSimpleName() + ":" + totalEntities;
		totalEntities++;
		model = Model.generateRectangularPrismModel(texturePath, this.boundingBox.getWidth(), this.boundingBox.getHeight(), this.boundingBox.getDepth());
	}
	/**
	 * Get the current Position of this entity
	 * @return The current position
	 */
	public Position getPosition(){
		return pos;
	}
	/**
	 * Get a new Position object of this entity's position
	 * @return The new position
	 */
	public Position getNewPosition(){
		return new Position(pos.x(), pos.y(), pos.z(), pos.xRot(), pos.yRot(), pos.zRot());
	}
	/**
	 * Get the BoundingBox object of this entity
	 * @return The BoundingBox object of this entity
	 */
	public BoundingBox getBounds(){
		return boundingBox;
	}
	
	/**
	 * Override this method to render the correct entity
	 */
	public void render(){
		GL11.glTranslatef(pos.x(), pos.y(), pos.z());
		model.render();
		GL11.glTranslatef(-pos.x(), -pos.y(), -pos.z());
	}

	/**
	 * Override this method to perform actions
	 * on the entity.  This includes input, movement,
	 * and other actions.
	 * @param enviro The environment this entity is in
	 */
	public void act(Environment enviro){
		applyPhysics(enviro);
	}
	
	public void applyPhysics(Environment enviro){
		if((collidedWith(enviro) == null)){
			force.setForce(
					(float)(-force.getXForce()-acc.getXAcceleration()*mass-(vel.getXVelocity()/(.06))*mass),
					(float)(-force.getYForce()-acc.getYAcceleration()*mass-(vel.getYVelocity()/(.06))*mass), 
					(float)(-force.getXForce()-acc.getXAcceleration()*mass-(vel.getXVelocity()/(.06))*mass));
			acc.setAccelerationFromForce(force, mass);
			vel.accelerate(acc);
			force.setForce(0, 0, 0);
			acc.setAcceleration(0, 0, 0);
			vel.setVelocity(0, 0, 0);
		}
		else{
//			if(gravity != null)
//				gravity(gravity);
			
		}
	}
	public void gravity(Position g){
		this.setForce(g, 9.8f);
		System.out.println("Applied Gravity!");
	}
	
	public Entity collidedWith(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(indexedEnt != this){
				if(isCollidedWith(indexedEnt)){
					return indexedEnt;
				}
			}
		}
		return null;
	}

	/**
	 * This method will return whether or not this entity and
	 * another are colliding with each other
	 * 
	 * @param ent An entity to check against this entity
	 * @return Whether or not the entities are colliding
	 */
	 public boolean isCollidedWith(Entity other){
		  float dx = (this.getPosition().x() - other.getPosition().x());
		  float dy = (this.getPosition().y() - other.getPosition().y());
		  float dz = (this.getPosition().z() - other.getPosition().z());
		  if (dx * dx + dy * dy + dz * dz <= (this.boundingBox.getWidth()/2) * (this.boundingBox.getHeight()/2) * (this.boundingBox.getDepth()/2)) {
			  if(other instanceof Bullet){
					 hit(); 
					 return true;
				  }
			  if(last != null && last.x() != pos.x() && last.y() != pos.y() && last.z() != pos.z()){
				 
			  
			  if(dx > dy && dx > dz){
					this.getPosition().setX(this.getPosition().x() + (this.boundingBox.getWidth()/2 + other.boundingBox.getWidth()/2 - dx)/2);
				}else if(dy > dx && dy > dz){
					this.getPosition().setY(this.getPosition().y() + (this.boundingBox.getHeight()/2 + other.boundingBox.getHeight()/2 - dy)/2);
				}else if(dz > dx && dz > dy){
					this.getPosition().setZ(this.getPosition().z() + (this.boundingBox.getDepth()/2 + other.boundingBox.getDepth()/2 - dz)/2);
				}
			  }
			  return true;

		  }else {
			  return false;
		  }
	 }
	private void hit() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * This method will return whether or not this entity and
	 * a position are colliding with each other
	 * 
	 * @param pos the position to check
	 * @return Whether or not the entities are colliding
	 */
	public boolean isCollidedWith(Position pos){
		float[] xyzPos = {pos.x(), pos.y(), pos.z()};
		float[] xyzThis = {getPosition().x(), getPosition().y(), getPosition().z()};
		float[] whdThis = {getBounds().getWidth(), getBounds().getHeight(), getBounds().getDepth()};

		for(int i = 0; i < 3; i++){
			if(xyzPos[i] > xyzThis[i] - whdThis[i]){
				if(xyzPos[i] < xyzThis[i] + whdThis[i]){
//					System.out.println(xyzPos[0] + " - Point Collision Detected!");
					return true;
				}
			}
		}
		return false;
	}
	public void setForce(Position position, float magnitude) {
		force.setForceTo(this.getPosition(), position, magnitude);
		acc.setAccelerationFromForce(force, mass);
		vel.accelerate(acc);
	}
	public void setVelocity(float x, float y, float z){
		vel.setVelocity(x, y, z);
	}
	public void applyVelocity(){
		last = pos;
		pos.setX(pos.x()+vel.getXVelocity());
		pos.setY(pos.y()+vel.getYVelocity());
		pos.setZ(pos.z()+vel.getZVelocity());
	}
	public void removeSelfFromEnviroment(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			if(enviro.getEntity(i) == this){
				enviro.removeEntity(i);
				return;
			}
		}
	}
}
