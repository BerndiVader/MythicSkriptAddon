package com.gmail.berndivader.mythicskript.events.skript;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public final class MythicSkriptDeathEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private ActiveMob am;
	
	public MythicSkriptDeathEvent(ActiveMob activeMob) {
		this.am = activeMob;
	}
	
	public ActiveMob getActiveMob() {
		return this.am;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
        return handlers;
    }

	public Entity getEntity() {
		return this.am.getEntity().getBukkitEntity();
	}	
}
