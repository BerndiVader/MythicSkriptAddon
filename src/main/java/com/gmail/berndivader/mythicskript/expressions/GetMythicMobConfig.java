package com.gmail.berndivader.mythicskript.expressions;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class GetMythicMobConfig extends SimpleExpression<String>{
	private Expression<ActiveMob> activemob;
	private Expression<String> mlc;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		mlc = (Expression<String>) expr[0];
		activemob = (Expression<ActiveMob>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected String[] get(Event e) {
		ActiveMob am = activemob.getSingle(e);
		return new String[]{am.getType().getConfig().getString(mlc.getSingle(e))};
	}
}