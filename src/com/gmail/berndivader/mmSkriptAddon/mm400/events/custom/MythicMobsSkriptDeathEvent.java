package com.gmail.berndivader.mmSkriptAddon.mm400.events.custom;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public final class MythicMobsSkriptDeathEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private ActiveMob am;
	private ItemStack[]drops;
	
	public MythicMobsSkriptDeathEvent(ActiveMob activeMob) {
		this.am = activeMob;
	}
	
	public ActiveMob getActiveMob() {
		return this.am;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
        return handlers;
    }

	public Entity getEntity() {
		return this.am.getEntity().getBukkitEntity();
	}	
}
