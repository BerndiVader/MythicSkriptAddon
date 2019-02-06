package com.gmail.berndivader.mythicskript.conditions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class ConditionAmHasCustomSpawner extends Condition {
	private Expression<ActiveMob> activeMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		activeMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return null;
	}

	@Override
	public boolean check(Event e) {
		ActiveMob am = activeMob.getSingle(e);
		MythicSpawner ms = am.getSpawner();
		if (ms==null) return false;
		return true;
	}
}
