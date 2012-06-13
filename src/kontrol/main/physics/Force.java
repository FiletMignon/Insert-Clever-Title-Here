package kontrol.main.physics;

import kontrol.main.util.Position;


public class Force {
	private float xFor;
	private float yFor;
	private float zFor;
	public Force(){
		xFor = 0;
		yFor = 0;
		zFor = 0;
	}
	public void setForce(float x, float y, float z){
		xFor = x;
		yFor = y;
		zFor = z;
	}
	public void setForceTo(Position self, Position other, float magnitude){
		double x = (self.x() - other.x());
		double y = (self.y() - other.y());
		double z = (self.z() - other.z());
		double xy = 0;
		double xz = 0;
		if(x != 0){
			if(x < 0){
				xy = (Math.atan(y/x));
			}
			else{
				xy = -(Math.atan(y/x) - Math.PI/2);
			}
			if(x < 0){
				xz = (Math.atan(z/x));
			}
			else{
				xz = -(Math.atan(z/ x) - Math.PI/2);
			}
		}
		x = (Math.cos(xy) * magnitude);
		y = (Math.sin(xy) * magnitude);
		z = (Math.cos(xz) * magnitude);
		setForce((float)x, (float)y, (float)z);
	}
	public float getXForce(){
		return xFor;
	}
	public float getYForce(){
		return yFor;
	}
	public float getZForce(){
		return zFor;
	}
}
