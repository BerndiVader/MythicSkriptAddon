package com.gmail.berndivader.mmSkriptAddon.mm400.events.custom;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class mmMythicMobsSkriptSkill extends Event {
	private enum TargetType {
		NONE,
		LOCATION,
		ENTITY,
	}
	private static final HandlerList handlers = new HandlerList();
	private Entity targetEntity=null, trigger=null;
	private Location targetLocation=null;
	private ActiveMob caster;
	private String skill, args;
	private TargetType targettype;

	public mmMythicMobsSkriptSkill(ActiveMob am, Entity target, Location targetloc, AbstractEntity t, String s, String a) {
		this.caster = am; skill = s; args = a.substring(1, a.length()-1);
		if (t!=null) trigger = t.getBukkitEntity();
		this.targettype = TargetType.NONE;
		if (target instanceof Entity) {
			this.targetEntity = target;
			this.targettype = TargetType.ENTITY;
		} else if (targetloc instanceof Location) {
			this.targetLocation = targetloc;
			this.targettype = TargetType.LOCATION;
		}
	}
	
	public ActiveMob getCaster() {
		return this.caster;
	}
	public Entity getTargetEntity() {
		return this.targetEntity;
	}
	public Entity getTrigger() {
		return this.trigger;
	}
	public Location getTargetLocation() {
		return this.targetLocation;
	}
	public String getSkill() {
		return this.skill;
	}
	public String getArgs() {
		return this.args;
	}
	public String getTargetType() {
		return this.targettype.toString();
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}
	public static HandlerList getHandlerList() {
        return handlers;
    }
}
