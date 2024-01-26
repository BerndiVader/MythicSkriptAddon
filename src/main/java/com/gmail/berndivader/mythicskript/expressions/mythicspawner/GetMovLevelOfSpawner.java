package com.gmail.berndivader.mythicskript.expressions.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class GetMovLevelOfSpawner extends SimpleExpression<Number> {
	private Expression<MythicSpawner> skriptSpawner;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptSpawner = (Expression<MythicSpawner>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Number[] get(Event e) {
		if (!(skriptSpawner.getSingle(e) instanceof MythicSpawner)) return null;
		return new Number[]{skriptSpawner.getSingle(e).getMobLevel().get()};
	}
}
