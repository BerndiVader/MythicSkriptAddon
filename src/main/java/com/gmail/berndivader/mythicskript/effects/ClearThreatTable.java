package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;

public class ClearThreatTable extends Effect {
	private Expression<ActiveMob> skriptMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int mp, Kleenean var3, ParseResult var4) {
		this.skriptMob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		skriptMob.getSingle(e).getThreatTable().getAllThreatTargets().clear();
	}
}
