package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

public class SetTarget extends Effect {
	private Expression<Entity> bukkitEntity;
	private Expression<ActiveMob> activeMob;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		bukkitEntity = (Expression<Entity>) expr[0];
		activeMob = (Expression<ActiveMob>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am=activeMob.getSingle(e);
		if (am.isDead()) return;
		AbstractEntity target = BukkitAdapter.adapt(bukkitEntity.getSingle(e));
		if (!target.isLiving()) return;
		if (am.hasThreatTable()) {
			if (am.getThreatTable().targetEvent(target)) return;
		}
		am.setTarget(target);
	}
}
