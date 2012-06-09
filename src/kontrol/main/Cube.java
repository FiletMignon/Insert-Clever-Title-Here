package kontrol.main;

public class Cube extends Entity{
	private float velocityX, velocityY, velocityZ;
	public Cube(String texture, Position pos) {
		super(texture, new BoundingBox(1,1,1), pos);
		velocityX = 0;
		velocityY = 0;
		velocityZ = 0;
	}
	public float velocityX(float velocity){
		velocityX += velocity;
		return velocityX;
	}
	public float velocityY(float velocity){
		velocityY += velocity;
		return velocityY;
	}
	public float velocityZ(float velocity){
		velocityZ += velocity;
		return velocityZ;
	}
	public float velocityX(){
		return velocityX;
	}
	public float velocityY(){
		return velocityY;
	}
	public float velocityZ(){
		return velocityZ;
	}
	public void act(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(!isCollidedWith(indexedEnt)){
				pos.x(velocityX);
				pos.y(velocityY);
				pos.z(velocityZ);
			}
		}
	}
}
