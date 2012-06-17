package kontrol.main.util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Position {
	private float x,y,z;
	private float xRot, yRot, zRot;
	public Position(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		xRot = 0;
		yRot = 0;
		zRot = 0;
	}
	public Position(float x, float y, float z, float xRot, float yRot, float zRot){
		this.x = x;
		this.y = y;
		this.z = z;
		this.xRot = xRot;
		this.yRot = yRot;
		this.zRot = zRot;
	}
	public void setX(float coord){
		x = coord;
	}
	public void setY(float coord){
		y = coord;
	}
	public void setZ(float coord){
		z = coord;
	}
	public void setXRot(float coord){
		xRot = coord;
	}
	public void setYRot(float coord){
		yRot = coord;
	}
	public void setZRot(float coord){
		zRot = coord;
	}
	/**
	 * Get the x coordinate
	 * @return The x coordinate
	 */
	public float x(){
		return x;
	}
	/**
	 * Get the y coordinate
	 * @return The y coordinate
	 */
	public float y(){
		return y;
	}
	/**
	 * Get the z coordinate
	 * @return The z coordinate
	 */
	public float z(){
		return z;
	}
	/**
	 * Get the x rotation
	 * @return The x rotation
	 */
	public float xRot(){
		return xRot;
	}
	/**
	 * Get the y rotation
	 * @return The y rotation
	 */
	public float yRot(){
		return yRot;
	}
	/**
	 * Get the z rotation
	 * @return The z rotation
	 */
	public float zRot(){
		return zRot;
	}
	

	/**
	 * @param xRot the Rotation to add to the current xRot
	 * @return The new x rotation
	 */
	public void addXRot(float xRot){
		if(this.xRot + xRot < 90 && this.xRot + xRot > -90){
			this.xRot += xRot;
		}
	}
	/**
	 * @param yRot the Rotation to add to the current xRot
	 * @return The new y rotation
	 */
	public void addYRot(float yRot){
		this.yRot += yRot;
	}
	/**
	 * @param zRot the Rotation to add to the current xRot
	 * @return The new z rotation
	 */
	public void addZRot(float zRot){
		this.zRot += zRot;
	}

	public void forward(float distance){
		x -= distance * (float)Math.sin(Math.toRadians(yRot));
//		y += distance * (float)Math.sin(Math.toRadians(xRot));
		z += distance * (float)Math.cos(Math.toRadians(yRot));
	}
	public void backward(float distance){
		x += distance * (float)Math.sin(Math.toRadians(yRot));
//		y -= distance * (float)Math.sin(Math.toRadians(xRot));
		z -= distance * (float)Math.cos(Math.toRadians(yRot));
	}
	public void strafeL(float distance){
		x -= distance * (float)Math.sin(Math.toRadians(yRot-90));
		z += distance * (float)Math.cos(Math.toRadians(yRot-90));
	}
	public void strafeR(float distance){
		x -= distance * (float)Math.sin(Math.toRadians(yRot+90));
		z += distance * (float)Math.cos(Math.toRadians(yRot+90));
	}
	public void up(float distance){
		y -= distance;
	}
	public void down(float distance){
		y += distance;
	}
	public Position inverse() {
		return new Position(-x, -y, -z, xRot, yRot, zRot);
	}
	public static Position screenToWorld(int x, int y){
		int[] v = new int[16];
        IntBuffer viewport = BufferUtils.createIntBuffer(16).put(v);  
        viewport.flip();
        float[] m = new float[16];
        FloatBuffer modelview = BufferUtils.createFloatBuffer(16).put(m);
        modelview.flip();
        float[] p = new float[16];
        FloatBuffer projection = BufferUtils.createFloatBuffer(16).put(p);
        projection.flip();
        float winX, winY;
        float[] z = new float[1];
        FloatBuffer winZ = BufferUtils.createFloatBuffer(1).put(z);
        winZ.flip();
        FloatBuffer pos = BufferUtils.createFloatBuffer(3);
        GL11.glGetFloat( GL11.GL_MODELVIEW_MATRIX, modelview);
        GL11.glGetFloat( GL11.GL_PROJECTION_MATRIX, projection);
        GL11.glGetInteger( GL11.GL_VIEWPORT, viewport);
        winX = (float)x;
        winY = (float)viewport.get(3) - (float)y;
        GL11.glReadPixels( x, (int)winY, 1, 1, GL11.GL_DEPTH_COMPONENT,  GL11.GL_FLOAT, winZ);
        GLU.gluUnProject( winX, winY, winZ.get(0), modelview, projection, viewport, pos);
        return new Position(pos.get(0),pos.get(1),pos.get(2));
	}
	public static Position lookingAt(){
		return screenToWorld(Display.getWidth()/2, Display.getHeight()/2);
	}
	public float distanceTo(Position pos){
		double sqX = Math.pow(pos.x()-x(), 2);
		double sqY = Math.pow(pos.y()-y(), 2);
		double sqZ = Math.pow(pos.z()-z(), 2);
		
		return (float) Math.sqrt(sqX+sqY+sqZ);
	}
	public static Position midpoint(Position pos1, Position pos2){
		float x = (pos1.x() + pos2.x()) / 2;
		float y = (pos1.y() + pos2.y()) / 2;
		float z = (pos1.z() + pos2.z()) / 2;
		
		return new Position(x, y, z);
	}
}
