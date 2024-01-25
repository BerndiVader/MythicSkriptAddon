package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationComparisonCondition;

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
