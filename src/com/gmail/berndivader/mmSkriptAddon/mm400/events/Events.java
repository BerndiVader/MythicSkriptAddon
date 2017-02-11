package com.gmail.berndivader.mmSkriptAddon.mm400.events;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobSpawnEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class Events {
	
	public static void register() {
		// register custom events
		new mmRegisterEvents();

		// register skript events
		Skript.registerEvent("MythicMobSpawnEvent", SimpleEvent.class, mmMythicMobSpawnEvent.class, "activemob spawnevent");
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
	}
}
