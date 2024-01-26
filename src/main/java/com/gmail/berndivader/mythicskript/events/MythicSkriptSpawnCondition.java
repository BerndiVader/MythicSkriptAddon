package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.ILocationCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillCondition;
import io.lumine.mythic.core.skills.conditions.ConditionAction;

public class MythicSkriptSpawnCondition extends SkillCondition implements ILocationCondition {
	private String skConditionName;
	private String skConditionArgs;
	private Boolean bool;

	public MythicSkriptSpawnCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION = ConditionAction.REQUIRED;
		this.skConditionName = mlc.getString(new String[]{"condition","c"},"");
		this.skConditionArgs = mlc.getString(new String[]{"args", "a"},"");
		this.bool = true;
	}

	@Override
	public boolean check(AbstractLocation loc) {
		Location l = BukkitAdapter.adapt(loc);
		MythicSkriptConditionEvent e = new MythicSkriptConditionEvent(null, l, this.skConditionName, this.skConditionArgs, this.bool);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return e.getBool();
	}
}
