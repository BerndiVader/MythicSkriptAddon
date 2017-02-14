package com.gmail.berndivader.mmSkriptAddon.mm400.events;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobSpawnEvent;
import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicSpawnerSpawnEvent;
import com.gmail.berndivader.mmSkriptAddon.mm400.classes.*;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class Events {
	
	public static void register() {
		// register custom events
		new mmRegisterEvents();

		// register skript events
		Skript.registerEvent("MythicMobSpawnEvent", SimpleEvent.class, mmMythicMobSpawnEvent.class, "mythicmob spawnevent");
		EventValues.registerEventValue(mmMythicMobSpawnEvent.class, ActiveMob.class, new Getter<ActiveMob, mmMythicMobSpawnEvent>() {
			@Override
			@Nullable
			public ActiveMob get(mmMythicMobSpawnEvent e) {
				return e.getActiveMob();
			}
		}, 0);
		EventValues.registerEventValue(mmMythicMobSpawnEvent.class, Entity.class, new Getter<Entity, mmMythicMobSpawnEvent>() {
			@Override
			@Nullable
			public Entity get(mmMythicMobSpawnEvent e) {
				return e.getEntity();
			}
		}, 0);
		Skript.registerEvent("MythicMobDeathEvent", SimpleEvent.class, MythicMobDeathEvent.class, "mythicmob deathevent");
		EventValues.registerEventValue(MythicMobDeathEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobDeathEvent>() {
			@Override
			@Nullable
			public ActiveMob get(MythicMobDeathEvent e) {
				return e.getMob();
			}}, 0);
		EventValues.registerEventValue(MythicMobDeathEvent.class, MythicDrops.class, new Getter<MythicDrops, MythicMobDeathEvent>() {
			@Override
			@Nullable
			public MythicDrops get(MythicMobDeathEvent e) {
				MythicDrops md = new MythicDrops(e.getDrops());
				return md;
			}}, 0);
		Skript.registerEvent("MythicSpawnerSpawnEvent", SimpleEvent.class, mmMythicSpawnerSpawnEvent.class, "mythicspawner spawnevent");
		EventValues.registerEventValue(mmMythicSpawnerSpawnEvent.class, MythicSpawner.class, new Getter<MythicSpawner, mmMythicSpawnerSpawnEvent>() {
			@Override
			@Nullable
			public MythicSpawner get(mmMythicSpawnerSpawnEvent e) {
				return e.getMs();
			}}, 0);
		EventValues.registerEventValue(mmMythicSpawnerSpawnEvent.class, ActiveMob.class, new Getter<ActiveMob, mmMythicSpawnerSpawnEvent>() {
			@Override
			@Nullable
			public ActiveMob get(mmMythicSpawnerSpawnEvent e) {
				return e.getAm();
			}}, 0);
	}
}
