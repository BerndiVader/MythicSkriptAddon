package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import javax.annotation.Nullable;

import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetWorld extends SimpleExpression<World>{
	private Expression<ActiveMob> activeMob;
	private ActiveMob am;

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
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Nullable
	protected World[] get(Event e) {
		if ((am = activeMob.getSingle(e))==null) return null;
		return new World[]{BukkitAdapter.adapt(am.getEntity().getWorld())};
	}
}
