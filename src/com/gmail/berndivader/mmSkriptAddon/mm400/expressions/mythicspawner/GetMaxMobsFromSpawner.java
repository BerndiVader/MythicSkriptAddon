package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

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
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		MythicSpawner ms = mythicSpawner.getSingle(e);
		Number amount = bool ? ms.getNumberOfMobs()+ms.getNumberOfCachedMobs() : ms.getMaxMobs();
		return new Number[]{amount};
	}
}
