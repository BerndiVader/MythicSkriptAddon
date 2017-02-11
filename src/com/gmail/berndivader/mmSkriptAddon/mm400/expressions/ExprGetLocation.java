package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetLocation extends SimpleExpression<Location> {
	private Expression<ActiveMob> activemob;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		activemob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected Location[] get(Event e) {
		return new Location[]{BukkitAdapter.adapt(activemob.getSingle(e).getLocation())};
	}
}
