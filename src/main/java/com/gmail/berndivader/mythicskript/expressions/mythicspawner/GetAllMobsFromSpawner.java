package com.gmail.berndivader.mythicskript.expressions.mythicspawner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class GetAllMobsFromSpawner extends SimpleExpression<ActiveMob> {
	private Expression<MythicSpawner> mythicSpawner;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends ActiveMob> getReturnType() {
		return ActiveMob.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		mythicSpawner = (Expression<MythicSpawner>) expr[0];
		return mythicSpawner!=null;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	protected ActiveMob[] get(Event e) {
		List<ActiveMob> ams = new ArrayList<ActiveMob>();
		ActiveMob am = null;
		MythicSpawner ms = mythicSpawner.getSingle(e);
		if (ms==null) return null;
		for (UUID uuid : ms.getAssociatedMobs()) {
			if (Utils.mobManager.getActiveMob(uuid).isPresent()) am = Utils.mobManager.getActiveMob(uuid).get();
			if (am!=null) ams.add(am);
		}
		return ams.toArray(new ActiveMob[0]);
	}
}
