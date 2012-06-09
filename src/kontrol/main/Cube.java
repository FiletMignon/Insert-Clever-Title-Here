package kontrol.main;

public class Cube extends Entity{
	private Velocity velocity;
	public Cube(String texture, Position pos) {
		super(texture, new BoundingBox(1,1,1), pos);
		velocity = new Velocity();
	}
	public void setVelocity(float x, float y, float z){
		velocity = new Velocity(x, y, z);
	}
	public void act(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(isCollidedWith(indexedEnt)){
				velocity.setVelocity(0, 0, 0);
			}
			else{
				pos.x(velocity.getXVelocity());
				pos.y(velocity.getYVelocity());
				pos.z(velocity.getZVelocity());
			}
		}
	}
}
