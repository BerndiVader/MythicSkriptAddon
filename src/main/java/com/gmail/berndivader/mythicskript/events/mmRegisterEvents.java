package com.gmail.berndivader.mythicskript.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.berndivader.mythicskript.Main;
import com.gmail.berndivader.mythicskript.events.custom.mmMythicMobSpawnEvent;
import com.gmail.berndivader.mythicskript.events.custom.mmMythicSpawnerSpawnEvent;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;

public class mmRegisterEvents implements Listener {
	private static Plugin plugin = Main.plugin;
	
	public mmRegisterEvents() {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason().equals(SpawnReason.CUSTOM) && !e.isCancelled()) {
			Entity bukkitEntity = e.getEntity();
			new BukkitRunnable() {
				@Override
				public void run() {
					if (!(MythicMobs.inst().getAPIHelper().isMythicMob(bukkitEntity))) return;
					ActiveMob am = MythicMobs.inst().getAPIHelper().getMythicMobInstance(bukkitEntity);
					if (am.getSpawner()!=null) {
						mmMythicSpawnerSpawnEvent e = new mmMythicSpawnerSpawnEvent(am.getSpawner(), am);
						Bukkit.getServer().getPluginManager().callEvent(e);
					}
					mmMythicMobSpawnEvent e = new mmMythicMobSpawnEvent(am);
					Bukkit.getServer().getPluginManager().callEvent(e);
				}
			}.runTaskLater(plugin, 1);
		}
	}
	
	@EventHandler
	public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {
		if (e.getMechanicName().toLowerCase().equals("skriptskill")) {
			SkillMechanic skill;
			if ((skill = new mmSkriptSkill(e.getContainer(), e.getConfig())) != null) e.register(skill);
		}
	}
	@EventHandler
	public void onMythicMobsCustomConditionsLoad(MythicConditionLoadEvent e) {
		if (e.getConditionName().toLowerCase().equals("skriptcondition")) {
			SkillCondition condition;
			if ((condition = new mmSkriptCondition(e.getConditionName(), e.getConfig())) != null) e.register(condition);
		} else if (e.getConditionName().toLowerCase().equals("skriptspawncondition")) {
			SkillCondition condition;
			if ((condition = new mmSkriptSpawnCondition(e.getConditionName(), e.getConfig())) != null) e.register(condition);
		} else if (e.getConditionName().toLowerCase().equals("skripttargetcondition")) {
			SkillCondition condition;
			if ((condition = new mmSkriptTargetCondition(e.getConditionName(), e.getConfig())) != null) e.register(condition);
		}
	}
}
