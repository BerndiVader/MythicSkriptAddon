package com.gmail.berndivader.mythicskript.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.events.custom.mmMythicMobsSkriptConditionEvent;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ConditionName extends SimpleExpression<String> {

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] e, int var2, Kleenean var3, ParseResult var4) {
		if (!ScriptLoader.isCurrentEvent(mmMythicMobsSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptSkill Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return "condition-name";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		return new String[]{((mmMythicMobsSkriptConditionEvent)e).getName()};
	}
}
