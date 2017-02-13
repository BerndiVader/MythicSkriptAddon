package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class SetHealth extends Effect {
	private Expression<ActiveMob> skriptMob;
	private Expression<Number> skriptHealth;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[0];
		skriptHealth = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am=null;
		if ((am=skriptMob.getSingle(e))==null) return;
		am.getEntity().setHealth(skriptHealth.getSingle(e).doubleValue());
	//		am.getEntity().setHealth(Math.min(am.getEntity().getMaxHealth(),skriptHealth.getSingle(e).doubleValue()));
	}
}
