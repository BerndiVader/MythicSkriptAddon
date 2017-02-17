package com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class AttachMobToSpawner extends Effect {
	private Expression<MythicSpawner> skriptSpawner;
	private Expression<ActiveMob> skriptMob;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptMob = (Expression<ActiveMob>) expr[0];
		skriptSpawner = (Expression<MythicSpawner>) expr[1];
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
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
