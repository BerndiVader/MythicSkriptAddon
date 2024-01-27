package com.gmail.berndivader.mythicskript.expressions.mythicspawner;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class SpawnerLocation extends SimpleExpression<Location> {
	private Expression<MythicSpawner> mythicSpawner;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		mythicSpawner = (Expression<MythicSpawner>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		Location loc = BukkitAdapter.adapt(mythicSpawner.getSingle(e).getLocation());
		if (loc!=null) return new Location[]{loc};
		return null;
	}
}
