package com.gmail.berndivader.mmSkriptAddon.mm400.conditions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

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
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	public boolean check(Event e) {
		ActiveMob am = skriptMob.getSingle(e);
		MythicSpawner ms = skriptSpawner.getSingle(e);
		if (am==null || ms==null) return false;
		if (ms.getAssociatedMobs().contains(am.getUniqueId())) return true;
		return false;
	}

}
