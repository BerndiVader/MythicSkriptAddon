package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class DropCombat extends Effect {
	private Expression<ActiveMob> activemob;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean var3, ParseResult var4) {
		activemob = (Expression<ActiveMob>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = activemob.getSingle(e);
		if (am.hasThreatTable()) {
			if (am.getThreatTable().getTotalThreat() > 0) {
				am.getThreatTable().getAllThreatTargets().clear();
			}
			am.getThreatTable().dropCombat();
		}
		am.setTarget(null);
	}
}
