package com.gmail.berndivader.mythicskript.events.skript;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.gmail.berndivader.mythicskript.Utils;

import io.lumine.mythic.core.mobs.ActiveMob;

public class MythicSkriptConditionEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Entity casterEntity, targetEntity;
	private Location targetLoc, casterLoc;
	private String conditionName;
	private String condtionArgs;
	private Boolean bool;
	
	
	public MythicSkriptConditionEvent(Location caster, Location target, String n, String a, Boolean b) {
		this(null,null,caster,target,n,a,b);
	}
	
	public MythicSkriptConditionEvent(Entity caster, Entity target, String n, String a, Boolean b) {
		this(caster,target,null,null,n,a,b);
	}
	
	public MythicSkriptConditionEvent(Entity casterEntity, Entity entityTarget, Location casterLoc, Location targetLoc, String n, String a, Boolean b) {
		this.casterEntity = casterEntity;
		this.targetEntity = entityTarget;
		this.casterLoc = casterLoc;
		this.targetLoc = targetLoc;
		this.conditionName = n;
		this.condtionArgs = a;
		this.bool = b;
	}

	public Entity getCasterEntity() {
		return this.casterEntity;
	}
	public Entity getTargetEntity() {
		return this.targetEntity;
	}
	public ActiveMob getActiveMob() {
		if (Utils.mobManager.isActiveMob(this.casterEntity.getUniqueId())) {
			return Utils.mythicHelper.getMythicMobInstance(this.casterEntity);
		}
		return null;
	}
	public Location getCasterLocation() {
		return this.casterLoc;
	}
	public Location getTargetLocation() {
		return this.targetLoc;
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
