package com.gmail.berndivader.mythicskript.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ConditionLocation extends SimpleExpression<Location> {

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean init(Expression<?>[] e, int var2, Kleenean var3, ParseResult var4) {
		if (!getParser().isCurrentEvent(MythicSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptSkill Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Location[] get(Event e) {
		return new Location[]{((MythicSkriptConditionEvent)e).getCasterLocation()};
	}
}
