package com.gmail.berndivader.mmSkriptAddon.mm400.expressions.mythicspawner;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class GetMythicSpawners extends SimpleExpression<MythicSpawner> {
	private Expression<String> worldString;
	private boolean all;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends MythicSpawner> getReturnType() {
		return MythicSpawner.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		if (matchedPattern==0) {
			worldString = (Expression<String>) expr[0];
			all = false;
		} else {
			all = true;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected MythicSpawner[] get(Event e) {
		List<MythicSpawner> spawners = new ArrayList<MythicSpawner>();
		if (all) {
			return MythicMobs.inst().getSpawnerManager().getSpawners().toArray(new MythicSpawner[0]);
		} else {
			String wn = worldString.getSingle(e).toLowerCase();
			for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawners()) {
				if (ms.getLocation().getWorld().getName().toLowerCase().equals(wn)) {
					spawners.add(ms);
					continue;
				}
			}
		}
		return spawners.toArray(new MythicSpawner[0]);
	}
}
