package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobsSkriptConditionEvent;

import ch.njol.skript.ScriptLoader;
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
		if (!ScriptLoader.isCurrentEvent(mmMythicMobsSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptSkill Event!");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return "condition-location";
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		return new Location[]{((mmMythicMobsSkriptConditionEvent)e).getLocation()};
	}
}
