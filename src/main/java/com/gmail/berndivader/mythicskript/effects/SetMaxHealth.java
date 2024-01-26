package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class SetMaxHealth extends Effect {
	private Expression<ActiveMob> skriptMob;
	private Expression<Number> skriptMaxHealth;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[0];
		skriptMaxHealth = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am=skriptMob.getSingle(e);
		if (am==null) return;
		am.getEntity().setMaxHealth(skriptMaxHealth.getSingle(e).doubleValue());
	}
}
