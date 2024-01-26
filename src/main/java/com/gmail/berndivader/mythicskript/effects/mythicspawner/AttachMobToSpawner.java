package com.gmail.berndivader.mythicskript.effects.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class AttachMobToSpawner extends Effect {
	private Expression<MythicSpawner> skriptSpawner;
	private Expression<ActiveMob> skriptMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[0];
		skriptSpawner = (Expression<MythicSpawner>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";

	}

	@Override
	protected void execute(Event e) {
		ActiveMob am = skriptMob.getSingle(e);
		MythicSpawner ms = skriptSpawner.getSingle(e);
		if (ms==null || am==null) return;
		if (!ms.getAssociatedMobs().contains(am.getUniqueId())) {
			ms.trackMob(am);
		}
	}
}
