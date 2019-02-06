package com.gmail.berndivader.mythicskript.classes;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class MythicDrops {
	private List<ItemStack> drops;
	public MythicDrops(final List<ItemStack> d) {
		setDrops(d);
	}
	public List<ItemStack> getDrops() {
		return drops;
	}
	public void setDrops(List<ItemStack> drops) {
		this.drops = drops;
	}
	public void removeItem(ItemStack item) {
		this.drops.remove(item);
	}
	public void addItem(ItemStack item) {
		this.drops.add(item);
	}
}
