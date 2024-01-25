package com.gmail.berndivader.mythicskript.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class MeetCondtion extends SimpleExpression<Boolean> {

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	public boolean init(Expression<?>[] e, int var2, Kleenean var3, ParseResult var4) {
		if (!getParser().isCurrentEvent(MythicSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptCondition Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected Boolean[] get(Event e) {
		return new Boolean[]{((MythicSkriptConditionEvent)e).getBool()};
	}
}
