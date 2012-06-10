package kontrol.main.physics;


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
		float xy = 0;
		float yz = 0;
		
		//set angles
		if(other.x() < 0){
			xy = -1 * (float) Math.atan2(other.y(), other.x());
		}
		else{
			xy = (float) Math.atan2(other.y(), other.x());
		}
		if(other.y() < 0){
			yz = -1 * (float) Math.atan2(other.z(), other.y());
		}
		else{
			yz = (float) Math.atan2(other.z(), other.y());
		}
		
		//calculate vectors
		xFor = magnitude * (float)Math.cos(xy);
		yFor = magnitude * (float)Math.sin(xy);
		zFor = magnitude * (float)Math.cos(yz);
		System.out.println("X:"+ xFor +" Y:"+ yFor+" Z:"+ zFor);
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
