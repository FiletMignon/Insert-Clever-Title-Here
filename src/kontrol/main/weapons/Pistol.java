package kontrol.main.weapons;

import kontrol.main.Environment;
import kontrol.main.entities.Bullet;
import kontrol.main.entities.Player;
import kontrol.main.util.Position;

public class Pistol extends Weapon{
	public Pistol() {
		weaponType = WEPTYPE_PISTOLS;
	}
	private int framesSinceLastShot = 0;
	private boolean canShoot = true;
	public void fire(Player player, Environment enviro){
		if(canShoot){
			canShoot = false;
			shoot(player.getPosition(), enviro);
			System.out.println("Pistol Fired!");
		}
		if(!canShoot){
			framesSinceLastShot++;
		}
		if(framesSinceLastShot > 30){
			canShoot = true;
			framesSinceLastShot = 0;
		}
	}
	private void shoot(Position shootFromPos, Environment enviro) {
		shootFromPos.forward(0.5f);
		enviro.addEntity(new Bullet(shootFromPos));
	}
	public void altFire(Player player, Environment enviro){
		shoot(player.getPosition(), enviro);
	}
}
