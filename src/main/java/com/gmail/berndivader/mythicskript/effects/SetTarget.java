package com.gmail.berndivader.mythicskript.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		ActiveMob am;
		if ((am = activeMob.getSingle(e)).isDead()) return;
		AbstractEntity target = BukkitAdapter.adapt(bukkitEntity.getSingle(e));
		if (!target.isLiving()) return;
		if (am.hasThreatTable()) {
			if (am.getThreatTable().targetEvent(target)) return;
		}
		am.setTarget(target);
	}
}
