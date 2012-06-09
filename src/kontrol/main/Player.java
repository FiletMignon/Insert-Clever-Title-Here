package kontrol.main;

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
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){  
        	getPosition().down(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){    
        	getPosition().up(speed);
        }
        if(Mouse.isButtonDown(0)){
    		Mouse.setGrabbed(true);
        }
        else if(!Mouse.isButtonDown(0)){
    		Mouse.setGrabbed(false);
        }
        if(Mouse.isButtonDown(1)){
        	getPosition().addZRot(-Mouse.getDY()*speed);
        }
        else if(!Mouse.isButtonDown(1)){
        	getPosition().addXRot(Mouse.getDX()*speed);
        	getPosition().addYRot(-Mouse.getDY()*speed);
        }
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
