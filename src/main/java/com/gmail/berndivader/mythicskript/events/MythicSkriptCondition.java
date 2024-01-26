package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;
import io.lumine.mythic.api.skills.conditions.ILocationCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillCondition;
import io.lumine.mythic.core.skills.conditions.ConditionAction;

public class MythicSkriptCondition extends SkillCondition 
implements 
IEntityCondition, 
ILocationCondition {
	private String skConditionName;
	private String skConditionArgs;
	private Boolean bool;

	public MythicSkriptCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION = ConditionAction.REQUIRED;
		this.skConditionName = mlc.getString(new String[]{"condition","c"},"");
		this.skConditionArgs = mlc.getString(new String[]{"args", "a"},"");
		this.bool = false;
	}

	@Override
	public boolean check(AbstractLocation t) {
		Location caster = BukkitAdapter.adapt(t);
		Location target = BukkitAdapter.adapt(t);
		MythicSkriptConditionEvent e = new MythicSkriptConditionEvent(caster, target, this.skConditionName, this.skConditionArgs, this.bool);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return e.getBool();
	}

	@Override
	public boolean check(AbstractEntity t) {
		Entity caster = BukkitAdapter.adapt(t);
		Entity target = BukkitAdapter.adapt(t);
		MythicSkriptConditionEvent e = new MythicSkriptConditionEvent(caster, target, this.skConditionName, this.skConditionArgs, this.bool);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return e.getBool();
	}
}
