package com.gmail.berndivader.mythicskript.events.skript;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class MythicSkriptSpawnerSpawnEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	
	private ActiveMob am;
	private MythicSpawner ms;

	public MythicSkriptSpawnerSpawnEvent (MythicSpawner spawner, ActiveMob mob) {
		this.am = mob;
		this.ms = spawner;
	}
	
	public ActiveMob getAm() {
		return this.am;
	}
	public MythicSpawner getMs() {
		return this.ms;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
        return handlers;
    }
}
