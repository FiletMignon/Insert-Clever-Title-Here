package kontrol.main.util;

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
	public float addXRot(float xRot){
		this.xRot += xRot;
		return xRot;
	}
	/**
	 * @param yRot the Rotation to add to the current xRot
	 * @return The new y rotation
	 */
	public float addYRot(float yRot){
		this.yRot += yRot;
		return yRot;
	}
	/**
	 * @param zRot the Rotation to add to the current xRot
	 * @return The new z rotation
	 */
	public float addZRot(float zRot){
		this.zRot += zRot;
		return zRot;
	}

	public void forward(float distance){
		x -= distance * (float)Math.sin(Math.toRadians(yRot));
		//camPos[1] += distance * (float)Math.tan(Math.toRadians(pitch));
		z += distance * (float)Math.cos(Math.toRadians(yRot));
	}
	public void backward(float distance){
		x += distance * (float)Math.sin(Math.toRadians(yRot));
		//camPos[1] -= distance * (float)Math.tan(Math.toRadians(pitch));
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
		return new Position(-x(), -y(), -z());
	}
	public float distanceTo(Position pos){
		double sqX = Math.pow(pos.x()-x(), 2);
		double sqY = Math.pow(pos.y()-y(), 2);
		double sqZ = Math.pow(pos.z()-z(), 2);
		
		return (float) Math.sqrt(sqX+sqY+sqZ);
	}
}
