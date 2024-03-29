package com.gmail.berndivader.mythicskript.expressions.mythicspawner;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class GetMaxMobsFromSpawner extends SimpleExpression<Number> {
	private Expression<MythicSpawner> mythicSpawner;
	private Boolean bool;
	
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
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		mythicSpawner = (Expression<MythicSpawner>) expr[0];
		bool = i==0 ? true : false;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Number[] get(Event e) {
		MythicSpawner ms = mythicSpawner.getSingle(e);
		Number amount = bool ? ms.getNumberOfMobs()+ms.getNumberOfCachedMobs() : ms.getMaxMobs().get();
		return new Number[]{amount};
	}
}
