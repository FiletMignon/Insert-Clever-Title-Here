package kontrol.main.entities;

import kontrol.main.Environment;
import kontrol.main.util.Position;

public class TrackingBullet extends Bullet {
	public TrackingBullet(Position shootFrom, Position shootTo, Environment enviro) {
		super(shootFrom, shootTo, enviro);
	}
	public void act(Environment enviro){
		this.setForce(Position.lookingAt(), 1.0f);
		super.act(enviro);
	}
}
