package com.gmail.berndivader.mmSkriptAddon.mm400.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.berndivader.mmSkriptAddon.Main;
import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobSpawnEvent;
import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicSpawnerSpawnEvent;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

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
}
