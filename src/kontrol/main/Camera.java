package kontrol.main;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
 
//First Person Camera Controller
public class Camera{
	private float[] camPos = new float[3];
	private float yaw = 0.0f;
	private float pitch = 0.0f;
	
	public Camera(float x, float y, float z){
		camPos[0] = x;
		camPos[1] = y;
		camPos[2] = z;
	}
	public void yaw(float amount){
		//Increase the yaw by the amount passed through
		yaw += amount;
	}
	public void pitch(float amount){
		//Increase the pitch by the amount passed through but not passing 90 degrees
		if(pitch+1 > 90){
			pitch = 90;
		}
		if(pitch-1 < -90){
			pitch = -90;
		}
		else{
			pitch += amount;
		}
	}
	public void forward(float distance){
		camPos[0] -= distance * (float)Math.sin(Math.toRadians(yaw));
		//camPos[1] += distance * (float)Math.tan(Math.toRadians(pitch));
		camPos[2] += distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void backward(float distance){
		camPos[0] += distance * (float)Math.sin(Math.toRadians(yaw));
		//camPos[1] -= distance * (float)Math.tan(Math.toRadians(pitch));
		camPos[2] -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void strafeL(float distance){
		camPos[0] -= distance * (float)Math.sin(Math.toRadians(yaw-90));
		camPos[2] += distance * (float)Math.cos(Math.toRadians(yaw-90));
	}
	public void strafeR(float distance){
		camPos[0] -= distance * (float)Math.sin(Math.toRadians(yaw+90));
		camPos[2] += distance * (float)Math.cos(Math.toRadians(yaw+90));
	}
	public void up(float distance){
		camPos[1] -= distance;
	}
	public void down(float distance){
		camPos[1] += distance;
	}
	public void lookThrough()
    {
        //rotate the pitch around the X axis
        GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        //rotate the yaw around the Y axis
        GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        GL11.glTranslatef(camPos[0], camPos[1], camPos[2]);
    }
	public float x(){
		return camPos[0];
	}
	public float y(){
		return camPos[1];
	}
	public float z(){
		return camPos[2];
	}
	private float speed = 0.05f;
	public void input(){
        if (Keyboard.isKeyDown(Keyboard.KEY_W)){
        	forward(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
        	backward(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){ 
        	strafeL(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){ 
        	strafeR(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){  
        	down(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){    
            up(speed);
        }
        if(Mouse.isButtonDown(0)){
            yaw(Mouse.getDX()*speed);
            pitch(-Mouse.getDY()*speed);
    		Mouse.setGrabbed(true);
        }
        else if(!Mouse.isButtonDown(0)){
    		Mouse.setGrabbed(false);
        }
	}
	public void speedBoostOn(){
		speed = 0.15f;
	}
	public void speedBoostOff(){
		speed = 0.05f;
	}
}