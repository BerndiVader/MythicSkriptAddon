package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetDisplayName extends SimpleExpression<String>{
	private Expression<ActiveMob> activeMob;

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
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		activeMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		ActiveMob am = activeMob.getSingle(e);
		if (am.getType().getDisplayName().isEmpty()) return null;
		return new String[]{am.getType().getDisplayName()};
	}
}
