package com.gmail.berndivader.mythicskript.expressions.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class GetMythicSpawnerByName extends SimpleExpression<MythicSpawner> {
	private Expression<String> spawnerName;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends MythicSpawner> getReturnType() {
		return MythicSpawner.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		spawnerName = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";

	}

	@Override
	protected MythicSpawner[] get(Event e) {
		return new MythicSpawner[] {Utils.mythicMobs.getSpawnerManager().getSpawnerByName(spawnerName.getSingle(e))};
	}
}
