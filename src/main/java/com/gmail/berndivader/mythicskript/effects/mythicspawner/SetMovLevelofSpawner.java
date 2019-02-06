package com.gmail.berndivader.mythicskript.effects.mythicspawner;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
import io.lumine.xikage.mythicmobs.util.types.RandomInt;

public class SetMovLevelofSpawner extends Effect {
	private Expression<MythicSpawner> skriptSpawner;
	private Expression<Number> skriptMobLevel;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int var2, Kleenean var3, ParseResult var4) {
		skriptSpawner = (Expression<MythicSpawner>) expr[0];
		skriptMobLevel = (Expression<Number>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		MythicSpawner ms = skriptSpawner.getSingle(e);
		if (ms==null) return;
		ms.setMobLevel(new RandomInt(skriptMobLevel.getSingle(e).intValue()));
	}
}
