package com.gmail.berndivader.mmSkriptAddon.mm400.conditions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;

public class ConditionEntityIsActiveMob extends Condition {
	private Expression<Entity> bukkitentity;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult arg3) {
		bukkitentity = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return null;
	}

	@Override
	public boolean check(final Event e) {
		if (MythicMobs.inst().getMobManager().isActiveMob(BukkitAdapter.adapt(bukkitentity.getSingle(e)))) {
			return true;
		}
		return false;
	}
}
