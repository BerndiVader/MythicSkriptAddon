package com.gmail.berndivader.mythicskript.effects;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class SetDisplayname extends Effect {
	private Expression<String>displayExpr;
	private Expression<ActiveMob>amExpr;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		amExpr=(Expression<ActiveMob>)expr[0];
		displayExpr=(Expression<String>)expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am=amExpr.getSingle(e);
		am.setDisplayName(displayExpr.getSingle(e));
	}
}
