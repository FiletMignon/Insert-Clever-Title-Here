package kontrol.main.weapons;

public class Pistol extends Weapon{
	public Pistol() {
		weaponType = WEPTYPE_PISTOLS;
	}
	public void fire(){
		System.out.println("This is me firing a pistol.");
	}
}
