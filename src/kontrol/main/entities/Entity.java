package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.physics.Acceleration;
import kontrol.main.physics.BoundingBox;
import kontrol.main.physics.Force;
import kontrol.main.physics.Velocity;
import kontrol.main.util.Position;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Entity {
	protected Texture texture;
	protected BoundingBox boundingBox;
	protected Position pos;
	protected Velocity vel;
	protected Acceleration acc;
	protected Force force;
	protected float mass;
	protected String name;
	
	protected static int totalEntities = 1;
	
	/**
	 * 
	 * @param texture File path in the source folder to locate the texture
	 * @param boundingBox The bounding box of the entity
	 * @param pos The position of the entity
	 */
	public Entity(String texture, BoundingBox boundingBox, Position pos){
		try {
			this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/" + texture));
		} catch (Exception d) {
			try {
				this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/" + "default.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.boundingBox = boundingBox;
		this.pos = pos;
		vel = new Velocity();
		acc = new Acceleration();
		force = new Force();
		mass = 100;
		name = "Entity#" + totalEntities;
	}
	/**
	 * Get the current Position of this entity
	 * @return The current position
	 */
	public Position getPosition(){
		return pos;
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
		float width = boundingBox.getWidth()/2;
		float height = boundingBox.getHeight()/2;
		float depth = boundingBox.getDepth()/2;
		GL11.glTranslatef(pos.x(), pos.y(), pos.z());
		texture.bind();
	    GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
		    
			GL11.glTexCoord2f(0.0f, 1.0f);
		    GL11.glVertex3f( width, height,-depth);         // Top Right Of The Quad (Top)
			GL11.glTexCoord2f(0.0f, 0.0f);
		    GL11.glVertex3f(-width, height,-depth);         // Top Left Of The Quad (Top)
			GL11.glTexCoord2f(1.0f, 0.0f);
		    GL11.glVertex3f(-width, height, depth);         // Bottom Left Of The Quad (Top)
			GL11.glTexCoord2f(1.0f, 1.0f);
		    GL11.glVertex3f( width, height, depth);         // Bottom Right Of The Quad (Top)
		
			GL11.glTexCoord2f(1.0f, 1.0f);
		    GL11.glVertex3f( width,-height, depth);         // Top Right Of The Quad (Bottom)
			GL11.glTexCoord2f(0.0f, 1.0f);
		    GL11.glVertex3f(-width,-height, depth);         // Top Left Of The Quad (Bottom)
			GL11.glTexCoord2f(0.0f, 0.0f);
		    GL11.glVertex3f(-width,-height,-depth);         // Bottom Left Of The Quad (Bottom)
			GL11.glTexCoord2f(1.0f, 0.0f);
		    GL11.glVertex3f( width,-height,-depth);         // Bottom Right Of The Quad (Bottom)
		
			GL11.glTexCoord2f(0.0f, 0.0f);
		    GL11.glVertex3f( width, height, depth);         // Top Right Of The Quad (Front)
		    GL11.glTexCoord2f(1.0f,0.0f);
		    GL11.glVertex3f(-width, height, depth);         // Top Left Of The Quad (Front)
		    GL11.glTexCoord2f(1.0f,1.0f);
		    GL11.glVertex3f(-width,-height, depth);         // Bottom Left Of The Quad (Front)
		    GL11.glTexCoord2f(0.0f,1.0f);
		    GL11.glVertex3f( width,-height, depth);         // Bottom Right Of The Quad (Front)
		
			GL11.glTexCoord2f(1.0f, 0.0f);
		    GL11.glVertex3f( width,-height,-depth);         // Bottom Left Of The Quad (Back)
			GL11.glTexCoord2f(1.0f, 1.0f);
		    GL11.glVertex3f(-width,-height,-depth);         // Bottom Right Of The Quad (Back)
			GL11.glTexCoord2f(0.0f, 1.0f);
		    GL11.glVertex3f(-width, height,-depth);         // Top Right Of The Quad (Back)
			GL11.glTexCoord2f(0.0f, 0.0f);
		    GL11.glVertex3f( width, height,-depth);         // Top Left Of The Quad (Back)
		
			GL11.glTexCoord2f(0.0f, 0.0f);
		    GL11.glVertex3f(-width, height, depth);         // Top Right Of The Quad (Left)
			GL11.glTexCoord2f(1.0f, 0.0f);
		    GL11.glVertex3f(-width, height,-depth);         // Top Left Of The Quad (Left)
			GL11.glTexCoord2f(1.0f, 1.0f);
		    GL11.glVertex3f(-width,-height,-depth);         // Bottom Left Of The Quad (Left)
			GL11.glTexCoord2f(0.0f, 1.0f);
		    GL11.glVertex3f(-width,-height, depth);         // Bottom Right Of The Quad (Left)
		
			GL11.glTexCoord2f(1.0f, 0.0f);
		    GL11.glVertex3f( width, height,-depth);         // Top Right Of The Quad (Right)
			GL11.glTexCoord2f(1.0f, 1.0f);
		    GL11.glVertex3f( width, height, depth);         // Top Left Of The Quad (Right)
		    GL11.glTexCoord2f(0.0f, 1.0f);
		    GL11.glVertex3f( width,-height, depth);         // Bottom Left Of The Quad (Right)
		    GL11.glTexCoord2f(0.0f, 0.0f);
		    GL11.glVertex3f( width,-height,-depth);         // Bottom Right Of The Quad (Right)
		GL11.glEnd();
		GL11.glTranslatef(-pos.x(), -pos.y(), -pos.z());
	}

	/**
	 * Override this method to perform actions
	 * on the entity.  This includes input, movement,
	 * and other actions.
	 * @param enviro The environment this entity is in
	 */
	public void act(Environment enviro){
		//Do everything
	}
	
	public boolean isCollidedWith(Entity other){
		float dx = this.getPosition().x() - other.getPosition().x();
		float dy = this.getPosition().y() - other.getPosition().y();
		float dz = this.getPosition().z() - other.getPosition().z();

		if (dx * dx + dy * dy + dz * dz <= this.boundingBox.getWidth() * this.boundingBox.getHeight() * this.boundingBox.getDepth())
		{
		 System.out.println("collision");
		 return true;
		}
		else 
		{
			System.out.println("no collision");
			return false;
		}
	}
	/**
	 * This method will return whether or not this entity and
	 * another are colliding with each other
	 * 
	 * @param ent An entity to check against this entity
	 * @return Whether or not the entities are colliding
	 */
/*	public boolean isCollidedWith(Entity ent){
	}*/
	/**
	 * This method will return whether or not this entity and
	 * a position are colliding with each other
	 * 
	 * @param pos the position to check
	 * @return Whether or not the entities are colliding
	 */
//	public boolean isCollidedWith(Position pos){
//		float[] xyzPos = {pos.x(), pos.y(), pos.z()};
//		float[] xyzThis = {getPosition().x(), getPosition().y(), getPosition().z()};
//		float[] whdThis = {getBounds().getWidth(), getBounds().getHeight(), getBounds().getDepth()};
//
//		for(int i = 0; i < 3; i++){
//			if(xyzPos[i] > xyzThis[i] - whdThis[i]){
//				if(xyzPos[i] < xyzThis[i] + whdThis[i]){
////					System.out.println(xyzPos[0] + " - Point Collision Detected!");
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	public void setForce(Position position) {
		force.setForceTo(getPosition(), position, 9.8f);
		acc.setAccelerationFromForce(force, mass);
		vel.accelerate(acc);
	}
	public void applyVelocity(){
		pos.setX(pos.x()+vel.getXVelocity());
		pos.setY(pos.y()+vel.getYVelocity());
		pos.setZ(pos.z()+vel.getZVelocity());
	}
}
