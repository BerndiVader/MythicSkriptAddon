package com.gmail.berndivader.mythicskript.conditions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ConditionEntityIsActiveMob extends Condition {
	private Expression<Entity> bukkitentity;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult arg3) {
		bukkitentity = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	public boolean check(final Event e) {
		return Utils.mythicHelper.isMythicMob(bukkitentity.getSingle(e).getUniqueId());
	}
}
