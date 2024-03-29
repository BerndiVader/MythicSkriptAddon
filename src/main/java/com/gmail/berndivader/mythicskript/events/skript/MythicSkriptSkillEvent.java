package com.gmail.berndivader.mythicskript.events.skript;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.skills.SkillCaster;

public class MythicSkriptSkillEvent extends Event {
	private enum TargetType {
		NONE,
		LOCATION,
		ENTITY,
	}
	private static final HandlerList handlers = new HandlerList();
	private Entity targetEntity=null, trigger=null;
	private Location targetLocation=null;
	private SkillCaster caster;
	private String skill, args;
	private TargetType targettype;

	public MythicSkriptSkillEvent(SkillCaster caster,@Nullable Entity target,@Nullable Location targetloc,@Nullable AbstractEntity t,String s,String a) {
		this.caster = caster; this.skill = s; 
		if (a.length()>1 && (a.startsWith("\"") && a.endsWith("\""))) {
			this.args = a.substring(1, a.length()-1);
		} else {
			this.args = a;
		}
		if (t!=null) this.trigger = t.getBukkitEntity();
		this.targettype = TargetType.NONE;
		if (target instanceof Entity) {
			this.targetEntity = target;
			this.targettype = TargetType.ENTITY;
		} else if (targetloc instanceof Location) {
			this.targetLocation = targetloc;
			this.targettype = TargetType.LOCATION;
		}
	}
	
	public SkillCaster getCaster() {
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
		return handlers;
	}
	public static HandlerList getHandlerList() {
        return handlers;
    }
}
