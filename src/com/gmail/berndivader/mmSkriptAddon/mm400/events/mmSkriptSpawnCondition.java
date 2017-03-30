package com.gmail.berndivader.mmSkriptAddon.mm400.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobsSkriptConditionEvent;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

public class mmSkriptSpawnCondition extends SkillCondition implements ILocationCondition {
	private String skConditionName;
	private String skConditionArgs;
	private Boolean bool;

	public mmSkriptSpawnCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION = ConditionAction.REQUIRED;
		this.skConditionName = mlc.getString(new String[]{"condition","c"},"");
		this.skConditionArgs = mlc.getString(new String[]{"args", "a"},"");
		this.bool = true;
	}

	@Override
	public boolean check(AbstractLocation loc) {
		Location l = BukkitAdapter.adapt(loc);
		mmMythicMobsSkriptConditionEvent e = new mmMythicMobsSkriptConditionEvent(null, l, this.skConditionName, this.skConditionArgs, this.bool);
		Bukkit.getServer().getPluginManager().callEvent(e);
		return e.getBool();
	}
}
