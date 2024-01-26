package com.gmail.berndivader.mythicskript.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class GetMobType extends SimpleExpression<String>{
	private Expression<ActiveMob> skriptMob;
	
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
	public boolean init(Expression<?>[] e, int var2, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected String[] get(Event e) {
		return new String[]{skriptMob.getSingle(e).getType().getInternalName()};
	}
}
