package com.gmail.berndivader.mythicskript.effects.conditions;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetConditionMeet extends Effect {
	private Expression<Boolean> bool;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int var2, Kleenean var3, ParseResult var4) {
		if (!getParser().isCurrentEvent(MythicSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptCondition Event!");
			return false;
		}
		bool = (Expression<Boolean>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		((MythicSkriptConditionEvent)e).setBool(bool.getSingle(e));
	}
}
