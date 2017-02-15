package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobsSkriptConditionEvent;

import ch.njol.skript.ScriptLoader;
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
		if (!ScriptLoader.isCurrentEvent(mmMythicMobsSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptCondition Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return "condition-meet";
	}

	@Override
	@Nullable
	protected Boolean[] get(Event e) {
		return new Boolean[]{((mmMythicMobsSkriptConditionEvent)e).getBool()};
	}
}
