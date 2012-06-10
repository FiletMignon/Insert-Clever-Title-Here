package kontrol.main.weapons;

public abstract class Weapon {
	public static final int WEPTYPE_PISTOLS = 0;
	public static final int WEPTYPE_SUPPORT_ENVIRONMENT = 1;
	public static final int WEPTYPE_CANNONS = 3;
	public static final int WEPTYPE_SUPPORT_PLAYER = 4;
	
	protected int weaponType;
	
	public Weapon(){
	}
	public void fire(){
	}
	public void altFire(){
	}
	public void reload(){
	}
}
