package com.gmail.berndivader.mythicskript.effects.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class WarmupMythicSpawner extends Effect {
	private Expression<MythicSpawner> mythicSpawner;
	private Expression<Number> seconds;
	private Boolean bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		mythicSpawner = (Expression<MythicSpawner>) expr[0];
		bool=i==0;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		MythicSpawner ms = mythicSpawner.getSingle(e);
		if (bool) {
			ms.setWarmupSeconds(seconds.getSingle(e).intValue());
		} else ms.setRemainingWarmupSeconds(seconds.getSingle(e).longValue());
	}
}
