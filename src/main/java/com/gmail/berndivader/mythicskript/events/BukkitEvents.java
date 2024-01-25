package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.berndivader.mythicskript.MythicSkript;
import com.gmail.berndivader.mythicskript.Utils;
import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSpawnEvent;
import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSpawnerSpawnEvent;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;

public class BukkitEvents implements Listener {
	
	public BukkitEvents() {
		MythicSkript.plugin.getServer().getPluginManager().registerEvents(this,MythicSkript.plugin);
	}

	@EventHandler
	public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason().equals(SpawnReason.CUSTOM) && !e.isCancelled()) {
			Entity bukkitEntity = e.getEntity();
			new BukkitRunnable() {
				@Override
				public void run() {
					if (!(Utils.mythicHelper.isMythicMob(bukkitEntity))) return;
					ActiveMob am = Utils.mythicHelper.getMythicMobInstance(bukkitEntity);
					if (am.getSpawner()!=null) {
						MythicSkriptSpawnerSpawnEvent e = new MythicSkriptSpawnerSpawnEvent(am.getSpawner(), am);
						Bukkit.getServer().getPluginManager().callEvent(e);
					}
					MythicSkriptSpawnEvent e = new MythicSkriptSpawnEvent(am);
					Bukkit.getServer().getPluginManager().callEvent(e);
				}
			}.runTaskLater(MythicSkript.plugin, 1l);
		}
	}
	
	@EventHandler
	public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {
		if (e.getMechanicName().toLowerCase().equals("skriptskill")) {
			SkillMechanic skill;
			if ((skill = new MythicSkriptSkill(e.getContainer(), e.getConfig())) != null) e.register(skill);
		}
	}
	@EventHandler
	public void onMythicMobsCustomConditionsLoad(MythicConditionLoadEvent e) {
		if (e.getConditionName().toLowerCase().equals("skriptcondition")) {
			SkillCondition condition;
			if ((condition = new MythicSkriptCondition(e.getConditionName(), e.getConfig())) != null) e.register(condition);
		} else if (e.getConditionName().toLowerCase().equals("skriptspawncondition")) {
			SkillCondition condition;
			if ((condition = new MythicSkriptSpawnCondition(e.getConditionName(), e.getConfig())) != null) e.register(condition);
		} else if (e.getConditionName().toLowerCase().equals("skripttargetcondition")) {
			SkillCondition condition;
			if ((condition = new MythicSkriptTargetCondition(e.getConditionName(), e.getConfig())) != null) e.register(condition);
		}
	}
}
