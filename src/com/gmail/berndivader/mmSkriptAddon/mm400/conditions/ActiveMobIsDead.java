package com.gmail.berndivader.mmSkriptAddon.mm400.conditions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ActiveMobIsDead extends Condition {
	private Expression<ActiveMob> activeMob;
	private ActiveMob am;
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		activeMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	public boolean check(Event e) {
		if ((am = activeMob.getSingle(e))==null) return true;
		if (am.isDead()) return true;
		return false;
	}
}
