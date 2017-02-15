package com.gmail.berndivader.mmSkriptAddon.mm400.events.custom;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class mmMythicMobsSkriptConditionEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Entity entity;
	private Location location;
	private String conditionName;
	private String condtionArgs;
	private Boolean bool;
	
	public mmMythicMobsSkriptConditionEvent(Entity e, Location l, String n, String a, Boolean b) {
		this.entity = e;
		this.location = l;
		this.conditionName = n;
		this.condtionArgs = a;
		this.bool = b;
	}

	public Entity getEntity() {
		return this.entity;
	}
	public ActiveMob getActiveMob() {
		if (MythicMobs.inst().getMobManager().isActiveMob(this.entity.getUniqueId())) {
			return MythicMobs.inst().getAPIHelper().getMythicMobInstance(this.entity);
		}
		return null;
	}
	public Location getLocation() {
		return this.location;
	}
	public String getName() {
		return this.conditionName;
	}
	public String getArgs() {
		return this.condtionArgs;
	}
	public Boolean getBool() {
		return this.bool;
	}
	public void setBool(Boolean b) {
		this.bool = b;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
        return handlers;
    }
}
