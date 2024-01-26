package com.gmail.berndivader.mythicskript.expressions;

import javax.annotation.Nullable;

import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

public class GetWorld extends SimpleExpression<World>{
	private Expression<ActiveMob> activeMob;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends World> getReturnType() {
		return World.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		activeMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected World[] get(Event e) {
		ActiveMob am=activeMob.getSingle(e);
		return new World[]{BukkitAdapter.adapt(am.getEntity().getWorld())};
	}
}
