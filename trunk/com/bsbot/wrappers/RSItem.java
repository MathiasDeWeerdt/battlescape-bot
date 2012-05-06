
package com.bsbot.wrappers;

import com.bsbot.api.Inventory;
import com.bsbot.hooks.ItemDef;
import com.bsbot.launcher.BSLoader;

public class RSItem{

	private int id;
	private ItemDef itemDef;
	private int slot;

	public RSItem(int theId, int theSlot) {
		this.slot = theSlot+1;
		this.id = theId;
		if(theId > -1){
		this.itemDef = BSLoader.getClient().getForId(id);
		}
	}
	
	

	public int getId() {
		return id;
	}

	public int getSlot() {
		return slot -1;
	}
	
	public void interact(String action) {
		Inventory.interactSlot(getSlot(), action + " " + getName());
		}

	public void interactBank(String action) {
		Inventory.interactSlotBank(getSlot(), action);
		}

	/*public boolean isStackable() {
		return itemDef.getStackable();
	}

	public int getValue() {
		return itemDef.getValue();
	}

	public int getTeam() {
		return itemDef.getTeam();
	}

	public String[] getGroundActions() {
		return itemDef.getGroundActions();
	}*/

	public String getName() {
		if(itemDef != null && itemDef.getName() != null){
		return itemDef.getName();
		}
		return "null";
	}

}