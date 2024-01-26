package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityComparisonCondition;
import io.lumine.mythic.api.skills.conditions.ILocationComparisonCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillCondition;
import io.lumine.mythic.core.skills.conditions.ConditionAction;

public class MythicSkriptTargetCondition extends SkillCondition 
implements 
IEntityComparisonCondition, 
ILocationComparisonCondition {
	private String skConditionName;
	private String skConditionArgs;
	private Boolean bool;

	public MythicSkriptTargetCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION = ConditionAction.REQUIRED;
		this.skConditionName = mlc.getString(new String[]{"condition","c"},"");
		this.skConditionArgs = mlc.getString(new String[]{"args", "a"},"");
		this.bool = false;
	}

	@Override
	public boolean check(AbstractLocation c, AbstractLocation t) {
		Location caster = BukkitAdapter.adapt(c);
		Location target = BukkitAdapter.adapt(t);
		MythicSkriptConditionEvent e = new MythicSkriptConditionEvent(caster, target, this.skConditionName, this.skConditionArgs, this.bool);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return e.getBool();
	}

	@Override
	public boolean check(AbstractEntity c, AbstractEntity t) {
		Entity caster = BukkitAdapter.adapt(c);
		Entity target = BukkitAdapter.adapt(t);
		MythicSkriptConditionEvent e = new MythicSkriptConditionEvent(caster, target, this.skConditionName, this.skConditionArgs, this.bool);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return e.getBool();
	}
}
