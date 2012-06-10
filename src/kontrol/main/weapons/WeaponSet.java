package kontrol.main.weapons;

import java.util.ArrayList;

public class WeaponSet {
	private ArrayList<Weapon> weaponSet = new ArrayList<Weapon>(0);
	
	public WeaponSet(){
	}
	public ArrayList<Weapon> getSet(){
		return weaponSet;
	}
	
	/**
	 * Check and see if the index given is a valid index in this weapon set
	 * @param weaponIndex The index to check
	 * @return Whether or not this index is valid in this WeaponSet
	 */
	public boolean isValidIndex(int weaponIndex){
		return !(weaponIndex < weaponSet.size()-1 && weaponIndex > 0);
	}
	
	/**
	 * Returns a weapon if the index is valid.  Otherwise returns null.
	 * @param weaponIndex The index to grab the weapon from
	 * @return weapon at weaponIndex if valid, otherwise returns null
	 */
	public Weapon getWeapon(int weaponIndex){
		if(isValidIndex(weaponIndex)){
			return weaponSet.get(weaponIndex);
		}
		return null;
	}
	/**
	 * Add a weapon to this set
	 * @param weapon The weapon being added
	 */
	public void addWeapon(Weapon weapon){
		weaponSet.add(weapon);
	}
}
