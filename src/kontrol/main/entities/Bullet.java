package kontrol.main.entities;

import org.lwjgl.opengl.GL11;

import kontrol.main.Environment;
import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Bullet extends Entity{
	
	private Position shootTo;
	private Position shootFrom;
	
	public Bullet(Position shootFrom, Position shootTo, Environment enviro){
		super(null, new BoundingBox(0.05f, 0.05f, 0.05f), shootFrom, enviro);
		this.shootFrom = shootFrom;
		this.shootTo = new Position(shootTo.x(), shootTo.y(), shootTo.z());
		this.mass = 5;
		this.setForce(this.shootTo, 10f);
	}
	public void render(){
		float width = boundingBox.getWidth()/2;
		float height = boundingBox.getHeight()/2;
		float depth = boundingBox.getDepth()/2;
		GL11.glTranslatef(pos.x(), pos.y(), pos.z());
	    GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
		    
		    GL11.glVertex3f( width, height,-depth);         // Top Right Of The Quad (Top)
		    GL11.glVertex3f(-width, height,-depth);         // Top Left Of The Quad (Top)
		    GL11.glVertex3f(-width, height, depth);         // Bottom Left Of The Quad (Top)
		    GL11.glVertex3f( width, height, depth);         // Bottom Right Of The Quad (Top)
		
		    GL11.glVertex3f( width,-height, depth);         // Top Right Of The Quad (Bottom)
		    GL11.glVertex3f(-width,-height, depth);         // Top Left Of The Quad (Bottom)
		    GL11.glVertex3f(-width,-height,-depth);         // Bottom Left Of The Quad (Bottom)
		    GL11.glVertex3f( width,-height,-depth);         // Bottom Right Of The Quad (Bottom)
		
		    GL11.glVertex3f( width, height, depth);         // Top Right Of The Quad (Front)
		    GL11.glVertex3f(-width, height, depth);         // Top Left Of The Quad (Front)
		    GL11.glVertex3f(-width,-height, depth);         // Bottom Left Of The Quad (Front)
		    GL11.glVertex3f( width,-height, depth);         // Bottom Right Of The Quad (Front)
		
		    GL11.glVertex3f( width,-height,-depth);         // Bottom Left Of The Quad (Back)
		    GL11.glVertex3f(-width,-height,-depth);         // Bottom Right Of The Quad (Back)
		    GL11.glVertex3f(-width, height,-depth);         // Top Right Of The Quad (Back)
		    GL11.glVertex3f( width, height,-depth);         // Top Left Of The Quad (Back)
		
		    GL11.glVertex3f(-width, height, depth);         // Top Right Of The Quad (Left)
		    GL11.glVertex3f(-width, height,-depth);         // Top Left Of The Quad (Left)
		    GL11.glVertex3f(-width,-height,-depth);         // Bottom Left Of The Quad (Left)
		    GL11.glVertex3f(-width,-height, depth);         // Bottom Right Of The Quad (Left)
		
		    GL11.glVertex3f( width, height,-depth);         // Top Right Of The Quad (Right)
		    GL11.glVertex3f( width, height, depth);         // Top Left Of The Quad (Right)
		    GL11.glVertex3f( width,-height, depth);         // Bottom Left Of The Quad (Right)
		    GL11.glVertex3f( width,-height,-depth);         // Bottom Right Of The Quad (Right)
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glEnd();
		GL11.glTranslatef(-pos.x(), -pos.y(), -pos.z());
	}
	public void act(Environment enviro){
		applyVelocity();
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!(indexedEnt instanceof Bullet) && this != indexedEnt){
				if(isCollidedWith(indexedEnt)){
					System.out.println(name + " has collided with " + indexedEnt.name);
					removeSelfFromEnviroment(enviro);
					return;
				}
			}
		}
	}
}
