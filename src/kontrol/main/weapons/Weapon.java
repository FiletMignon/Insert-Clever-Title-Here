package kontrol.main.weapons;

import kontrol.main.Environment;
import kontrol.main.entities.Player;

public abstract class Weapon {
	public static final int WEPTYPE_PISTOLS = 0;
	public static final int WEPTYPE_SUPPORT_ENVIRONMENT = 1;
	public static final int WEPTYPE_CANNONS = 3;
	public static final int WEPTYPE_SUPPORT_PLAYER = 4;
	
	protected int weaponType;
	
	public Weapon(){
	}
	public void fire(Player player, Environment enviro){
	}
	public void altFire(Player player, Environment enviro){
	}
	public void reload(Player player, Environment enviro){
	}
}
