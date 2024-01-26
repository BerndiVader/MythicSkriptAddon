package com.gmail.berndivader.mythicskript.expressions.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class GetSpawnerWorld extends SimpleExpression<World> {
	private Expression<MythicSpawner> mythicSpawner;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends World> getReturnType() {
		return World.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		mythicSpawner = (Expression<MythicSpawner>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";

	}

	@Override
	@Nullable
	protected World[] get(Event e) {
		World world = BukkitAdapter.adapt(mythicSpawner.getSingle(e).getLocation().getWorld());
		if (world==null) return null;
		return new World[]{world};
	}
}
