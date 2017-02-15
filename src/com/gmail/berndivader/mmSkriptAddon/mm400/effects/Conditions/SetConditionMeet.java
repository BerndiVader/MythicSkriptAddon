package com.gmail.berndivader.mmSkriptAddon.mm400.effects.Conditions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mmSkriptAddon.mm400.events.custom.mmMythicMobsSkriptConditionEvent;

import ch.njol.skript.ScriptLoader;
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
		if (!ScriptLoader.isCurrentEvent(mmMythicMobsSkriptConditionEvent.class)) {
			Skript.error("Only allowed in SkriptCondition Event!");
			return false;
		}
		bool = (Expression<Boolean>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		// TODO Auto-generated method stub
		return "set condition-meet";
	}

	@Override
	protected void execute(Event e) {
		((mmMythicMobsSkriptConditionEvent)e).setBool(bool.getSingle(e));
	}
}
