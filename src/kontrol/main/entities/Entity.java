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
//		float xDistance = (float)Math.abs(this.getPosition().x() - other.getPosition().x());
//		float yDistance = (float)Math.abs(this.getPosition().y() - other.getPosition().y());
//		float zDistance = (float)Math.abs(this.getPosition().z() - other.getPosition().z());
//		System.out.println(pos.distanceTo(other.getPosition()));
//		if(xDistance <= ((this.boundingBox.getWidth()/2) + (other.boundingBox.getWidth()/2)) && 
//				yDistance <= ((this.boundingBox.getHeight()/2) + (other.boundingBox.getHeight()/2)) &&
//				zDistance <= ((this.boundingBox.getDepth()/2) + (other.boundingBox.getDepth()/2)))
//		{
//			return true;
//		}
//		else
//			return false;

//		System.out.println(pos.distanceTo(other.getPosition()));
//	    if ( Math.abs(getPosition().x() - other.getPosition().x()) > (getBounds().getWidth() + other.getBounds().getWidth() )) return false;
//	    if ( Math.abs(getPosition().y() - other.getPosition().y()) > (getBounds().getHeight() + other.getBounds().getHeight() )) return false;
//	    if ( Math.abs(getPosition().z() - other.getPosition().z()) > (getBounds().getDepth() + other.getBounds().getDepth() )) return false;
//		System.out.println("Collided!");
//	    return true;

		Position minVertexThis = getBounds().getMinVertex(getPosition());
		Position maxVertexThis = getBounds().getMaxVertex(getPosition());
		
		Position minVertexOther = other.getBounds().getMinVertex(other.getPosition());
		Position maxVertexOther = other.getBounds().getMaxVertex(other.getPosition());

		//Check if BB1's max is greater than BB2's min and BB1's min is less than BB2's max

		System.out.println(pos.distanceTo(other.getPosition()));
		return(maxVertexThis.x() > minVertexOther.x() && 
				minVertexThis.x() < maxVertexOther.x() &&
			    maxVertexThis.y() > minVertexOther.y() &&
			    minVertexThis.y() < maxVertexOther.y() &&
			    maxVertexThis.z() > minVertexOther.z() &&
			    minVertexThis.z() < maxVertexOther.z());
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
