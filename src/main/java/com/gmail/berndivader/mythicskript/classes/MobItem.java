package com.gmail.berndivader.mythicskript.classes;

import org.bukkit.inventory.ItemStack;

public class MobItem {
	private ItemStack item;
	
	public MobItem(ItemStack i) {
		this.setItem(i);
	}

	public ItemStack getItem() {
		return item;
	}
	public void setItem(ItemStack item) {
		this.item = item;
	}
}
