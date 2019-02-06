package com.gmail.berndivader.mythicskript;

import org.bukkit.entity.Entity;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;

public class ActivePlayer implements SkillCaster {
	private AbstractEntity entity;
	private boolean usedamageskill;
	private int level;
	
	public ActivePlayer(Entity e) {
		this.entity = BukkitAdapter.adapt(e);
		this.usedamageskill=false;
		this.level=0;
	}

	@Override
	public AbstractEntity getEntity() {
		return this.entity;
	}

	@Override
	public AbstractLocation getLocation() {
		return this.entity.getLocation();
	}

	@Override
	public void setUsingDamageSkill(boolean bool) {
		this.usedamageskill=bool;
	}

	@Override
	public boolean isUsingDamageSkill() {
		return this.usedamageskill;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public float getPower() {
		return 0;
	}
}
