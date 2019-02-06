package com.gmail.berndivader.mythicskript.effects.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class MakeSpawnerSpawn extends Effect {
	private Expression<MythicSpawner> skriptSpawner;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptSpawner = (Expression<MythicSpawner>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	protected void execute(Event e) {
		MythicSpawner ms = skriptSpawner.getSingle(e);
		if (ms==null) return;
		ms.Spawn();
	}
}
