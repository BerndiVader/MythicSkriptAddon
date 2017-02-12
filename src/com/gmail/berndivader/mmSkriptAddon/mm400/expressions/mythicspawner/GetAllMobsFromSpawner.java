package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.mythicspawner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

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
		return false;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected ActiveMob[] get(Event e) {
		List<ActiveMob> ams = new ArrayList<ActiveMob>();
		ActiveMob am;
		MythicSpawner ms = mythicSpawner.getSingle(e);
		if (ms==null) return null;
		for (UUID uuid : ms.mobs) {
			am = MythicMobs.inst().getMobManager().getActiveMob(uuid).get();
			if (am!=null) ams.add(am);
		}
		return ams.toArray(new ActiveMob[0]);
	}
}
