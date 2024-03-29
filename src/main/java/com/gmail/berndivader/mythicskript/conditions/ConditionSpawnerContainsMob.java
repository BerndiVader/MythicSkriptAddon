package com.gmail.berndivader.mythicskript.conditions;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class ConditionSpawnerContainsMob extends Condition {
	private Expression<MythicSpawner> skriptSpawner;
	private Expression<ActiveMob> skriptMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptSpawner = (Expression<MythicSpawner>) expr[0];
		skriptMob = (Expression<ActiveMob>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	public boolean check(Event e) {
		ActiveMob am = skriptMob.getSingle(e);
		MythicSpawner ms = skriptSpawner.getSingle(e);
		if (am==null || ms==null) return false;
		return ms.getAssociatedMobs().contains(am.getUniqueId());
	}

}
