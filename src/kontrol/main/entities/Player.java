package kontrol.main.entities;

import kontrol.main.physics.BoundingBox;
import kontrol.main.physics.Position;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Player extends Entity {
	private String name;
	public Player(String texture, Position pos, String name) {
		super(texture, new BoundingBox(1, 1, 1), pos);
		this.name = name;
	}

	/**
	 * Gets the name of the player
	 * @return The name of the player
	 */
	public String getName(){
		return name;
	}
	public void render(){
		//Render Nothing
	}
	public void act(){
		input();
	}
	private float speed = 0.05f;
	public void input(){
    	getPosition().addXRot(-Mouse.getDY()*speed);
    	getPosition().addYRot(Mouse.getDX()*speed);
		Mouse.setGrabbed(true);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)){
        	getPosition().forward(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
        	getPosition().backward(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){ 
        	getPosition().strafeL(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){ 
        	getPosition().strafeR(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Z)){  
        	getPosition().down(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){  
        	getPosition().up(speed);
        }
        if(Mouse.isButtonDown(0)){
    		fire();
        }

	}
	
	private void fire() {
		Bullet b = new Bullet("bullet", new BoundingBox(.1f,.1f,.1f),getPosition());
		
	}

	/**
	 * Used before rendering to revolve the world around you
	 * in order to get a perspective of things  :P
	 */
	public void cameraView(){
        //rotate xRot around the X axis
        GL11.glRotatef(getPosition().xRot(), 1.0f, 0.0f, 0.0f);
        //rotate yRot around the Y axis
        GL11.glRotatef(getPosition().yRot(), 0.0f, 1.0f, 0.0f);
        //rotate zRot around the Z axis
        GL11.glRotatef(getPosition().zRot(), 0.0f, 0.0f, 1.0f);
        //translate to the position vector's location
        GL11.glTranslatef(getPosition().x(), getPosition().y(), getPosition().z());
	}
}
